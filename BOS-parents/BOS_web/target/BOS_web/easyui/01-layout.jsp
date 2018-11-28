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
    <%--引入ztree对象--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/ztree/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/ztree/jquery.ztree.all-3.5.js"></script>
<%--节点的函数--%>
    <script>
        function zTreeOnCheck(event, treeId, treeNode) {
            alert(treeNode.tId + ", " + treeNode.name + "," + treeNode.checked);
        };
    </script>

</head>
<body class="easyui-layout">
<%--使用div布局--%>
<%--<div class="easyui-layout">--%>
    <%--<div style="height: 100px" data-options="region:'north'">北部区域</div>--%>
    <%--<div style="height: 100px" data-options="region:'west'">西部区域</div>--%>
    <%--<div style="height: 100px" data-options="region:'east'">东部区域</div>--%>
    <%--<div style="height: 100px" data-options="region:'south'">南部区域</div>--%>
<%--</div>--%>
<!-- 使用div元素描述每个区域 -->
<div region="north" title="xxx管理系统" border="false" style="height:60px;background:#B3DFDA;padding:10px">north region</div>
<div region="west" split="true" title="系统菜单" style="width:150px;padding:10px;">
    <%--嵌套面板
    fit:true:填充父元素
    --%>
    <div   class="easyui-accordion" data-options="fit:true">
        <div title="面板一" >
            <a  id="btn1" href="#" class="easyui-linkbutton">添加选项卡
            <script>
                $(function () {
                    $("#btn1").click(function () {
                       //添加属性面板
                    // $("#mytbs").tabs('add',{
                    //     title:'添加面板',
                    //     content:'Tab Body',
                    //     closable:true,
                    // })
                        var  e=$("#mytbs").tabs("exists","系统管理");
                        //判断面板是否已经存在，否则就动态添加
                        if(e){
                            //已经存在选中就可以
                            $("#mytbs").tabs("select","系统管理");
                        }else{
                            $("#mytbs").tabs('add',{
                             // title:'添加面板',
                             // content:'Tab Body',
                             // closable:true,
                                title:'系统管理',
                                iconCls:'icon-edit',
                                closable:true,
                                content:'<iframe frameborder="0" height="100%" width="100%" src="https://www.baidu.com"></iframe>'
                            })
                        }
                    });
                });
            </script>
            </a>
        </div>
        <%--<iframe frameborder="0" width="100%" height="100%"  src="https://www.baidu.com"></iframe>--%>
        <div title="面板二">
            <%--使用面板二构ztree--%>
            <%--展示ztree效果，使用标准得json对象构造--%>
                <ul id="ztree1" class="ztree"></ul>

            <script>
             $(function () {
                 <%--页面加载完成后进行执行js代码--%>
                 var  setting={ };
             //  构造树节点
                 var  zNodes=[
                     {"name":"节点一","children":[
                             {"name":"子节点一"},
                             {"name":"子节点二"}
                         ]
                     },
                     {"name":"节点二"},
                     {"name":"节点三"}
                 ];
                 //初始化api
                 //使用json动态的显示数据

                 $.fn.zTree.init($("#ztree1"), setting, zNodes );
             })
            </script>
        </div>
        <div title="面板三">

            <ul id="ztree2" class="ztree"></ul>

            <script>

                <%--$(function () {--%>
                    <%--var  setting2={ };--%>
                    <%--var  zNodes2=[--%>
                        <%--{"name":"节点一","children":[--%>
                                <%--{"name":"子节点一"},--%>
                                <%--{"name":"子节点二"}--%>
                            <%--]--%>
                        <%--},--%>
                        <%--{"name":"节点二"},--%>
                        <%--{"name":"节点三"}--%>
                    <%--];--%>
                    //使用json动态的显示数据,使用ajax方发送请求
                    <%--var  url="${pageContext.request.contextPath}/json/menu.json"--%>
                <%--$.post(url,{},function (data) {--%>
                    <%--$.fn.zTree.init($("#ztree2"), setting2, data );--%>
                <%--}),--%>
                    <%--"json"--%>

                <%--&lt;%&ndash;})&ndash;%&gt;--%>
                <%--使用简单的json构造节点--%>
                $(function(){
                    //页面加载完成后，执行这段代码----动态创建ztree
                    var setting2 = {
                        data: {
                            simpleData: {
                                enable: true//使用简单json数据构造ztree节点
                            }
                        },
                        //为ztree绑定事件
                        callback:{
                            onClick:function (event,treeId,treeNode) {
                                 alert("你点击了"+treeNode.name);
                                if(treeNode.name!=undefined){
                                    var exists=$("#mytbs").tabs("exists",treeNode.name);
                                    if(exists){
                                        //选中选项卡
                                        $("#mytbs").tabs("select",treeNode.name);
                                    }else{
                                        $("#mytbs").tabs("add",{
                                            title:treeNode.name,
                                            content:'<iframe frameborder="0" height="100%" width="100%" src="'+treeNode.page+'"></iframe>'
                                        });
                                    }
                                }
                            }
                        }
                        }
                    //构造节点数据
                    var  url="${pageContext.request.contextPath}/json/menu.json"
                    $.post(url,{},function (data) {
                        //调用API初始化ztree
                        $.fn.zTree.init($("#ztree2"), setting2, data);
                    })
                    "json"

                });


            </script>
        </div>
    </div>
</div>
<div region="east" split="true" collapsed="true" title="East" style="width:100px;padding:10px;">east region</div>
<div region="south" border="false" style="height:50px;background:#A9FACD;padding:10px;">south region</div>
<%--为中间部分添加内容--%>
<div region="center" title="Main Title"  class="easyui-tabs" >
    <div class="easyui-tabs" data-options="fit:true"  id="mytbs">
    <div  data-options="closeable:true" title="面板一" ></div>
    <div title="面板二">22222</div>
    <div title="面板三">33333</div>
        <div data-options="closable:true" title="面板二">2222</div>
    </div>
</div>

</body>
</html>
