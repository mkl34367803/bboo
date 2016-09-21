package com.smart.oo.service;

import java.util.List;

import net.sf.json.JSONArray;

import com.smart.base.exception.SqlException;
import com.smart.comm.entity.BaseAccountEntity;
import com.smart.comm.entity.BaseRefundEntity;
import com.smart.comm.entity.GjSaleOrderEntity;
import com.smart.entity.User;
import com.smart.framework.base.Page;
import com.smart.oo.domain.res.GetPnrInfoResult;
import com.smart.oo.domain.res.GjSaleOrderVO;
import com.smart.oo.from.OrderSummaryVo;
import com.smart.oo.from.RefundVo;
import com.smart.oo.from.ReservePnrVo;
import com.smart.oo.from.SaleOrderParamVo;
import com.smart.oo.vo.OrderAndPassengerParamVo;
import com.smart.oo.vo.SaleOrderAndPassengerVo;

public interface IGjSaleOrderService{

	/**
	 * 根据订单号查询订单详情
	 * @param orderNum
	 * @return
	 */
	List<GjSaleOrderEntity> queryByOrderNo(String orderNo) throws Exception;
	/**
	 * 分页查询订单汇总信息
	 * @return
	 * @throws Exception
	 */
	public List<com.smart.oo.from.OrderSummaryVo> readOrderSummaryByPage(Page page,User user,com.smart.oo.from.SaleOrderParamVo saleOrderParamEntity) throws Exception;
	/**
	 * 分页查询订单汇总信息
	 * @return
	 * @throws Exception
	 */
	public List<GjSaleOrderEntity> readOrderSummaryByPage1(Page page,User user,com.smart.oo.from.SaleOrderParamVo saleOrderParamEntity) throws Exception;

	/**
	 * 添加   X
	 * @param entity
	 * @throws SqlException
	 */
	public void  saveorder(GjSaleOrderEntity orderentity)throws Exception;
	/**
	 *  查询   X
	 * @param order
	 * @param page
	 * @param user
	 * @return
	 * @throws SqlException
	 */
	List<GjSaleOrderEntity>orderlist(GjSaleOrderEntity order,Page page,User user)throws SqlException;
	

	/**
	 * 导入单个订单
	 * @param baseAccountEntity
	 * @param orderNo 订单号
	 * @param accountID
	 * @param isAutoImport 是否为自动导入,true:自动导入的订单，false:手动导入的订单
	 * @param isFirst 是否是第一次导入订单？true是，false，不是
	 * @return
	 * @throws Exception
	 */
	public List<GjSaleOrderEntity> importOrder(BaseAccountEntity baseAccountEntity,String orderNo,Boolean isAutoImport,Boolean isFirst) throws Exception;
	/**
	 * 通过id查询订单信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public GjSaleOrderEntity queryOrderByID(String id) throws Exception;
	
	/**
	 * 更新锁定人
	 * @param id 订单的id
	 * @param lockUser 锁定人
	 * @return
	 */
	public boolean updateLockUser(String id,String lockUser) throws Exception;

	/**
	 * 更新订单状态
	 * @param id
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public boolean updateSlfStatus(String id, String slfStatus) throws Exception;

	/**
	 * 拆分订单
	 * @return
	 */
	public boolean splitOrder(String id,String[] passengerIDs) throws Exception;
	
	/**
	 * 更新pnr
	 * @param id
	 * @param pnrCode
	 * @return
	 * @throws Exception
	 */
	public boolean updatePnrCode(String id, String pnrCode) throws Exception;
	
	/**
	 * 取消订单
	 * @param id 订单的id
	 * @param lockUser 锁定人
	 * @return
	 */
	public boolean cancelOrder(String id,String lockUser) throws Exception;
	
	/**
	 * 更新pnrNoTime以及留票备注。
	 * @param id 订单号
	 * @param pnrNoTime no位时间
	 * @param leaveRemark 留票备注
	 * @throws Exception
	 */
	public boolean updatePnrNoTime(String id,String pnrNoTime,String leaveRemark) throws Exception;
	
