/*用JS代码渲染div,主要消除页面的样式渲染过慢*/
var loginOpt = 0;
$('#loginPanel').panel({
    width:400,
    title : " "
}); 
$('#userCode').textbox({
    prompt:'Username',
    iconCls:'icon-man',
    iconWidth:38,
   	height:40,
    width:250
});

$('#userPassword').textbox({
    prompt:'Password',
    iconCls:'icon-lock',
    iconWidth:38,
   	height:40,
   	width:250
});
   $('#loginBtn').linkbutton({
    iconCls: 'icon-ok',
    width:250,
    onClick : function(e){
    		var x = $(this).offset().left;
    		var y = $(this).offset().top;
    		$("#tip").css({
                "top": y-55 + "px",
                "left":  x+50 + "px",
                "position": "absolute"
            });
		   var userCode = $("#userCode").textbox("getValue");
		   var userPassword = $("#userPassword").textbox("getValue");
		   if(userCode == ""  ){
			   
			   $("#tipTxt")[0].innerText="用户名不能为空，请输入！";
			    $("#tip").fadeIn(1000);
			    $("#tip").fadeOut('fast');
		     return false;
		   }
		   if(userPassword == ""){
			   $("#tipTxt")[0].innerText="密码不能为空，请输入！";
			   $("#tip").fadeIn(1000);
			   $("#tip").fadeOut('fast');
			   return false;
		   }
		   $('#loginForm').form('submit',{
		   	   url : contextPath+"/login.do?verify",
		   	   success : function(json){
		   	   	 	var data = $.parseJSON(json);
		   	   		if(data.state=="fail"){
		    		   $("#tipTxt")[0].innerText=data.msg;
					   $("#tip").fadeIn(1000);
					   $("#tip").fadeOut(1000);
					   return false
		    	   }else{
		    		   window.location.href=contextPath+"/mainframe.do?showMainPage";
		    		   return false
		    	   }
		   	   }
		}); 
    }
});

 //加载后执行
$(function(){
	$(".panel-header").height(50);
	$(".panel-title").hide();
	$("#userCode").textbox('textbox').bind('focus',function(){
		$("#usertip .textbox").css("border-color","#136fb8");
	});
	$("#userCode").textbox('textbox').bind('blur',function(){
		$("#usertip .textbox").css("border-color","#DFE4F4");
	});
	$("#userPassword").textbox('textbox').bind('focus',function(){
		$("#pwtip .textbox").css("border-color","#136fb8");
	});
	$("#userPassword").textbox('textbox').bind('blur',function(){
		$("#pwtip .textbox").css("border-color","#DFE4F4");
	});
	//$("#userPassword").textbox('textbox').focus();
	$(document).bind('keydown',function(e){
		if(e.which==13){
			
			//$("#loginBtn").focus();
			if($('#userCode').textbox('textbox').val()!="Username"){
				$('#userCode').textbox('setValue',$('#userCode').textbox('textbox').val());
			}
			if($('#userPassword').textbox('textbox').val()!="Password"){
				$('#userPassword').textbox('setValue',$('#userPassword').textbox('textbox').val());
			}
			$('#loginBtn').trigger("click");
		}
	});
	$("span").attr("tabindex",'-1');
	$("a").attr("tabindex",'-1');
	$("input").attr("tabindex",'-1');
	$("#userCode").textbox('textbox').attr("tabindex",'1');
	$("#userPassword").textbox('textbox').attr("tabindex",'2');
	login.init();
});

var login = (function(){
	var currentObj = null;
	return {
		init : function(){
			$(window).resize(function() {
				$("#loginFrame").css("margin-top",($(this).height()-$("#loginFrame").height())/2);
				$("#loginFrame").css("margin-left",($(this).width()-$("#loginFrame").width())/2);
			});
		}
	};
})(); 