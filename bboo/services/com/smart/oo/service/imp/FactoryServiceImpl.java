package com.smart.oo.service.imp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.smart.oo.service.IAfterPolicyService;
import com.smart.oo.service.IAirFlightService;
import com.smart.oo.service.IAirPriceService;
import com.smart.oo.service.IAllApiService;
import com.smart.oo.service.IBaseAccountService;
import com.smart.oo.service.IBaseContactsService;
import com.smart.oo.service.IBaseOfficeService;
import com.smart.oo.service.IBaseOptionService;
import com.smart.oo.service.IBaseRefundService;
import com.smart.oo.service.IBaseRoleService;
import com.smart.oo.service.IBaseSelectService;
import com.smart.oo.service.IBookRuleService;
import com.smart.oo.service.ICabinMapService;
import com.smart.oo.service.ICabinRuleService;
import com.smart.oo.service.IDropDataService;
import com.smart.oo.service.IDropFileService;
import com.smart.oo.service.IFinanceFileService;
import com.smart.oo.service.IFinanceSaleService;
import com.smart.oo.service.IGJBuyOrderService;
import com.smart.oo.service.IGjBuyFlightService;
import com.smart.oo.service.IGjBuyPassengerService;
import com.smart.oo.service.IGjResourceService;
import com.smart.oo.service.IGjSaleFlightService;
import com.smart.oo.service.IGjSaleOrderService;
import com.smart.oo.service.IGjSalePassengerService;
import com.smart.oo.service.IKeyValService;
import com.smart.oo.service.IMerchantService;
import com.smart.oo.service.IOrderReportService;
import com.smart.oo.service.IOrderStatisticService;
import com.smart.oo.service.IOrderTrendsService;
import com.smart.oo.service.IProductDataService;
import com.smart.oo.service.IProductPriceService;
import com.smart.oo.service.IQuestionsService;
import com.smart.oo.service.IRefundService;
import com.smart.oo.service.IReportFormsService;
import com.smart.oo.service.IRoleService;
import com.smart.oo.service.IShareFlightService;
import com.smart.oo.service.ISwitchService;
import com.smart.oo.service.ISysLogService;
import com.smart.oo.service.ITicketFileService;
import com.smart.oo.service.ITicketStateService;
import com.smart.oo.service.IUserService;

@Service("GFactoryServiceImpl")
public class FactoryServiceImpl {

	@Resource(name = "GjSaleOrderServiceImpl")
	private IGjSaleOrderService saleOrderService;

	@Resource(name = "GjSaleFlightServiceImpl")
	private IGjSaleFlightService saleFlightService;

	@Resource(name = "GjSalePassengerServiceImpl")
	private IGjSalePassengerService salePassengerService;

	@Resource(name = "GTicketFileServiceImpl")
	private ITicketFileService iticketFileService;

	@Resource(name = "GTicketStateServiceImpl")
	private ITicketStateService iticketStateService;

	@Resource(name = "GJBuyOrderServiceImpl")
	private IGJBuyOrderService gjBuyOrderService;

	@Resource(name = "GjBuyFlightServiceImpl")
	private IGjBuyFlightService gjBuyFlightService;

	@Resource(name = "GjBuyPassengerServiceImpl")
	private IGjBuyPassengerService gjBuyPassengerService;

	@Resource(name = "BaseAccountServiceImpl")
	private IBaseAccountService baseAccountService;

	@Resource(name = "GjResouceServiceImpl")
	private IGjResourceService gjResourceService;

	@Resource(name = "GKeyValServiceImpl")
	private IKeyValService ikeyValService;

	@Resource(name = "BBOOBaseOfficeServiceImpl")
	private IBaseOfficeService baseOfficeService;

	@Resource(name = "SysLogServiceImpl")
	private ISysLogService sysLogService;
	@Resource(name = "FinanceSaleServiceImpl")
	private IFinanceSaleService financeSaleService;

	@Resource(name = "FinanceFileServiceImpl")
	private IFinanceFileService financeFileService;
	@Resource(name = "ReportFormsServiceImpl")
	private IReportFormsService reportFormsService;

	@Resource(name = "UserServiceImpl")
	private IUserService userService;

	@Resource(name = "BaseOptionServiceImpl")
	private IBaseOptionService baseOptionService;

	@Resource(name = "BaseSelectServiceImpl")
	private IBaseSelectService baseSelectService;

	@Resource(name = "BBOOOrderReportServiceImpl")
	private IOrderReportService orderReportService;

	@Resource(name = "MerchantServiceImpl")
	private IMerchantService merchantService;

	@Resource(name = "RoleServiceImpl")
	private IRoleService roleService;

	@Resource(name = "SwitchServiceImpl")
	private ISwitchService switchService;

	@Resource(name = "BBOOAirFlightServiceImpl")
	private IAirFlightService airFlightService;

	@Resource(name = "ProductDataServiceImpl")
	private IProductDataService productDataService;

	@Resource(name = "ProductPriceServiceImpl")
	private IProductPriceService productPriceService;

