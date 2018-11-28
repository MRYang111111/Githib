
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
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ocupload-1.1.2.js"></script>
    <%--书写js函数动态的改变html的元素--%>
    <script>
        $(function () {
           $("#btn1").upload({
                   action:'xxx.action',
                   method:'post',
                   name:'myfile'
           }
           ) ;
        });
    </script>
</head>
<body>
<input type="button" id="btn1" value="文件上传">
</body>
</html>
