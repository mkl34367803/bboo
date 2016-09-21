package com.smart.oo.action.report;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.comm.utils.DownloadExcelUtils;
import com.smart.comm.utils.OOLogUtil;
import com.smart.framework.base.BaseAction;
import com.smart.oo.from.OrderReportVo;
import com.smart.oo.service.imp.FactoryServiceImpl;
import com.smart.timer.base.DateFormat;
import com.smart.utils.StringUtils;

@Results({ @Result(name = "queryorderdownload", location = "/jsp/build/reportorder/orderReport.jsp") })
public class OrderReportAction extends BaseAction {

	@Autowired
	private FactoryServiceImpl servicef;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1721098122395694425L;

	public String querySaleList() {

		return "queryorderdownload";
	}

	// 下载
	public void downloadExcek() {
		try {
			JSONObject jsonObject = new JSONObject();
			
			String createStart = request.getParameter("createStart");
			String createEnd = request.getParameter("createEnd");
			String ticketStart = request.getParameter("ticketStart");
			String ticketEnd = request.getParameter("ticketEnd");
			String departureStart = request.getParameter("departureStart");
			String departureEnd = request.getParameter("departureEnd");
			String distributor = request.getParameter("distributor");
			
			// 两天间隔时间
			long intervalTime = 4 * 24 * 60 * 60 * 1000;
			
			if (StringUtils.isNotEmpty(createStart) && StringUtils.isNotEmpty(createEnd)) {
				Date csDate = DateFormat.getStringDate(createStart, "yyyy-MM-dd");
				Date cdDate = DateFormat.getStringDate(createEnd, "yyyy-MM-dd");
				long csl = csDate.getTime();
				long cdl = cdDate.getTime();
				if ((cdl - csl) > intervalTime) {
					jsonObject.put("error", "销售日期时间间隔不能超过4天");
					this.writeStream(jsonObject, "utf-8");
					return ;
				}
			}
			
			if (StringUtils.isNotEmpty(ticketStart) && StringUtils.isNotEmpty(ticketEnd)) {
				Date tsData = DateFormat.getStringDate(ticketStart, "yyyy-MM-dd");
				Date tdDate = DateFormat.getStringDate(ticketEnd, "yyyy-MM-dd");
				long tsl = tsData.getTime();
				long tdl = tdDate.getTime();
				if ((tdl - tsl) > intervalTime) {
					jsonObject.put("error", "创单日期时间间隔不能超过2天");
					this.writeStream(jsonObject, "utf-8");
					return ;
				}
			}
			
			OrderReportVo vo = new OrderReportVo();
			vo.setCreateTime(createStart);
			vo.setTicketDate(ticketStart);
			vo.setDepartureDate(departureStart);
			vo.setDistributor(distributor);
			
			OrderReportVo voDate = new OrderReportVo();
			voDate.setCreateTime(createEnd);
			voDate.setTicketDate(ticketEnd);
			voDate.setDepartureDate(departureEnd);
			List<OrderReportVo> orderReportList = this.servicef.getOrderReportService().getSaleOrderList(vo, voDate);
			if (null != orderReportList && orderReportList.size() > 0) {
				//String file_name = DateFormat.getSysdateString("yyyyMMddHHmmss");
				String file_name = "orderReport";
				String filePath = DownloadExcelUtils.getFilePath("jsp/files/finance", file_name);
				DownloadExcelUtils.writeOrderReportToExcel(filePath, orderReportList);
				String zipFilePath = DownloadExcelUtils.writeEnd(filePath);
				zipFilePath = "jsp" + zipFilePath.split("jsp")[1];
				
				info(file_name+"报表下载成功");
				
				jsonObject.put("zipFilePath", zipFilePath);
			}else {
				jsonObject.put("error", "数据为空");
			}
			this.writeStream(jsonObject, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("error", e.toString());
			this.writeStream(jsonObject, "utf-8");
		}
	}
	
	
	private void info(String msg){
		OOLogUtil.info(msg, OrderReportAction.class, null);
	}

}
