package com.smart.oo.action.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.smart.comm.utils.StringUtilsc;
import com.smart.framework.base.BaseAction;
import com.smart.oo.from.OrderChartsVo;
import com.smart.oo.service.imp.FactoryServiceImpl;
import com.smart.timer.base.DateFormat;
import com.smart.utils.StringUtils;

public class GjOrderChartsAction extends BaseAction {

	private static final long serialVersionUID = 7045573912777553660L;
	@Autowired
	private FactoryServiceImpl factoryService;

		// 统计
		public void getStatisticCharts(){
			try {
				String startDate=request.getParameter("startDate");
				String endDate=request.getParameter("endDate");
				String chartType = request.getParameter("chartType");
				String type = request.getParameter("type");
				String flightClass = request.getParameter("flightClass");
				String showType = request.getParameter("showType");
				
				JSONObject jsonObject=new JSONObject();
				if (StringUtils.isEmpty(type)) {
					jsonObject.put(ERROR, "类型为空");
					this.writeStream(jsonObject, "utf-8");
					return;
				}
				
				String[] showTypeArr = null;
				if (StringUtils.isNotEmpty(showType)) {
					showTypeArr = showType.split(",");
				}
				
				boolean timeFlag = false;
				if (showTypeArr != null && isContain(showTypeArr, "day")) {
					timeFlag = true;
				}
				
				if ("createTime".equals(chartType) || timeFlag) {
					if (StringUtils.isNotEmpty(startDate) && StringUtils.isNotEmpty(endDate)) {
						if (!checkIntervalDate(startDate, endDate, 15)) {
							jsonObject.put(ERROR, "出票日期不能超过15天");
							this.writeStream(jsonObject, "utf-8");
							return;
						}
					} else {
						jsonObject.put(ERROR, "请输入出票日期");
						this.writeStream(jsonObject, "utf-8");
						return;
					}
				}
				
				OrderChartsVo vo = new OrderChartsVo();
				vo.setStartCreateDate(startDate);
				vo.setEndCreateDate(endDate);
				vo.setFlightClass(flightClass);
				
				if (StringUtils.isNotEmpty(chartType)) {
					List<OrderChartsVo> voList = getOrderChartList(vo, type, chartType ,showTypeArr);;
					double all = 0;
					if (showTypeArr != null && isContain(showTypeArr, "percent")) {
						all = getAllProfit(vo, type);
					}
					
					if (showTypeArr != null && isContain(showTypeArr, "day")) {
						if (voList != null && voList.size() > 0) {
							jsonObject.put("list", parseCtmToJSON(voList, all));
						} else {
							jsonObject.put("list", "[]");
						}
					} else {
						if (voList != null && voList.size() > 0) {
							jsonObject.put("list", parseToJSON(voList, all));
						} else {
							jsonObject.put("list", "[]");
						}
					}
					jsonObject.put("success", "查询成功");
					this.writeStream(jsonObject, "utf-8");
				} else {
					jsonObject.put(ERROR, "统计类型为空");
					this.writeStream(jsonObject, "utf-8");
				}
			} catch (Exception e) {
				e.printStackTrace();
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("error", "服务器出错了："+e.toString());
				this.writeStream(jsonObject, "utf-8");
			}
		}
		
		/**
		 * 获取总利润
		 * @param vo
		 * @param type
		 * @return
		 * @throws Exception
		 */
		private double getAllProfit(OrderChartsVo vo, String type) throws Exception {
			double all = 0;
			if ("ticketNum".equals(type)) {
				all = this.factoryService.getOrderStatisticService().getAllTicket(vo);
			} else if ("profit".equals(type)) {
				all = this.factoryService.getOrderStatisticService().getAllProfit(vo);
			} else if ("saleroom".equals(type)) {
				all = this.factoryService.getOrderStatisticService().getAllSaleroom(vo);
			}
			return all;
		}
		
