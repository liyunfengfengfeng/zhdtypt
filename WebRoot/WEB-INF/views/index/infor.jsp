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
    <link rel="stylesheet" href="/static/css/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/cutImg.css">

    <style>
        .container{
            padding: 15px;
        }
        body{
            background: rgb(229, 243, 233);
        }
        #img{
            width: 120px;
            height: 120px;
        }
        #inforform1{
            border: solid 4px #548b9a;
            box-shadow: 0px 0px 10px 6px #548b9a;
            border-radius: 15px;
            padding: 10px;
            font-size: 18px;
            margin-bottom: 20px;
        }
        .inforformtop{
            width: 100%;
            height: 50px;
            /*background: darkgoldenrod;*/
        }
        .form-group label{
            margin: 4px 0;
            padding: 2px;
        }
        .form-group input{
            margin: 2px;
        }
        #btn2{
            width: 150px;
            margin: 20px auto;
            font-size: 18px;
            display: none;
        }
        .show{
            display: block;
        }
        .hide{
            display: none;
        }
        #inforform2{
            border: solid 4px #548b9a;
            box-shadow: 0px 0px 10px 6px #548b9a;
            border-radius: 15px;
            padding: 35px;
            font-size: 16px;
            margin-bottom: 20px;

            /*color: #2b669a;*/
            color:#000000;
        }
        #btn4{
            width: 150px;
            margin: 20px auto;
            font-size: 18px;
            display: none;
        }
        button{
            border: solid 2px #548b9a;
            background: #539a4a;
            color: #ffffff;
            font-size: 25px;
        }
        button:hover{
            box-shadow: 0px 0px 10px 2px #2a9a71;
            color: #fff;
        }
        label{
            font-size: 18px;
            color: darkblue;
        }
    </style>
