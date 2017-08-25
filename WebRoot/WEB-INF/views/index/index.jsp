<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>index</title>
    <link rel="stylesheet" href="/static/css/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/metisMenu/metisMenu.css">
    <link rel="stylesheet" href="/static/css/sideMenu.css">
    <link rel="stylesheet" href="/static/css/index.css">
    <link rel="stylesheet" href="/static/css/optiscroll.css">
    <link rel="stylesheet" href="/static/css/iziToast.min.css"> <!--消息框-->
    <style>
        body{
            /*width: 80%;*/
            /*margin: 0 auto;*/
            background: #000000;
        }
    </style>
</head>
<body>
<div id="bg" style="position: fixed;width: 100%;height: 100%;z-index: -1">
</div>
<div id="lock">
    <div style="margin: 200px auto">
        <h3 class="text-center">请输入登录密码解锁</h3>
        <div class="unlock">
            <input type="password" >
            <span></span>
            <div class="switch-box">
                <input id="default" class="switch-box-input" type="checkbox" style="display: none"/>
                <label for="default" class="switch-box-slider"></label>
                <label for="default" class="switch-box-label">解锁</label>
            </div>
        </div>
    </div>
</div>

<!--header-->
<div class="navbar" role="navigation" id="header">
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
                <li class="hidemenu"><a href="#"><span class="glyphicon glyphicon-align-justify "></span></a></li>
                <li class="user"><a href="#"><span class="glyphicon glyphicon-user icon-white"></span></a></li>
                <li class="lockscreen"><a href="#" ><span class="glyphicon glyphicon-lock icon-white"></span></a></li>
            </ul>
            <ul class=" nav navbar-nav navbar-right" >
                <li id="search"><a href="#" ><span class="glyphicon glyphicon-search "></span></a></li><!--搜索框-->
                <li id="fullscreen"><a href="#" ><span class="glyphicon glyphicon-resize-full "></span></a></li>
                <li><a href="LoginOutAction_quit.do" ><span class="glyphicon glyphicon-log-out "></span></a></li>
                <li id="msg" >
                    <a href="#"  data-container="body" data-toggle="popover" data-placement="bottom"
                       data-content="右侧的 Popover 中的一些内容" data-animation="true">
                        <span class="glyphicon glyphicon-bell "></span>
                        <span class="label label-danger " style="position:absolute;top:2px;left: 22px;display:inline-block;">3</span>
                    </a>
                </li>
                <li><a href="#" ><span class="glyphicon glyphicon-book "></span></a></li>
            </ul>
            <div id="topsearch" class="nav navbar-nav navbar-right">
                    <form action="SearchAction_toSearch.do" target="show" method="post"  class="input-group">
                    <input type="text" name="searchecontent" id="searchecontent" class="form-control">
                    <span class="input-group-btn">
                        <button class="btn btn-info" type="submit" ><span class="glyphicon glyphicon-search "></span></button><!--<a href="SearchAction_toSearch.do" target="show">搜索框进行查询-->
                    </span>
                    </form>
            </div>
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
                         <img src="${picture_path}" class="img-responsive img-circle" alt="">
                     </div>
                     <div class="">
                         <h4 class="text-center">您好，${name}</h4>
                         <h5 class="text-center" style="color: #f5f5f5">系统管理员</h5>
                     </div>
                <ul class="nav">
                    <li><a href="IndexAction_toAccount.do" target="show">个人账户</a></li>
                    <li><a href="IndexAction_toInfor.do" target="show">个人信息</a></li>
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
            <a href="manageAudit.html" target="show">点击进入注册管理</a> <br>
            <a href="dataPermission.html" target="show">点击进入数据权限管理</a> <br>
            <a href="orgdepartment.html" target="show">点击进入组织管理</a> <br>
        </aside>
    </div>
    <div id="content">
        <iframe src="" id="contentiframe" name="show" width="100%"></iframe>
    </div>
    <div id="theme">

    </div>
