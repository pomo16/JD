$(function(){
	createTable();
	validateForm();
	
	$("#updateBtn").click(function(){
		$('#update_user_form').submit()
	});
	
	$("#updateResetBtn").click(function(){
		$("#update_user_form").form("reset");
		$(".message").html("");
	});
	
	$("#updateCancelBtn").click(function(){
		$("#update_user_window").window('close');
	});
})

function createTable(){
	$("#user_info_table").datagrid({
		title:'用户信息表',
		singleSelect:false,
		collapsible:false,
		url:'userInfo/userInfoList.do',
		method:'get',
		rownumbers:true,
		iconCls:'icon-save',
		pagination:true,
		pageList:[10,20,40],
		pageSize:10,
		width:1000,
		height:480,
		columns:[[
			{field:'ck',checkbox:true},
			{field:'userName',title:'用户名',width:200},
		    {field:'phone',title:'手机',width:500},
			{field:'id',title:'操作', width:200,
				formatter: function(value,row,index){
					return "<a onclick='createUpdateUser(" + index + ")' class='jd_btn'>修改手机</a>";
				}
			}
		]],
		onLoadSuccess:function(data){
			$('.jd_btn').linkbutton({
				plain:true,
				iconCls:'icon-edit'
			});
		},
		toolbar: [{
				iconCls: 'icon-cancel',
				text:'删除',
				handler: function(){
					var rows = $("#user_info_table").datagrid("getSelections");
					if(rows.length>0){
						$.messager.confirm('删除用户', '您确定要删除已选择的用户信息?', function(r){
							if (r){
								var url = "userInfo/deleteByIds.do?";
								for(var i=0;i<rows.length-1;i++){
									url += "ids=" + rows[i].id + "&";
								}
								url += "ids=" + rows[i].id;
								$.ajax({
									url : url,
									type : "GET",
									success : function(result) {
										$.messager.alert("删除成功","成功删除" + result + "条记录！","info");
										$("#user_info_table").datagrid("reload");
									}
								});
							}
						});
					}else{
						$.messager.alert("删除失败","请先选择记录，再执行删除操作！","error");
					}
				}
		}]
	});
}

function validateForm(){
	$("#update_user_form").validate({
		submitHandler: function () {	
			$.ajax({
				url : "userInfo/updateUserInfo.do",
				data : $("#update_user_form").serialize(),
				type : "POST",
				success : function(result) {
					if(result.code == 100){
						$("#update_user_window").window('close');
						$.messager.alert("修改成功","用户【" + $("#userName").html() + "】手机修改成功！","info");
						$("#user_info_table").datagrid("gotoPage",1);
					}
				}
			});
		},
		rules:{
			phone:{
				required:true,
				rangelength:"[11,11]",
				digits:true,
				phoneRule:true
			}
		},
		messages:{
			phone:{
				required:"手机不能为空",
				rangelength:"手机号必须为11位",
				digits:"手机号必须由数字组成",
				phoneRule:"手机号格式错误"
			}
		},
		errorPlacement: function ( error, element ) {
			error.appendTo(element.parent().next());
		},
		errorElement: "span",		
		highlight: function ( element, errorClass, validClass ) {
			$(element).parent().next().html("<img src='../common/easyui/themes/icons/no.png' style='float:left;margin:3px'/>");
		},
		unhighlight: function ( element, errorClass, validClass ) {
			$(element).parent().next().html("<img src='../common/easyui/themes/icons/ok.png' style='float:left;margin:3px'/>");
		}
	});
}

function createUpdateUser(index){
	$("#update_user_window").window('open');
	$(".message").html("");
	var rows = $("#user_info_table").datagrid("getRows");
	var row = rows[index];
	$("#idUpdate").val(row.id);
	$("#userName").html(row.userName);
	$("#phone").val(row.phone);
}

$.validator.addMethod( "phoneRule", function( value, element ) {
	return this.optional( element ) || /^1[38][0-9]{9}|15[012356789][0-9]{8}|14[57][0-9]{8}|17[678][0-9]{8}$/.test( value );
});