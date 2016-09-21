package com.smart.oo.service;

import java.util.List;

import com.smart.comm.entity.FinanceFileEntity;
import com.smart.comm.entity.FinanceSaleEntity;
import com.smart.framework.base.Page;

public interface IFinanceSaleService {

	/**
	 * 统计利润
	 * @param fs
	 * @param fs2
	 * @return
	 * @throws Exception
	 */
	List<Object> statisticsProfit(FinanceSaleEntity fs, FinanceSaleEntity fs2, String[] xiaoShouProjos) throws Exception;// 根据fileNO统计利润

	public abstract void saveFinancesSale(FinanceSaleEntity fs)
			throws Exception;

	public abstract void saveFinancesSaleAndFinanceFile(
			List<FinanceSaleEntity> fsList, FinanceFileEntity ff)
			throws Exception;// 同一个事务下保存FinanceSaleEntity和FinanceFileEntity

	public abstract List<FinanceSaleEntity> findFinanceSaleByCondition(
			FinanceSaleEntity fs, Page p, FinanceSaleEntity fs2, String[] xiaoShouProjos)
			throws Exception;// 条件查询分页展示

	public List<FinanceSaleEntity> download(FinanceSaleEntity fs,
			FinanceSaleEntity fs2) throws Exception;// 下载

	public List<FinanceSaleEntity> getCount(FinanceSaleEntity fs,
			FinanceSaleEntity fs2, String[] xiaoShouProjos) throws Exception;// 获取总数量

	/**
	 * 通过订单号查询
	 * 
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
}
