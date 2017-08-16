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
            background: rgb(210, 240, 243);
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
            border: solid 4px palevioletred;
            box-shadow: 0px 0px 10px 6px palevioletred;
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
            <input type="email" class="form-control "  placeholder="454674513@qq.com" disabled/>
        </div>
    </div>
    <div class="form-group">
    <label  class="col-sm-2 control-label">密码：</label>
    <div class="col-sm-9">
        <input type="password" class="form-control"  name="password"  placeholder="******" disabled/>
    </div>
    </div>
</form>
<form class="form-horizontal form" id="form2" role="form">
        <div class="form-group">
            <label class="col-sm-2 control-label name">邮箱：</label>
            <div class="col-sm-9">
                <input type="email" class="form-control "  placeholder="454674513@qq.com" disabled/>
            </div>
        </div>
        <div class="form-group">
            <label  class="col-sm-2 control-label">密码：</label>
            <div class="col-sm-9">
                <input type="password" class="form-control"  name="password"  placeholder="******" />
            </div>
        </div>
        <div class="form-group repass" >
            <label  class="col-sm-2 control-label relable">重复密码：</label>
            <div class="col-sm-9" >
                <input type="text" class="form-control"  name="repassword"  placeholder="******"/>
            </div>
        </div>
    <div class="form-group" id="btn">
        <div class="col-sm-offset-3 col-sm-6">
            <button id="cancel" type="button" class="btn btn-info" style="float:left;">取消</button>
            <button id="submit" type="submit " class="btn btn-success" style="float: right;">提交</button>
        </div>
    </div>
    </form>
</div>
<script src="/static/js/jquery-2.1.1.min.js"></script>
<script src="/static/js/bootstrap/bootstrap.min.js"></script>
<script>
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
</script>
</body>
</html>