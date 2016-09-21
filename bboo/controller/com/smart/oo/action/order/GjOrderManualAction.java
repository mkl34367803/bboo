package com.smart.oo.action.order;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.comm.entity.GjBuyOrderEntity;
import com.smart.comm.entity.GjSaleFlightEntity;
import com.smart.comm.entity.GjSaleOrderEntity;
import com.smart.comm.entity.GjSalePassengerEntity;
import com.smart.comm.utils.StringUtilsc;
import com.smart.framework.base.BaseAction;
import com.smart.oo.dao.IGjSaleFlightDao;
import com.smart.oo.dao.IGjSaleOrderDao;
import com.smart.oo.dao.IGjSalePassengerDao;
import com.smart.oo.service.imp.FactoryServiceImpl;
import com.smart.utils.StringUtils;
import com.smart.utils.UniqId;

@Results({ @Result(name = "insertorder", location = "/jsp/build/manualadd/orderadd.jsp"), 
		   @Result(name="queryorder",location="/jsp/build/manualadd/text2.jsp")
})
		   
public class GjOrderManualAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private GjSaleOrderEntity manuaorder;
	
	@Autowired
	 private FactoryServiceImpl factoryService;
	
	
/**
 * 查询
 * @return
 */
	/*public String showOrderDetail() {
		try {
			String id = request.getParameter("id");
			if (StringUtilsc.isNotEmpty(id)) {
				GjSaleOrderEntity orderDetail = this.factoryService.getSaleOrderService().queryOrderByID(id);
				
					GjBuyOrderEntity buyOrderEntity = this.factoryService.getGjBuyOrderService().queyOrderByID(id);
					
						request.setAttribute("buyOrderEntity", buyOrderEntity);
						
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "orderDetail";
	}
	*/
	public String  queryorder(){
		String id = request.getParameter("id");
			 try {
				manuaorder = this.factoryService.getSaleOrderService().queryOrderByID(id);
				
				request.setAttribute("manuaorder", manuaorder);
			 } catch (Exception e) {
				
				e.printStackTrace();
			}
		return "queryorder";
	}

	public String insertorder() {
		try {
			GjSaleOrderEntity orderentity = new GjSaleOrderEntity();
			GjSalePassengerEntity passentity = new GjSalePassengerEntity();
			GjSaleFlightEntity flight = new GjSaleFlightEntity();
			entity(orderentity);
			entity(passentity);
			entity(flight);
			orderentity.setId(UniqId.getInstance().getStrId());
			passentity.setId(UniqId.getInstance().getStrId());
			passentity.setFkid(orderentity.getId());
			flight.setId(UniqId.getInstance().getStrId());
			flight.setFkid(orderentity.getId());

			Set<GjSaleFlightEntity> flightSet = new HashSet<GjSaleFlightEntity>();
			flightSet.add(flight);
			orderentity.setFlights(flightSet);
			
			Set<GjSalePassengerEntity> passengerSet = new HashSet<GjSalePassengerEntity>();
			passengerSet.add(passentity);
			orderentity.setPassengers(passengerSet);
			this.factoryService.getSaleOrderService().saveorder(orderentity);

			
			response.getWriter().write("SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "insertorder";
	}

	private void entity(GjSaleOrderEntity orderentity) {
		
		String extOrderID = request.getParameter("extOrderID");// B2C网站入库号
		String orderNo = request.getParameter("orderNo");// 订单号:
		String pnrCode = request.getParameter("pnrCode");// *PNR:
		String pnrCodeBig = request.getParameter("pnrCodeBig");// PNR 大编
		String status = request.getParameter("status");// 订单状态
		String flightType = request.getParameter("flightType");// 航程类型：
		Integer urgency = Integer.valueOf(request.getParameter("urgency"));// 出票紧急度
		String seats = request.getParameter("seats");// 航班剩余座位
		Integer childrenCount = Integer.valueOf(request.getParameter("childrenCount"));// 儿童数目
	    Integer adultsCount = Integer.valueOf(request.getParameter("adultsCount"));// 成人数目
		String oldId = request.getParameter("oldId");// 订单分离
		String source = request.getParameter("source");// 订单来源
		String lockUser = request.getParameter("lockUser");// *锁定操作人
		
		String adultPriceStr = request.getParameter("adultPrice");
		double adultPrice = strParseDouble(adultPriceStr);
		
		String childPriceStr = request.getParameter("childPrice");
		if(StringUtils.isNotEmpty(childPriceStr));
		double childPrice = strParseDouble(childPriceStr);
		String adultTaxStr = request.getParameter("adultTax");
		double adultTax = strParseDouble(adultTaxStr);
		String childTaxStr = request.getParameter("childTax");
		double childTax = strParseDouble(childTaxStr);
		String allpriceStr = request.getParameter("allprice");
		System.out.println(allpriceStr+"********");
		double allprice = strParseDouble(allpriceStr);
		System.out.println(allprice+"==========");
		String pnrOffNo = request.getParameter("pnrOffNo");// PNR 预定的OFFICE号
		String rtOffno = request.getParameter("rtOffno");// 提取PNR 的OFFICE号
		String pnrText = request.getParameter("pnrText");// PNR文本
		Integer policyType = Integer.valueOf(request.getParameter("policyType"));// 政策类型
		String productSource = request.getParameter("productSource");// 政策来源
		String policyId = request.getParameter("policyId");// 销售政策ID
		String policyCode = request.getParameter("policyCode");// 销售政策code
		
		String contactName = request.getParameter("contactName");// 联系人姓名
		String contactMob = request.getParameter("contactMob");// 联系人手机
		String contactTel = request.getParameter("contactTel");// 联系人电话
		String contactEmail = request.getParameter("contactEmail");// 联系人邮箱
		String receiver = request.getParameter("receiver");// 收件人
		String postcode = request.getParameter("postcode");// 邮政编码
		String address = request.getParameter("address");// 投递地址
		String returnTicketRule = request.getParameter("returnTicketRule");// 退票规则
		String changeDateRule = request.getParameter("changeDateRule");// 改期规则
		String refundCause = request.getParameter("refundCause");// 退款原因
		String statement = request.getParameter("statement");// 其他说明
		
		orderentity.setStatement(statement);
		orderentity.setRefundCause(refundCause);
		orderentity.setChangeDateRule(changeDateRule);
		orderentity.setReturnTicketRule(returnTicketRule);
		orderentity.setAddress(address);
		orderentity.setPostcode(postcode);
		orderentity.setReceiver(receiver);
		orderentity.setContactEmail(contactEmail);
		orderentity.setContactTel(contactTel);
		orderentity.setContactMob(contactMob);
		orderentity.setContactName(contactName);
		
		orderentity.setPolicyCode(policyCode);
		orderentity.setPolicyId(policyId);
		orderentity.setSource(source);
		orderentity.setProductSource(productSource);
		orderentity.setPolicyType(policyType);
		orderentity.setPnrText(pnrText);
		orderentity.setRtOffno(rtOffno);
		orderentity.setPnrOffNo(pnrOffNo);
		orderentity.setExtOrderID(extOrderID);
		orderentity.setOrderNo(orderNo);
		orderentity.setPnrCode(pnrCode);
		orderentity.setPnrCodeBig(pnrCodeBig);
		orderentity.setStatus(status);
		orderentity.setFlightType(flightType);
		orderentity.setUrgency(urgency);
		if(seats!=null){
			orderentity.setSeats(Integer.parseInt(seats));
		}
		orderentity.setChildrenCount(childrenCount);
		orderentity.setAdultsCount(adultsCount);
		orderentity.setOldId(oldId);
		orderentity.setLockUser(lockUser);
		orderentity.setAdultPrice(adultPrice);
		orderentity.setChildPrice(childPrice);
		orderentity.setAdultTax(adultTax);
		orderentity.setChildTax(childTax);
		orderentity.setAllprice(allprice);
		
	}

	
	private void entity(GjSalePassengerEntity passentity) {
		Integer pindex = Integer.valueOf(request.getParameter("pindex"));//序号
		String name = request.getParameter("name");// 姓名
		String cardNum = request.getParameter("cardNum");// 证件号码
		String birthday = request.getParameter("birthday");// 生日
		Integer ageType = Integer.valueOf(request.getParameter("ageType"));// 类型
		String ageDes = request.getParameter("ageDes");// 描述 
		String gender = request.getParameter("gender");// 性别
		String cardType = request.getParameter("cardType");// 证件类型
		String cardIssuePlace = request.getParameter("cardIssuePlace");// 签发地
		String nationality = request.getParameter("nationality");// 国籍
		String cardExpired = request.getParameter("cardExpired");// 证件有效期
		String bxCountStr = request.getParameter("bxCount");// 保险
		Integer bxCount = null;
		if(StringUtilsc.isNotEmptyAndNULL(bxCountStr)){
			bxCount = Integer.parseInt(bxCountStr);
		}
		String eticketNum = request.getParameter("eticketNum");//票号
		String costStr = request.getParameter("cost");
		double cost = strParseDouble(costStr);//销售价
		String Strprice =request.getParameter("price");
		double price = strParseDouble(Strprice);//票面价
		
		
		passentity.setPindex(pindex);
		passentity.setName(name);
		passentity.setGender(gender);
	    passentity.setAgeType(ageType);
		passentity.setAgeDes(ageDes);
		passentity.setBirthday(birthday);
		passentity.setCardType(cardType);
		passentity.setNationality(nationality);
		passentity.setCardNum(cardNum);
		passentity.setCardExpired(cardExpired);
		passentity.setCardIssuePlace(cardIssuePlace);
		passentity.setBxCount(bxCount);
		passentity.setEticketNum(eticketNum);
		passentity.setCost(cost);
		passentity.setPrice(price);

	}

	
	private void entity(GjSaleFlightEntity flight) {
		Integer sequence = Integer.valueOf(request.getParameter("sequence"));// 序号
		String flightNum = request.getParameter("flightNum");// 航班号
		String carrier = request.getParameter("carrier");// 承运
		String isShared = request.getParameter("isShared");// 共享航班
		String cabin = request.getParameter("cabin");// 舱位
		String depAircode = request.getParameter("depAircode");// 出发机场三字码
		String arrAircode = request.getParameter("arrAircode");// 到达机场三字码
		String departureDate = request.getParameter("departureDate");// 出发日期
		String arrivalDate = request.getParameter("arrivalDate");// 到达日期
		String departureTime = request.getParameter("departureTime");// 起飞时间
		String arrivalTime = request.getParameter("arrivalTime");// 到达时间
		Integer segmentType = Integer.valueOf(request.getParameter("segmentType"));// 航程类型
		
		flight.setIsShared(isShared);
		flight.setSequence(sequence);
		flight.setCarrier(carrier);
		flight.setFlightNum(flightNum);
		flight.setCabin(cabin);
		flight.setDepAircode(depAircode);
		flight.setArrAircode(arrAircode);
		flight.setDepartureDate(departureDate);
		flight.setDepartureTime(departureTime);
		flight.setArrivalDate(arrivalDate);
		flight.setArrivalTime(arrivalTime);
		flight.setSegmentType(segmentType);

	}
	
	private double strParseDouble(String doubleStr){
		double d = 0;
		if(StringUtilsc.isNotEmptyAndNULL(doubleStr)){
			d = Double.parseDouble(doubleStr);
		}
		return d;
	}

}
