package com.taozhu.common.task;

import java.text.ParseException;

import com.taozhu.common.task.bean.LvTaskQuartz;

public interface TaskService {
public void initTask()throws ParseException ;
public void addJob(LvTaskQuartz ttaskQuartz);
public void updateJob(LvTaskQuartz ttaskQuartz);
public void deleteJob(Integer  taskQuartzId);
public void pauseJob(Integer  taskQuartzId);
public void resumeJob(Integer  taskQuartzId);
}
