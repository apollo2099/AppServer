package com.taozhu.common.task.job;

import org.springframework.scheduling.quartz.QuartzJobBean;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class MyJob extends QuartzJobBean {
	
	private MyTask myTask;

	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		
		myTask.run();
	}

	public MyTask getMyTask() {
		return myTask;
	}

	public void setMyTask(MyTask myTask) {
		this.myTask = myTask;
	}
}