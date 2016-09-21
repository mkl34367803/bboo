package com.smart.oo.service.imp;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.smart.comm.entity.FinanceSaleEntity;
import com.smart.comm.entity.ReportFormsEntity;
import com.smart.framework.base.Page;
import com.smart.oo.dao.imp.FactoryDaoImpl;

import com.smart.oo.service.IReportFormsService;
@Service("ReportFormsServiceImpl")
public class ReportFormsServiceImpl implements IReportFormsService{
    @Autowired
    public FactoryDaoImpl daoFactory;
    @Override
	public List<ReportFormsEntity> getFieldName(ReportFormsEntity r)
			throws Exception {
		return this.daoFactory.getReportFormDao().getFieldName(r);
	}

	@Override
	public void saveFieldName(ReportFormsEntity r) throws Exception {
		this.daoFactory.getReportFormDao().saveFieldName(r);	
	}

	@Override
	public void updateFieldName(ReportFormsEntity r) throws Exception {
		this.daoFactory.getReportFormDao().updateFieldName(r);
	}

	@Override
	public List<ReportFormsEntity> findByPage(ReportFormsEntity r,Page p) throws Exception {
		// TODO Auto-generated method stub
		return this.daoFactory.getReportFormDao().findByPage(r,p);
	}

	@Override
	public ReportFormsEntity getById(ReportFormsEntity r) throws Exception {
		// TODO Auto-generated method stub
		return this.daoFactory.getReportFormDao().getById(r);
	}