	@Resource(name = "BBOOAfterPolicyServiceImpl")
	private IAfterPolicyService afterPolicyService;

	@Resource(name = "BBOOShareFlightServiceImpl")
	private IShareFlightService shareFlightService;
	
	@Resource(name = "BBOOCabinMapServiceImpl")
	private ICabinMapService cabinMapService;
	
	@Resource(name = "BBOOBaseRoleServiceImpl")
	private IBaseRoleService baseRoleService;
	
	@Resource(name = "BBOOAirPriceServiceImpl")
	private IAirPriceService airPriceService;
	
	@Resource(name = "BBOOBookRuleServiceImpl")
	private IBookRuleService bookRuleService;
	
	@Resource(name = "BBOOBaseContactsServiceImpl")
	private IBaseContactsService baseContactsService;
	
	@Resource(name = "BBOOOrderStatisticServiceImpl")
	private IOrderStatisticService orderStatisticService;
	
	@Resource(name = "AllApiServiceImpl")
	private IAllApiService allApiService;
	
	@Resource(name = "BBOORefundServiceImpl")
	private IRefundService refundService;
	
	@Resource(name = "BBOOBaseRefundServiceImpl")
	private IBaseRefundService baseRefundService;
	
	@Resource(name = "BBOOOrderTrendsServiceImpl")
	private IOrderTrendsService orderTrendsService;
	
	@Resource(name = "BBOOQuestionsServiceImpl")
	private IQuestionsService questionsService;
	
	@Resource(name = "GDropDataServiceImpl")
	private IDropDataService dropDataService;
	
	@Resource(name = "GDropFileServiceImpl")
	private IDropFileService dropFileService;
	
	@Resource(name = "BBOOCabinRuleServiceImpl")
	private ICabinRuleService cabinRuleService;

	public IGjSaleOrderService getSaleOrderService() {
		return saleOrderService;
	}

	public IRoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

	public IMerchantService getMerchantService() {
		return merchantService;
	}

	public void setMerchantService(IMerchantService merchantService) {
		this.merchantService = merchantService;
	}

	public void setSaleOrderService(IGjSaleOrderService saleOrderService) {
		this.saleOrderService = saleOrderService;
	}

	public IGjSaleFlightService getSaleFlightService() {
		return saleFlightService;
	}

	public void setSaleFlightService(IGjSaleFlightService saleFlightService) {
		this.saleFlightService = saleFlightService;
	}

	public IGjSalePassengerService getSalePassengerService() {
		return salePassengerService;
	}

	public void setSalePassengerService(
			IGjSalePassengerService salePassengerService) {
		this.salePassengerService = salePassengerService;
	}

	public ITicketFileService getIticketFileService() {
		return iticketFileService;
	}

	public void setIticketFileService(ITicketFileService iticketFileService) {
		this.iticketFileService = iticketFileService;
	}

	public ITicketStateService getIticketStateService() {
		return iticketStateService;
	}

	public void setIticketStateService(ITicketStateService iticketStateService) {
		this.iticketStateService = iticketStateService;
	}

	public IGJBuyOrderService getGjBuyOrderService() {
		return gjBuyOrderService;
	}

	public void setGjBuyOrderService(IGJBuyOrderService gjBuyOrderService) {
		this.gjBuyOrderService = gjBuyOrderService;
	}

	public IGjBuyFlightService getGjBuyFlightService() {
		return gjBuyFlightService;
	}

	public void setGjBuyFlightService(IGjBuyFlightService gjBuyFlightService) {
		this.gjBuyFlightService = gjBuyFlightService;
	}

	public IGjBuyPassengerService getGjBuyPassengerService() {
		return gjBuyPassengerService;
	}

	public void setGjBuyPassengerService(
			IGjBuyPassengerService gjBuyPassengerService) {
		this.gjBuyPassengerService = gjBuyPassengerService;
	}

	public IBaseAccountService getBaseAccountService() {
		return baseAccountService;
	}

	public void setBaseAccountService(IBaseAccountService baseAccountService) {
		this.baseAccountService = baseAccountService;
	}

	public IGjResourceService getGjResourceService() {
		return gjResourceService;
	}

	public void setGjResourceService(IGjResourceService gjResourceService) {
		this.gjResourceService = gjResourceService;
	}

	public IKeyValService getIkeyValService() {
		return ikeyValService;
	}

	public void setIkeyValService(IKeyValService ikeyValService) {
		this.ikeyValService = ikeyValService;
	}

	

	public IBaseOfficeService getBaseOfficeService() {
		return baseOfficeService;
	}

	public void setBaseOfficeService(IBaseOfficeService baseOfficeService) {
		this.baseOfficeService = baseOfficeService;
	}

	public ISysLogService getSysLogService() {
		return sysLogService;
	}

	public void setSysLogService(ISysLogService sysLogService) {
		this.sysLogService = sysLogService;
	}

	public IFinanceSaleService getFinanceSaleService() {
		return financeSaleService;
	}

