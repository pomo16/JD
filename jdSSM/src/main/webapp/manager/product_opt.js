$(function(){
	createTable();
	validateForm();
	$("#resetBtn").click(function(){
		$("#product_form").form("reset");
		$(".message").html("");
	});
	
	$("#addBtn").click(function(){
		$('#product_form').submit();
	});
	
	$("#cancelBtn").click(function(){
		$("#product_window").window('close');
	});
	
	$("#updateResetBtn").click(function(){
		$("#update_product_form").form("reset");
		$(".message").html("");
	});
	
	$("#updateBtn").click(function(){
		$('#update_product_form').submit()
	});
	
	$("#updateCancelBtn").click(function(){
		$("#update_product_window").window('close');
	});
	
	$("#add_product_picture").click(function(){
		$("#file_uplad_message").html("");
		$("#progress").css("display","none");
		var url = "../common/util/upload.do";
		if($("#picture_input").val() != ""){
			url += "?fileSaveName=" + $("#picture_input").val();
		}
		createFileUpload(url,"");
		$("#fileupload").click();
	});
	
	$("#add_product_picture_update").click(function(){
		$("#file_uplad_message_update").html("");
		$("#progress_update").css("display","none");
		var url = "../common/util/upload.do";
		if($("#picture_input_update").val() != ""){
			url += "?fileSaveName=" + $("#picture_input_update").val();
		}
		createFileUpload(url,"_update");
		$("#fileupload").click();
	});
});

