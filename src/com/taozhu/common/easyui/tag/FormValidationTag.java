package com.taozhu.common.easyui.tag;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;



public class FormValidationTag extends TagSupport {
	private static final long serialVersionUID = 6497581349920499492L;
	
	protected String formid = "formobj";// 表单FORM ID
	protected Boolean refresh = true;
	protected Boolean enctype=false;//上传属性
	protected String callback;// 回调函数
	protected String beforeSubmit;// 提交前处理函数
	protected String btnsub = "btn_sub";// 以ID为标记触发提交事件
	protected String btnreset = "btn_reset";// 以ID为标记触发提交事件
	protected String layout = "div";// 表单布局
	protected String usePlugin;// 外调插件
	protected boolean dialog = true;// 是否是弹出窗口模式
	protected String action;// 表单提交路径
	protected String tabtitle;// 表单选项卡
	protected String tiptype = "4";//校验方式

	@Override
	public int doStartTag() throws JspException {
		try {
			JspWriter out = this.pageContext.getOut();
			StringBuffer sb = new StringBuffer();
			if ("div".equals(layout)) {
				sb.append("<div id=\"content\">");
				sb.append("<div id=\"wrapper\">");
				sb.append("<div id=\"steps\">");
			}
			sb.append("<form id=\"" + formid + "\" action=\"" + action + "\" name=\"" + formid + "\" method=\"post\"");
			if(!enctype){
				sb.append("enctype=\"multipart/form-data\" ");
			}
			sb.append(">");
			if ("btn_sub".equals(btnsub) && dialog)
				sb.append("<input type=\"hidden\" id=\"" + btnsub + "\" class=\"" + btnsub + "\"/>");
			out.print(sb.toString());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	@Override
	public int doEndTag() throws JspException {
		try {
			JspWriter out = this.pageContext.getOut();
			StringBuffer sb = new StringBuffer();
			HttpServletRequest req = (HttpServletRequest) this.pageContext.getRequest();
			String basePath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+req.getContextPath();
			if (layout.equals("div")) {
				sb.append("<link rel=\"stylesheet\" href=\""+basePath+"/plug-in/Validform/css/divfrom.css\" type=\"text/css\"/>");
				if (tabtitle != null)
					sb.append("<script type=\"text/javascript\" src=\""+basePath+"/plug-in/Validform/js/form.js\"></script>");
			}
			sb.append("<link rel=\"stylesheet\" href=\""+basePath+"/plug-in/Validform/css/style.css\" type=\"text/css\"/>");
			sb.append("<link rel=\"stylesheet\" href=\""+basePath+"/plug-in/Validform/css/tablefrom.css\" type=\"text/css\"/>");
			sb.append("<script type=\"text/javascript\" src=\""+basePath+"/plug-in/Validform/js/Validform_v5.3.1_min.js\"></script>");
			sb.append("<script type=\"text/javascript\" src=\""+basePath+"/plug-in/Validform/js/Validform_Datatype.js\"></script>");
			sb.append("<script type=\"text/javascript\" src=\""+basePath+"/plug-in/Validform/js/datatype.js\"></script>");
			if (usePlugin != null) {
				if (usePlugin.indexOf("jqtransform") >= 0) {
					sb.append("<SCRIPT type=\"text/javascript\" src=\""+basePath+"/plug-in/Validform/plugin/jqtransform/jquery.jqtransform.js\"></SCRIPT>");
					sb.append("<LINK rel=\"stylesheet\" href=\""+basePath+"/plug-in/Validform/plugin/jqtransform/jqtransform.css\" type=\"text/css\"></LINK>");
				}
				if (usePlugin.indexOf("password") >= 0) {
					sb.append("<SCRIPT type=\"text/javascript\" src=\""+basePath+"/plug-in/Validform/plugin/passwordStrength/passwordStrength-min.js\"></SCRIPT>");
				}
			}
			sb.append("<script type=\"text/javascript\">");
			sb.append("$(function(){");
			sb.append("$(\"#" + formid + "\").Validform({");
			if(this.getTiptype()!=null && !"".equals(this.getTiptype())){
				sb.append("tiptype:"+this.getTiptype()+",");
			}else{
				sb.append("tiptype:1,");
			}
			sb.append("btnSubmit:\"#" + btnsub + "\",");
			sb.append("btnReset:\"#" + btnreset + "\",");
			sb.append("ajaxPost:true,");
			if (beforeSubmit != null) {
				sb.append("beforeSubmit:function(curform){var tag=false;");
				sb.append("return " + beforeSubmit + "(curform);");

				sb.append("},");
			}
			if (usePlugin != null) {
				StringBuffer passsb = new StringBuffer();
				if (usePlugin.indexOf("password") >= 0) {
					passsb.append("passwordstrength:{");
					passsb.append("minLen:6,");
					passsb.append("maxLen:18,");
					passsb.append("trigger:function(obj,error)");
					passsb.append("{");
					passsb.append("if(error)");
					passsb.append("{");
					passsb.append("obj.parent().next().find(\".Validform_checktip\").show();");
					passsb.append("obj.find(\".passwordStrength\").hide();");
					passsb.append("}");
					passsb.append("else");
					passsb.append("{");
					passsb.append("$(\".passwordStrength\").show();");
					passsb.append("obj.parent().next().find(\".Validform_checktip\").hide();");
					passsb.append("}");
					passsb.append("}");// trigger结尾
					passsb.append("}");// passwordstrength结尾
				}
				StringBuffer jqsb = new StringBuffer();
				if (usePlugin.indexOf("jqtransform") >= 0) {
					if (usePlugin.indexOf("password") >= 0) {
						sb.append(",");
					}
					jqsb.append("jqtransform :{selector:\"select\"}");
				}
				sb.append("usePlugin:{");
				if (usePlugin.indexOf("password") >= 0) {
					sb.append(passsb);
				}
				if (usePlugin.indexOf("jqtransform") >= 0) {
					sb.append(jqsb);
				}
				sb.append("},");
			}
			sb.append("callback:function(data){");
			if (dialog) {
				if(callback!=null&&callback.contains("@Override")){//复写默认callback
					sb.append(callback.replaceAll("@Override", "") + "(data);");
				}else{
					sb.append("var win = frameElement.api.opener; ");
					//先判断是否成功，成功再刷新父页面，否则return false    
					// 如果不成功，返回值接受使用data.msg. 原有的data.responseText会报null 
					sb.append("if(data.success==true){frameElement.api.close();win.tip(data.msg);}else{if(data.responseText==''||data.responseText==undefined){$.messager.alert('错误', data.msg);}else{try{var emsg = data.responseText.substring(data.responseText.indexOf('错误描述'),data.responseText.indexOf('错误信息')); $.messager.alert('错误',emsg);}catch(ex){$.messager.alert('错误',data.responseText+\"\");}} return false;}");
					//
					if (refresh) {
						sb.append("win.reloadTable();");
					}
					if (StringUtils.isNotEmpty(callback)) {
						sb.append("win."+ callback + "(data);");
					}
				}
				//失败tip不提示
				//sb.append("win.tip(data.msg);");
			} else {
				sb.append("" + callback + "(data);");
			}
			sb.append("}" + "});" + "});" + "</script>");
			sb.append("");
			sb.append("</form>");
			if ("div".equals(layout)) {
				sb.append("</div>");
				if (tabtitle != null) {
					String[] tabtitles = tabtitle.split(",");
					sb.append("<div id=\"navigation\" style=\"display: none;\">");
					sb.append("<ul>");
					for (String string : tabtitles) {
						sb.append("<li>");
						sb.append("<a href=\"#\">" + string + "</a>");
						sb.append("</li>");
					}
					sb.append("</ul>");
					sb.append("</div>");
				}
				sb.append("</div></div>");
			}
			out.print(sb.toString());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}
	
	public void setTabtitle(String tabtitle) {
		this.tabtitle = tabtitle;
	}

	public void setDialog(boolean dialog) {
		this.dialog = dialog;
	}

	public void setBtnsub(String btnsub) {
		this.btnsub = btnsub;
	}

	public void setRefresh(Boolean refresh) {
		this.refresh = refresh;
	}

	public void setBtnreset(String btnreset) {
		this.btnreset = btnreset;
	}

	public void setFormid(String formid) {
		this.formid = formid;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setUsePlugin(String usePlugin) {
		this.usePlugin = usePlugin;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	public void setBeforeSubmit(String beforeSubmit) {
		this.beforeSubmit = beforeSubmit;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}

	public String getTiptype() {
		return tiptype;
	}

	public void setTiptype(String tiptype) {
		this.tiptype = tiptype;
	}
	public Boolean getEnctype() {
		return enctype;
	}

	public void setEnctype(Boolean enctype) {
		this.enctype = enctype;
	}

}