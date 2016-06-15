package com.taozhu.common.easyui.tag;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

public class DataGridQueryColumnTag  extends TagSupport {
	protected String title;//表格列名
	protected String field;//数据库对应字段
	protected Integer width;//宽度
	protected String replace;//条件为combobox时用，分割的值
	protected boolean datebox =false;//是否为时间格式
	protected boolean combobox=false;//是否为jquery combobox控件
	private boolean autocomplete = false;//是否为自动补全
	private String queryMode = "single";//字段查询模式：single单字段查询；group范围查询
	protected boolean queryHide=false;//是否隐藏查询框
	protected String extend;
	public String getExtend() {
		return extend;
	}

	public void setExtend(String extend) {
		this.extend = extend;
	}

	public boolean isQueryHide() {
		return queryHide;
	}

	public void setQueryHide(boolean queryHide) {
		this.queryHide = queryHide;
	}

	public int doStartTag() throws JspTagException {
		return EVAL_PAGE;
	}
	
	public int doEndTag() throws JspTagException {
		Tag t = findAncestorWithClass(this, DataGridTag.class);
		DataGridTag parent = (DataGridTag) t;
		parent.setQueryColumn(title, field, width, replace,combobox, queryMode, autocomplete,datebox,queryHide,extend);
		return EVAL_PAGE;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public String getReplace() {
		return replace;
	}
	public void setReplace(String replace) {
		this.replace = replace;
	}
	public boolean isDatebox() {
		return datebox;
	}
	public void setDatebox(boolean datebox) {
		this.datebox = datebox;
	}
	public boolean isCombobox() {
		return combobox;
	}
	public void setCombobox(boolean combobox) {
		this.combobox = combobox;
	}
	public boolean isAutocomplete() {
		return autocomplete;
	}
	public void setAutocomplete(boolean autocomplete) {
		this.autocomplete = autocomplete;
	}
	public String getQueryMode() {
		return queryMode;
	}
	public void setQueryMode(String queryMode) {
		this.queryMode = queryMode;
	}
}
