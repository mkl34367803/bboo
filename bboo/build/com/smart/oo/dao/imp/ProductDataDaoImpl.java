package com.smart.oo.dao.imp;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.smart.comm.entity.ProductDataEntity;
import com.smart.framework.base.BaseDAO;
import com.smart.oo.dao.IProductDataDao;
@Repository("ProductDataDaoImpl")
public class ProductDataDaoImpl extends BaseDAO<ProductDataEntity, String> implements IProductDataDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public boolean saveProductData(ProductDataEntity productDataEntity)
			throws Exception {
		String id=this.save(productDataEntity);
		if(id!=null){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public List<ProductDataEntity> getProductDataByOrderId(String orderId) throws Exception {
		String hql="select distinct pd from ProductDataEntity pd left join fetch pd.priceList left join fetch pd.tripList left join fetch pd.psgList where fkid='"+orderId+"'";
		return this.find(hql);
	}

	@Override
	public void deleteProductDataByOrderId(String orderId) throws Exception {
		int i=this.executeHql("delete from ProductDataEntity where fkid='"+orderId+"'");
		System.out.println(i);
	}
	
}
