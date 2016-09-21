package com.smart.oo.service.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.smart.comm.entity.AfterPolicyEntity;
import com.smart.comm.entity.BaseAccountEntity;
import com.smart.comm.entity.GjSaleFlightEntity;
import com.smart.comm.entity.GjSaleOrderEntity;
import com.smart.comm.entity.GjSalePassengerEntity;
import com.smart.comm.entity.KeyValEntity;
import com.smart.comm.entity.ProductDataEntity;
import com.smart.comm.entity.ProductFlightEntity;
import com.smart.comm.entity.ProductPassengerEntity;
import com.smart.comm.entity.ProductPriceEntity;
import com.smart.comm.utils.BBOOConstants;
import com.smart.comm.utils.OOLogUtil;
import com.smart.oo.dao.imp.FactoryDaoImpl;
import com.smart.oo.domain.AccountVO;
import com.smart.oo.domain.FlightLegsVO;
import com.smart.oo.domain.FreightPriceVO;
import com.smart.oo.domain.ProductsDomain;
import com.smart.oo.domain.TakePeopleVO;
import com.smart.oo.domain.res.AirPriceVO;
import com.smart.oo.domain.res.AirPsgVO;
import com.smart.oo.domain.res.AirTripVO;
import com.smart.oo.domain.res.GetPnrInfoResult;
import com.smart.oo.domain.res.ProductElementVO;
import com.smart.oo.domain.res.ProductRootResult;
import com.smart.oo.domain.res.RtPnrPriceVO;
import com.smart.oo.service.IProductDataService;
import com.smart.oo.service.api.AllApi;
import com.smart.utils.DateUtils;
import com.smart.utils.JsonPluginsUtil;
import com.smart.utils.UniqId;

@Service("ProductDataServiceImpl")
public class ProductDataServiceImpl implements IProductDataService {
	@Autowired
	private FactoryDaoImpl factoryDao;
	@Autowired
	private FactoryServiceImpl factoryService;

	@Override
	public List<ProductDataEntity> getProductDataByOrderId(String orderId) throws Exception {
		return factoryDao.getProductDataDao().getProductDataByOrderId(orderId);
	}

	@Override
	public void deleteProductDataByOrderId(String orderId) throws Exception {
		// TODO Auto-generated method stub
		factoryDao.getProductDataDao().deleteProductDataByOrderId(orderId);
	}



