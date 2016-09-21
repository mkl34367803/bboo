package com.smart.oo.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.comm.entity.BaseAccountEntity;
import com.smart.comm.entity.BaseOfficeEntity;
import com.smart.comm.entity.KeyValEntity;
import com.smart.comm.entity.TicketFileEntity;
import com.smart.comm.entity.TicketStateEntity;
import com.smart.comm.utils.OOComms;
import com.smart.comm.utils.OOLogUtil;
import com.smart.comm.utils.ObjectToObiectUtils;
import com.smart.framework.base.Page;
import com.smart.oo.dao.imp.FactoryDaoImpl;
import com.smart.oo.domain.DetnDomain;
import com.smart.oo.domain.DetrBy517Domain;
import com.smart.oo.domain.DetrByTnoDomain;
import com.smart.oo.domain.OpenApi517AccountVO;
import com.smart.oo.domain.TfareAccVO;
import com.smart.oo.domain.TfareDetrDomain;
import com.smart.oo.domain.res.DetnResult;
import com.smart.oo.domain.res.DetrBy517Result;
import com.smart.oo.domain.res.DetrByTnoResult;
import com.smart.oo.domain.res.TfareDetrResult;
import com.smart.oo.domain.res.TicketInfo;
import com.smart.oo.service.ITicketStateService;
import com.smart.oo.service.api.AllApi;
import com.smart.oo.vo.DetrFltVO;
import com.smart.oo.vo.DetrInfoResVO;
import com.smart.utils.StringUtils;

@Service("GTicketStateServiceImpl")
public class TicketStateServiceImpl implements ITicketStateService {

	@Autowired
	private FactoryDaoImpl daof;

	public static boolean QUERYXTYDETR = true;

