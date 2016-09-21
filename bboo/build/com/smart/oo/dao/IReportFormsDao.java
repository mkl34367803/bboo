package com.smart.oo.dao;

import java.util.List;

import com.smart.base.exception.SqlException;
import com.smart.comm.entity.ReportFormsEntity;
import com.smart.framework.base.Page;

public interface IReportFormsDao {
   public abstract  List<ReportFormsEntity> getFieldName(ReportFormsEntity r) throws SqlException;
   void saveFieldName(ReportFormsEntity r) throws SqlException; //保存
   void updateFieldName(ReportFormsEntity r) throws SqlException;//修改
   List<ReportFormsEntity> findByPage(ReportFormsEntity r,Page p) throws SqlException;//分页
   ReportFormsEntity getById(ReportFormsEntity r) throws SqlException; //根据id获取对象
   List<ReportFormsEntity>  getCount(ReportFormsEntity r) throws SqlException;//获取总数量
   
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
