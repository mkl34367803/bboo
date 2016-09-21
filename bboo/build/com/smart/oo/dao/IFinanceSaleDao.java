package com.smart.oo.dao;

import java.util.List;

import com.smart.base.exception.SqlException;
import com.smart.comm.entity.FinanceSaleEntity;
import com.smart.framework.base.Page;

public interface IFinanceSaleDao {
  public abstract void  savePaserExcelDatas(FinanceSaleEntity fs) throws Exception ;//向数据库导入数据
  
  /**
   * 根据采购平台统计利润
   * @param fs
   * @return
   * @throws SqlException
   */
  List<Object[]> statisticsProfit(FinanceSaleEntity fs, FinanceSaleEntity fs2, String[] xiaoShouProjos) throws Exception;//根据fileNO统计利润
  
  public abstract List<FinanceSaleEntity> findFinanceSaleByCondition(FinanceSaleEntity fs,Page p,FinanceSaleEntity fs2, String[] xiaoShouProjos) throws Exception;//条件查询分页展示
  public abstract List<FinanceSaleEntity> downloadChooseData(FinanceSaleEntity f) throws Exception;//根据选择的字段下载数据
  public List<FinanceSaleEntity> download(FinanceSaleEntity fs,FinanceSaleEntity fs2) throws Exception;//下载
  public List<FinanceSaleEntity> getCount(FinanceSaleEntity fs,FinanceSaleEntity fs2, String[] xiaoShouProjos)  throws Exception;//获取总数量
  
  /**
   * 通过文件号删除
   * @param fileno
   * @param mno
   * @throws Exception
   */
  void deleteByFileNo(String fileno, String mno) throws Exception;
  
  
  /**
   * 删除
   * @param id
   * @throws Exception
   */
  void deleteFinanceSale(String id, String mno) throws Exception;
  
  /**
   * 通过订单号查询
   * @param orderNo
   * @param mno
   * @return
   * @throws Exception
   */
  List<FinanceSaleEntity> queryByOrderNo(String orderNo, String mno) throws Exception;
  
  /**
   * 更新
   * @param financeSaleEntity
   * @throws Exception
   */
  void updateFinanceSale(FinanceSaleEntity financeSaleEntity) throws Exception;
  
  /**
   * 获取采购平台
   * @param fs
   * @return
   * @throws Exception
   */
  List<String> queryCaiGouPojos(FinanceSaleEntity fs) throws Exception;
  
  /**
   * 获取采购平台
   * @param fs
   * @return
   * @throws Exception
   */
  List<String> queryXiaoShouPojos(FinanceSaleEntity fs) throws Exception;
  
  /**
   * 根据销售平台获取利润
   * @param fs
   * @param fs2
   * @param caigouProjos
   * @return
   * @throws SqlException
   */
  List<Object> getProfitBySalePojo(FinanceSaleEntity fs, FinanceSaleEntity fs2, String[] caigouProjos) throws Exception;
  
  /**
   * 通过ID查询
   * @param id
   * @param mno
   * @return
   * @throws Exception
   */
  FinanceSaleEntity queryById(String id, String mno) throws Exception;
}
