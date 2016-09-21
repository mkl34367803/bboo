package com.smart.oo.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.smart.oo.dao.imp.FactoryDaoImpl;
import com.smart.oo.from.OrderReportVo;
import com.smart.oo.service.IOrderReportService;
@Repository("BBOOOrderReportServiceImpl")
public class OrderReportServiceImpl implements IOrderReportService {
	 
	
 	@Autowired
	private FactoryDaoImpl daoFactory;
	
	@Override
	public List<OrderReportVo> getSaleOrderList(OrderReportVo from, OrderReportVo voDate) throws Exception {
		List<Object[]> objList = this.daoFactory.getOrderReportDao().getSaleOrderList(from, voDate);
		List<OrderReportVo> list = null;
		if (objList != null && objList.size() > 0) {
			list = parseToOrderReports(objList);
		}
		return list;
	}
	
	// 解析报表
	private List<OrderReportVo> parseToOrderReports(List<Object[]> list) {
		List<OrderReportVo> orderReports = new ArrayList<OrderReportVo>();
		OrderReportVo vo = null;
		for (Object[] obj : list) {
			vo = new OrderReportVo();
			// GjBuyOrderEntity
			int i = 1;
			vo.setExtOrderID(parseString(obj[i++]));
			vo.setPurchaseNo(parseString(obj[i++]));
			vo.setOldOrderNo(parseString(obj[i++]));
			vo.setFlightType(parseString(obj[i++]));
			vo.setAirlineCount(parseInteger(obj[i++]));
			vo.setPassengerCount(parseInteger(obj[i++]));
			vo.setBoBxFee(parseDouble(obj[i++]));
			vo.setBoBxCount(parseInteger(obj[i++]));
			vo.setBoPnrCode(parseString(obj[i++]));
			vo.setBoPnrCodeBig((parseString(obj[i++])));//10
			vo.setPayWay(parseString(obj[i++]));
			vo.setTransactionNo(parseString(obj[i++]));
			vo.setCreateTime(parseString(obj[i++]));
			vo.setTicketDate(parseString(obj[i++]));
			vo.setFlightClass(parseString(obj[i++]));
			vo.setPurchasePlace(parseString(obj[i++]));
			vo.setPurchasePlaceCh(parseString(obj[i++]));
			vo.setSupplier(parseString(obj[i++]));
			vo.setCabinDes(parseString(obj[i++]));
			vo.setBackPoint(parseDouble(obj[i++]));
			vo.setAfterPoint(parseDouble(obj[i++]));
			vo.setStickingPoint(parseDouble(obj[i++]));
			vo.setReward(parseDouble(obj[i++]));
			vo.setSalePrice(parseDouble(obj[i++]));
			vo.setLr(parseDouble(obj[i++]));
			vo.setBoAllprice(parseDouble(obj[i++]));
			vo.setSlfRemark(parseString(obj[i++]));
			vo.setBoMerchantNo(parseString(obj[i++]));
			vo.setStatus(parseString(obj[i++]));//30
			vo.setSlfStatus(parseString(obj[i++]));
			
			// GjBuyPassengerEntity
			vo.setBpid(parseString(obj[i++]));
			vo.setName(parseString(obj[i++]));
			vo.setGender(parseString(obj[i++]));
			vo.setAgeType(parseInteger(obj[i++]));
			vo.setAgeDes(parseString(obj[i++]));
			vo.setEticketNum(parseString(obj[i++]));
			vo.setBpPrintPrice(parseDouble(obj[i++]));
			vo.setBpOilFee(parseDouble(obj[i++]));
			vo.setBpTaxFee(parseDouble(obj[i++]));//40
			vo.setBpPayPirce(parseDouble(obj[i++]));
			vo.setBpCost(parseDouble(obj[i++]));//42
			
			// GjSaleOrderEntity
			vo.setExtOid(parseString(obj[i++]));
			vo.setOrderNo(parseString(obj[i++]));
			vo.setPolicyType(parseInteger(obj[i++]));//20
			vo.setAdultPrice(parseDouble(obj[i++]));
			vo.setAdultTax(parseDouble(obj[i++]));
			vo.setChildPrice(parseDouble(obj[i++]));
			vo.setChildTax(parseDouble(obj[i++]));
			vo.setSoBxFee(parseDouble(obj[i++]));
			vo.setSoPnrCode(parseString(obj[i++]));//50
			vo.setSoPnrCodeBig(parseString(obj[i++]));
			vo.setBillId(parseString(obj[i++]));
			vo.setShopName(parseString(obj[i++]));
			vo.setShopNameCh(parseString(obj[i++]));
			vo.setDistributor(parseString(obj[i++]));
			vo.setDistributorCh(parseString(obj[i++]));
			vo.setRtOffno(parseString(obj[i++]));
			vo.setOpenStatus(parseInteger(obj[i++]));
			vo.setOpenDes(parseString(obj[i++]));
			vo.setBuyPrice(parseDouble(obj[i++]));//60
			vo.setPolicyId(parseString(obj[i++]));
			vo.setPolicyCode(parseString(obj[i++]));
			vo.setProductSource(parseString(obj[i++]));
			vo.setOperator(parseString(obj[i++]));
			vo.setOldPnrCode(parseString(obj[i++]));
			vo.setOldAllprice(parseDouble(obj[i++]));
			vo.setSoAllprice(parseDouble(obj[i++]));
			vo.setAccountInfo(parseString(obj[i++]));
			vo.setMno(parseString(obj[i++]));
			vo.setJourneySheetPrice(parseString(obj[i++]));//70
			vo.setLockUser(parseString(obj[i++]));
			vo.setStatement(parseString(obj[i++]));//72
			
			// GjSalePassengerEntity
			vo.setBxCount(parseInteger(obj[i++]));//73
			vo.setInsNo(parseString(obj[i++]));
			vo.setSpCost(parseDouble(obj[i++]));
			vo.setSpOilFee(parseDouble(obj[i++]));
			vo.setSpTaxFee(parseDouble(obj[i++]));
			vo.setPrice(parseDouble(obj[i++]));
			vo.setCommission(parseDouble(obj[i++]));
			vo.setSpPrintPrice(parseDouble(obj[i++]));//80
			vo.setSpPrice(parseDouble(obj[i++]));
			
			// GjSaleFlightEntity
			vo.setSequence(parseInteger(obj[i++]));//82
			vo.setCarrier(parseString(obj[i++]));
			vo.setAirCodeCh(parseString(obj[i++]));
			vo.setFlightNum(parseString(obj[i++]));
			vo.setCabin(parseString(obj[i++]));
			vo.setDepAircode(parseString(obj[i++]));
			vo.setDepAircodeCh(parseString(obj[i++]));
			vo.setArrAircode(parseString(obj[i++]));
			vo.setArrAircodeCh(parseString(obj[i++]));//90
			vo.setDepartureDate(parseString(obj[i++]));
			vo.setDepartureTime(parseString(obj[i++]));
			vo.setArrivalDate(parseString(obj[i++]));
			vo.setArrivalTime(parseString(obj[i++]));
			vo.setSegmentType(parseInteger(obj[i++]));
			vo.setAddTime(parseString(obj[i++]));
			vo.setSelfPrintPrice(parseDouble(obj[i++]));//97
			
			orderReports.add(vo);
		}
		return orderReports;
	}
	
	private Double parseDouble(Object obj) {
		if (obj != null) {
			return Double.parseDouble(obj.toString());
		}
		return 0.0;
	}
	private Integer parseInteger(Object obj) {
		if (obj != null) {
			return Integer.parseInt(obj.toString());
		}
		return 0;
	}
	private String parseString(Object obj) {
		return obj != null ? obj.toString() : "";
	}

}
