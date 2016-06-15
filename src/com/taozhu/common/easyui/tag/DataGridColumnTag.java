package com.taozhu.common.easyui.tag;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 
 * 类描述：列表字段处理项目
 * 
 */
public class DataGridColumnTag extends TagSupport {
	private static final long serialVersionUID = -2885969861950784644L;
	
	protected String title;
	protected String field;
	protected Integer width;
	protected String rowspan;
	protected String colspan;
	protected String align;
	protected boolean sortable=true;
	protected boolean checkbox;
	protected String formatter;//时间 格式的datagrid formatter="yyyy-MM-dd hh:mm:ss" 
	protected boolean hidden=false;//是否隐藏列
	protected boolean datebox =false;//是否为时间格式
	protected String treefield;
	protected String editor;
	protected boolean image;
	private boolean frozenColumn=false; // 是否是冰冻列    默认不是
	protected boolean bSearchable=true;
	protected String url;//自定义链接
	protected String funname="openwindow";//自定义函数名称
	protected String arg;//自定义链接传入参数字段
	protected String dictionary;	//数据字典组编码
	protected String extend; //扩展属性
	protected String style; //Td的CSS
	
	protected String imageSize;
	protected String downloadName;
	private boolean autocomplete = false;
	private String extendParams;
	
	public int doStartTag() throws JspTagException {
		return EVAL_PAGE;
	}
	
	public int doEndTag() throws JspTagException {
		Tag t = findAncestorWithClass(this, DataGridTag.class);
		DataGridTag parent = (DataGridTag) t;
		parent.setColumn(title, field, width, rowspan, colspan, align, sortable, checkbox, 
				formatter, hidden, treefield, image, imageSize, 
				 url, funname, arg, dictionary, frozenColumn,
				extend, style, downloadName, autocomplete, extendParams,datebox,editor);
		return EVAL_PAGE;
	}
	
	public void setArg(String arg) {
		this.arg = arg;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setFunname(String funname) {
		this.funname = funname;
	}

	public void setbSearchable(boolean bSearchable) {
		this.bSearchable = bSearchable;
	}


	public void setImage(boolean image) {
		this.image = image;
	}

	public void setTreefield(String treefield) {
		this.treefield = treefield;
	}


	public void setTitle(String title) {
		this.title = title;
	}

	public void setField(String field) {
		this.field = field;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public void setRowspan(String rowspan) {
		this.rowspan = rowspan;
	}

	public void setColspan(String colspan) {
		this.colspan = colspan;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public void setSortable(boolean sortable) {
		this.sortable = sortable;
	}

	public void setCheckbox(boolean checkbox) {
		this.checkbox = checkbox;
	}

	public void setFormatter(String formatter) {
		this.formatter = formatter;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}
	
	public void setDictionary(String dictionary) {
		this.dictionary = dictionary;
	}
	
	public boolean isFrozenColumn() {
		return frozenColumn;
	}

	public void setFrozenColumn(boolean frozenColumn) {
		this.frozenColumn = frozenColumn;
	}

	public String getExtend() {
		return extend;
	}

	public void setExtend(String extend) {
		this.extend = extend;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public void setDownloadName(String downloadName) {
		this.downloadName = downloadName;
	}

	public void setImageSize(String imageSize) {
		this.imageSize = imageSize;
	}

	public void setAutocomplete(boolean autocomplete) {
		this.autocomplete = autocomplete;
	}

	public void setExtendParams(String extendParams) {
		this.extendParams = extendParams;
	}

	public void setDatebox(boolean datebox) {
		this.datebox = datebox;
	}

	public final String getStyle() {
		return style;
	}

	public final void setEditor(String editor) {
		this.editor = editor;
	}

}