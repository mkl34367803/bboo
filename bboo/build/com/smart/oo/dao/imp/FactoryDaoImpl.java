package com.smart.oo.dao.imp;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.smart.oo.dao.IAfterPolicyDao;
import com.smart.oo.dao.IAirFlightDao;
import com.smart.oo.dao.IAirPriceDao;
import com.smart.oo.dao.IBaseAccountDao;
import com.smart.oo.dao.IBaseContactsDao;
import com.smart.oo.dao.IBaseOfficeDao;
import com.smart.oo.dao.IBaseOptionDao;
import com.smart.oo.dao.IBaseRefundDao;
import com.smart.oo.dao.IBaseSelectDao;
import com.smart.oo.dao.IBookRuleDao;
import com.smart.oo.dao.ICabinMapDao;
import com.smart.oo.dao.ICabinRuleDao;
import com.smart.oo.dao.IFinanceFileDao;
import com.smart.oo.dao.IFinanceSaleDao;
import com.smart.oo.dao.IGjBuyFlightDao;
import com.smart.oo.dao.IGjBuyOrderDao;
import com.smart.oo.dao.IGjBuyPassengerDao;
import com.smart.oo.dao.IGjResourseDao;
import com.smart.oo.dao.IGjSaleFlightDao;
import com.smart.oo.dao.IGjSaleOrderDao;
import com.smart.oo.dao.IGjSalePassengerDao;
import com.smart.oo.dao.IKeyValDao;
import com.smart.oo.dao.IMerchantDao;
import com.smart.oo.dao.IOrderReportDao;
import com.smart.oo.dao.IOrderTrendsDao;
import com.smart.oo.dao.IProductDataDao;
import com.smart.oo.dao.IProductPriceDao;
import com.smart.oo.dao.IQuestionsDao;
import com.smart.oo.dao.IRefundDao;
import com.smart.oo.dao.IReportFormsDao;
import com.smart.oo.dao.IRoleDao;
import com.smart.oo.dao.IShareFlightDao;
import com.smart.oo.dao.ISwitchDao;
import com.smart.oo.dao.ISysLogDao;
import com.smart.oo.dao.ITicketFileDao;
import com.smart.oo.dao.ITicketStateDao;
import com.smart.oo.dao.IUserDao;

@Repository
public class FactoryDaoImpl {

	@Resource(name = "GjSaleOrderDaoImpl")
	private IGjSaleOrderDao saleOrderDao;

	@Resource(name = "GjSaleFlightDaoImpl")
	private IGjSaleFlightDao saleFlightDao;

	@Resource(name = "GjSalePassengerDaoImpl")
	private IGjSalePassengerDao salePassengerDao;

	@Resource(name = "GjBuyOrderDaoImpl")
	private IGjBuyOrderDao gjBuyOrderDao;

	@Resource(name = "GjBuyFlightDaoImpl")
	private IGjBuyFlightDao gjBuyFlightDao;

	@Resource(name = "GjBuyPassengerDaoImpl")
	private IGjBuyPassengerDao gjBuyPassengerDao;

	@Resource(name = "GTicketFileDaoImpl")
	private ITicketFileDao iticketFileDao;

	@Resource(name = "GTicketStateDaoImpl")
	private ITicketStateDao iticketStateDao;

	@Resource(name = "BaseAccountDaoImpl")
	private IBaseAccountDao baseAccountDao;

	@Resource(name = "GjResourseDaoImpl")
	private IGjResourseDao gjResourseDao;

	@Resource(name = "GKeyValDaoImpl")
	private IKeyValDao ikeyValDao;

	@Resource(name = "BBOOBaseOfficeDaoImpl")
	private IBaseOfficeDao ibaseOfficeDao;

	@Resource(name = "SysLogDaoImpl")
	private ISysLogDao sysLogDao;
	@Resource(name = "FinanceSaleDaoImpl")
	private IFinanceSaleDao financeSaleDao;

	@Resource(name = "FinanceFileDaoImpl")
	private IFinanceFileDao financeFileDao;

	@Resource(name = "UserDaoImpl")
	private IUserDao userDao;

	@Resource(name = "BaseOptionDaoImpl")
	private IBaseOptionDao baseOptionDao;

	@Resource(name = "BaseSelectDaoImpl")
	private IBaseSelectDao baseSelectDao;
	@Resource(name = "ReportFormsDaoImpl")
	private IReportFormsDao reportFormDao;

	@Resource(name = "MerchantDaoImpl")
	private IMerchantDao merchantDao;

	@Resource(name = "RoleDaoImpl")
	private IRoleDao roleDao;

	@Resource(name = "BBOOOrderReportDaoImpl")
	private IOrderReportDao orderReportDao;

	@Resource(name = "SwitchDaoImpl")
	private ISwitchDao switchDao;

	@Resource(name = "BBOOAirFlightDaoImpl")
	private IAirFlightDao airFlightDao;

	@Resource(name = "ProductDataDaoImpl")
	private IProductDataDao productDataDao;
	
