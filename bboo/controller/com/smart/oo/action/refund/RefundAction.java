package com.smart.oo.action.refund;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.smart.comm.entity.BaseRefundEntity;
import com.smart.comm.entity.GjBuyOrderEntity;
import com.smart.comm.entity.GjSaleOrderEntity;
import com.smart.comm.entity.RefundEntity;
import com.smart.comm.utils.DownloadExcelUtils;
import com.smart.comm.utils.OOLogUtil;
import com.smart.comm.utils.POIExportUtils;
import com.smart.comm.utils.StringUtilsc;
import com.smart.entity.User;
import com.smart.framework.base.BaseAction;
import com.smart.framework.security.SecurityContext;
import com.smart.oo.from.RefundReportVo;
import com.smart.oo.from.RefundVo;
import com.smart.oo.service.imp.FactoryServiceImpl;
import com.smart.timer.base.DateFormat;
import com.smart.utils.StringUtils;
import com.smart.utils.UniqId;

@Results({ @Result(name = "refund", location = "/jsp/build/refound/refund.jsp"),
	@Result(name = "refundQuery", location = "/jsp/build/refound/refundQuery.jsp")})
public class RefundAction extends BaseAction {
	
	private static final long serialVersionUID = -4037758328936198150L;
	@Autowired
	private FactoryServiceImpl  factoryService;
	
	private final static String REFUND = "refund";
	private final static String RESERVE = "reserve";
	
	public void queryByPage() {
		
	}
	