	public DetrInfoResVO detrByApi(TicketStateEntity ticket,
			Map<String, KeyValEntity> kv, List<BaseAccountEntity> accounts,
			List<BaseOfficeEntity> offobjs, String rawType) {
		AllApi api = new AllApi();
		DetrInfoResVO resV = null;
		boolean bol = false;
		if (ticket != null && StringUtils.isNotEmpty(ticket.getNo())) {
			// 方案1 走信天游
			if (StringUtils.isNotEmpty(ticket.getPassengerName())
					&& QUERYXTYDETR) {
				if (false && rawType != null && rawType.equalsIgnoreCase("tn")) {
					KeyValEntity kvobj = kv.get(OOComms.GET_DETR_BY_XTY_URL);
					if (kvobj != null) {
						OOLogUtil.info(ticket.getNo() + ";提取票号方案1，信天游获取",
								TicketStateServiceImpl.class, null);
						TicketInfo xtyRs = api.detr_xtyForObj(
								ticket.getPassengerName(), ticket.getNo(),
								kvobj.getV(), OOComms.GET_DETR_INFO_BY_XTY);
						if (xtyRs != null && xtyRs.getMsg() != null
								&& xtyRs.getMsg().contains("IP暂时被停用")) {
							QUERYXTYDETR = false;
						}
						resV = ObjectToObiectUtils.toDetrInfoResVO(xtyRs,
								ticket);
					}
				}
			}
			bol = resV != null && detrisok(resV.getData());
			if (!bol) {
				// 方案2 TFARE
				if (resV == null || resV.getFlt() == null
						|| resV.getFlt().size() == 0
						|| "0".equals(resV.getCode())) {
					if (rawType != null && rawType.equalsIgnoreCase("tn")) {
						BaseAccountEntity tac = null;
						if (accounts != null && accounts.size() > 0) {
							for (BaseAccountEntity c : accounts) {
								if ("TFARE_GDS"
										.equalsIgnoreCase(c.getAcctype())
										&& ticket.getMno().equalsIgnoreCase(
												c.getMno())) {
									tac = c;
								}
							}
						}
						if (tac != null) {
							KeyValEntity kvobj = kv
									.get(OOComms.GET_API_COMM_URL);
							if (kvobj != null) {
								OOLogUtil.info(ticket.getNo()
										+ ";提取票号方案2，TFARE获取",
										TicketStateServiceImpl.class, null);
								TfareDetrDomain tfareV = new TfareDetrDomain();
								TfareAccVO tfareAcc = new TfareAccVO();
								tfareAcc.setUserAct(tac.getUserName());
								tfareAcc.setUserPwd(tac.getSecret());
								tfareAcc.setUserKey(tac.getAppkey());
								tfareV.setAccount(tfareAcc);
								tfareV.setInstructionType(StringUtils
										.isNotEmpty(rawType) ? rawType : "tn");
								tfareV.setInstructionValue(ticket.getNo());
								TfareDetrResult tfareRs = api.detr_tfareForObj(
										tfareV, kvobj.getV(),
										OOComms.GET_DETR_INFO_BY_TFARE);
								resV = ObjectToObiectUtils.toDetrInfoResVO(
										tfareRs, ticket);
							}
						} else {
							OOLogUtil.info(ticket.getNo()
									+ ";提取票号方案2，TFARE获取,账号信息不存在",
									TicketStateServiceImpl.class, null);
						}
					}
				} else {
					OOLogUtil.info(ticket.getNo() + ";提取票号方案1，信天游获取,"
							+ (resV != null ? resV.getData() : ""),
							TicketStateServiceImpl.class, null);
				}
			} else {
				OOLogUtil.info(ticket.getNo() + ";提取票号方案2，TFARE获取,不在优先获取队列中",
						TicketStateServiceImpl.class, null);
			}
			bol = resV != null && detrisok(resV.getData());
			if (!bol) {
				// 方案3 517
				if (resV == null || resV.getFlt() == null
						|| resV.getFlt().size() == 0
						|| "0".equals(resV.getCode())) {
					if (StringUtils.isNotEmpty(ticket.getPassengerName())) {
						if (rawType != null && rawType.equalsIgnoreCase("tn")) {
							BaseAccountEntity tac = null;
							if (accounts != null && accounts.size() > 0) {
								for (BaseAccountEntity c : accounts) {
									if ("517NA".equals(c.getAcctype())
											&& ticket.getMno()
													.equalsIgnoreCase(
															c.getMno())) {
										tac = c;
									}
								}
							}
							if (tac != null) {
								KeyValEntity kvobj = kv
										.get(OOComms.GET_API_COMM_URL);
								if (kvobj != null) {
									OOLogUtil.info(ticket.getNo()
											+ ";提取票号方案3，517获取",
											TicketStateServiceImpl.class, null);
									OpenApi517AccountVO account = new OpenApi517AccountVO();
									account.setAccno(tac.getUserName());
									account.setPid(tac.getAppkey());
									account.setPwd(tac.getSecret());
									account.setScode(tac.getCodes());
									DetrBy517Domain by517V = new DetrBy517Domain();
									by517V.setAccount(account);
									by517V.setPassengerName(ticket
											.getPassengerName());
									by517V.setTicketNo(ticket.getNo());
									DetrBy517Result by517Rs = api
											.detr_517ForObj(
													by517V,
													kvobj.getV(),
													OOComms.GET_DETR_INFO_BY_517);
									if (by517Rs == null
											|| (by517Rs.getMsg() != null && by517Rs
													.getMsg().contains(
															"接口返回空异常"))) {
										by517Rs = api.detr_517ForObj(by517V,
												kvobj.getV(),
												OOComms.GET_DETR_INFO_BY_517);
									}
									resV = ObjectToObiectUtils.toDetrInfoResVO(
											by517Rs, ticket);
								}
							}
						}
					}
				}
			}
			bol = resV != null && detrisok(resV.getData());
			if (!bol) {
				// 方案4 detr tn
				if (resV == null || resV.getFlt() == null
						|| resV.getFlt().size() == 0
						|| "0".equals(resV.getCode())) {
					if (rawType != null && rawType.equalsIgnoreCase("tn")) {
						BaseOfficeEntity offobj = null;
						if (offobjs != null && offobjs.size() > 0) {
							for (BaseOfficeEntity o : offobjs) {
								if (o.getOfftypes() != null
										&& o.getOfftypes().toLowerCase()
												.contains("detr")
										&& ticket.getMno().equalsIgnoreCase(
												o.getMno()))
									offobj = o;
							}
						}
						if (offobj != null) {
							KeyValEntity kvobj = kv
									.get(OOComms.GET_API_COMM_URL);
							if (kvobj != null) {
								OOLogUtil.info(ticket.getNo()
										+ ";提取票号方案4，DETR TN获取",
										TicketStateServiceImpl.class, null);
								DetrByTnoDomain detnV = new DetrByTnoDomain();
								detnV.setAppKey(offobj.getAppkey());
								detnV.setTicketNo(ticket.getNo());
								detnV.setTermId(String.valueOf(System
										.currentTimeMillis()));
								DetrByTnoResult detnRs = null;
								try {
									detnRs = api.detr_byno_gds(detnV,
											kvobj.getV(),
											OOComms.GET_DETR_INFO_BY_GDSDETRTN);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								if (detnRs != null) {
									resV = ObjectToObiectUtils.toDetrInfoResVO(
											detnRs, ticket);
								}
							}
						}
					}
				}
			}
			bol = resV != null && detrisok(resV.getData());
			if (false && !bol) {
				// 方案5 detn
				if (resV == null || resV.getFlt() == null
						|| resV.getFlt().size() == 0
						|| "0".equals(resV.getCode())) {
					if (rawType != null && rawType.equalsIgnoreCase("tn")) {
						BaseOfficeEntity offobj = null;
						if (offobjs != null && offobjs.size() > 0) {
							for (BaseOfficeEntity o : offobjs) {
								if (o.getOfftypes() != null
										&& o.getOfftypes().toLowerCase()
												.contains("detn")
										&& ticket.getMno().equalsIgnoreCase(
												o.getMno()))
									offobj = o;
							}
						}
						if (offobj != null) {
							KeyValEntity kvobj = kv
									.get(OOComms.GET_API_COMM_URL);
							if (kvobj != null) {
								OOLogUtil.info(ticket.getNo()
										+ ";提取票号方案5，DETN获取",
										TicketStateServiceImpl.class, null);
								DetnDomain detnV = new DetnDomain();
								detnV.setAppkey(offobj.getAppkey());
								detnV.setTicketNo(ticket.getNo());
								detnV.setUrl("");
								detnV.setUserName("");
								detnV.setVisitName("");
								DetnResult detnRs = api.detn_cmdForObj(detnV,
										kvobj.getV(),
										OOComms.GET_DETR_INFO_BY_GDSDETN);
								resV = ObjectToObiectUtils.toDetrInfoResVO(
										detnRs, ticket);
							}
						}
					}
				}
			}
			bol = resV != null && detrisok(resV.getData());
			// 方案5 detr
		}
		if (bol) {
			resV.setCode("1");
			if (resV.getFlt() == null || resV.getFlt().size() == 0) {
				List<DetrFltVO> flts = new ArrayList<DetrFltVO>();
				DetrFltVO f = new DetrFltVO();
				f.setArrCh("");
				f.setArrCode("");
				f.setArrt("");
				f.setCabin("");
				f.setDepCh("");
				f.setDepCode("");
				f.setDepDate("");
				f.setFltno("");
				f.setOrgt("");
				f.setState(OOComms.TICKET_OTHER_STATE);
				flts.add(f);
				resV.setFlt(flts);
			}
		}
		return resV;
	}

	private boolean detrisok(String data) {
		if (data != null
				&& (data.toUpperCase().contains("TICKET NOT FOUND")
						|| data.toUpperCase().contains("FOR INPUT STRING")
						|| data.toUpperCase().contains("DATA NOT FOUND")
						|| data.contains("票号数据格式错误")
						|| data.toUpperCase().contains(
								"ET TICKET NUMBER IS NOT EXIST")
						|| data.toUpperCase().contains("TICKET NUMBER") || data
						.toUpperCase().contains("ET PASSENGER DATA NOT FOUND"))) {
			return true;
		}
		return false;
	}

	@Override
	public String saveData(TicketStateEntity ticket) throws Exception {
		// TODO Auto-generated method stub
		return daof.getIticketStateDao().saveData(ticket);
	}

	@Override
	public void saveData(List<TicketStateEntity> tickets) throws Exception {
		// TODO Auto-generated method stub
		if (tickets != null && tickets.size() > 0) {
			for (TicketStateEntity t : tickets) {
				daof.getIticketStateDao().saveData(t);
			}
		}
	}

	@Override
	public int updateData(TicketStateEntity ticket) throws Exception {
		// TODO Auto-generated method stub
		return daof.getIticketStateDao().updateData(ticket);
	}

	@Override
	public List<TicketStateEntity> findDatas(TicketStateEntity ticket, Page p)
			throws Exception {
		// TODO Auto-generated method stub
		return daof.getIticketStateDao().findDatas(ticket, p);
	}

	@Override
	public List<TicketStateEntity> findDatas(TicketStateEntity ticket)
			throws Exception {
		// TODO Auto-generated method stub
		return daof.getIticketStateDao().findDatas(ticket);
	}

	@Override
	public void saveData(List<TicketStateEntity> tickets,
			TicketFileEntity entity) throws Exception {
		// TODO Auto-generated method stub
		/**
		 * 保存文件数据
		 */

		if (tickets != null && tickets.size() > 0) {
			for (TicketStateEntity t : tickets) {
				daof.getIticketStateDao().saveData(t);
			}
		}
		/**
		 * 保存操作记录
		 */
		if (entity != null)
			daof.getIticketFileDao().saveData(entity);

	}

	@Override
	public int updateDataById(TicketStateEntity ticket) throws Exception {
		// TODO Auto-generated method stub
		return daof.getIticketStateDao().updateDataById(ticket);
	}

}