</head>
<body>
<div class="container">
    <form action="ModifyAction_updatePersonInfo.do" id="inforform1" role="form" method="post" enctype="multipart/form-data">
        <s:if test="%{#request.personnel != null}">
        <s:iterator value="%{#request.personnel}" var="personnel">
        <div class="inforformtop">
            <button type="button" class="btn btn-info" id="btn1" style="float: right">上传头像</button>
            <button type="button" class="btn btn-info" id="imgcancelbtn" style="float: right;display: none">取消</button>
        </div>
        <div class="row" id="dealimg" style="display: none">
            <div class="container">
                <div class="imageBox">
                    <div class="thumbBox"></div>
                    <div class="spinner" style="display: none">Loading...</div>
                </div>
                <div class="action">
                    <div class="new-contentarea tc">
                        <a href="javascript:void(0)" class="upload-img">
                            <label for="upload-file">上传图像</label>
                        </a>
                        <input type="file" class="form-control" name="upload_file" id="upload-file" />
                    </div>
                    <input type="button" id="btnCrop"  class="Btnsty_peyton" value="裁切">
                    <input type="button" id="btnZoomIn" class="Btnsty_peyton" value="+"  >
                    <input type="button" id="btnZoomOut" class="Btnsty_peyton" value="-" >
                </div>
                <div class="cropped"></div>
            </div>
        </div>
        <div class="row" id="basicinfor">
            <div class="col-md-6">
                <div id="img" class="center-block">
                    <img src="<s:property value="%{#personnel.picture_path}"/>" class="img-responsive" alt="">
                </div>
                <div class="form-group text-center" style="margin-top: 20px;margin-left: 5px;">
                    <label  class="col-sm-2 control-label text-left"></label>
                    <div class="col-sm-8">
                        <p class="form-control-static"><s:property value="%{#personnel.Mail}"/></p>
                    </div>
                </div>
                <!--<div class="form-group center-block" style="margin-top: 20px;">-->
                <!--<label for="personpic"  class="col-sm-3 control-label text-left">更换头像</label>-->
                <!--<div class="col-sm-8">-->
                <!--<input id="personpic" name="pic" class="form-control" type="file" AUTOCOMPLETE=OFF  disabled/>-->
                <!--</div>-->
                <!--</div>-->
            </div>
            <div class="col-md-6" style="margin-top: 30px;">
                <div class="form-group">
                    <label for="name"  class="col-sm-3 control-label text-left">姓名</label>
                    <div class="col-sm-8">
                        <input id="name" name="name" class="form-control" type="text" AUTOCOMPLETE=OFF  value="<s:property value="%{#personnel.Name}"/>" disabled/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="idnumber" class="col-sm-3 control-label text-left">身份证号</label>
                    <div class="col-sm-8">
                        <input id="idnumber" name="idnumber" value="<s:property value="%{#personnel.Id_no}"/>" class="form-control" type="text" AUTOCOMPLETE=OFF disabled/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="sex" class="col-sm-3 control-label text-left">性别</label>
                    <div class="col-sm-8">
                        <input class="form-control" type="text" value="<s:property value="%{#personnel.Sex}"/>" AUTOCOMPLETE=OFF disabled/>
                        <select name="sex"  id="sex" style="display: none">
                            <option value="男">男</option>
                            <option value="女">女</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="birth" class="col-sm-3 control-label text-left">出生日期</label>
                    <div class="col-sm-8">
                        <input id="birth" name="birth" class="form-control" value="<s:property value="%{#personnel.Bath}"/>" type="date" AUTOCOMPLETE=OFF disabled/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="organize" class="col-sm-3 control-label text-left">组织名称</label>
                    <div class="col-sm-8">
                        <input id="organize" name="organize" value="<s:property value="%{#personnel.Belong_cmp}"/>" class="form-control" type="text" AUTOCOMPLETE=OFF disabled/>
                    </div>
                </div>
            </div>
        </div>
        <div class="center-block">
            <button type="submit" class="btn  center-block" id="btn2" >确&nbsp;&nbsp;定</button>
        </div>
    </form>
    <form action="ModifyAction_updateMorePersonInfo.do" id="inforform2">
        <div class="inforformtop">
            <button type="button" class="btn btn-info" id="btn3" style="float: right">修改详细信息</button>
        </div>
        <div class="row">
            <div class="col-md-6">
                <div class="form-group">
                    <label for="user_code" class="col-sm-3 control-label text-left">员工编号</label>
                    <div class="col-sm-8">
                        <input id="user_code" name="user_code" value="<s:property value="%{#personnel.User_code}"/>" class="form-control" type="text" AUTOCOMPLETE=OFF disabled/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="area_code" class="col-sm-3 control-label text-left">地区代码</label>
                    <div class="col-sm-8">
                        <input id="area_code" name="area_code" value="<s:property value="%{#personnel.Area_code}"/>" class="form-control" type="text" AUTOCOMPLETE=OFF disabled/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="post_address" class="col-sm-3 control-label text-left">籍贯</label>
                    <div class="col-sm-8">
                        <input id="post_address" name="post_address" value="<s:property value="%{#personnel.Post_address}"/>" class="form-control" type="text" AUTOCOMPLETE=OFF disabled/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="army_date" class="col-sm-3 control-label text-left">入伍时间</label>
                    <div class="col-sm-8">
                        <input id="army_date" name="army_date" type="date" value="<s:property value="%{#personnel.Army_date}"/>" class="form-control"  AUTOCOMPLETE=OFF disabled/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="political_status" class="col-sm-3 control-label text-left">政治面貌</label>
                    <div class="col-sm-8">
                        <input id="political_status" name="political_status" value="<s:property value="%{#personnel.Political_status}"/>" class="form-control" type="text" AUTOCOMPLETE=OFF disabled/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="party_date" class="col-sm-3 control-label text-left">入党时间</label>
                    <div class="col-sm-8">
                        <input id="party_date" name="party_date" value="<s:property value="%{#personnel.Party_date}"/>" class="form-control" type="date" AUTOCOMPLETE=OFF disabled/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="passport_no" class="col-sm-3 control-label text-left">护照号码</label>
                    <div class="col-sm-8">
                        <input id="passport_no" name="passport_no" value="<s:property value="%{#personnel.Passport_no}"/>" class="form-control" type="text" AUTOCOMPLETE=OFF disabled/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="master_date" class="col-sm-3 control-label text-left">任职时间</label>
                    <div class="col-sm-8">
                        <input id="master_date" name="master_date" value="<s:property value="%{#personnel.Master_date}"/>" class="form-control" type="date" AUTOCOMPLETE=OFF disabled/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="post" class="col-sm-3 control-label text-left">岗位</label>
                    <div class="col-sm-8">
                        <input id="post" name="post" value="<s:property value="%{#personnel.Post}"/>" class="form-control" type="text" AUTOCOMPLETE=OFF disabled/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="operate_date" class="col-sm-3 control-label text-left">工作时间</label>
                    <div class="col-sm-8">
                        <input id="operate_date" name="operate_date" value="<s:property value="%{#personnel.operate_date}"/>" class="form-control" type="date" AUTOCOMPLETE=OFF disabled/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="belong_cmp" class="col-sm-3 control-label text-left">所属单位</label>
                    <div class="col-sm-8">
                        <input id="belong_cmp" name="belong_cmp" class="form-control" value="<s:property value="%{#personnel.Belong_cmp}"/>" type="text" AUTOCOMPLETE=OFF disabled/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="technical_title" class="col-sm-3 control-label text-left">职称</label>
                    <div class="col-sm-8">
                        <input id="technical_title" value="<s:property value="%{#personnel.Technical_title}"/>" name="technical_title" class="form-control" type="text" AUTOCOMPLETE=OFF disabled/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="rate_time" class="col-sm-3 control-label text-left">评定时间</label>
                    <div class="col-sm-8">
                        <input id="rate_time" name="rate_time" value="<s:property value="%{#personnel.Rate_time}"/>" class="form-control" type="date" AUTOCOMPLETE=OFF disabled/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="professional" class="col-sm-3 control-label text-left">专业特长</label>
                    <div class="col-sm-8">
                        <input id="professional" name="professional" value="<s:property value="%{#personnel.professional}"/>" class="form-control" type="text" AUTOCOMPLETE=OFF disabled/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="foreign_language" class="col-sm-3 control-label text-left">外语</label>
                    <div class="col-sm-8">
                        <input id="foreign_language" name="foreign_language" value="<s:property value="%{#personnel.Foreign_language}"/>" class="form-control" type="text" AUTOCOMPLETE=OFF disabled/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="post_cmp" class="col-sm-3 control-label text-left">原工作单位</label>
                    <div class="col-sm-8">
                        <input id="post_cmp" name="post_cmp" value="<s:property value="%{#personnel.Post_cmp}"/>" class="form-control" type="text" AUTOCOMPLETE=OFF disabled/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="post_duty" class="col-sm-3 control-label text-left">在职情况</label>
                    <div class="col-sm-8">
                        <input id="post_duty" name="post_duty" value="<s:property value="%{#personnel.Post_duty}"/>" class="form-control" type="text" AUTOCOMPLETE=OFF disabled/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="marry" class="col-sm-3 control-label text-left">婚否</label>
                    <div class="col-sm-8">
                        <input id="marry" name="marry" value="<s:property value="%{#personnel.Marry}"/>" class="form-control" type="text" AUTOCOMPLETE=OFF disabled/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="marry_name" class="col-sm-3 control-label text-left">配偶姓名</label>
                    <div class="col-sm-8">
                        <input id="marry_name" name="marry_name" value="<s:property value="%{#personnel.Marry_name}"/>" class="form-control" type="text" AUTOCOMPLETE=OFF disabled/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="marry_cmp" class="col-sm-3 control-label text-left">配偶工作单位</label>
                    <div class="col-sm-8">
                        <input id="marry_cmp" name="marry_cmp" value="<s:property value="%{#personnel.Marry_cmp}"/>" class="form-control" type="text" AUTOCOMPLETE=OFF disabled/>
                    </div>
                </div>

            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <label for="marry_tel" class="col-sm-3 control-label text-left">配偶电话</label>
                    <div class="col-sm-8">
                        <input id="marry_tel" name="marry_tel" value="<s:property value="%{#personnel.Marry_tel}"/>" class="form-control" type="text" AUTOCOMPLETE=OFF disabled/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="retire_date" class="col-sm-3 control-label text-left">退休时间</label>
                    <div class="col-sm-8">
                        <input id="retire_date" name="retire_date" value="<s:property value="%{#personnel.Retire_date}"/>" class="form-control" type="date" AUTOCOMPLETE=OFF disabled/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="retire_duty" class="col-sm-3 control-label text-left">退休职务</label>
                    <div class="col-sm-8">
                        <input id="retire_duty" name="retire_duty" value="<s:property value="%{#personnel.Retire_duty}"/>" class="form-control" type="text" AUTOCOMPLETE=OFF disabled/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="con_address" class="col-sm-3 control-label text-left">职系地址</label>
                    <div class="col-sm-8">
                        <input id="con_address" name="con_address" value="<s:property value="%{#personnel.Con_Address}"/>" class="form-control" type="text" AUTOCOMPLETE=OFF disabled/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="leave_date" class="col-sm-3 control-label text-left">离职日期</label>
                    <div class="col-sm-8">
                        <input id="leave_date" name="leave_date" value="<s:property value="%{#personnel.Leave_date}"/>" class="form-control" type="date" AUTOCOMPLETE=OFF disabled/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="post_code" class="col-sm-3 control-label text-left">邮政编码</label>
                    <div class="col-sm-8">
                        <input id="post_code" name="post_code" value="<s:property value="%{#personnel.Post_code}"/>" class="form-control" type="text" AUTOCOMPLETE=OFF disabled/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="status" class="col-sm-3 control-label text-left">状态</label>
                    <div class="col-sm-8">
                        <input id="status" name="status" class="form-control" value="<s:property value="%{#personnel.Statu}"/>" type="text" AUTOCOMPLETE=OFF disabled/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="file_no" class="col-sm-3 control-label text-left">档案编号</label>
                    <div class="col-sm-8">
                        <input id="file_no" name="file_no" value="<s:property value="%{#personnel.File_no}"/>" class="form-control" type="text" AUTOCOMPLETE=OFF disabled/>
                    </div>
                </div><div class="form-group">
                <label for="user_type" class="col-sm-3 control-label text-left">人员类型</label>
                <div class="col-sm-8">
                    <input id="user_type" name="user_type" value="<s:property value="%{#personnel.User_type}"/>" class="form-control" type="text" AUTOCOMPLETE=OFF disabled/>
                </div>
            </div><div class="form-group">
                <label for="phone" class="col-sm-3 control-label text-left">电话号</label>
                <div class="col-sm-8">
                    <input id="phone" name="phone" value="<s:property value="%{#personnel.Phone}"/>" class="form-control" type="text" AUTOCOMPLETE=OFF disabled/>
                </div>
            </div>
                <div class="form-group">
                    <label for="mail" class="col-sm-3 control-label text-left">邮箱</label>
                    <div class="col-sm-8">
                        <input id="mail" name="mail" class="form-control" type="text" value="<s:property value="%{#personnel.Mail}"/>" AUTOCOMPLETE=OFF disabled/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="age" class="col-sm-3 control-label text-left">年龄</label>
                    <div class="col-sm-8">
                        <input id="age" name="age" class="form-control" value="<s:property value="%{#personnel.Age}"/>" type="text" AUTOCOMPLETE=OFF disabled/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="job" class="col-sm-3 control-label text-left">工作</label>
                    <div class="col-sm-8">
                        <input id="job" name="job" class="form-control" value="<s:property value="%{#personnel.Job}"/>" type="text" AUTOCOMPLETE=OFF disabled/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="address" class="col-sm-3 control-label text-left">地址</label>
                    <div class="col-sm-8">
                        <input id="address" name="address" class="form-control" value="<s:property value="%{#personnel.Address}"/>" type="text" AUTOCOMPLETE=OFF disabled/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="education" class="col-sm-3 control-label text-left">教育情况</label>
                    <div class="col-sm-8">
                        <input id="education" name="education" class="form-control" value="<s:property value="%{#personnel.Education}"/>" type="text" AUTOCOMPLETE=OFF disabled/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="create_date" class="col-sm-3 control-label text-left">创建日期</label>
                    <div class="col-sm-8">
                        <input id="create_date" name="create_date" class="form-control" value="<s:property value="%{#personnel.Create_date}"/>" type="date" AUTOCOMPLETE=OFF disabled/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="create_by"  class="col-sm-3 control-label text-left">审核人</label>
                    <div class="col-sm-8">
                        <input id="create_by" name="create_by" class="form-control" value="<s:property value="%{#personnel.Create_by}"/>" type="text" AUTOCOMPLETE=OFF disabled/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="modity_by" class="col-sm-3 control-label text-left">修改人</label>
                    <div class="col-sm-8">
                        <input id="modity_by" name="modity_by" class="form-control" value="<s:property value="%{#personnel.Modity_by}"/>" type="text" AUTOCOMPLETE=OFF disabled/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="modity_date" class="col-sm-3 control-label text-left">修改日期</label>
                    <div class="col-sm-8">
                        <input id="modity_date" name="modity_date" class="form-control" value="<s:property value="%{#personnel.Modity_date}"/>" type="date" AUTOCOMPLETE=OFF disabled/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="rmk" class="col-sm-3 control-label text-left">备注</label>
                    <div class="col-sm-8">
                        <input id="rmk" name="rmk" class="form-control" value="<s:property value="%{#personnel.Rmk}"/>" type="text" AUTOCOMPLETE=OFF disabled/>
                    </div>
                </div>
                </s:iterator>
                </s:if>

            </div>
        </div>
        <div class="center-block">
            <button type="submit" class="btn bg-success center-block" id="btn4" >提&nbsp;&nbsp;交</button>
        </div>
    </form>

</div>


<script src="/static/js/jquery-2.1.1.min.js"></script>
<script src="/static/js/bootstrap/bootstrap.min.js"></script>
<script src="/static/js/cropbox.js"></script><!-- 上传头像，裁切头像-->
<script>
    $(function () {
        $('#btn3').on('click',function () {
            $(this).removeClass('show').addClass('hide');
            $('#btn4').removeClass('hide').addClass('show');
            $('#inforform2 input').removeAttr('disabled');
            $('#create_date').attr('disabled','disabled');
            $('#create_by').attr('disabled','disabled');
            $('#modity_date').attr('disabled','disabled');
            $('#inforcancelbutton').removeClass('hide').addClass('show')
        })
        $('#inforcancelbutton').on('click',function () {

            $('#inforform2 input').attr('disabled','disabled');
            $(this).removeClass('show').addClass('hide');
            $('#btn4').removeClass('show').addClass('hide');
            $('#btn3').removeClass('hide').addClass('show');
        })
        $('#btn1').on('click',function () {
            $('#dealimg').removeClass('hide').addClass('show');
            $('#basicinfor').removeClass('show').addClass('hide');
            $('#btn1').removeClass('show').addClass('hide');
            $('#imgcancelbtn').removeClass('hide').addClass('show');
            $('#btn2').removeClass('hide').addClass('show');
        })
        $('#imgcancelbtn').on('click',function () {
            $('#basicinfor').removeClass('hide').addClass('show');
            $('#dealimg').removeClass('show').addClass('hide');
            $('#imgcancelbtn').removeClass('show').addClass('hide');
            $('#btn1').removeClass('hide').addClass('show');
            $('#btn2').removeClass('show').addClass('hide');
        })
        $('#btn2').on('click',function () {
            console.log($('#upload-file').val())
        })
    })

</script>


<script>//裁切头像
$(window).load(function() {
    var options =
    {
        thumbBox: '.thumbBox',
        spinner: '.spinner',
        imgSrc: 'static/img/pic.png'
    }
    var cropper = $('.imageBox').cropbox(options);
    $('#upload-file').on('change', function(){
        var reader = new FileReader();
        reader.onload = function(e) {
            options.imgSrc = e.target.result;
            cropper = $('.imageBox').cropbox(options);
        }
        reader.readAsDataURL(this.files[0]);
        this.files = [];
    })
    $('#btnCrop').on('click', function(){
        var img = cropper.getDataURL();
        $('.cropped').html('');
        $('.cropped').append('<img src="'+img+'" align="absmiddle" style="width:64px;margin-top:4px;border-radius:64px;box-shadow:0px 0px 12px #7E7E7E;" ><p>64px*64px</p>');
        $('.cropped').append('<img src="'+img+'" align="absmiddle" style="width:128px;margin-top:4px;border-radius:128px;box-shadow:0px 0px 12px #7E7E7E;"><p>128px*128px</p>');
        $('.cropped').append('<img src="'+img+'" align="absmiddle" style="width:180px;margin-top:4px;border-radius:180px;box-shadow:0px 0px 12px #7E7E7E;"><p>180px*180px</p>');
    })
    $('#btnZoomIn').on('click', function(){
        cropper.zoomIn();
    })
    $('#btnZoomOut').on('click', function(){
        cropper.zoomOut();
    })
});
</script>

</body>
</html>