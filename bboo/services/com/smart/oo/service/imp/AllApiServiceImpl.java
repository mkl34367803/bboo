package com.smart.oo.service.imp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.smart.comm.entity.BaseAccountEntity;
import com.smart.comm.entity.BaseContactsEntity;
import com.smart.comm.entity.BaseOfficeEntity;
import com.smart.comm.entity.GjBuyOrderEntity;
import com.smart.comm.entity.GjSaleFlightEntity;
import com.smart.comm.entity.GjSaleOrderEntity;
import com.smart.comm.entity.GjSalePassengerEntity;
import com.smart.comm.entity.KeyValEntity;
import com.smart.comm.entity.ProductFlightEntity;
import com.smart.comm.entity.ProductPriceEntity;
import com.smart.comm.utils.BBOOConstants;
import com.smart.comm.utils.OOComms;
import com.smart.comm.utils.OOLogUtil;
import com.smart.oo.dao.imp.FactoryDaoImpl;
import com.smart.oo.domain.AccountVO;
import com.smart.oo.domain.BuildPnrDomain;
import com.smart.oo.domain.CreateOrderForPb2bDomain;
import com.smart.oo.domain.DistributionModifyOrderInfoDomain;
import com.smart.oo.domain.EtdzDomain;
import com.smart.oo.domain.FlightDynamicsDomain;
import com.smart.oo.domain.FlightLegsVO;
import com.smart.oo.domain.FreightPriceVO;
import com.smart.oo.domain.GDSDomain;
import com.smart.oo.domain.GetFlightByLineDomain;
import com.smart.oo.domain.GetPnrInfoDomain;
import com.smart.oo.domain.GjOrderAccountVO;
import com.smart.oo.domain.GjOrderGetDetailDomain;
import com.smart.oo.domain.IeBookFlightVO;
import com.smart.oo.domain.IeBookPnrVO;
import com.smart.oo.domain.IeIssueTicketVO;
import com.smart.oo.domain.IePassengerTicketVO;
import com.smart.oo.domain.PayOrderForPb2bDomain;
import com.smart.oo.domain.PnrFlightVO;
import com.smart.oo.domain.PnrPassengerVO;
import com.smart.oo.domain.ProductsDomain;
import com.smart.oo.domain.TakePeopleVO;
import com.smart.oo.domain.res.AirPriceVO;
import com.smart.oo.domain.res.AirPsgVO;
import com.smart.oo.domain.res.AirTripVO;
import com.smart.oo.domain.res.BuildPnrResult;
import com.smart.oo.domain.res.CreateOrderForPb2bResult;
import com.smart.oo.domain.res.DistributionModifyOrderInfoResult;
import com.smart.oo.domain.res.EtdzResult;
import com.smart.oo.domain.res.FlightDynamicsResult;
import com.smart.oo.domain.res.GDSResult;
import com.smart.oo.domain.res.GetFlightByLineResult;
import com.smart.oo.domain.res.GetPnrInfoResult;
import com.smart.oo.domain.res.GjOrderGetDetailResult;
import com.smart.oo.domain.res.PayOrderForPb2bResult;
import com.smart.oo.domain.res.ProductElementVO;
import com.smart.oo.domain.res.ProductRootResult;
import com.smart.oo.domain.res.RtPnrPriceVO;
import com.smart.oo.service.IAllApiService;
import com.smart.oo.service.api.AllApi;
import com.smart.oo.vo.InterfaceAddressVO;
import com.smart.utils.MathUtil;

@Service("AllApiServiceImpl")
public class AllApiServiceImpl implements IAllApiService {
	@Autowired
	private FactoryDaoImpl factoryDao;

