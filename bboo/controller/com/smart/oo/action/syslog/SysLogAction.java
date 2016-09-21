package com.smart.oo.action.syslog;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.smart.comm.entity.SysLogEntity;
import com.smart.comm.utils.DateUtil;
import com.smart.comm.utils.OOLogUtil;
import com.smart.entity.User;
import com.smart.framework.base.BaseAction;
import com.smart.oo.action.order.GjOrderDetailAction;
import com.smart.oo.service.imp.FactoryServiceImpl;
import com.smart.utils.UniqId;

@Controller("SysLogAction")
public class SysLogAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Resource(name="GFactoryServiceImpl")
	private FactoryServiceImpl factoryService;
	
	/**
	 * 添加系统操作日志
	 * @param contents
	 * @throws Exception 
	 */
	public void addSysLog(User user, String visitip, String foreginKey, String logType, String contents) throws Exception{
			//首先获取商户号，几乎每个订单页面都要获取商户号
//			User user = (User) request.getSession().getAttribute(
//					"SESSION_KEY_CURRENT_USER");
			String mno = null;
			if (user!=null&&user.getMert() != null) {
				mno = user.getMert().getMno(); // 每个用户对应一个航司的商户号
			}
			// 获得客户端的ip地址
			//String visitip = request.getRemoteAddr();
			SysLogEntity sysLogEntity=new SysLogEntity();
			sysLogEntity.setId(UniqId.getInstance().getStrId());
			sysLogEntity.setMno(mno);
			sysLogEntity.setLogType(logType); //记录订单相关的日志
			sysLogEntity.setForeginKey(foreginKey);
			sysLogEntity.setContents(contents);
			sysLogEntity.setUserName(user.getName());//登录用户的名字(后来改成了name而不是登录名)
			sysLogEntity.setOperatime(DateUtil.DateToStr(new Date()));
			sysLogEntity.setVisitip(visitip);
			factoryService.getSysLogService().saveSysLog(sysLogEntity);
			info("日志插入成功");
	}
	
	/**
	 * 查询系统操作日志
	 * @throws Exception 
	 */
	public List<SysLogEntity> querySysLog(String foreginKey) throws Exception{
		List<SysLogEntity> sysLogEntities = new ArrayList<SysLogEntity>();
		sysLogEntities = factoryService.getSysLogService().querySysLogByForeginKey(foreginKey);
//		if(null != sysLogEntities && sysLogEntities.size() > 0){
//			request.setAttribute("sysLogEntities", sysLogEntities);
//		}
		return sysLogEntities;
	}
	
	public static void info(String msg){
		OOLogUtil.info(msg, GjOrderDetailAction.class, null);
	}

}
