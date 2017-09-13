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
    <link rel="stylesheet" href="/static/css/tagInput/bootstrap-tagsinput.css">
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
            <span class="text-left"><h3 style="color: #ffffff">考勤组维护</h3></span>
        </div>
    </div>
    <div class="row" id="content">
        <form action="/checkwork/CheckWorkAction_saveCheckWork.do" class="col-md-12" method="post" >
            <div class="form-group col-sm-12">
                <label  class="col-sm-3 control-label text-left">考勤组名称</label>
                <div class="col-sm-8">
                    <input  class="form-control" type="text" name="checkworkname" placeholder="必填" AUTOCOMPLETE=OFF />
                </div>
            </div>
            <div class="form-group col-sm-12">
                <label  class="col-sm-3 control-label text-left">所属</label>
                <div class="col-sm-8">
                    <div style="width: 100%">
                        <c:if test="${ ! empty requestScope.organizations }">
                            <c:forEach items="${requestScope.organizations }" var="organization" varStatus="abc">
                                <label class="checkbox-inline">
                                    <input type="checkbox" name="of" value="${organization.cmp_id}"> ${organization.cmp_name}
                                </label>
                            </c:forEach>
                        </c:if>
                    </div>
                </div>
            </div>
            <div class="form-group col-sm-12">
                <label  class="col-sm-3 control-label text-left">描述</label>
                <div class="col-sm-8">
                    <textarea class="form-control" rows="3" name="descriptions"></textarea>
                </div>
            </div>

            <div class="form-group col-sm-12">
                <label  class="col-sm-3 control-label text-left">包含用户</label>
                <div class="col-sm-8">
                    <input id="users" name="users" class="form-control" type="text" value="Sydney,Beijing,Cairo" data-role="tagsinput"/>
                </div>
            </div>

            <div class="form-group col-sm-12">
                <label  class="col-sm-3 control-label text-left">状态</label>
                <div class="col-sm-8">
                    <!--<input class="form-control" type="text"  AUTOCOMPLETE=OFF/>-->
                    <div class="radio">
                    <label>
                        <input type="radio" name="state"  value="1" checked> 启用
                    </label>
                </div>
                    <div class="radio">
                        <label>
                            <input type="radio" name="state"  value="0">  停用
                        </label>
                    </div>
                </div>
            </div>

            <div class="col-sm-12">
                <div class="center-block" style="width: 320px">
                    <%--<button type="button" id="backbtn" class="btn btn-warning backbtn" style="margin-top: 30px;width: 150px;font-size: 20px"><a href="/checkwork/CheckWorkAction_toCheckWork.do"><span class="glyphicon glyphicon-circle-arrow-left"></span> 返回</a></button>--%>
                    <button type="button" id="backbtn" class="btn btn-warning backbtn" style="margin-top: 30px;width: 150px;font-size: 20px"><span class="glyphicon glyphicon-circle-arrow-left"></span> 返回</button>
                    <!--不要把type换成submit！！！-->
                    <button  class="btn btn-success"  type="button" id="checksubmit" style="margin-top: 30px;width: 150px;font-size: 20px"><span class="glyphicon glyphicon-saved"></span>  保存</button></button>
                </div>
            </div>

        </form>
    </div>




</div>
<script src="/static/js/jquery/jquery-1.11.0.min.js"></script>
<script src="/static/js/bootstrap/bootstrap.min.js"></script>
<script src="/static/js/background/milkybg.js"></script>
<script src="/static/js/tagInput/bootstrap-tagsinput.js"></script>
<script>
    $(function () {

        $('#checksubmit').on('click',function () {
            var $checkworkname=$('[name=checkworkname]').val();
            var $of=$('[name=of]').val();
            var $descriptions=$('[name=descriptions]').val();
            var $users=$('[name=users]').val();
            var $state=$('[name=state]').val();
            if($checkworkname==''||$of==''||$descriptions==''||$users==''||$state==''){
               alert("请完整填写信息");
            }else{
                $('form').submit();
            }
//            console.log($(this).tagsinput('items'));//获取包含用户的方式
        })
//        //返回考勤组页面
        $('#backbtn').on('click',function () {
            $.ajax({
                url: "/checkwork/CheckWorkAction_backToCheckWork.do",
                type: "post",
                data: "",
                contentType: "application/json",
                dataType: "html json",
                success: function (data) {
                    var jsonStr=data;
                    var jsonObj =  JSON.parse(jsonStr);
                    window.location.href = "/checkwork/CheckWorkAction_toCheckWork.do";
                }
            })
        })
    })
</script>
</body>
</html>