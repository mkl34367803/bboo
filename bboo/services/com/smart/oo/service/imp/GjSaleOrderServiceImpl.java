package com.smart.oo.service.imp;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.smart.base.exception.SqlException;
import com.smart.comm.entity.BaseAccountEntity;
import com.smart.comm.entity.BaseOfficeEntity;
import com.smart.comm.entity.BaseRefundEntity;
import com.smart.comm.entity.BookRuleEntity;
import com.smart.comm.entity.GjBuyOrderEntity;
import com.smart.comm.entity.GjSaleFlightEntity;
import com.smart.comm.entity.GjSaleOrderEntity;
import com.smart.comm.entity.GjSalePassengerEntity;
import com.smart.comm.utils.BBOOConstants;
import com.smart.comm.utils.StringUtilsc;
import com.smart.entity.User;
import com.smart.framework.base.Page;
import com.smart.oo.dao.imp.FactoryDaoImpl;
import com.smart.oo.domain.res.BuildPnrResult;
import com.smart.oo.domain.res.CabinVO;
import com.smart.oo.domain.res.FlightByLineVO;
import com.smart.oo.domain.res.GDSResult;
import com.smart.oo.domain.res.GetFlightByLineResult;
import com.smart.oo.domain.res.GetPnrInfoResult;
import com.smart.oo.domain.res.GjOrderGetDetailResult;
import com.smart.oo.domain.res.GjSaleFlightVO;
import com.smart.oo.domain.res.GjSaleOrderVO;
import com.smart.oo.domain.res.GjSalePassengerVO;
import com.smart.oo.domain.res.ProductRootResult;
import com.smart.oo.domain.res.RtPnrLineVO;
import com.smart.oo.domain.res.RtPnrPassengerVO;
import com.smart.oo.domain.res.RtPnrPriceVO;
import com.smart.oo.from.FlightVo;
import com.smart.oo.from.OrderSummaryVo;
import com.smart.oo.from.RefundVo;
import com.smart.oo.from.ReservePnrVo;
import com.smart.oo.from.SaleOrderParamVo;
import com.smart.oo.service.IGjSaleOrderService;
import com.smart.oo.vo.OrderAndPassengerParamVo;
import com.smart.oo.vo.SaleOrderAndPassengerVo;
import com.smart.utils.DateUtils;
import com.smart.utils.MathUtil;
import com.smart.utils.StringUtils;
import com.smart.utils.UniqId;

@Service("GjSaleOrderServiceImpl")
@Transactional
public class GjSaleOrderServiceImpl implements IGjSaleOrderService {

	@Autowired
	private FactoryDaoImpl factoryDao;
	@Autowired
	private FactoryServiceImpl factoryService;

	public GjSaleOrderServiceImpl() {
	}

	@Override
	public List<GjSaleOrderEntity> queryByOrderNo(String orderNo) throws Exception {
		// return factoryDao.getSaleOrderDao().queryByOrderNo(orderNo);
		return factoryDao.getSaleOrderDao().queryByOrderNo(orderNo);
	}

	@Deprecated
	@Override
	public List<OrderSummaryVo> readOrderSummaryByPage(Page page, User user, SaleOrderParamVo saleOrderParamEntity) throws Exception {
		// return factoryDao.getSaleOrderDao().queryOrderSummaryByPage(page,
		// user, saleOrderParamEntity);
		// saleOrderParamEntity = new SaleOrderParamEntity();
		// saleOrderParamEntity.setPassengerName("CHEN/WEI");
		// saleOrderParamEntity.setStartDate("2016-04-08 09:55:14");
		// saleOrderParamEntity.setEndDate("2016-06-02 12:42:27");
		// saleOrderParamEntity.setTicketNum("yuanqiao");
		Long startTime=new Date().getTime();
		//List<Object[]> list = factoryDao.getSaleOrderDao().queryOrderSummaryByPage(page, user, saleOrderParamEntity);
		List<Object[]> list = factoryDao.getSaleOrderDao().queryOrderSummaryByPage1(page, user, saleOrderParamEntity);
		System.out.println(new Date().getTime()-startTime);
		/** 这里做一次转化，转化成传给前端的对象 **/
		Map<String, OrderSummaryVo> map = null;
		if (list != null && list.size() > 0) {
			map = new LinkedHashMap<String, OrderSummaryVo>();
			for (Object[] objects : list) {
				OrderSummaryVo orderSummaryVo = map.get(objects[0]);
				if (orderSummaryVo != null) {
					FlightVo flightVo = new FlightVo();
					flightVo.setDepartureTime(objects[4].toString());
					flightVo.setDepAircode(objects[5].toString());
					flightVo.setArrivalTime(objects[6].toString());
					flightVo.setArrAircode(objects[7].toString());
					flightVo.setDepartureDate(objects[8].toString());
					flightVo.setFlightNum(objects[9].toString());
					flightVo.setCabin(objects[15].toString());
					if (objects[23] != null) {
						flightVo.setId(objects[23].toString());
					}
					if (objects[27] != null) {
						flightVo.setIsShared(objects[27].toString());
					}
					if (objects[29] != null) {
						flightVo.setCabinNum(objects[29].toString());
					}
					if (objects[31] != null) {
						flightVo.setLowspace(objects[31].toString());
					}
					List<FlightVo> flightVos = orderSummaryVo.getFlightVos();
					if (flightVos != null && flightVos.size() > 0) {
						flightVos.add(flightVo);
					} else {
						flightVos = new ArrayList<FlightVo>();
						flightVos.add(flightVo);
					}
				} else {
					orderSummaryVo = new OrderSummaryVo();
					orderSummaryVo.setId(objects[0].toString());
					orderSummaryVo.setOrderNo(objects[1].toString());
					orderSummaryVo.setCreateTime(objects[2].toString());
					orderSummaryVo.setFlightType(objects[3].toString());
					FlightVo flightVo = new FlightVo();
					if(objects[4]!=null){
						flightVo.setDepartureTime(objects[4].toString());
					}
					if(objects[5]!=null){
						flightVo.setDepAircode(objects[5].toString());
					}
					if(objects[6]!=null){
						flightVo.setArrivalTime(objects[6].toString());
					}
					if(objects[7]!=null){
						flightVo.setArrAircode(objects[7].toString());
					}
					if(objects[8]!=null){
						flightVo.setDepartureDate(objects[8].toString());
					}
					if(objects[9]!=null){
						flightVo.setFlightNum(objects[9].toString());
					}
					if(objects[15]!=null){
						flightVo.setCabin(objects[15].toString());
					}
					if (objects[23] != null) {
						flightVo.setId(objects[23].toString());
					}
					if (objects[27] != null) {
						flightVo.setIsShared(objects[27].toString());
					}
					if (objects[29] != null) {
						flightVo.setCabinNum(objects[29].toString());
					}
					if (objects[31] != null) {
						flightVo.setLowspace(objects[31].toString());
					}
					List<FlightVo> flightVos = new ArrayList<FlightVo>();
					flightVos.add(flightVo);
					orderSummaryVo.setFlightVos(flightVos);
					orderSummaryVo.setPnrCode(objects[10].toString());
					orderSummaryVo.setAllprice((Double) objects[11]);
					orderSummaryVo.setPassengerCount((Integer) objects[12]);
					orderSummaryVo.setSlfStatus(objects[13].toString());
					if (objects[14] != null) {
						orderSummaryVo.setLockUser(objects[14].toString());
					}
					if (objects[16] != null) {
						orderSummaryVo.setDistributor(objects[16].toString());
					}
					if (objects[17] != null) {
						orderSummaryVo.setPnrNoTime(objects[17].toString());
					}
					if (objects[18] != null) {
						orderSummaryVo.setLeaveRemark(objects[18].toString());
					}
					if (objects[19] != null) {
						orderSummaryVo.setBillId(objects[19].toString());
					}
					if (objects[20] != null) {
						orderSummaryVo.setShopName(objects[20].toString());
					}
					if (objects[21] != null) {
						orderSummaryVo.setShopNameCh(objects[21].toString());
					}
					if (objects[22] != null) {
						orderSummaryVo.setOldOrderNo(objects[22].toString());
					}
					if (objects[24] != null) {
						orderSummaryVo.setPolicyType(Integer.parseInt(objects[24].toString()));
					}
					if (objects[25] != null) {
						orderSummaryVo.setIsAuto(Integer.parseInt(objects[25].toString()));
					}
					if (objects[26] != null) {
						orderSummaryVo.setStatement(objects[26].toString());
					}
					if (objects[28] != null) {
						orderSummaryVo.setPnrType(Integer.parseInt(objects[28].toString()));
					}
					if (objects[30] != null) {
						orderSummaryVo.setIsLowspace(Integer.parseInt(objects[30].toString()));
					}
					if (objects[32] != null) {
						orderSummaryVo.setIsNewCode(Integer.parseInt(objects[32].toString()));
					}
					if (objects[33] != null) {
						if(StringUtils.isNotEmpty(objects[33].toString())&&StringUtils.isFigure(objects[33].toString()))
						orderSummaryVo.setSeats(Integer.parseInt(objects[33].toString()));
						else
							orderSummaryVo.setSeats(-1);
					}
					map.put(orderSummaryVo.getId(), orderSummaryVo);
				}
			}
		}
		List<OrderSummaryVo> orderSummaryVos = null;
		if (map != null && map.size() > 0) {
			orderSummaryVos = new ArrayList<OrderSummaryVo>();
			for (String key : map.keySet()) {
				orderSummaryVos.add(map.get(key));
			}
		}
		return orderSummaryVos;

	}

