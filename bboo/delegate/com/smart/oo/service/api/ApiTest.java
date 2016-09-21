package com.smart.oo.service.api;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.map.HashedMap;

import com.smart.oo.domain.AccountVO;
import com.smart.oo.domain.GetPnrInfoDomain;
import com.smart.oo.domain.ProductsDomain;
import com.smart.oo.domain.res.GetPnrInfoResult;
import com.smart.oo.domain.res.ProductRootResult;
import com.smart.utils.JsonPluginsUtil;

public class ApiTest {
	/**
	 * 获取政策信息
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		AllApi api = new AllApi();
		GetPnrInfoDomain pnrvo = new GetPnrInfoDomain();
		pnrvo.setAppKey("testapix");
		pnrvo.setPnr("HDH15X");
		pnrvo.setPat(1);
		pnrvo.setOffice("SZX632");
		pnrvo.setPnrType("N");
		pnrvo.setTermId(String.valueOf(System.currentTimeMillis()));
		GetPnrInfoResult rtrs = null;
		try {
			rtrs = api
					.rt(pnrvo,
							"http://211.154.142.162:4005/ots/services/interface-test!entrance.do",
							"gds_for_rt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (rtrs != null) {
			System.out.println(JsonPluginsUtil.beanToJson(rtrs));
			ProductsDomain pv = new ProductsDomain();
			pv.setPnrContent(rtrs.getTxt());
//			pv.setPnrContent("");
			List<AccountVO> accounts = new ArrayList<AccountVO>();
			AccountVO acc = new AccountVO();
			acc.setUserName("");
			acc.setSecret("");
//			acc.setAcctype("LOCALBSP");
			acc.setAcctype("CTRIP_O_SALE");
			acc.setAppkey("2a0c8695-9ea8-4905-be4e-d725bdc0e8a0");
			accounts.add(acc);
			pv.setAccounts(accounts);
			List<ProductRootResult> plist = null;
			try {
				plist = api
						.getPolicys(
								pv,
								"http://211.154.142.162:4005/ots/services/interface-test!entrance.do",
								"query_b2b_policys");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (plist != null) {
				System.out.println(JsonPluginsUtil.beanListToJson(plist));
			}
		}
	}
	
	
	/**
	 * 获取政策的测试方法
	 * @param args
	 */
	public static void main1(String[] args) {
		AllApi api = new AllApi();
		GetPnrInfoDomain pnrvo = new GetPnrInfoDomain();
		pnrvo.setAppKey("testapix");
		pnrvo.setPnr("HDH15X");
		pnrvo.setPat(1);
		pnrvo.setOffice("SZX632");
		pnrvo.setPnrType("N");
		pnrvo.setTermId(String.valueOf(System.currentTimeMillis()));
		GetPnrInfoResult rtrs = null;
		try {
			rtrs = api
					.rt(pnrvo,
							"http://211.154.142.162:4005/ots/services/interface-test!entrance.do",
							"gds_for_rt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (rtrs != null) {
			System.out.println(JsonPluginsUtil.beanToJson(rtrs));
			ProductsDomain pv = new ProductsDomain();
			pv.setPnrContent(rtrs.getTxt());
//			pv.setPnrContent("");
			List<AccountVO> accounts = new ArrayList<AccountVO>();
			AccountVO acc = new AccountVO();
			acc.setUserName("");
			acc.setSecret("");
			acc.setAcctype("LOCALBSP");
			acc.setAppkey("2a0c8695-9ea8-4905-be4e-d725bdc0e8a0");
			accounts.add(acc);
			pv.setAccounts(accounts);
			List<ProductRootResult> plist = null;
			try {
				plist = api
						.getPolicys(
								pv,
								"http://211.154.142.162:4005/ots/services/interface-test!entrance.do",
								"query_b2b_policys");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (plist != null) {
				System.out.println(JsonPluginsUtil.beanListToJson(plist));
			}
		}

	}
}