	public void setFinanceSaleService(IFinanceSaleService financeSaleService) {
		this.financeSaleService = financeSaleService;
	}

	public IFinanceFileService getFinanceFileService() {
		return financeFileService;
	}

	public void setFinanceFileService(IFinanceFileService financeFileService) {
		this.financeFileService = financeFileService;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public IReportFormsService getReportFormsService() {
		return reportFormsService;
	}

	public void setReportFormsService(IReportFormsService reportFormsService) {
		this.reportFormsService = reportFormsService;
	}

	public IBaseOptionService getBaseOptionService() {
		return baseOptionService;
	}

	public void setBaseOptionService(IBaseOptionService baseOptionService) {
		this.baseOptionService = baseOptionService;
	}

	public IBaseSelectService getBaseSelectService() {
		return baseSelectService;
	}

	public void setBaseSelectService(IBaseSelectService baseSelectService) {
		this.baseSelectService = baseSelectService;
	}

	public IOrderReportService getOrderReportService() {
		return orderReportService;
	}

	public void setOrderReportService(IOrderReportService orderReportService) {
		this.orderReportService = orderReportService;
	}

	public ISwitchService getSwitchService() {
		return switchService;
	}

	public void setSwitchService(ISwitchService switchService) {
		this.switchService = switchService;
	}

	public IAirFlightService getAirFlightService() {
		return airFlightService;
	}

	public void setAirFlightService(IAirFlightService airFlightService) {
		this.airFlightService = airFlightService;
	}

	public IProductDataService getProductDataService() {
		return productDataService;
	}

	public void setProductDataService(IProductDataService productDataService) {
		this.productDataService = productDataService;
	}

	public IProductPriceService getProductPriceService() {
		return productPriceService;
	}

	public void setProductPriceService(IProductPriceService productPriceService) {
		this.productPriceService = productPriceService;
	}

	public IAfterPolicyService getAfterPolicyService() {
		return afterPolicyService;
	}

	public void setAfterPolicyService(IAfterPolicyService afterPolicyService) {
		this.afterPolicyService = afterPolicyService;
	}

	public IShareFlightService getShareFlightService() {
		return shareFlightService;
	}

	public void setShareFlightService(IShareFlightService shareFlightService) {
		this.shareFlightService = shareFlightService;
	}

	public ICabinMapService getCabinMapService() {
		return cabinMapService;
	}

	public void setCabinMapService(ICabinMapService cabinMapService) {
		this.cabinMapService = cabinMapService;
	}

	public IBaseRoleService getBaseRoleService() {
		return baseRoleService;
	}

	public void setBaseRoleService(IBaseRoleService baseRoleService) {
		this.baseRoleService = baseRoleService;
	}

	public IAirPriceService getAirPriceService() {
		return airPriceService;
	}

	public void setAirPriceService(IAirPriceService airPriceService) {
		this.airPriceService = airPriceService;
	}

	public IBookRuleService getBookRuleService() {
		return bookRuleService;
	}

	public void setBookRuleService(IBookRuleService bookRuleService) {
		this.bookRuleService = bookRuleService;
	}

	public IBaseContactsService getBaseContactsService() {
		return baseContactsService;
	}

	public void setBaseContactsService(IBaseContactsService baseContactsService) {
		this.baseContactsService = baseContactsService;
	}

	public IOrderStatisticService getOrderStatisticService() {
		return orderStatisticService;
	}

	public void setOrderStatisticService(
			IOrderStatisticService orderStatisticService) {
		this.orderStatisticService = orderStatisticService;
	}

	public IAllApiService getAllApiService() {
		return allApiService;
	}

	public void setAllApiService(IAllApiService allApiService) {
		this.allApiService = allApiService;
	}

	public IRefundService getRefundService() {
		return refundService;
	}

	public void setRefundService(IRefundService refundService) {
		this.refundService = refundService;
	}

	public IBaseRefundService getBaseRefundService() {
		return baseRefundService;
	}

	public void setBaseRefundService(IBaseRefundService baseRefundService) {
		this.baseRefundService = baseRefundService;
	}

	public IOrderTrendsService getOrderTrendsService() {
		return orderTrendsService;
	}

	public void setOrderTrendsService(IOrderTrendsService orderTrendsService) {
		this.orderTrendsService = orderTrendsService;
	}

	public IQuestionsService getQuestionsService() {
		return questionsService;
	}

	public void setQuestionsService(IQuestionsService questionsService) {
		this.questionsService = questionsService;
	}

	public IDropDataService getDropDataService() {
		return dropDataService;
	}

	public void setDropDataService(IDropDataService dropDataService) {
		this.dropDataService = dropDataService;
	}

	public IDropFileService getDropFileService() {
		return dropFileService;
	}

	public void setDropFileService(IDropFileService dropFileService) {
		this.dropFileService = dropFileService;
	}

	public ICabinRuleService getCabinRuleService() {
		return cabinRuleService;
	}

	public void setCabinRuleService(ICabinRuleService cabinRuleService) {
		this.cabinRuleService = cabinRuleService;
	}

}