	@Override
	public synchronized List<GjSaleOrderEntity> importOrder(BaseAccountEntity baseAccountEntity, String orderNo, Boolean isAutoImport,Boolean isFirst) throws Exception {
		//首先判断一下订单是否已经存在了
		List<GjSaleOrderEntity> listResult=factoryService.getSaleOrderService().queryByOrderNo(orderNo);
		if(isFirst&&listResult!=null&&listResult.size()>0){
			throw new RuntimeException("数据库中已经存在该订单了，请刷新后重试！");
		}
		System.out.println("看看synchronized关键字是否起作用了！");
		List<GjSaleOrderEntity> resultGjSaleOrderEntity = null;
		GjOrderGetDetailResult rlt = factoryService.getAllApiService().getOrderInfoByInteface(baseAccountEntity, orderNo);
		if (rlt != null) {
			// 这里入库就可以了。分别向订单表，乘客表、航班表插入数据就可以了。
			List<GjSaleOrderVO> gjSaleOrderVOs = rlt.getOrders();
			if (gjSaleOrderVOs != null && gjSaleOrderVOs.size() > 0) {
				for (GjSaleOrderVO gjSaleOrderVO : gjSaleOrderVOs) {
					resultGjSaleOrderEntity = convertSaleOrderVoToSaleOrderEntities(baseAccountEntity, isAutoImport, gjSaleOrderVO);
				}
			} else {
				throw new Exception("调用第三方接口成功，返回的信息中无订单信息");
			}
		} else {
			throw new RuntimeException("调用第三方接口没有返回任何东西");
		}
		return resultGjSaleOrderEntity;
	}

	@Override
	public List<GjSaleOrderEntity> convertSaleOrderVoToSaleOrderEntities(
			BaseAccountEntity baseAccountEntity, Boolean isAutoImport,
			GjSaleOrderVO gjSaleOrderVO) throws Exception {
		List<GjSaleOrderEntity> resultGjSaleOrderEntity;
		GjSaleOrderEntity gjSaleOrderEntity = new GjSaleOrderEntity();
		Set<GjSaleFlightEntity> gjSaleFlightEntities = new HashSet<GjSaleFlightEntity>();
		Set<GjSalePassengerEntity> gjSalePassengerEntities = new HashSet<GjSalePassengerEntity>();
		// 这里给订单实体赋值，下面的乘客和航班赋值是一样的。
		BeanUtils.copyProperties(gjSaleOrderVO, gjSaleOrderEntity, new String[] { "flights", "passengers","seats"});
		if(StringUtils.isNotEmpty(gjSaleOrderVO.getSeats())){
			gjSaleOrderEntity.setSeats(Integer.parseInt(gjSaleOrderVO.getSeats()));
		}
		gjSaleOrderEntity.setLockUser("");
		gjSaleOrderEntity.setMno(baseAccountEntity.getMno());// 设置订单的商户号
		String id = UniqId.getInstance().getStrId();
		gjSaleOrderEntity.setId(id);
		gjSaleOrderEntity.setAccountid(baseAccountEntity.getId()); // 用于产生外键关联，给订单添加账号id
		gjSaleOrderEntity.setShopName(baseAccountEntity.getAcctype());
		gjSaleOrderEntity.setShopNameCh(baseAccountEntity.getDescribe());
		gjSaleOrderEntity.setSlfStatus(BBOOConstants.GjSaleOrderEntity_SLFSTATUS_ONE); // 刚导进来的订单，设置订单状态为等待出票。
		// rt指令的officeNo,设置默认值为account表中的office
		gjSaleOrderEntity.setRtOffno(baseAccountEntity.getOffice());
		if (gjSaleOrderEntity.getStatement() != null && gjSaleOrderEntity.getStatement().length() > 500) {
			gjSaleOrderEntity.setStatement(gjSaleOrderEntity.getStatement().substring(0, 499));// 超过1500字节截取。
		}
		if (gjSaleOrderEntity.getRemark() != null && gjSaleOrderEntity.getRemark().length() > 500) {
			gjSaleOrderEntity.setRemark(gjSaleOrderEntity.getRemark().substring(0, 499));// 超过1500字节截取。
		}
		if (gjSaleOrderEntity.getReturnTicketRule() != null && gjSaleOrderEntity.getReturnTicketRule().length() > 500) {
			gjSaleOrderEntity.setReturnTicketRule(gjSaleOrderEntity.getReturnTicketRule().substring(0, 499));// 超过1500字节截取。
		}
		if (gjSaleOrderEntity.getChangeDateRule() != null && gjSaleOrderEntity.getChangeDateRule().length() > 500) {
			gjSaleOrderEntity.setChangeDateRule(gjSaleOrderEntity.getChangeDateRule().substring(0, 499));// 超过1500字节截取。
		}
		if (StringUtils.isEmpty(gjSaleOrderEntity.getPnrNoTime())) {
			gjSaleOrderEntity.setPnrNoTime(BBOOConstants.GjSaleOrderEntity_pnrNoTime_max);
		}
		System.out.println(JSON.toJSONString(gjSaleOrderEntity));
		getProperty(gjSaleOrderEntity);
		if (gjSaleOrderVO.getFlights() != null && gjSaleOrderVO.getFlights().size() > 0) {
			gjSaleOrderEntity.setCarrier(gjSaleOrderVO.getFlights().get(0).getCarrier());
			for (GjSaleFlightVO gjSaleFlightVO : gjSaleOrderVO.getFlights()) {
				GjSaleFlightEntity gjSaleFlightEntity = new GjSaleFlightEntity();
				BeanUtils.copyProperties(gjSaleFlightVO, gjSaleFlightEntity);
				gjSaleFlightEntity.setOrder(gjSaleOrderEntity);
				gjSaleFlightEntity.setId(UniqId.getInstance().getStrId());
				gjSaleFlightEntities.add(gjSaleFlightEntity);
				// 这里直接保存，就可以做到级联保存了吗？
				// gjSaleFlightEntity.setOrder(gjSaleOrderEntity);
				// factoryDao.getSaleFlightDao().saveSaleFlight(gjSaleFlightEntity);
			}
		}
		if (gjSaleOrderVO.getPassengers() != null && gjSaleOrderVO.getPassengers().size() > 0) {
			for (GjSalePassengerVO gjSalePassengerVO : gjSaleOrderVO.getPassengers()) {
				GjSalePassengerEntity gjSalePassengerEntity = new GjSalePassengerEntity();
				BeanUtils.copyProperties(gjSalePassengerVO, gjSalePassengerEntity);
				gjSalePassengerEntity.setId(UniqId.getInstance().getStrId());
				gjSalePassengerEntity.setOrder(gjSaleOrderEntity);
				gjSalePassengerEntities.add(gjSalePassengerEntity);
				// 这里直接保存，就可以做到级联保存了吗？
				// gjSalePassengerEntity.setOrder(gjSaleOrderEntity);
				// factoryDao.getSalePassengerDao().saveSalePassenger(gjSalePassengerEntity);
			}
		}
		// hibernate的级联操作数据
		gjSaleOrderEntity.setPassengers(gjSalePassengerEntities);
		gjSaleOrderEntity.setFlights(gjSaleFlightEntities);
		//给订单的两个字段设置默认属性
		if(isAutoImport){
			//用isAuto来做cacheKey
			List<BookRuleEntity> bookRuleEntities=factoryService.getBookRuleService().getAll("isAuto", true);
			setIsAutoFieldForOrder(gjSaleOrderEntity, bookRuleEntities);
		}else{
			gjSaleOrderEntity.setIsAuto(BBOOConstants.GjSaleOrderEntity_isAuto_two);
		}
		gjSaleOrderEntity.setIsGet(BBOOConstants.GjSaleOrderEntity_isGet_two);
		//在这里加上avh指令获取舱位数的代码
		fillCabinNumber(gjSaleOrderEntity);
		//在这里加上一个自动拆分订单的功能，如何？将一个订单拆分为多个订单。
		List<GjSaleOrderEntity> gjSaleOrderEntities= autoSplitOrder(gjSaleOrderEntity);
		for(GjSaleOrderEntity gjSaleOrderEntity2:gjSaleOrderEntities){
			factoryDao.getSaleOrderDao().saveOrder(gjSaleOrderEntity2);  //循环插入拆分后的订单
		}
		resultGjSaleOrderEntity = gjSaleOrderEntities;
//					factoryDao.getSaleOrderDao().importOrder(gjSaleOrderEntity);
//					resultGjSaleOrderEntity=gjSaleOrderEntity;
		return resultGjSaleOrderEntity;
	}

	private void setIsAutoFieldForOrder(GjSaleOrderEntity gjSaleOrderEntity, List<BookRuleEntity> bookRuleEntities) {
		if(bookRuleEntities!=null&&bookRuleEntities.size()>0){
			for(BookRuleEntity bookRuleEntity:bookRuleEntities){
				if(bookRuleEntity.getIsuse().equals(BBOOConstants.BookRuleEntity_isuse_enable)&&bookRuleEntity.getBookChannel().equals(gjSaleOrderEntity.getProductSource())&&
						bookRuleEntity.getPolicyType().equals(gjSaleOrderEntity.getPolicyType())){
					gjSaleOrderEntity.setIsAuto(BBOOConstants.GjSaleOrderEntity_isAuto_one);//设置为自动出票
				}
			}
		}
	}



	@Override
	public List<GjSaleOrderEntity> orderlist(GjSaleOrderEntity order, Page page, User user) throws SqlException {

		return factoryDao.getSaleOrderDao().orderlist(order, page, user);
	}

