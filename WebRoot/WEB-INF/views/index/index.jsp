<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>index</title>
    <link rel="stylesheet" href="/static/css/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/metisMenu.css">
    <link rel="stylesheet" href="/static/css/sideMenu.css">
    <link rel="stylesheet" href="/static/css/index.css">
    <link rel="stylesheet" href="/static/css/iziToast.min.css"> <!--消息框-->
</head>
<body>
                                          <!--header-->
<div class="navbar navbar-default " role="navigation" id="header">
    <div id="logo" class="">
        <a href=""><img src="/static/img/logo.png " class="img-responsive" alt=""></a>
    </div>
    <nav id="topmenu">
        <div class="container-fluid">
            <div class="nav-header">
                <button type="button" class="navbar-toggle"  data-toggle="collapse" data-target="#topmenumain">
                    <span class="sr-only">切换导航</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <!--<a class="navbar-brand" href="#">菜鸟教程</a>-->
            </div>
        </div>
        <div class=" collapse navbar-collapse" id="topmenumain">
            <ul  class="nav navbar-nav navbar-left " >
                <li><a href="#"><span class="glyphicon glyphicon-align-justify "></span></a></li>
                <li class="user"><a href="#"><span class="glyphicon glyphicon-user icon-white"></span></a></li>
                <li><a href="#" ><span class="glyphicon glyphicon-lock icon-white"></span></a></li>
            </ul>
            <div id="topsearch" class="nav navbar-nav center-block">
                <div class="input-group">
                    <input type="text" class="form-control">
                    <span class="input-group-btn">
                        <button class="btn btn-info" type="button"><span class="glyphicon glyphicon-search "></span></button>
                    </span>
                </div>
            </div>
            <ul class=" nav navbar-nav navbar-right" >
                <li id="search"><a href="#" ><span class="glyphicon glyphicon-search "></span></a></li>
                <li><a href="#" ><span class="glyphicon glyphicon-resize-full "></span></a></li>
                <li><a href="#" ><span class="glyphicon glyphicon-log-out "></span></a></li>
                <li id="msg" >
                    <a href="#"  data-container="body" data-toggle="popover" data-placement="bottom"
                       data-content="右侧的 Popover 中的一些内容" data-animation="true">
                        <span class="glyphicon glyphicon-bell "></span>
                        <span class="label label-danger " style="position:absolute;top:2px;left: 22px;display:inline-block;">3</span>
                    </a>
                </li>
                <li><a href="#" ><span class="glyphicon glyphicon-book "></span></a></li>
            </ul>
        </div>
    </nav>
</div>
       <ul class="list-group msglist">
            <li class="list-group-item"><a href=""><span class="glyphicon glyphicon-folder-open"></span>
                 <span> 新事件 </span> <small>2个新事件</small>
                 </a><span class="badge  right">新</span></li>
            <li class="list-group-item"><a href=""><span class="glyphicon glyphicon-envelope"></span>
                <span> 新邮件 </span> <small>您有10封信</small>
                </a><span class="badge right">新</span></li>
            <li class="list-group-item"><a href=""><span class="glyphicon glyphicon-tasks"></span>
                <span>当前任务 </span> <small>11条工作需要处理</small></a></li>
            <li class="list-group-item"><a href="">更多提醒</a><span class="label label-danger" style="float: right">14</span></li>
       </ul>
