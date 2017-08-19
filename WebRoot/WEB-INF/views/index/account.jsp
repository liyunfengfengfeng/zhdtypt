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
    <title>account</title>
    <link rel="stylesheet" href="/static/css/bootstrap/bootstrap.min.css">
    <style>
        body{
            padding: 15px;
            background: rgb(229, 243, 233);
        }
        .form{
            width: 90%;
            margin: 50px auto;
            font-size: 18px;
            color: #000000;
        }
        .form label{

        }
        .container{
            margin: 200px auto;
            border: solid 4px #548b9a;
            box-shadow: 0px 0px 10px 6px #548b9a;
            border-radius: 18px;
            padding: 20px;
        }
        .mod{
            float: right;
        }
        #btn{
            display: none;
        }
        #form2{
            display: none;
        }
        .show{
            display: block;
        }
        .hide{
            display: none;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="mod">
        <button id="modify" class="btn btn-info" >修改密码</button>
    </div>
    <form class="form-horizontal form" id="form" role="form" >
        <div class="form-group">
            <label class="col-sm-2 control-label name">邮箱：</label>
            <div class="col-sm-9">
                <input type="email" class="form-control "  placeholder="${email}" disabled/>
            </div>
        </div>
        <div class="form-group">
            <label  class="col-sm-2 control-label">密码：</label>
            <div class="col-sm-9">
                <input type="password" class="form-control"  name="password"  placeholder="******" disabled/>
            </div>
        </div>
    </form>
    <form class="form-horizontal form" id="form2" role="form" method="post" action="ModifyPwd_modifyAccountPwd.do">
        <div class="form-group">
            <label class="col-sm-2 control-label name">邮箱：</label>
            <div class="col-sm-9">
                <input type="hidden" name="email1" class="form-control "  value="${email}" />
                <input type="email" class="form-control " name="email" placeholder="${email}" value="" disabled/>
            </div>
        </div>
        <div class="form-group">
            <label  class="col-sm-2 control-label">输入原密码：</label>
            <div class="col-sm-9">
                <input type="password" class="form-control" id="oldpassword"  name="oldpassword"  placeholder="******"/>
                <span></span>
            </div>
        </div>
        <div class="form-group">
            <label  class="col-sm-2 control-label">新密码：</label>
            <div class="col-sm-9">
                <input type="password" class="form-control" id="password" name="password" value=""  placeholder="******" />
            </div>
        </div>
        <div class="form-group repass" >
            <label  class="col-sm-2 control-label relable">重复密码：</label>
            <div class="col-sm-9" >
                <input type="password" class="form-control"  name="repassword" value="" placeholder="******"/>
            </div>
        </div>
        <div class="form-group" id="btn">
            <div class="col-sm-offset-3 col-sm-6">
                <button id="cancel" type="button" class="btn btn-info" style="float:left;">取消</button>
                <button id="submit" type="submit" class="btn btn-success" style="float: right;">提交</button>
            </div>
        </div>
    </form>
</div>
<script src="/static/js/jquery-2.1.1.min.js"></script>
<script src="/static/js/bootstrap/bootstrap.min.js"></script>
<script src="/static/js/jquery.validate.js"></script>
<script src="/static/js/tojson.js"></script>
<script>
    $('#form2').validate({
        rules:{
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
        messages:{
            password: {
                required: "请输入密码",
                minlength: jQuery.format("密码为6~10位")
            } ,
            repassword: {
                required: "请输入确认密码",
                equalTo: "两次输入密码不一致"
            }
        },
        errorPlacement: function (error, element) { //指定错误信息位置
            error.insertAfter( element).wrapInner('<span style="color: red;" class="errormsg"/>');
        }
    })
    $('#modify').on('click',function () {
        $('#form').addClass('hide');
        $('#form2').addClass('show');
        $(this).addClass('hide');
        $('#btn').addClass('show');
    })
    $('#cancel').on('click',function () {
        $('#form2').toggleClass('show');
        $('#form').toggleClass('hide');
        $('#btn').toggleClass('show');
        $('#modify').toggleClass('hide')
    })
    <!--检查输入的原密码是否正确-->
    $('#oldpassword').bind('blur',function() {
        //alert("123456");
        var json = JSON.stringify($("#oldpassword").serializeObject());

        $.ajax({
            type: "POST",
            url: "ModifyAction_checkOldPwd.do",
            contentType: "application/json",
            dataType: "html json",
            data: json,
            success: function (data) {
                var jsonStr=data;
                var jsonObj =  JSON.parse(jsonStr);
                console.log(jsonObj.state);
                if(jsonObj.state==1){//原密码正确
                    $('#oldpassword').next().css("color","green").html("原密码正确");
                    $("#submit").attr('disabled',false);
                }else{//验证码错误
                    $('#oldpassword').next().css("color","red").html("原密码错误");
                    $("#submit").attr('disabled',true);
                }
            }
        })
    });
</script>
</body>
</html>