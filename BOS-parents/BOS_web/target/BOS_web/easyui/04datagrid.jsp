
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%--easyui的资源文件引入--%>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/default/easyui.css">
    <%--映入icon的样式文件--%>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/locale/easyui-lang-zh_CN.js"></script>
    <%--引入ztree对象--%>
    <script type="text/javascript">
        $(function () {
            $("#mytable").datagrid(
                {
                    //定义所有的列
                    columns:[[
                        {field:'name',title:'姓名',width:100,},
                        {field:'age',title:'年龄',width:100},
                        {field:'order',title:'大家',width:100,align:'right'}
                    ]],
                    //请求ajax数据
                    url:'${pageContext.request.contextPath}/json/datagrid.json',
                    //行号
                rownumbers:true,
                //鼠标单选
                singleSelect:true,
                //toolBar
                toolbar:[
                            {text:'添加',iconCls:'icon-add',
                                //为按钮绑定事件
                                handler:function () {
                                alert('hello java')
                        }
                    },
                    {text:'修改',iconCls:'icon-update'},
                    {text:'删除',iconCls:'icon-delete'},
                    {text:'查询',iconCls:'icon-select'}
                ],
                  //分页栏
                    pagination:true
                }
        )
        });
    </script>
</head>
<body>
<%--静态的datagrid--%>
<table class="easyui-datagrid">
    <thead>
    <tr >
        <th data-options="field:'name'">name</th>
        <th data-options="field:'name'">name</th>
        <th data-options="field:'name'">name</th>
    </tr>
        </thead>
    <tbody>
    <tr>
     <td>xiaoy1</td>
      <td>xiaoy1</td>
        <td>xiaoy1</td>
    </tr>
    <tr>
        <td>xiaoy1</td>
        <td>xiaoy1</td>
        <td>xiaoy1</td>
    </tr>
    </tbody>
</table>

<%--/
--%>
<%--使用easyui的插件去做--%>
<table id="mytable">

</table>
</body>
</html>
