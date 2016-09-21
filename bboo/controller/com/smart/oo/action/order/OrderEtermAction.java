package com.smart.oo.action.order;

import java.text.ParseException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.sf.json.JSONObject;

import org.bouncycastle.jce.provider.JDKKeyFactory.RSA;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.JsonObject;
import com.smart.comm.entity.BaseAccountEntity;
import com.smart.comm.entity.BaseOfficeEntity;
import com.smart.comm.entity.GjSaleFlightEntity;
import com.smart.comm.entity.GjSaleOrderEntity;
import com.smart.comm.entity.GjSalePassengerEntity;
import com.smart.comm.entity.KeyValEntity;
import com.smart.comm.utils.BBOOConstants;
import com.smart.framework.base.BaseAction;
import com.smart.oo.domain.GDSDomain;
import com.smart.oo.domain.res.GDSResult;
import com.smart.oo.service.api.AllApi;
import com.smart.oo.service.imp.FactoryServiceImpl;
import com.smart.utils.DateUtils;
import com.smart.utils.StringUtils;

public class OrderEtermAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private FactoryServiceImpl factoryService;

	public void getEtermInfo() {
		try {
			//id表示订单表中的id
			String id = request.getParameter("id");
			//passenger表里面的id字段
			String passengerId = request.getParameter("passengerId");
			GjSaleOrderEntity gjSaleOrderEntity = factoryService.getSaleOrderService().queryOrderByID(id);
			if (gjSaleOrderEntity != null) {
				BaseAccountEntity baseAccountEntity = factoryService.getBaseAccountService().queryBaseAccountByID(gjSaleOrderEntity.getAccountid());
				String appkey="";
				String cmds="";
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
				appkey=baseOfficeEntities.get(0).getAppkey();
				Set<GjSalePassengerEntity> gjSalePassengerEntities=gjSaleOrderEntity.getPassengers();
				Iterator< GjSalePassengerEntity> passengerIterator=gjSalePassengerEntities.iterator();
				String pnrCode=null;
				while(passengerIterator.hasNext()){
					GjSalePassengerEntity gjSalePassengerEntity=passengerIterator.next();
					if(passengerId.equals(gjSalePassengerEntity.getId())){
						pnrCode=gjSalePassengerEntity.getPnr();
						break;
					}
				}
				if(BBOOConstants.GjSaleOrderEntity_flightClass_I.equals(gjSaleOrderEntity.getFlightClass())){
					cmds="RT:" + pnrCode+"^QTE:/"+gjSaleOrderEntity.getCarrier();
				}else{
					//国内的要单独处理
					cmds="RT:" + pnrCode+"^PAT:A";
				}
				GDSResult rs = null;
				rs = factoryService.getAllApiService().getCmdsByThirdInterface(appkey, cmds);
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("success", "查询成功");
				if(BBOOConstants.GjSaleOrderEntity_flightClass_I.equals(gjSaleOrderEntity.getFlightClass())){
					jsonObject.put("eterm","RT:" +gjSaleOrderEntity.getPnrCode()+"\r"+rs.getTxt()[0]+"\r"+"QTE:/"+gjSaleOrderEntity.getCarrier()+"\r"+rs.getTxt()[1]);
				}else{
					jsonObject.put("eterm","RT:" +gjSaleOrderEntity.getPnrCode()+"\r"+rs.getTxt()[0]+"\r"+"PAT:A\r"+rs.getTxt()[1]);
				}
				this.writeStream(jsonObject, "utf-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("error", e.toString());
			this.writeStream(jsonObject, "utf-8");
		}
	}
	/**
	 * 获取航程相关的信息
	 */
	public void getAvhInfo() {
		try {
			String id = request.getParameter("id");
			String flightId = request.getParameter("flightId");
			GjSaleOrderEntity gjSaleOrderEntity = factoryService.getSaleOrderService().queryOrderByID(id);
			if (gjSaleOrderEntity != null) {
				BaseAccountEntity baseAccountEntity = factoryService.getBaseAccountService().queryBaseAccountByID(gjSaleOrderEntity.getAccountid());
				// 订单导入之后去中航信拿pnr相关的信息
				String appkey="";
				String cmds="";
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
				appkey=baseOfficeEntities.get(0).getAppkey();
				Set<GjSaleFlightEntity>  gjSaleFlightEntities=gjSaleOrderEntity.getFlights();
				Iterator<GjSaleFlightEntity> iterator=gjSaleFlightEntities.iterator();
				while(iterator.hasNext()){
					GjSaleFlightEntity gjSaleFlightEntity=iterator.next();
					if(flightId.equals(gjSaleFlightEntity.getId())){
						//以前是用carrier字段，现在是用flightNumber字段来查询。
						cmds="AVH/"+gjSaleFlightEntity.getDepAircode()+gjSaleFlightEntity.getArrAircode()+"/"+dealFlightDate(gjSaleFlightEntity.getDepartureDate())+"/"+gjSaleFlightEntity.getFlightNum();
						GDSResult rs = null;
						rs = factoryService.getAllApiService().getCmdsByThirdInterface(appkey, cmds);
						JSONObject jsonObject = new JSONObject();
						jsonObject.put("success", "查询成功");
						jsonObject.put("eterm",cmds+"\r"+rs.getTxt()[0]);
						this.writeStream(jsonObject, "utf-8");
						return;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("error", e.toString());
			this.writeStream(jsonObject, "utf-8");
		}
	}
	private String dealFlightDate(String dateString){
		String[] str=dateString.split("-");
		String month="";
		if(str[1].equals("01")){
			month="JAN";
		}else if(str[1].equals("02")){
			month="FEB";
		}else if(str[1].equals("03")){
			month="MAR";
		}else if(str[1].equals("04")){
			month="APR";
		}else if(str[1].equals("05")){
			month="MAY";
		}else if(str[1].equals("06")){
			month="JUN";
		}else if(str[1].equals("07")){
			month="JUL";
		}else if(str[1].equals("08")){
			month="AUG";
		}else if(str[1].equals("09")){
			month="SEP";
		}else if(str[1].equals("10")){
			month="OCT";
		}else if(str[1].equals("11")){
			month="NOV";
		}else if(str[1].equals("12")){
			month="DEC";
		}
		return str[2]+month;
		
	}
	public static void main(String[] args) throws Exception {
		AllApi api = new AllApi();
		GDSDomain gdsDomain = new GDSDomain();
//		String office = baseAccountEntity.getOffice();
//		List<BaseOfficeEntity> baseOfficeEntities = factoryService.getIbaseOfficeService().queryByOffice(baseAccountEntity.getMno(), office);
//		if (baseOfficeEntities == null || baseOfficeEntities.size() < 1) {
//			//携程的订单rt不需要带office信息
//				throw new RuntimeException("未查询到该商户对应的office信息");
//		}
		gdsDomain.setAppKey("testapix");
//		Set<GjSaleFlightEntity>  gjSaleFlightEntities=gjSaleOrderEntity.getFlights();
//		Iterator<GjSaleFlightEntity> iterator=gjSaleFlightEntities.iterator();
//		String carrier=null;
//		if(iterator.hasNext()){
//			carrier=iterator.next().getCarrier();
//		}
		//gdsDomain.setIns("RT:JEXJ48");  //+"^QTE:/"+carrier
		gdsDomain.setIns("RT:JEXJ48"+"^PAT:A");  //+"^QTE:/"+carrier
		gdsDomain.setRequestIP("");
		gdsDomain.setTermId(String.valueOf(System.currentTimeMillis()));
		gdsDomain.setUrl("");
		gdsDomain.setUserId("");
		GDSResult rs = null;
		//KeyValEntity keyValEntity = factoryService.getIkeyValService().queryByKey("GDS_URL");
//		if (keyValEntity == null) {
//			throw new RuntimeException("未查询到GDS_URL的key_value信息");
//		}
		rs = api.gds_cmd(gdsDomain, "http://211.154.142.162:4005/ots/services/interface-test!entrance.do", "gds_cmds");
		String pnrNoTime=new OrderEtermAction().dealPnrNoTimeThree(rs.getTxt()[0]);
		System.out.println(pnrNoTime);
	}
	/**
	 *  处理这种格式的ADTK:9.SSR ADTK 1E JL S-CLS BY XX 27JUL XX USING SSR OR WILL BE XLD 
	 * @param pnrText
	 * @return
	 * @throws ParseException
	 */
	private  String dealPnrNoTimeThree(String pnrText) throws ParseException{
		String date=null;  //格式为
		String[] str=pnrText.split(" ");
		for(int i=0;i<str.length;i++){
			if("ADTK".equalsIgnoreCase(str[i])){
				date= str[i+6];
				break;
			}
		}
		if(date==null){
			return null;
		}
		String day=date.substring(0, 2);
		String mon=date.substring(2, 5);
		String month=parseMonthToNumber(mon);
		String year=""+DateUtils.getCurrentYear();
		String hour="00";
		String minute="00";
		return year+"-"+month+"-"+day+" "+hour+":"+minute+":"+"00";  //组装时间字符串
	}
	
	/**
	 *  处理这种格式的ADTK:8.SSR OTHS 1E PLS TKT BY 1615/22JUL/SZX OR ITIN WILL BE AUTO-CANCELED BY UL/       /MJ   
	 * @param pnrText
	 * @return
	 * @throws ParseException
	 */
	private  String dealPnrNoTimeFour(String pnrText) throws ParseException{
		String date=null;  //格式为
		String[] str=pnrText.split(" ");
		for(int i=0;i<str.length;i++){
			if("TKT".equalsIgnoreCase(str[i])){
				if("By".equalsIgnoreCase(str[i+1])){
					date= str[i+2];
					break;
				}
			}
		}
		if(date==null){
			return null;
		}
		String hour=date.substring(0, 2);
		String minute=date.substring(2, 4);
		String day=date.substring(5, 7);
		String mon=date.substring(7, 10);
		String month=parseMonthToNumber(mon);
		String year=""+DateUtils.getCurrentYear();
		return year+"-"+month+"-"+day+" "+hour+":"+minute+":"+"00";  //组装时间字符串
	}
	private String parseMonthToNumber(String month){
		if(StringUtils.isEmpty(month)){
			throw new RuntimeException("传入的月份不能为空");
		}
		if("JAN".equalsIgnoreCase(month)){
			return "01";
		}else if("Feb".equalsIgnoreCase(month)){
			return "02";
		}else if("Mar".equalsIgnoreCase(month)){
			return "03";
		}else if("Apr".equalsIgnoreCase(month)){
			return "04";
		}else if("May".equalsIgnoreCase(month)){
			return "05";
		}else if("Jun".equalsIgnoreCase(month)){
			return "06";
		}else if("Jul".equalsIgnoreCase(month)){
			return "07";
		}else if("Aug".equalsIgnoreCase(month)){
			return "08";
		}else if("Sep".equalsIgnoreCase(month)){
			return "09";
		}else if("Oct".equalsIgnoreCase(month)){
			return "10";
		}else if("Nov".equalsIgnoreCase(month)){
			return "11";
		}else if("Dec".equalsIgnoreCase(month)){
			return "12";
		}
		return "00";
	}
}