<!--<button class="send " style="position: fixed;right: 300px;">dgsd</button>                                     &lt;!&ndash;main&ndash;&gt;-->
<div id="main" class="container" >
    <!--sider-->
    <div id="sider">
        <aside class="sidebar">
            <div class="infor">
                <div class="pic center-block">
                    <img src="img/pic.png" class="img-responsive img-circle" alt="">
                </div>
                <div class="">
                    <h4 class="text-center">您好，***</h4>
                    <h5 class="text-center" style="color: #f5f5f5">系统管理员</h5>
                </div>
                <ul class="nav">
                    <li><a href="account.html" target="show">个人账户</a></li>
                    <li><a href="infor.html" target="show">个人信息</a></li>
                </ul>
            </div>
            <h5>业务导航</h5>
            <div id="servicenav" class="optiscroll" >
                <nav class="sidebar-nav sidemenu1">
                    <!--<ul id="menu" class="metismenu menu" class="" style="height: 0px;" aria-expanded="true">-->
                    <!--<li class=""><a href="login.html" aria-expanded="true" >Menu 0-->
                    <!--<i class="icon-chevron-right" style="float: right"></i> </a>-->
                    <!--<ul class="" style="height: 0px;" aria-expanded="true">-->
                    <!--<li><a href="page1.html" target="show">item 0.1</a></li>-->
                    <!--<li><a href="page2.html" target="show">item 0.2</a></li>-->
                    <!--<li><a href="page3.html" target="show">item 0.3</a></li>-->
                    <!--<li><a href="page4.html" target="show">item 0.4</a></li>-->
                    <!--</ul>-->
                    <!--</li>-->
                    <!--<li class=""><a href="#" aria-expanded="false" >Menu 1-->
                    <!--<i class="icon-chevron-right" style="float: right"></i> </a>-->
                    <!--<ul class="" style="height: 0px;" aria-expanded="true">-->
                    <!--<li><a href="#">item 1.1</a></li>-->
                    <!--<li><a href="#" aria-expanded="true">item 1.2 <i class="icon-chevron-right" style="float: right"></i>-->
                    <!--</a>-->
                    <!--<ul class="" style="height: 0px;" aria-expanded="true">-->
                    <!--<li><a href="#">item 1.2.1</a></li>-->
                    <!--<li><a href="#">item 1.2.2</a></li>-->
                    <!--<li><a href="#">item 1.2.3</a></li>-->
                    <!--<li><a href="#">item 1.2.4</a></li>-->
                    <!--</ul>-->
                    <!--</li>-->
                    <!--<li><a href="#">item 1.3</a></li>-->
                    <!--<li><a href="#">item 1.4</a></li>-->
                    <!--</ul>-->
                    <!--</li>-->
                    <!--<li class=""><a href="#" aria-expanded="false" >Menu 2-->
                    <!--<i class="icon-chevron-right" style="float: right"></i></a>-->
                    <!--<ul class="" style="height: 0px;" aria-expanded="true">-->
                    <!--<li><a href="#">item 2.1</a></li>-->
                    <!--<li><a href="#">item 2.2</a></li>-->
                    <!--<li><a href="#">item 2.3</a></li>-->
                    <!--<li><a href="#">item 2.4</a></li>-->
                    <!--</ul>-->
                    <!--</li>-->
                    <!--<li class=""><a href="#" aria-expanded="false" >Menu 3-->
                    <!--<i class="icon-chevron-right" style="float: right"></i></a>-->
                    <!--<ul class="" style="height: 0px;" aria-expanded="true">-->
                    <!--<li><a href="#">item 3.1</a></li>-->
                    <!--<li><a href="#">item 3.2</a></li>-->
                    <!--<li><a href="#">item 3.3</a></li>-->
                    <!--<li><a href="#">item 3.4</a></li>-->
                    <!--</ul>-->
                    <!--</li>-->
                    <!--<li class=""><a href="#" aria-expanded="false" >Menu 4-->
                    <!--<i class="icon-chevron-right" style="float: right"></i></a>-->
                    <!--<ul class="" style="height: 0px;" aria-expanded="true">-->
                    <!--<li><a href="#">item 4.1</a></li>-->
                    <!--<li><a href="#" aria-expanded="false">item 4.2 <i class="icon-chevron-right" style="float: right"></i></a>-->
                    <!--<ul class="" style="height: 0px;" aria-expanded="true">-->
                    <!--<li><a href="#">item 4.2.1</a></li>-->
                    <!--<li><a href="#">item 4.2.2</a></li>-->
                    <!--<li><a href="#">item 4.2.3</a></li>-->
                    <!--<li><a href="#">item 4.2.4</a></li>-->
                    <!--</ul>-->
                    <!--</li>-->
                    <!--<li><a href="#">item 4.3</a></li>-->
                    <!--<li><a href="#">item 4.4</a></li>-->
                    <!--</ul>-->
                    <!--</li>-->
                    <!--</ul>-->
                </nav>
            </div>
            <h5>功能模块</h5>
            <div id="functionnav" class="optiscroll">
                <nav class="sidebar-nav sidemenu2">
                    <!--<ul id="menu" class="metismenu menu" class="" style="height: 0px;" aria-expanded="true">-->
                    <!--<li class=""><a href="#" aria-expanded="true" >Menu 0-->
                    <!--<i class="icon-chevron-right" style="float: right"></i> </a>-->
                    <!--<ul class="" style="height: 0px;" aria-expanded="true">-->
                    <!--<li><a href="page1.html" target="show">item 0.1</a></li>-->
                    <!--<li><a href="page2.html" target="show">item 0.2</a></li>-->
                    <!--<li><a href="page3.html" target="show">item 0.3</a></li>-->
                    <!--<li><a href="page4.html" target="show">item 0.4</a></li>-->
                    <!--</ul>-->
                    <!--</li>-->
                    <!--<li class=""><a href="#" aria-expanded="false" >Menu 1-->
                    <!--<i class="icon-chevron-right" style="float: right"></i> </a>-->
                    <!--<ul class="" style="height: 0px;" aria-expanded="true">-->
                    <!--<li><a href="#">item 1.1</a></li>-->
                    <!--<li><a href="#" aria-expanded="true">item 1.2 <i class="icon-chevron-right" style="float: right"></i>-->
                    <!--</a>-->
                    <!--<ul class="" style="height: 0px;" aria-expanded="true">-->
                    <!--<li><a href="#">item 1.2.1</a></li>-->
                    <!--<li><a href="#">item 1.2.2</a></li>-->
                    <!--<li><a href="#">item 1.2.3</a></li>-->
                    <!--<li><a href="#">item 1.2.4</a></li>-->
                    <!--</ul>-->
                    <!--</li>-->
                    <!--<li><a href="#">item 1.3</a></li>-->
                    <!--<li><a href="#">item 1.4</a></li>-->
                    <!--</ul>-->
                    <!--</li>-->
                    <!--<li class=""><a href="#" aria-expanded="false" >Menu 2-->
                    <!--<i class="icon-chevron-right" style="float: right"></i></a>-->
                    <!--<ul class="" style="height: 0px;" aria-expanded="true">-->
                    <!--<li><a href="#">item 2.1</a></li>-->
                    <!--<li><a href="#">item 2.2</a></li>-->
                    <!--<li><a href="#">item 2.3</a></li>-->
                    <!--<li><a href="#">item 2.4</a></li>-->
                    <!--</ul>-->
                    <!--</li>-->
                    <!--<li class=""><a href="#" aria-expanded="false" >Menu 3-->
                    <!--<i class="icon-chevron-right" style="float: right"></i></a>-->
                    <!--<ul class="" style="height: 0px;" aria-expanded="true">-->
                    <!--<li><a href="#">item 3.1</a></li>-->
                    <!--<li><a href="#">item 3.2</a></li>-->
                    <!--<li><a href="#">item 3.3</a></li>-->
                    <!--<li><a href="#">item 3.4</a></li>-->
                    <!--</ul>-->
                    <!--</li>-->
                    <!--<li class=""><a href="#" aria-expanded="false" >Menu 4-->
                    <!--<i class="icon-chevron-right" style="float: right"></i></a>-->
                    <!--<ul class="" style="height: 0px;" aria-expanded="true">-->
                    <!--<li><a href="#">item 4.1</a></li>-->
                    <!--<li><a href="#" aria-expanded="false">item 4.2 <i class="icon-chevron-right" style="float: right"></i></a>-->
                    <!--<ul class="" style="height: 0px;" aria-expanded="true">-->
                    <!--<li><a href="#">item 4.2.1</a></li>-->
                    <!--<li><a href="#">item 4.2.2</a></li>-->
                    <!--<li><a href="#">item 4.2.3</a></li>-->
                    <!--<li><a href="#">item 4.2.4</a></li>-->
                    <!--</ul>-->
                    <!--</li>-->
                    <!--<li><a href="#">item 4.3</a></li>-->
                    <!--<li><a href="#">item 4.4</a></li>-->
                    <!--</ul>-->
                    <!--</li>-->
                    <!--</ul>-->
                </nav>
            </div>
            <%--<a href="manageAudit.html" target="show">点击进入注册管理</a> <br>--%>
            <%--<a href="dataPermission.html" target="show">点击进入数据权限管理</a> <br>--%>
            <%--<a href="orgdepartment.html" target="show">点击进入组织管理</a>--%>
        </aside>
    </div>
    <div id="content">
        <a href="../manageAudit.html" target="show">点击进入注册管理</a>
        <iframe src="" name="show" width="100%"></iframe>
    </div>