	@Override
	public  List<ReportFormsEntity> getCount(ReportFormsEntity r) throws Exception {
		// TODO Auto-generated method stub
		return this.daoFactory.getReportFormDao().getCount(r);
	}


@Override
public List<FinanceSaleEntity> downloadChooseData(FinanceSaleEntity fse,ReportFormsEntity r) throws Exception {
	List<FinanceSaleEntity>  list=new ArrayList<FinanceSaleEntity>();
	List<FinanceSaleEntity> fseList=new ArrayList<FinanceSaleEntity>();
	List<ReportFormsEntity> rflist=new ArrayList<ReportFormsEntity>();
	FinanceSaleEntity  fEntity=null;
	if(r!=null&&fse!=null){
		 rflist=this.daoFactory.getReportFormDao().getFieldName(r);
		 fseList= this.daoFactory.getFinanceSaleDao().downloadChooseData(fse);
	}
	if(fseList.size()>0&&rflist.size()>0){
		  for(FinanceSaleEntity fs:fseList){
			    if(fs!=null){
			    	 fEntity=new FinanceSaleEntity();
			         for(ReportFormsEntity rf:rflist){
			        	     if("xiaoShouPojo".equals(rf.getFieldName())){
			        	    	    fEntity.setXiaoShouPojo(fs.getXiaoShouPojo()!=null?fs.getXiaoShouPojo():"");
							}				
							if("orderNO".equals(rf.getFieldName())){
								 fEntity.setOrderNO(fs.getOrderNO()!=null?fs.getOrderNO():"");
							}
							if("orderCreateDate".equals(rf.getFieldName())){
								 fEntity.setOrderCreateDate(fs.getOrderCreateDate()!=null?fs.getOrderCreateDate():"");		
							}					
							if("PNR".equals(rf.getFieldName())){
								 fEntity.setPNR(fs.getPNR()!=null?fs.getPNR():"");
							}
							if("chengJiRenName".equals(rf.getFieldName())){
								 fEntity.setChengJiRenName(fs.getChengJiRenName()!=null?fs.getChengJiRenName():"");			
							}
							if("fareNO".equals(rf.getFieldName())){
								 fEntity.setFareNO(fs.getFareNO()!=null?fs.getFareNO():"");									
							}
							if("chuPiaoDate".equals(rf.getFieldName())){
								 fEntity.setChuPiaoDate(fs.getChuPiaoDate()!=null?fs.getChuPiaoDate():"");
							}
							if("fightDate".equals(rf.getFieldName())){
								 fEntity.setFightDate(fs.getFightDate()!=null?fs.getFightDate():"");
							}
							if("travlTyle".equals(rf.getFieldName())){
								 fEntity.setTravlTyle(fs.getTravlTyle()!=null?fs.getTravlTyle():"");		
							}
							if("chengJiRenType".equals(rf.getFieldName())){
								 fEntity.setChengJiRenType(fs.getChengJiRenType()!=null?fs.getChengJiRenType():"");			
							}
							if("startCodeToAriveCode".equals(rf.getFieldName())){
								 fEntity.setStartCodeToAriveCode(fs.getStartCodeToAriveCode()!=null?fs.getStartCodeToAriveCode():"");	
							}
							if("chengYunRen".equals(rf.getFieldName())){
								 fEntity.setChengYunRen(fs.getChengYunRen()!=null?fs.getChengYunRen():"");	
							}
							if("fightNO".equals(rf.getFieldName())){
								 fEntity.setFightNO(fs.getFightNO()!=null?fs.getFightNO():"");
											
							}
							if("cabin".equals(rf.getFieldName())){
								 fEntity.setCabin(fs.getCabin()!=null?fs.getCabin():"");
							}
							if("chengRenPice".equals(rf.getFieldName())){
								 fEntity.setChengRenPice(fs.getChengRenPice()!=null?fs.getChengRenPice():0.00);	
							}
							if("chengRenJiJian".equals(rf.getFieldName())){
								 fEntity.setChengRenJiJian(fs.getChengRenJiJian()!=null?fs.getChengRenJiJian():0.00);		
							}
							if("chengRenRanYouShui".equals(rf.getFieldName())){
								 fEntity.setChengRenRanYouShui(fs.getChengRenRanYouShui()!=null?fs.getChengRenRanYouShui():0.00);	
							}
							if("kuaiDi".equals(rf.getFieldName())){
								 fEntity.setKuaiDi(fs.getKuaiDi()!=null?fs.getKuaiDi():0.00);
							}
							if("kuaiDiChengBen".equals(rf.getFieldName())){
								 fEntity.setKuaiDiChengBen(fs.getKuaiDiChengBen()!=null?fs.getKuaiDiChengBen():0.00);	
							}
							if("yongJin".equals(rf.getFieldName())){
								 fEntity.setYongJin(fs.getYongJin()!=null?fs.getYongJin():0.00);	
							}
							if("surePrice".equals(rf.getFieldName())){
								 fEntity.setSurePrice(fs.getSurePrice()!=null?fs.getSurePrice():0.00);		
							}
							if("shouKuaiDate".equals(rf.getFieldName())){
								 fEntity.setShouKuaiDate(fs.getShouKuaiDate()!=null?fs.getShouKuaiDate():"");
							}
							if("shouKuanAccount".equals(rf.getFieldName())){
								 fEntity.setShouKuanAccount(fs.getShouKuanAccount()!=null?fs.getShouKuanAccount():"");	
							}
							if("chuaPiaoRen".equals(rf.getFieldName())){
								 fEntity.setChuaPiaoRen(fs.getChuaPiaoRen()!=null?fs.getChuaPiaoRen():"");	
							}
							if("beiZhuFirst".equals(rf.getFieldName())){
								 fEntity.setBeiZhuFirst(fs.getBeiZhuFirst()!=null?fs.getBeiZhuFirst():"");
							}
							if("baoJiaTyle".equals(rf.getFieldName())){
								 fEntity.setBaoJiaTyle(fs.getBaoJiaTyle()!=null?fs.getBaoJiaTyle():"");	
							}
							if("orderStatus".equals(rf.getFieldName())){
								 fEntity.setOrderStatus(fs.getOrderStatus()!=null?fs.getOrderStatus():"");	
							}
							if("chuPiaoSelf".equals(rf.getFieldName())){
								 fEntity.setChuPiaoSelf(fs.getChuPiaoSelf()!=null?fs.getChuPiaoSelf():"");		
							}
							if("money".equals(rf.getFieldName())){
								 fEntity.setMoney(fs.getMoney()!=null?fs.getMoney():0.00);
							}
							if("price".equals(rf.getFieldName())){
								 fEntity.setPrice(fs.getPrice()!=null?fs.getPrice():0.00);		
							}
							if("caiGaoProjo".equals(rf.getFieldName())){
								 fEntity.setCaiGaoProjo(fs.getCaiGaoProjo()!=null?fs.getCaiGaoProjo():"");		
							}
							if("projoName".equals(rf.getFieldName())){
								 fEntity.setProjoName(fs.getProjoName()!=null?fs.getProjoName():"");		
							}
							if("csoShuJu".equals(rf.getFieldName())){
								 fEntity.setCsoShuJu(fs.getCsoShuJu()!=null?fs.getCsoShuJu():0.00);
							}
							if("houFan".equals(rf.getFieldName())){
								 fEntity.setHouFan(fs.getHouFan()!=null?fs.getHouFan():0.00);		
							}
							if("shiJiChengBen".equals(rf.getFieldName())){
								 fEntity.setShiJiChengBen(fs.getShiJiChengBen()!=null?fs.getShiJiChengBen():0.00);
							}
							if("liRun".equals(rf.getFieldName())){
								 fEntity.setLiRun(fs.getLiRun()!=null?fs.getLiRun():0.00);
							}
							if("beiZhuSecond".equals(rf.getFieldName())){
								 fEntity.setBeiZhuSecond(fs.getBeiZhuSecond()!=null?fs.getBeiZhuSecond():"");	
							}
							if("CSOBeiZhu".equals(rf.getFieldName())){
								 fEntity.setCSOBeiZhu(fs.getCSOBeiZhu()!=null?fs.getCSOBeiZhu():"");	
							}
							if("beiZhuLast".equals(rf.getFieldName())){
								 fEntity.setBeiZhuLast(fs.getBeiZhuLast()!=null?fs.getBeiZhuLast():"");
							}
							if("jiaoYiType".equals(rf.getFieldName())){
								 fEntity.setJiaoYiType(fs.getJiaoYiType()!=null?fs.getJiaoYiType():"");
											
							}
			         }
			         list.add(fEntity);
			    }
		  }
	}
	return list;
}

public static void main(String[] args) throws Exception{
	ApplicationContext ac =new  ClassPathXmlApplicationContext("applicationContext.xml");
	ReportFormsServiceImpl service=(ReportFormsServiceImpl) ac.getBean("ReportFormsServiceImpl");
	List<FinanceSaleEntity> list=new ArrayList<FinanceSaleEntity>();
	FinanceSaleEntity f=new FinanceSaleEntity();
	f.setMno("84e7d1aae13a7116");
	ReportFormsEntity r=new ReportFormsEntity();
	r.setMno("84e7d1aae13a7116");
	//r.setFieldName("orderNO");
	list=service.downloadChooseData(f, r);
	for(FinanceSaleEntity fs:list){
		System.out.println(fs);
	}
}

@Override
public void deleteReportForms(String id, String mno) throws Exception {
	this.daoFactory.getReportFormDao().deleteReportForms(id, mno);
}

@Override
public List<ReportFormsEntity> queryReportForms(
		ReportFormsEntity reportFormsEntity) throws Exception {
	return this.daoFactory.getReportFormDao().queryReportForms(reportFormsEntity);
}

}
