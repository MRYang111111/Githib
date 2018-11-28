<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
		//alert("增加...");
		$('#addStaffWindow').window("open");

	}
	
	function doView(){
		alert("查看...");
	}
	
	function doDelete(){
        // getSelections none 返回所有被选中的行，当没有记录被选中的时候将返回一个空数组。
        //判断是否选择多少行
        var rows=$("#grid").datagrid("getSelections");
        //如果没有选中则给出提示
        if(rows.length==0){
            $.messager.alert("提示","请选择要删除的取派员","warning")
        }else {
            //选中时弹出确认框
            $.messager.confirm("删除确认","你确定要删除取派员吗",function (r) {
                if(r){
                    // //确认，方发送请求
                     var arr=new Array();
                    // //遍历被选中的id
                    for ( var i=0;i<rows.length;i++){
                        //遍历数组中的json对象
                        var staff=rows[i];//获取json对象里面的id
                        var id=staff.id;
						arr.push(id);
                    }
                    var  ids=arr.join(",");
                    location.href="${pageContext.request.contextPath}/staffAction_deleteBatch.action?ids="+ids
                }
            });
        }
	}
	
	function doRestore(){
		alert("将取派员还原...");
	}
	//工具栏
	var toolbar = [ {
		id : 'button-view',	
		text : '查询',
		iconCls : 'icon-search',
		handler : doView
	}, {
		id : 'button-add',
		text : '增加',
		iconCls : 'icon-add',
		handler : doAdd
	}, {
		id : 'button-delete',
		text : '删除',
		iconCls : 'icon-cancel',
		handler : doDelete
	},{
		id : 'button-save',
		text : '还原',
		iconCls : 'icon-save',
		handler : doRestore
	}];
	// 定义列
	var columns = [ [ {
		field : 'id',
		checkbox : true,
	},{
		field : 'name',
		title : '姓名',
		width : 120,
		align : 'center'
	}, {
		field : 'telephone',
		title : '手机号',
		width : 120,
		align : 'center'
	}, {
		field : 'haspda',
		title : '是否有PDA',
		width : 120,
		align : 'center',
		formatter : function(data,row, index){
			if(data=="1"){
				return "有";
			}else{
				return "无";
			}
		}
	}, {
		field : 'deltag',
		title : '是否作废',
		width : 120,
		align : 'center',
		formatter : function(data,row, index){
			if(data=="0"){
				return "正常使用"
			}else{
				return "已被删除";
			}
		}
	}, {
		field : 'standard',
		title : '取派标准',
		width : 120,
		align : 'center'
	}, {
		field : 'station',
		title : '所谓单位',
		width : 200,
		align : 'center'
	} ] ];
	
	$(function(){
		// 先将body隐藏，再显示，不会出现页面刷新效果
		$("body").css({visibility:"visible"});
		
		// 取派员信息表格
		$('#grid').datagrid( {
			iconCls : 'icon-forward',
            pageSize: 15,//每页显示的记录条数，默认为5
			fit : false,
			border : true,
			rownumbers : true,
			striped : true,
			pageList: [5,10,20],
			pagination : true,
			toolbar : toolbar,
			url : "${pageContext.request.contextPath}/staffAction_pageQuery",
			idField : 'id',
			columns : columns,
			//为表格绑定事件
			onDblClickRow : doDblClickRow
		});
		
		// 添加取派员窗口
		$('#addStaffWindow').window({
	        title: '添加取派员',
	        width: 400,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 400,
	        resizable:false
	    });
        // 修改取派员取派员窗口
        $('#editStaffWindow').window({
            title: '修改取派员',
            width: 400,
            modal: true,
            shadow: true,
            closed: true,
            height: 400,
            resizable:false

        });
		
	});

	function doDblClickRow(rowIndex, rowData){
	    //rowData,为json对象
		//打开一个新的窗口
		$("#editStaffWindow").window("open");
		//用表单的load方法回显表格的数据
		$("#editstaffFrom").form("load",rowData);
		alert(rowData.id);

	}
