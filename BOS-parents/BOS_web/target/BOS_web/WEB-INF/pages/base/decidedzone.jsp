<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理定区/调度排班</title>
<!-- 导入jquery核心类库 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<!-- 导入easyui类库 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/ext/portal.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/default.css">	
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.portal.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.cookie.js"></script>
<script
	src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
<script type="text/javascript">
	function doAdd(){
		$('#addDecidedzoneWindow').window("open");
	}
	
	function doEdit(){
		alert("修改...");
	}
	
	function doDelete(){
    alert("删除")

    }
	
	function doSearch(){
		$('#searchWindow').window("open");
	}
	
	function doAssociations(){
	    //判断是否已经选中,返回一个数组对象
		var  v=$("#grid").datagrid("getSelections");
		if(v.length==1){
		    //发送ajax请求获取所有未关联的客户
            $('#customerWindow').window('open');
            $("#noassociationSelect").empty();
            $("#associationSelect").empty();
            //第一个ajax请求查询未未关联的客户
            var  url_1="${pageContext.request.contextPath}/decidedzoneAction_findListNotAssociation.action";
            // $.post(url_1,function (data) {
            //
            //     for (var i = 0; i < data.length; i++) {
            //         var id = data[i].id;
            //         var name = data.name;
            //         var telephone = data.telephone;
            //         name = name + "(" + telephone + ")";
            //         $("#noassociationSelect").empty();
            //         //将信息添加进option中
            //         $("#noassociationSelect").append("<option value='" + id + "'>" + name + "<option>");
            //     }
            //
            // }
            $.post(url_1,function (data) {
                $("#noassociationSelect").empty();
                $("#associationSelect").empty();
                //ajax提交数据，后遍历数组对象
                for (var i = 0; i < data.length; i++) {
                            // var id = data[i].id;
                            // var name = data[i].name;
                            // var telephone = data[i].telephone;
                    var id = data[i].id;
                    var name = data[i].name;
                    var telephone = data[i].telephone;
                    name = name + "(" + telephone + ")";
                            //将信息添加进option中
                    // $("#associationSelect").append("<option value='"+id+"'>"+name+"<option>")
                    $("#noassociationSelect").append("<option value='"+id+"'>"+name+"</option>")
                        }
            });
            //第二个ajax请求
            var  url_2="${pageContext.request.contextPath}/decidedzoneAction_findListAssociation.action";
            var  decidedzone_id=v[0].id;
            $.post(url_2,{"id":decidedzone_id},function (data) {
                $("#noassociationSelect").empty();
                $("#associationSelect").empty();
                for ( var i=0;i<data.length;i++){
                    //将数据添加进option里面
                    var id=data[i].id;
                    var  name=data[i].name;
                    var telephone=data[i].telephone;
                    $("#associationSelect").append("<option value='"+id+"'>"+name+"<option>");
                }
            });
		}else {
		 //如果不是选中一个而是多个
			$.messager.alert("提示信息","请选择定区","warning");
		}


	}
	
	//工具栏
	var toolbar = [ {
		id : 'button-search',	
		text : '查询',
		iconCls : 'icon-search',
		handler : doSearch
	}, {
		id : 'button-add',
		text : '增加',
		iconCls : 'icon-add',
		handler : doAdd
	}, {
		id : 'button-edit',	
		text : '修改',
		iconCls : 'icon-edit',
		handler : doEdit
	},{
		id : 'button-delete',
		text : '删除',
		iconCls : 'icon-cancel',
		handler : doDelete
	},{
		id : 'button-association',
		text : '关联客户',
		iconCls : 'icon-sum',
		handler : doAssociations
	}];
	// 定义列
	var columns = [ [ {
		field : 'id',
		title : '定区编号',
		width : 120,
		align : 'center'
	},{
		field : 'name',
		title : '定区名称',
		width : 120,
		align : 'center'
	}, {
		field : 'staff.name',
		title : '负责人',
		width : 120,
		align : 'center',
		formatter : function(data,row ,index){
			return row.staff.name;
		}
	}, {
		field : 'staff.telephone',
		title : '联系电话',
		width : 120,
		align : 'center',
		formatter : function(data,row ,index){
			return row.staff.telephone;
		}
	}, {
		field : 'staff.station',
		title : '所属公司',
		width : 120,
		align : 'center',
		formatter : function(data,row ,index){
			return row.staff.station;
		}
	} ] ];
	
	$(function(){
		// 先将body隐藏，再显示，不会出现页面刷新效果
		$("body").css({visibility:"visible"});
		
		// 收派标准数据表格
		$('#grid').datagrid( {
			iconCls : 'icon-forward',
			fit : true,
			border : true,
			rownumbers : true,
			striped : true,
			pageList: [30,50,100],
			pagination : true,
			toolbar : toolbar,
			url : "${pageContext.request.contextPath}/decidedzoneAction_pageQuery",
			idField : 'id',
			columns : columns,
			onDblClickRow : doDblClickRow
		});
		
		// 添加、修改定区
		$('#addDecidedzoneWindow').window({
	        title: '添加修改定区',
	        width: 600,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 400,
	        resizable:false
	    });
		
		// 查询定区
		$('#searchWindow').window({
	        title: '查询定区',
	        width: 400,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 400,
	        resizable:false
	    });
		$("#btn").click(function(){
			alert("执行查询...");
		});
		
	});

	function doDblClickRow(index,data){
		//将table标签变成数据表格
		$('#association_subarea').datagrid( {
			fit : true,
			border : true,
			rownumbers : true,
			striped : true,
			//根据定区的id查询分区的所有数据
			url : "${pageContext.request.contextPath}/subAreaAction_findListByDecidedzoneId.action?decidedzoneId="+data.id,
			columns : [ [{
				field : 'id',
				title : '分拣编号',
				width : 120,
				align : 'center'
			},{
				field : 'province',
				title : '省',
				width : 120,
				align : 'center',
				formatter : function(data,row ,index){
					return row.region.province;
				}
			}, {
				field : 'city',
				title : '市',
				width : 120,
				align : 'center',
				formatter : function(data,row ,index){
					return row.region.city;
				}
			}, {
				field : 'district',
				title : '区',
				width : 120,
				align : 'center',
				formatter : function(data,row ,index){
					return row.region.district;
				}
			}, {
				field : 'addresskey',
				title : '关键字',
				width : 120,
				align : 'center'
			}, {
				field : 'startnum',
				title : '起始号',
				width : 100,
				align : 'center'
			}, {
				field : 'endnum',
				title : '终止号',
				width : 100,
				align : 'center'
			} , {
				field : 'single',
				title : '单双号',
				width : 100,
				align : 'center'
			} , {
				field : 'position',
				title : '位置',
				width : 200,
				align : 'center'
			} ] ]
		});
		//查找已经关联的客户窗口
		$('#association_customer').datagrid( {
			fit : true,
			border : true,
			rownumbers : true,
			striped : true,
			url : "${pageContext.request.contextPath}/decidedzoneAction_findListAssociation.action?id="+data.id,
			columns : [[{
				field : 'id',
				title : '客户编号',
				width : 120,
				align : 'center'
			},{
				field : 'name',
				title : '客户名称',
				width : 120,
				align : 'center'
			}, {
				field : 'station',
				title : '所属单位',
				width : 120,
				align : 'center'
			}]]
		});
		

    }
