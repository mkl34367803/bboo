package com.smart.oo.service.imp;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smart.comm.entity.BaseAccountEntity;
import com.smart.comm.entity.BaseOfficeEntity;
import com.smart.comm.entity.GjBuyFlightEntity;
import com.smart.comm.entity.GjBuyOrderEntity;
import com.smart.comm.entity.GjBuyPassengerEntity;
import com.smart.comm.entity.GjSaleFlightEntity;
import com.smart.comm.entity.GjSaleOrderEntity;
import com.smart.comm.entity.GjSalePassengerEntity;
import com.smart.comm.entity.KeyValEntity;
import com.smart.comm.entity.ProductPriceEntity;
import com.smart.comm.utils.BBOOConstants;
import com.smart.comm.utils.DateUtil;
import com.smart.framework.base.Page;
import com.smart.oo.dao.imp.FactoryDaoImpl;
import com.smart.oo.domain.AccountVO;
import com.smart.oo.domain.CreateOrderForPb2bDomain;
import com.smart.oo.domain.PayOrderForPb2bDomain;
import com.smart.oo.domain.res.AirPriceVO;
import com.smart.oo.domain.res.AirPsgVO;
import com.smart.oo.domain.res.AirTripVO;
import com.smart.oo.domain.res.CreateOrderForPb2bResult;
import com.smart.oo.domain.res.EtdzPassengerVO;
import com.smart.oo.domain.res.EtdzResult;
import com.smart.oo.domain.res.PayOrderForPb2bResult;
import com.smart.oo.domain.res.Pb2bOrderElementVO;
import com.smart.oo.domain.res.ProductElementVO;
import com.smart.oo.from.BuyOrderParamVo;
import com.smart.oo.from.BuyOrderProfit;
import com.smart.oo.from.BuyOrderSummaryVo;
import com.smart.oo.from.OrderChartsVo;
import com.smart.oo.from.PurchaseInfoVo;
import com.smart.oo.service.IGJBuyOrderService;
import com.smart.oo.service.api.AllApi;
import com.smart.oo.service.trigger.MainTest;
import com.smart.utils.DateUtils;
import com.smart.utils.MathUtil;
import com.smart.utils.UniqId;

@Service("GJBuyOrderServiceImpl")
public class GJBuyOrderServiceImpl implements IGJBuyOrderService {
	@Autowired
	private FactoryDaoImpl factoryDao;
	
	@Autowired
	private FactoryServiceImpl factoryService;

	@Transactional
	@Override
	public boolean splitOrder(String id, String[] passengerIDs) throws Exception {
		GjBuyOrderEntity gjBuyOrderEntity = factoryDao.getGjBuyOrderDao().queyOrderByID(id);
		if (gjBuyOrderEntity != null) {
			GjBuyOrderEntity newBuyOrderEntity = new GjBuyOrderEntity();
			BeanUtils.copyProperties(gjBuyOrderEntity, newBuyOrderEntity, new String[] { "passengers" });
			// 需不需要先将航班信息的订单的主键设置为空？？？？
			newBuyOrderEntity.setId(UniqId.getInstance().getStrId());
			Set<GjBuyPassengerEntity> passengers = gjBuyOrderEntity.getPassengers();
			if (passengers != null && passengers.size() > 0) {
				Set<GjBuyPassengerEntity> newBuyPassengerEntities = new HashSet<GjBuyPassengerEntity>();
				for (GjBuyPassengerEntity gjBuyPassengerEntity : passengers) {
					for (String string : passengerIDs) {
						if (gjBuyPassengerEntity.getId().equals(string)) {
							GjBuyPassengerEntity newBuyPassengerEntity = new GjBuyPassengerEntity();
							BeanUtils.copyProperties(gjBuyPassengerEntity, newBuyPassengerEntity);
							newBuyPassengerEntities.add(newBuyPassengerEntity);
							gjBuyPassengerEntity.setIsDelete(true);
						}
					}
				}
				newBuyOrderEntity.setPassengers(newBuyPassengerEntities);
			}
			factoryDao.getGjBuyOrderDao().saveBuyOrder(gjBuyOrderEntity);
		}
		factoryDao.getGjBuyOrderDao().updateBuyOrder(gjBuyOrderEntity);
		return false;

	}

