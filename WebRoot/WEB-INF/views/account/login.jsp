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
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="/static/css/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/login.css">
    <link rel="stylesheet" href="/static/css/iziToast.min.css"> <!--消息框-->
</head>
<body>
<div id="title">
    <a href=""><img src="/static/img/logo-darker.png" class="img-responsive" alt=""></a>
</div>
<div id="main" style="position: absolute; width: 100%; height: 100%; z-index: -1;">
    <!--<img style="position: fixed" src="img/1.jpg" alt="" height="100%" width="100%" class="bgd">-->
    <div class="row">
    <div id="myCarousel" class="carousel slide col-md-6">
                <!-- 轮播（Carousel）指标 -->
                <!--<ol class="carousel-indicators">-->
                    <!--<li data-target="#myCarousel" data-slide-to="0" class="active"></li>-->
                    <!--<li data-target="#myCarousel" data-slide-to="1"></li>-->
                    <!--<li data-target="#myCarousel" data-slide-to="2"></li>-->
                <!--</ol>-->
                <!-- 轮播（Carousel）项目 -->
                <div class="carousel-inner" id="Carouselitem">
                    <div class="item active">
                        <img src="/static/img/1.jpg" class="img-responsive" alt="First slide">
                    </div>
                    <div class="item">
                        <img src="/static/img/2.jpg" class="img-responsive" alt="Second slide">
                    </div>
                    <div class="item">
                        <img src="/static/img/3.1.jpg" class="img-responsive" alt="Third slide">
                    </div>
                </div>
                 <!--轮播（Carousel）导航 -->
                <!--<a class="carousel-control left" href="#myCarousel"-->
                   <!--data-slide="prev">&lsaquo;-->
                <!--</a>-->
                <!--<a class="carousel-control right" href="#myCarousel"-->
                   <!--data-slide="next">&rsaquo;-->
                <!--</a>-->
    </div>
  <div class="col-md-6" id="right">
    <div id="content" >
            <div id="publicity">
                <p class="text-left">智慧电梯云管中心</p>
                <small class=" lead text-muted text-left">智慧电梯集中管控平台，设备物联和全程跟踪、单位及人员协同、常规作业安排并实施、预测预警和突发应急救援</small>
            </div>
            <div class="msg"></div>
            <form class="form-horizontal form" role="form" id="loginform" method="post">
                <div class="form-group">
                    <label for="email" class="col-sm-2 control-label name">邮箱</label>
                    <div class="col-sm-9">
                        <input type="email" name="email" class="form-control inputmain" id="email" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="col-sm-2 control-label name">密码</label>
                    <div class="col-sm-9">
                        <input type="password" class="form-control inputmain" name="password" id="password" />
                    </div>
                </div>
                <div class="form-group" >
                    <div class="col-sm-offset-2 col-sm-7" >
                        <div class="checkbox" style="float: left">
                            <label style="color: #e9f4e6"><input type="checkbox" name="sun"/>记住密码</label>
                        </div>
                        <div style="float: right">
                        <a href="ForgetPwdAction_forgetpwd.do"><p class="form-control-static" style="color: #c4e3f3">忘记密码？</p></a>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-3 col-sm-5" style="padding-left: 0px">
                        <button id="submit" type="button" class="btn btn-info" style="float:left;font-size: 20px">&nbsp;登&nbsp;&nbsp;录&nbsp;</button>
                        <button id="register" type="button " class="btn btn-success" style="float: right;font-size: 20px"><a href="AccountAction_toRegister.do">&nbsp;注&nbsp;&nbsp;册&nbsp;</a></button>
                    </div>
                </div>
            </form>

        </div>
      <div id="footer" style="float: right">© 2017 深圳德讯信息技术有限公司</div>
    </div>
    </div>
</div >

<script type="text/javascript" src="/static/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="/static/js/jquery.cookie.js"></script>
<script type="text/javascript" src="/static/js/jquery.gradientify.min.js"></script>
<script src="/static/js/bootstrap/bootstrap.min.js"></script>
<script src="/static/js/iziToast.min.js"></script> <!--消息框-->
<script src="/static/js/jquery.validate.js"></script><!--表单验证-->
<script src="/static/js/tojson.js"></script>
<script src="/static/js/login.js"></script>

<script>
    $(function () {
        $("#main").gradientify({
            gradients: [
                { start: [175,238,238], stop: [224,255,255] },
                { start: [49,76,172], stop: [242,159,191] },
                { start: [255,103,69], stop: [240,154,241] }
            ],
            angle:'45deg'
        });
        var email=$.cookie('email');
        var password=$.cookie('password');
        $('#email').val(email);
        $('#password').val('password');
    })


</script>
</body>
</html>