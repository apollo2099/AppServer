// 判断控制台打印
if (typeof(console) == "undefined") {
	console = {};
	console.log = function() {
	}
}
// 表单ID,查询按钮ID
var searchId = "search";
// 表格ID
var dgId = "dg";
/**
 * 初始化页面组件
 * 
 * @param {string}
 *            id 初始位置ID
 * @param {string}
 *            url 配置文件位置
 * @param {object}
 *            formValidate 表单验证
 */
function initComp1(id, url, formValidate) {
	var id = '#' + id;
	$.messager.progress(); // display the progress bar
	$.ajax({
		url : url,
		dateType : "xml",
		success : function(xml) {
			$(xml).find("ROOT").each(function() {
				var html = '';
				// 初始化添加区
				var form = initQueryParamsComp($(this).children("queryParams"));
				if(form!=''){
					html += form;
					html += '<hr />';
				}
				
				// 初始化表格，工具栏
				var result = initResultComp($(this).children("result"));
				html += result;
				$(id).html(html);
				// 渲染页面
				$.parser.parse($(id));
				// 添加监听
				addListener(formValidate);
			});
			$.messager.progress('close');
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			console.log('message', textStatus + ', ' + errorThrown);
			$.messager.progress('close'); // hide progress bar while
			// submit
			alert('读取配置文件出错！');
		}

	});
}
/**
 * 初始化查询条件区
 * 
 * @param {}
 *            queryParams
 * @return {}
 */
function initQueryParamsComp(queryParams) {
	// console.log(queryParams);
	var cols = queryParams.children("cols").text();
	// console.log(cols)
	if(cols==''){
		return '';
	}
	var form = '';
	form += '<div id="queryPanel" class="easyui-panel">';
	form += '<form id="ff" method="post">';
	form += '	<table style="width:100%">';
	form += '		<tr>';
	var items = queryParams.children("item");
	items.each(function(index, item) {
				// 换行判断
				if (items.length != index && index % cols == 0) {
					form += '</tr>';
					form += '<tr>';
				}
				form += '<td';
				var attr = $(item).attr("colspan");
				if (attr != null) {
					form += ' colspan="' + attr + '"';
				}
				attr = $(item).attr("style");
				if (attr != null) {
					form += ' style="' + attr + '"';
				}
				form += ">"
				$(item).children().each(function(index, param) {
					tagName = $(this).get(0).tagName;
					form += '<' + tagName;
					$.each($(param).get(0).attributes, function(i, attrib) {
								form += ' ' + attrib.name + '="' + attrib.value
										+ '"';
							});
					form += '>' + $(param).text() + '</' + tagName + '>';
				});
				form += '</td>';
			});
	form += '		</tr>';
	form += '</table>';
	form += '</form>'
	form += '</div>';
	return form;
}
/**
 * 初始化表格 工具栏
 * 
 * @param {}
 *            result
 * @return {}
 */
function initResultComp(result) {
	var table = '';
	// 工具栏
	var toolbar = result.children("toolbar");
	// 有toolbar节点
	// table += '<div id="p" class="easyui-panel">';
	if (toolbar) {
		var toolbarId = $(toolbar).attr('id');
		table += '<div id="' + toolbarId + '">';
		items = $(toolbar).children("item");
		// console.log(items);
		items.each(function(index, item) {
					table += '<a href="#" class="easyui-linkbutton"';
					table += ' iconCls="' + $(item).attr("iconCls") + '"';
					table += ' plain="true" onclick="'
							+ $(item).attr("handler") + '()"';
					table += '>' + $(item).text() + '</a>';
				});
		table += '</div>';
	}
	// 表格
	var datagrid = result.children("datagrid");
	table += '<table ';
	$.each($(datagrid).get(0).attributes, function(i, attrib) {
				table += ' ' + attrib.name + '="' + attrib.value + '"';
				if (name == "id")
					dgId = value;
				
			});
	table += '>';
	table += '<thead><tr>';
	items = $(datagrid).children("item");
	// console.log(items);
	items.each(function(index, item) {
				table += '<th ';
				$.each($(item).get(0).attributes, function(i, attrib) {
							table += ' ' + attrib.name + '="' + attrib.value
									+ '"';
						});
				table += '>' + $(item).text() + '</th>';
			});
	table += '</tr></thead>';
	table += '</table>'
	// table += '</div>';
	console.log(table);

	return table;
}
/**
 * 添加监听
 * 
 * @param {}
 *            formValidate 表单验证
 */
