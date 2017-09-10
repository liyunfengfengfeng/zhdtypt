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
    <title>创建角色</title>
    <link rel="stylesheet" href="/static/css/bootstrap/bootstrap.min.css">
    <style>
        body{
            /*padding: 30px;*/
            background: rgb(229, 243, 233);
        }
        #title{
            height: 55px;
            /*width: 100%;*/
            /*margin: 40px auto;*/
            margin-bottom: 30px;
            background: #015775;
            border: solid 3px #015775;
            box-shadow: 0px 0px 2px 6px #015775;
            border-radius: 3px;
        }
        #content{
            font-size: 18px;
            color: white;
            background: #015775;
            border: solid 3px #015775;
            box-shadow: 0px 0px 2px 6px #015775;
            border-radius: 3px;
        }
        #l-map{height:300px;width:100%;}
        #r-result{width:100%;}
        #control{width:100%;}
        .show{
            display: block;
        }
        .hide{
            display: none;
        }
    </style>
</head>
<body>
<canvas id="canvas" style="position:fixed;z-index:-1;height: 100%;width: 100%;"></canvas>
<div class="container">
    <div class="row">
        <div class="col-sm-12" id="title">
            <span class="text-left"><h3 style="color: #ffffff">创建角色</h3></span>
        </div>
    </div>
    <div class="row" id="content">
        <form action="/checkwork/RoleAction_saveRoleInfo.do" class="col-md-12" method="post">
            <div class="row" >
                <div class="col-md-12">
                    <div class="form-group">
                        <label for="roleName" class="col-sm-3 control-label text-center">角色名称</label>
                        <div class="col-sm-9">
                            <input name="roleName" id="roleName" class="form-control" type="text" placeholder="请写入角色名称" AUTOCOMPLETE=OFF required="required"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="roleDescriptions" class="col-sm-3 control-label text-center"> 角色描述 </label>
                        <div class="col-sm-9">
                            <textarea class="form-control" id="roleDescriptions" name="roleDescriptions" rows="3" placeholder="请写入您对角色的描述..." required="required"></textarea>
                        </div>
                    </div>
                </div>
                <div class="col-sm-12">
                    <div class="center-block" style="width: 320px">
                        <%--<button class="btn btn-warning backbtn" style="margin-top: 30px;width: 150px;font-size: 20px"><span class="glyphicon glyphicon-circle-arrow-left"></span> 返回</button>--%>
                        <button type="submit" class="btn btn-success" id="submit" style="margin-top: 30px;width: 150px;font-size: 20px"><span class="glyphicon glyphicon-saved"></span>  保存</button>
                    </div>
                </div>
            </div>
        </form>
    </div>


</div>
<script src="/static/js/jquery/jquery-1.11.0.min.js"></script>
<script src="/static/js/bootstrap/bootstrap.min.js"></script>
<script src="/static/js/tojson.js"></script>
<script src="/static/js/background/milkybg.js"></script>
<script>
    $(function () {
        $('.backbtn').on('click',function () {
            history.back();
        })



    })
</script>
</body>
</html>