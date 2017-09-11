<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>orgdepartment_add</title>
    <link rel="stylesheet" href="/static/css/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/uploadfile/jquery.filer.css">
    <link rel="stylesheet" href="/static/css/uploadfile/jquery.filer-dragdropbox-theme.css">
    <style>
        /*body{*/
        /*padding: 30px;*/
        /*background: rgb(229, 243, 233);*/
        /*}*/
        .contenttop{
            height: 40px;
            background: rgba(255,255,255,0.5);
            color: #ffffff;
            padding: 2px;
            border-radius: 5px;
            margin-bottom: 1px;
        }
        .form-group{
            font-size: 18px;
        }
        .form-group input{
            margin-bottom: 10px;
        }
        .formpart{
            border-bottom-left-radius: 5px;
            border-bottom-right-radius: 5px;
        }
        .basic{
            background: #e5e5e5;
            padding: 20px;
        }
        #mainform .additions{
            background: #e5e5e5;
            padding: 20px;
        }
        input.error { border: 1px solid red; }
        label.error {
            padding-left: 16px;

            padding-bottom: 2px;

            font-weight: bold;
            font-size: 10px;

            color: #EA5200;
        }
    </style>
</head>
<body>

<canvas id="canvas" style="position:fixed;z-index:-1;height: 100%;width: 100%;"></canvas>
<div class="container">

    <form  action="OrganizationUploadFile_upload.do"  id="fileform" enctype="multipart/form-data" method="post">
        <div class="col-sm-12 contenttop" style="margin-top: 15px">
            <h4 style="float: left">附件资料</h4>
        </div>
        <div  class="col-sm-12 formpart basic additions">
            <div class="row">
                <div class="col-md-12">
                    <input type="file" name="file" id="file1" >
                    <input type="file" name="file" id="file2">
                    <input type="file" name="file" id="file3">
                    <!--<input type="submit" value="Submit">-->
                    <!--<button type="submit" class="btn btn-info center-block" value="Submit" style="width: 100px;font-size: 15px;">提交 <span class="glyphicon glyphicon-ok-circle"></span></button>-->
                </div>
            </div>
        </div>
        <input type="hidden" name="cmpId" id="cmpIdHidden" value="<%=request.getAttribute("cmp_id")%>">

        <div class="col-sm-12" style="margin-top: 15px">
            <div class="btn-group center-block" style="width: 160px">
                    <button type="button" id="backbtn" class="btn btn-default center-block">&nbsp;<span class="glyphicon glyphicon-circle-arrow-left"></span>返回&nbsp;</button>
                <button type="submit" id="addbtn" class="btn btn-success center-block">&nbsp;<span class="glyphicon glyphicon-saved"></span> 上传&nbsp;</button>
            </div>
        </div>
    </form>
</div>

<script src="/static/js/jquery/jquery-2.1.1.min.js"></script>
<script src="/static/js/bootstrap/bootstrap.min.js"></script>
<script src="/static/js/background/milkybg.js"></script>
<script src="/static/js/jquery/jquery.validate.js"></script>
<script src="/static/js/jQuery.print.js"></script><!--打印-->
<script src="/static/js/clipboard.min.js"></script><!--复制-->
<script src="/static/js/uploadfile/jquery.filer.min.js"></script><!--上传文件-->
<script src="/static/js/placechoose/distpicker.data.js"></script>
<script src="/static/js/placechoose/distpicker.js"></script>
<script type="text/javascript">
    $('#addbtn').on('click',function () {

        alert($('#cmpIdHidden').val());
//        $('#filer_input').attr("name","files");

    })
    $('#backbtn').on('click',function () {
        window.location.href = "http://127.0.0.1:8080/OrganizationManager_archive.do";
    })
</script>

<script type="text/javascript">
    $('#fileform').validate({
        rules: {
            file1: {
                required: true,
            },
        messages: {
            file1: {
                required: "请上传文件",
            },

        },
        errorPlacement: function (error, element) { //指定错误信息位置
            if (element.is(':radio') || element.is(':checkbox')) { //如果是radio或checkbox
                var eid = element.attr('name'); //获取元素的name属性
                error.insertAfter('[value="female"]'); //将错误信息添加当前元素的父结点后面
            } else {
                error.insertAfter( element).wrapInner('<span class="errormsg"/>');
            }
        }
    })
</script>

</body>

</html>