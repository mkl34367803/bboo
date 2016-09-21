package com.smart.oo.service;

import java.util.List;

import com.smart.base.exception.SqlException;
import com.smart.comm.entity.FinanceFileEntity;
import com.smart.framework.base.Page;

public interface IFinanceFileService {
	   List<FinanceFileEntity> findByPage(FinanceFileEntity ff, Page p, String endDate) throws SqlException;//分页
	   
	   /**
	    * 删除FinanceFileEntity和FinanceSaleEntity表中的相关数据
	    * @param f
	    * @throws SqlException
	    */
	   void deleteFile(FinanceFileEntity f) throws Exception; //删除
	   
	   /**
	    * 查询
	    * @param fileName
	    * @param mno
	    * @return
	    * @throws SqlException
	    */
	   List<FinanceFileEntity> queryFiles(FinanceFileEntity financeFile) throws Exception;
	   
	   /**
	    * 保存
	    * @param ff
	    * @throws SqlException
	    */
	   void saveUploadExcelFile(FinanceFileEntity ff) throws SqlException;
	   
	   /**
	    * 更新
	    * @param fileEntity
	    * @throws Exception
	    */
	   void updateFinanceFile(FinanceFileEntity fileEntity) throws Exception;
	   
	   
}
