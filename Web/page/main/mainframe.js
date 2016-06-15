$(function(){
	mainframe.init();
});

var  mainframe = (function(){
	var currentObj = null;
	var sonMenuUrl = contextPath+"/mainframe.do?getSecondMenu";
	return{
		init : function(){
			currentObj = this;
			$('#menu-accordion').accordion({
				    animate:false,
				    onSelect : function(title,index){
				    	//定义一级菜单选择事件，加载二级菜单。
				    	var menuPanel = $(this).accordion('getSelected');
				    	currentObj.loadSonMenu(menuPanel);
				    }
				});
			//加载默认选择一级菜单的子菜单
			var menuPanel = $('#menu-accordion').accordion('getSelected');
			this.loadSonMenu(menuPanel);
		},
		loadSonMenu : function(menuPanel){
			var menuId = menuPanel[0].id;
			var loadMenuId  = menuId.substr(4,menuId.length);
				$("#son"+menuId).tree({
					url : sonMenuUrl+"&parentId="+loadMenuId,
					onClick : function(node){
						if(!$('#mainTabs').tabs('exists',node.text)){
							$('#mainTabs').tabs('add',{
							    title : node.text,
							    content : "<iframe id='"+node.id+"' src='"+node.attributes.page+"' width='100%' height='100%' scrolling='no' frameborder='0'></iframe>",
							    closable:true
							});
						}else{
							$('#mainTabs').tabs('select',node.text);
						}
					}
				});
		}
	};
})(); 