	@Override
	public GetPnrInfoResult getRtByThirdInterface(GjSaleOrderEntity gjSaleOrderEntity,boolean isUsePnrTxtCache) {
		GetPnrInfoResult rtrs = null;
		if(gjSaleOrderEntity!=null&&StringUtils.isNotEmpty(gjSaleOrderEntity.getPnrCode())){ //没有编码的订单，rt没有任何意义。
			try {
				AllApi api = new AllApi();
				GetPnrInfoDomain pnrvo = new GetPnrInfoDomain();
				BaseOfficeEntity paramVo=new BaseOfficeEntity();
				String mno=gjSaleOrderEntity.getMno();
				String office = gjSaleOrderEntity.getRtOffno();
				paramVo.setMno(mno);
				paramVo.setOffice(office);
				List<BaseOfficeEntity> baseOfficeEntities = factoryDao.getIbaseOfficeDao().queryByParamVo(paramVo);
				if (baseOfficeEntities == null || baseOfficeEntities.size() < 1) {
					return null; // 不抛异常，直接返回。
					// throw new RuntimeException("未查询到该商户对应的office信息");
				}
				pnrvo.setAppKey(baseOfficeEntities.get(0).getAppkey());
				pnrvo.setOffice(office);
				pnrvo.setPat(BBOOConstants.GetPnrInfoDomain_pat_one);
				pnrvo.setPnr(gjSaleOrderEntity.getPnrCode());
				if (BBOOConstants.GjSaleOrderEntity_flightClass_N.equals(gjSaleOrderEntity.getFlightClass())) {
					pnrvo.setPnrType(BBOOConstants.GjSaleOrderEntity_flightClass_N);
				} else {
					pnrvo.setPnrType(BBOOConstants.GjSaleOrderEntity_flightClass_I);
				}
				if(isUsePnrTxtCache){
					pnrvo.setPnrContent(gjSaleOrderEntity.getPnrText());//适用缓存的pnrtxt文本信息，这样不会浪费流量
				}else{
					pnrvo.setPnrContent("");
				}
				pnrvo.setRequestIP("");
				pnrvo.setTermId(String.valueOf(System.currentTimeMillis()));
				pnrvo.setUrl("");
				pnrvo.setUserId("");
				KeyValEntity keyValEntity = factoryDao.getIkeyValDao().queryByKey(BBOOConstants.AllApi_url_GdsUrl);
				if (keyValEntity == null) {
					throw new RuntimeException("未查询到GDS_URL的key_value信息");
				}
				OOLogUtil.info("RT获取pnr相关信息参数："+JSON.toJSONString(pnrvo), AllApiServiceImpl.class, null);
				if (gjSaleOrderEntity.getPnrCode().length() == 6) {// 有編碼的時候查詢
					rtrs = api.rt(pnrvo, keyValEntity.getV(), BBOOConstants.AllApi_service_GdsForRt); // rts.getcode==0,代表成功
				}
				OOLogUtil.info("RT获取pnr相关信息结果："+JSON.toJSONString(rtrs), AllApiServiceImpl.class, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return rtrs;
		
	}

	@Override
	public BuildPnrResult reservePnr(GjSaleOrderEntity gjSaleOrderEntity) throws Exception {
		if(gjSaleOrderEntity==null){
			return null;
		}
		AllApi api = new AllApi();
		BuildPnrDomain buildPnrDomain = new BuildPnrDomain();
		BaseOfficeEntity paramVo=new BaseOfficeEntity();
		String mno=gjSaleOrderEntity.getMno();
		String office = gjSaleOrderEntity.getRtOffno();
		paramVo.setMno(mno);
		paramVo.setOffice(office);
		List<BaseOfficeEntity> baseOfficeEntities = factoryDao.getIbaseOfficeDao().queryByParamVo(paramVo);
		if(baseOfficeEntities==null||baseOfficeEntities.size()<1){
			return null;
		}
		buildPnrDomain.setAppKey(baseOfficeEntities.get(0).getAppkey());
		buildPnrDomain.setRequestIP("");
		buildPnrDomain.setTermId(String.valueOf(System.currentTimeMillis()));
		buildPnrDomain.setUrl("");
		buildPnrDomain.setUserId("");
		buildPnrDomain.setIsiorn(gjSaleOrderEntity.getFlightClass());
		BaseContactsEntity param=new BaseContactsEntity();
		param.setMno(mno);
		List<BaseContactsEntity> baseContactsEntities= factoryDao.getBaseContactsDao().queryContacts(param);
		if(baseContactsEntities!=null&&baseContactsEntities.size()>0){
			BaseContactsEntity baseContactsEntity=baseContactsEntities.get(0);
			buildPnrDomain.setLinkman(baseContactsEntity.getLinkman());
			buildPnrDomain.setLinktel(baseContactsEntity.getLinktel());
			buildPnrDomain.setLinkphone(baseContactsEntity.getPhone());
		}
		buildPnrDomain.setOffice(office); //from html page
		buildPnrDomain.setRemark("");
		buildPnrDomain.setTktlMinutes("");
		buildPnrDomain.setTktlTime("");
		buildPnrDomain.setApply(BBOOConstants.BuildPnrDomain_apply_HK);
		Set<GjSalePassengerEntity> gjSalePassengerEntities=gjSaleOrderEntity.getPassengers();
		if(gjSalePassengerEntities!=null&&gjSalePassengerEntities.size()>0){
			List<PnrPassengerVO> pnrPassengerVOs = new ArrayList<PnrPassengerVO>();
			Iterator<GjSalePassengerEntity> iterator=gjSalePassengerEntities.iterator();
			while(iterator.hasNext()){
				GjSalePassengerEntity gjSalePassengerEntity=iterator.next();
				PnrPassengerVO pnrPassengerVO = new PnrPassengerVO();
				pnrPassengerVO.setName(gjSalePassengerEntity.getName());
				pnrPassengerVO.setIdentificationNo(gjSalePassengerEntity.getCardNum());
				//乘客类别 -1 留学生 0 成人 1 儿童 2婴儿 -2其他  
				//// CHD 儿童 ADU成人
				if(BBOOConstants.GjSalePassengerEntity_ageType_adult.equals(gjSalePassengerEntity.getAgeType())){
					pnrPassengerVO.setPsgType(BBOOConstants.PnrPassengerVO_psgType_adult);
				}else if(BBOOConstants.GjSalePassengerEntity_ageType_child.equals(gjSalePassengerEntity.getAgeType())){
					pnrPassengerVO.setPsgType(BBOOConstants.PnrPassengerVO_psgType_child);
				}
				pnrPassengerVOs.add(pnrPassengerVO);
			}
			buildPnrDomain.setPsg(pnrPassengerVOs);
		}
		List<PnrFlightVO> flt = new ArrayList<PnrFlightVO>();
		Set<GjSaleFlightEntity> gjSaleFlightEntities=gjSaleOrderEntity.getFlights();
		if(gjSaleFlightEntities!=null&&gjSaleFlightEntities.size()>0){
			Iterator<GjSaleFlightEntity> iterator=gjSaleFlightEntities.iterator();
			while(iterator.hasNext()){
				GjSaleFlightEntity gjSaleFlightEntity=iterator.next();
				PnrFlightVO f = new PnrFlightVO();
				f.setFlightNum(gjSaleFlightEntity.getFlightNum());
				f.setDepCode(gjSaleFlightEntity.getDepAircode());
				f.setDepTime(gjSaleFlightEntity.getDepartureDate());
				f.setCabin(gjSaleFlightEntity.getCabin());
				f.setArrCode(gjSaleFlightEntity.getArrAircode());
				flt.add(f);
			}
		}
		buildPnrDomain.setFlt(flt);
		KeyValEntity keyValEntity = factoryDao.getIkeyValDao().queryByKey(BBOOConstants.AllApi_url_GdsUrl);
		if (keyValEntity == null) {
			throw new RuntimeException("未查询到GDS_URL的key_value信息");
		}
		//打印日志
		OOLogUtil.info("预定编码参数："+JSON.toJSONString(buildPnrDomain), this.getClass(), null);
		String service=BBOOConstants.AllApi_service_GdsForSd;
		BuildPnrResult sdRs = api.sd(buildPnrDomain, keyValEntity.getV(), service);
		OOLogUtil.info("预定编码结果："+JSON.toJSONString(sdRs), this.getClass(), null);
		return sdRs;
	}
	@Override
	public GjOrderGetDetailResult getOrderInfoByInteface(BaseAccountEntity baseAccountEntity, String orderNo) throws Exception {
		AllApi api = new AllApi();
		InterfaceAddressVO address = new InterfaceAddressVO();
		address.setHost(baseAccountEntity.getApiHost());
		address.setServiceName(baseAccountEntity.getApiServiceName());
		address.setClassName(baseAccountEntity.getApiClassName());
		address.setMethodName(baseAccountEntity.getApiMethodName());
		GjOrderAccountVO account = new GjOrderAccountVO();
		account.setPassWord(baseAccountEntity.getSecret()); // 设置密码，注意，这里的命名有些奇怪
		account.setUserName(baseAccountEntity.getUserName());
		account.setOts(baseAccountEntity.getUrl());
		account.setSessionKey(baseAccountEntity.getSessions());
		GjOrderGetDetailDomain v = new GjOrderGetDetailDomain();
		v.setAccount(account);
		v.setOrderNo(orderNo);
		if (baseAccountEntity.getAcctype().contains(BBOOConstants.BASEACCOUNT_ACCTYPE_N)) {
			v.setOrderType(BBOOConstants.GjOrderGetDetailDomain_orderType_N);
		} else {
			v.setOrderType(BBOOConstants.GjOrderGetDetailDomain_orderType_I);
		}
		String name = new String();
		if (baseAccountEntity.getAcctype().equals(BBOOConstants.BASEACCOUNT_ACCTYPE_QUNAR_I) || (baseAccountEntity.getAcctype().equals(BBOOConstants.BASEACCOUNT_ACCTYPE_QUNAR_N))) {
			name = OOComms.GET_QUNAR_ORDER_DETAIL_INFO_METH;
		} else if (baseAccountEntity.getAcctype().equals(BBOOConstants.BASEACCOUNT_ACCTYPE_CTRIP_I) || baseAccountEntity.getAcctype().equals(BBOOConstants.BASEACCOUNT_ACCTYPE_CTRIP_N)) {
			name = OOComms.GET_CTRIP_ORDER_DETAIL_INFO_METH;
		} else if (baseAccountEntity.getAcctype().equals(BBOOConstants.BASEACCOUNT_ACCTYPE_TAOBAO_I) || baseAccountEntity.getAcctype().equals(BBOOConstants.BASEACCOUNT_ACCTYPE_TAOBAO_N)) {
			name = OOComms.GET_QUA_ORDER_DETAIL_INFO_METH;

		}else if (baseAccountEntity.getAcctype().equals(BBOOConstants.BASEACCOUNT_ACCTYPE_TONGC_I) || baseAccountEntity.getAcctype().equals(BBOOConstants.BASEACCOUNT_ACCTYPE_TONGC_N)) {
			name = OOComms.GET_TONGC_ORDER_DETAIL_INFO_METH;

		}else if (baseAccountEntity.getAcctype().equals(BBOOConstants.BASEACCOUNT_ACCTYPE_TUNIU_I) || baseAccountEntity.getAcctype().equals(BBOOConstants.BASEACCOUNT_ACCTYPE_TUNIU_N)) {
			name = OOComms.GET_TUNIU_ORDER_DETAIL_INFO_METH;

		}
		GjOrderGetDetailResult rlt = api.getDistributorOrderDetailByNo(v, address, name);
		return rlt;
	}

	@Override
	public GetFlightByLineResult getAvhByThirdInterface(String mno,String office,GjSaleFlightEntity gjSaleFlightEntity) throws Exception {
		AllApi allApi=new AllApi();
		GetFlightByLineDomain vo=new GetFlightByLineDomain ();
		vo.setApiType("");
		vo.setAppKey(getAppkey(mno, office));
		vo.setArrCode(gjSaleFlightEntity.getArrAircode());
		vo.setCarrier(gjSaleFlightEntity.getCarrier());
		vo.setDepCode(gjSaleFlightEntity.getDepAircode());
		vo.setDepDate(gjSaleFlightEntity.getDepartureDate());
		//vo.setDispSellOut(dispSellOut);
		//vo.setDispShare(dispShare);
		//vo.setEndTime(gjSaleFlightEntity.getArrivalTime());
		vo.setFlightNum(gjSaleFlightEntity.getFlightNum());
		vo.setOffice(office);
		vo.setRequestIP("");
		//vo.setStartTime(gjSaleFlightEntity.getDepartureTime());
		vo.setTermId("");
		vo.setUrl("");
		vo.setUserId("");
		String url=getUrl(BBOOConstants.AllApi_url_GdsUrl);
		//String service=BBOOConstants.AllApi_service_GdsForAvh;
		String service="gds_for_avh2016";  //测试用的数据
		OOLogUtil.info("获取avh的参数："+JSON.toJSONString(vo), this.getClass(), null);
		GetFlightByLineResult getFlightByLineResult=allApi.avh(vo, url, service);
		return getFlightByLineResult;
	}
	
	@Override
	public List<ProductRootResult> getProductDataByThirdAuthInterface(GjSaleOrderEntity gjSaleOrderEntity, GetPnrInfoResult rtrs) throws Exception {
		AllApi api = new AllApi();
		ProductsDomain pv = new ProductsDomain();
		List<FreightPriceVO> price = new ArrayList<FreightPriceVO>();
		if (rtrs != null && rtrs.getCode() == 0) { // code==0代表成功
			if (rtrs.getPrices() != null && rtrs.getPrices().size() > 0) {
				for (RtPnrPriceVO rtPnrPriceVO : rtrs.getPrices()) {
					FreightPriceVO freightPriceVO = new FreightPriceVO();
					freightPriceVO.setFace(Double.parseDouble(rtPnrPriceVO.getFare()));
					freightPriceVO.setTax(Double.parseDouble(rtPnrPriceVO.getTax()));
					freightPriceVO.setYq(Double.parseDouble(rtPnrPriceVO.getYq()));
					price.add(freightPriceVO);
				}
			} else {
				// 如果rt指令没有执行过，那么用salepassenger里面的数据，随便取一个。
				FreightPriceVO freightPriceVO = new FreightPriceVO();
				// 票面价
				freightPriceVO.setFace(gjSaleOrderEntity.getPassengers().iterator().next().getPrice());
				// 基建
				freightPriceVO.setTax(gjSaleOrderEntity.getPassengers().iterator().next().getTaxFee());
				// 燃油
				freightPriceVO.setYq(gjSaleOrderEntity.getPassengers().iterator().next().getOilFee());
				price.add(freightPriceVO);
			}
		} else {
			// 如果rt指令没有执行过，那么用salepassenger里面的数据，随便取一个。
			FreightPriceVO freightPriceVO = new FreightPriceVO();
			// 票面价
			freightPriceVO.setFace(gjSaleOrderEntity.getPassengers().iterator().next().getPrice());
			// 基建
			freightPriceVO.setTax(gjSaleOrderEntity.getPassengers().iterator().next().getTaxFee());
			// 燃油
			freightPriceVO.setYq(gjSaleOrderEntity.getPassengers().iterator().next().getOilFee());
			price.add(freightPriceVO);
		}
		pv.setPrice(price);
		List<BaseAccountEntity> baseAccountEntities = factoryDao.getBaseAccountDao().queryBaseAccounts();
		List<AccountVO> accounts = null;
		if (baseAccountEntities != null && baseAccountEntities.size() > 0) {
			accounts = new ArrayList<AccountVO>();
			for (BaseAccountEntity entity : baseAccountEntities) {
				if (entity.getAcctype().contains("CP_")) {
					AccountVO acc = new AccountVO();
					BeanUtils.copyProperties(entity, acc);
					// 这里要处理一些，传过去的参数需要将"CP_"去掉
					String acctype = entity.getAcctype();
					acc.setAcctype(acctype.substring(3, acctype.length()));
					acc.setAccid(entity.getId());
					accounts.add(acc);
				}
			}
		}
		pv.setAccounts(accounts);
		List<FlightLegsVO> flights = new ArrayList<FlightLegsVO>();
		for (GjSaleFlightEntity gjSaleFlightEntity : gjSaleOrderEntity.getFlights()) {
				FlightLegsVO flightLegsVO = new FlightLegsVO();
				flightLegsVO.setArrCode(gjSaleFlightEntity.getArrAircode());// 到达城市三字码
				flightLegsVO.setCarrier(gjSaleFlightEntity.getCarrier());
				flightLegsVO.setDepCode(gjSaleFlightEntity.getDepAircode());
				//这里特别要注意下面这个参数，是由两个字段拼接出来的。
				flightLegsVO.setDepDate(gjSaleFlightEntity.getDepartureDate()+" "+subStringOfDepatureTime(gjSaleFlightEntity.getDepartureTime()));
				if(StringUtils.isNotEmpty(gjSaleFlightEntity.getActFltno())){
					flightLegsVO.setFlightNo(gjSaleFlightEntity.getActFltno());
				}else{
					flightLegsVO.setFlightNo(gjSaleFlightEntity.getFlightNum());
				}
				if(StringUtils.isNotEmpty(gjSaleFlightEntity.getActCabin())){
					flightLegsVO.setCabin(gjSaleFlightEntity.getActCabin());
				}else{
					flightLegsVO.setCabin(gjSaleFlightEntity.getCabin());
				}
				flights.add(flightLegsVO);
		}
		// 作为参数
		pv.setFlights(flights);
		List<TakePeopleVO> peoples = new ArrayList<TakePeopleVO>();
		for (GjSalePassengerEntity gjSalePassengerEntity : gjSaleOrderEntity.getPassengers()) {
			TakePeopleVO takePeopleVO = new TakePeopleVO();
			takePeopleVO.setBirthday(gjSalePassengerEntity.getBirthday());
			takePeopleVO.setCertNo(gjSalePassengerEntity.getCardNum());
			takePeopleVO.setPsgName(gjSalePassengerEntity.getName());
			if (BBOOConstants.GjSalePassengerEntity_ageType_adult.equals(gjSalePassengerEntity.getAgeType())) {
				takePeopleVO.setPsgType(BBOOConstants.TakePeopleVO_psgType_adult);
			} else if (BBOOConstants.GjSalePassengerEntity_ageType_child.equals(gjSalePassengerEntity.getAgeType())) {
				takePeopleVO.setPsgType(BBOOConstants.TakePeopleVO_psgType_child);
			}
			peoples.add(takePeopleVO);
		}
		// 作为参数
		pv.setPeoples(peoples);
		pv.setBigPnr(gjSaleOrderEntity.getPnrCodeBig());
		//pv.setPatContent(patContent);
		pv.setTel(gjSaleOrderEntity.getContactTel());
		pv.setPnr(gjSaleOrderEntity.getPnrCode());
		//pv.setPnrContent(gjSaleOrderEntity.getPnrText());
		if (rtrs != null && rtrs.getCode() == 0) {
			pv.setPnrContent(rtrs.getTxt()); //不要再用旧的pnrtxt信息去获取政策了
		}
		pv.setLinkman(gjSaleOrderEntity.getContactName());
		pv.setTripType(getTripTypeByFlightType(gjSaleOrderEntity.getFlightType()));
		pv.setPsgCount(gjSaleOrderEntity.getPassengerCount());
		pv.setProductType(gjSaleOrderEntity.getProductSource());
		pv.setRequestId("");
		//pv.setProxyip(proxyip);
		//.setProxyport(proxyport);
		List<ProductRootResult> plist = null;
		KeyValEntity keyValEntity = factoryDao.getIkeyValDao().queryByKey(BBOOConstants.AllApi_url_GdsUrl);
		if (keyValEntity == null) {
			throw new RuntimeException("未查询到GDS_URL的key_value信息");
		}
		OOLogUtil.info("获取第三方政策信息参数："+JSON.toJSONString(pv), ProductDataServiceImpl.class, null);
		plist = api.getPolicys(pv, keyValEntity.getV(), BBOOConstants.AllApi_service_b2bPolicy);
		OOLogUtil.info("获取第三方政策信息结果："+JSON.toJSONString(plist), ProductDataServiceImpl.class, null);
		return plist;
	}

	@Override
	public GDSResult getCmdsByThirdInterface(String appkey,String cmds) throws Exception {
		AllApi api = new AllApi();
		GDSDomain gdsDomain = new GDSDomain();
		gdsDomain.setAppKey(appkey);
		gdsDomain.setIns(cmds);
		gdsDomain.setRequestIP("");
		gdsDomain.setTermId(String.valueOf(System.currentTimeMillis()));
		gdsDomain.setUrl("");
		gdsDomain.setUserId("");
		String url=getUrl(BBOOConstants.AllApi_url_GdsUrl);
		String service=BBOOConstants.AllApi_service_GdsCmds;
		GDSResult rs =  api.gds_cmds(gdsDomain, url, service);
		return rs;
	}

	@Override
	public CreateOrderForPb2bResult createOrderByThirdInterface(ProductPriceEntity productPriceEntity, GjSaleOrderEntity gjSaleOrderEntity) throws Exception {
		AllApi allApi=new AllApi();
		CreateOrderForPb2bDomain vo=packageCreateOrderParam(productPriceEntity, gjSaleOrderEntity);
		String url=getUrl(BBOOConstants.AllApi_url_CommUrl);
		String  service="b2b_create_order";
		CreateOrderForPb2bResult createOrderForPb2bResult=allApi.createOrder(vo, url, service);
		return createOrderForPb2bResult;
	}

	@Override
	public PayOrderForPb2bResult payOrderByThirdInterface(GjBuyOrderEntity gjBuyOrderEntity,BaseAccountEntity baseAccountEntity) throws Exception {
		//这里好像要区分B2B和Bsp出票？
		AllApi allApi=new AllApi();
		PayOrderForPb2bDomain vo=packagepayOrderParam(gjBuyOrderEntity,baseAccountEntity);
		String  url=getUrl(BBOOConstants.AllApi_url_CommUrl);
		String service=BBOOConstants.AllApi_service_b2bPayOrder;
		PayOrderForPb2bResult payOrderForPb2bResult=allApi.payOrder(vo, url, service);
		return payOrderForPb2bResult;
	}
	@Override
	public DistributionModifyOrderInfoResult modifyOrderByThirdInterface(GjSaleOrderEntity saleOrderEntity, GjBuyOrderEntity buyOrderEntity, BaseAccountEntity baseAccountEntity) throws Exception {
		AllApi api = new AllApi();
		GjOrderAccountVO account = new GjOrderAccountVO();
		account.setPassWord(baseAccountEntity.getSecret());
		account.setUserName(baseAccountEntity.getUserName());
		account.setOts(baseAccountEntity.getApiServiceName());
		account.setSessionKey(baseAccountEntity.getSessions());

		DistributionModifyOrderInfoDomain vo = new DistributionModifyOrderInfoDomain();
		vo.setAccount(account);
		vo.setAgentId("");
		vo.setOrderType(saleOrderEntity.getFlightClass());
		
		IeIssueTicketVO order = new IeIssueTicketVO();
		order.setOrderNo(saleOrderEntity.getOrderNo());
		order.setBillId(saleOrderEntity.getBillId());
		order.setId(saleOrderEntity.getExtOid());
		
		List<IeBookPnrVO> pnrInfo = new ArrayList<IeBookPnrVO>();
		IeBookPnrVO pnr = new IeBookPnrVO();
		pnr.setId(saleOrderEntity.getExtOid());
		pnr.setOfficeNo(saleOrderEntity.getPnrOffNo());
		pnr.setPnrNo(buyOrderEntity.getPnrCode());
		pnr.setPNRResultStatus(1);
		pnr.setPnrType(saleOrderEntity.getPnrTypeEn());
		pnrInfo.add(pnr);
		order.setPnrInfo(pnrInfo);

		List<IePassengerTicketVO> passengerInfo = new ArrayList<IePassengerTicketVO>();
		Set<GjSalePassengerEntity> salePassengerEntities = saleOrderEntity.getPassengers();
		IePassengerTicketVO psg = null;
		List<String> tnos = null;
		for (GjSalePassengerEntity salePassengerEntity : salePassengerEntities) {
			psg = new IePassengerTicketVO();
			psg.setId(salePassengerEntity.getExtId());
			psg.setPassengerName(salePassengerEntity.getName());
			psg.setCertNo(salePassengerEntity.getCardNum());
			tnos = new ArrayList<String>();
			tnos.add(salePassengerEntity.getEticketNum());
			psg.setTnos(tnos);
			passengerInfo.add(psg);
		}

		order.setPassengerInfo(passengerInfo);
		
		List<IeBookFlightVO> flightInfo = new ArrayList<IeBookFlightVO>();
		Set<GjSaleFlightEntity> flightEntities = saleOrderEntity.getFlights();
		IeBookFlightVO flight = null;
		for (GjSaleFlightEntity flightEntity : flightEntities) {
			flight = new IeBookFlightVO();
			flight.setSequence(flightEntity.getSequence().toString());
			flight.setFlightNo(flightEntity.getFlightNum());
			flight.setSubClass(flightEntity.getCabin());
			flight.setDPort(flightEntity.getDepAircode());
			flight.setAPort(flightEntity.getArrAircode());
			flight.setDepTime(flightEntity.getDepartureDate()+" "+flightEntity.getDepartureTime());
			flight.setCarrier(flightEntity.getCarrier());
			flightInfo.add(flight);
		}
		order.setFltInfo(flightInfo);
		
		vo.setOrder(order);
		
		String acctype = baseAccountEntity.getAcctype();
		String service = null;
		if (BBOOConstants.BASEACCOUNT_ACCTYPE_QUNAR_I.equals(acctype)||BBOOConstants.BASEACCOUNT_ACCTYPE_QUNAR_N.equals(acctype)) {
			service = OOComms.GET_QUA_ORDER_MODIFY_INFO_METH;
		} else if (BBOOConstants.BASEACCOUNT_ACCTYPE_CTRIP_I.equals(acctype)||BBOOConstants.BASEACCOUNT_ACCTYPE_CTRIP_N.equals(acctype)) {
			service = OOComms.GET_CTRIP_ORDER_MODIFY_INFO_METH;
		} else if (BBOOConstants.BASEACCOUNT_ACCTYPE_TAOBAO_I.equals(acctype)||BBOOConstants.BASEACCOUNT_ACCTYPE_TAOBAO_N.equals(acctype)) {
			service = OOComms.GET_QUA_ORDER_MODIFY_INFO_METH;
		}else if (BBOOConstants.BASEACCOUNT_ACCTYPE_TONGC_I.equals(acctype)||BBOOConstants.BASEACCOUNT_ACCTYPE_TONGC_N.equals(acctype)) {
			service = OOComms.GET_TONGC_ORDER_MODIFY_INFO_METH;
		}else if (BBOOConstants.BASEACCOUNT_ACCTYPE_TUNIU_I.equals(acctype)||BBOOConstants.BASEACCOUNT_ACCTYPE_TUNIU_N.equals(acctype)) {
			service = OOComms.GET_TUNIU_ORDER_MODIFY_INFO_METH;
		}
		String url = getUrl(BBOOConstants.AllApi_url_CommUrl);
		//String str = JSON.toJSONString(vo);
		DistributionModifyOrderInfoResult rs = api.modifyOrder(vo, url, service);
		return rs;
	}
	
	private String getAppkey(String mno,String office) throws Exception{
		BaseOfficeEntity paramVo=new BaseOfficeEntity();
		paramVo.setMno(mno);
		paramVo.setOffice(office);
		List<BaseOfficeEntity> baseOfficeEntities = factoryDao.getIbaseOfficeDao().queryByParamVo(paramVo);
		if(baseOfficeEntities==null||baseOfficeEntities.size()<1){
			return null;
		}else{
			return baseOfficeEntities.get(0).getAppkey();
		}
	}
	
	/**
	 * 通过url类型获取对应的url的具体值
	 * @param urlType url类型
	 * @return
	 * @throws Exception
	 */
	private String getUrl(String urlType) throws Exception{
		KeyValEntity keyValEntity = factoryDao.getIkeyValDao().queryByKey(urlType);
		if (keyValEntity == null) {
			throw new RuntimeException("未查询到"+urlType+"的keyValue值");
		}
		return keyValEntity.getV();
	}
	
	private Integer getTripTypeByFlightType(String flightType){
		//(0:单程/1:往返/2:单程及往返 3多程)
		if(BBOOConstants.GjSaleOrderEntity_flightType_D.equals(flightType)){
			return 0;
		}else if(BBOOConstants.GjSaleOrderEntity_flightType_S.equals(flightType)){
			return 1;
		}else if(BBOOConstants.GjSaleOrderEntity_flightType_X.equals(flightType)){
			return 3;
		}else{
			return 2;
		}
	}
	
	
private CreateOrderForPb2bDomain packageCreateOrderParam(ProductPriceEntity productPriceEntity,GjSaleOrderEntity gjSaleOrderEntity) throws Exception{
		
		if(productPriceEntity!=null&&gjSaleOrderEntity!=null){
			CreateOrderForPb2bDomain createOrderForPb2bDomain=new CreateOrderForPb2bDomain();
			createOrderForPb2bDomain.setBigPnr(gjSaleOrderEntity.getPnrCodeBig());
			BaseContactsEntity param=new BaseContactsEntity();
			param.setMno(gjSaleOrderEntity.getMno());
			List<BaseContactsEntity> baseContactsEntities= factoryDao.getBaseContactsDao().queryContacts(param);
			//注意要用采购方面的联系人
			if(baseContactsEntities!=null&&baseContactsEntities.size()>0){
				BaseContactsEntity baseContactsEntity=baseContactsEntities.get(0);
				createOrderForPb2bDomain.setEmail(baseContactsEntity.getEmail());
				createOrderForPb2bDomain.setLinkman(baseContactsEntity.getLinkman());
				createOrderForPb2bDomain.setTel(baseContactsEntity.getLinktel());
			}
			//createOrderForPb2bDomain.setPatContent(gjSaleOrderEntity.getPnrText());
			createOrderForPb2bDomain.setPnr(gjSaleOrderEntity.getPnrCode());
			GetPnrInfoResult getPnrInfoResult=getRtByThirdInterface(gjSaleOrderEntity,false);
			if(getPnrInfoResult!=null&&BBOOConstants.GetPnrInfoResult_code_zero.equals(getPnrInfoResult.getCode())){
				createOrderForPb2bDomain.setPnrContent(getPnrInfoResult.getTxt());
			}else{
				createOrderForPb2bDomain.setPnrContent(gjSaleOrderEntity.getPnrText());
			}
			//createOrderForPb2bDomain.setProxyip(proxyip);
			//createOrderForPb2bDomain.setProxyport(proxyport);
			createOrderForPb2bDomain.setPsgCount(gjSaleOrderEntity.getPassengerCount());
			createOrderForPb2bDomain.setRequestId(gjSaleOrderEntity.getId());
			createOrderForPb2bDomain.setSaleNo(gjSaleOrderEntity.getOrderNo());
			createOrderForPb2bDomain.setTripType(getTripTypeByFlightType(gjSaleOrderEntity.getFlightType()));
			//这里要用采购方的accountId去查询
			BaseAccountEntity baseAccountEntity=factoryDao.getBaseAccountDao().queryBaseAccountByID(Integer.parseInt(productPriceEntity.getProduct().getAccountId()));
			if(baseAccountEntity!=null){
				List<AccountVO> accountVOs=new ArrayList<AccountVO>();
				AccountVO accountVo=new AccountVO();
				BeanUtils.copyProperties(baseAccountEntity, accountVo);
				
				accountVo.setAccid(baseAccountEntity.getId());
				String acctype=accountVo.getAcctype();
				accountVo.setAcctype(acctype.substring(3, acctype.length()));
				accountVOs.add(accountVo);
				createOrderForPb2bDomain.setAccounts(accountVOs);
				
			}else{
				return null;
			}
			ProductElementVO productElementVO=new ProductElementVO();
			BeanUtils.copyProperties(productPriceEntity.getProduct(), productElementVO,new String[]{"isChangePnr","priceList","tripList","psgList"});
			List<AirPriceVO> priceList=new ArrayList<AirPriceVO>();
			AirPriceVO airPriceVO=new AirPriceVO();
			BeanUtils.copyProperties(productPriceEntity, airPriceVO);
			priceList.add(airPriceVO);
			productElementVO.setPriceList(priceList);
			List<AirPsgVO> psgList= new ArrayList<AirPsgVO>();
			if(gjSaleOrderEntity.getPassengers()!=null){
				Iterator<GjSalePassengerEntity> iterator=gjSaleOrderEntity.getPassengers().iterator();
				while(iterator.hasNext()){
					GjSalePassengerEntity gjSalePassengerEntity=iterator.next();
					AirPsgVO airPsgVO=new AirPsgVO();
					airPsgVO.setBirthday(gjSalePassengerEntity.getBirthday());
					airPsgVO.setCertNo(gjSalePassengerEntity.getCardNum());
					airPsgVO.setPsgName(gjSalePassengerEntity.getName());
					if(BBOOConstants.GjSalePassengerEntity_ageType_adult.equals(gjSalePassengerEntity.getAgeType())){
						airPsgVO.setPsgType(BBOOConstants.AirPsgVO_psgType_ADU);
					}else if(BBOOConstants.GjSalePassengerEntity_ageType_child.equals(gjSalePassengerEntity.getAgeType())){
						airPsgVO.setPsgType(BBOOConstants.AirPsgVO_psgType_CHD);
					}
					airPsgVO.setTicketNo(gjSalePassengerEntity.getEticketNum());
					psgList.add(airPsgVO);
				}
			}
			productElementVO.setPsgList(psgList);
			List<AirTripVO> tripList=new ArrayList<AirTripVO>();
			Set<ProductFlightEntity> productFlightEntities=productPriceEntity.getProduct().getTripList();
			if(productFlightEntities!=null&&productFlightEntities.size()>0){
				Iterator<ProductFlightEntity> iterator=productFlightEntities.iterator();
				while(iterator.hasNext()){
					ProductFlightEntity productFlightEntity=iterator.next();
					AirTripVO airTripVO=new AirTripVO();
					BeanUtils.copyProperties(productFlightEntity, airTripVO);
					tripList.add(airTripVO);
				}
			}
			productElementVO.setTripList(tripList);
			createOrderForPb2bDomain.setProduct(productElementVO);
			return createOrderForPb2bDomain;
		}else{
			return null;
		}
	}
	
	/**
	 * 组装
	 * @param productPriceEntity
	 * @param gjBuyOrderEntity
	 * @return
	 * @throws Exception
	 */
	private PayOrderForPb2bDomain packagepayOrderParam(GjBuyOrderEntity gjBuyOrderEntity,BaseAccountEntity baseAccountEntity) throws Exception{
		if(gjBuyOrderEntity==null){
			return null;
		}else{
			PayOrderForPb2bDomain vo=new PayOrderForPb2bDomain();
			//支付的时候用的是政策里面的accountid
			if(baseAccountEntity!=null){
				List<AccountVO> accountVOs=new ArrayList<AccountVO>();
				AccountVO accountVo=new AccountVO();
				BeanUtils.copyProperties(baseAccountEntity, accountVo);
				accountVo.setAccid(baseAccountEntity.getId());
				String acctype=accountVo.getAcctype();
				accountVo.setAcctype(acctype.substring(3, acctype.length()));
				accountVOs.add(accountVo);
				vo.setAccounts(accountVOs);
			}else{
				return null;
			}
			vo.setBigPnr(gjBuyOrderEntity.getPnrCodeBig());
			vo.setOrderNo(gjBuyOrderEntity.getPurchaseNo()); //采购订单号
			//vo.setPatContent(patContent);
			vo.setPayPrice(gjBuyOrderEntity.getAllprice());
			vo.setPnr(gjBuyOrderEntity.getPnrCode());
			vo.setPnrContent(gjBuyOrderEntity.getPnrText());
			//vo.setProxyip(proxyip);
			//vo.setProxyport(proxyport);
			vo.setPsgCount(gjBuyOrderEntity.getPassengerCount());
			//vo.setRequestId(requestId);
			vo.setSaleId(gjBuyOrderEntity.getId()); //暂时设置为交易流水号
			vo.setTax(MathUtil.mul((gjBuyOrderEntity.getTaxFee()), gjBuyOrderEntity.getPassengerCount()));
			vo.setTicketPar(MathUtil.mul(gjBuyOrderEntity.getPrintPrice(),gjBuyOrderEntity.getPassengerCount()));
			return vo;
		}
	}
	private String subStringOfDepatureTime(String departureTime){
		try {
			if(StringUtils.isNotEmpty(departureTime)){
				return departureTime.substring(0, 5);
			}else{
				return "";
			}
		} catch (Exception e) {
			return "";
		}
	}

	@Override
	public EtdzResult createOrderByEtdz(GjSaleOrderEntity gjSaleOrderEntity,BaseOfficeEntity baseOfficeEntity) throws Exception {
		AllApi allApi=new AllApi();
		EtdzDomain vo=new EtdzDomain();
		vo.setAppKey(baseOfficeEntity.getAppkey());
//		vo.setFace();  //这个字段我还不知道什么意思
		vo.setInkpad(baseOfficeEntity.getEtdzNum());//这个用谁的office号呢？
		vo.setOffice(baseOfficeEntity.getOffice());
		vo.setPnr(gjSaleOrderEntity.getPnrCode());
		vo.setRequestIP("");
		vo.setTermId(String.valueOf(System.currentTimeMillis()));
		vo.setUrl("");
		vo.setUserId("");
		String url=getUrl(BBOOConstants.AllApi_url_CommUrl);
		String service=BBOOConstants.AllApi_service_GdsForEtdz;
		EtdzResult etdzResult=allApi.etdz(vo, url, service);
		return etdzResult;
	}

	@Override
	public FlightDynamicsResult getFlightDynamics(
			FlightDynamicsDomain flightDynamicsDomain) throws Exception {
		AllApi allApi=new AllApi();
		
		String url = getUrl(BBOOConstants.AllApi_url_CommUrl);
		String service = BBOOConstants.FLIGHT_DYNAMICS;
		
		FlightDynamicsResult result = allApi.getFlightDynamics(flightDynamicsDomain, url, service);
		return result;
	}
}
