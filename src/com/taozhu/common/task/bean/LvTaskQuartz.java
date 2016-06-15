package com.taozhu.common.task.bean;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author liaoxj
 * @version 1.0
 * @since 1.0
 */
public class LvTaskQuartz implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//date formats
	public static final String FORMAT_CREATE_TIME = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_MODIFY_TIME = "yyyy-MM-dd HH:mm:ss";
	
	/**
     * 定义私有属性get、set
     */
	
      //id       db_column: id 
    
	private java.lang.Integer id;
	
      //任务名称       db_column: task_name 
    
	private java.lang.String taskName;
	
      //目标实例       db_column: target_object 
    
	private java.lang.String targetObject;
	
      //目标方法       db_column: target_method 
    
	private java.lang.String targetMethod;
	
      //定时目标时间       db_column: target_time 
    
	private java.lang.String targetDate;
	
      //描述       db_column: description 
    
	private java.lang.String description;
	
      //0=>暂停任务,1=>启用任务       db_column: status 
    
	private java.lang.Integer status;
	
      //创建时间       db_column: create_time 
    
	private java.util.Date createTime;
	
      //修改时间       db_column: modify_time 
    
	private java.util.Date modifyTime;
	
      //修改人id       db_column: modify_user_id 
    
	private java.lang.Integer modifyUserId;
	
      //修改人名称       db_column: modify_user_name 
    
	private java.lang.String modifyUserName;
	//columns END

	public LvTaskQuartz(){
	}
	/**
	 * id       db_column: id 
	 */
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	/**
	 * id       db_column: id 
	 */
	public java.lang.Integer getId() {
		return this.id;
	}
	/**
	 * 任务名称       db_column: task_name 
	 */
	public void setTaskName(java.lang.String value) {
		this.taskName = value;
	}
	/**
	 * 任务名称       db_column: task_name 
	 */
	public java.lang.String getTaskName() {
		return this.taskName;
	}
	/**
	 * 目标实例       db_column: target_object 
	 */
	public void setTargetObject(java.lang.String value) {
		this.targetObject = value;
	}
	/**
	 * 目标实例       db_column: target_object 
	 */
	public java.lang.String getTargetObject() {
		return this.targetObject;
	}
	/**
	 * 目标方法       db_column: target_method 
	 */
	public void setTargetMethod(java.lang.String value) {
		this.targetMethod = value;
	}
	/**
	 * 目标方法       db_column: target_method 
	 */
	public java.lang.String getTargetMethod() {
		return this.targetMethod;
	}
	/**
	 * 定时目标时间       db_column: target_time 
	 */
	public void setTargetDate(java.lang.String value) {
		this.targetDate = value;
	}
	/**
	 * 定时目标时间       db_column: target_time 
	 */
	public java.lang.String getTargetDate() {
		return this.targetDate;
	}
	/**
	 * 描述       db_column: description 
	 */
	public void setDescription(java.lang.String value) {
		this.description = value;
	}
	/**
	 * 描述       db_column: description 
	 */
	public java.lang.String getDescription() {
		return this.description;
	}
	/**
	 * 0=>暂停任务,1=>启用任务       db_column: status 
	 */
	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}
	/**
	 * 0=>暂停任务,1=>启用任务       db_column: status 
	 */
	public java.lang.Integer getStatus() {
		return this.status;
	}

	
	/**
	 * 创建时间       db_column: create_time 
	 */
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	/**
	 * 创建时间       db_column: create_time 
	 */
	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	
	/**
	 * 修改时间       db_column: modify_time 
	 */
	public void setModifyTime(java.util.Date value) {
		this.modifyTime = value;
	}
	/**
	 * 修改时间       db_column: modify_time 
	 */
	public java.util.Date getModifyTime() {
		return this.modifyTime;
	}
	/**
	 * 修改人id       db_column: modify_user_id 
	 */
	public void setModifyUserId(java.lang.Integer value) {
		this.modifyUserId = value;
	}
	/**
	 * 修改人id       db_column: modify_user_id 
	 */
	public java.lang.Integer getModifyUserId() {
		return this.modifyUserId;
	}
	/**
	 * 修改人名称       db_column: modify_user_name 
	 */
	public void setModifyUserName(java.lang.String value) {
		this.modifyUserName = value;
	}
	/**
	 * 修改人名称       db_column: modify_user_name 
	 */
	public java.lang.String getModifyUserName() {
		return this.modifyUserName;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("TaskName",getTaskName())
			.append("TargetObject",getTargetObject())
			.append("TargetMethod",getTargetMethod())
			.append("TargetDate",getTargetDate())
			.append("Description",getDescription())
			.append("Status",getStatus())
			.append("CreateTime",getCreateTime())
			.append("ModifyTime",getModifyTime())
			.append("ModifyUserId",getModifyUserId())
			.append("ModifyUserName",getModifyUserName())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof LvTaskQuartz == false) return false;
		if(this == obj) return true;
		LvTaskQuartz other = (LvTaskQuartz)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