<!--</div>-->
<!--<button id="tmp">button</button>-->
</div>
<script src="/static/js/jquery/jquery-2.1.1.min.js"></script>
<script src="/static/js/bootstrap/bootstrap.min.js"></script>
<script src="/static/js/jquery.cookie.js"></script><!--顶部菜单背景-->
<script src="/static/js/jquery/jquery.optiscroll.js"></script> <!--滚动条样式-->
<script src="/static/js/metisMenu/metisMenu.min.js"></script> <!--侧边菜单样式-->
<%--<script src="/static/js/menu_data.js"></script><!--动态菜单数据-->--%>
<script src="/static/js/index.js"></script>
<script src="/static/js/jquery.warpdrive.min.js"></script><!--顶部菜单背景-->
<script src="/static/js/tojson.js"></script><!--顶部菜单背景-->
<script>
    if($.cookie('lock')=='lock'){
        lockscreen();
    }else{
        unlockscreen();
    }
    var settings = {
        width: 800,
        height:800,
        autoResize: true,
        autoResizeMinWidth: 5,
        autoResizeMaxWidth: null,
        autoResizeMinHeight: null,
        autoResizeMaxHeight: null,
        addMouseControls: true,
        addTouchControls: true,
        hideContextMenu: true,
        starCount: 6666,
        starBgCount: 2222,
        starBgColor: { r:0, g:204, b:255 },
        starBgColorRangeMin: 20,
        starBgColorRangeMax: 80,
        starColor: { r:0, g:204, b:255 },
        starColorRangeMin: 50,
        starColorRangeMax: 100,
        starfieldBackgroundColor: { r:5, g:5, b:14 },
        starDirection: 1,
        starSpeed: 20,
        starSpeedMax: 200,
        starSpeedAnimationDuration: 2,
        starFov: 300,
        starFovMin: 200,
        starFovAnimationDuration: 2,
        starRotationPermission: true,
        starRotationDirection: 1,
        starRotationSpeed: 0.0,
        starRotationSpeedMax: 1.0,
        starRotationAnimationDuration: 2,
        starWarpLineLength: 2.0,
        starWarpTunnelDiameter: 100,
        starFollowMouseSensitivity: 0.025,
        starFollowMouseXAxis: true,
        starFollowMouseYAxis: true

    };
    $( '#bg' ).warpDrive(settings);
//    $( '#sider' ).warpDrive(settings);
    //控制全屏
    function enterfullscreen() {//进入全屏
        var docElm = document.documentElement;
//W3C
        if (docElm.requestFullscreen) {
            docElm.requestFullscreen();
        }
//FireFox
        else if (docElm.mozRequestFullScreen) {
            docElm.mozRequestFullScreen();
        }
//Chrome等
        else if (docElm.webkitRequestFullScreen) {
            docElm.webkitRequestFullScreen();
        }
//IE11
        else if (elem.msRequestFullscreen) {
            elem.msRequestFullscreen();
        }
    }
    function exitfullscreen() { //退出全屏
        if (document.exitFullscreen) {
            document.exitFullscreen();
        }
        else if (document.mozCancelFullScreen) {
            document.mozCancelFullScreen();
        }
        else if (document.webkitCancelFullScreen) {
            document.webkitCancelFullScreen();
        }
        else if (document.msExitFullscreen) {
            document.msExitFullscreen();
        }
    }
    var a=0;
    $('#fullscreen').on('click',function () {
        a++;
        a%2==1?enterfullscreen():exitfullscreen();
    })


    //获取模块菜单
    var str;
    function getMenus(data) {
             str="";
            return getMenu(data)
    }
    function getMenu(data) {
        str+="<ul class='metismenu' style='height:0px' aria-expanded='true'>";
        $(data).each(function (index) {
            var turn= data[index].url==""? "javascript:void(0)":data[index].url+".do";
            str+="<li class=''><a href='"+turn+"' aria-expanded='true' target='show'>"
                    +data[index].title
                    +"</a>";
            if(data[index].childList.length>=1){
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
            console.log(jsonObj);
            var menu=jsonObj.menus;
            var rs1=getMenus(menu);
            $(".sidemenu1").append(rs1);

            var funcmenu=jsonObj.function_menus;
            var rs2=getMenus(funcmenu);
            $(".sidemenu2").append(rs2);

            $(".sidebar-nav>ul").addClass("menu");
            $('#sider .menu').metisMenu({
                toggle: false
            });
//            $(menu).each(function (i) {
//                console.log(menu[i].url);
//            })

        }
    })

    //    var rs=getMenu(menu);
    //    $(".sidebar-nav").append(rs);
    //    $(".sidebar-nav>ul").addClass("menu");
//    $('#sider .menu').metisMenu({
//        toggle: false
//    });
</script>
<script type="text/javascript">
    //修改菜单栏滚动条样式
    var m1 = new Optiscroll(document.getElementById('servicenav'),
            { forceScrollbars: false });
    var m2=new Optiscroll(document.getElementById('functionnav'),
            { forceScrollbars: false
            })
</script>
</body>
</html>