function addListener(formValidate) {
	/*
	$("#" + dgId).datagrid({
				frozenColumns : [[{
							field : 'ok',
							checkbox : true
						}]]
			});
	*/
	$("#" + searchId).click(function() {
				// 获得表单数据
				console.log($("form#ff").serialize());
				// 表单验证信息 存在并且为false时，返回
				if (formValidate != null && !formValidate()) {
					return;
				}
				$("#" + dgId).datagrid({
							url : "getRecords.action?"
									+ $("form#ff").serialize(),
							pageNumber : 1
						});
			})
	// 自适应
	$(window).resize(function() {
				// 这样resize也行
				$("#" + dgId).datagrid("resize", {
							width : getWidth(0.99)
						});
				$("#queryPanel").panel("resize", {
							width : getWidth(0.99)
						});
			});
}
/**
 * 获取屏幕宽度
 * 
 * @param {}
 *            percent 百分比
 * @return {}
 */
function getWidth(percent) {
	return $(window).width() * percent;
}
/**
 * 封装部分的datagrid
 * @param {Object} params
 */
function datagridForm(params){
	var toolbar=[];
	if(params.toolbarRes==''&&params.toolbarRes!=undefined){
		toolbar=[{
				id:'update',
				text:params.isquery=='update'?'修改':'详细查询',
				iconCls:params.isquery=='update'?'icon-edit':'icon-search',
				handler:update
			},{
				id:'batchUpdate',
				text:'批量修改',
				iconCls:'icon-edit',
				handler:batchUpdate
			},{
				id:'deleteRes',
				text:'删除',
				iconCls:'icon-cancel',
				handler:deleteRes
			},{
				id:'batchDeleteRes',
				text:'批量删除',
				iconCls:'icon-cancel',
				handler:batchDeleteRes
			}];
	}else if(params.toolbarDev==''&&params.toolbarDev!=undefined){
		toolbar=[{
				id:'update',
				text:'修改',
				iconCls:'icon-edit',
				handler:update
			},{
				id:'deleteDev',
				text:'删除',
				iconCls:'icon-cancel',
				handler:deleteDev
			},{
				id:'allot',
				text:'设备调拨',
				iconCls:'icon-tip',
				handler:allot
			},{
				id:'res',
				text:params.isquery=='update'?'资源维护':'资源查询',
				iconCls:params.isquery=='update'?'icon-tip':'icon-search',
				handler:res
			}];
	}
	if(params.tbar!=undefined){
		toolbar= params.tbar;
	}
	//通过isExportXls属性控制是否显示导出按钮默认显示
	if(isNull(params.isExportXls)&& params.isExportXls!='false'){
		toolbar.push({
				id:'exportXls',
	 			text:'导出',
	 			iconCls:'icon-back',
	 			handler:exportXlsFunc
			});
	}
	if(params.pageList==undefined||params.pageList==null){
		params.pageList=[30,50,100,200];
	}
	var url=params.url==undefined?'datagridTemplate.action?initParams='+params.initParams:params.url;
	url=encodeURI(url); 
	url=encodeURI(url); 
	$("#"+id).datagrid({
			width:document.body.clientWidth * 0.98,
			height:document.body.clientHeight * 0.76 ,
			nowrap: false,
			striped: true,
			url:url,
			pageSize:params.pageSize==undefined?30:params.pageSize,
			pageList:params.pageList,
			pagination:params.pagination==undefined?true:params.pagination,
			rownumbers:true,
			queryParams:{},
			frozenColumns:params.frozenColumns,
			columns:params.columns,
			toolbar:toolbar
	});
}
//将js对象转换为json字符串
function obj2str(o) {
	var r = [];
	if (typeof o == "string")
		return "\""
				+ o.replace(/([\'\"\\])/g, "\\$1").replace(/(\n)/g, " \\n")
						.replace(/(\r)/g, "\\r").replace(/(\t)/g, "\\t") + "\"";
	if (typeof o == "undefined")
		return "";
	if (typeof o == "object") {
		if (o === null)
			return "null";
		else if (!o.sort) {
			for ( var i in o) {
				r.push("\"" + i + "\":" + obj2str(o[i]));
			}
			r = "{" + r.join() + "}";
		} else {
			for ( var i = 0; i < o.length; i++)
				r.push(obj2str(o[i]));
			r = "[" + r.join() + "]";
		}
		return r;
	}
	return o.toString();
}
/**
 * 导出的公用方法
 * @param {Object} dgData导出的条件
 * @param {Object} sheetName导出文件名
 * @param {Object} boName实现导出的方法
 */
function exportXlsFunc(){
	var dgData={}
	if(!isNull(params.selectMapperId)){
		dgData.sqlMapper=params.selectMapperId;
	}
 	var sheetName=params.sheetName;//"FTTH-IP资源信息导出";
	var boName = null;
	if(isNull(params.exportboName)){
		boName = "DevOrResInfoFileExportService";
	}else{
		boName = params.exportboName;
	}
  var columns=$("#datagrid").datagrid('options').columns;
  var arr=new Array();
  $(columns).each(function (index) {
	  for (var i = 0; i < columns[index].length; ++i) {
		  if (!columns[index][i].hidden) {
			  if(columns[index][i].field!=undefined&&columns[index][i].field!=''){
			  	if(columns[index][i].title!=undefined&&columns[index][i].title!=''){
			  		var file=columns[index][i].field;
			  		var title=columns[index][i].title;
			  		var tableMeta='';
			  		tableMeta+='{'+'\'' + file + '\':' + '\''+title+ '\'}';
			  		arr.push(tableMeta);
			  	}
		  	  }
	     }
	 }
  });
 var rows = $('#datagrid').datagrid('getSelections');
	if(rows.length>0){
		dgData.rows = rows;
	}else if(rows.length==0){
		rows = $('#'+id).datagrid('getRows');
		if(rows.length==0){
			alert("没有信息不能导出数据！");
			return;
		}
		var queryparams=obj2str(queryform());
		dgData.queryparams=queryform();
	}
	
  var exportMap=obj2str(dgData);
  var exportMetaMap='['+arr.join()+']';
  $("#exportXls").mask("正在导出数据,请稍等……");
        $.ajax({
			type : "POST",
			url : "fileExportAction.action",
			data : {
        		exportMetaMap:exportMetaMap,
        		exportMap : exportMap,
        		sheetName : sheetName,
        		boName:boName
			},
			success : function(data) {
				$("#exportXls").unmask();
				var dataObj = eval("(" + data + ")");
				if (dataObj.msg == 'ok') {
					var path = root + dataObj.resultContents;
					window.open(path);
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				var message = textStatus + ', ' + errorThrown;
				$.messager.alert('提示', message);
			}
		});
}
/**
 * open新的window
 * @param {Object} url
 * @param {Object} title
 * @param {Object} style
 */
function openNewWindow(winparams){
	var width=800;
	var height=400;
	var fit=false;
	var s=new Array();
	if(winparams.style!=''&&winparams.title!=undefined){
		var style=winparams.style;
		s=style.split(";");
		for(var i=0;i<s.length;i++){
			var s1="";
			s1=s[i].split(":");
			if(s1[0]=="width"){
				width=s1[1];
			}else if(s1[0]=="height"){
				height=s1[1];
			}else if(s1[0]=="fit"){
				if(s1[1]=="true"){
					fit=true;
				}
			}else{
				alert('Window的style格式不对！');
				return;
			}
		}
	}
	var _content = '<iframe id="FRMdetail"  scrolling="yes" frameborder="0"  '
  					+'src="'+winparams.url+'"'
  					+'style="width:100%;height:100%;" ></iframe>';
	$('#'+winparams.id).window({
  				content: _content,
  				width:width,
  				height:height,
  				title:winparams.title,
  				fit:fit
  			});
}
function openNewDialog(diaparams){
	var width=500;
	var height=300;
	var fit=false;
	var s=new Array();
	if(diaparams.style!=''&&diaparams.style!=undefined){
		var style=diaparams.style;
		s=style.split(";");
		for(var i=0;i<s.length;i++){
			var s1="";
			s1=s[i].split(":");
			if(s1[0]=="width"){
				width=s1[1];
			}else if(s1[0]=="height"){
				height=s1[1];
			}else if(s1[0]=="fit"){
				if(s1[1]=="true"){
					fit=true;
				}
			}
		}
	}
	var _content = '<iframe id="FRMdetail111"  scrolling="yes" frameborder="0"  '
  					+'src="'+diaparams.url+'"'
  					+'style="width:100%;height:100%;" ></iframe>';
	$("#"+diaparams.id).dialog({
  				content: _content,
  				width:width,
  				height:height,
  				hrefMode:"iframe",resizable: true,
  				title:diaparams.title
  			});
}
/**
 * 查询的公用方法
 */
function searchFunc(){
  		var queryParams=obj2str(queryform());
  		$("#queryParams").val(queryParams);
  		$("#datagrid").datagrid('load',{
  			queryParams:queryParams
  		});
}

/**
 * 判断空
 * @param {String} val
 * @returns {Boolean}
 */
function isNull(val){
	if(!val){
		return true;
	}
	if(val == null || val == "" || val == "undefined" || val == "null" || val == "NULL"){
		return true;
	}
	return false;
}