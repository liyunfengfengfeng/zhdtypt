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
    <title>Title</title>
    <link rel="stylesheet" href="css/bootstrap/bootstrap.min.css">
    <style>
        body{
            background: rgba(255,255,255,0.4);
        }
        .container{
            /*background: rgba(255,255,255,0.4);*/
            border-radius: 5px;
            color: #ffffff;
        }
        #detail title{
            border-bottom: solid 2px #ffffff;
        }
        #detail .content{
            font-size: 20px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row">
        <div id="detail" class="col-md-12">
            <div class="page-header title">
                <h2>${info.title}</h2>
            </div>
            <div class="content">
                ${info.content}
            </div>
        </div>
        <%--<div class="col-md-12">--%>
            <%--<button class="btn btn-warning backbtn center-block" style="margin-top: 30px;width: 150px;font-size: 18px"><span class="glyphicon glyphicon-circle-arrow-left"></span> 返回</button>--%>
        <%--</div>--%>
    </div>
</div>
<script src="js/jquery/jquery.min.js"></script>
<script src="js/bootstrap/bootstrap.min.js"></script>
<script>
    $(function () {
        $('.backbtn').on('click',function () {
            history.back();
        })
    })
</script>
</body>
</html>