	@Override
	public void saveorder(GjSaleOrderEntity orderentity) throws Exception {

		this.factoryDao.getSaleOrderDao().saveOrder(orderentity);
	}

	@SuppressWarnings("unused")
	private void copyOrderProperties(GjSaleOrderVO source, GjSaleOrderEntity target) {
		target.setAccountInfo(source.getAccountInfo());
		target.setAddress(source.getAddress());
		target.setAdultPrice(source.getAdultPrice());
		target.setAdultsCount(source.getAdultsCount());
		target.setAdultTax(source.getAdultTax());
		target.setAirlineCount(source.getAirlineCount());
		target.setAllprice(source.getAllprice());
		target.setAvText(source.getAvText());
		target.setBillId(source.getBillId());
		// target.setSalePrice(source.getBxFee());
		target.setBxCount(source.getBxCount());
		target.setCabinDes(source.getCabinDes());
		target.setChangeDateRule(source.getChangeDateRule());
		target.setChildPrice(source.getChildPrice());
		target.setChildrenCount(source.getChildrenCount());
		target.setChildTax(source.getChildTax());
		target.setContactEmail(source.getContactEmail());
		target.setContactMob(source.getContactMob());
		target.setContactName(source.getContactName());
		target.setContactTel(source.getContactTel());
		target.setCreateTime(source.getCreateTime());
		target.setCurrency(source.getCurrency());
		target.setDistributor(source.getDistributor());
		target.setDistributorCh(source.getDistributorCh());
		target.setExtOid(source.getExtOid());
		target.setExtOrderID(source.getExtOrderID());
		target.setFareBasis(source.getFareBasis());
		target.setFlightClass(source.getFlightClass());
		target.setFlightType(source.getFlightType());
		target.setFzstatus(source.getFzstatus());
		// target.setId(source.getid());
		target.setJourneySheet(source.getJourneySheet());
		target.setJourneySheetNo(source.getJourneySheetNo());
		target.setJourneySheetPrice(source.getJourneySheetPrice());
		target.setJourneySheetStatu(source.getJourneySheetStatu());
		target.setJourneySheetWay(source.getJourneySheetWay());
		target.setLastUpdated(source.getLastUpdated());
		target.setLuggageRule(source.getLuggageRule());
		// target.setMno(source.getmno);
		target.setNoPay(source.getNoPay());
		target.setOldAllprice(source.getOldAllprice());
		// target.setOldId(source.getid());
		target.setOldOrderNo(source.getOldOrderNo());
		// target.setOldPnrCode(source.getol)
		target.setOpenDes(source.getOpenDes());
		target.setOpenStatus(source.getOpenStatus());
		target.setOperator(source.getOperator());
		target.setOrderDesc(source.getOrderDesc());
		target.setOrderNeedConfirm(source.getOrderNeedConfirm());
		target.setOrderNo(source.getOrderNo());
		target.setPassengerCount(source.getPassengerCount());
		target.setPayWay(source.getPayWay());
		target.setPnrCode(source.getPnrCode());
		target.setPnrCodeBig(source.getPnrCodeBig());
		target.setPnrOffNo(source.getPnrOffNo());
		target.setPnrStatus(source.getPnrStatus());
		target.setPnrText(source.getPnrText());
		target.setPnrType(source.getPnrType());
		target.setPolicyCode(source.getPolicyCode());
		target.setPolicyId(source.getPolicyId());
		target.setPolicyType(source.getPolicyType());
		target.setPostcode(source.getPostcode());
		target.setProductSource(source.getProductSource());
		target.setReceiver(source.getReceiver());
		target.setRefundCause(source.getRefundCause());
		target.setRemark(source.getRemark());
		target.setReturnTicketRule(source.getReturnTicketRule());
		target.setRtOffno(source.getRtOffno());
		if(source.getSeats()!=null){
			target.setSeats(Integer.parseInt(source.getSeats()));
		}
		target.setSeatsDes(source.getSeatsDes());
		// target.setShopName(source.getsh)
		// target.setShopNameCh(shopNameCh)
		target.setSlfAddtime(source.getSlfAddtime());
		// target.setSlfRemark(source.getsl
		target.setSlfStatus(source.getSlfStatus());
		target.setSource(source.getSource());
		target.setSpecialCode(source.getSpecialCode());
		target.setStatement(source.getStatement());
		target.setStatus(source.getStatus());
		target.setTicketDate(source.getTicketDate());
		target.setTransactionNo(source.getTransactionNo());
		target.setUnfzTime(source.getUnfzTime());
		target.setUrgency(source.getUrgency());
		target.setUrgeTimes(source.getUrgeTimes());
		target.setUrl(source.getUrl());
		target.setUserName(source.getUserName());
	}

	/**
	 * 获得一个对象各个属性的字节流
	 */
	@SuppressWarnings("rawtypes")
	public static void getProperty(Object entityName) throws Exception {
		Class c = entityName.getClass();
		Field field[] = c.getDeclaredFields();
		for (Field f : field) {
			Object v = invokeMethod(entityName, f.getName(), null);
			if (v != null) {
				System.out.println(f.getName() + "\t" + v + "\t" + v.toString().length());
			}
		}
	}

	/**
	 * 获得对象属性的值
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Object invokeMethod(Object owner, String methodName, Object[] args) throws Exception {
		Class ownerClass = owner.getClass();
		methodName = methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
		Method method = null;
		try {
			method = ownerClass.getMethod("get" + methodName);
		} catch (SecurityException e) {
		} catch (NoSuchMethodException e) {
			return " can't find 'get" + methodName + "' method";
		}
		return method.invoke(owner);
	}
	/**
	 * 通过id查询订单号
	 */
	@Override
	public GjSaleOrderEntity queryOrderByID(String id) throws Exception {
		GjSaleOrderEntity gjSaleOrderEntity = factoryDao.getSaleOrderDao().queryOrderByID(id);
		if (gjSaleOrderEntity == null) {
			throw new RuntimeException("未查询到订单号为：" + id + "的订单");
		}
		Set<GjSalePassengerEntity> gjSalePassengerEntities = gjSaleOrderEntity.getPassengers();
		Iterator<GjSalePassengerEntity> iterator = gjSalePassengerEntities.iterator();
		if (gjSalePassengerEntities != null && gjSalePassengerEntities.size() > 0) {
			//只能用迭代器来删除
			while (iterator.hasNext()) {
				GjSalePassengerEntity gjSalePassengerEntity = (GjSalePassengerEntity) iterator.next();
				if (gjSalePassengerEntity.getIsDelete()) {
					iterator.remove();
				}
			}
		}
		return gjSaleOrderEntity;
	}

	@Override
	public boolean updateLockUser(String id, String lockUser) throws Exception {
		return factoryDao.getSaleOrderDao().updateLockUser(id, lockUser);
	}

	@Override
	public boolean updateSlfStatus(String id, String slfStatus) throws Exception {
		return factoryDao.getSaleOrderDao().updateSlfStatus(id, slfStatus);
	}

