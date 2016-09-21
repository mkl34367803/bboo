package com.smart.oo.service.trigger;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;


import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.comm.entity.BaseAccountEntity;
import com.smart.comm.entity.GjSaleOrderEntity;
import com.smart.comm.entity.SwitchEntity;
import com.smart.comm.utils.BBOOConstants;
import com.smart.comm.utils.OOComms;
import com.smart.comm.utils.OOLogUtil;
import com.smart.oo.domain.GjOrderAccountVO;
import com.smart.oo.domain.GjOrderGetListDomain;
import com.smart.oo.domain.res.GjOrderGetBaseVO;
import com.smart.oo.domain.res.GjOrderGetDetailResult;
import com.smart.oo.domain.res.GjOrderGetListResult;
import com.smart.oo.domain.res.GjSaleOrderVO;
import com.smart.oo.service.api.AllApi;
import com.smart.oo.service.imp.FactoryServiceImpl;
import com.smart.oo.vo.InterfaceAddressVO;
import com.smart.utils.DateUtils;

@Service("BBOOSyncNationOrderTrigger")
public class SyncNationOrderTrigger {
	@Autowired
	private FactoryServiceImpl factoryService;

	private final static int PAGE_SIZE = 50;

	public SyncNationOrderTrigger() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 同步国际的订单，商户号有效的订单。
	 */
	public void syncOrder() {
		try {
			List<BaseAccountEntity> result = factoryService.getBaseAccountService().queryBaseAccounts();
			List<BaseAccountEntity> baseAccountEntities = null;
			if (result != null && result.size() > 0) {
				baseAccountEntities = new ArrayList<BaseAccountEntity>();
				for (int i = 0; i < result.size(); i++) {
					baseAccountEntities.add(result.get(i));
				}
			} else {
				return;
			}
			// 筛选一下商户，将禁用的商户踢出，将非国际的商户踢出。
			Iterator<BaseAccountEntity> iterator = baseAccountEntities.iterator();
			while (iterator.hasNext()) {
				BaseAccountEntity baseAccountEntity = (BaseAccountEntity) iterator.next();
				if (BBOOConstants.BASEACCOUNT_ISU_TWO.equals(baseAccountEntity.getIsu()) || !baseAccountEntity.getAcctype().contains(BBOOConstants.BASEACCOUNT_ACCTYPE_I)) {
					iterator.remove();
				}
			}
			if (baseAccountEntities != null && baseAccountEntities.size() > 0) {
				ExecutorService executorService = Executors.newCachedThreadPool();
				Future<String> f = null;
				List<Future<String>> flist = new ArrayList<Future<String>>();
				final Semaphore semp = new Semaphore(baseAccountEntities.size());
				Callable<String> call = null;
				String startTime = DateUtils.getDateys(); // 线程池开始时间
				for (final BaseAccountEntity baseAccountEntity : baseAccountEntities) {
					call = new Callable<String>() {
						@Override
						public String call() {
							// 获取许可
							try {
								semp.acquire();
								syncOrderByAccount(baseAccountEntity);
								// 访问完后，释放
								semp.release();
							} catch (Exception e) {
								e.printStackTrace();
							}
							return "true";
						}
					};
					f = executorService.submit(call);
					flist.add(f);

				}
				executorService.shutdown();
				if (flist.size() > 0) {
					while (!executorService.isTerminated()) {
						// 等待所有子线程结束，才退出主线程
						OOLogUtil.info("同步订单任务正在进行中...", SyncNationOrderTrigger.class, null);
						try {
							executorService.awaitTermination(30, TimeUnit.SECONDS);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					OOLogUtil.info("同步订单任务结束..." + "START-TIME:" + startTime + "END-TIME:" + DateUtils.getDateys(), QueryTicketStateTrigger.class, null);
					executorService.shutdownNow();
				}

			} else {
				OOLogUtil.info("同步订单任务开启无数据...", SyncNationOrderTrigger.class, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 同步国内的订单，商户号有效的订单。
	 */
	public void syncNationalOrder() {
		try {
			List<BaseAccountEntity> result = factoryService.getBaseAccountService().queryBaseAccounts();
			List<BaseAccountEntity> baseAccountEntities = null;
			if (result != null && result.size() > 0) {
				baseAccountEntities = new ArrayList<BaseAccountEntity>();
				for (int i = 0; i < result.size(); i++) {
					baseAccountEntities.add(result.get(i));
				}
			} else {
				return;
			}
			// 筛选一下商户，将禁用的商户踢出，将非国际的商户踢出。
			Iterator<BaseAccountEntity> iterator = baseAccountEntities.iterator();
			while (iterator.hasNext()) {
				BaseAccountEntity baseAccountEntity = (BaseAccountEntity) iterator.next();
				if (BBOOConstants.BASEACCOUNT_ISU_TWO.equals(baseAccountEntity.getIsu()) || !baseAccountEntity.getAcctype().contains(BBOOConstants.BASEACCOUNT_ACCTYPE_N)) {
					iterator.remove();
				}
			}
			if (baseAccountEntities != null && baseAccountEntities.size() > 0) {
				ExecutorService executorService = Executors.newCachedThreadPool();
				Future<String> f = null;
				List<Future<String>> flist = new ArrayList<Future<String>>();
				final Semaphore semp = new Semaphore(baseAccountEntities.size());
				Callable<String> call = null;
				String startTime = DateUtils.getDateys(); // 线程池开始时间
				for (final BaseAccountEntity baseAccountEntity : baseAccountEntities) {
					call = new Callable<String>() {
						@Override
						public String call() {
							// 获取许可
							try {
								semp.acquire();
								syncOrderByAccount(baseAccountEntity);
								// 访问完后，释放
								semp.release();
							} catch (Exception e) {
								e.printStackTrace();
							}
							return "true";
						}
					};
					f = executorService.submit(call);
					flist.add(f);

				}
				executorService.shutdown();
				if (flist.size() > 0) {
					while (!executorService.isTerminated()) {
						// 等待所有子线程结束，才退出主线程
						OOLogUtil.info("同步订单任务正在进行中...", SyncNationOrderTrigger.class, null);
						try {
							executorService.awaitTermination(30, TimeUnit.SECONDS);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					OOLogUtil.info("同步订单任务结束..." + "START-TIME:" + startTime + "END-TIME:" + DateUtils.getDateys(), QueryTicketStateTrigger.class, null);
					executorService.shutdownNow();
				}

			} else {
				OOLogUtil.info("同步订单任务开启无数据...", SyncNationOrderTrigger.class, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取每个商户下的订单号list（注意，这里同步订单no的list集合不区分国内国际）
	 * 
	 * @param baseAccountEntity
	 *            商户
	 * @throws Exception
	 *             异常
	 */
	private void syncOrderByAccount(BaseAccountEntity baseAccountEntity) throws Exception {
		AllApi api = new AllApi();
		GjOrderGetListDomain vo = new GjOrderGetListDomain();
		GjOrderAccountVO account = new GjOrderAccountVO();
		account.setPassWord(baseAccountEntity.getSecret()); // 设置密码，注意，这里的命名有些奇怪
		account.setUserName(baseAccountEntity.getUserName());
		account.setOts(baseAccountEntity.getUrl());
		account.setSessionKey(baseAccountEntity.getSessions());
		if (baseAccountEntity.getAcctype().contains(BBOOConstants.BASEACCOUNT_ACCTYPE_I)) {
			vo.setOrderType(BBOOConstants.GjOrderGetListDomain_orderType_I);
		} else if (baseAccountEntity.getAcctype().contains(BBOOConstants.BASEACCOUNT_ACCTYPE_N)) {
			vo.setOrderType(BBOOConstants.GjOrderGetListDomain_orderType_N);
		}
		vo.setAccount(account);
		Integer page = 1;
		vo.setCurrentPage(page.toString());
		vo.setPageSize(String.valueOf(PAGE_SIZE));
		vo.setOrderStatus(BBOOConstants.GjOrderGetListDomain_orderStatus_one);// 1表示支付成功等待出票的订单//测试的时候用2
		Date currentDate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		calendar.add(Calendar.HOUR_OF_DAY, -6);
		vo.setBeginTime(DateUtils.formatDate(calendar.getTime(), "yyyy-MM-dd HH:mm:ss"));
		vo.setEndTime(DateUtils.formatDate(currentDate, "yyyy-MM-dd HH:mm:ss"));
		// vo.setFareSource(fareSource);
		InterfaceAddressVO address = new InterfaceAddressVO();
		address.setHost(baseAccountEntity.getApiHost());
		address.setServiceName(baseAccountEntity.getApiServiceName());
		address.setClassName(baseAccountEntity.getApiClassName());
		address.setMethodName(baseAccountEntity.getApiMethodName());

		String name = new String();
		SwitchEntity switchEntity = new SwitchEntity();
		switchEntity.setMno(baseAccountEntity.getMno());
		//不管是国际还是国内的，获取订单list列表的时候，都是调用相同的方法。
		switchEntity.setMkey(baseAccountEntity.getAcctype());
		List<SwitchEntity> switchEntities = factoryService.getSwitchService().queryByMkey(switchEntity);
		if (switchEntities != null && switchEntities.size() > 0 && switchEntities.get(0).getOnoff().equals(BBOOConstants.SWITCH_ONOFF_ON)) {
			if (baseAccountEntity.getAcctype().equals(BBOOConstants.BASEACCOUNT_ACCTYPE_CTRIP_I) || baseAccountEntity.getAcctype().equals(BBOOConstants.BASEACCOUNT_ACCTYPE_CTRIP_N)) {
				name = OOComms.GET_CTRIP_ORDER_LIST_INFO_METH; //携程
			}else if (baseAccountEntity.getAcctype().equals(BBOOConstants.BASEACCOUNT_ACCTYPE_TAOBAO_I) || baseAccountEntity.getAcctype().equals(BBOOConstants.BASEACCOUNT_ACCTYPE_TAOBAO_N)) {
				name = OOComms.GET_QUA_ORDER_LIST_INFO_METH;	//去啊，淘宝
			}else if (baseAccountEntity.getAcctype().equals(BBOOConstants.BASEACCOUNT_ACCTYPE_QUNAR_I) || baseAccountEntity.getAcctype().equals(BBOOConstants.BASEACCOUNT_ACCTYPE_QUNAR_N)) {
				name = OOComms.GET_QUNAR_ORDER_LIST_INFO_METH;  //去哪儿
			} else if (baseAccountEntity.getAcctype().equals(BBOOConstants.BASEACCOUNT_ACCTYPE_TONGC_I) || baseAccountEntity.getAcctype().equals(BBOOConstants.BASEACCOUNT_ACCTYPE_TONGC_N)) {
				name = OOComms.GET_TONGC_ORDER_LIST_INFO_METH;  //同程
			} else if (baseAccountEntity.getAcctype().equals(BBOOConstants.BASEACCOUNT_ACCTYPE_TUNIU_I) || baseAccountEntity.getAcctype().equals(BBOOConstants.BASEACCOUNT_ACCTYPE_TUNIU_N)) {
				name = OOComms.GET_TUNIU_ORDER_LIST_INFO_METH;	//途牛
				GjOrderGetDetailResult gjOrderGetDetailResult= api.getDistributorOrderDetails(vo, address, name);
				dealSaleOrderVosFromThirdInterface(baseAccountEntity,gjOrderGetDetailResult);
				while(gjOrderGetDetailResult!=null&&gjOrderGetDetailResult.getOrders()!=null&&gjOrderGetDetailResult.getOrders().size()>=PAGE_SIZE){
					page++;
					vo.setCurrentPage(page.toString());
					gjOrderGetDetailResult= api.getDistributorOrderDetails(vo, address, name);
					dealSaleOrderVosFromThirdInterface(baseAccountEntity,gjOrderGetDetailResult);
				}
				return;
			}else{
				return;//其他的都是调不通的，也就没有必要调第三方接口获取orderNo的list集合了
			}  
		} else {
			return; //如果店铺同步的开关已经关掉了，也就没必要再去同步第三方数据了。
		}
		GjOrderGetListResult gjOrderGetListResult = api.getDistributorOrderList(vo, address, name);
		dealSaleOrderNosFromThirdInterface(baseAccountEntity,gjOrderGetListResult);
		while (gjOrderGetListResult!=null&&gjOrderGetListResult.getHasNext()) {
			page++;
			vo.setCurrentPage(page.toString());
			gjOrderGetListResult = api.getDistributorOrderList(vo, address, name);
			dealSaleOrderNosFromThirdInterface(baseAccountEntity,gjOrderGetListResult);
			if (gjOrderGetListResult.getOrder().size() < PAGE_SIZE) {
				break;
			}
		}
	}

	/**
	 * 处理来自第三方的订单号列表
	 * @param baseAccountEntity
	 * @param gjOrderGetListResult
	 * @throws Exception
	 */
	private void dealSaleOrderNosFromThirdInterface(
			BaseAccountEntity baseAccountEntity,
			GjOrderGetListResult gjOrderGetListResult) throws Exception {
		if ("0".equals(gjOrderGetListResult.getCode()) && gjOrderGetListResult.getOrder() != null && gjOrderGetListResult.getOrder().size() > 0) {
			for (GjOrderGetBaseVO gjOrderGetBaseVo : gjOrderGetListResult.getOrder()) {
				List<GjSaleOrderEntity> queryResult = factoryService.getSaleOrderService().queryByOrderNo(gjOrderGetBaseVo.getTradeOrderId());
				if (queryResult != null && queryResult.size() > 0) {
					continue; // 如果订单已经存在，跳到下一个订单
				}
				List<GjSaleOrderEntity> gjSaleOrderEntities = factoryService.getSaleOrderService().importOrder(baseAccountEntity, gjOrderGetBaseVo.getTradeOrderId(), true,true);
				syncOrderdetail(baseAccountEntity, gjSaleOrderEntities);
			}
		}
	}

	/**
	 * 处理来自第三方的的订单结果，将其插入到数据库中
	 * @param baseAccountEntity
	 * @param gjOrderGetDetailResult
	 * @throws Exception
	 */
	private void dealSaleOrderVosFromThirdInterface(
			BaseAccountEntity baseAccountEntity,
			GjOrderGetDetailResult gjOrderGetDetailResult) throws Exception {
		if(gjOrderGetDetailResult!=null){
			List<GjSaleOrderVO> gjSaleOrderVOs=gjOrderGetDetailResult.getOrders();
			if(gjSaleOrderVOs!=null&&gjSaleOrderVOs.size()>0){
				for(GjSaleOrderVO gjSaleOrderVO:gjSaleOrderVOs){
					List<GjSaleOrderEntity> queryResult = factoryService.getSaleOrderService().queryByOrderNo(gjSaleOrderVO.getOrderNo());
					if (queryResult != null && queryResult.size() > 0) {
						continue; // 如果订单已经存在，跳到下一个订单
					}
					 List<GjSaleOrderEntity> gjOrderEntities=factoryService.getSaleOrderService().convertSaleOrderVoToSaleOrderEntities(baseAccountEntity, true, gjSaleOrderVO);
					 syncOrderdetail(baseAccountEntity, gjOrderEntities);
				}
			}
		}
	}

	/**
	 * 同步单个订单详情
	 */
	private void syncOrderdetail(BaseAccountEntity baseAccountEntity, List<GjSaleOrderEntity> gjSaleOrderEntities) throws Exception {
		for(GjSaleOrderEntity gjSaleOrderEntity:gjSaleOrderEntities){
			try{
				factoryService.getSaleOrderService().autoReservePnr(baseAccountEntity, gjSaleOrderEntity);
			}catch (Exception e) {
				// TODO: handle exception
			}
			if (gjSaleOrderEntity != null) {
				if (StringUtils.isEmpty(gjSaleOrderEntity.getPnrNoTime()) || BBOOConstants.GjSaleOrderEntity_pnrNoTime_max.equals(gjSaleOrderEntity.getPnrNoTime())) {
					// 如果份销商没有传pnrNotime过来，才自己去航信查询。
					factoryService.getSaleOrderService().updatePnrInfoByRtResult(baseAccountEntity, gjSaleOrderEntity);
				}
				// 增加一个订单政策价信息的代码。单独开线程来同步政策
				SyncBigPnrAndPolicyCallable syncBigPnrAndPolicyCallable = new SyncBigPnrAndPolicyCallable(factoryService, baseAccountEntity, gjSaleOrderEntity);
				FutureTask<Boolean> task = new FutureTask<Boolean>(syncBigPnrAndPolicyCallable);
				new Thread(task, "同步政策的子线程").start();
				// factoryService.getSaleOrderService().updateBigpnrAndImportPolicy(baseAccountEntity,
				// gjSaleOrderEntity);
			}
		}
	}

}
