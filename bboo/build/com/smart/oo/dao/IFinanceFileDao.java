package com.smart.oo.dao;

import java.util.List;

import com.smart.base.exception.SqlException;
import com.smart.comm.entity.FinanceFileEntity;
import com.smart.framework.base.Page;

public interface IFinanceFileDao {
   void saveUploadExcelFile(FinanceFileEntity ff) throws SqlException;
   List<FinanceFileEntity> findByPage(FinanceFileEntity ff, Page p, String endDate) throws SqlException;//分页
   
   /**
    * 通过id和mno删除
    * @param id
    * @param mno
    * @return
    * @throws SqlException
    */
   int deleteFile(String id, String mno) throws SqlException; //删除
   
   /**
    * 更新
    * @param fileEntity
    * @throws Exception
    */
   void updateFinanceFile(FinanceFileEntity fileEntity) throws Exception;
   
   /**
    * 查询
    * @param fileName
    * @param mno
    * @return
    * @throws SqlException
    */
   List<FinanceFileEntity> queryFiles(FinanceFileEntity fileEntity) throws Exception;
}
