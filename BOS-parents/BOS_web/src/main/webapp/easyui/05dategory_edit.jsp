
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
            //创建一个全局变量
            var  myIndex=-1;
            $("#mytable").datagrid(
                {
                    //定义所有的列
                    columns:[[
                        {field:'name',title:'姓名',width:100,width:150,editor:{
                                type:',validatebox',
                                option:{}
                            }},
                        {field:'age',title:'年龄',width:100,width:150,editor:{
                            type:',numberbox',
                                option:{}
                            }},
                        {field:'order',title:'日期',width:100,width:150,align:'right',editor:{
                                type:'datebox',
                                option:{}
                            }}
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
                                //添加一行
                                alert('hello java');
                                    // $("#mytable").datagrid("insertRow",{
                                    //     index:0,
                                    //     row:{}
                                    // });
                                    // $("#mytable").datagrid("beginEdit",0);
                                    // myIndex=0;
                                    $("#mytable").datagrid("insertRow",{
                                        index:0,//在第一行插入数据
                                        row:{}//空行
                                    })
                                    $("#mytable").datagrid("beginEdit",0)
                                    myIndex = 0;
                        }
                    },
                    {text:'修改',iconCls:'icon-edit',handler:function () {
                           //获取选择的行
                           //      var  row=$("#mytable").datagrid("getSelections");
                           //      if(index.length==1){
                           //          var rows= row[0];
                           //          myIndex=$("#mytable").datagrid("getRowIndex",rows);
                           //      }
                           //  $("#mytable").datagrid("beginEdit",myIndex);
                            //获取选择的行
                            var  rows=$("#mytable").datagrid("getSelections");
                            if(rows.length==1){
                                var  index=rows[0];
                                //获取对象的所有索引
                                myIndex=$("#mytable").datagrid("getRowIndex",index);
                            }
                            $("#mytable").datagrid("beginEdit",myIndex);
                        }},
                    {text:'删除',iconCls:'icon-delete',handler:function () {
                            var  rows=$("#mytable").datagrid("getSelections");
                            if(rows.length==1){
                                var  index=rows[0];
                                //获取对象的所有索引
                                myIndex=$("#mytable").datagrid("getRowIndex",index);
                            }
                            $("#mytable").datagrid("deleteRow",myIndex);
                        }},
                    {text:'保存',iconCls:'icon-save',handler:function(){
                            $("#mytable").datagrid("endEdit",myIndex);
                        }},
                    {text:'查询',iconCls:'icon-select'}
                ],
                  //分页栏
                    pagination:true,
                    pageList:[1,3,5,7],
                    onAfterEdit:function (index,data,changes) {
                        alert("修改后编辑"+data.name);
                        console.info(data)
                        $.post();
                    }

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
