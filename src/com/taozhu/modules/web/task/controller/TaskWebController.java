package com.taozhu.modules.web.task.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.taozhu.common.base.controller.BaseWebController;
import com.taozhu.common.mybatis.util.BaseDAOUtil;
import com.taozhu.common.task.TaskService;
import com.taozhu.common.task.bean.LvTaskQuartz;
import com.taozhu.common.util.AjaxUtil;
import com.taozhu.modules.web.task.service.TaskWebService;

@Controller
@RequestMapping("taskWebController.do")
public class TaskWebController extends BaseWebController{
	
	@Resource
	private TaskWebService taskWebService;
	@Resource
	private TaskService taskService;
	/**
	 * 插入和更新 返回需要两个字段 success、msg
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "saveTask")
	public void saveTask(HttpServletRequest request,HttpServletResponse response){
		Map params = this.getRequestParams(request);
		int id = BaseDAOUtil.getIntValue(params, "id");
		if(id==0){
			//获取问题编码
			params.put("status", 1);
			params.put("create_time", new Date());
			int num= taskWebService.saveTask(params);
			params.clear();
			params.put("msg", "插入成功！");
			
			//启动任务
			LvTaskQuartz quartz=new LvTaskQuartz();
			quartz.setId(num);
			quartz.setTaskName(BaseDAOUtil.getStringValue(params, "task_name"));
			quartz.setTargetObject(BaseDAOUtil.getStringValue(params, "target_object"));
			quartz.setTargetMethod(BaseDAOUtil.getStringValue(params, "target_method"));
			quartz.setTargetDate(BaseDAOUtil.getStringValue(params, "target_date"));
			 taskService.addJob(quartz);
		}else{
			taskWebService.updateTask(params);
		
			//修改任务
			LvTaskQuartz quartz=new LvTaskQuartz();
			quartz.setId(id);
			quartz.setTaskName(BaseDAOUtil.getStringValue(params, "task_name"));
			quartz.setTargetObject(BaseDAOUtil.getStringValue(params, "target_object"));
			quartz.setTargetMethod(BaseDAOUtil.getStringValue(params, "target_method"));
			quartz.setTargetDate(BaseDAOUtil.getStringValue(params, "target_date"));
			taskService.updateJob(quartz);
			
			params.clear();
			params.put("msg", "更新成功！");
		}
		params.put("success", true);
		AjaxUtil.ajaxResponse(response, JSONArray.toJSONString(params), AjaxUtil.RESPONCE_TYPE_JSON);
	}
	
	@RequestMapping(params="taskGrid")
	public String taskGrid(HttpServletResponse response,HttpServletRequest request){
		return "/task/taskGrid";
	}
	@RequestMapping(params="showTask")
	public String showTask(HttpServletResponse response,HttpServletRequest request,Map<String,Object> model){
		Map params = this.getRequestParams(request);
		Map mp = new HashMap();
		if(params.get("id")!=null){
			mp = taskWebService.getTaskById(params);
		}
		model.put("mp", mp);
		return "/task/task";
	}
	
	@RequestMapping(params="showTaskStatus")
	public String showTaskStatus(HttpServletResponse response,HttpServletRequest request,Map<String,Object> model){
		Map params = this.getRequestParams(request);
		Map mp = new HashMap();
		if(params.get("id")!=null){
			mp = taskWebService.getTaskById(params);
		}
		model.put("mp", mp);
		return "/task/taskStatus";
	}
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params="deleteTask")
	public void deleteTask(HttpServletRequest request,HttpServletResponse response){
		Map params = this.getRequestParams(request);
		taskWebService.deleteTask(params);
		params.put("msg", "删除成功!");
		params.put("success", true);
		AjaxUtil.ajaxResponse(response, JSONArray.toJSONString(params), AjaxUtil.RESPONCE_TYPE_JSON);
	}
	
	@RequestMapping(params="updateTaskStatus")
	public void updateTaskStatus(HttpServletRequest request,HttpServletResponse response){
		Map params = this.getRequestParams(request);
		Integer status = BaseDAOUtil.getIntValue(params, "status");
		if(status==1){
			//修改定时任务状态
			params.put("status", 1);
			taskWebService.updateTaskStatus(params);
			
			//启动定时任务
			taskService.resumeJob(BaseDAOUtil.getIntValue(params, "id"));
			
			params.put("msg", "任务启动成功!");
			params.put("success", true);
			AjaxUtil.ajaxResponse(response, JSONArray.toJSONString(params), AjaxUtil.RESPONCE_TYPE_JSON);
		}else if(status==0){
			//修改定时任务状态
			params.put("status",0);
			taskWebService.updateTaskStatus(params);
			
			//停止定时任务
			taskService.pauseJob(BaseDAOUtil.getIntValue(params, "id"));
			
			params.put("msg", "任务停止成功!");
			params.put("success", true);
			AjaxUtil.ajaxResponse(response, JSONArray.toJSONString(params), AjaxUtil.RESPONCE_TYPE_JSON);
		}
	}
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ActionException
	 */
	@RequestMapping(params="startTask")
	public void startTask(HttpServletRequest request,HttpServletResponse response){
		Map params = this.getRequestParams(request);
		//修改定时任务状态
		params.put("status", 1);
		taskWebService.updateTaskStatus(params);
		
		//启动定时任务
		taskService.resumeJob(BaseDAOUtil.getIntValue(params, "id"));
		params.put("msg", "任务启动成功!");
		params.put("success", true);
		AjaxUtil.ajaxResponse(response, JSONArray.toJSONString(params), AjaxUtil.RESPONCE_TYPE_JSON);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ActionException
	 */
	@RequestMapping(params="startTask")
	public void stopTask(HttpServletRequest request,HttpServletResponse response){
		Map params = this.getRequestParams(request);
		//修改定时任务状态
		params.put("status",0);
		taskWebService.updateTaskStatus(params);
		
		//停止定时任务
		taskService.pauseJob(BaseDAOUtil.getIntValue(params, "id"));
		params.put("msg", "任务停止成功!");
		params.put("success", true);
		AjaxUtil.ajaxResponse(response, JSONArray.toJSONString(params), AjaxUtil.RESPONCE_TYPE_JSON);
	}
}
