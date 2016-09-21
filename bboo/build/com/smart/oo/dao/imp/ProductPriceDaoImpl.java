package com.smart.oo.dao.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.smart.comm.entity.ProductDataEntity;
import com.smart.comm.entity.ProductFlightEntity;
import com.smart.comm.entity.ProductPriceEntity;
import com.smart.framework.base.BaseDAO;
import com.smart.oo.dao.IProductPriceDao;
@Repository("ProductPriceDaoImpl")
public class ProductPriceDaoImpl extends BaseDAO<ProductPriceEntity, String> implements IProductPriceDao{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<ProductPriceEntity> getProductPriceByFkpids(String[] fkpids)
			throws Exception {
		String temp="";
		for(int i=0;i<fkpids.length;i++){
			temp=temp+fkpids[i]+",";
		}
		//注意这里的排序呢，用disPrice+1000*after
		String hql="from ProductPriceEntity where fkpid in("+temp.substring(0,temp.length()-1)+") order by disPrice-1000*after/100";
		return this.find(hql);
	}

	@Override
	public ProductPriceEntity getProductPriceById(String id) throws Exception {
		return this.findUnique("from ProductPriceEntity where id='"+id+"'");
	}

	@Override
	public List<ProductPriceEntity> getProductPriceByOrderId(String orderId) throws Exception {
		String hql="from ProductPriceEntity where product.fkid='"+orderId+"' order by disPrice-farePrice*after/100";
		return this.find(hql);
	}
	
	public List<ProductPriceEntity> getProductPriceByOrderIdBySQL(String orderId) throws Exception {
		String sql="SELECT  pp.[id]" 
			  +" ,pd.[product_source]"
			  +" ,pp.[after]"
		      +" ,pp.[base_rate]"
		      +" ,pp.[dis_price]"
		      +" ,pp.[fare_price]"
		      +" ,pp.[fixed_fee]"
		      +" ,pp.[fkpid]"
		      +" ,pp.[pay_price]"
		      +" ,pp.[price_type]"
		      +" ,pp.[promotion]"
		      +" ,pp.[rebates]"
		      +" ,pp.[tax]"
		      +" ,pp.[ticket_par]"
		      +" ,pp.[yj]"
		      +" ,pd.[off_no]"
		      +" ,pps.[air_no]"
		      +" ,pps.[bunk_no]"
		      +" ,pd.[remark]"
		      +" FROM t_product_price pp left join t_product_data pd on pd.id=pp.fkpid left join t_product_flight pps on pd.id=pps.fkpid  where pd.fkid='"+orderId+"' "
		      +" order by dis_price-fare_price*after/100";
		List<Object[]> objectsList=  this.findBySQL(sql);
		
		//下面开始封装sql返回的对象
		List<ProductPriceEntity> productPriceEntities=null;
		if(objectsList!=null&&objectsList.size()>0){
			productPriceEntities=new ArrayList<ProductPriceEntity>();
			Map<String,ProductPriceEntity> map=new HashMap<String,ProductPriceEntity>();
			for(Object[] objects:objectsList){
				ProductPriceEntity tempProductPriceEntity=map.get(objects[0]);
				if(tempProductPriceEntity==null){
					ProductPriceEntity productPriceEntity=new ProductPriceEntity();
					ProductDataEntity productDataEntity=new ProductDataEntity();
					ProductFlightEntity productFlightEntity=new ProductFlightEntity();
					if(objects[0]!=null){
						productPriceEntity.setId(objects[0].toString());
					}
					if(objects[1]!=null){
						productDataEntity.setProductSource(objects[1].toString());
					}
					if(objects[2]!=null){
						productPriceEntity.setAfter(Double.parseDouble(objects[2].toString()));
					}
					if(objects[3]!=null){
						productPriceEntity.setBaseRate(Double.parseDouble(objects[3].toString()));
					}
					if(objects[4]!=null){
						productPriceEntity.setDisPrice(Double.parseDouble(objects[4].toString()));
					}
					if(objects[5]!=null){
						productPriceEntity.setFarePrice(Double.parseDouble(objects[5].toString()));
					}
					if(objects[6]!=null){
						productPriceEntity.setFixedFee(Double.parseDouble(objects[6].toString()));
					}
					if(objects[7]!=null){
						productPriceEntity.setFkpid(objects[7].toString());
					}
					if(objects[8]!=null){
						productPriceEntity.setPayPrice(Double.parseDouble(objects[8].toString()));
					}
					if(objects[9]!=null){
						productPriceEntity.setPriceType(objects[9].toString());
					}
					if(objects[10]!=null){
						productPriceEntity.setPromotion(Double.parseDouble(objects[10].toString()));
					}
					if(objects[11]!=null){
						productPriceEntity.setRebates(Double.parseDouble(objects[11].toString()));
					}
					if(objects[12]!=null){
						productPriceEntity.setTax(Double.parseDouble(objects[12].toString()));
					}
					if(objects[13]!=null){
						productPriceEntity.setTicketPar(Double.parseDouble(objects[13].toString()));
					}
					if(objects[14]!=null){
						productPriceEntity.setYj(Double.parseDouble(objects[14].toString()));
					}
					if(objects[15]!=null){
						productDataEntity.setOffNo(objects[15].toString());
					}
					if(objects[16]!=null){
						productFlightEntity.setAirNo(objects[16].toString());
					}
					if(objects[17]!=null){
						productFlightEntity.setBunkNo(objects[17].toString());
					}
					if(objects[18]!=null){
						productDataEntity.setRemark(objects[18].toString());
					}
					productDataEntity.getTripList().add(productFlightEntity);
					productPriceEntity.setProduct(productDataEntity);
					map.put(productPriceEntity.getId(), productPriceEntity);
				}else{
					ProductDataEntity productDataEntity=tempProductPriceEntity.getProduct();
					ProductFlightEntity productFlightEntity=new ProductFlightEntity();
					if(objects[16]!=null){
						productFlightEntity.setAirNo(objects[16].toString());
					}
					if(objects[17]!=null){
						productFlightEntity.setBunkNo(objects[17].toString());
					}
					productDataEntity.getTripList().add(productFlightEntity);
				}
			}
			for(String id:map.keySet()){
				productPriceEntities.add(map.get(id));
			}
		}
		return productPriceEntities;
	}
	

}