</script>	
</head>
<body class="easyui-layout" style="visibility:hidden;">
	<div region="center" border="false">
    	<table id="grid"></table>
	</div>
	<%--添加窗口--%>
	<div class="easyui-window" title="对收派员进行添加或者修改" id="addStaffWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div region="north" style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >保存</a>
			</div>
		</div>
		
		<div region="center" style="overflow:auto;padding:5px;" border="false">
			<form id="staffFrom" action="staffAction_add.action" method="post">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">收派员信息</td>
					</tr>
					<!-- TODO 这里完善收派员添加 table -->

					<tr>
						<td>姓名</td>
						<td><input type="text" name="name" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>手机</td>
						<%--加入手机号校验的函数--%>

						<script type="text/javascript">
						$(function () {
                            //进行表单的校验
                            $("#save").click(function () {
                                //对表单进行提交后进行刷新
                                var v=$("#staffFrom").form("validate");
                                if(v){
                                    $("#staffFrom").submit()
                                }else {
                                    $.messager().alert("提示信息","取派员信息保存失败","warning");
                                }
                            });
                            <%--自定义手机号的校验规则--%>
                            $.extend($.fn.validatebox.defaults.rules, {
                                telephone: {
                                    validator: function(value, param){
                                     var reg=/^1[1|3|4|7|8][0-9]{9}$/
                                        return reg.test(value);
                                    },
                                    message: '手机号输入有误.'
                                }
                            });
                        });

						</script>
						<td><input type="text"  data-options="validType:'telephone'" name="telephone" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>单位</td>
						<td><input type="text" name="station" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td colspan="2">
						<input type="checkbox" name="haspda" value="1" />
						是否有PDA</td>
					</tr>
					<tr>
						<td>取派标准</td>
						<td>
							<input type="text" name="standard" class="easyui-validatebox" required="true"/>  
						</td>
					</tr>
					</table>
			</form>
		</div>
	</div>
	<%--修改窗口--%>
	<div class="easyui-window" title="对收派员进行添加或者修改" id="editStaffWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">

		<div region="north" style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="edit" icon="icon-edit" href="#" class="easyui-linkbutton" plain="true" >保存</a>
			</div>
		</div>

		<div region="center" style="overflow:auto;padding:5px;" border="false">

			<form id="editstaffFrom" action="staffAction_edit.action" method="post">
				<input type="hidden" name="id">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">收派员信息</td>
					</tr>
					<!-- TODO 这里完善收派员添加 table -->

					<tr>
						<td>姓名</td>
						<td><input type="text" name="name" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>手机</td>
						<%--加入手机号校验的函数--%>

						<script type="text/javascript">
                            $(function () {
                                //进行表单的校验
                                $("#edit").click(function () {
                                    //对表单进行提交后进行刷新
                                        var v=$("#editstaffFrom").form("validate");
                                        if(v){
                                            $("#editstaffFrom").submit()
                                        }else {
                                        $.messager().alert("提示信息","取派员信息修改失败","warning");
                                    }
                                });
                                <%--自定义手机号的校验规则--%>
                                $.extend($.fn.validatebox.defaults.rules, {
                                    telephone: {
                                        validator: function(value, param){
                                            var reg=/^1[1|3|4|7|8][0-9]{9}$/
                                            return reg.test(value);
                                        },
                                        message: '手机号输入有误.'
                                    }
                                });
                            });

						</script>
						<td><input type="text"  data-options="validType:'telephone'" name="telephone" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>单位</td>
						<td><input type="text" name="station" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="checkbox" name="haspda" value="1" />
							是否有PDA</td>
					</tr>
					<tr>
						<td>取派标准</td>
						<td>
							<input type="text" name="standard" class="easyui-validatebox" required="true"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>	