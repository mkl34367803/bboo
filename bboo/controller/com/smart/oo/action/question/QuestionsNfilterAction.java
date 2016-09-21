package com.smart.oo.action.question;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.smart.comm.entity.QuestionsEntity;
import com.smart.entity.User;
import com.smart.framework.base.BaseAction;
import com.smart.framework.base.Page;
import com.smart.framework.security.SecurityContext;
import com.smart.oo.service.imp.FactoryServiceImpl;
import com.smart.timer.base.DateFormat;
import com.smart.utils.StringUtils;

public class QuestionsNfilterAction extends BaseAction {

	private static final long serialVersionUID = -6088756120980719090L;
	
	@Autowired
	private FactoryServiceImpl factoryService;

	public void queryByPage() {
		try {
			String QType = request.getParameter("QType");
			String classify = request.getParameter("classify");
			String intoPerson = request.getParameter("intoPerson");
			String isuse = request.getParameter("isuse");
			String startPage = request.getParameter("startPage");
			String pageSize = request.getParameter("pageSize");
			
			JSONObject result = new JSONObject();
			
			Page page = null;
			if (StringUtils.isNotEmpty(startPage) && StringUtils.isNotEmpty(pageSize)) {
				page = new Page();
				page.setStartPage(Integer.parseInt(startPage));
				page.setPageSize(Integer.parseInt(pageSize));
			} else {
				result.put(ERROR, "startPage或pageSize不存在");
				this.writeStream(result, "utf-8");
				return ;
			}
			
			QuestionsEntity entity = new QuestionsEntity();
			if (StringUtils.isNotEmpty(QType)) {
				entity.setQType(Integer.parseInt(QType));
			}
			if (StringUtils.isNotEmpty(classify)) {
				entity.setClassify(Integer.parseInt(classify));
			}
			if (StringUtils.isNotEmpty(isuse)) {
				entity.setIsuse(Integer.parseInt(isuse));
			}
			entity.setIntoPerson(intoPerson);
			
			List<QuestionsEntity> questionsList = this.factoryService.getQuestionsService().queryByPage(entity, page);
			String list = "";
			if (null != questionsList && questionsList.size() > 0) {
				list = JSON.toJSONString(questionsList);
			} else {
				list = "[]";
			}
			result.put("list", list);
			result.put("total", page.getTotalCount());
			result.put(SUCCESS, "查询成功");
			this.writeStream(result, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject result = new JSONObject();
			result.put(ERROR, e.toString());
			this.writeStream(result, "utf-8");
		}
	}
	
	public void saveOrUpdate() {
		try {
			String id = request.getParameter("id");
			String intoPerson = request.getParameter("intoPerson");
			String createTime = request.getParameter("createTime");
			
			QuestionsEntity entity = getData();
			JSONObject result = new JSONObject();
			
			if (entity.getQuestion().length() > 250) {
				result.put(ERROR, "问题的长度不能超过250");
				this.writeStream(result, "utf-8");
				return;
			}
			if (entity.getAnswerTxt().length() > 250) {
				result.put(ERROR, "答案的长度不能超过250");
				this.writeStream(result, "utf-8");
				return;
			}
			
			if (StringUtils.isNotEmpty(id)) {
				entity.setId(Integer.parseInt(id));
				entity.setIntoPerson(intoPerson);
				entity.setCreateTime(createTime);
				this.factoryService.getQuestionsService().updateEntity(entity);
				result.put(SUCCESS, "更新成功");
			} else {
				User user = SecurityContext.getUser();
				entity.setIntoPerson(user!=null?user.getName():"");
				entity.setCreateTime(DateFormat.getStandardSysdate());
				this.factoryService.getQuestionsService().saveEntity(entity);
				result.put(SUCCESS, "保存成功");
			}
			this.writeStream(result, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject result = new JSONObject();
			result.put(ERROR, e.toString());
			this.writeStream(result, "utf-8");
		}
	}
	
	private QuestionsEntity getData() throws Exception {
		String QType = request.getParameter("QType");
		String classify = request.getParameter("classify");
		String question = request.getParameter("question");
		String A = request.getParameter("A");
		String B = request.getParameter("B");
		String C = request.getParameter("C");
		String D = request.getParameter("D");
		String answerSelect = request.getParameter("answerSelect");
		String answerTxt = request.getParameter("answerTxt");
		String isuse = request.getParameter("isuse");
		
		QuestionsEntity entity = new QuestionsEntity();
		if (StringUtils.isNotEmpty(QType)) {
			entity.setQType(Integer.parseInt(QType));
		}
		if (StringUtils.isNotEmpty(classify)) {
			entity.setClassify(Integer.parseInt(classify));
		}
		entity.setQuestion(question);
		entity.setA(A);
		entity.setB(B);
		entity.setC(C);
		entity.setD(D);
		entity.setAnswerSelect(answerSelect);
		entity.setAnswerTxt(answerTxt);
		if (StringUtils.isNotEmpty(isuse)) {
			entity.setIsuse(Integer.parseInt(isuse));
		}
		return entity;
	}
	
	public void delete() {
		try {
			String id = request.getParameter("id");
			
			JSONObject result = new JSONObject();
			if (StringUtils.isNotEmpty(id)) {
				this.factoryService.getQuestionsService().deleteEntity(Integer.parseInt(id));
				
				result.put(SUCCESS, "删除成功");
			} else {
				result.put(ERROR, "id不存在");
			}
			this.writeStream(result, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject result = new JSONObject();
			result.put(ERROR, e.toString());
			this.writeStream(result, "utf-8");
		}
	}
	
	public void queryById() {
		try {
			String id = request.getParameter("id");
			
			JSONObject result = new JSONObject();
			if (StringUtils.isNotEmpty(id)) {
				QuestionsEntity entity = this.factoryService.getQuestionsService().queryById(Integer.parseInt(id));
				if (null != entity) {
					
					result.put(SUCCESS, "查询成功");
				} else {
					result.put(ERROR, "为空");
				}
			} else {
				result.put(ERROR, "id不存在");
			}
			this.writeStream(result, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject result = new JSONObject();
			result.put(ERROR, e.toString());
			this.writeStream(result, "utf-8");
		}
	}
	
	public void queryList() {
		try {
			String QType = request.getParameter("QType");
			String classify = request.getParameter("classify");
			String intoPerson = request.getParameter("intoPerson");
			
			JSONObject result = new JSONObject();
			
			QuestionsEntity entity = new QuestionsEntity();
			if (StringUtils.isNotEmpty(QType)) {
				entity.setQType(Integer.parseInt(QType));
			}
			if (StringUtils.isNotEmpty(classify)) {
				entity.setClassify(Integer.parseInt(classify));
			}
			entity.setIntoPerson(intoPerson);
			List<QuestionsEntity> questionsList = this.factoryService.getQuestionsService().queryList(entity);
			String list = "";
			if (null != questionsList && questionsList.size() > 1) {
				list = JSON.toJSONString(questionsList);
			} else {
				list = "[]";
			}
			result.put(SUCCESS, "查询成功");
			result.put("list", list);
			this.writeStream(result, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject result = new JSONObject();
			result.put(ERROR, e.toString());
			this.writeStream(result, "utf-8");
		}
	}
}