		/**
		 * 获取数据
		 * @param vo
		 * @param type
		 * @param chartType
		 * @return
		 * @throws Exception
		 */
		private List<OrderChartsVo> getOrderChartList(OrderChartsVo vo, String type, String chartType, String[] showTypeArr) throws Exception {
			List<OrderChartsVo> voList = null;
			if (showTypeArr != null && isContain(showTypeArr, "day")) {
				if ("ticketNum".equals(type)) {
					voList = this.factoryService.getOrderStatisticService().statisticTicketChartsByCrt(vo, chartType);
				} else if ("profit".equals(type)) {
					voList = this.factoryService.getOrderStatisticService().statisticProfitChartsByCrt(vo, chartType);
				}
			} else {
				if ("ticketNum".equals(type)) {
					voList = this.factoryService.getOrderStatisticService().statisticTicketCharts(vo, chartType);
				} else if ("profit".equals(type)) {
					voList = this.factoryService.getOrderStatisticService().statisticProfitCharts(vo, chartType);
				}
			}
			return voList;
		}
		
		/**
		 * 验证间隔日期
		 * @param startDate
		 * @param endDate 
		 * @param intervalDate 间隔天数
		 * @return
		 */
		private boolean checkIntervalDate(String startDate, String endDate, int intervalDate) {
			long intervalTime = intervalDate * 24 * 60 * 60 * 1000;
			long startTime = DateFormat.getStringDate(startDate, "yyyy-MM-dd").getTime();
			long endTime = DateFormat.getStringDate(endDate, "yyyy-MM-dd").getTime();
			if ((endTime - startTime) > intervalTime) {
				return false;
			}
			return true;
		}
		
		/**
		 * 转换为JSON
		 * @param voList
		 * @param all
		 * @return
		 */
		private String parseToJSON(List<OrderChartsVo> voList, double all) {
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			int i = 0;
			for (OrderChartsVo vo : voList) {
				sb.append("{");
				if (StringUtils.isNotEmpty(vo.getCarrier())) {
					sb.append("\"categories\": \""+vo.getCarrier()+"\",");
				}
				if (StringUtils.isNotEmpty(vo.getPurchasePlaceCh())) {
					sb.append("\"categories\": \""+vo.getPurchasePlaceCh()+"\",");
				}
				if (StringUtils.isNotEmpty(vo.getOperator())) {
					sb.append("\"categories\": \""+vo.getOperator()+"\",");
				}
				if (StringUtils.isNotEmpty(vo.getAgeDes())) {
					sb.append("\"categories\": \""+vo.getAgeDes()+"\",");
				}
				if (StringUtils.isNotEmpty(vo.getDistributor())) {
					sb.append("\"categories\": \""+vo.getDistributor()+"\",");
				}
				if (StringUtils.isNotEmpty(vo.getShopName())) {
					sb.append("\"categories\": \""+vo.getShopName()+"\",");
				}
				if (StringUtils.isNotEmpty(vo.getCreateTime())) {
					sb.append("\"categories\": \""+vo.getCreateTime()+"\",");
				}
				if (all != 0) {
					if (StringUtils.isNotEmpty(vo.getTicketNum())) {
						sb.append("\"ticketNum\": \""+getPercentage(vo.getTicketNum(), all)+"\",");
					}
					if (StringUtils.isNotEmpty(vo.getProfit())) {
						sb.append("\"profit\": \""+getPercentage(vo.getProfit(), all)+"\",");
					}
					if (StringUtils.isNotEmpty(vo.getSaleroom())) {
						sb.append("\"saleroom\": \""+getPercentage(vo.getSaleroom(), all)+"\",");
					}
				} else {
					if (StringUtils.isNotEmpty(vo.getTicketNum())) {
						sb.append("\"ticketNum\": \""+vo.getTicketNum()+"\",");
					}
					if (StringUtils.isNotEmpty(vo.getProfit())) {
						sb.append("\"profit\": \""+StringUtilsc.formatDoubleStr(vo.getProfit())+"\",");
					}
					if (StringUtils.isNotEmpty(vo.getSaleroom())) {
						sb.append("\"saleroom\": \""+StringUtilsc.formatDoubleStr(vo.getSaleroom())+"\",");
					}
				}
				sb.append("}");
				if(i < voList.size() -1) {
					sb.append(",");
				}
				i++;
			}
			sb.append("]");
			return sb.toString();
		}
		
