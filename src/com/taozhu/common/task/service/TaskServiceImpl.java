package com.taozhu.common.task.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.taozhu.common.base.dao.BaseDAO;
import com.taozhu.common.mybatis.util.BaseDAOUtil;
import com.taozhu.common.task.TaskService;
import com.taozhu.common.task.bean.LvTaskQuartz;
import com.taozhu.common.task.job.MyJob;
import com.taozhu.common.task.job.MyTask;
import com.taozhu.common.util.ObjectUtils;
import com.taozhu.modules.web.task.service.TaskWebService;


@Lazy(false)
@Service("taskService")
public class TaskServiceImpl implements TaskService {

	@Resource
	private BaseDAO BaseDao;
	@Resource
	private Scheduler scheduler;
	@Resource
	private TaskWebService  taskWebService;

	private String job = "job_";
	private String trigger = "cron_";

	@Override
	@PostConstruct
	public void initTask() throws ParseException {//web容器启动的时候加载任务
		// 获取任务列表的HQL语句
		List<Map<String,Object>> listTask = taskWebService.queryList(null);
		if(ObjectUtils.isNotEmpty(listTask)){
			for (Map<String, Object> map : listTask) {
				LvTaskQuartz quartz =new LvTaskQuartz();
				Integer id=(Integer) map.get("id");
				String task_name=(String) map.get("task_name");
				String target_object=(String) map.get("target_object");
                String target_method=(String) map.get("target_method");
				String target_date=(String) map.get("target_date");
				Integer status=(Integer) map.get("status");
				quartz.setId(id);
				quartz.setTaskName(task_name);
				quartz.setTargetObject(target_object);
				quartz.setTargetMethod(target_method);
				quartz.setTargetDate(target_date);
				quartz.setStatus(status);
				this.addJob(quartz);
				if(quartz.getStatus()==0){
					this.pauseJob(id);
				}
			}
		}
	}

	/**
	 * 增加任务
	 * 
	 * @param quartz
	 * @throws SchedulerException
	 * @throws ParseException
	 */
	@Override
	public void addJob(LvTaskQuartz ttaskQuartz) {
		String jobName = this.job +ttaskQuartz.getId();
		String triggerName = this.trigger + ttaskQuartz.getId();
		try {

			JobDetail jobDetail = new JobDetail();
			jobDetail.setName(jobName);
			MyTask myTask = new MyTask();
			myTask.setTargetObject(ttaskQuartz.getTargetObject());
			myTask.setTargetMethod(ttaskQuartz.getTargetMethod());
			jobDetail.getJobDataMap().put("myTask", myTask);
			jobDetail.setJobClass(MyJob.class);
			scheduler.addJob(jobDetail, true);
			CronTrigger cronTrigger = new CronTrigger(triggerName, Scheduler.DEFAULT_GROUP, jobDetail.getName(), Scheduler.DEFAULT_GROUP);
			cronTrigger.setCronExpression(ttaskQuartz.getTargetDate());
			scheduler.scheduleJob(cronTrigger);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 删除任务
	 */
	@Override
	public void deleteJob(Integer  taskQuartzId) {
		String jobName = this.job +taskQuartzId;
		String triggerName = this.trigger +taskQuartzId;

		try {
			scheduler.pauseTrigger(triggerName, Scheduler.DEFAULT_GROUP);// 停止触发器
			scheduler.unscheduleJob(triggerName, Scheduler.DEFAULT_GROUP);
			// 移除触发器
			scheduler.deleteJob(jobName, Scheduler.DEFAULT_GROUP);// 删除任务
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 修改任务
	 */
	@Override
	public void updateJob(LvTaskQuartz ttaskQuartz) {
		String jobName = this.job + ttaskQuartz.getId();
		String triggerName = this.trigger + ttaskQuartz.getId();
		try {
			Trigger trigger = scheduler.getTrigger(triggerName,
					Scheduler.DEFAULT_GROUP);
			if (trigger != null) {
				JobDetail jobDetail=scheduler.getJobDetail(jobName, Scheduler.DEFAULT_GROUP);
				MyTask myTask = (MyTask)jobDetail.getJobDataMap().get("myTask");;
				myTask.setTargetObject(ttaskQuartz.getTargetObject());
				myTask.setTargetMethod(ttaskQuartz.getTargetMethod());
				CronTrigger ct = (CronTrigger)trigger;  
				try {
					ct.setCronExpression(ttaskQuartz.getTargetDate());
				} catch (ParseException e) {
			
					e.printStackTrace();
				}
				scheduler.rescheduleJob(triggerName,Scheduler.DEFAULT_GROUP, trigger);	//重新按排
			}
		} catch (SchedulerException e) {
	
			e.printStackTrace();
		}
	}
    /**
     * 暂停任务
     */
	@Override
	public void pauseJob(Integer  taskQuartzId) {
		String jobName = this.job +taskQuartzId;
		String triggerName = this.trigger + taskQuartzId;
		Trigger trigger;
		try {
			trigger = scheduler.getTrigger(triggerName,
					Scheduler.DEFAULT_GROUP);
			if (trigger != null) {
				scheduler.pauseJob(jobName, Scheduler.DEFAULT_GROUP);
				scheduler.pauseTrigger(triggerName, Scheduler.DEFAULT_GROUP);
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		
	}
    /**
     * 恢复任务
     */
	@Override
	public void resumeJob(Integer  taskQuartzId) {
		String triggerName = this.trigger +taskQuartzId;
		String jobName = this.job + taskQuartzId;
		Trigger trigger;
		try {
			trigger = scheduler.getTrigger(triggerName,
					Scheduler.DEFAULT_GROUP);
			if (trigger != null) {
				scheduler.resumeJob(jobName, Scheduler.DEFAULT_GROUP);
				scheduler.resumeTrigger(triggerName, Scheduler.DEFAULT_GROUP);
			}
		} catch (SchedulerException e) {

			e.printStackTrace();
		}
	
		
	}

}