	@Transactional
	@Override
	public boolean splitOrder(String id, String[] passengerIDs) throws Exception {
		GjSaleOrderEntity gjSaleOrderEntity = factoryDao.getSaleOrderDao().queryOrderByID(id);
		if (gjSaleOrderEntity != null) {
			//拆单之前，将原来的政策信息也删掉，旧的政策信息毕竟不适用
			factoryService.getProductDataService().deleteProductDataByOrderId(id);
			GjSaleOrderEntity newSaleOrderEntity = new GjSaleOrderEntity();
			BeanUtils.copyProperties(gjSaleOrderEntity, newSaleOrderEntity, new String[] { "flights", "passengers" });
			// 需不需要先将航班信息的订单的主键设置为空？？？？
			newSaleOrderEntity.setId(UniqId.getInstance().getStrId());
			Set<GjSaleFlightEntity> gjSaleFlightEntities = gjSaleOrderEntity.getFlights();
			if (gjSaleFlightEntities != null && gjSaleFlightEntities.size() > 0) {
				Set<GjSaleFlightEntity> newgjSaleFlightEntitities = new HashSet<GjSaleFlightEntity>();
				for (GjSaleFlightEntity gjSaleFlightEntity : gjSaleFlightEntities) {
					GjSaleFlightEntity newSaleFlightEntity = new GjSaleFlightEntity();
					BeanUtils.copyProperties(gjSaleFlightEntity, newSaleFlightEntity); // 这里有点问题，把订单对象也copy给新对象了、
					newSaleFlightEntity.setOrder(newSaleOrderEntity);
					newSaleFlightEntity.setId(UniqId.getInstance().getStrId());
					// newSaleFlightEntity.setFkid(newSaleOrderEntity.getId());
					newgjSaleFlightEntitities.add(newSaleFlightEntity);
				}
				newSaleOrderEntity.setFlights(newgjSaleFlightEntitities);
			}
			Integer passengerCount = new Integer(0);// 乘客总人数
			Double allprice = new Double(0);// 订单总价
			Integer adultCount = new Integer(0); // 成人数；
			Integer childCount = new Integer(0); // 儿童数；

			Integer newPassengerCount = new Integer(0);// 拆分出来的乘客总人数
			Double newAllprice = new Double(0);// 拆分出来的订单总价
			Integer newAdultCount = new Integer(0); // 成人数；
			Integer newChildCount = new Integer(0); // 儿童数；
			Set<GjSalePassengerEntity> passengers = gjSaleOrderEntity.getPassengers();
			if (passengers != null && passengers.size() > 0) {
				Set<GjSalePassengerEntity> newSalePassengerEntities = new HashSet<GjSalePassengerEntity>();
				Iterator<GjSalePassengerEntity> iterator=passengers.iterator();
				while(iterator.hasNext()) {
					GjSalePassengerEntity gjSalePassengerEntity=iterator.next();
					for (String string : passengerIDs) {
						if (gjSalePassengerEntity.getId().equals(string)) {
							GjSalePassengerEntity newSalePassengerEntity = new GjSalePassengerEntity();
							BeanUtils.copyProperties(gjSalePassengerEntity, newSalePassengerEntity);
							newSalePassengerEntity.setOrder(newSaleOrderEntity);
							newSalePassengerEntity.setId(UniqId.getInstance().getStrId());
							// newSalePassengerEntity.setFkid(newSaleOrderEntity.getId());
							newSalePassengerEntities.add(newSalePassengerEntity);
							newPassengerCount++;
							if (newSalePassengerEntity.getAgeType() == 0) {
								newAdultCount++;
							}
							if (newSalePassengerEntity.getAgeType() == 1) {
								newChildCount++;
							}
							newAllprice += gjSalePassengerEntity.getCost() + gjSalePassengerEntity.getOilFee() + gjSalePassengerEntity.getTaxFee();
							gjSalePassengerEntity.setIsDelete(true);//这里是拆单的关键，拆完之后，查询的订单详情的时候，就要将这个剔除掉
							factoryDao.getSalePassengerDao().deleteById(gjSalePassengerEntity.getId());
							iterator.remove();
							break;
						}
					}
					// 如果不是被拆分出去的订单，要进行订单人数和总价值的重新计算。
					if (!gjSalePassengerEntity.getIsDelete()) {
						gjSaleOrderEntity.setPnrCode(gjSalePassengerEntity.getPnr()); // 2016-07-20加
						passengerCount++;
						allprice += gjSalePassengerEntity.getCost() + gjSalePassengerEntity.getOilFee() + gjSalePassengerEntity.getTaxFee();
						if (gjSalePassengerEntity.getAgeType() == 0) {
							adultCount++;
						}
						if (gjSalePassengerEntity.getAgeType() == 1) {
							childCount++;
						}
					} else {
						newSaleOrderEntity.setPnrCode(gjSalePassengerEntity.getPnr()); // 2016-07-20加
						newSaleOrderEntity.setPnrCodeBig("");// 2016-07-20新生成的订单大编码置空
					}
				}
				newSaleOrderEntity.setPassengers(newSalePassengerEntities);
			}
			// 设置新的订单总价，和乘客人数
			gjSaleOrderEntity.setPassengerCount(passengerCount);
			gjSaleOrderEntity.setAllprice(allprice);
			gjSaleOrderEntity.setAdultsCount(adultCount);
			gjSaleOrderEntity.setChildrenCount(childCount);
			gjSaleOrderEntity.setIsSplit(true);
			newSaleOrderEntity.setPassengerCount(newPassengerCount);
			newSaleOrderEntity.setAllprice(newAllprice);
			newSaleOrderEntity.setAdultsCount(newAdultCount);
			newSaleOrderEntity.setChildrenCount(newChildCount);
			newSaleOrderEntity.setIsSplit(true);
			factoryDao.getSaleOrderDao().update(gjSaleOrderEntity);//为什么这里要这么用呢？因为乘机人中有个isDelete字段也变化了，不然应该写sql的
			factoryDao.getSaleOrderDao().saveOrder(newSaleOrderEntity);
			return true;
		}
		return false;

	}

	@Override
	public boolean updatePnrCode(String id, String pnrCode) throws Exception {
		return this.factoryDao.getSaleOrderDao().updatePnrCode(id, pnrCode);
	}

	@Override
	public boolean cancelOrder(String id, String lockUser) throws Exception {
		return this.factoryDao.getSaleOrderDao().cancelOrder(id, lockUser);
	}

	@Override
	public boolean updatePnrNoTime(String id, String pnrNoTime, String leaveRemark) throws Exception {
		return this.factoryDao.getSaleOrderDao().updatePnrNoTime(id, pnrNoTime, leaveRemark);
	}
	