<!--</div>-->

</div>
<script src="/static/js/jquery-2.1.1.min.js"></script>
<script src="/static/js/bootstrap/bootstrap.min.js"></script>
<script src="/static/js/bootstrap/popover.js"></script>
<script src="/static/js/bootstrap/dropdown.js"></script>
<script src="/static/js/metisMenu.min.js"></script>
<script src="/static/js/menu_data.js"></script>
<script src="/static/js/iziToast.min.js"></script> <!--消息框-->
<!--<script src="js/turnBox.js"></script>&lt;!&ndash;旋转立方体&ndash;&gt;-->
<script src="/static/js/common.js"></script><script src="/static/js/jquery.gradientify.min.js"></script>
<script>
    //背景颜色

//获取模块菜单
    var str="";
//    function getMenu(data) {
//        str+="<ul class='metismenu' style='height: 0px;' aria-expanded='true'>";
//        $(data).each(function (index) {
//            var turn= data[index].target=="#"? "javascript:void(0)":data[index].target+".html";
//            str+="<li class=''><a href='"+turn+"' aria-expanded='true' target='show'>"
//                    +data[index].name
//                    +"</a>";
//            if(data[index].child!=null){
//                getMenu(data[index].child);
//            }
//            str+="</li>";
//        })
//        str+="</ul>";
//        return str;
//    }
    function getMenu(data) {
        str+="<ul class='metismenu' style='height: 0px;' aria-expanded='true'>";
        $(data).each(function (index) {
            var turn= data[index].url=="#"? "javascript:void(0)":data[index].url+".html";
            str+="<li class=''><a href='"+turn+"' aria-expanded='true' target='show'>"
                    +data[index].title
                    +"</a>";
            if(data[index].childList.length>0){
                getMenu(data[index].childList);
            }
            str+="</li>";
        })
        str+="</ul>";
        return str;
    }
    //查询菜单信息
    $.ajax({
        url:"MenuAction_queryAccountMenus.do",
        type:"post",
        data:"",
        contentType:"application/json",
        dataType:"html json",
        success:function (data) {

            var jsonStr=data;
            var jsonObj =  JSON.parse(jsonStr);
//            console.log(jsonObj);
//            var rs=getMenu(jsonObj);
//            $(".sidemenu2").append(rs);
//            $(".sidemenu1").append(rs);
//            $(".sidebar-nav>ul").addClass("menu");
            var menu=jsonObj.menus;
            var rs=getMenu(menu);
            $(".sidemenu2").append(rs);
            $(".sidemenu1").append(rs);
            $(".sidebar-nav>ul").addClass("menu");
//            console.log(menu[0].url);
//            $(menu).each(function (i) {
//                console.log(menu[i].url);
//            })

        }
    })

    //    var rs=getMenu(menu);
//    $(".sidebar-nav").append(rs);
//    $(".sidebar-nav>ul").addClass("menu");
    $('#sider .menu').metisMenu({
            toggle: false
        });

</script>

</body>
</html>