	@Resource(name = "ProductPriceDaoImpl")
	private IProductPriceDao productPriceDao;
	
	@Resource(name = "BBOOAfterPolicyDaoImpl")
	private IAfterPolicyDao afterPolicyDao;
	
	@Resource(name = "BBOOShareFlightDaoImpl")
	private IShareFlightDao  shareFlightDao;
	
	@Resource(name = "BBOOCabinMapDaoImpl")
	private ICabinMapDao cabinMapDao;
	
	@Resource(name = "BBOOBaseRoleDaoImpl")
	private BaseRoleDaoImpl baseRoleDaoImpl;
	
	@Resource(name = "BBOOAirPriceDaoImpl")
	private IAirPriceDao airPriceDao;
	
	@Resource(name = "BBOOBookRuleDaoImpl")
	private IBookRuleDao bookRuleDao;
	
	@Resource(name = "BBOOBaseContactsDaoImpl")
	private IBaseContactsDao baseContactsDao;
	
	@Resource(name = "BBOORefundDaoImpl")
	private IRefundDao refundDao;
	
	@Resource(name = "BBOOBaseRefundDaoImpl")
	private IBaseRefundDao baseRefundDao;
	
	@Resource(name = "BBOOOrderTrendsDaoImpl")
	private IOrderTrendsDao orderTrendsDao;
	
	@Resource(name = "BBOOQuestionsDaoImpl")
	private IQuestionsDao questionsDao;
	
	@Resource(name = "BBOOCabinRuleDaoImpl")
	private ICabinRuleDao cabinRuleDao;

	public IOrderReportDao getOrderReportDao() {
		return orderReportDao;
	}

	public void setOrderReportDao(IOrderReportDao orderReportDao) {
		this.orderReportDao = orderReportDao;
	}

	public FactoryDaoImpl() {

	}

	public IRoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public IMerchantDao getMerchantDao() {
		return merchantDao;
	}

	public void setMerchantDao(IMerchantDao merchantDao) {
		this.merchantDao = merchantDao;
	}

	public IGjBuyOrderDao getGjBuyOrderDao() {
		return gjBuyOrderDao;
	}

	public void setGjBuyOrderDao(IGjBuyOrderDao gjBuyOrderDao) {
		this.gjBuyOrderDao = gjBuyOrderDao;
	}

	public IGjSaleOrderDao getSaleOrderDao() {
		return saleOrderDao;
	}

	public void setSaleOrderDao(IGjSaleOrderDao saleOrderDao) {
		this.saleOrderDao = saleOrderDao;
	}

	public IGjSaleFlightDao getSaleFlightDao() {
		return saleFlightDao;
	}

	public void setSaleFlightDao(IGjSaleFlightDao saleFlightDao) {
		this.saleFlightDao = saleFlightDao;
	}

	public IGjSalePassengerDao getSalePassengerDao() {
		return salePassengerDao;
	}

	public void setSalePassengerDao(IGjSalePassengerDao salePassengerDao) {
		this.salePassengerDao = salePassengerDao;
	}

	public ITicketFileDao getIticketFileDao() {
		return iticketFileDao;
	}

	public void setIticketFileDao(ITicketFileDao iticketFileDao) {
		this.iticketFileDao = iticketFileDao;
	}

	public ITicketStateDao getIticketStateDao() {
		return iticketStateDao;
	}

	public void setIticketStateDao(ITicketStateDao iticketStateDao) {
		this.iticketStateDao = iticketStateDao;
	}

	public IGjBuyFlightDao getGjBuyFlightDao() {
		return gjBuyFlightDao;
	}

	public void setGjBuyFlightDao(IGjBuyFlightDao gjBuyFlightDao) {
		this.gjBuyFlightDao = gjBuyFlightDao;
	}

	public IGjBuyPassengerDao getGjBuyPassengerDao() {
		return gjBuyPassengerDao;
	}

	public void setGjBuyPassengerDao(IGjBuyPassengerDao gjBuyPassengerDao) {
		this.gjBuyPassengerDao = gjBuyPassengerDao;
	}

	public IBaseAccountDao getBaseAccountDao() {
		return baseAccountDao;
	}

	public void setBaseAccountDao(IBaseAccountDao baseAccountDao) {
		this.baseAccountDao = baseAccountDao;
	}

	public IGjResourseDao getGjResourseDao() {
		return gjResourseDao;
	}

	public void setGjResourseDao(IGjResourseDao gjResourseDao) {
		this.gjResourseDao = gjResourseDao;
	}

	public IKeyValDao getIkeyValDao() {
		return ikeyValDao;
	}

	public void setIkeyValDao(IKeyValDao ikeyValDao) {
		this.ikeyValDao = ikeyValDao;
	}

	public IBaseOfficeDao getIbaseOfficeDao() {
		return ibaseOfficeDao;
	}

	public void setIbaseOfficeDao(IBaseOfficeDao ibaseOfficeDao) {
		this.ibaseOfficeDao = ibaseOfficeDao;
	}