</script>	
</head>
<body class="easyui-layout" style="visibility:hidden;">
	<div region="center" border="false">
    	<table id="grid"></table>
	</div>
	<div region="south" border="false" style="height:150px">
		<div id="tabs" fit="true" class="easyui-tabs">
			<div title="关联分区" id="subArea"
				style="width:100%;height:100%;overflow:hidden">
				<table id="association_subarea"></table>
			</div>	
			<div title="关联客户" id="customers"
				style="width:100%;height:100%;overflow:hidden">
				<table id="association_customer"></table>
			</div>	
		</div>
	</div>
	
	<!-- 添加 修改分区 -->
	<div class="easyui-window" title="定区添加修改" id="addDecidedzoneWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >保存</a>
				<script type="text/javascript">
					$(function () {
						$("#save").click(function () {
							//为表单提交绑定事件
							var v=$("#adddecldedzoneForm").form("validate");
							if(v){
							    $("#adddecldedzoneForm").submit();
							}else {
							    $.messager.alert("提示信息","表单提交失败","warning");
							}
                        });
                    })
				</script>
			</div>
		</div>
		
		<div style="overflow:auto;padding:5px;" border="false">
			<form id="adddecldedzoneForm" method="post" action="${pageContext.request.contextPath}/decidedzoneAction_add.action">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">定区信息</td>
					</tr>
					<tr>
						<td>定区编码</td>
						<td><input type="text" name="id" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>定区名称</td>
						<td><input type="text" name="name" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>选择负责人</td>
						<td>
							<input class="easyui-combobox" name="staff.id"
								   <%--提交表单，获去没有被删除的取派员信息--%>
    							data-options="valueField:'id',textField:'name',url:'${pageContext.request.contextPath}/staffAction_listajax.action'" />
						</td>
					</tr>
					<tr height="300">
						<td valign="top">关联分区</td>
						<td>
							<%--是用ajax回显数据表格，关联未关联的分区数据--%>
							<table id="subareaGrid"  class="easyui-datagrid" border="false" style="width:300px;height:300px" data-options="url:'${pageContext.request.contextPath}/subAreaAction_listajax.action',fitColumns:true,singleSelect:false">
								<thead>  
							        <tr>  
							            <th data-options="field:'subareaid',width:30,checkbox:true">编号</th>
							            <th data-options="field:'addresskey',width:150">关键字</th>  
							            <th data-options="field:'position',width:200,align:'right'">位置</th>  
							        </tr>  
							    </thead> 
							</table>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<!-- 查询定区 -->
	<div class="easyui-window" title="查询定区窗口" id="searchWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div style="overflow:auto;padding:5px;" border="false">
			<form>
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">查询条件</td>
					</tr>
					<tr>
						<td>定区编码</td>
						<td><input type="text" name="id" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>所属单位</td>
						<td><input type="text" name="staff.station" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td colspan="2"><a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a> </td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	
	<!-- 关联客户窗口 -->
	<div class="easyui-window" title="关联客户窗口" id="customerWindow" collapsible="false" closed="true" minimizable="false" maximizable="false" style="top:20px;left:200px;width: 400px;height: 300px;">
		<div style="overflow:auto;padding:5px;" border="false">
			<form id="customerForm" action="${pageContext.request.contextPath}/decidedzoneAction_assigncustomerstodecidedzone.action" method="post">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="3">关联客户</td>
					</tr>
					<tr>
						<td>
							<input type="hidden" name="id" id="customerDecidedZoneId" />
							<select id="noassociationSelect" multiple="multiple" size="10"></select>
						</td>
						<td>
							<input type="button" value="》》" id="toRight"><br/>
							<input type="button" value="《《" id="toLeft">
                            <%--为按钮绑定事件，将左右值转换--%>
                         <script type="text/javascript">
                             $(function () {
                                $("#toRight").click(function () {
                                    //将为关联的客户转换到已经关联色option中
                                    $("#associationSelect").append($("#noassociationSelect option:selected"))
                                });
                                 $("#toLeft").click(function () {
                                     //将关联的客户转换到已经关联色option中
                                     $("#noassociationSelect").append($("#associationSelect option:selected"))
                                 });
                             });
                         </script>
						</td>
						<td>
							<select id="associationSelect" name="customerIds" multiple="multiple" size="10"></select>
						</td>
					</tr>
					<tr>
						<td colspan="3"><a id="associationBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">关联客户</a>
                        <%--为关联客户按钮绑定事件--%>
                            <script type="text/javascript">
                                $(function () {

									$("#associationBtn").click(function () {
									    alert("helo")
                                        //将定区的id通过隐藏域提交过去
                                        var  row=$("#grid").datagrid("getSelections");                                        //隐藏域为id赋值
										var  	id1=row[0].id;
                                        //隐藏域为id赋值
                                        $("input[name=id]").val(id1);
                                        $("#associationSelect option").attr("selected","selected");
                                     $("#customerForm").submit();
                                    });
                                })
                            </script>

                        </td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>