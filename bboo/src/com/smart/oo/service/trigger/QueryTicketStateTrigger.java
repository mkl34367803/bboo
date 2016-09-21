package com.smart.oo.service.trigger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.api.util.StringUtils;
import com.smart.comm.entity.BaseAccountEntity;
import com.smart.comm.entity.BaseOfficeEntity;
import com.smart.comm.entity.KeyValEntity;
import com.smart.comm.entity.TicketFileEntity;
import com.smart.comm.entity.TicketStateEntity;
import com.smart.comm.utils.OOComms;
import com.smart.comm.utils.OOLogUtil;
import com.smart.comm.utils.ObjectToObiectUtils;
import com.smart.oo.service.imp.FactoryServiceImpl;
import com.smart.oo.vo.DetrInfoResVO;
import com.smart.utils.DateUtils;

/**
 * 定时 查询客票状态
 * 
 * @author eric
 * 
 */
@Service("BBOOQueryTicketStateTrigger")
public class QueryTicketStateTrigger {

	@Autowired
	private FactoryServiceImpl serviceF;

	private static final Integer TICKETFILESTATE = 1;

	public void syncdata() {
		List<TicketFileEntity> datas = null;
		try {
			datas = getTicketFileData(TICKETFILESTATE);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String startTime = DateUtils.getDateys();
		if (datas != null && datas.size() > 0) {
			ExecutorService exec = Executors.newCachedThreadPool();
			Future<String> f = null;
			List<Future<String>> flist = new ArrayList<Future<String>>();
			final Semaphore semp = new Semaphore(datas.size() + 1);
			Callable<String> call = null;
			final Map<String, KeyValEntity> kv = getKeyVals();
			final List<BaseAccountEntity> accounts = getAccounts();
			final List<BaseOfficeEntity> offobjs = getOffices();

			for (final TicketFileEntity fl : datas) {
				call = new Callable<String>() {
					@Override
					public String call() {
						// 获取许可
						try {
							semp.acquire();
							syncdodata(fl, kv, accounts, offobjs);
							// 访问完后，释放
							semp.release();
						} catch (Exception e) {
							e.printStackTrace();
						}
						return "true";
					}
				};
				f = exec.submit(call);
				flist.add(f);

			}
			exec.shutdown();
			if (flist.size() > 0) {
				while (!exec.isTerminated()) {
					// 等待所有子线程结束，才退出主线程
					OOLogUtil.info("查询客票状态任务正在进行中...",
							QueryTicketStateTrigger.class, null);
					try {
						exec.awaitTermination(30, TimeUnit.SECONDS);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				OOLogUtil.info("查询客票状态任务结束..." + "START-TIME:" + startTime
						+ "END-TIME:" + DateUtils.getDateys(),
						QueryTicketStateTrigger.class, null);
				exec.shutdownNow();
			}
		} else {
			OOLogUtil.info("查询客票状态任务开启无数据...", QueryTicketStateTrigger.class,
					null);
		}
	}

	public DetrInfoResVO getTicketInfo(TicketStateEntity t) {
		Map<String, KeyValEntity> kv = getKeyVals();
		List<BaseAccountEntity> accounts = getAccounts();
		List<BaseOfficeEntity> offobjs = getOffices();
		return dodata(t, kv, accounts, offobjs);
	}

	private DetrInfoResVO dodata(TicketStateEntity t,
			Map<String, KeyValEntity> kv, List<BaseAccountEntity> accounts,
			List<BaseOfficeEntity> offobjs) {
		DetrInfoResVO res = serviceF.getIticketStateService().detrByApi(t, kv,
				accounts, offobjs, "tn");
		return res;
	}

	private void syncdodata(final TicketFileEntity f,
			Map<String, KeyValEntity> kv, List<BaseAccountEntity> accounts,
			List<BaseOfficeEntity> offobjs) {
		List<TicketStateEntity> datas = null;
		try {
			datas = getTicketStateData(f.getFileno(), f.getMno());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int total = 0;
		int query = 0;
		int notquery = 0;
		int succ = 0;
		int errc = 0;
		if (accounts == null || accounts.size() == 0) {
			f.setScanSpecification("accounts is null");
		} else {
			if (datas != null && datas.size() > 0) {
				total = datas.size();
				Iterator<TicketStateEntity> iterator = datas.iterator();
				while (iterator.hasNext()) {
					query++;
					TicketStateEntity t = iterator.next();
					boolean isquery = isQuery(t);
					if (isquery) {
						DetrInfoResVO res = serviceF.getIticketStateService()
								.detrByApi(t, kv, accounts, offobjs, "tn");
						TicketStateEntity newt = ObjectToObiectUtils
								.toTicketStateEntity(res, t);
						if (newt != null) {
							if (2 == newt.getQueryok()) {
								succ++;
							} else {
								errc++;
							}
						} else {
							errc++;
						}
						try {
							serviceF.getIticketStateService().updateDataById(
									newt);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						f.setLastTime(DateUtils.getDateys());
						f.setScanSpecification("合计：" + total + "条;已查询：" + query
								+ "条[S:" + succ + ",F:" + errc + ",N:"
								+ notquery + "]" + ";还剩余：" + (total - query));
						try {
							serviceF.getIticketFileService().updateDataById(f);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						notquery++;
					}
					iterator.remove();
				}
			}
			f.setScanSpecification("合计：" + total + "条;已查询：" + query + "条[S:"
					+ succ + ",F:" + errc + ",N:" + notquery + "]" + ";还剩余：0");
		}
		// 修改文件状态
		f.setLastTime(DateUtils.getDateys());
		f.setScanStatus(2);
		try {
			serviceF.getIticketFileService().updateDataById(f);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean isQuery(TicketStateEntity t) {

		if (t.getMessages() != null
				&& t.getMessages().contains("ET TICKET NUMBER IS NOT EXIST")) {
			return false;
		}
		if (t.getState() == null || StringUtils.isEmpty(t.getState())) {
			return true;
		} else if (t.getState().toUpperCase().trim().endsWith("OPEN")) {
			return true;
		} else if (t.getState().toUpperCase().trim().endsWith("REFUNDED")) {
			return true;
		} else if (t.getState().toUpperCase().trim().endsWith("CHECKED")) {
			return true;
		} else if (t.getState().toUpperCase().trim().endsWith("EXCHANGED")) {
			return true;
		} else if (t.getState().toUpperCase().trim().endsWith("SUSPENDED")) {
			return true;
		}
		return false;
	}

	private Map<String, KeyValEntity> getKeyVals() {
		List<KeyValEntity> list = null;
		try {
			list = serviceF.getIkeyValService().findDataList(
					OOComms.CACHE_KEY_VAL_DATAS_KEY, true);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Map<String, KeyValEntity> map = new HashMap<String, KeyValEntity>();
		if (list != null && list.size() > 0) {
			for (KeyValEntity k : list) {
				map.put(k.getK(), k);
			}
		}
		return map;
	}

	private List<BaseAccountEntity> getAccounts() {

		List<BaseAccountEntity> accList = null;
		try {
			accList = serviceF.getBaseAccountService().queryBaseAccounts();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (accList != null && accList.size() > 0) {
			Iterator<BaseAccountEntity> iterator = accList.iterator();
			while (iterator.hasNext()) {
				BaseAccountEntity acc = iterator.next();
				if (acc.getIsu() != null && acc.getIsu() == 2) {
					iterator.remove();
				}
			}
		}
		return accList;
	}

	private List<BaseOfficeEntity> getOffices() {

		List<BaseOfficeEntity> accList = null;
		try {
			accList = serviceF.getBaseOfficeService().findDataList(
					OOComms.CACHE_OFF_VAL_DATAS_NAME,
					OOComms.CACHE_OFF_VAL_DATAS_KEY, true);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return accList;
	}

	private List<TicketStateEntity> getTicketStateData(String fileno, String mno)
			throws Exception {
		TicketStateEntity ticke = new TicketStateEntity();
		ticke.setFileno(fileno);
		ticke.setMno(mno);
		List<TicketStateEntity> list = serviceF.getIticketStateService()
				.findDatas(ticke);
		return list;
	}

	private List<TicketFileEntity> getTicketFileData(int state)
			throws Exception {
		TicketFileEntity ticke = new TicketFileEntity();
		ticke.setScanStatus(state);
		List<TicketFileEntity> list = serviceF.getIticketFileService()
				.findDatas(ticke);
		return list;
	}

}