		/**
		 * 带日期转换为JSON
		 * @param voList
		 * @param all
		 * @return
		 */
		private String parseCtmToJSON(List<OrderChartsVo> voList, double all) {
			Map<String, Map<String,List<String>>> map = new HashMap<String, Map<String,List<String>>>();
			Set<String> key = null;
			for (OrderChartsVo vo : voList) {
				Map<String,List<String>> vMap = null;
				List<String> dataList = null;
				List<String> cateList = null;
				
				key = map.keySet();
				String name = getName(vo);
				for (String kStr : key) {
					if (kStr.equals(name)) {
						vMap = map.get(kStr);
						dataList = vMap.get("data");
						cateList = vMap.get("categories");
					}
				}
				if (vMap == null) {
					vMap = new HashMap<String, List<String>>();
					map.put(name, vMap);
					dataList = new ArrayList<String>();
					cateList = new ArrayList<String>();
				}
				
				if (StringUtils.isNotEmpty(vo.getCreateTime())) {
					cateList.add(vo.getCreateTime());
				}
				
				// 获取数据
				if (all != 0) {
					if (StringUtils.isNotEmpty(vo.getTicketNum())) {
						dataList.add(getPercentage(vo.getTicketNum(), all));
					}
					if (StringUtils.isNotEmpty(vo.getProfit())) {
						dataList.add(getPercentage(vo.getProfit(), all));
					}
					if (StringUtils.isNotEmpty(vo.getSaleroom())) {
						dataList.add(getPercentage(vo.getSaleroom(), all));
					}
				} else {
					if (StringUtils.isNotEmpty(vo.getTicketNum())) {
						dataList.add(vo.getTicketNum());
					}
					if (StringUtils.isNotEmpty(vo.getProfit())) {
						dataList.add(StringUtilsc.formatDoubleStr(vo.getProfit()));
					}
					if (StringUtils.isNotEmpty(vo.getSaleroom())) {
						dataList.add(StringUtilsc.formatDoubleStr(vo.getSaleroom()));
					}
				}
				
				vMap.put("data", dataList);
				vMap.put("categories", cateList);
				
			}
			String list = parseMapToJson(map);
			return list;
		}
		
		private String parseMapToJson(Map<String, Map<String,List<String>>> map) {
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			if (map != null && map.size() > 0) {
				Set<String> keySet = map.keySet();
				for (String key : keySet) {
					sb.append("{");
					sb.append("\"name\":\""+key+"\",");
					Map<String,List<String>> vMap = map.get(key);
					String content = JSON.toJSONString(vMap);
					sb.append("\"content\":"+content);
					sb.append("},");
				}
			}
			sb.delete(sb.length()-1, sb.length());
			sb.append("]");
			return sb.toString();
		}
		
		private String getName(OrderChartsVo vo) {
			if (StringUtils.isNotEmpty(vo.getCarrier())) {
				return vo.getCarrier();
			}
			if (StringUtils.isNotEmpty(vo.getPurchasePlaceCh())) {
				return vo.getPurchasePlaceCh();
			}
			if (StringUtils.isNotEmpty(vo.getOperator())) {
				return vo.getOperator();
			}
			if (StringUtils.isNotEmpty(vo.getAgeDes())) {
				return vo.getAgeDes();
			}
			if (StringUtils.isNotEmpty(vo.getDistributor())) {
				return vo.getDistributor();
			}
			if (StringUtils.isNotEmpty(vo.getShopName())) {
				return vo.getShopName();
			}
			return null;
		}
		
		/**
		 * 获取百分比
		 * @param str
		 * @param all
		 * @return
		 */
		private String getPercentage(String str, double all){
			Double d = Double.parseDouble(str);
			double percent = 0.0;
			if (all >0) {
				percent = d / all * 100;
			} else if (all < 0) {
				percent = 0 - d / all * 100;
			}
			return StringUtilsc.formatDoubleStr(percent);
		}
		
		private boolean isContain(String[] arr, String s) {
			for (String str : arr) {
				if (s.equals(str)) {
					return true;
				}
			}
			return false;
		}
}
