package com.smart.oo.service;



import java.util.List;

import com.smart.base.exception.SqlException;
import com.smart.comm.entity.FinanceSaleEntity;

public interface ILiRunSaleBaoBiaoService {
 public abstract void parseUploadExcel(FinanceSaleEntity fs) throws SqlException;
 public abstract List<FinanceSaleEntity> getByCaiGouProjo(String caiGouProjo) throws SqlException ;//根据销售平台计算利润
 public abstract List<String> getAllCaiGouProjo() throws SqlException; //获取数据库中所有的采购平台信息
}
