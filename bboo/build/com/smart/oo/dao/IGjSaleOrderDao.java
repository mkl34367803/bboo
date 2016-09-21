package com.smart.oo.dao;

import java.util.List;

import com.smart.base.exception.SqlException;
import com.smart.comm.entity.BaseRefundEntity;
import com.smart.comm.entity.GjSaleOrderEntity;
import com.smart.entity.User;
import com.smart.framework.base.Page;
import com.smart.oo.from.OrderChartsVo;
import com.smart.oo.from.RefundVo;
import com.smart.oo.from.SaleOrderParamVo;
import com.smart.oo.vo.OrderAndPassengerParamVo;
import com.smart.oo.vo.SaleOrderAndPassengerVo;

public interface IGjSaleOrderDao {

	/**
	 * 根据订单号查询订单详情
	 * 
	 * @param orderNum
	 * @return
	 */
	public List<GjSaleOrderEntity> queryByOrderNo(String orderNo) throws Exception;

	/**
	 * 分页查询订单汇总信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Object[]> queryOrderSummaryByPage(Page page, User user, SaleOrderParamVo saleOrderParamEntity) throws Exception;
	/**
	 * 分页查询订单汇总信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Object[]> queryOrderSummaryByPage1(Page page, User user, SaleOrderParamVo saleOrderParamEntity) throws Exception;
	/**
	 * 查询 x
	 * 
	 * @param order
	 * @param page
	 * @param user
	 * @return
	 * @throws SqlException
	 */
	public List<GjSaleOrderEntity> orderlist(GjSaleOrderEntity order, Page page, User user) throws SqlException;
	/**
	 * 查询订单总条数
	 * 
	 * @param user
	 * @param saleOrderParamEntity
	 * @return
	 * @throws Exception
	 */
	public Integer queryOrderTotal(User user, SaleOrderParamVo saleOrderParamEntity) throws Exception;

	/**
	 * 通过id查询订单信息
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public GjSaleOrderEntity queryOrderByID(String id) throws Exception;

	/**
	 * 更新锁定人
	 * 
	 * @param id
	 *            订单的id
	 * @param lockUser
	 *            锁定人
	 * @return
	 */
	public boolean updateLockUser(String id, String lockUser) throws Exception;

	/**
	 * 更新订单状态
	 * 
	 * @param id
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public boolean updateSlfStatus(String id, String slfStatus) throws Exception;

	/**
	 * 更新卖出订单
	 * 
	 * @param gjSaleOrderEntity
	 * @throws Exception
	 */
	public void update(GjSaleOrderEntity gjSaleOrderEntity) throws Exception;
	/**
	 * 保存订单
	 * 
	 * @return 实体的主键
	 * @throws Exception
	 */
	public String saveOrder(GjSaleOrderEntity gjSaleOrderEntity) throws Exception;
	/**
	 * 更新pnr
	 * 
	 * @param id
	 * @param pnrCode
	 * @return
	 * @throws Exception
	 */
	public boolean updatePnrCode(String id, String pnrCode) throws Exception;

	/**
	 * 取消订单
	 * 
	 * @param id
	 *            订单的id
	 * @param lockUser
	 *            锁定人
	 * @return
	 */
	public boolean cancelOrder(String id, String lockUser) throws Exception;

	/**
	 * 更新pnrNoTime以及留票备注。
	 * 
	 * @param id
	 *            订单号
	 * @param pnrNoTime
	 *            no位时间
	 * @param leaveRemark
	 *            留票备注
	 * @throws Exception
	 */
	public boolean updatePnrNoTime(String id, String pnrNoTime, String leaveRemark) throws Exception;
	/**
	 * 更新订单状态
	 * 
	 * @param id
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public boolean updateStatus(String id, String status) throws Exception;
	/**
	 * 通过创单时间来查询卖出订单。
	 * @return 买入订单list
	 * @throws Exception
	 */
	public List<GjSaleOrderEntity> queryByCreateTime(String startTime,String endTime) throws Exception;
	/**
	 * 查询卖出订单中的订单和乘机人组成的信息
	 * @return
	 * @throws Exception
	 */
	public List<SaleOrderAndPassengerVo> queryOrderAndPassengerByCreateTime(OrderAndPassengerParamVo orderAndPassengerParamVo)  throws Exception;
	/**
	 * 写一个通用的方法（通过id更新各种字段，以后就不用单独写更新的方法了）
	 * @param gjSaleOrderEntity
	 * @return
	 * @throws Exception
	 */
	public boolean updateByID(GjSaleOrderEntity gjSaleOrderEntity) throws Exception;
	
	
	/**
	 * 以航空公司分组，统计在各个航空公司的利润
	 * @return
	 * @throws Exception
	 */
	public  List<Object[]> countProfitByAirline(OrderChartsVo vo) throws Exception;
	
	/**
	 * 查询退票订单
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<Object[]> queryRefundOrders(RefundVo vo, Page page) throws Exception;
	
	/**
	 * 查询退票订单总数量
	 * @param vo
	 * @param entity
	 * @param page
	 * @return
	 * @throws Exception
	 */
	Integer countRefundOrders(SaleOrderParamVo vo, BaseRefundEntity entity) throws Exception;
	
	/**
	 * 分页查询订单（每页20条数据）
	 * @param page
	 * @param user
	 * @param saleOrderParamEntity
	 * @return
	 * @throws Exception
	 */
	public List<GjSaleOrderEntity> queryOrderSummaryByPage2(Page page, User user,
			SaleOrderParamVo saleOrderParamEntity) throws Exception;
	public void deleteById(String id) throws Exception;

}
