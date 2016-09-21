package com.smart.oo.service;

import java.util.List;

import com.smart.comm.entity.FinanceSaleEntity;
import com.smart.comm.entity.ReportFormsEntity;
import com.smart.framework.base.Page;


public interface IReportFormsService {
	  void saveFieldName(ReportFormsEntity r) throws Exception; //保存
	  void updateFieldName(ReportFormsEntity r) throws Exception;//修改
	  List<ReportFormsEntity> findByPage(ReportFormsEntity r,Page p) throws Exception;//分页
	  ReportFormsEntity getById(ReportFormsEntity r) throws Exception; //根据id获取对象
	  List<ReportFormsEntity> getCount(ReportFormsEntity r) throws Exception;//获取总数量
	  List<ReportFormsEntity> getFieldName(ReportFormsEntity r) throws Exception;
	  List<FinanceSaleEntity> downloadChooseData(FinanceSaleEntity fse,ReportFormsEntity r) throws Exception;//选择数据下载
	  
	  /**
	    * 删除
	    * @param id
	    */
	   void deleteReportForms(String id, String mno) throws Exception;
	   
	   /**
	    * 查询
	    * @param reportFormsEntity
	    * @return
	    * @throws Exception
	    */
	   List<ReportFormsEntity> queryReportForms(ReportFormsEntity reportFormsEntity) throws Exception;
}
