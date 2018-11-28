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
    <script>
        <%--创建message方法--%>
        $(function () {
            //alert('警告框')
            $.messager.alert('我是一个帅哥','这是一个提示信息！');
            $.messager.confirm('帅哥','哈哈',function (r) {
                // fn(b): 当用户点击“确定”按钮的时侯将传递一个true值给回调函数，否则传递一个false值。
                alert(r)
            })
            //show方法，显示一个欢饮框，显示在底部
            $.messager.show({
                    title:'欢迎您',
                    msg:'欢迎admin登入',
                    showType:'slide',
                }
            )

        });

    </script>
</head>
<body>




</body>
</html>