	@Override
	public boolean insertPolicyToDB(List<ProductRootResult> productRootResults, GjSaleOrderEntity gjSaleOrderEntity) throws Exception {
		if (productRootResults == null || productRootResults.size() < 1) {
			return false; // 如果没有政策信息，不做插入操作
		}
		//查询后返列表，通过商户去查询
		Map<String,List<AfterPolicyEntity>> map = factoryService.getAfterPolicyService().findDataMap(gjSaleOrderEntity.getMno(), true);
		List<AfterPolicyEntity> list=null;
		if(map!=null){
			list=map.get(gjSaleOrderEntity.getMno());//获取后返list
		}
		for (ProductRootResult productRootResult : productRootResults) {//productRootResults代表不同平台的政策
			List<ProductElementVO> productElementVOs = productRootResult.getRlts();// 从一个平台获取的政策信息,有不同的价格？
			if (productElementVOs != null && productElementVOs.size() > 0) {
				for (ProductElementVO productElementVO : productElementVOs) {
					// 这里开始封装，将政策信息主体存入到数据库
					ProductDataEntity productDataEntity = new ProductDataEntity();
					BeanUtils.copyProperties(productElementVO, productDataEntity, new String[] { "priceList", "tripList", "psgList", "isChangePnr" });
					if (productElementVO.getIsChangePnr()) {
						// TRUE 换编码 FASLE 原编码// 1 换编码 2 原编码
						productDataEntity.setIsChangePnr(new Integer(1));
					} else {
						// TRUE 换编码 FASLE 原编码// 1 换编码 2 原编码
						productDataEntity.setIsChangePnr(new Integer(2));
					}
					String productDataId = UniqId.getInstance().getStrId();
					productDataEntity.setId(productDataId);
					productDataEntity.setFkid(gjSaleOrderEntity.getId());
					productDataEntity.setCreateTime(DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
					List<AirPriceVO> airPriceVOs = productElementVO.getPriceList();
					Set<ProductPriceEntity> productPriceEntities = null;
					if (airPriceVOs != null && airPriceVOs.size() > 0) {
						productPriceEntities = new HashSet<ProductPriceEntity>();
						for (AirPriceVO airPriceVO : airPriceVOs) {
							ProductPriceEntity productPriceEntity = new ProductPriceEntity();
							BeanUtils.copyProperties(airPriceVO, productPriceEntity);
							//这里加一个增加后返的东西
							addAfterPolicyForProduct(list, productElementVO, airPriceVO, productPriceEntity);
							String id = UniqId.getInstance().getStrId();
							productPriceEntity.setId(id);
							productPriceEntity.setProduct(productDataEntity);
							productPriceEntities.add(productPriceEntity);
						}
					}
					// 保存passenger信息
					List<AirPsgVO> airPsgVOs = productElementVO.getPsgList();
					Set<ProductPassengerEntity> productPassengerEntities = null;
					if (airPsgVOs != null && airPriceVOs.size() > 0) {
						productPassengerEntities = new HashSet<ProductPassengerEntity>();
						for (AirPsgVO airPsgVO : airPsgVOs) {
							ProductPassengerEntity productPassengerEntity = new ProductPassengerEntity();
							BeanUtils.copyProperties(airPsgVO, productPassengerEntity);
							String id = UniqId.getInstance().getStrId();
							productPassengerEntity.setId(id);
							productPassengerEntity.setProduct(productDataEntity);
							productPassengerEntities.add(productPassengerEntity);
						}
					}
					List<AirTripVO> airTripVOs = productElementVO.getTripList();
					Set<ProductFlightEntity> productFlightEntities = null;
					if (airTripVOs != null && airTripVOs.size() > 0) {
						productFlightEntities = new HashSet<ProductFlightEntity>();
						for (AirTripVO airTripVO : airTripVOs) {
							ProductFlightEntity productFlightEntity = new ProductFlightEntity();
							BeanUtils.copyProperties(airTripVO, productFlightEntity);
							String id = UniqId.getInstance().getStrId();
							productFlightEntity.setId(id);
							productFlightEntity.setProduct(productDataEntity);
							productFlightEntities.add(productFlightEntity);
						}
					}

					productDataEntity.setPriceList(productPriceEntities);
					productDataEntity.setTripList(productFlightEntities);
					productDataEntity.setPsgList(productPassengerEntities);
					// 调用dao层的保存方法
					factoryDao.getProductDataDao().saveProductData(productDataEntity);
				}
				
			}
		}
		return true;
	}
	
	@Override
	public boolean insertProductAndUpdateOrderFields(
			GjSaleOrderEntity gjSaleOrderEntity,List<ProductRootResult> productRootResults)
			throws Exception {
		if(gjSaleOrderEntity!=null&&productRootResults!=null&&productRootResults.size()>0){
			//查询到了新的政策信息，删除原来的政策信息
			factoryService.getProductDataService().deleteProductDataByOrderId(gjSaleOrderEntity.getId());
			Boolean bool=factoryService.getProductDataService().insertPolicyToDB(productRootResults, gjSaleOrderEntity);
			if(bool){
				GjSaleOrderEntity paramVo = new GjSaleOrderEntity();
				paramVo.setId(gjSaleOrderEntity.getId());
				paramVo.setGettime(DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
				paramVo.setIsGet(BBOOConstants.GjSaleOrderEntity_isGet_one);
				factoryDao.getSaleOrderDao().updateByID(paramVo);
				return true;
			}
		}else{
			GjSaleOrderEntity paramVo = new GjSaleOrderEntity();
			paramVo.setId(gjSaleOrderEntity.getId());
			paramVo.setIsAuto(BBOOConstants.GjSaleOrderEntity_isAuto_two);
			//将是否自动出票设置为手动出票
			factoryDao.getSaleOrderDao().updateByID(paramVo);
		}
		return false;
	}
	/**
	 * 往政策实体中添加后返
	 * @param list 某个商户对应的后返list
	 * @param productElementVO 通过第三方接口查询到的政策信息
	 * @param airPriceVO 通过第三方接口查询到的政策下的运价信息
	 * @param productPriceEntity 本地用来存放政策的实体
	 */
	private void addAfterPolicyForProduct(List<AfterPolicyEntity> list,ProductElementVO productElementVO,AirPriceVO airPriceVO,ProductPriceEntity productPriceEntity){
		if(list==null||list.size()<1){
			return;
		}
		for (AfterPolicyEntity afterPolicyEntity : list) {
			boolean bool=StringUtils.isNotEmpty(afterPolicyEntity.getChannelCh());
			if (bool&&afterPolicyEntity.getChannelCh().length()>2&&afterPolicyEntity.getChannelCh().substring(3, afterPolicyEntity.getChannelCh().length()).equalsIgnoreCase(productElementVO.getProductSource())) {
				if (StringUtils.isNotEmpty(afterPolicyEntity.getProductType())&&afterPolicyEntity.getProductType().equals(airPriceVO.getPriceType())) {
					if ( StringUtils.isNotEmpty(afterPolicyEntity.getCarrier())&&StringUtils.isNotEmpty(productElementVO.getTripList().get(0).getAirNo())&&afterPolicyEntity.getCarrier().equals(productElementVO.getTripList().get(0).getAirNo().substring(0, 2))) {
						productPriceEntity.setAfter(afterPolicyEntity.getAfter());// 设置后返
						return;
					}
				}
				if (StringUtils.isNotEmpty(afterPolicyEntity.getProductType())&&afterPolicyEntity.getProductType().equals(airPriceVO.getPriceType())) {
					if (StringUtils.isEmpty(afterPolicyEntity.getCarrier())) {
						productPriceEntity.setAfter(afterPolicyEntity.getAfter());// 设置后返
						return;
					}
				}
				if (StringUtils.isEmpty(afterPolicyEntity.getProductType())) {
					if ( StringUtils.isNotEmpty(afterPolicyEntity.getCarrier())&&StringUtils.isNotEmpty(productElementVO.getTripList().get(0).getAirNo())&&afterPolicyEntity.getCarrier().equals(productElementVO.getTripList().get(0).getAirNo().substring(0, 2))) {
						productPriceEntity.setAfter(afterPolicyEntity.getAfter());// 设置后返
						return;
					}
				}
				if (StringUtils.isEmpty(afterPolicyEntity.getProductType()) ) {
					if (StringUtils.isEmpty(afterPolicyEntity.getCarrier())) {
						productPriceEntity.setAfter(afterPolicyEntity.getAfter());// 设置后返
						return;
					}
				}
			}
		}
	}


}
