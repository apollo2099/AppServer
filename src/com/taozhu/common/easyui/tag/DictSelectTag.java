package com.taozhu.common.easyui.tag;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;



import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.taozhu.common.base.dao.BaseDAO;


/**
 * 
 * 选择下拉框
 * 
 */
public class DictSelectTag extends TagSupport {
	private static final long serialVersionUID = 2644844621016085794L;
	
	private String typeGroupCode;	//数据字典类型
	private String field;	//选择表单的Name  EAMPLE:<select name="selectName" id = "" />
	private String id;	//选择表单ID   EAMPLE:<select name="selectName" id = "" />
	private String defaultVal;	//默认值 
	private String divClass;	//DIV样式
	private String labelClass;	//Label样式
	private String title;	//label显示值
	private boolean hasLabel = true;	//是否显示label
	private String type;
	private String dictTable;
	private String dictField;
	private String dictText;
	
	@Autowired
	private BaseDAO mybatisDAO;
	
	public int doStartTag() throws JspTagException {
		return EVAL_PAGE;
	}

	public int doEndTag() throws JspTagException {
		try {
			JspWriter out = this.pageContext.getOut();
			out.print(end().toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}
	

	public StringBuffer end() {
		StringBuffer sb = new StringBuffer();
		if (StringUtils.isBlank(divClass)) {
			divClass = "form";	//默认form样式
		}
		if (StringUtils.isBlank(labelClass)){
			labelClass = "Validform_label";	//默认label样式
		}
		
		if (this.dictTable != null) {
			List<Map<String, Object>> list = queryDic();
			if ("radio".equals(this.type)) {
				for (Map<String, Object> map : list){
					radio(map.get("text").toString(), map.get("field").toString(), sb);
				}
			} else if ("checkbox".equals(this.type)) {
				for (Map<String,Object> map : list){
					checkbox(map.get("text").toString(), map.get("field").toString(), sb);
				}
			} else if ("text".equals(this.type)) {
				for (Map<String, Object> map : list){
					text(map.get("text").toString(), map.get("field").toString(), sb);
				}
			} else {
				sb.append("<select name=\"" + this.field + "\"");
				if (!StringUtils.isBlank(this.id)) {
					sb.append(" id=\"" + this.id + "\"");
				}
				sb.append(">");
				for (Map<String, Object> map : list) {
					select(map.get("text").toString(), map.get("field").toString(), sb);
				}
				sb.append("</select>");
			}
		}
		return sb;
	}

	private void text(String name, String code, StringBuffer sb) {
		if (code.equals(this.defaultVal)){
			sb.append("<input name='" + this.field + "'" + " id='" + this.id
					+ "' value='" + name + "' readOnly = 'readOnly' />");
		}
	}

	private void radio(String name, String code, StringBuffer sb) {
		if (code.equals(this.defaultVal)) {
			sb.append("<input type=\"radio\" name=\"" + this.field
					+ "\" checked=\"checked\" value=\"" + code + "\"");
			if (!StringUtils.isBlank(this.id)) {
				sb.append(" id=\"" + this.id + "\"");
			}
			sb.append(" />");
		} else {
			sb.append("<input type=\"radio\" name=\"" + this.field
					+ "\" value=\"" + code + "\"");
			if (!StringUtils.isBlank(this.id)) {
				sb.append(" id=\"" + this.id + "\"");
			}
			sb.append(" />");
		}
		sb.append(name);
	}

	private void checkbox(String name, String code, StringBuffer sb) {
		String[] values = this.defaultVal.split(",");
		for (int i = 0; i < values.length; i++) {
			String value = values[i];
			if (code.equals(value)) {
				sb.append("<input type=\"checkbox\" name=\"" + this.field
						+ "\" checked=\"checked\" value=\"" + code + "\"");
				if (!StringUtils.isBlank(this.id)) {
					sb.append(" id=\"" + this.id + "\"");
				}
				sb.append(" />");
			} else {
				sb.append("<input type=\"checkbox\" name=\"" + this.field
						+ "\" value=\"" + code + "\"");
				if (!StringUtils.isBlank(this.id)) {
					sb.append(" id=\"" + this.id + "\"");
				}
				sb.append(" />");
			}
			sb.append(name);
		}
	}

	private void select(String name, String code, StringBuffer sb) {
		if (code.equals(this.defaultVal))
			sb.append(" <option value=\"" + code + "\" selected=\"selected\">");
		else {
			sb.append(" <option value=\"" + code + "\">");
		}
		sb.append(name);
		sb.append(" </option>");
	}
	
	private List<Map<String, Object>> queryDic() {
		String sql = "select " + this.dictField + " as field," + this.dictText + " as text from " + this.dictTable;
		List<Map<String, Object>> list = this.mybatisDAO.querySql(sql);
		return list;
	}

	public String getTypeGroupCode() {
		return typeGroupCode;
	}

	public void setTypeGroupCode(String typeGroupCode) {
		this.typeGroupCode = typeGroupCode;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDefaultVal() {
		return defaultVal;
	}

	public void setDefaultVal(String defaultVal) {
		this.defaultVal = defaultVal;
	}

	public String getDivClass() {
		return divClass;
	}

	public void setDivClass(String divClass) {
		this.divClass = divClass;
	}

	public String getLabelClass() {
		return labelClass;
	}

	public void setLabelClass(String labelClass) {
		this.labelClass = labelClass;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isHasLabel() {
		return hasLabel;
	}

	public void setHasLabel(boolean hasLabel) {
		this.hasLabel = hasLabel;
	}
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDictTable() {
		return dictTable;
	}

	public void setDictTable(String dictTable) {
		this.dictTable = dictTable;
	}

	public String getDictField() {
		return dictField;
	}

	public void setDictField(String dictField) {
		this.dictField = dictField;
	}

	public String getDictText() {
		return dictText;
	}

	public void setDictText(String dictText) {
		this.dictText = dictText;
	}
}