	@Override
	public void updatePnrInfoByRtResult(BaseAccountEntity baseAccountEntity, GjSaleOrderEntity gjSaleOrderEntity) throws Exception {
		try { // 这个方法的东西全部自己捕获异常
			if (BBOOConstants.GjSaleOrderEntity_flightClass_N.equals(gjSaleOrderEntity.getFlightClass())) {
				return; // 国内的订单，不做pnrNotime的更新处理
			}
			BaseOfficeEntity paramVo=new BaseOfficeEntity();
			String mno=baseAccountEntity.getMno();
			String office = baseAccountEntity.getOffice();
			paramVo.setMno(mno);
			paramVo.setOffice(office);
			List<BaseOfficeEntity> baseOfficeEntities = factoryService.getBaseOfficeService().queryByParamVo(paramVo);
			if (baseOfficeEntities == null || baseOfficeEntities.size() < 1) {
				//携程的订单rt不需要带office信息
					throw new RuntimeException("未查询到该商户对应的office信息");
			}
			String appkey=baseOfficeEntities.get(0).getAppkey();
			String cmds="rt" + gjSaleOrderEntity.getPnrCode().split(",")[0];
			GDSResult rs=factoryService.getAllApiService().getCmdsByThirdInterface(appkey, cmds);
			if (rs.getTxt().length > 0) {
				GjSaleOrderEntity pnrGjSaleOrderEntity = new GjSaleOrderEntity();
				pnrGjSaleOrderEntity.setId(gjSaleOrderEntity.getId());
				pnrGjSaleOrderEntity.setPnrText(rs.getTxt()[0]);
				if (pnrGjSaleOrderEntity.getPnrText() != null && pnrGjSaleOrderEntity.getPnrText().length() > 500) {
					pnrGjSaleOrderEntity.setPnrText(pnrGjSaleOrderEntity.getPnrText().substring(0, 499));// 超过1500字节截取。
				}
				try {
					pnrGjSaleOrderEntity.setPnrNoTime(dealPnrNoTime(rs.getTxt()[0]));
				} catch (Exception e) {
					try {
						pnrGjSaleOrderEntity.setPnrNoTime(dealPnrNoTimeTwo(rs.getTxt()[0]));
					} catch (Exception e2) {
						try {
							pnrGjSaleOrderEntity.setPnrNoTime(dealPnrNoTimeThree(rs.getTxt()[0]));
						} catch (Exception e3) {
							try {
								pnrGjSaleOrderEntity.setPnrNoTime(dealPnrNoTimeFour(rs.getTxt()[0]));
							} catch (Exception e4) {
							}
						}
					}
				}
				if (StringUtils.isEmpty(pnrGjSaleOrderEntity.getPnrNoTime())) {
					//设置一个最大的pnrNotime
					pnrGjSaleOrderEntity.setPnrNoTime(BBOOConstants.GjSaleOrderEntity_pnrNoTime_max);
				}
				factoryDao.getSaleOrderDao().updateByID(pnrGjSaleOrderEntity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 通过pnrText截取pnrNoTime 解析：SSR ADTK 1E BY SZX15JUL16/1811 OR CXL 3U8085
	 * E20JUL 这种格式ADTK
	 * 
	 * @param pnrText
	 * @return
	 * @throws ParseException
	 */
	private String dealPnrNoTime(String pnrText) throws ParseException {
		String adtk = null;
		String[] str = pnrText.split(" ");
		for (int i = 0; i < str.length; i++) {
			if ("ADTK".equalsIgnoreCase(str[i])) {
				adtk = str[i + 3];
				break;
			}
		}
		if (adtk == null) {
			return null;
		}
		// String str1 = "11";
		// SimpleDateFormat sdf = new SimpleDateFormat("MM");
		// Date date = sdf.parse(str1);
		// sdf = new SimpleDateFormat("MMMMM",Locale.US);
		// System.out.println(sdf.format(date));
		String day = adtk.substring(3, 5);
		String mon = adtk.substring(5, 8);
		String month = parseMonthToNumber(mon);
		String year = adtk.substring(8, 10);
		String hour = adtk.substring(11, 13);
		String minute = adtk.substring(13, 15);
		return "20" + year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + "00"; // 组装时间字符串
	}

	/**
	 * 通过pnrText截取pnrNoTime 解析：SSR ADTK 1E TO KE BY 30JUL 1900 HKG OTHERWISE
	 * WILL BE XLD这种格式的ADTK
	 * 
	 * @param pnrText
	 * @return
	 * @throws ParseException
	 */
	private String dealPnrNoTimeTwo(String pnrText) throws ParseException {
		String date = null; // 格式为
		String time = null;
		String[] str = pnrText.split(" ");
		for (int i = 0; i < str.length; i++) {
			if ("ADTK".equalsIgnoreCase(str[i])) {
				date = str[i + 5];
				time = str[i + 6];
				break;
			}
		}
		if (date == null) {
			return null;
		}
		// String str1 = "11";
		// SimpleDateFormat sdf = new SimpleDateFormat("MM");
		// Date date = sdf.parse(str1);
		// sdf = new SimpleDateFormat("MMMMM",Locale.US);
		// System.out.println(sdf.format(date));
		String day = date.substring(0, 2);
		String mon = date.substring(2, 5);
		String month = parseMonthToNumber(mon);
		String year = "" + DateUtils.getCurrentYear();
		String hour = time.substring(0, 2);
		String minute = time.substring(2, 4);
		return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + "00"; // 组装时间字符串
	}

	/**
	 * 处理这种格式的ADTK:9.SSR ADTK 1E JL S-CLS BY XX 27JUL XX USING SSR OR WILL BE
	 * XLD
	 * 
	 * @param pnrText
	 * @return
	 * @throws ParseException
	 */
	private String dealPnrNoTimeThree(String pnrText) throws ParseException {
		String date = null; // 格式为
		String[] str = pnrText.split(" ");
		for (int i = 0; i < str.length; i++) {
			if ("ADTK".equalsIgnoreCase(str[i])) {
				date = str[i + 6];
				break;
			}
		}
		if (date == null) {
			return null;
		}
		String day = date.substring(0, 2);
		String mon = date.substring(2, 5);
		String month = parseMonthToNumber(mon);
		String year = "" + DateUtils.getCurrentYear();
		String hour = "00";
		String minute = "00";
		return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + "00"; // 组装时间字符串
	}

	/**
	 * 处理这种格式的ADTK:8.SSR OTHS 1E PLS TKT BY 1615/22JUL/SZX OR ITIN WILL BE
	 * AUTO-CANCELED BY UL/ /MJ
	 * 
	 * @param pnrText
	 * @return
	 * @throws ParseException
	 */
	private String dealPnrNoTimeFour(String pnrText) throws ParseException {
		String date = null; // 格式为
		String[] str = pnrText.split(" ");
		for (int i = 0; i < str.length; i++) {
			if ("TKT".equalsIgnoreCase(str[i])) {
				if ("By".equalsIgnoreCase(str[i + 1])) {
					date = str[i + 2];
					break;
				}
			}
		}
		if (date == null) {
			return null;
		}
		String hour = date.substring(0, 2);
		String minute = date.substring(2, 4);
		String day = date.substring(5, 7);
		String mon = date.substring(7, 10);
		String month = parseMonthToNumber(mon);
		String year = "" + DateUtils.getCurrentYear();
		return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + "00"; // 组装时间字符串
	}

	private String parseMonthToNumber(String month) {
		if (StringUtils.isEmpty(month)) {
			throw new RuntimeException("传入的月份不能为空");
		}
		if ("JAN".equalsIgnoreCase(month)) {
			return "01";
		} else if ("Feb".equalsIgnoreCase(month)) {
			return "02";
		} else if ("Mar".equalsIgnoreCase(month)) {
			return "03";
		} else if ("Apr".equalsIgnoreCase(month)) {
			return "04";
		} else if ("May".equalsIgnoreCase(month)) {
			return "05";
		} else if ("Jun".equalsIgnoreCase(month)) {
			return "06";
		} else if ("Jul".equalsIgnoreCase(month)) {
			return "07";
		} else if ("Aug".equalsIgnoreCase(month)) {
			return "08";
		} else if ("Sep".equalsIgnoreCase(month)) {
			return "09";
		} else if ("Oct".equalsIgnoreCase(month)) {
			return "10";
		} else if ("Nov".equalsIgnoreCase(month)) {
			return "11";
		} else if ("Dec".equalsIgnoreCase(month)) {
			return "12";
		}
		return "00";
	}

	@Override
	public boolean updateStatus(String id, String status) throws Exception {
		return this.factoryDao.getSaleOrderDao().updateStatus(id, status);
	}

	@Override
	public List<GjSaleOrderEntity> queryByCreateTime(String startTime, String endTime) throws Exception {
		List<GjSaleOrderEntity> gjSaleOrderEntities = factoryDao.getSaleOrderDao().queryByCreateTime(startTime, endTime);
		if (gjSaleOrderEntities != null && gjSaleOrderEntities.size() > 0) {
			List<String> fkids = new ArrayList<String>();
			for (GjSaleOrderEntity gjSaleOrderEntity : gjSaleOrderEntities) {
				fkids.add(gjSaleOrderEntity.getId());
			}
			List<GjSalePassengerEntity> gjSalePassengerEntities = factoryDao.getSalePassengerDao().queryByFkids(fkids);
			for (GjSaleOrderEntity gjSaleOrderEntity : gjSaleOrderEntities) {
				for (int i = gjSalePassengerEntities.size() - 1; i >= 0; i--) {
					if (gjSaleOrderEntity.getId().equals(gjSalePassengerEntities.get(i).getFkid())) {
						gjSaleOrderEntity.getPassengers().add(gjSalePassengerEntities.get(i));
						gjSalePassengerEntities.remove(gjSalePassengerEntities.get(i)); // 这样移除后是不是会加快循环的效率？
					}
				}
			}
		}
		return gjSaleOrderEntities;
	}

	@Override
	public boolean updateEticketAndPnr(String orderId, String pnrCode, JSONArray jsonArray) throws Exception {
		// 更新buy表pnr,pnr不为空才更新，为空一定不能乱更新
		if(StringUtils.isNotEmpty(pnrCode)){
			this.factoryDao.getGjBuyOrderDao().updatePnrCode(orderId, pnrCode);
		}

		// 更新票号
		for (int i = 0; i < jsonArray.size(); i++) {
			String id = jsonArray.getJSONObject(i).getString("id");
			String eticketNum = jsonArray.getJSONObject(i).getString("eticketNum");
			if (StringUtilsc.isNotEmptyAndNULL(id) && StringUtilsc.isNotEmptyAndNULL(eticketNum)) {
				this.factoryDao.getSalePassengerDao().updateTicketNum(id, eticketNum);
				this.factoryDao.getGjBuyPassengerDao().updateTicketNum(id, eticketNum);
			}
		}

		if (isUpdateAllETicketNum(orderId)) {
			// 更新订单状态
			GjSaleOrderEntity gjSaleOrderEntity=new GjSaleOrderEntity();
			gjSaleOrderEntity.setId(orderId);
			gjSaleOrderEntity.setStatus(BBOOConstants.GjSaleOrderEntity_SLFSTATUS_TWO);
			gjSaleOrderEntity.setSlfStatus(BBOOConstants.GjSaleOrderEntity_SLFSTATUS_TWO);
			GjBuyOrderEntity gjBuyOrderEntity=new GjBuyOrderEntity();
			gjBuyOrderEntity.setId(orderId);
			gjBuyOrderEntity.setStatus(BBOOConstants.GjBuyOrderEntity_slfStatus_two);
			gjBuyOrderEntity.setSlfStatus(BBOOConstants.GjBuyOrderEntity_slfStatus_two);
			factoryDao.getSaleOrderDao().updateByID(gjSaleOrderEntity);
			factoryDao.getGjBuyOrderDao().updateById(gjBuyOrderEntity);
			return true;
		}
		return false;
	}

	/**
	 * 是否更新所有票号
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	private boolean isUpdateAllETicketNum(String id) throws Exception {
		GjSaleOrderEntity saleOrderEntity = this.factoryDao.getSaleOrderDao().queryOrderByID(id);
		Set<GjSalePassengerEntity> salePassengerEntities = saleOrderEntity.getPassengers();
		int i = 0;
		for (GjSalePassengerEntity gjSalePassengerEntity : salePassengerEntities) {
			if (StringUtilsc.isNotEmptyAndNULL(gjSalePassengerEntity.getEticketNum())) {
				i++;
			}
		}
		if (i == salePassengerEntities.size()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<SaleOrderAndPassengerVo> queryOrderAndPassengerByCreateTime(OrderAndPassengerParamVo orderAndPassengerParamVo) throws Exception {
		return factoryDao.getSaleOrderDao().queryOrderAndPassengerByCreateTime(orderAndPassengerParamVo);
	}

	@Deprecated
	@Override
	public boolean updateBigpnrAndImportPolicy(BaseAccountEntity baseAccountEntity, GjSaleOrderEntity gjSaleOrderEntity) throws Exception {
		try {
			if(BBOOConstants.GjSaleOrderEntity_flightClass_N.equals(gjSaleOrderEntity.getFlightClass())){
				if (gjSaleOrderEntity.getPnrCode().length() > 6) {
					return false;// 如果既有成人又有儿童pnr，直接不查询运价。
				}
				GetPnrInfoResult rtrs = factoryService.getAllApiService().getRtByThirdInterface(gjSaleOrderEntity,false);
				if (rtrs != null && rtrs.getCode() == 0) { // 如果rt指令成功，更新大编码和pnrtxt
					GjSaleOrderEntity paramVo = new GjSaleOrderEntity();
					paramVo.setId(gjSaleOrderEntity.getId());
					paramVo.setPnrCodeBig(rtrs.getBigPnr());
					paramVo.setPnrText(rtrs.getTxt());
					factoryDao.getSaleOrderDao().updateByID(paramVo);
					//更新flight表
					updateFlightPnrInfo(gjSaleOrderEntity, rtrs);
				}
				List<ProductRootResult> productRootResults=factoryService.getAllApiService().getProductDataByThirdAuthInterface(gjSaleOrderEntity, rtrs);
				if(productRootResults!=null&&productRootResults.size()>0){
					//查询到了新的政策信息，删除原来的政策信息
					factoryService.getProductDataService().deleteProductDataByOrderId(gjSaleOrderEntity.getId());
					Boolean bool=factoryService.getProductDataService().insertPolicyToDB(productRootResults, gjSaleOrderEntity);
					if(bool){
						GjSaleOrderEntity paramVo = new GjSaleOrderEntity();
						paramVo.setId(gjSaleOrderEntity.getId());
						paramVo.setGettime(DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
						paramVo.setIsGet(BBOOConstants.GjSaleOrderEntity_isGet_one);
						factoryDao.getSaleOrderDao().updateByID(paramVo);
					}
				}else{
					GjSaleOrderEntity paramVo = new GjSaleOrderEntity();
					paramVo.setId(gjSaleOrderEntity.getId());
					paramVo.setIsAuto(BBOOConstants.GjSaleOrderEntity_isAuto_two);
					//将是否自动出票设置为手动出票
					factoryDao.getSaleOrderDao().updateByID(paramVo);
				}
			}else if(BBOOConstants.GjSaleOrderEntity_flightClass_I.equals(gjSaleOrderEntity.getFlightClass())){
				//这些写国际订单拉取政策的代码
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	@Override
	public boolean modifyPnr(String orderId,String newPnr) throws Exception {
		if(StringUtils.isEmpty(orderId)||StringUtils.isEmpty(newPnr)){
			return false;
		}
		// 首先将订单查询出来
		GjSaleOrderEntity result = factoryDao.getSaleOrderDao().queryOrderByID(orderId);
		if (result!=null&&!newPnr.equals(result.getPnrCode())) {
			GjSaleOrderEntity paramVo=new GjSaleOrderEntity();
			BeanUtils.copyProperties(result, paramVo);
			paramVo.setPnrCode(newPnr);//这一步设置是最重要的，然而查询出来的result又不能做修改，修改了由于处于持久层，会自动更新数据库
			//首先做rt指令,如果没有rt到东西，说明是错误的pnr，不修改数据库
			GetPnrInfoResult rtrs=factoryService.getAllApiService().getRtByThirdInterface(paramVo,false);
			if(!isPnrMatchPassenger(rtrs, result)){
				throw new RuntimeException("修改后的pnr用rt查询出来的乘机人和订单本身的乘机人不对应，请检测修改后pnr是否为正确的pnr！");
			}
			if(rtrs!=null&&rtrs.getCode()==0){
				// 通过id更新掉pnr和oldpnr
				GjSaleOrderEntity gjSaleOrderEntity=new GjSaleOrderEntity();
				gjSaleOrderEntity.setId(orderId);
				gjSaleOrderEntity.setPnrCode(newPnr);
				gjSaleOrderEntity.setOldPnrCode(result.getPnrCode());
				gjSaleOrderEntity.setPnrCodeBig(rtrs.getBigPnr());
				gjSaleOrderEntity.setPnrText(rtrs.getTxt());
				factoryDao.getSaleOrderDao().updateByID(gjSaleOrderEntity);
				//更新flight表,实际出票航班，实际出票仓位，等等
				updateFlightPnrInfo(paramVo, rtrs);
				//修改了pnr，删除原来的政策信息
				factoryService.getProductDataService().deleteProductDataByOrderId(orderId);
				return true;
			}else{
				if(rtrs!=null){
					throw new RuntimeException(rtrs.getMsg());
				}else{
					throw new RuntimeException("获取pnr信息有误，修改请输入正确的pnr！");
				}
			}
		}else{
			throw new Exception("修改后的pnr和原pnr相同");
		}
	}


	
	private boolean updateFlightPnrInfo(GjSaleOrderEntity gjSaleOrderEntity,GetPnrInfoResult rtrs) throws Exception{
		if(gjSaleOrderEntity!=null&&rtrs!=null){
			List<RtPnrPriceVO> rtPnrPriceVOs=rtrs.getPrices();
			RtPnrPriceVO rtPnrPriceVO=null;
			//求最低价
			if(rtPnrPriceVOs!=null&&rtPnrPriceVOs.size()>0){
				rtPnrPriceVO=rtPnrPriceVOs.get(0);
				for(int i=1;i<rtPnrPriceVOs.size();i++){
						Double double0=Double.parseDouble(rtPnrPriceVO.getFare());
						Double doublei=Double.parseDouble(rtPnrPriceVOs.get(i).getFare());
						if(double0>doublei){
							rtPnrPriceVO=rtPnrPriceVOs.get(i);
						}
				}
			}
			List<RtPnrLineVO> rtPnrLineVOs=rtrs.getLines();
			if(rtPnrLineVOs!=null&&rtPnrLineVOs.size()>0){
				for(RtPnrLineVO rtPnrLineVO:rtPnrLineVOs){
					Set<GjSaleFlightEntity> flights=gjSaleOrderEntity.getFlights();
					if(flights!=null&&flights.size()>0){
						Iterator<GjSaleFlightEntity> iterator=flights.iterator();
						while(iterator.hasNext()){
							GjSaleFlightEntity gjSaleFlightEntity=iterator.next();
							if(gjSaleFlightEntity.getDepAircode().equals(rtPnrLineVO.getDepCityCode())&&gjSaleFlightEntity.getArrAircode().equals(rtPnrLineVO.getArrCityCode())){
								GjSaleFlightEntity newGjSaleFlightEntity=new GjSaleFlightEntity();
								newGjSaleFlightEntity.setId(gjSaleFlightEntity.getId());//id是where条件
								newGjSaleFlightEntity.setActCabin(rtPnrLineVO.getCabinCode());
								newGjSaleFlightEntity.setActDept(rtPnrLineVO.getDepDate());
								newGjSaleFlightEntity.setActFltno(rtPnrLineVO.getFlightNo());
								if(rtPnrPriceVO!=null){
									newGjSaleFlightEntity.setActPrice(rtPnrPriceVO.getFare());
									newGjSaleFlightEntity.setActTax(rtPnrPriceVO.getTax());
									newGjSaleFlightEntity.setActYq(rtPnrPriceVO.getYq());
									newGjSaleFlightEntity.setPriceCount(rtrs.getPrices().size());
								}
								factoryDao.getSaleFlightDao().updateById(newGjSaleFlightEntity);
							}
						}
					}
					
				}
				
			}
		}
		return false;
	}

	@Override
	public boolean reservePnrCode(ReservePnrVo reservePnrVo) throws Exception {
		if(reservePnrVo==null){
			return false;
		}
		GjSaleOrderEntity result=factoryDao.getSaleOrderDao().queryOrderByID(reservePnrVo.getId());
		GjSaleOrderEntity gjSaleOrderEntity=new GjSaleOrderEntity();
		BeanUtils.copyProperties(result, gjSaleOrderEntity); //复制对象
		Set<GjSaleFlightEntity> flights=new HashSet<GjSaleFlightEntity>();
		Set<GjSaleFlightEntity> ftsss=result.getFlights();
		if(ftsss!=null&&ftsss.size()>0){
			for(GjSaleFlightEntity f:ftsss){
				GjSaleFlightEntity nf=new GjSaleFlightEntity();
				BeanUtils.copyProperties(f, nf);
				flights.add(nf);
			}
		}
		gjSaleOrderEntity.setFlights(flights);
		//上面的代码用于将持久层对象复制到游离对象中去
		if(gjSaleOrderEntity!=null){
			if(StringUtils.isNotEmpty(reservePnrVo.getOffice())){
				gjSaleOrderEntity.setRtOffno(reservePnrVo.getOffice());
			}
			Set<GjSaleFlightEntity> gjSaleFlightEntities= gjSaleOrderEntity.getFlights();
			List<FlightVo> flightVos=reservePnrVo.getFlights();
			if(gjSaleFlightEntities!=null&&gjSaleFlightEntities.size()>0&&flightVos!=null&&flightVos.size()>0){
				Iterator<GjSaleFlightEntity> iterator=gjSaleFlightEntities.iterator();
				while(iterator.hasNext()){
					GjSaleFlightEntity gjSaleFlightEntity=iterator.next();
					for(FlightVo flightVo:flightVos){
						if(gjSaleFlightEntity.getId().equals(flightVo.getId())){
							gjSaleFlightEntity.setFlightNum(flightVo.getFlightNum());
							gjSaleFlightEntity.setCabin(flightVo.getCabin());
						}
					}
				}
			}
			BuildPnrResult buildPnrResult=factoryService.getAllApiService().reservePnr(gjSaleOrderEntity);
			if(buildPnrResult!=null&&buildPnrResult.getCode()==BBOOConstants.BuildPnrResult_code_zero){
				GjSaleOrderEntity param=new GjSaleOrderEntity();
				param.setId(reservePnrVo.getId());
				param.setPnrCode(buildPnrResult.getPnr());
				param.setRtOffno(reservePnrVo.getOffice());   //预定编码用的什么office就将rtOffice变成对应的
				param.setIsNewCode(BBOOConstants.GjSaleOrderEntity_isNewCode_one);
				param.setOldPnrCode(result.getPnrCode());  //设置oldpnr
				param.setPnrText(""); //编码变了，需要将pnr相应的做修改！
				factoryDao.getSaleOrderDao().updateByID(param);  //获取到预定的pnr之后，更新本地数据
				return true;
			}
		}
		return false;
	}

	@Override
	public void fillCabinNumber(GjSaleOrderEntity gjSaleOrderEntity) throws Exception {
		if(gjSaleOrderEntity!=null){
			Set<GjSaleFlightEntity> gjSaleFlightEntities=gjSaleOrderEntity.getFlights();
			if(gjSaleFlightEntities!=null&&gjSaleFlightEntities.size()>0){
				int minCabinNumber=999999999;//一个很大的数,用来表示舱位的上限
				Integer isLowspace=BBOOConstants.GjSaleOrderEntity_isLowspace_TWO; //1有底舱 2正常（考虑到以后要排序，有底舱的排到前面）
				boolean isExistCabin=false; //是否某个航程能够匹配舱位，并且舱位数大于0
				Iterator<GjSaleFlightEntity> iterator=gjSaleFlightEntities.iterator();
				while(iterator.hasNext()){
					GjSaleFlightEntity gjSaleFlightEntity=iterator.next();
					GetFlightByLineResult gjFlightByLineResult=factoryService.getAllApiService().getAvhByThirdInterface(gjSaleOrderEntity.getMno(), gjSaleOrderEntity.getRtOffno(), gjSaleFlightEntity);
					
					if(gjFlightByLineResult.getCode()==0){
						 List<FlightByLineVO> flightByLineVOs=gjFlightByLineResult.getLines();
						if(flightByLineVOs!=null&&flightByLineVOs.size()>0){
							FlightByLineVO result=null;
							for(FlightByLineVO flightByLineVO:flightByLineVOs){
								if(flightByLineVO.getFlightNum().equals(gjSaleFlightEntity.getFlightNum())){
									result=flightByLineVO;
									break;
								}
							}
							if(result!=null){
								StringBuffer stringBuffer=new StringBuffer();
								List<CabinVO> cabinVOs=result.getCabins();
								if(cabinVOs!=null&&cabinVOs.size()>0){
									for(CabinVO cabinVO:cabinVOs){
										stringBuffer.append(cabinVO.getCode()+cabinVO.getNumber()+",");
										if(isExistCabin&&cabinVO.getNumber()>0){
											isLowspace=BBOOConstants.GjSaleOrderEntity_isLowspace_ONE;
											gjSaleFlightEntity.setLowspace(cabinVO.getCode()+cabinVO.getState());
											isExistCabin=false; //这样就只执行一次了
										}
										if(cabinVO.getCode()!=null&&cabinVO.getCode().equals(gjSaleFlightEntity.getCabin().substring(0, 1))){
											int number=cabinVO.getNumber();
											if(number>0){
												isExistCabin=true;
											}
											gjSaleFlightEntity.setCabinNum(number);
											if(number<minCabinNumber){
												minCabinNumber=number;
											}
										}
									}
								}
								gjSaleFlightEntity.setAvText("航班号："+result.getFlightNum()+"；舱位："+stringBuffer.toString());
								gjSaleFlightEntity.setAvTime(DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
							}
						}
					}
					
				}
				gjSaleOrderEntity.setIsLowspace(isLowspace);  //设置是否有底舱
				if(minCabinNumber!=999999999){
					gjSaleOrderEntity.setSeats(minCabinNumber);  //设置剩余舱位数
				}else{
					gjSaleOrderEntity.setSeats(0);  //设置剩余舱位数
				}
			}
		}
		
	}

	@Override
	public List<OrderSummaryVo> queryRefundOrders(RefundVo vo, Page page)
			throws Exception {
		//page.setTotalCount(this.factoryService.getSaleOrderService().countRefundOrders(vo, entity));
		List<Object[]>  objList = this.factoryDao.getSaleOrderDao().queryRefundOrders(vo, page);
		List<OrderSummaryVo> list = new ArrayList<OrderSummaryVo>();
		OrderSummaryVo orderSummaryVo = null;
		List<FlightVo> flightList = null;
		FlightVo flightVo = null;
		for (Object[] objs : objList) {
			orderSummaryVo = parseToOrderSummaryVo(objs);
			
			flightList = new ArrayList<FlightVo>();
			List<Object[]> flightVoList = this.factoryDao.getBaseRefundDao().queryFlightVos(orderSummaryVo.getBrid());
			for (Object[] fltObjs : flightVoList) {
				flightVo = parseToFlightVo(fltObjs);
				flightList.add(flightVo);
			}
			orderSummaryVo.setFlightVos(flightList);
			list.add(orderSummaryVo);
		}
		return list;
	}
	
	private FlightVo parseToFlightVo(Object[] objs) {
		FlightVo vo = new FlightVo();
		int i = 0;
		vo.setId(checkNullStr(objs[i++]));
		vo.setDepartureTime(checkNullStr(objs[i++]));
		vo.setDepAircode(checkNullStr(objs[i++]));
		vo.setArrivalTime(checkNullStr(objs[i++]));
		vo.setArrAircode(checkNullStr(objs[i++]));
		vo.setDepartureDate(checkNullStr(objs[i++]));
		vo.setCabin(checkNullStr(objs[i++]));
		vo.setFlightNum(checkNullStr(objs[i++]));
		vo.setCabinNum(checkNullStr(objs[i++]));
		vo.setLowspace(checkNullStr(objs[i++]));
		return vo;
	}
	
	/**
	 * Object[] 转化为 OrderSummaryVo
	 * @param objs
	 * @return
	 */
	private OrderSummaryVo parseToOrderSummaryVo(Object[] objs) {
		OrderSummaryVo vo = new OrderSummaryVo();
		int i = 0;
		vo.setBrid(checkNullStr(objs[i++]));
		vo.setPassengerCount(Integer.parseInt(objs[i++].toString()));
		vo.setLockUser(checkNullStr(objs[i++]));
		
		vo.setId(checkNullStr(objs[i++]));
		vo.setOrderNo(checkNullStr(objs[i++]));
		vo.setCreateTime(checkNullStr(objs[i++]));
		vo.setFlightType(checkNullStr(objs[i++]));
		vo.setPnrCode(checkNullStr(objs[i++]));
		vo.setAllprice(Double.parseDouble(objs[i++].toString()));
		vo.setDistributor(checkNullStr(objs[i++]));
		vo.setDistributeCh(checkNullStr(objs[i++]));
		vo.setPnrNoTime(checkNullStr(objs[i++]));
		vo.setLeaveRemark(checkNullStr(objs[i++]));
		vo.setBillId(checkNullStr(objs[i++]));
		vo.setShopName(checkNullStr(objs[i++]));
		vo.setShopNameCh(checkNullStr(objs[i++]));
		vo.setOldOrderNo(checkNullStr(objs[i++]));
		vo.setPolicyType(Integer.parseInt(objs[i++].toString()));
		vo.setIsAuto(checkNumInt(objs[i++]));
		vo.setStatement(checkNullStr(objs[i++]));
		vo.setPnrType(Integer.parseInt(objs[i++].toString()));
		vo.setSlfStatus(checkNullStr(objs[i++]));
		return vo;
	}

	@Override
	public Integer countRefundOrders(SaleOrderParamVo vo,
			BaseRefundEntity entity) throws Exception {
		return this.factoryDao.getSaleOrderDao().countRefundOrders(vo, entity);
	}
	
	private String checkNullStr(Object obj) {
		return obj == null ? "" : obj.toString();
	}
	private Integer checkNumInt(Object obj) {
		return obj == null ? 0 : Integer.parseInt(obj.toString());
	}

	@Override
	public void autoReservePnr(BaseAccountEntity baseAccountEntity, GjSaleOrderEntity gjSaleOrderEntity) throws Exception {
		if(baseAccountEntity!=null&&gjSaleOrderEntity!=null){
			if(StringUtils.isEmpty(gjSaleOrderEntity.getPnrCode())&&BBOOConstants.BASEACCOUNT_issd_one.equals(baseAccountEntity.getIssd())&&StringUtils.isNotEmpty(baseAccountEntity.getSdoffice())){
				boolean isSharedFlightExist=false;
				Set<GjSaleFlightEntity> gjSaleFlightEntities=gjSaleOrderEntity.getFlights();
				Iterator<GjSaleFlightEntity> iterator=gjSaleFlightEntities.iterator();
				while(iterator.hasNext()){
					GjSaleFlightEntity gjSaleFlightEntity=iterator.next();
					if(BBOOConstants.GjSaleFlightEntity_isShared_Y.equals(gjSaleFlightEntity.getIsShared())){
						//是否共享航班 Y:是,N:否 T其它
						isSharedFlightExist=true;
					}
				}
				if(!isSharedFlightExist){
					gjSaleOrderEntity.setRtOffno(baseAccountEntity.getSdoffice());
					BuildPnrResult buildPnrResult=factoryService.getAllApiService().reservePnr(gjSaleOrderEntity);
					if(buildPnrResult!=null&&buildPnrResult.getCode()==BBOOConstants.BuildPnrResult_code_zero){
						GjSaleOrderEntity param=new GjSaleOrderEntity();
						param.setId(gjSaleOrderEntity.getId());
						param.setPnrCode(buildPnrResult.getPnr());
						param.setRtOffno(gjSaleOrderEntity.getRtOffno());   //预定编码用的什么office就将rtOffice变成对应的
						param.setOldPnrCode(gjSaleOrderEntity.getPnrCode());  //设置oldpnr
						param.setIsNewCode(BBOOConstants.GjSaleOrderEntity_isAuto_one);
						param.setPnrText(""); //编码变了，需要将pnr相应的做修改！
						factoryDao.getSaleOrderDao().updateByID(param);  //获取到预定的pnr之后，更新本地数据
						gjSaleOrderEntity.setPnrCode(buildPnrResult.getPnr()); //这里一定要将pnr设置为预定后的pnr，后面去查rt才有pnr
					}
				}else{
					//共享航班的处理代码
				}
			}
		}
		
	}

	@Override
	public List<GjSaleOrderEntity> autoSplitOrder(GjSaleOrderEntity gjSaleOrderEntity) throws Exception {
		if(gjSaleOrderEntity==null){
			return null;
		}else{
			//步凑一，将航班信息进行一次拆分
			Set<GjSaleFlightEntity> gjSaleFlightEntities=gjSaleOrderEntity.getFlights();
			Iterator<GjSaleFlightEntity> iterator=gjSaleFlightEntities.iterator();
			Map<String,Set<GjSaleFlightEntity>> flightMap=new HashMap<String, Set<GjSaleFlightEntity>>();
			while(iterator.hasNext()){
				GjSaleFlightEntity gjSaleFlightEntity=iterator.next();
				String carrier=gjSaleFlightEntity.getCarrier();  //航司作为map的key
				Set<GjSaleFlightEntity> mapFlightEntities=flightMap.get(carrier);
				if( mapFlightEntities!= null){
					mapFlightEntities.add(gjSaleFlightEntity);
				}else{
					Set<GjSaleFlightEntity> firstFlightEntities=new HashSet<GjSaleFlightEntity>();
					firstFlightEntities.add(gjSaleFlightEntity);
					flightMap.put(carrier, firstFlightEntities);
				}
			}
			//步凑二，将乘客进行一次拆分
			Set<GjSalePassengerEntity> gjSalePassengerEntities=gjSaleOrderEntity.getPassengers();
			Iterator<GjSalePassengerEntity> passengerIterator=gjSalePassengerEntities.iterator();
			Map<Integer,Set<GjSalePassengerEntity>> passengerMap=new HashMap<Integer,Set<GjSalePassengerEntity>>();
			while(passengerIterator.hasNext()){
				GjSalePassengerEntity gjSalePassengerEntity=passengerIterator.next();
				Integer ageType=gjSalePassengerEntity.getAgeType();
				Set<GjSalePassengerEntity> passengerSet=passengerMap.get(ageType);
				if(passengerSet!=null){
					passengerSet.add(gjSalePassengerEntity);
				}else{
					Set<GjSalePassengerEntity> firstPassengerEntities=new HashSet<GjSalePassengerEntity>();
					firstPassengerEntities.add(gjSalePassengerEntity);
					passengerMap.put(ageType, firstPassengerEntities);
				}
			}
			
			
			List<GjSaleOrderEntity> gjSaleOrderEntities=new ArrayList<GjSaleOrderEntity>();
			if(flightMap.size()<2&&passengerMap.size()<2){
				gjSaleOrderEntities.add(gjSaleOrderEntity);
				return gjSaleOrderEntities;
			}
			for(String flightKey:flightMap.keySet()){
				for(Integer passengerKey:passengerMap.keySet()){
					GjSaleOrderEntity tempGjSaleOrderEntity=new GjSaleOrderEntity();
					BeanUtils.copyProperties(gjSaleOrderEntity, tempGjSaleOrderEntity, new String[]{"flights","passengers"});
					tempGjSaleOrderEntity.setId(UniqId.getInstance().getStrId());
					tempGjSaleOrderEntity.setOldId(gjSaleOrderEntity.getId());
					 Set<GjSaleFlightEntity> gjSaleFlightEntitySet=flightMap.get(flightKey);
					 Set<GjSaleFlightEntity> newGjSaleFlightEntities=new HashSet<GjSaleFlightEntity>();
					 //由于用的是hibernate多对多关联，所以这里要做处理
					 for(GjSaleFlightEntity gjSaleFlightEntity:gjSaleFlightEntitySet){
						 GjSaleFlightEntity newGjSaleFlightEntity=new GjSaleFlightEntity();
						 BeanUtils.copyProperties(gjSaleFlightEntity, newGjSaleFlightEntity);
						 newGjSaleFlightEntity.setId(UniqId.getInstance().getStrId());
						 newGjSaleFlightEntity.setOrder(tempGjSaleOrderEntity);
						 newGjSaleFlightEntities.add(newGjSaleFlightEntity);
					 }
					tempGjSaleOrderEntity.setFlights(newGjSaleFlightEntities);
					Iterator<GjSaleFlightEntity> gjSaleFlightEntityIterator=gjSaleFlightEntitySet.iterator();
					GjSaleFlightEntity gjSaleFlightEntity=gjSaleFlightEntityIterator.next();
					String tempString=gjSaleFlightEntity.getDepartureDate()+" "+gjSaleFlightEntity.getDepartureTime();
					for(GjSaleFlightEntity gjSaleFlightEntity2:flightMap.get(flightKey)){
						String departure=gjSaleFlightEntity2.getDepartureDate()+" "+gjSaleFlightEntity2.getDepartureTime();
						if(tempString.compareTo(departure)>1){
							gjSaleFlightEntity=gjSaleFlightEntity2;
						}
					}
					tempGjSaleOrderEntity.setDepartureDate(gjSaleFlightEntity.getDepartureDate());
					tempGjSaleOrderEntity.setDepartureTime(gjSaleFlightEntity.getDepartureTime());
					Set<GjSalePassengerEntity> gjPassengerEntities=passengerMap.get(passengerKey);
					Set<GjSalePassengerEntity> newGjPassengerEntities=new HashSet<GjSalePassengerEntity>();
					for(GjSalePassengerEntity gjSalePassengerEntity:gjPassengerEntities){
						GjSalePassengerEntity newGjSalePassengerEntity=new GjSalePassengerEntity();
						BeanUtils.copyProperties(gjSalePassengerEntity, newGjSalePassengerEntity);
						newGjSalePassengerEntity.setId(UniqId.getInstance().getStrId());
						newGjSalePassengerEntity.setOrder(tempGjSaleOrderEntity);
						newGjPassengerEntities.add(newGjSalePassengerEntity);
						//这里额外设置一个东西，pnr
						tempGjSaleOrderEntity.setPnrCode(gjSalePassengerEntity.getPnr());//值类型，不用担心引用问题
						tempGjSaleOrderEntity.setSalePnrCode(gjSalePassengerEntity.getPnr());
					}
					tempGjSaleOrderEntity.setPassengers(newGjPassengerEntities);
					//下面用于计算拆单后需要单独计算的总数相关的字段
					Iterator<GjSalePassengerEntity> gjIterator=gjPassengerEntities.iterator();
					Double allprice=0.0;
					Integer passengerCount=0;
					Integer ageType=-1;//0 成人 1 儿童
					while(gjIterator.hasNext()){
						GjSalePassengerEntity gjSalePassengerEntity=gjIterator.next();
						ageType=gjSalePassengerEntity.getAgeType();  //0 成人 1 儿童
						//销售价+燃油+基建+保险费+快递费=客人的支付价格
						Double payPrice=MathUtil.add(gjSalePassengerEntity.getCost(), MathUtil.add(gjSalePassengerEntity.getTaxFee(),gjSalePassengerEntity.getOilFee()));
						allprice=MathUtil.add(allprice,payPrice);
						passengerCount++;
					}
					tempGjSaleOrderEntity.setPassengerCount(passengerCount);
					if(ageType==BBOOConstants.GjSalePassengerEntity_ageType_adult){
						tempGjSaleOrderEntity.setAdultsCount(passengerCount);
					}else if(ageType==BBOOConstants.GjSalePassengerEntity_ageType_child){
						tempGjSaleOrderEntity.setChildrenCount(passengerCount);
					}
					tempGjSaleOrderEntity.setOldAllprice(gjSaleOrderEntity.getAllprice());
					tempGjSaleOrderEntity.setAllprice(allprice);
					tempGjSaleOrderEntity.setIsSplit(true);
					gjSaleOrderEntities.add(tempGjSaleOrderEntity);
				}
			}
			return gjSaleOrderEntities;
		}
	}
	
	public boolean isPnrMatchPassenger(GetPnrInfoResult rtrs,GjSaleOrderEntity gjSaleOrderEntity){
		if(rtrs!=null&&gjSaleOrderEntity!=null){
			if(BBOOConstants.GetPnrInfoResult_code_zero.equals(rtrs.getCode())){
				List<RtPnrPassengerVO> rtPnrPassengerVOs=rtrs.getPassengers();
				Set<GjSalePassengerEntity> gjSalePassengerEntities=gjSaleOrderEntity.getPassengers();
				if(rtPnrPassengerVOs!=null&&rtPnrPassengerVOs.size()>0&&gjSalePassengerEntities!=null&&gjSalePassengerEntities.size()>0){
					for(RtPnrPassengerVO rtPnrPassengerVO:rtPnrPassengerVOs){
						for(GjSalePassengerEntity gjSalePassengerEntity:gjSalePassengerEntities){
							String passengerName=rtPnrPassengerVO.getName();
							if(passengerName!=null&&passengerName.equals(gjSalePassengerEntity.getName())){
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	@Override
	public List<GjSaleOrderEntity> readOrderSummaryByPage1(Page page,
			User user, SaleOrderParamVo saleOrderParamEntity) throws Exception {
		return factoryDao.getSaleOrderDao().queryOrderSummaryByPage2(page, user, saleOrderParamEntity);
	}

	@Override
	public boolean updatePnrTxtAndBigPnrByRtResult(
			GjSaleOrderEntity gjSaleOrderEntity, GetPnrInfoResult rtrs)
			throws Exception {
		if (rtrs != null && rtrs.getCode() == 0) { // 如果rt指令成功，更新大编码和pnrtxt
			GjSaleOrderEntity paramVo = new GjSaleOrderEntity();
			paramVo.setId(gjSaleOrderEntity.getId());
			paramVo.setPnrCodeBig(rtrs.getBigPnr());
			paramVo.setPnrText(rtrs.getTxt());
			factoryDao.getSaleOrderDao().updateByID(paramVo);
			//更新flight表
			updateFlightPnrInfo(gjSaleOrderEntity, rtrs);
			return true;
		}
		return false;
	}
	
}
