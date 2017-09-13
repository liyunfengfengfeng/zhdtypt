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
<script type="text/javascript">
    window.onload=function(){
        //所在区域联动列表
        setup();//加载的时候就执行里面的代码：如下的js代码有显示
    }

</script>
<canvas id="canvas" style="position:fixed;z-index:-1;height: 100%;width: 100%;"></canvas>
<div class="container">
    <!--<div style="height: 100px"></div>-->
    <form  action="OrganizationManager_update.do" class="row" id="mainform" method="post">
        <div class="col-sm-12 contenttop">
            <h4 style="float: left;">基本信息</h4>
            <div class="btn-group btns" style="float: right;font-size: 15px;">
                <button type="button" class="btn btn-default copy" data-clipboard-target="#basicform"><span class="glyphicon glyphicon-tags"></span> 复制</button>
                <button type="button" class="btn btn-default print" ><span class="glyphicon glyphicon-print"></span> 打印</button>
            </div>
        </div>
        <div  class="col-sm-12 formpart basic">
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="cmp_code" class="col-sm-3 control-label text-left">单位编码</label>
                        <div class="col-sm-8">
                            <input name="cmp_code" id="cmp_code" class="form-control"value="<s:property value="%{#request.organizationDetail.cmp_code}"/>"  type="text" placeholder="必填" AUTOCOMPLETE=OFF />
                        </div>
                        <span></span>
                    </div>
                    <div class="form-group">
                        <label for="cmp_address" class="col-sm-3 control-label text-left">所属区域</label>
                        <div class="col-sm-8">
                            <div class="form-group col-sm-4">
                                <select style="WIDTH: 80px" id="province" name="provinceName" >
                                    <option value="unchoose">--请选择省份--</option>
                                    <s:iterator value="%{#request.provinceList}" id="TCityEntity">
                                        <option value="${TCityEntity.id}">${TCityEntity.name}</option>
                                    </s:iterator>
                                </select>
                            </div>
                            <div class="form-group col-sm-4">
                                <select style="WIDTH: 80px" id="city" name="cityName" >
                                    <option value="unchoose">--请选择城市--</option>
                                </select>
                            </div>
                            <div class="form-group col-sm-4">
                                <select style="WIDTH: 90px" id="area" name="districtName" >
                                    <option value="unchoose">--请选择区域--</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="cmp_address" class="col-sm-3 control-label text-left">详细地址</label>
                        <div class="col-sm-8">
                            <input name="cmp_address" id="cmp_address" class="form-control" value="<s:property value="%{#request.organizationDetail.cmp_address}"/>" type="text" placeholder="必填" AUTOCOMPLETE=OFF />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="law_person" class="col-sm-3 control-label text-left">法人</label>
                        <div class="col-sm-8">
                            <input  name="law_person" id="law_person" class="form-control" value="<s:property value="%{#request.organizationDetail.law_person}"/>" type="text" placeholder="必填" AUTOCOMPLETE=OFF />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="cmp_name" class="col-sm-3 control-label text-left">名称</label>
                        <div class="col-sm-8">
                            <input name="cmp_name" id="cmp_name" class="form-control"value="<s:property value="%{#request.organizationDetail.cmp_name}"/>"  type="text" placeholder="必填" AUTOCOMPLETE=OFF  />

                        </div>
                        <span></span>
                    </div>
                    <div class="form-group">
                        <label for="email" class="col-sm-3 control-label text-left">邮箱</label>
                        <div class="col-sm-8">
                            <input  name="email" id="email" class="form-control" value="<s:property value="%{#request.organizationDetail.cmp_mail}"/>"  type="text" AUTOCOMPLETE=OFF />
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="phone" class="col-sm-3 control-label text-left">电话</label>
                        <div class="col-sm-8">
                            <input  name="phone" id="phone" class="form-control" value="<s:property value="%{#request.organizationDetail.phone}"/>" type="text" AUTOCOMPLETE=OFF />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="employee_number" class="col-sm-3 control-label text-left">员工数量</label>
                        <div class="col-sm-8">
                            <input name="employee_number" id="employee_number" class="form-control" value="<s:property value="%{#request.organizationDetail.employee_number}"/>" type="text" AUTOCOMPLETE=OFF />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="type" class="col-sm-3 control-label text-left">单位类型</label>
                        <div class="col-sm-8">
                            <!--<input  name="" class="form-control" type="text" AUTOCOMPLETE=OFF />-->
                            <select name="cmp_type" id="type" class="form-control">
                                <s:iterator value="%{#request.typeList}" id="TOrganizationTypeEntity">
                                    <option value="${TOrganizationTypeEntity.cmptypeid}">${TOrganizationTypeEntity.cmptype}</option>
                                </s:iterator>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="Mfax" class="col-sm-3 control-label text-left">传真</label>
                        <div class="col-sm-8">
                            <input  name="fax" class="form-control"value="<s:property value="%{#request.organizationDetail.fax}"/>" type="text" AUTOCOMPLETE=OFF />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="license_no" class="col-sm-3 control-label text-left">许可证号</label>
                        <div class="col-sm-8">
                            <input id="license_no" name="license_no" class="form-control"value="<s:property value="%{#request.organizationDetail.license_no}"/>" type="text" AUTOCOMPLETE=OFF />
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <input type="hidden" name="cmp_id" id="cmp_id" class="form-control"value="<s:property value="%{#request.organizationDetail.cmp_id}"/>"  type="text" placeholder="必填" />
        <input type="hidden" name="area" id="areahidden">
        <div class="col-sm-12" style="margin-top: 15px">
            <div class="btn-group center-block" style="width: 160px">
                <button type="button" id="backbtn" class="btn btn-default center-block">&nbsp;<span class="glyphicon glyphicon-circle-arrow-left"></span> 返回&nbsp;</button>
                <button type="submit" id="addbtn" class="btn btn-success center-block">&nbsp;<span class="glyphicon glyphicon-saved"></span> 保存&nbsp;</button>
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
<script src="/static/js/tojson.js"></script>
<script>
    $('#chooseplace').distpicker({
        autoSelect: false
    });
    //        <!--打印-->
    $('.print').on('click',function () {
        $("form").print({
            globalStyles: false,
            iframe:false,
            noPrintSelector: "caption",
            stylesheet:"css/bootstrap/bootstrap.min.css"
        });
    })
    //        <!--复制-->
    var clipboard = new Clipboard('.copy');
    clipboard.on('success', function(e) {
        alert("文字已复制到剪贴板中");
    });
    clipboard.on('error', function(e) {
    });


    $('#backbtn').on('click',function () {
        history.go(-1);
    })
    $('#mainform').validate({
        rules: {
            cmp_code: {
                required: true,
            },
            cmp_name: {
                required: true,
            },
            cmp_address: {
                required: true,
            },
            law_person:{
                required:true
            },

            email: {
                email:true
            },
            employee_number:{
                digits:true
            },
            province:{
                required: true,
            },
            city: {
                required: true,
            },
            district:{
                required: true,
            }
        },
        messages: {
            cmp_code: {
                required: "请输入单位编码",
            },
            cmp_name: {
                required: "请输入名称",
            },
            cmp_address: {
                required: "请输入详细地址",
            },
            law_person:{
                required:"请输入法人"
            },
            email: {
                email:"请注意邮箱格式"
            },
            employee_number:{
                digits:"请填写整数"
            },
            province:{
                required: "请选择省份",
            },
            city: {
                required: "请选择市区",
            },
            district:{
                required: "请选择县/镇",
            }
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

    $('#filer_input').filer({
        showThumbs: true,
        templates: {
            box: '<ul class="jFiler-items-list jFiler-items-grid"></ul>',
            item: '<li class="jFiler-item">\
                    <div class="jFiler-item-container">\
                        <div class="jFiler-item-inner">\
                            <div class="jFiler-item-thumb">\
                                <div class="jFiler-item-status"></div>\
                                <div class="jFiler-item-info">\
                                    <span class="jFiler-item-title"><b title="{{fi-name}}">{{fi-name | limitTo: 25}}</b></span>\
                                    <span class="jFiler-item-others">{{fi-size2}}</span>\
                                </div>\
                                {{fi-image}}\
                            </div>\
                            <div class="jFiler-item-assets jFiler-row">\
                                <ul class="list-inline pull-left">\
                                    <li>{{fi-progressBar}}</li>\
                                </ul>\
                                <ul class="list-inline pull-right">\
                                    <li><span class="glyphicon glyphicon-trash jFiler-item-trash-action"></span></li>\
                                </ul>\
                            </div>\
                        </div>\
                    </div>\
                </li>',
            itemAppend: '<li class="jFiler-item">\
                        <div class="jFiler-item-container">\
                            <div class="jFiler-item-inner">\
                                <div class="jFiler-item-thumb">\
                                    <div class="jFiler-item-status"></div>\
                                    <div class="jFiler-item-info">\
                                        <span class="jFiler-item-title"><b title="{{fi-name}}">{{fi-name | limitTo: 25}}</b></span>\
                                        <span class="jFiler-item-others">{{fi-size2}}</span>\
                                    </div>\
                                    {{fi-image}}\
                                </div>\
                                <div class="jFiler-item-assets jFiler-row">\
                                    <ul class="list-inline pull-left">\
                                        <li><span class="jFiler-item-others">{{fi-icon}}</span></li>\
                                    </ul>\
                                    <ul class="list-inline pull-right">\
                                        <li><a class="icon-jfi-trash jFiler-item-trash-action"></a></li>\
                                    </ul>\
                                </div>\
                            </div>\
                        </div>\
                    </li>',
            progressBar: '<div class="bar"></div>',
            itemAppendToEnd: false,
            removeConfirmation: true,
            _selectors: {
                list: '.jFiler-items-list',
                item: '.jFiler-item',
                progressBar: '.bar',
                remove: '.jFiler-item-trash-action'
            }
        },
        uploadFile: {
            url: "",
            data: null,
            type: 'POST',
            enctype: 'multipart/form-data',
            beforeSend: function(){},
            success: function(data, el){
                var parent = el.find(".jFiler-jProgressBar").parent();
                el.find(".jFiler-jProgressBar").fadeOut("slow", function(){
                    $("<div class=\"jFiler-item-others text-success\"><span class=\"glyphicon glyphicon-ok-circle\"></span> Success</div>").hide().appendTo(parent).fadeIn("slow");
                });
            },
            error: function(el){
                var parent = el.find(".jFiler-jProgressBar").parent();
                el.find(".jFiler-jProgressBar").fadeOut("slow", function(){
                    $("<div class=\"jFiler-item-others text-error\"><span class=\"glyphicon glyphicon-remove-circle\"></span> Error</div>").hide().appendTo(parent).fadeIn("slow");
                });
            },
            statusCode: null,
            onProgress: null,
            onComplete: null
        },
        onRemove: function(itemEl, file){
            var file = file.name;
            $.post('', {file: file});
        }
    });
</script>

<script type="text/javascript">
    $(function() {
        //校验
        $('#cmp_name').bind('blur',function(){

            var json=JSON.stringify($("#cmp_name").serializeObject());
            var value=$('#cmp_name').val();
            $.ajax({
                type:"GET",
                url:"/organization/OrganizationManager_checkCmpName.do?cmp_name="+value,
                contentType:"application/json",
                dataType:"html json",

                success:function (data) {
                    var jsonStr=data;
                    var jsonObj =  JSON.parse(jsonStr);
                    console.log(jsonObj);
                    console.log(jsonObj.state);
                    if(jsonObj.state==1){//
                        $('#cmp_name').next().html("组织名称可用");
                    }else{//验证码错误
                        $('#cmp_name').next().html("名称已经注册");
                    }
                }
            })
        });
    });
    $(function() {
        //校验验证码
        $('#cmp_code').bind('blur',function(){
            var value=$('#cmp_code').val();
            var json=JSON.stringify($("#cmp_code").serializeObject());
            $.ajax({
                type:"POST",
                url:"/organization/OrganizationManager_checkCmpId.do?cmp_code="+value,
                contentType:"application/json",
                dataType:"html json",
                success:function (data) {

                    var jsonStr=data;
                    var jsonObj =  JSON.parse(jsonStr);
                    console.log(jsonObj);
                    console.log(jsonObj.state);
                    if(jsonObj.state==1){//验证码正确
                        $('#cmp_code').next().html("");
                        $("#addbtn").attr('disabled',false);
                    }else{//验证码错误
                        $('#cmp_code').next().html("代码已经注册");
                        $("#addbtn").attr('disabled',true);

                    }
                }
            })
        });
    });

</script>

<script type="text/javascript">

    $('#addbtn').on('click',function () {
        var option1=$("#province option:selected").val(); //获取选中的项
        var option2=$("#city option:selected").val(); //获取选中的项
        var option3=$("#area option:selected").val(); //获取选中的项
        var place=option1+'.'+option2+'.'+option3;
        $('#areahidden').val(place);
    })
</script>
<script type="text/javascript">
    /***
     * 省市区三级联动：
     * 三个下拉列表id分别是：province、city、area
     * @returns
     */
    function setup() {
        //获取城市
        $("#province").click(function () {//当点击省份的下拉选的时候，选中之后，城市进行联动

            var options=$("#province option:selected"); //获取选中的项
            $.ajax({
                url: "DataDictionary_queryCityByProvinceId.do?provinceId="+options.val(),
                type: "GET",
                dataType:'json',

                success:function(data){
                    $("#city").empty(); //清空下拉列表·
                    var jData = JSON.parse(data);
                    var strOptions ='<OPTION value="unchoose">--请选择城市--</OPTION>';
                    console.log(jData);
                    for(var i=0;i<jData.length;i++){
                        strOptions = strOptions + '<OPTION value="' + jData[i].id + '">' + jData[i].name + '</OPTION>';
                    }
                    document.getElementById("city").innerHTML = strOptions;
                }
            });

        });
        //获取区县
        $("#city").click(function () {//当选择城市的下拉选的时候，区域进行联动
            var options=$("#city option:selected"); //获取选中的项
            $.ajax({
                url: "DataDictionary_queryAreaByCityId.do?cityId="+options.val(),
                type: "GET",
                dataType:'json',

                success:function(data){
                    $("#area").empty(); //清空下拉列表
                    var jData = JSON.parse(data);
                    var strOptions = '<OPTION value="unchoose">--请选择区域--</OPTION>';
                    console.log(jData);
                    for(var i=0;i<jData.length;i++){
                        strOptions = strOptions + '<OPTION value="' + jData[i].id + '">' + jData[i].name + '</OPTION>';
                    }
                    document.getElementById("area").innerHTML = strOptions;
                }
            });
        });
    }
</script>
</body>

</html>