function createTable(){
	$("#product_table").datagrid({
		title:'商品信息表',
		singleSelect:false,
		collapsible:false,
		url:'product/productList.do',
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
		    {field:'productCode',title:'商品编号',width:100},
		    {field:'productName',title:'商品名称',width:100},
		    {field:'price',title:'价格(&yen;)',width:100},
		    {field:'weight',title:'重量(kg)',width:100},
		    {field:'place',title:'产地',width:100},
		    {field:'description',title:'描述',width:200,align:'center',
		    	formatter: function(value,row,index){
					return "<textarea cols='20' rows='5' readonly style='border-radius:5px'>" + value + "</textarea>";
				}			    
		    },
		    {field:'picture',title:'图片',width:100,align:'center',
		    	formatter: function(value,row,index){
		    		if(value==""){
		    			return "";
		    		}else{
						return "<img style='width:80px;height:60px' src='../common/images/product/" + value + "?" + new Date().getTime() + "' style='width:80px;border-radius:5px;border:1px solid #ddd'/>";
		    		}
				}
		    },
		    {field:'id',title:'操作', width:100,
				formatter: function(value,row,index){
					return "<a onclick='createUpdateProduct(" + index + ")' class='jd_btn'>修改</a>";
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
				iconCls: 'icon-add',
				text:'添加',
				handler: function(){
					$("#productCode").removeData("previousValue");
					$("#product_window").window('open');
					$("#product_form input,#product_form textarea").val("");
					$(".message").html("");	
				}
			},'-',{
				iconCls: 'icon-cancel',
				text:'删除',
				handler: function(){
					var rows = $("#product_table").datagrid("getSelections");
					if(rows.length>0){
						$.messager.confirm('删除商品', '您确定要删除已选择的商品信息?', function(r){
							if (r){
								var url = "product/deleteByIds.do?";
								for(var i=0;i<rows.length-1;i++){
									url += "ids=" + rows[i].id + "&";
								}
								url += "ids=" + rows[i].id;
								$.ajax({
									url : url,
									data : $("#product_form").serialize(),
									type : "GET",
									success : function(result) {
										$.messager.alert("删除成功","成功删除" + result + "条记录！","info");
										$("#product_table").datagrid("reload");
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
	$("#product_form").validate({
		submitHandler: function () {	
			$.ajax({
				url : "product/addProduct.do",
				data : $("#product_form").serialize(),
				type : "POST",
				success : function(result) {
					if(result.code == 100){
						$("#product_window").window('close');
						$.messager.alert("添加成功","【" + $("#productName").val() + "】商品添加成功！","info");
						$("#product_table").datagrid("gotoPage",1);
					}
				}
			});
		},
		rules:{
			productCode:{
				required:true,
				rangelength:"[4,12]",
				remote:"product/productCodeValidate.do"
			},
			productName:"required",
			price:{
				required:true,
				number:true
			},
			weight:{
				required:true,
				number:true
			},
			place:"required",
			description:"required"
		},
		messages:{
			productCode:{
				required:"商品编号不能为空",
				rangelength:"长度只能在4-12个字符之间",
				remote:"商品编号已存在，请更换编号"
			},
			productName:"商品名称不能为空",
			price:{
				required:"价格不能为空",
				number:"价格必须为有效的数值"
			},
			weight:{
				required:"重量不能为空",
				number:"重量必须为有效的数值"
			},
			place:"产地不能为空",
			description:"描述不能为空"
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
	
	$("#update_product_form").validate({
		submitHandler: function () {
			$.ajax({
				url : "product/updateById.do",
				data : $("#update_product_form").serialize(),
				type : "POST",
				success : function(result) {
					if(result.code == 100){
						$("#update_product_window").window('close');
						$.messager.alert("修改成功","【" + $("#productNameUpdate").val() + "】商品修改成功！","info");
						$("#product_table").datagrid("reload");
					}
				}
			});
		},
		rules:{			
			productName:"required",
			price:{
				required:true,
				number:true
			},
			weight:{
				required:true,
				number:true
			},
			place:"required",
			description:"required"
		},
		messages:{			
			productName:"商品名称不能为空",
			price:{
				required:"价格不能为空",
				number:"价格必须为有效的数值"
			},
			weight:{
				required:"重量不能为空",
				number:"重量必须为有效的数值"
			},
			place:"产地不能为空",
			description:"描述不能为空"
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

function createUpdateProduct(index){
	$("#file_uplad_message_update").html("");
	$("#progress_update").css("display","none");
	$("#update_product_window").window('open');
	$(".message").html("");
	var rows = $("#product_table").datagrid("getRows");
	var row = rows[index];
	$("#productCodeUpdate").html(row.productCode);
	$("#productNameUpdate").val(row.productName);
	$("#priceUpdate").val(row.price);
	$("#weightUpdate").val(row.weight);
	$("#placeUpdate").val(row.place);
	$("#descriptionUpdate").val(row.description);
	$("#idUpdate").val(row.id);
	$("#picture_input_update").val(row.picture);
	if(row.picture !=""){
		$("#picture_img_update").css("display","inline");
	}else{
		$("#picture_img_update").css("display","none");
	}
	if(row.picture != ""){
		$("#picture_img_update").attr("src","../common/images/product/" + row.picture + "?" + new Date().getTime());
	}	
}


//创建FileUpload对象
function createFileUpload(url,update){
	$("#fileupload").fileupload({
      dataType: 'json',
      url:url,
      acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i,
      maxFileSize: 1024 * 1024 * 500,
      messages: {           
          acceptFileTypes: '图片文件格式错误',
          maxFileSize: '文件不能超过5M'
      },
      done: function (e, data) {
         if(data.result.key == "ok"){
      	   $("#file_uplad_message" + update).append("<span style='color:green;margin-left:20px'>上传完毕</span>");
      	   $("#picture_img" + update).css("display","inline");
      	   $("#picture_img" + update).attr("src","../common/images/product/" + data.result.fileName + "?" + new Date().getTime());
      	   $("#picture_input" + update).val(data.result.fileName);
         }
      },
      processfail:function(e,data){ 	
      	var fileName = data.files[data.index].name;
      	var error = data.files[data.index].error;
      	$("#file_uplad_message" + update).append("<span style='color:red'>" + fileName + " " + error + "</span>");    
      },
      progress: function (e, data) {
      	var progress = parseInt(data.loaded / data.total * 100);
      	$("#progress" + update).css("display","block");
      	$('#progress' + update).progressbar('setValue', progress);
      	var fileName = data.files[0].name;
      	var loaded = (data.loaded/1024/1024).toFixed(2) + "M";
          var total = (data.total/1024/1024).toFixed(2) + "M";
          var bitrate = (data.bitrate/1024/1024).toFixed(2) + "M";
          $("#file_uplad_message" + update).html(fileName + " 已上传：" + loaded + " 大小：" + total + " 速度：" + bitrate);
          
      }
  });
}