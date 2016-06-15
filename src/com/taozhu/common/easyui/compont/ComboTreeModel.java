package com.taozhu.common.easyui.compont;

/**
 * @ClassName: ComboTreeModel 
 * @Description: TODO(下拉树形选择框模型设置类) 
 * 
 */
public class ComboTreeModel implements java.io.Serializable {
	
	private static final long serialVersionUID = -3780804877521760055L;
	
	private String idField;
	private String textField;
	private String iconCls;// 前面的小图标样式
	private String childField;// 子节点
	private String srcField;//地址字段
	
	public ComboTreeModel(String idField, String textField, String childField) {
		this.idField = idField;
		this.textField = textField;
		this.childField = childField;
	}
	public ComboTreeModel(String idField, String textField, String childField, String srcField) {
		this.idField = idField;
		this.textField = textField;
		this.childField = childField;
		this.srcField = srcField;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public String getChildField() {
		return childField;
	}
	public void setChildField(String childField) {
		this.childField = childField;
	}
	public String getIdField() {
		return idField;
	}
	public void setIdField(String idField) {
		this.idField = idField;
	}
	public String getTextField() {
		return textField;
	}
	public void setTextField(String textField) {
		this.textField = textField;
	}
	public String getSrcField() {
		return srcField;
	}
	public void setSrcField(String srcField) {
		this.srcField = srcField;
	}
}