	public ISysLogDao getSysLogDao() {
		return sysLogDao;
	}

	public void setSysLogDao(ISysLogDao sysLogDao) {
		this.sysLogDao = sysLogDao;
	}

	public IFinanceSaleDao getFinanceSaleDao() {
		return financeSaleDao;
	}

	public void setFinanceSaleDao(IFinanceSaleDao financeSaleDao) {
		this.financeSaleDao = financeSaleDao;
	}

	public IFinanceFileDao getFinanceFileDao() {
		return financeFileDao;
	}

	public void setFinanceFileDao(IFinanceFileDao financeFileDao) {
		this.financeFileDao = financeFileDao;
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public IBaseOptionDao getBaseOptionDao() {
		return baseOptionDao;
	}

	public void setBaseOptionDao(IBaseOptionDao baseOptionDao) {
		this.baseOptionDao = baseOptionDao;
	}

	public IBaseSelectDao getBaseSelectDao() {
		return baseSelectDao;
	}

	public IReportFormsDao getReportFormDao() {
		return reportFormDao;
	}

	public void setReportFormDao(IReportFormsDao reportFormDao) {
		this.reportFormDao = reportFormDao;
	}

	public void setBaseSelectDao(IBaseSelectDao baseSelectDao) {
		this.baseSelectDao = baseSelectDao;
	}

	public ISwitchDao getSwitchDao() {
		return switchDao;
	}

	public void setSwitchDao(ISwitchDao switchDao) {
		this.switchDao = switchDao;
	}

	public IAirFlightDao getAirFlightDao() {
		return airFlightDao;
	}

	public void setAirFlightDao(IAirFlightDao airFlightDao) {
		this.airFlightDao = airFlightDao;
	}

	public IProductDataDao getProductDataDao() {
		return productDataDao;
	}

	public void setProductDataDao(IProductDataDao productDataDao) {
		this.productDataDao = productDataDao;
	}

	public IProductPriceDao getProductPriceDao() {
		return productPriceDao;
	}

	public void setProductPriceDao(IProductPriceDao productPriceDao) {
		this.productPriceDao = productPriceDao;
	}

	public IAfterPolicyDao getAfterPolicyDao() {
		return afterPolicyDao;
	}

	public void setAfterPolicyDao(IAfterPolicyDao afterPolicyDao) {
		this.afterPolicyDao = afterPolicyDao;
	}

	public IShareFlightDao getShareFlightDao() {
		return shareFlightDao;
	}

	public void setShareFlightDao(IShareFlightDao shareFlightDao) {
		this.shareFlightDao = shareFlightDao;
	}

	public ICabinMapDao getCabinMapDao() {
		return cabinMapDao;
	}

	public void setCabinMapDao(ICabinMapDao cabinMapDao) {
		this.cabinMapDao = cabinMapDao;
	}

	public BaseRoleDaoImpl getBaseRoleDaoImpl() {
		return baseRoleDaoImpl;
	}

	public void setBaseRoleDaoImpl(BaseRoleDaoImpl baseRoleDaoImpl) {
		this.baseRoleDaoImpl = baseRoleDaoImpl;
	}

	public IAirPriceDao getAirPriceDao() {
		return airPriceDao;
	}

	public void setAirPriceDao(IAirPriceDao airPriceDao) {
		this.airPriceDao = airPriceDao;
	}

	public IBookRuleDao getBookRuleDao() {
		return bookRuleDao;
	}

	public void setBookRuleDao(IBookRuleDao bookRuleDao) {
		this.bookRuleDao = bookRuleDao;
	}

	public IBaseContactsDao getBaseContactsDao() {
		return baseContactsDao;
	}

	public void setBaseContactsDao(IBaseContactsDao baseContactsDao) {
		this.baseContactsDao = baseContactsDao;
	}

	public IRefundDao getRefundDao() {
		return refundDao;
	}

	public void setRefundDao(IRefundDao refundDao) {
		this.refundDao = refundDao;
	}

	public IBaseRefundDao getBaseRefundDao() {
		return baseRefundDao;
	}

	public void setBaseRefundDao(IBaseRefundDao baseRefundDao) {
		this.baseRefundDao = baseRefundDao;
	}

	public IOrderTrendsDao getOrderTrendsDao() {
		return orderTrendsDao;
	}

	public void setOrderTrendsDao(IOrderTrendsDao orderTrendsDao) {
		this.orderTrendsDao = orderTrendsDao;
	}

	public IQuestionsDao getQuestionsDao() {
		return questionsDao;
	}

	public void setQuestionsDao(IQuestionsDao questionsDao) {
		this.questionsDao = questionsDao;
	}

	public ICabinRuleDao getCabinRuleDao() {
		return cabinRuleDao;
	}

	public void setCabinRuleDao(ICabinRuleDao cabinRuleDao) {
		this.cabinRuleDao = cabinRuleDao;
	}

}
