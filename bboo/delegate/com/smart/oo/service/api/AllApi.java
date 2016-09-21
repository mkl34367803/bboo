package com.smart.oo.service.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bbang.api.BbangClient;
import com.bbang.api.DefaultBbangClient;
import com.smart.comm.utils.OOLogUtil;
import com.smart.oo.domain.BuildPnrDomain;
import com.smart.oo.domain.CreateOrderForPb2bDomain;
import com.smart.oo.domain.DetnDomain;
import com.smart.oo.domain.DetrBy517Domain;
import com.smart.oo.domain.DetrByTnoDomain;
import com.smart.oo.domain.DetrInsDomain;
import com.smart.oo.domain.DistributionModifyOrderInfoDomain;
import com.smart.oo.domain.EtdzDomain;
import com.smart.oo.domain.FlightDynamicsDomain;
import com.smart.oo.domain.GDSDomain;
import com.smart.oo.domain.GetCacheFdPriceDomain;
import com.smart.oo.domain.GetFlightByLineDomain;
import com.smart.oo.domain.GetPnrInfoDomain;
import com.smart.oo.domain.GjOrderGetDetailDomain;
import com.smart.oo.domain.GjOrderGetListDomain;
import com.smart.oo.domain.PayOrderForPb2bDomain;
import com.smart.oo.domain.ProductsDomain;
import com.smart.oo.domain.TfareDetrDomain;
import com.smart.oo.domain.res.AirPriceVO;
import com.smart.oo.domain.res.AirPsgVO;
import com.smart.oo.domain.res.AirTripVO;
import com.smart.oo.domain.res.BuildPnrResult;
import com.smart.oo.domain.res.CouponInfoVO;
import com.smart.oo.domain.res.CreateOrderForPb2bResult;
import com.smart.oo.domain.res.DetnResult;
import com.smart.oo.domain.res.DetrBy517Result;
import com.smart.oo.domain.res.DetrByTnoResult;
import com.smart.oo.domain.res.DetrInsResult;
import com.smart.oo.domain.res.DistributionModifyOrderInfoResult;
import com.smart.oo.domain.res.EtdzResult;
import com.smart.oo.domain.res.FlightDynamicsResult;
import com.smart.oo.domain.res.GDSResult;
import com.smart.oo.domain.res.GetFdsPriceResult;
import com.smart.oo.domain.res.GetFlightByLineResult;
import com.smart.oo.domain.res.GetPnrInfoResult;
import com.smart.oo.domain.res.GjOrderGetDetailResult;
import com.smart.oo.domain.res.GjOrderGetListResult;
import com.smart.oo.domain.res.PayOrderForPb2bResult;
import com.smart.oo.domain.res.ProductElementVO;
import com.smart.oo.domain.res.ProductRootResult;
import com.smart.oo.domain.res.TfareDetrResult;
import com.smart.oo.domain.res.TicketInfo;
import com.smart.oo.request.CommParamRequest;
import com.smart.oo.response.CommResultResponse;
import com.smart.oo.vo.InterfaceAddressVO;
import com.smart.utils.JsonPluginsUtil;
import com.smart.utils.StringUtils;

public class AllApi {

