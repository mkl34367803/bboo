package com.smart.oo.service.imp;

import java.util.Date;
import java.util.List;

import org.apache.bcel.generic.NEW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.comm.entity.BaseAccountEntity;
import com.smart.comm.entity.GjSaleOrderEntity;
import com.smart.comm.entity.ProductDataEntity;
import com.smart.comm.entity.ProductPriceEntity;
import com.smart.comm.utils.BBOOConstants;
import com.smart.oo.dao.imp.FactoryDaoImpl;
import com.smart.oo.domain.res.GetPnrInfoResult;
import com.smart.oo.domain.res.ProductRootResult;
import com.smart.oo.service.IProductPriceService;
import com.smart.utils.DateUtils;
import com.smart.utils.StringUtils;

@Service("ProductPriceServiceImpl")
public class ProductPriceServiceImpl implements IProductPriceService {
	@Autowired
	private FactoryDaoImpl factoryDao;
	@Autowired
	private FactoryServiceImpl factoryService;

	@Override
	public List<ProductPriceEntity> getProductPriceByFkpids(String[] fkpids) throws Exception {
		return factoryDao.getProductPriceDao().getProductPriceByFkpids(fkpids);
	}

	@Override
	public List<ProductPriceEntity> obtainProductPriceByOrderId(String orderId,boolean isUsePnrTxtCache) throws Exception {
		GjSaleOrderEntity gjSaleOrderEntity = factoryDao.getSaleOrderDao().queryOrderByID(orderId);
		if (gjSaleOrderEntity != null) {
			// 是否缓存时间大于10分钟？
			if (StringUtils.isEmpty(gjSaleOrderEntity.getGettime())|| countDataInterval(DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"), gjSaleOrderEntity.getGettime())) {
				// 如果缓存时间大于十分钟
				return obtainProductByThirdAuthInterface(orderId,isUsePnrTxtCache);
			} else {
				// 如果缓存时间不大于10分钟
				List<ProductPriceEntity> list= factoryDao.getProductPriceDao().getProductPriceByOrderIdBySQL(orderId);
				if (list != null && list.size() > 0) {
					// 如果数据库中有政策信息
					return list;
				} else {
					// 如果数据库中没有政策信息，还是得从第三方接口获取运价信息
					return obtainProductByThirdAuthInterface(orderId,isUsePnrTxtCache);
				}
			}
		} else { //如果订单都没有查询到，直接不返回政策信息
			return null;
		}
	}
	
	/**
	 * 注：为什么这个方法会存在呢？因为，如果政策虽然是在10分钟内获取过，但是我还是想要重新获取一次政策信息的时候，这个方法就有作用了
	 */
	@Override
	public List<ProductPriceEntity> obtainProductByThirdAuthInterface(String orderId,boolean isUsePnrTxtCache) throws Exception {
		//需要调接口获取运价的情况
			GjSaleOrderEntity gjSaleOrderEntity=factoryService.getSaleOrderService().queryOrderByID(orderId);
			try {
				if(BBOOConstants.GjSaleOrderEntity_flightClass_N.equals(gjSaleOrderEntity.getFlightClass())){
					if (gjSaleOrderEntity.getPnrCode().length() > 6) {
						return null;// 如果既有成人又有儿童pnr，直接不查询运价。
					}
					GetPnrInfoResult rtrs = factoryService.getAllApiService().getRtByThirdInterface(gjSaleOrderEntity,isUsePnrTxtCache);
					List<ProductRootResult> productRootResults=factoryService.getAllApiService().getProductDataByThirdAuthInterface(gjSaleOrderEntity, rtrs);
					factoryService.getProductDataService().insertProductAndUpdateOrderFields(gjSaleOrderEntity,productRootResults);
				}else if(BBOOConstants.GjSaleOrderEntity_flightClass_I.equals(gjSaleOrderEntity.getFlightClass())){
					//这些写国际订单拉取政策的代码
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			//factoryService.getSaleOrderService().updateBigpnrAndImportPolicy(baseAccountEntity, gjSaleOrderEntity); //这里调这个方法是完全正确的。
			//重新查询一次
			return factoryDao.getProductPriceDao().getProductPriceByOrderIdBySQL(orderId);
	}


	/**
	 * 计算dateString1-dateString2是否大于10分钟 如果满足条件返回true 否则返回false
	 * 
	 * @param dateString1
	 * @param dateString2
	 * @return
	 */
	private boolean countDataInterval(String dateString1, String dateString2) {
		Date date1 = DateUtils.parseDate(dateString1, "yyyy-MM-dd HH:mm:ss");
		Date date2 = DateUtils.parseDate(dateString2, "yyyy-MM-dd HH:mm:ss");
		long interval = date1.getTime() - date2.getTime();
		if (interval > 10 * 60 * 1000) { //10分钟
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		String date2 = "2016-08-17 10:22:38";
		String date1=DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
		System.out.println(new ProductPriceServiceImpl().countDataInterval(date1,date2));

	}

	@Override
	public ProductPriceEntity getProductPriceById(String id) throws Exception {
		return factoryDao.getProductPriceDao().getProductPriceById(id);
	}


}
