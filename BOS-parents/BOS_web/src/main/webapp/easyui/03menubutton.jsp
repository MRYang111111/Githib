<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/10/29/029
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>
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

</head>
<body>
<%--制作菜单--%>
<%--<a href="$"   data-options="iconCls:'icon-help" class="easyui-menubutton">控制面板</a>--%>
<a  data-options="iconCls:'icon-help'，menu:'#mm'" class="easy-menubutton">控制面板</a>
<%--<a data-options="iconCls:'icon-help',menu:'#mm'" class="easyui-menubutton">控制面板</a>--%>
<a data-options="iconCls:'icon-help',menu:'#mm'" class="easyui-menubutton">控制面板</a>
<a data-options="iconCls:'icon-help',menu:'#mm'" class="easyui-menubutton">控制面板222</a>
<div id="mm">
    <div onclick="alert(111)" data-options="iconCls:'icon-edit'">修改密码</div>
    <div>联系管理员</div>
    <div>退出登入</div>

</div>


</body>
</html>