	/**
	 * 通过BaseRefund查询
	 */
	public void queryByBaseRefund() {
		try {
			String fkid = request.getParameter("fkid");
			String state = request.getParameter("state");
			String orderStatus = request.getParameter("orderStatus");
			JSONObject result = new JSONObject();
			if (StringUtils.isNotEmpty(fkid)) {
				BaseRefundEntity br = new BaseRefundEntity();
				br.setFkid(fkid);
				if (StringUtils.isNotEmpty(state)) {
					br.setState(Integer.parseInt(state));
				}
				br.setOrderStatus(orderStatus);
				List<RefundEntity> refundEntities = this.factoryService.getRefundService().queryByBaseRefund(br);
				if (null != refundEntities && refundEntities.size() > 0) {
					String list = JSON.toJSONString(refundEntities);
					result.put("list", list);
				}
				result.put(SUCCESS, "查询成功");
			}
			this.writeStream(result, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject result = new JSONObject();
			result.put(ERROR, e.toString());
			this.writeStream(result, "utf-8");
		}
	}
	
	/**
	 * 保存
	 */
	public void save() {
		try {
			String psgids = request.getParameter("psgids");
			String fltids = request.getParameter("fltids");
			
			String baserefundId = request.getParameter("brid");
			
			RefundEntity refundEntity = getRefundData();
			BaseRefundEntity baseRefundEntity = getBaseRefundData();
			
			JSONObject result = new JSONObject();
			if (psgids == null || fltids == null) {
				result.put(ERROR, "航班、乘客id为空");
				this.writeStream(result, "utf-8");
				return;
			}
			
			String [] psgidArr = psgids.split(",");
			String[] fltidsArr = fltids.split(",");
			
			String bfid = UniqId.getInstance().getStrId();
			baseRefundEntity.setId(bfid);
			baseRefundEntity.setCreateTime(DateFormat.getStandardSysdate());
			long pcount = psgidArr.length;
			baseRefundEntity.setPcount(pcount);
			
			refundEntity.setFkrid(bfid);
			Set<RefundEntity> refundList = new HashSet<RefundEntity>();
			RefundEntity entity = null;
			for (String fltid : fltidsArr) {
				for (String psgid : psgidArr) {
					entity = new RefundEntity();
					copyEntity(entity, refundEntity);
					entity.setFltid(fltid);
					entity.setPsgid(psgid);
					entity.setId(UniqId.getInstance().getStrId());
					entity.setCreateTime(DateFormat.getStandardSysdate());
					refundList.add(entity);
				}
			}
			this.factoryService.getBaseRefundService().saveRefundAndBaseRefund(baseRefundEntity, refundList, baserefundId);
			result.put(SUCCESS, "退票成功");
			info("退票信息保存成功");
			this.writeStream(result, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject result = new JSONObject();
			result.put(ERROR, e.toString());
			this.writeStream(result, "utf-8");
		}
	}
	
	// 获取退票数据
	private RefundEntity getRefundData() throws Exception {
		
		String face = request.getParameter("face");
		String baseFace = request.getParameter("baseFace");
		String yq = request.getParameter("yq");
		String tax = request.getParameter("tax");
		String rate = request.getParameter("rate");
		String fee = request.getParameter("fee");
		String refund = request.getParameter("refund");
		String actRefund = request.getParameter("actRefund");
		//String verifyAmount = request.getParameter("verifyAmount");
		String rtype = request.getParameter("rtype");
		String isvoid = request.getParameter("isvoid");
		//String issubmit = request.getParameter("issubmit");
		
		String face2 = request.getParameter("face2");
		String baseFace2 = request.getParameter("baseFace2");
		String yq2 = request.getParameter("yq2");
		String tax2 = request.getParameter("tax2");
		String rate2 = request.getParameter("rate2");
		String fee2 = request.getParameter("fee2");
		String refund2 = request.getParameter("refund2");
		String actRefund2 = request.getParameter("actRefund2");
		//String verifyAmount2 = request.getParameter("verifyAmount2");
		String rtype2 = request.getParameter("rtype2");
		String isvoid2 = request.getParameter("isvoid2");
		//String issubmit2 = request.getParameter("issubmit2");

		//String islock = request.getParameter("islock");
		
		RefundEntity entity = new RefundEntity();
		
		entity.setFace(Double.parseDouble(face));
		entity.setBaseFace(Double.parseDouble(baseFace));
		entity.setYq(Double.parseDouble(yq));
		entity.setTax(Double.parseDouble(tax));
		entity.setRate(Double.parseDouble(rate));
		entity.setFee(Double.parseDouble(fee));
		entity.setRefund(Double.parseDouble(refund));
		entity.setActRefund(Double.parseDouble(actRefund));
		//entity.setVerifyAmount(Double.parseDouble(verifyAmount));
		entity.setRtype(Integer.parseInt(rtype));
		entity.setIsvoid(Integer.parseInt(isvoid));
		//entity.setIssubmit(Integer.parseInt(issubmit));
		
		entity.setFace2(Double.parseDouble(face2));
		entity.setBaseFace2(Double.parseDouble(baseFace2));
		entity.setYq2(Double.parseDouble(yq2));
		entity.setTax2(Double.parseDouble(tax2));
		entity.setRate2(Double.parseDouble(rate2));
		entity.setFee2(Double.parseDouble(fee2));
		entity.setRefund2(Double.parseDouble(refund2));
		entity.setActRefund2(Double.parseDouble(actRefund2));
		//entity.setVerifyAmount2(Double.parseDouble(verifyAmount2));
		entity.setRtype2(Integer.parseInt(rtype2));
		entity.setIsvoid2(Integer.parseInt(isvoid2));
		//entity.setIssubmit2(Integer.parseInt(issubmit2));
		
		//entity.setIslock(Integer.parseInt(islock));
		
		return entity;
	}
	
	// 获取BaseRefund数据
	private BaseRefundEntity getBaseRefundData() throws Exception {
		String fkid = request.getParameter("fkid");
		String state = request.getParameter("state");
		String orderStatus = request.getParameter("orderStatus");
		
		BaseRefundEntity entity = new BaseRefundEntity();
		entity.setFkid(fkid);
		entity.setState(Integer.parseInt(state));
		entity.setOrderStatus(orderStatus);
		
		User user = SecurityContext.getUser();
		String operator = user.getName();
		entity.setOperator(operator);
		if ("2".equals(state)) {
			entity.setLocker(user.getName());
		}
		return entity;
	}
	
	/**
	 * 查询退票订单
	 * 
	 * @return
	 */
	public String queryOrderByID() {
		request.setAttribute("operator", "refund");
		try {
			String id = request.getParameter("id");
			if (StringUtilsc.isNotEmptyAndNULL(id)) {
				GjSaleOrderEntity gjSaleOrderEntity = this.factoryService.getSaleOrderService().queryOrderByID(id);
				if (null != gjSaleOrderEntity) {
					request.setAttribute("orderDetail", gjSaleOrderEntity);
					GjBuyOrderEntity gjBuyOrderEntity=this.factoryService.getGjBuyOrderService().queyOrderByID(id);
					if (null == gjBuyOrderEntity) {
						request.setAttribute(ERROR, "该订单采购表数据不存在");
						return "refundQuery";
					}
					request.setAttribute("buyOrderEntity", gjBuyOrderEntity);
					return "refund";
				} else {
					request.setAttribute("error", "销售表数据不存在");
				}
			} else {
				request.setAttribute("error", "id参数为空");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute(ERROR, e.toString());
		}
		return "refundQuery";
	}
	
	/**
	 * 复制Refund数据
	 * @param entity
	 * @param refundEntity
	 */
	public void copyEntity(RefundEntity entity, RefundEntity refundEntity) {
		entity.setFkrid(refundEntity.getFkrid());
		
		entity.setFace(refundEntity.getFace());
		entity.setBaseFace(refundEntity.getBaseFace());
		entity.setYq(refundEntity.getYq());
		entity.setTax(refundEntity.getTax());
		entity.setRate(refundEntity.getRate());
		entity.setFee(refundEntity.getFee());
		entity.setRefund(refundEntity.getRefund());
		entity.setActRefund(refundEntity.getActRefund());
		entity.setVerifyAmount(refundEntity.getVerifyAmount());
		entity.setRtype(refundEntity.getRtype());
		entity.setIsvoid(refundEntity.getIsvoid());
		entity.setIssubmit(refundEntity.getIssubmit());
		
		entity.setFace2(refundEntity.getFace2());
		entity.setBaseFace2(refundEntity.getBaseFace2());
		entity.setYq2(refundEntity.getYq2());
		entity.setTax2(refundEntity.getTax2());
		entity.setRate2(refundEntity.getRate2());
		entity.setFee2(refundEntity.getFee2());
		entity.setRefund2(refundEntity.getRefund2());
		entity.setActRefund2(refundEntity.getActRefund2());
		entity.setVerifyAmount2(refundEntity.getVerifyAmount2());
		entity.setRtype2(refundEntity.getRtype2());
		entity.setIsvoid2(refundEntity.getIsvoid2());
		entity.setIssubmit2(refundEntity.getIssubmit2());
		
		entity.setIslock(refundEntity.getIslock());
	}
	
	/**
	 * 下载退票信息
	 */
	public void downloadRefund() {
		try {
			String operate = request.getParameter("operate");
			
			String refundStart = request.getParameter("refundStart");
			String refundEnd = request.getParameter("refundEnd");
			String departureStart = request.getParameter("departureStart");
			String departureEnd = request.getParameter("departureEnd");
			String distributor = request.getParameter("distributor");
			
			JSONObject jsonObject = new JSONObject();
			
			
			if (StringUtils.isNotEmpty(operate)) {
				RefundVo vo = new RefundVo();
				long time = 7 * 24 * 60 * 60 * 1000;
				if (StringUtils.isNotEmpty(refundStart) && StringUtils.isNotEmpty(refundEnd)) {
					long rs = DateFormat.getStringDate(refundStart, "yyyy-MM-dd").getTime();
					long re = DateFormat.getStringDate(refundEnd, "yyyy-MM-dd").getTime();
					if ((re - rs) > time) {
						jsonObject.put(ERROR, "退票日期间隔时间不能超过7天");
						this.writeStream(jsonObject, "utf-8");
						return;
					}
					vo.setStartRefundDate(refundStart);
					vo.setEndRefundData(refundEnd);
				}
				if (StringUtils.isNotEmpty(departureStart) && StringUtils.isNotEmpty(departureEnd)) {
					long ds = DateFormat.getStringDate(departureStart, "yyyy-MM-dd").getTime();
					long de = DateFormat.getStringDate(departureEnd, "yyyy-MM-dd").getTime();
					if ((de - ds) > time) {
						jsonObject.put(ERROR, "起飞日期间隔时间不能超过7天");
						this.writeStream(jsonObject, "utf-8");
						return;
					}
					vo.setStartDepartureDate(departureStart);
					vo.setEndDepartureDate(departureEnd);
				}
				vo.setDistributor(distributor);
				
				String file_name = "";
				if (REFUND.equals(operate)) {
					file_name = "refundReport";
					vo.setState(1);
				} else if (RESERVE.equals(operate)) {
					file_name = "reserveReport";
					vo.setState(2);
				}
				
				List<RefundReportVo> refundVoList = this.factoryService.getRefundService().downloadRefund(vo);
				
				if (refundVoList != null && refundVoList.size() > 0) {
					String filePath = DownloadExcelUtils.getFilePath("jsp/files/finance", file_name);
					
					POIExportUtils.writeRefundToExcel(filePath, refundVoList, operate);
					
					String zipFilePath = DownloadExcelUtils.writeEnd(filePath);
					zipFilePath = "jsp" + zipFilePath.split("jsp")[1];
					
					info(file_name+"下载成功");
					
					jsonObject.put("zipFilePath", zipFilePath);
				} else {
					jsonObject.put(ERROR, "数据为空");
				}
				this.writeStream(jsonObject, "utf-8");
			} else {
				jsonObject.put(ERROR, "operate不能为空");
				this.writeStream(jsonObject, "utf-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put(ERROR, e.toString());
			this.writeStream(jsonObject, "utf-8");
		}
	}
	
	private void info(String msg){
		OOLogUtil.info(msg, RefundAction.class, null);
	}

}