	public static void main(String[] args) {
		AllApi api = new AllApi();
		DetrByTnoDomain fdv = new DetrByTnoDomain();
		fdv.setTicketNo("479-2306103892");
		fdv.setTermId(String.valueOf(System.currentTimeMillis()));
		fdv.setAppKey("testapix");
		DetrByTnoResult frs = null;
		try {
			frs = api
					.detr_byno_gds(
							fdv,
							"http://211.154.142.162:4005/ots/services/interfaceabc-test!entrance.do",
							"gds_for_detr_tn");
			if (frs != null) {
				System.out.println(JsonPluginsUtil.beanToJson(frs));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param vo
	 * @param url
	 * @param service
	 *            gds_for_rt
	 * @return
	 * @throws Exception
	 */
	public String _etdz(EtdzDomain vo, String url, String service)
			throws Exception {
		return request(JsonPluginsUtil.beanToJson(vo), service, url);
	}

	/**
	 * 出BSP客票
	 * 
	 * @param vo
	 * @param url
	 * @param service
	 *            gds_for_etdz
	 * @return
	 * @throws Exception
	 */
	public EtdzResult etdz(EtdzDomain vo, String url, String service)
			throws Exception {
		String json = _etdz(vo, url, service);
		if (json != null) {
			return JsonPluginsUtil.jsonToFastBean(json, EtdzResult.class);
		}
		return null;

	}

	/**
	 * 查询航班公布运价
	 */
	/**
	 * 
	 * @param vo
	 * @param url
	 *            http://www.bangpiao.net/services/bbang-interface!entrance.do
	 * @param service
	 *            ibe_cmd_cache_fds
	 * @return
	 * @throws Exception
	 */
	public GetFdsPriceResult getCacheFdsPrice(GetCacheFdPriceDomain vo,
			String url, String service) throws Exception {
		String json = request(JsonPluginsUtil.beanToJson(vo), service, url);
		GetFdsPriceResult rlt = null;
		if (json != null && json.contains("{")) {
			rlt = JsonPluginsUtil.jsonToFastBean(json, GetFdsPriceResult.class);
		}
		return rlt;
	}

	public DetrBy517Result detr_517ForObj(DetrBy517Domain vo, String url,
			String service) {
		String json = detr_517(vo, url, service);
		DetrBy517Result rlt = null;
		if (json != null && json.contains("{")) {
			rlt = JsonPluginsUtil.jsonToFastBean(json, DetrBy517Result.class);
		}
		return rlt;
	}

	/**
	 * 
	 * @param vo
	 * @param url
	 * @param name
	 *            ibe_cmd_517_detr
	 * @return
	 */
	public String detr_517(DetrBy517Domain vo, String url, String service) {
		// TODO Auto-generated method stub
		try {
			return request(JsonPluginsUtil.beanToJson(vo), service, url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public DetrByTnoResult detr_byno_gds(DetrByTnoDomain v, String url,
			String service) throws Exception {
		String json = _detr_byno_gds(v, url, service);
		DetrByTnoResult rlt = null;
		if (json != null && json.contains("{")) {
			rlt = JsonPluginsUtil.jsonToFastBean(json, DetrByTnoResult.class);
		}
		return rlt;
	}

	/**
	 * 
	 * @param psgName
	 * @param tno
	 * @param url
	 * @param name
	 *            gds_for_detr_tn
	 * @return
	 * @throws Exception
	 */
	public String _detr_byno_gds(DetrByTnoDomain v, String url, String service)
			throws Exception {
		// TODO Auto-generated method stub
		return request(JsonPluginsUtil.beanToJson(v), service, url);
	}

	public TicketInfo detr_xtyForObj(String psgName, String tno, String url,
			String service) {
		String json = detr_xty(psgName, tno, url, service);
		TicketInfo rlt = null;
		if (json != null && json.contains("{")) {
			rlt = JsonPluginsUtil.jsonToFastBean(json, TicketInfo.class);
		}
		return rlt;
	}

	/**
	 * 
	 * @param psgName
	 * @param tno
	 * @param url
	 * @param name
	 *            ibe_cmd_xty_detr
	 * @return
	 */
	public String detr_xty(String psgName, String tno, String url,
			String service) {
		// TODO Auto-generated method stub
		try {
			return request(psgName + "," + tno, service, url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public TfareDetrResult detr_tfareForObj(TfareDetrDomain vo, String url,
			String service) {
		String json = detr_tfare(vo, url, service);
		TfareDetrResult rlt = null;
		if (json != null && json.contains("{")) {
			rlt = JsonPluginsUtil.jsonToFastBean(json, TfareDetrResult.class);
		} else {
			System.out.println(json);
		}
		return rlt;
	}

	/**
	 * 
	 * @param vo
	 * @param url
	 * @param service
	 *            tfare_detr
	 * @return
	 */
	public String detr_tfare(TfareDetrDomain vo, String url, String service) {
		// TODO Auto-generated method stub
		try {
			return request(JsonPluginsUtil.beanToJson(vo), service, url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public DetrInsResult detr_cmdForObj(DetrInsDomain vo, String url,
			String service) {
		String json = detr_cmd(vo, url, service);
		DetrInsResult rlt = null;
		if (json != null && json.contains("{")) {
			rlt = JsonPluginsUtil.jsonToFastBean(json, DetrInsResult.class);
		}
		return rlt;
	}

	/**
	 * 
	 * @param vo
	 * @param url
	 * @param service
	 *            ibe_cmd_detr
	 * @return
	 */
	public String detr_cmd(DetrInsDomain vo, String url, String service) {
		// TODO Auto-generated method stub
		try {
			return request(JsonPluginsUtil.beanToJson(vo), service, url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public DetnResult detn_cmdForObj(DetnDomain vo, String url, String service) {

		String json = detn_cmd(vo, url, service);
		DetnResult rlt = null;
		if (json != null && json.contains("{")) {
			rlt = JsonPluginsUtil.jsonToFastBean(json, DetnResult.class);
		}
		return rlt;
	}

	/**
	 * 
	 * @param vo
	 * @param url
	 * @param service
	 *            ibe_cmd_detn
	 * @return
	 */
	public String detn_cmd(DetnDomain vo, String url, String service) {
		try {
			return request(JsonPluginsUtil.beanToJson(vo), service, url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param vo
	 * @param url
	 * @param service
	 *            gds_for_avh
	 * @return
	 * @throws Exception
	 */
	public String _avh(GetFlightByLineDomain vo, String url, String service)
			throws Exception {
		return request(JsonPluginsUtil.beanToJson(vo), service, url);
	}

	/**
	 * 查询航班余票信息
	 * 
	 * @param vo
	 * @param url
	 * @param service
	 * @return
	 * @throws Exception
	 */
	public GetFlightByLineResult avh(GetFlightByLineDomain vo, String url,
			String service) throws Exception {
		String json = _avh(vo, url, service);
		if (json != null) {
			return JsonPluginsUtil.jsonToFastBean(json,
					GetFlightByLineResult.class);
		}
		return null;
	}

	/**
	 * 
	 * @param vo
	 * @param url
	 * @param service
	 *            gds_for_rt
	 * @return
	 * @throws Exception
	 */
	public String _rt(GetPnrInfoDomain vo, String url, String service)
			throws Exception {
		return request(JsonPluginsUtil.beanToJson(vo), service, url);
	}

	/**
	 * 提取 编码信息
	 * 
	 * @param vo
	 * @param url
	 * @param service
	 * @return
	 * @throws Exception
	 */
	public GetPnrInfoResult rt(GetPnrInfoDomain vo, String url, String service)
			throws Exception {
		String json = _rt(vo, url, service);
		if (json != null) {
			return JsonPluginsUtil.jsonToFastBean(json, GetPnrInfoResult.class);
		}
		return null;

	}

	/**
	 * 
	 * @param vo
	 * @param url
	 * @param service
	 *            gds_for_sd
	 * @return
	 * @throws Exception
	 */
	public String _sd(BuildPnrDomain vo, String url, String service)
			throws Exception {
		return request(JsonPluginsUtil.beanToJson(vo), service, url);
	}

	/**
	 * 预定编码 在航司系统中下单 编码信息
	 * 
	 * @param vo
	 * @param url
	 * @param service
	 *            gds_for_sd
	 * @return
	 * @throws Exception
	 */
	public BuildPnrResult sd(BuildPnrDomain vo, String url, String service)
			throws Exception {
		String json = _sd(vo, url, service);
		if (json != null) {
			return JsonPluginsUtil.jsonToFastBean(json, BuildPnrResult.class);
		}
		return null;

	}

	/**
	 * 
	 * @param vo
	 * @param url
	 * @param service
	 *            query_b2b_policys
	 * @return
	 * @throws Exception
	 */
	public String _getPolicys(ProductsDomain vo, String url, String service)
			throws Exception {
		return request(JsonPluginsUtil.beanToJson(vo), service, url);
	}

	/**
	 * 获取政策列表
	 * 
	 * @param vo
	 * @param url
	 * @param service
	 * @return
	 * @throws Exception
	 */
	public List<ProductRootResult> getPolicys(ProductsDomain vo, String url,
			String service) throws Exception {
		String json = _getPolicys(vo, url, service);
		if (json != null) {
			@SuppressWarnings("rawtypes")
			Map<String, Class> classMap = new HashMap<String, Class>();
			classMap.put("rlts", ProductElementVO.class);
			classMap.put("priceList", AirPriceVO.class);
			classMap.put("tripList", AirTripVO.class);
			classMap.put("psgList", AirPsgVO.class);
			classMap.put("couponInfo", CouponInfoVO.class);
			return JsonPluginsUtil.jsonToBeanList(json,
					ProductRootResult.class, classMap);
		}
		return null;

	}

	/**
	 * pay订单
	 * 
	 * @param vo
	 * @param url
	 * @param service
	 * 
	 * @return
	 * @throws Exception
	 */
	public String _payOrder(PayOrderForPb2bDomain vo, String url, String service)
			throws Exception {
		return request(JsonPluginsUtil.beanToJson(vo), service, url);
	}

	/**
	 * pay订单
	 * 
	 * @param vo
	 * @param url
	 * @param service
	 * @return
	 * @throws Exception
	 */
	public PayOrderForPb2bResult payOrder(PayOrderForPb2bDomain vo, String url,
			String service) throws Exception {
		String json = _payOrder(vo, url, service);
		if (json != null)
			return JsonPluginsUtil.jsonToFastBean(json,
					PayOrderForPb2bResult.class);
		return null;
	}

	/**
	 * 
	 * @param vo
	 * @param url
	 * @param service
	 *            b2b_create_order
	 * @return
	 * @throws Exception
	 */
	public String _createOrder(CreateOrderForPb2bDomain vo, String url,
			String service) throws Exception {
		return request(JsonPluginsUtil.beanToJson(vo), service, url);
	}

	/**
	 * 下单接口
	 * 
	 * @param vo
	 * @param url
	 * @param service
	 * @return
	 * @throws Exception
	 */
	public CreateOrderForPb2bResult createOrder(CreateOrderForPb2bDomain vo,
			String url, String service) throws Exception {
		String json = _createOrder(vo, url, service);
		if (json != null)
			return JsonPluginsUtil.jsonToFastBean(json,
					CreateOrderForPb2bResult.class);
		return null;

	}

	/**
	 * 查询航班动态
	 * 
	 * @param vo
	 * @param url
	 * @param service
	 *            query_hbdt
	 * @return
	 * @throws Exception
	 */
	public String _getFlightDynamics(FlightDynamicsDomain vo, String url,
			String service) throws Exception {
		return request(JsonPluginsUtil.beanToJson(vo), service, url);
	}

	/**
	 * 查询航班动态
	 * 
	 * @param vo
	 * @param url
	 * @param service
	 *            query_hbdt
	 * @return
	 * @throws Exception
	 */
	public FlightDynamicsResult getFlightDynamics(FlightDynamicsDomain vo,
			String url, String service) throws Exception {
		String json = _getFlightDynamics(vo, url, service);
		if (json != null)
			return JsonPluginsUtil.jsonToFastBean(json,
					FlightDynamicsResult.class);
		return null;

	}

	/**
	 * 
	 * @param vo
	 * @param url
	 * @param service
	 *            gds_cmd
	 * @return
	 * @throws Exception
	 */
	public String _gds_cmd(GDSDomain vo, String url, String service)
			throws Exception {
		return request(JsonPluginsUtil.beanToJson(vo), service, url);
	}

	/**
	 * 
	 * @param vo
	 * @param url
	 * @param service
	 *            gds_cmds
	 * @return
	 * @throws Exception
	 */
	public String _gds_cmds(GDSDomain vo, String url, String service)
			throws Exception {
		return request(JsonPluginsUtil.beanToJson(vo), service, url);
	}

	public GDSResult gds_cmd(GDSDomain vo, String url, String service)
			throws Exception {
		String json = _gds_cmd(vo, url, service);
		if (json != null)
			return JsonPluginsUtil.jsonToFastBean(json, GDSResult.class);
		return null;
	}

	public GDSResult gds_cmds(GDSDomain vo, String url, String service)
			throws Exception {
		String json = _gds_cmds(vo, url, service);
		if (json != null)
			return JsonPluginsUtil.jsonToFastBean(json, GDSResult.class);
		return null;
	}

	/**
	 * 修改订单状态
	 * 
	 * @param vo
	 * @param url
	 * @param service
	 *            modifyFxOrderInfo@qua modifyFxOrderInfo@ctrip
	 *            modifyFxOrderInfo@qunar
	 * @return
	 * @throws Exception
	 */
	public DistributionModifyOrderInfoResult modifyOrder(
			DistributionModifyOrderInfoDomain vo, String url, String service)
			throws Exception {
		String json = _modifyOrder(vo, url, service);
		if (json != null)
			return JsonPluginsUtil.jsonToFastBean(json,
					DistributionModifyOrderInfoResult.class);
		return null;
	}

	public String _modifyOrder(DistributionModifyOrderInfoDomain vo,
			String url, String service) throws Exception {
		return request(JsonPluginsUtil.beanToJson(vo), service, url);
	}

	public String detr_db() {

		return null;
	}

	public GjOrderGetDetailResult getDistributorOrderDetailByNo(
			GjOrderGetDetailDomain vo, InterfaceAddressVO address, String name)
			throws Exception {
		// TODO Auto-generated method stub
		CommParamRequest treq = new CommParamRequest();
		treq.setJson(JsonPluginsUtil.beanToJson(vo));
		treq.setServiceName(name);
		BbangClient client = newClient(address);
		CommResultResponse resp = client.execute(treq);
		if (resp != null && StringUtils.isNotEmpty(resp.getResjson())) {
			GjOrderGetDetailResult results = JsonPluginsUtil.jsonToFastBean(
					resp.getResjson(), GjOrderGetDetailResult.class);
			if (results != null
					&& (results.getOrders() == null || results.getOrders()
							.size() == 0)) {
				throw new RuntimeException(results.getCode() + ";"
						+ results.getMsg());
			}
			return results;
		} else {
			if (resp != null) {
				OOLogUtil.info(JsonPluginsUtil.beanToJson(resp), AllApi.class,
						null);
				if (resp != null && !"1000".equals(resp.getMsgcode())) {
					throw new RuntimeException(resp.getMsgcode() + ";"
							+ resp.getMsg() + "," + resp.getBody());
				}
			}
		}
		return null;
	}

	/**
	 * 获取分销订单列表
	 * 
	 * @param vo
	 * @param address
	 * @param name
	 *            getDisOrderList@qua
	 * @return
	 * @throws Exception
	 */
	public GjOrderGetDetailResult getDistributorOrderDetails(
			GjOrderGetListDomain vo, InterfaceAddressVO address, String name)
			throws Exception {
		// TODO Auto-generated method stub
		CommParamRequest treq = new CommParamRequest();
		treq.setJson(JsonPluginsUtil.beanToJson(vo));
		treq.setServiceName(name);
		BbangClient client = newClient(address);
		CommResultResponse resp = client.execute(treq);
		if (resp != null && StringUtils.isNotEmpty(resp.getResjson())) {
			GjOrderGetDetailResult results = JsonPluginsUtil.jsonToFastBean(
					resp.getResjson(), GjOrderGetDetailResult.class);
			if (results != null && !"0".equals(results.getCode())) {
				throw new RuntimeException(results.getCode() + ";"
						+ results.getMsg());
			}
			return results;
		} else {
			if (resp != null) {
				OOLogUtil.info(JsonPluginsUtil.beanToJson(resp), AllApi.class,
						null);
				if (resp != null && !"1000".equals(resp.getMsgcode())) {
					throw new RuntimeException(resp.getMsgcode() + ";"
							+ resp.getMsg() + "," + resp.getBody());
				}
			}
		}
		return null;
	}

	/**
	 * 获取分销订单列表
	 * 
	 * @param vo
	 * @param address
	 * @param name
	 *            getDisOrderList@qua
	 * @return
	 * @throws Exception
	 */
	public GjOrderGetListResult getDistributorOrderList(
			GjOrderGetListDomain vo, InterfaceAddressVO address, String name)
			throws Exception {
		// TODO Auto-generated method stub
		CommParamRequest treq = new CommParamRequest();
		treq.setJson(JsonPluginsUtil.beanToJson(vo));
		treq.setServiceName(name);
		BbangClient client = newClient(address);
		CommResultResponse resp = client.execute(treq);
		if (resp != null && StringUtils.isNotEmpty(resp.getResjson())) {
			GjOrderGetListResult results = JsonPluginsUtil.jsonToFastBean(
					resp.getResjson(), GjOrderGetListResult.class);
			if (results != null && !"0".equals(results.getCode())) {
				throw new RuntimeException(results.getCode() + ";"
						+ results.getMsg());
			}
			return results;
		} else {
			if (resp != null) {
				OOLogUtil.info(JsonPluginsUtil.beanToJson(resp), AllApi.class,
						null);
				if (resp != null && !"1000".equals(resp.getMsgcode())) {
					throw new RuntimeException(resp.getMsgcode() + ";"
							+ resp.getMsg() + "," + resp.getBody());
				}
			}
		}
		return null;
	}

	private static BbangClient newClient(InterfaceAddressVO vo) {
		BbangClient client = new DefaultBbangClient("http://" + vo.getHost()
				+ "/" + vo.getServiceName() + "/services/" + vo.getClassName()
				+ "!" + vo.getMethodName() + ".do", "", "",
				com.bbang.api.Constants.FORMAT_JSON,
				com.bbang.api.Constants.REQUEST_PROTOCOL_BINARY_JSON);
		return client;
	}

	private static BbangClient newClient(String url) {
		BbangClient client = new DefaultBbangClient(url, "", "",
				com.bbang.api.Constants.FORMAT_JSON,
				com.bbang.api.Constants.REQUEST_PROTOCOL_BINARY_JSON);
		return client;
	}

	private String request(String data, String service, String url)
			throws Exception {
		OOLogUtil.info(data, AllApi.class, null);
		CommParamRequest treq = new CommParamRequest();
		treq.setJson(data);
		treq.setServiceName(service);
		BbangClient client = newClient(url);
		CommResultResponse resp = client.execute(treq);
		if (resp != null && StringUtils.isNotEmpty(resp.getResjson())) {
			OOLogUtil.info(resp.getResjson(), AllApi.class, null);
			return resp.getResjson();
		} else {
			if (resp != null) {
				OOLogUtil.info(JsonPluginsUtil.beanToJson(resp), AllApi.class,
						null);
				throw new RuntimeException(resp.getMsgcode() + ";"
						+ resp.getMsg());
			}
		}
		return null;
	}
}