	/**
	 * 自动的方式，更新pnr先关信息
	 * @param baseAccountEntity
	 * @param gjSaleOrderEntity
	 * @throws Exception
	 */
	public void  updatePnrInfoByRtResult(BaseAccountEntity baseAccountEntity,GjSaleOrderEntity gjSaleOrderEntity) throws Exception;
	
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
	 * 更新pnr和票号
	 * @param orderId
	 * @param pnrCode
	 * @param jsonArray
	 * @param isUpdateInteface 是否去更新第三方接口
	 * @throws Exception
	 * 
	 */
	public boolean updateEticketAndPnr(String orderId, String pnrCode, JSONArray jsonArray) throws Exception;
	
	/**
	 * 查询卖出订单中的订单和乘机人组成的信息
	 * @return
	 * @throws Exception
	 */
	public List<SaleOrderAndPassengerVo> queryOrderAndPassengerByCreateTime(OrderAndPassengerParamVo orderAndPassengerParamVo)  throws Exception;
	
	/**
	 * (这个方法内部要区分国内还是国际订单)通过订单pnr，然后调rt指令接口，获取bigpnr和pnr文本信息，然后再调用政策信息，存储到本地数据库。
	 * @return
	 * @throws Exception
	 */
	public boolean updateBigpnrAndImportPolicy(BaseAccountEntity baseAccountEntity,
			GjSaleOrderEntity gjSaleOrderEntity) throws Exception;
	/**
	 * 通过id更改pnr，oldpnr，bigpnr,还有删除productData信息
	 * 传入的参数只要需要id，pnr。
	 * 注意如果pnr没有改变，该方法直接返回false
	 * @param orderId 订单id
	 * @param newPnr 修改后的pnr
	 * @return
	 * @throws Exception
	 */
	public boolean modifyPnr(String orderId,String newPnr) throws Exception;
	/**
	 * 预定编码
	 * @param reservePnrVo
	 * @return
	 * @throws Exception
	 */
	public boolean reservePnrCode(ReservePnrVo reservePnrVo) throws Exception;
	/**
	 * 给订单中航班添加舱位数信息，还有av信息。avtime
	 * 订单中的剩余舱位数，如果是多程的情况，按最小的舱位数计算
	 * @param gjSaleOrderEntity
	 * @throws Exception
	 */
	public void fillCabinNumber(GjSaleOrderEntity gjSaleOrderEntity) throws Exception;
	
	/**
	 * 查询退票订单
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<OrderSummaryVo> queryRefundOrders(RefundVo vo, Page page) throws Exception;
	
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
	 * 导单的时候自动预定编码
	 * @param baseAccountEntity
	 * @param gjSaleOrderEntity
	 * @throws Exception
	 */
	public void autoReservePnr(BaseAccountEntity baseAccountEntity,GjSaleOrderEntity gjSaleOrderEntity) throws Exception;
	
	
	public List<GjSaleOrderEntity> autoSplitOrder(GjSaleOrderEntity gjSaleOrderEntity) throws Exception;
	/**
	 * 通过rt指令返回的结果，更新订单中的pnrtxt信息，大编码，已经passenger表中的
	 * 实际出票航班号，舱位，价格等信息。
	 * @param gjSaleOrderEntity
	 * @param rtrs rt接口返回的信息
	 * @return
	 * @throws Exception
	 */
	public boolean updatePnrTxtAndBigPnrByRtResult(GjSaleOrderEntity gjSaleOrderEntity,GetPnrInfoResult rtrs) throws Exception;
	/**
	 * 将第三方返回来的订单信息，转化为本地订单的订单实体对象
	 * @param baseAccountEntity
	 * @param isAutoImport
	 * @param gjSaleOrderVO
	 * @return
	 * @throws Exception
	 */
	public List<GjSaleOrderEntity> convertSaleOrderVoToSaleOrderEntities(BaseAccountEntity baseAccountEntity, Boolean isAutoImport,GjSaleOrderVO gjSaleOrderVO) throws Exception;
}