	@Transactional
	@Override
	public void savePurchaseInfo(PurchaseInfoVo purchaseInfoVo) throws Exception {
		if (StringUtils.isNotEmpty(purchaseInfoVo.getId())) {
			GjBuyOrderEntity buyOrder = factoryDao.getGjBuyOrderDao().queyOrderByID(purchaseInfoVo.getId());
			GjSaleOrderEntity gjSaleOrderEntity = factoryDao.getSaleOrderDao().queryOrderByID(purchaseInfoVo.getId());
			if (buyOrder != null) {
				// 说明已经保存过采购信息，这次是修改以前保存的采购信息。
				buyOrder.setPurchaseNo(purchaseInfoVo.getPurchaseNo());
				buyOrder.setPnrCode(purchaseInfoVo.getPnrCode());
				buyOrder.setPayWay(purchaseInfoVo.getPayWay());
				buyOrder.setPayAccount(purchaseInfoVo.getPayAccount());
				buyOrder.setTransactionNo(purchaseInfoVo.getTransactionNo());
				buyOrder.setPurchasePlace(purchaseInfoVo.getPurchasePlace());
				buyOrder.setPurchasePlaceCh(purchaseInfoVo.getPurchasePlaceCh());
				buyOrder.setSlfRemark(purchaseInfoVo.getSlfRemark());
				if (StringUtils.isNotEmpty(purchaseInfoVo.getPrintPrice())) {
					buyOrder.setPrintPrice(Double.parseDouble(purchaseInfoVo.getPrintPrice())); // 票面价
				}else{
					buyOrder.setPrintPrice(new Double(0)); // 票面价,设置默认值
				}
				if (StringUtils.isNotEmpty(purchaseInfoVo.getBackPoint())) {
					buyOrder.setBackPoint(Double.parseDouble(purchaseInfoVo.getBackPoint()));
				}else{
					buyOrder.setBackPoint(new Double(0));
				}
				if (StringUtils.isNotEmpty(purchaseInfoVo.getAfterPoint())) {
					buyOrder.setAfterPoint(Double.parseDouble(purchaseInfoVo.getAfterPoint()));
				}else{
					buyOrder.setAfterPoint(new Double(0));
				}
				if (StringUtils.isNotEmpty(purchaseInfoVo.getStickingPoint())) {
					buyOrder.setStickingPoint(Double.parseDouble(purchaseInfoVo.getStickingPoint()));
				}else{
					buyOrder.setStickingPoint(new Double(0));
				}
				if (StringUtils.isNotEmpty(purchaseInfoVo.getReward())) {
					buyOrder.setReward(Double.parseDouble(purchaseInfoVo.getReward()));
				}else {
					buyOrder.setReward(new Double(0));
				}
				if (StringUtils.isNotEmpty(purchaseInfoVo.getPrice())) {
					buyOrder.setCost(Double.parseDouble(purchaseInfoVo.getPrice()));
				}else {
					buyOrder.setCost(new Double(0));
				}
				if (StringUtils.isNotEmpty(purchaseInfoVo.getOilFee())) {
					buyOrder.setOilFee(Double.parseDouble(purchaseInfoVo.getOilFee()));
				}else {
					buyOrder.setOilFee(new Double(0));
				}
				if (StringUtils.isNotEmpty(purchaseInfoVo.getTaxFee())) {
					buyOrder.setTaxFee(Double.parseDouble(purchaseInfoVo.getTaxFee()));
				}else {
					buyOrder.setTaxFee(new Double(0));
				}
				// buyOrder.setSalePrice(gjSaleOrderEntity.getAllprice());//第一次设置了，这里修改的时候不用设置
				if (StringUtils.isNotEmpty(purchaseInfoVo.getLr())) {
					buyOrder.setLr(Double.parseDouble(purchaseInfoVo.getLr()));// 利润
				}else{
					buyOrder.setLr(new Double(0));
				}
				if (StringUtils.isNotEmpty(purchaseInfoVo.getAllprice())) { // 结算总价
					buyOrder.setAllprice(Double.parseDouble(purchaseInfoVo.getAllprice()));
				}else{
					buyOrder.setAllprice(new Double(0));
				}
				if (StringUtils.isNotEmpty(purchaseInfoVo.getPayPirce())) { // 结算总价
					buyOrder.setPayPirce(Double.parseDouble(purchaseInfoVo.getPayPirce()));
				}else{
					buyOrder.setPayPirce(new Double(0));
				}
				fillPurchaseInfo(purchaseInfoVo, buyOrder);
				if (buyOrder.getPassengers() != null && buyOrder.getPassengers().size() > 0) {
					for (GjBuyPassengerEntity gjBuyPassengerEntity : buyOrder.getPassengers()) {
						if (StringUtils.isNotEmpty(purchaseInfoVo.getPrintPrice())) {
							gjBuyPassengerEntity.setPrintPrice(Double.parseDouble(purchaseInfoVo.getPrintPrice())); // 设置票面价
						}else {
							gjBuyPassengerEntity.setPrintPrice(new Double(0));
						}
						if (StringUtils.isNotEmpty(purchaseInfoVo.getPrice())) {
							gjBuyPassengerEntity.setCost(Double.parseDouble(purchaseInfoVo.getPrice())); // 设置支付机票款
						}else {
							gjBuyPassengerEntity.setCost(new Double(0));
						}
						if (StringUtils.isNotEmpty(purchaseInfoVo.getOilFee())) {
							gjBuyPassengerEntity.setOilFee(Double.parseDouble(purchaseInfoVo.getOilFee())); // 设置燃油费
						}else {
							gjBuyPassengerEntity.setOilFee(new Double(0));
						}
						if (StringUtils.isNotEmpty(purchaseInfoVo.getTaxFee())) {
							gjBuyPassengerEntity.setTaxFee(Double.parseDouble(purchaseInfoVo.getTaxFee())); // 设置基建费
						}else {
							gjBuyPassengerEntity.setTaxFee(new Double(0));
						}
						if (StringUtils.isNotEmpty(purchaseInfoVo.getPayPirce())) {
							gjBuyPassengerEntity.setPayPirce(Double.parseDouble(purchaseInfoVo.getPayPirce())); // 设置结算价
						}else {
							gjBuyPassengerEntity.setPayPirce(new Double(0));
						}
					}
				}
				factoryDao.getGjBuyOrderDao().updateBuyOrder(buyOrder);
			} else if (gjSaleOrderEntity != null) {
				GjBuyOrderEntity gjBuyOrderEntity = new GjBuyOrderEntity();
				BeanUtils.copyProperties(gjSaleOrderEntity, gjBuyOrderEntity, new String[] { "flights", "passengers","status" });
				fillPurchaseInfo(purchaseInfoVo, gjBuyOrderEntity);
				gjBuyOrderEntity.setAddtime(DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
				gjBuyOrderEntity.setStatus(BBOOConstants.GjBuyOrderEntity_slfStatus_five);
				gjBuyOrderEntity.setSlfStatus(BBOOConstants.GjBuyOrderEntity_slfStatus_five);  //将订单状态改成出票中,只有第一次保存需要做这个处理
				if (StringUtils.isNotEmpty(purchaseInfoVo.getPrintPrice())) {
					gjBuyOrderEntity.setPrintPrice(Double.parseDouble(purchaseInfoVo.getPrintPrice())); // 票面价
				}else{
					gjBuyOrderEntity.setPrintPrice(new Double(0)); // 票面价,设置默认值
				}
				if (StringUtils.isNotEmpty(purchaseInfoVo.getBackPoint())) {
					gjBuyOrderEntity.setBackPoint(Double.parseDouble(purchaseInfoVo.getBackPoint()));
				}else{
					gjBuyOrderEntity.setBackPoint(new Double(0));
				}
				if (StringUtils.isNotEmpty(purchaseInfoVo.getAfterPoint())) {
					gjBuyOrderEntity.setAfterPoint(Double.parseDouble(purchaseInfoVo.getAfterPoint()));
				}else{
					gjBuyOrderEntity.setAfterPoint(new Double(0));
				}
				if (StringUtils.isNotEmpty(purchaseInfoVo.getStickingPoint())) {
					gjBuyOrderEntity.setStickingPoint(Double.parseDouble(purchaseInfoVo.getStickingPoint()));
				}else{
					gjBuyOrderEntity.setStickingPoint(new Double(0));
				}
				if (StringUtils.isNotEmpty(purchaseInfoVo.getReward())) {
					gjBuyOrderEntity.setReward(Double.parseDouble(purchaseInfoVo.getReward()));
				}else {
					gjBuyOrderEntity.setReward(new Double(0));
				}
				if (StringUtils.isNotEmpty(purchaseInfoVo.getPrice())) {
					gjBuyOrderEntity.setCost(Double.parseDouble(purchaseInfoVo.getPrice()));
				}else {
					gjBuyOrderEntity.setCost(new Double(0));
				}
				if (StringUtils.isNotEmpty(purchaseInfoVo.getOilFee())) {
					gjBuyOrderEntity.setOilFee(Double.parseDouble(purchaseInfoVo.getOilFee()));
				}else {
					gjBuyOrderEntity.setOilFee(new Double(0));
				}
				if (StringUtils.isNotEmpty(purchaseInfoVo.getTaxFee())) {
					gjBuyOrderEntity.setTaxFee(Double.parseDouble(purchaseInfoVo.getTaxFee()));
				}else {
					gjBuyOrderEntity.setTaxFee(new Double(0));
				}
				gjBuyOrderEntity.setSalePrice(gjSaleOrderEntity.getAllprice());// 销售总价等于卖方的订单总价
				if (gjSaleOrderEntity.getFlights() != null && gjSaleOrderEntity.getFlights().size() > 0) {
					for (GjSaleFlightEntity gjSaleFlightEntity : gjSaleOrderEntity.getFlights()) {
						GjBuyFlightEntity gjBuyFlightEntity = new GjBuyFlightEntity();
						BeanUtils.copyProperties(gjSaleFlightEntity, gjBuyFlightEntity, new String[] { "order" });
						gjBuyFlightEntity.setOrder(gjBuyOrderEntity);
						// 保存航班信息。
						factoryDao.getGjBuyFlightDao().saveFlight(gjBuyFlightEntity);
					}
				}
				if (gjSaleOrderEntity.getPassengers() != null && gjSaleOrderEntity.getPassengers().size() > 0) {
					for (GjSalePassengerEntity gjSalePassengerEntity : gjSaleOrderEntity.getPassengers()) {
						//最新添加，如果修改了pnr信息后，sale里面的pnr信息也要跟着变（包括saleorder和salepassenger）
						gjSalePassengerEntity.setPnr(purchaseInfoVo.getPnrCode());
						GjBuyPassengerEntity gjBuyPassengerEntity = new GjBuyPassengerEntity();
						BeanUtils.copyProperties(gjSalePassengerEntity, gjBuyPassengerEntity, new String[] { "order" });
						gjBuyPassengerEntity.setOrder(gjBuyOrderEntity);
						if (StringUtils.isNotEmpty(purchaseInfoVo.getPrintPrice())) {
							gjBuyPassengerEntity.setPrintPrice(Double.parseDouble(purchaseInfoVo.getPrintPrice())); // 设置票面价
						}else {
							gjBuyPassengerEntity.setPrintPrice(new Double(0));
						}
						if (StringUtils.isNotEmpty(purchaseInfoVo.getPrice())) {
							gjBuyPassengerEntity.setCost(Double.parseDouble(purchaseInfoVo.getPrice())); // 设置支付机票款
						}else {
							gjBuyPassengerEntity.setCost(new Double(0));
						}
						if (StringUtils.isNotEmpty(purchaseInfoVo.getOilFee())) {
							gjBuyPassengerEntity.setOilFee(Double.parseDouble(purchaseInfoVo.getOilFee())); // 设置燃油费
						}else {
							gjBuyPassengerEntity.setOilFee(new Double(0));
						}
						if (StringUtils.isNotEmpty(purchaseInfoVo.getTaxFee())) {
							gjBuyPassengerEntity.setTaxFee(Double.parseDouble(purchaseInfoVo.getTaxFee())); // 设置基建费
						}else {
							gjBuyPassengerEntity.setTaxFee(new Double(0));
						}
						if (StringUtils.isNotEmpty(purchaseInfoVo.getPayPirce())) {
							gjBuyPassengerEntity.setPayPirce(Double.parseDouble(purchaseInfoVo.getPayPirce())); // 设置结算价
						}else {
							gjBuyPassengerEntity.setPayPirce(new Double(0));
						}
						factoryDao.getGjBuyPassengerDao().savePassenger(gjBuyPassengerEntity);
					}
				}
			gjSaleOrderEntity.setOldPnrCode(gjSaleOrderEntity.getPnrCode());
			gjSaleOrderEntity.setPnrCode(purchaseInfoVo.getPnrCode());
			gjSaleOrderEntity.setSlfStatus(BBOOConstants.GjSaleOrderEntity_SLFSTATUS_FIVE);
			factoryDao.getSaleOrderDao().update(gjSaleOrderEntity);
			}
		}
	}
	/**
	 * 将前端传过来的采购信息，保存到buyOrder表中
	 * @param purchaseInfoVo
	 * @param buyOrder
	 */
	private void fillPurchaseInfo(PurchaseInfoVo purchaseInfoVo, GjBuyOrderEntity buyOrder) {
		buyOrder.setPurchaseNo(purchaseInfoVo.getPurchaseNo());
		buyOrder.setPnrCode(purchaseInfoVo.getPnrCode());
		buyOrder.setPayWay(purchaseInfoVo.getPayWay());
		buyOrder.setPayAccount(purchaseInfoVo.getPayAccount());
		buyOrder.setTransactionNo(purchaseInfoVo.getTransactionNo());
		buyOrder.setPurchasePlace(purchaseInfoVo.getPurchasePlace());
		buyOrder.setPurchasePlaceCh(purchaseInfoVo.getPurchasePlaceCh());
		buyOrder.setSlfRemark(purchaseInfoVo.getSlfRemark());
		if (StringUtils.isNotEmpty(purchaseInfoVo.getPrintPrice())) {
			buyOrder.setPrintPrice(Double.parseDouble(purchaseInfoVo.getPrintPrice())); // 票面价
		}else{
			buyOrder.setPrintPrice(new Double(0)); // 票面价,设置默认值
		}
		if (StringUtils.isNotEmpty(purchaseInfoVo.getBackPoint())) {
			buyOrder.setBackPoint(Double.parseDouble(purchaseInfoVo.getBackPoint()));
		}else{
			buyOrder.setBackPoint(new Double(0));
		}
		if (StringUtils.isNotEmpty(purchaseInfoVo.getAfterPoint())) {
			buyOrder.setAfterPoint(Double.parseDouble(purchaseInfoVo.getAfterPoint()));
		}else{
			buyOrder.setAfterPoint(new Double(0));
		}
		if (StringUtils.isNotEmpty(purchaseInfoVo.getStickingPoint())) {
			buyOrder.setStickingPoint(Double.parseDouble(purchaseInfoVo.getStickingPoint()));
		}else{
			buyOrder.setStickingPoint(new Double(0));
		}
		if (StringUtils.isNotEmpty(purchaseInfoVo.getReward())) {
			buyOrder.setReward(Double.parseDouble(purchaseInfoVo.getReward()));
		}else {
			buyOrder.setReward(new Double(0));
		}
		if (StringUtils.isNotEmpty(purchaseInfoVo.getPrice())) {
			buyOrder.setCost(Double.parseDouble(purchaseInfoVo.getPrice()));
		}else {
			buyOrder.setCost(new Double(0));
		}
		if (StringUtils.isNotEmpty(purchaseInfoVo.getOilFee())) {
			buyOrder.setOilFee(Double.parseDouble(purchaseInfoVo.getOilFee()));
		}else {
			buyOrder.setOilFee(new Double(0));
		}
		if (StringUtils.isNotEmpty(purchaseInfoVo.getTaxFee())) {
			buyOrder.setTaxFee(Double.parseDouble(purchaseInfoVo.getTaxFee()));
		}else {
			buyOrder.setTaxFee(new Double(0));
		}
		// buyOrder.setSalePrice(gjSaleOrderEntity.getAllprice());//第一次设置了，这里修改的时候不用设置
		if (StringUtils.isNotEmpty(purchaseInfoVo.getLr())) {
			buyOrder.setLr(Double.parseDouble(purchaseInfoVo.getLr()));// 利润
		}else{
			buyOrder.setLr(new Double(0));
		}
		if (StringUtils.isNotEmpty(purchaseInfoVo.getAllprice())) { // 结算总价
			buyOrder.setAllprice(Double.parseDouble(purchaseInfoVo.getAllprice()));
		}else{
			buyOrder.setAllprice(new Double(0));
		}
		if (StringUtils.isNotEmpty(purchaseInfoVo.getPayPirce())) { // 结算总价
			buyOrder.setPayPirce(Double.parseDouble(purchaseInfoVo.getPayPirce()));
		}else{
			buyOrder.setPayPirce(new Double(0));
		}
	}
	/**
	 * 将前端传过来的采购信息，保存到buyOrder表中
	 * @param purchaseInfoVo
	 * @param buyOrder
	 */
	private void fillPurchaseInfo(Pb2bOrderElementVO purchaseInfoVo,ProductPriceEntity productPriceEntity, GjBuyOrderEntity buyOrder,Double saleAllPrice) throws Exception{
		if(purchaseInfoVo==null){
			return;
		}else{
			BaseAccountEntity baseAccountEntity=factoryDao.getBaseAccountDao().queryBaseAccountByID(Integer.parseInt(productPriceEntity.getProduct().getAccountId()));
			buyOrder.setAccountid(Integer.parseInt(productPriceEntity.getProduct().getAccountId()));
			buyOrder.setPurchaseAccount(baseAccountEntity.getUserName());
			buyOrder.setProductSource(productPriceEntity.getProduct().getProductSource());
			buyOrder.setPolicyCode(productPriceEntity.getProduct().getId());
			buyOrder.setPolicyId(productPriceEntity.getId());
			buyOrder.setPriceType(productPriceEntity.getPriceType());
			buyOrder.setAfterPoint(productPriceEntity.getAfter()); //设置后返(从政策价格里面获取后返)
			buyOrder.setPurchaseNo(purchaseInfoVo.getOrderNo());
			buyOrder.setPnrCode(StringUtils.isNotEmpty(purchaseInfoVo.getNewPnr())?purchaseInfoVo.getNewPnr():buyOrder.getPnrCode());
			//buyOrder.setPayWay(purchaseInfoVo.getPayWay());
			//buyOrder.setPayAccount(purchaseInfoVo.getPayAccount());
			//buyOrder.setTransactionNo(purchaseInfoVo.getTransactionNo());
			buyOrder.setPurchasePlace(baseAccountEntity.getAcctype());// CP_517NA
			buyOrder.setPurchasePlaceCh(baseAccountEntity.getDescribe());
			if(purchaseInfoVo.getPriceList()==null||purchaseInfoVo.getPriceList().size()<1){
				return;
			}else{
				int psgcount=purchaseInfoVo.getPsgList().size();
				buyOrder.setPrintPrice(purchaseInfoVo.getPriceList().get(0).getFarePrice()); // 票面价,设置默认值
				buyOrder.setBackPoint(purchaseInfoVo.getPriceList().get(0).getRebates()); //设置返点
				buyOrder.setStickingPoint(0d);
				buyOrder.setReward(purchaseInfoVo.getPriceList().get(0).getFixedFee());
				buyOrder.setCost(purchaseInfoVo.getPriceList().get(0).getDisPrice());
				buyOrder.setOilFee(new Double(0));//接口返回的没有燃油字段,附个默认值0
				buyOrder.setTaxFee(MathUtil.div(purchaseInfoVo.getPriceList().get(0).getTax(),psgcount));
			    // buyOrder.setSalePrice(gjSaleOrderEntity.getAllprice());//调用方法之前已经设置了这个值了
				buyOrder.setAllprice(purchaseInfoVo.getPriceList().get(0).getPayPrice());
				buyOrder.setPayPirce(MathUtil.div(purchaseInfoVo.getPriceList().get(0).getPayPrice(),psgcount));
				buyOrder.setLr(MathUtil.sub(saleAllPrice, buyOrder.getAllprice()));
			}
			
		}
	}

	@Override
	public GjBuyOrderEntity queyOrderByID(String id) throws Exception {
		return factoryDao.getGjBuyOrderDao().queyOrderByID(id);
	}

	@Override
	public boolean updatePnrCode(String id, String pnrCode) throws Exception {
		return this.factoryDao.getGjBuyOrderDao().updatePnrCode(id, pnrCode);
	}

	@Override
	public List<GjBuyOrderEntity> queryByCreateTime(String startTime, String endTime) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BuyOrderProfit> getProfitByPurchasePlace() throws Exception {
		List<Object[]> result=this.factoryDao.getGjBuyOrderDao().getProfitByPurchasePlace();
		List<BuyOrderProfit> buyOrderProfits=null;
		if(result!=null&&result.size()>0){
			buyOrderProfits=new ArrayList<BuyOrderProfit>();
			for (Iterator<Object[]> iterator = result.iterator(); iterator.hasNext();) {
				Object[] objects = (Object[]) iterator.next();
				BuyOrderProfit buyOrderProfit=new BuyOrderProfit();
				if(objects[0]!=null){
					buyOrderProfit.setLr(Double.parseDouble(objects[0].toString()));
				}
				if(objects[1]!=null){
					buyOrderProfit.setPurchasePlace(objects[1].toString());
				}
				buyOrderProfits.add(buyOrderProfit);
			}
		}
		return buyOrderProfits;
	}

	@Override
	public Boolean createOrderByThirdInterface(String productPriceId,GjSaleOrderEntity gjSaleOrderEntity) throws Exception {
//		return true;
		ProductPriceEntity productPriceEntity=factoryDao.getProductPriceDao().getProductPriceById(productPriceId);
		CreateOrderForPb2bResult createOrderForPb2bResult=factoryService.getAllApiService().createOrderByThirdInterface(productPriceEntity, gjSaleOrderEntity);
		//保存采购信息，将订单状态为创单成功等待支付。
		if(createOrderForPb2bResult!=null){
			if(BBOOConstants.CreateOrderForPb2bResult_executeStatus_SUCCESS.equals(createOrderForPb2bResult.getExecuteStatus())){
				return	saveThirdInterfacePurchaseInfo(createOrderForPb2bResult, productPriceEntity, gjSaleOrderEntity);
			}else{
				throw new RuntimeException(createOrderForPb2bResult.getExecuteMsg()); //将接口的异常抛出去
			}
		}else{
			throw new RuntimeException("创单接口没有返回任何东西");
		}
	}
	
	/**
	 * 保存第三方接口返回来的采购信息
	 * @return
	 * @throws Exception 
	 */
	private Boolean saveThirdInterfacePurchaseInfo(CreateOrderForPb2bResult createOrderForPb2bResult,ProductPriceEntity productPriceEntity,GjSaleOrderEntity gjSaleOrderEntity) throws Exception{
		if(createOrderForPb2bResult==null||createOrderForPb2bResult.getRlts()==null){
			return false;
		}else{

			GjBuyOrderEntity gjBuyOrderEntity = new GjBuyOrderEntity();
			
			BeanUtils.copyProperties(gjSaleOrderEntity, gjBuyOrderEntity, new String[] { "flights", "passengers","status"});
			gjBuyOrderEntity.setSalePrice(gjSaleOrderEntity.getAllprice());// 销售总价等于卖方的订单总价（这一句一定要放到这个位置)这个不能用来算利润，因为没减去佣金
			Double saleAllPrice=0.0;
			for(GjSalePassengerEntity tempPassenger:gjSaleOrderEntity.getPassengers()){
				saleAllPrice=MathUtil.sub(MathUtil.add(tempPassenger.getCost(),(MathUtil.add(tempPassenger.getTaxFee(), tempPassenger.getOilFee()))), tempPassenger.getCommission());
			}
			fillPurchaseInfo(createOrderForPb2bResult.getRlts(),productPriceEntity, gjBuyOrderEntity,saleAllPrice);
			gjBuyOrderEntity.setPayWay(null);//创单的时候置空
			gjBuyOrderEntity.setAddtime(DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
			gjBuyOrderEntity.setStatus(BBOOConstants.GjBuyOrderEntity_slfStatus_zero);
			gjBuyOrderEntity.setSlfStatus(BBOOConstants.GjBuyOrderEntity_slfStatus_zero);  //将订单状态改成出票中,只有第一次保存需要做这个处理
			if (gjSaleOrderEntity.getFlights() != null && gjSaleOrderEntity.getFlights().size() > 0) {
				for (GjSaleFlightEntity gjSaleFlightEntity : gjSaleOrderEntity.getFlights()) {
					GjBuyFlightEntity gjBuyFlightEntity = new GjBuyFlightEntity();
					BeanUtils.copyProperties(gjSaleFlightEntity, gjBuyFlightEntity, new String[] { "order" });
					gjBuyFlightEntity.setOrder(gjBuyOrderEntity);
					// 保存航班信息。
					factoryDao.getGjBuyFlightDao().saveFlight(gjBuyFlightEntity);
				}
			}
			if (gjSaleOrderEntity.getPassengers() != null && gjSaleOrderEntity.getPassengers().size() > 0) {
				for (GjSalePassengerEntity gjSalePassengerEntity : gjSaleOrderEntity.getPassengers()) {
					GjBuyPassengerEntity gjBuyPassengerEntity = new GjBuyPassengerEntity();
					BeanUtils.copyProperties(gjSalePassengerEntity, gjBuyPassengerEntity, new String[] { "order" });
					gjBuyPassengerEntity.setOrder(gjBuyOrderEntity);
					gjBuyPassengerEntity.setPrintPrice(gjBuyOrderEntity.getPrintPrice());//票面价
					gjBuyPassengerEntity.setCost(gjBuyOrderEntity.getCost()); //机票支付款
					gjBuyPassengerEntity.setOilFee(new Double(0));
					gjBuyPassengerEntity.setTaxFee(gjBuyOrderEntity.getTaxFee());
					gjBuyPassengerEntity.setPayPirce(gjBuyOrderEntity.getPayPirce());//采购价
					factoryDao.getGjBuyPassengerDao().savePassenger(gjBuyPassengerEntity);
				}
			}
			GjSaleOrderEntity updateParamVo=new GjSaleOrderEntity();
			updateParamVo.setId(gjSaleOrderEntity.getId());
			updateParamVo.setSlfStatus(BBOOConstants.GjSaleOrderEntity_SLFSTATUS_FIVE);//采购方下单，分销方还是出票中
			factoryDao.getSaleOrderDao().updateByID(updateParamVo);
			return true;
		}
		
	}

	@Override
	public Boolean payOrderByThirdInterface(GjBuyOrderEntity gjBuyOrderEntity) throws Exception {
//		if(0==0){
//			return true;
//		}
		//ProductPriceEntity productPriceEntity=factoryDao.getProductPriceDao().getProductPriceById(gjBuyOrderEntity.getPolicyId());
		BaseAccountEntity baseAccountEntity=factoryDao.getBaseAccountDao().queryBaseAccountByID(gjBuyOrderEntity.getAccountid());
		PayOrderForPb2bResult payOrderForPb2bResult=factoryService.getAllApiService().payOrderByThirdInterface(gjBuyOrderEntity,baseAccountEntity);
		//支付成功后，还要做相应的处理
		if(payOrderForPb2bResult==null){
			return false;
		}
		GjBuyOrderEntity paramVo=new GjBuyOrderEntity();
		paramVo.setId(gjBuyOrderEntity.getId());
		if("S0".equalsIgnoreCase(payOrderForPb2bResult.getExecuteCode())){
			//支付失败，也要修改订单的状态
			paramVo.setStatus(BBOOConstants.GjBuyOrderEntity_slfStatus_one);
			paramVo.setSlfStatus(BBOOConstants.GjBuyOrderEntity_slfStatus_one);
			//gjBuyOrderEntity.setPayWay(payWay);
			paramVo.setPayAccount(baseAccountEntity.getAccpay());
			paramVo.setTransactionNo(payOrderForPb2bResult.getBatchNo());//交易流水号
			List<AirPriceVO> prices=null;
			if(payOrderForPb2bResult.getRlts()!=null){
				Pb2bOrderElementVO elms=payOrderForPb2bResult.getRlts();
				 prices=elms.getPriceList();
			}
			int psgcount=gjBuyOrderEntity.getPassengers().size();
			if(prices!=null&&prices.size()>0){
				Double payPrice=prices.get(0).getPayPrice();
				if(payPrice!=null&&payPrice>0){
					paramVo.setAllprice(payPrice);
					paramVo.setPayPirce(MathUtil.div(payPrice, psgcount)); //支付价格
				}
			}
			factoryDao.getGjBuyOrderDao().updateById(paramVo);
			return true;
		}else{
			//支付失败，也要修改订单的状态
			paramVo.setStatus(BBOOConstants.GjBuyOrderEntity_slfStatus_sixty);
			paramVo.setSlfStatus(BBOOConstants.GjBuyOrderEntity_slfStatus_sixty);
			factoryDao.getGjBuyOrderDao().updateById(paramVo);
			throw new RuntimeException(payOrderForPb2bResult.getExecuteMsg());
		}
	}
	
	@Override
	public List<OrderChartsVo> statisticByPurchasePlaceCh(
			OrderChartsVo orderChartsVo) throws Exception {
		List<Object[]> list = this.factoryDao.getGjBuyOrderDao().statisticTicketByPurchasePlace(orderChartsVo);
		List<OrderChartsVo> voList = null;
		if (list != null && list.size() > 0) {
			voList = new ArrayList<OrderChartsVo>();
			Iterator<Object[]> it = list.iterator();
			while(it.hasNext()) {
				Object[] objs = it.next();
				OrderChartsVo vo = new OrderChartsVo();
				if (objs != null && objs.length > 0) {
					vo.setPurchasePlaceCh(objs[0].toString());
					vo.setTicketNum(objs[1].toString());
					vo.setProfit(objs[2].toString());
					voList.add(vo);
				}
			}
		}
		return voList;
	}

	@Override
	public void updateById(GjBuyOrderEntity gjBuyOrderEntity) throws Exception {
		factoryDao.getGjBuyOrderDao().updateById(gjBuyOrderEntity);
	}

	@Override
	public List<BuyOrderSummaryVo> queryOrderSummaryByPage(Page page, BuyOrderParamVo buyOrderParamVo) throws Exception {
		List<Object[]> list=factoryDao.getGjBuyOrderDao().queryOrderSummaryByPage(page, buyOrderParamVo);
		if(list!=null&&list.size()>0){
			List<BuyOrderSummaryVo> buyOrderSummaryVos=new ArrayList<BuyOrderSummaryVo>();
			for(Object[] objects:list){
				BuyOrderSummaryVo buyOrderSummaryVo=new BuyOrderSummaryVo();
				buyOrderSummaryVo.setId(objects[0].toString());
				if(objects[1]!=null){
					buyOrderSummaryVo.setPurchaseNo(objects[1].toString());
				}
				if(objects[2]!=null){
					buyOrderSummaryVo.setCreateTime(objects[2].toString());
				}
				if(objects[3]!=null){
					buyOrderSummaryVo.setPurchasePlace(objects[3].toString());
				}
				if(objects[4]!=null){
					buyOrderSummaryVo.setFlightType(objects[4].toString());
				}
				if(objects[5]!=null){
					buyOrderSummaryVo.setPnrCode(objects[5].toString());
				}
				if(objects[6]!=null){
					buyOrderSummaryVo.setPassengerCount(Integer.parseInt(objects[6].toString()));
				}
				if(objects[7]!=null){
					buyOrderSummaryVo.setPrintPrice(Double.parseDouble(objects[7].toString()));
				}
				if(objects[8]!=null){
					buyOrderSummaryVo.setCost(Double.parseDouble(objects[8].toString()));
				}
				if(objects[9]!=null){
					buyOrderSummaryVo.setPayPirce(Double.parseDouble(objects[9].toString()));
				}
				if(objects[10]!=null){
					buyOrderSummaryVo.setAllprice(Double.parseDouble(objects[10].toString()));
				}
				if(objects[11]!=null){
					buyOrderSummaryVo.setDistributor(objects[11].toString());
				}
				if(objects[12]!=null){
					buyOrderSummaryVo.setOrderNo(objects[12].toString());
				}
				if(objects[13]!=null){
					buyOrderSummaryVo.setShopName(objects[13].toString());
				}
				if(objects[14]!=null){
					buyOrderSummaryVo.setShopNameCh(objects[14].toString());
				}
				if(objects[15]!=null){
					buyOrderSummaryVo.setOldOrderNo(objects[15].toString());
				}
				if(objects[16]!=null){
					buyOrderSummaryVo.setPurchasePlaceCh(objects[16].toString());
				}
				if(objects[17]!=null){
					buyOrderSummaryVo.setLockUser(objects[17].toString());
				}
				if(objects[18]!=null){
					buyOrderSummaryVo.setSlfStatus(objects[18].toString());
				}
				if(objects[19]!=null){
					buyOrderSummaryVo.setFlightClass(objects[19].toString());
				}
				if(objects[20]!=null){
					buyOrderSummaryVo.setAddtime(objects[20].toString());
				}
				buyOrderSummaryVos.add(buyOrderSummaryVo);
			}
			return buyOrderSummaryVos;
		}else{
			return null;
		}
	}

	@Override
	public void alreadyPay(String orderId) throws Exception {
		GjBuyOrderEntity gjBuyOrderEntity=new GjBuyOrderEntity();
		gjBuyOrderEntity.setStatus(BBOOConstants.GjBuyOrderEntity_slfStatus_one);
		gjBuyOrderEntity.setSlfStatus(BBOOConstants.GjBuyOrderEntity_slfStatus_one);
		gjBuyOrderEntity.setId(orderId);
		factoryDao.getGjBuyOrderDao().updateById(gjBuyOrderEntity);
	}

	@Override
	public boolean createOrderByEtdz(String orderId, String baseOfficeId,String productPriceId)
			throws Exception {
		if(StringUtils.isNotEmpty(orderId)&&StringUtils.isNotEmpty(baseOfficeId)){
			GjSaleOrderEntity gjSaleOrderEntity=factoryDao.getSaleOrderDao().queryOrderByID(orderId);
			BaseOfficeEntity baseOfficeEntity=factoryDao.getIbaseOfficeDao().queryOfficeByID(Integer.parseInt(baseOfficeId));
			EtdzResult etdzResult=factoryService.getAllApiService().createOrderByEtdz(gjSaleOrderEntity, baseOfficeEntity);
			ProductPriceEntity productPriceEntity=factoryDao.getProductPriceDao().getProductPriceById(productPriceId);
			Integer baseAccountId=Integer.parseInt(productPriceEntity.getProduct().getAccountId());
			BaseAccountEntity baseAccountEntity=factoryDao.getBaseAccountDao().queryBaseAccountByID(baseAccountId);
			if(etdzResult!=null){
				if(BBOOConstants.EtdzResult_code_zero.equals(etdzResult.getCode())){
					GjBuyOrderEntity gjBuyOrderEntity=saveEtdzPurchaseInfo(baseAccountEntity,gjSaleOrderEntity,baseOfficeEntity,productPriceEntity,etdzResult);//保存etdz返回的采购信息，包括采购的价格
					MainTest.getProperty(gjBuyOrderEntity);
					try {
						//回填第三方票号，即使失败了也不抛异常出去（注：订单状态都已经改成出票完成了，不用判断是否所有乘机人票号都填完了，直接调第三方接口）
						BaseAccountEntity saleBaseAccountEntity=factoryDao.getBaseAccountDao().queryBaseAccountByID(gjSaleOrderEntity.getAccountid());
						factoryService.getAllApiService().modifyOrderByThirdInterface(gjSaleOrderEntity, gjBuyOrderEntity, saleBaseAccountEntity);
					} catch (Exception e) {
						e.printStackTrace();
					}
					return true;
				}else{
					throw new RuntimeException(etdzResult.getMsg());
				}
			}else{
				throw new RuntimeException("创单接口没有返回任何东西");
			}
		}
		return false;
	}

	private GjBuyOrderEntity saveEtdzPurchaseInfo(BaseAccountEntity baseAccountEntity,GjSaleOrderEntity gjSaleOrderEntity,
			BaseOfficeEntity baseOfficeEntity,ProductPriceEntity productPriceEntity,EtdzResult etdzResult) throws Exception {
		if(gjSaleOrderEntity!=null&&baseOfficeEntity!=null&&etdzResult!=null&&productPriceEntity!=null){
			GjBuyOrderEntity gjBuyOrderEntity = new GjBuyOrderEntity();
			BeanUtils.copyProperties(gjSaleOrderEntity, gjBuyOrderEntity, new String[] { "flights", "passengers","status"});
			Double saleAllPrice=0.0; //销售方的支付总价，这里的支付总价，是分销商给我们的，不是客人，分销商扣了我们的佣金。
			for(GjSalePassengerEntity tempPassenger:gjSaleOrderEntity.getPassengers()){
				saleAllPrice=MathUtil.sub(MathUtil.add(tempPassenger.getCost(),(MathUtil.add(tempPassenger.getTaxFee(), tempPassenger.getOilFee()))), tempPassenger.getCommission());
			}
			fillPurchaseInfo(baseAccountEntity, etdzResult, productPriceEntity, gjBuyOrderEntity, saleAllPrice);
			gjBuyOrderEntity.setSalePrice(gjSaleOrderEntity.getAllprice());// 销售总价等于卖方的订单总价（这一句一定要放到这个位置）
			gjBuyOrderEntity.setPayWay(null);//创单的时候置空
			gjBuyOrderEntity.setEtdzNo(baseOfficeEntity.getEtdzNum());
			gjBuyOrderEntity.setEtdzOffno(baseOfficeEntity.getOffice());
			gjBuyOrderEntity.setAddtime(DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
			gjBuyOrderEntity.setStatus(BBOOConstants.GjBuyOrderEntity_slfStatus_two);
			gjBuyOrderEntity.setSlfStatus(BBOOConstants.GjBuyOrderEntity_slfStatus_two);  //销售总价等于卖方的订单总价（这一句一定要放到这个位置)这个不能用来算利润，因为没减去佣金
			if (gjSaleOrderEntity.getFlights() != null && gjSaleOrderEntity.getFlights().size() > 0) {
				for (GjSaleFlightEntity gjSaleFlightEntity : gjSaleOrderEntity.getFlights()) {
					GjBuyFlightEntity gjBuyFlightEntity = new GjBuyFlightEntity();
					BeanUtils.copyProperties(gjSaleFlightEntity, gjBuyFlightEntity, new String[] { "order" });
					gjBuyFlightEntity.setOrder(gjBuyOrderEntity);
					// 保存航班信息。
					factoryDao.getGjBuyFlightDao().saveFlight(gjBuyFlightEntity);
				}
			}
			if (gjSaleOrderEntity.getPassengers() != null && gjSaleOrderEntity.getPassengers().size() > 0) {
				for (GjSalePassengerEntity gjSalePassengerEntity : gjSaleOrderEntity.getPassengers()) {
					GjBuyPassengerEntity gjBuyPassengerEntity = new GjBuyPassengerEntity();
					BeanUtils.copyProperties(gjSalePassengerEntity, gjBuyPassengerEntity, new String[] { "order" });
					 List<EtdzPassengerVO> etdzPassengerVOs=etdzResult.getPsgs();
					if(etdzPassengerVOs!=null&&etdzPassengerVOs.size()>0){
						for(EtdzPassengerVO etdzPassengerVO:etdzPassengerVOs){
							if(gjBuyPassengerEntity.getName().equals(etdzPassengerVO.getName())||gjBuyPassengerEntity.getCardNum().equals(etdzPassengerVO.getCertNo())){
								gjBuyPassengerEntity.setEticketNum(etdzPassengerVO.getNo());//直接这里就把票号填写了
							}
						}
					}
					gjBuyPassengerEntity.setOrder(gjBuyOrderEntity);
					gjBuyPassengerEntity.setPrintPrice(gjBuyOrderEntity.getPrintPrice());//票面价
					gjBuyPassengerEntity.setCost(gjBuyOrderEntity.getCost()); //机票支付款
					gjBuyPassengerEntity.setOilFee(new Double(0));
					gjBuyPassengerEntity.setTaxFee(gjBuyOrderEntity.getTaxFee());
					gjBuyPassengerEntity.setPayPirce(gjBuyOrderEntity.getPayPirce());//采购价
					factoryDao.getGjBuyPassengerDao().savePassenger(gjBuyPassengerEntity);
				}
			}
			GjSaleOrderEntity updateParamVo=new GjSaleOrderEntity();
			updateParamVo.setId(gjSaleOrderEntity.getId());
			updateParamVo.setStatus(BBOOConstants.GjSaleOrderEntity_SLFSTATUS_TWO);
			updateParamVo.setSlfStatus(BBOOConstants.GjSaleOrderEntity_SLFSTATUS_TWO);//采购方下单，分销方还是出票中
			factoryDao.getSaleOrderDao().updateByID(updateParamVo);
			return gjBuyOrderEntity;
		}
		return null;
	}
	
	private void fillPurchaseInfo(BaseAccountEntity baseAccountEntity,EtdzResult etdzResult,ProductPriceEntity productPriceEntity,GjBuyOrderEntity buyOrder,Double saleAllPrice) throws Exception{
		//设置account相关的信息
		if(baseAccountEntity!=null){
			buyOrder.setAccountid(baseAccountEntity.getId());
			buyOrder.setPurchaseAccount(baseAccountEntity.getUserName());
			buyOrder.setPurchasePlace(baseAccountEntity.getAcctype().substring(3));// CP_517NA(这里要将cp_截取之后插入数据库)
			buyOrder.setPurchasePlaceCh(baseAccountEntity.getDescribe());
		}
		//下面是从政策价格里面获取到的信息，填充到订单去
		if(productPriceEntity!=null){
			buyOrder.setProductSource(productPriceEntity.getProduct().getProductSource());
			buyOrder.setPolicyCode(productPriceEntity.getProduct().getId());
			buyOrder.setPolicyId(productPriceEntity.getId());
			buyOrder.setPriceType(productPriceEntity.getPriceType());
			buyOrder.setAfterPoint(productPriceEntity.getAfter()); //设置后返(从政策价格里面获取后返)
			buyOrder.setBackPoint(productPriceEntity.getRebates()); //设置返点
			buyOrder.setStickingPoint(0d);
			buyOrder.setReward(productPriceEntity.getFixedFee());
			buyOrder.setOilFee(new Double(0));//接口返回的没有燃油字段,附个默认值0
		}
		//用etdz返回的结果进行赋值
		if(etdzResult!=null){
			buyOrder.setCost(etdzResult.getFare());
			buyOrder.setPrintPrice(etdzResult.getFare()); // 票面价,设置默认值
			buyOrder.setTaxFee(etdzResult.getTax());
			buyOrder.setAllprice(MathUtil.mul(etdzResult.getFare(), etdzResult.getPsgs().size()));
			buyOrder.setPayPirce(etdzResult.getFare());
		}
		
		buyOrder.setLr(MathUtil.sub(saleAllPrice, buyOrder.getAllprice()));
	}
}
