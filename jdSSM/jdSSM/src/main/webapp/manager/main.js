$(function() {
	var menuHeight = $("#menu").height();
	$("#menu_accordion").accordion({
		height : menuHeight + "px",
		animate : true
	});

	$("#menu_accordion a").click(function() {
		var url = $(this).attr("rel");
		// var title = $(this).find(".l-btn-text").html();
		// var title = $(this).find(".l-btn-text").text();
		var title = $(this).text();
		var icon = $(this).attr("iconCls");
		addTab(title, icon, url);
	});

	$("#tab_close").click(function() {
		var title = $("#tabs_menu").data("tabTitle");
		$("#jd_tabs").tabs('close', title);
	});

	$("#tab_close_all").click(function() {
		$(".tabs-inner .tabs-title").each(function() {
			var title = $(this).text();
			if (title != "首页") {
				$("#jd_tabs").tabs('close', title);
			}
		});
	});

	$("#tab_close_right").click(function() {
		var nextAll = $(".tabs-selected").nextAll();
		nextAll.each(function() {
			var title = $(this).text();
			$("#jd_tabs").tabs('close', title);
		});
	});

	$("#tab_close_left").click(function() {
		var prevAll = $(".tabs-selected").prevAll();
		prevAll.each(function() {
			var title = $(this).text();
			if (title != "首页") {
				$("#jd_tabs").tabs('close', title);
			}
		});
	});

	$("#tab_close_other").click(function() {
		$("#tab_close_right").click();
		$("#tab_close_left").click();
	});

	$("#exit_menu").click(function() {
		$('#tabs_menu').menu('hide');
	});

	$("#exit_btn").click(function(){
		//$.messager.alert('退出','您确定要退出系统!','info');
		$.messager.confirm('退出', '您确定要退出系统?', function(r){
			if (r){
				$.get("userInfo/logout.do",function(result){
					if(result.code == 100){
						window.location = "../login.html";
					}
				});				
			}
		});
	});
});

function addTab(title, icon, url) {
	if ($('#jd_tabs').tabs('exists', title)) {
		$('#jd_tabs').tabs('select', title);
		var tab = $('#jd_tabs').tabs('getSelected');
		$('#jd_tabs').tabs('update', {
			tab : tab,
			options : {
				content : tabContent(url)
			}
		});
	} else {
		$('#jd_tabs').tabs('add', {
			title : title,
			content : tabContent(url),
			closable : true,
			icon : icon
		});
	}

	$(".tabs-inner").bind('contextmenu', function(e) {
		e.preventDefault();
		$('#tabs_menu').menu('show', {
			left : e.pageX,
			top : e.pageY
		});
		var tabTitle = $(this).text();
		$("#jd_tabs").tabs("select", tabTitle);
		$("#tabs_menu").data("tabTitle", tabTitle);
	});
}

function tabContent(url) {
	return '<iframe src="' + url
			+ '" frameborder="0" style="width:100%;height:100%"></iframe>';
}