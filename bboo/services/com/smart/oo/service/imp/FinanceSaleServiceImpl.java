package com.smart.oo.service.imp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smart.comm.entity.FinanceFileEntity;
import com.smart.comm.entity.FinanceSaleEntity;
import com.smart.comm.utils.StringUtilsc;
import com.smart.framework.base.Page;
import com.smart.oo.dao.imp.FactoryDaoImpl;
import com.smart.oo.service.IFinanceSaleService;

@Service("FinanceSaleServiceImpl")
@Transactional
public class FinanceSaleServiceImpl implements IFinanceSaleService{
	
	@Autowired
	private FactoryDaoImpl daoFactory;
	@Override
	public List<Object> statisticsProfit(FinanceSaleEntity fs, FinanceSaleEntity fs2, String[] xiaoShouProjos) throws Exception{//根据fileNO统计利润
		List<Object> list=new ArrayList<Object>();
		List<Integer> numList=new ArrayList<Integer>();
		List<Object> totalList=new ArrayList<Object>();	
		List<FinanceSaleEntity> 			 fslist=new ArrayList<FinanceSaleEntity>();
		List<Object[]> objList=this.daoFactory.getFinanceSaleDao().statisticsProfit(fs, fs2, xiaoShouProjos);
		FinanceSaleEntity f=null;
		Integer num=0;
		Integer totalNum=0;
		Double totoalShiShou=0.0;
		Double totoalChengBen=0.0;
		Double totoalLiRen=0.0;
		// num,cai_gao_projo,money,hou_fan,yong_jin,sure_price, kuai_di,kuai_di_cheng_ben 
		 for(Object[] obj:objList){
			num=(Integer)obj[0];
			f=new FinanceSaleEntity();
			f.setCaiGaoProjo((String)obj[1]);
			f.setMoney(StringUtilsc.formatDouble2((Double)obj[2]));
			f.setHouFan(StringUtilsc.formatDouble2((Double)obj[3]));
			f.setYongJin(StringUtilsc.formatDouble2((Double)obj[4]));
			f.setSurePrice(StringUtilsc.formatDouble2((Double)obj[5]));
			f.setKuaiDi(StringUtilsc.formatDouble2((Double)obj[6]));
			f.setKuaiDiChengBen(StringUtilsc.formatDouble2((Double)obj[7]));
			f.setShiJiChengBen(StringUtilsc.formatDouble2((Double)obj[8]));
			f.setLiRun(StringUtilsc.formatDouble2((Double)obj[9]));
			totalNum += num;
			totoalShiShou += f.getSurePrice();
			totoalChengBen += f.getShiJiChengBen();
			totoalLiRen += f.getLiRun();
			fslist.add(f);
			numList.add(num);
		 }
		 totalList.add(totalNum);
		 totalList.add(StringUtilsc.formatDoubleStr(totoalShiShou));
		 totalList.add(StringUtilsc.formatDoubleStr(totoalChengBen));		 
		 totalList.add(StringUtilsc.formatDoubleStr(totoalLiRen));
		 
		 list.add(fslist);
		 list.add(numList);
		 list.add(totalList);;
		return list;
	}

	@Override
	public void saveFinancesSaleAndFinanceFile(List<FinanceSaleEntity> fsList,FinanceFileEntity ff) throws Exception {
		if(ff!=null){
			this.daoFactory.getFinanceFileDao().saveUploadExcelFile(ff);
		}
		if(fsList.size()>0&&fsList!=null){
			Iterator<FinanceSaleEntity> it = fsList.iterator();
			while (it.hasNext()) {
				FinanceSaleEntity fs = it.next();
				this.daoFactory.getFinanceSaleDao().savePaserExcelDatas(fs);
				it.remove();
			}
		  }
	}

	@Override
	public void saveFinancesSale(FinanceSaleEntity fs) throws Exception {
		this.daoFactory.getFinanceSaleDao().savePaserExcelDatas(fs);
	}

	@Override
	public List<FinanceSaleEntity> findFinanceSaleByCondition(
			FinanceSaleEntity fs, Page p,FinanceSaleEntity fs2, String[] xiaoShouProjos) throws Exception {
		return this.daoFactory.getFinanceSaleDao().findFinanceSaleByCondition(fs, p, fs2, xiaoShouProjos);
	}

	@Override
	public List<FinanceSaleEntity> download(FinanceSaleEntity fs,
			FinanceSaleEntity fs2) throws Exception {
		// TODO Auto-generated method stub
		return this.daoFactory.getFinanceSaleDao().download(fs, fs2);
	}

	@Override
	public List<FinanceSaleEntity> getCount(FinanceSaleEntity fs,FinanceSaleEntity fs2, String[] xiaoShouProjos) throws Exception {
		// TODO Auto-generated method stub
		return this.daoFactory.getFinanceSaleDao().getCount(fs,fs2,xiaoShouProjos);
	}

	@Override
	public List<FinanceSaleEntity> queryByOrderNo(String orderNo, String mno)
			throws Exception {
		return this.daoFactory.getFinanceSaleDao().queryByOrderNo(orderNo, mno);
	}

	@Override
	public void updateFinanceSale(FinanceSaleEntity financeSaleEntity)
			throws Exception {
		this.daoFactory.getFinanceSaleDao().updateFinanceSale(financeSaleEntity);
	}

	@Override
	public List<String> queryCaiGouPojos(FinanceSaleEntity fs) throws Exception {
		return this.daoFactory.getFinanceSaleDao().queryCaiGouPojos(fs);
	}

	@Override
	public List<String> queryXiaoShouPojos(FinanceSaleEntity fs)
			throws Exception {
		return this.daoFactory.getFinanceSaleDao().queryXiaoShouPojos(fs);
	}

}



