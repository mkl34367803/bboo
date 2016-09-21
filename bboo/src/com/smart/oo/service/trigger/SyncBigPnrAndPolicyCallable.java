package com.smart.oo.service.trigger;

import java.util.List;
import java.util.concurrent.Callable;

import com.smart.comm.entity.BaseAccountEntity;
import com.smart.comm.entity.GjSaleOrderEntity;
import com.smart.oo.domain.res.GetPnrInfoResult;
import com.smart.oo.domain.res.ProductRootResult;
import com.smart.oo.service.imp.FactoryServiceImpl;

public class SyncBigPnrAndPolicyCallable implements Callable<Boolean> {
	private FactoryServiceImpl factoryService;
	private BaseAccountEntity baseAccountEntity;
	private GjSaleOrderEntity gjSaleOrderEntity;
	

	public SyncBigPnrAndPolicyCallable(FactoryServiceImpl factoryService,BaseAccountEntity baseAccountEntity, GjSaleOrderEntity gjSaleOrderEntity) {
		super();
		this.factoryService = factoryService;
		this.baseAccountEntity = baseAccountEntity;
		this.gjSaleOrderEntity = gjSaleOrderEntity;
	}


	@Override
	public Boolean call() throws Exception {
		GetPnrInfoResult rtrs=factoryService.getAllApiService().getRtByThirdInterface(gjSaleOrderEntity, false);
		factoryService.getSaleOrderService().updatePnrTxtAndBigPnrByRtResult(gjSaleOrderEntity, rtrs);
		List<ProductRootResult> productRootResults= factoryService.getAllApiService().getProductDataByThirdAuthInterface(gjSaleOrderEntity, rtrs);
		boolean bool=factoryService.getProductDataService().insertProductAndUpdateOrderFields(gjSaleOrderEntity, productRootResults);
//		Boolean bool=factoryService.getSaleOrderService().updateBigpnrAndImportPolicy(baseAccountEntity, gjSaleOrderEntity);
		return bool;
	}

}
