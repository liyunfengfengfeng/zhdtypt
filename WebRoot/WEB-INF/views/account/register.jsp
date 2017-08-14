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
    <title>register</title>
    <link rel="stylesheet" href="/static/css/bootstrap/bootstrap.min.css">
    <!--<link rel="stylesheet" href="css/login.css">-->
    <link rel="stylesheet" href="/static/css/iziToast.min.css"> <!--消息框-->
    <link rel="stylesheet" href="/static/css/register.css">
    <style>
        span.reference{
            position:fixed;
            left:5px;
            top:5px;
            font-size:10px;
            text-shadow:1px 1px 1px #fff;
        }
        span.reference a{
            color:#555;
            text-decoration:none;
            text-transform:uppercase;
        }
        span.reference a:hover{
            color:#000;

        }
        h1{
            color:darkblue;
            font-size:36px;
            text-shadow:3px 2px 2px #fff;
            padding:20px;
        }
    </style>
</head>
<body>
<div id="title">
    <a href=""><img src="/static/img/logo-darker.png"class="img-responsive" alt=""></a>
</div>
<div id="main" style="position: absolute; width: 100%; height: 100%; z-index: -1;">
    <!--<img style="position: fixed" src="img/1.jpg" alt="" height="100%" width="100%" class="bgd">-->
    <div class="row">
        <div id="myCarousel" class="carousel slide col-md-6">
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
        </div>
        <div class="col-md-6" id="right">
                <div id="content">
                    <p>欢迎注册智慧电梯管理平台</p>
                    <div id="wrapper">
                        <div id="steps">
                            <form id="formElem" name="formElem" action="" method="post">
                                <fieldset class="step">
                                    <legend>个人账户</legend>
                                    <p>
                                        <label for="email">邮箱账号</label>
                                        <input id="email" name="email" placeholder="info@htmleaf.com" type="email" AUTOCOMPLETE=OFF />
                                        <button type="button" class="" style="width: 150px;font-size: 10px;height: 30px;">发送验证码</button>
                                    </p>
                                    <p>
                                        <label for="vericode">请填写验证码</label>
                                        <input id="vericode" name="vericode" />
                                    </p>
                                    <p>
                                        <label for="password">密码</label>
                                        <input id="password" name="password" type="password" placeholder="请输入6~10位的密码" AUTOCOMPLETE=OFF />
                                    </p>
                                    <p>
                                        <label for="repassword">重复密码</label>
                                        <input id="repassword" name="repassword" type="password" placeholder="请重复输入密码" AUTOCOMPLETE=OFF />
                                    </p>
                                </fieldset>
                                <fieldset class="step">
                                    <legend>基本信息</legend>
                                    <p>
                                        <label for="name">姓名</label>
                                        <input id="name" name="name" type="text" AUTOCOMPLETE=OFF />
                                    </p>
                                    <p>
                                        <label for="idCard">身份证号</label>
                                        <input id="idCard" name="idCard"  type="text" AUTOCOMPLETE=OFF />
                                    </p>
                                    <p>
                                        <label >性别</label>
                                        <select name="sex">
                                            <option value="男">男</option>
                                            <option value="女">女</option>
                                        </select>
                                    </p>
                                    <p>
                                        <label for="birth">出生日期</label>
                                        <input id="birth" name="birth"  type="date" AUTOCOMPLETE=OFF />
                                    </p>
                                </fieldset>
                                <fieldset class="step">
                                    <legend>注册</legend>
                                    <p>
                                        <label for="organize">组织代码</label>
                                        <input id="organize" name="organize"  type="text" AUTOCOMPLETE=OFF />
                                    </p>
                                    <p>
                                        请确保信息填写完整无误后提交
                                    </p>
                                    <p class="submit">
                                        <button id="registerButton" type="submit">注册</button>
                                    </p>
                                </fieldset>
                            </form>
                            </div>
                        </div>
                        <div id="navigation" style="display:none;">
                            <ul>
                                <li class="selected">
                                    <a href="#">个人账户</a>
                                </li>
                                <li>
                                    <a href="#">基本信息</a>
                                </li>
                                <li>
                                    <a href="#">注册</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
        </div>
</div >

<script type="text/javascript" src="/static/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="/static/js/jquery.gradientify.min.js"></script>
<script src="/static/js/bootstrap/bootstrap.min.js"></script>
<script src="/static/js/iziToast.min.js"></script> <!--消息框-->
<script src="/static/js/login.js"></script>
<script src="/static/js/jquery.validate.js"></script>
<script src="/static/js/form_validate.js"></script>
<script  src="/static/js/register.js"></script>
<script>
    $(function () {
        $("#main").gradientify({
            gradients: [
                { start: [175,238,238], stop: [224,255,255] },
                { start: [49,76,172], stop: [242,159,191] },
                { start: [255,103,69], stop: [240,154,241] },
            ],
            angle:'45deg'
        });

    })
</script>
</body>
</html>