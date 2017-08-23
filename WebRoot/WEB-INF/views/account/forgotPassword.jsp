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
    <title>forgetpassword</title>
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
                <p>智慧电梯管理平台</p>
                <div id="wrapper">
                    <div id="steps">
                        <form id="formElem" name="formElem" action="SendEmailAction_updatepassword.do" method="post">
                            <fieldset class="step">
                                <legend>找回密码</legend>
                                <p>
                                    <label for="email">邮箱账号</label>
                                    <input id="email" name="email"  type="email" AUTOCOMPLETE=OFF />
                                    <span></span>
                                    <button type="button" class="" id="emailbutton" style="width: 150px;font-size: 10px;height: 30px;">发送验证码</button>
                                </p>
                                <p>
                                    <label for="vericode">请填写验证码</label>
                                    <input id="vericode" name="vericode" />
                                    <span></span>
                                </p>
                                <p>
                                    <label for="password">新密码</label>
                                    <input id="password" name="password" type="password" placeholder="请输入6~10位的密码" AUTOCOMPLETE=OFF />
                                </p>
                                <p>
                                    <label for="repassword">重复密码</label>
                                    <input id="repassword" name="repassword" type="password" placeholder="请重复输入密码" AUTOCOMPLETE=OFF />
                                </p>
                                    <button type="submit" id="findpwdbutton" class="btn btn-info">提交</button>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div >

<script type="text/javascript" src="/static/js/jquery/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="/static/js/jquery/jquery.gradientify.min.js"></script><!--背景颜色更换-->
<script src="/static/js/bootstrap/bootstrap.min.js"></script>
<script src="/static/js/login.js"></script>
<script src="/static/js/jquery/jquery.validate.js"></script>
<script src="/static/js/tojson.js"></script>
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


        $("#formElem").validate({
            rules: {
                email: {
                    required: true,
                    email: true
                },
                password: {
                    required: true,
                    minlength: 6,
                    maxlength:10
                },
                repassword: {
                    required: true,
                    equalTo: "#password"
                }
            },
            messages: {
                email: {
                    required: "请输入邮箱",
                    email: "请输入正确的邮箱地址",
                },
                password: {
                    required: "请输入密码",
                    minlength: jQuery.format("密码为6~10位")
                },
                repassword: {
                    required: "请输入确认密码",
                    equalTo: "两次输入密码不一致"
                }
            },
            debug:true,
            errorPlacement: function (error, element) { //指定错误信息位置
                if (element.is(':radio') || element.is(':checkbox')) { //如果是radio或checkbox
                    var eid = element.attr('name'); //获取元素的name属性
                    error.insertAfter('[value="female"]'); //将错误信息添加当前元素的父结点后面
                } else {
                    error.insertAfter( element).wrapInner('<span class="errormsg"/>');
                }
            }

        })

        $("#email").on('blur',function (event) {//by id
            var json=JSON.stringify($("#email").serializeObject());
            //alert("123456");
            $.ajax({
                type:"POST",
                url:"ForgetPwdAction_checkEmail.do",
                contentType:"application/json",
                dataType:"html json",
                data:json,
                success:function (data) {
                    var jsonStr=data;
                    var jsonObj =  JSON.parse(jsonStr);

                    console.log(jsonObj.flag);
                    if(jsonObj.flag==1){//邮箱正确
                        $('#email').next().css("color","green").html("邮箱已经存在");
                        $("#emailbutton").attr('disabled',false);
                    }else{//邮箱错误
                        $('#email').next().css("color","red").html("邮箱不存在");
                        $("#emailbutton").attr('disabled',true);
                    }
                }

            })

        })


        //发送验证码
        $('#emailbutton').on('click',function () {
            alert('已发送验证码至你的邮箱，请查收');
            //ajax发送
            var json=JSON.stringify($("#email").serializeObject());
            console.log("=========="+json);
            $.ajax({
                type:"POST",
                url:"SendEmailAction_sendEmail.do",
                contentType:"application/json",
                dataType:"html json",
                data:json,
                success:function (data) {
                    //var jsonStr=data;
                    //var jsonObj =  JSON.parse(jsonStr);
                    //console.log(jsonObj.state);
                }

            })
        })

        //校验验证码
        $('#vericode').on('blur',function () {
            //ajax发送
            var json=JSON.stringify($("#vericode").serializeObject());
            console.log("=========="+json);
            $.ajax({
                type:"POST",
                url:"SendEmailAction_checkVericode.do",
                contentType:"application/json",
                dataType:"html json",
                data:json,
                success:function (data) {
                    var jsonStr=data;
                    var jsonObj =  JSON.parse(jsonStr);
                    console.log(jsonObj.state);
                    if(jsonObj.state==1){
                        $('#vericode').next().css("color","green").html("验证码正确");
                        $("#findpwdbutton").attr('disabled',false);
                    }else{
                        $('#vericode').next().css("color","red").html("验证码错误");
                        $("#findpwdbutton").attr('disabled',true);
                    }
                }

            })
        })

        //提交找回密码表单
        $('#findpwdbutton').on('blur',function () {
            //ajax发送
            var json=JSON.stringify($("#formElem").serializeObject());
            console.log("=========="+json);
            $.ajax({
                type:"POST",
                url:"SendEmailAction_updatepassword.do",
                contentType:"application/json",
                dataType:"html json",
                data:json,
                success:function (data) {
                    var jsonStr=data;
                    var jsonObj =  JSON.parse(jsonStr);
                    console.log(jsonObj.state);
                    window.location="AccountAction_toLogin.do";
                }

            })
        })




    })




</script>
</body>
</html>