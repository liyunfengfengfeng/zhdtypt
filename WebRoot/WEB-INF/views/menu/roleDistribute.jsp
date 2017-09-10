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
    <title>角色分配权限</title>
    <link rel="stylesheet" href="/static/css/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/optiscroll.css">
    <script type="text/javascript" src="/static/js/kkpaper/kkpager.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/static/css/kkpaper/kkpager_blue.css" />
    <style>
        body{
            /*padding: 15px;*/
            /*background: rgb(229, 243, 233);*/
        }
        .show{
            display: block;
        }
        .hide{
            display: none;
        }
        /*body{*/
        /*padding: 80px;*/
        /*}*/
        #startbtn{
            margin: 20px;
        }
        #waitAudit{
            background: darkred;
            border-radius: 20px;
            border: solid 4px darkred;
            opacity: 0.8;
            box-shadow: 0px 0px 10px 6px darkred;
            margin-bottom: 10px;
            padding: 20px;
            color: #ffffff;
        }
        #waitAudit table>tbody>tr{
            /*cursor: pointer;*/
        }
        #waitAudit table>tbody>tr:hover{
            cursor: pointer;
            background: #286385;
        }

        #presentAudit{
            margin-top: 20px;
            border: solid 4px #539a4a;
            box-shadow: 0px 0px 10px 6px #539a4a;
            border-radius: 20px;
            padding: 20px;
            display: none;
            background: #539a4a;
            color: #ffffff;
            opacity: 0.8;
        }
        #presentAudit .operate{
            padding-bottom: 20px;
        }
        #presentAudit .operate .btns{
            margin: 80px auto;
            /*display: none;*/
        }

        #manage{
            border: solid 4px  #ec971f;
            box-shadow: 0px 0px 10px 6px  #ec971f;
            border-radius: 20px;
            padding: 10px;
            margin-top: 20px;
            display:none;
            background: #ec971f;
            opacity: 0.8;
            /*background: rgba(236, 151, 31, 0.7);*/
        }
        #manage select{
            margin: 0 auto;
            border-radius: 5px;
            border: solid 1px #539a4a;
            box-shadow: 0px 0px 3px 1px #4e9a49;
            margin-bottom: 3px;
            background: #f7ecb5;
            font-size: 18px;
        }
        #manage .chooserole{
            /*display: none;*/
        }
        #manage .sendmsg{
            /*display: none;*/
        }
        button:hover{
            border: solid 1px #2a9a71;
            box-shadow: 0px 0px 3px 1px #2a9a71;
        }
    </style>
</head>
<body>
<canvas id="canvas" style="position:fixed;z-index:-1;height: 100%;width: 100%;"></canvas>
<div style="height: 100px"></div>
<div class="container" >
    <div class="row clearfix" id="waitAudit">
        <div class="table-responsive">
            <table class="table table-hover" class="waitAuditTable" style="font-size: 18px;">
                <caption class="text-center"><h2 style="color: #ffffff">等待分配权限的角色</h2></caption>
                <thead>
                <tr>
                    <th>名称</th>
                    <th>创建人</th>
                </tr>
                </thead>
                <tbody>
                <c:if test="${ !empty requestScope.roles}">
                    <c:forEach items="${roles}" var="role">
                        <tr>
                            <%--<input type="hidden" name="hiddenroleid" id="hiddenroleid" value="${role.roleid}"/>--%>
                            <td>${role.rolename}</td>
                            <td>${role.createby}</td>
                            <td><input type="button" value="详细信息" style="background-color:#FFB90F" name="${role.roleid}" class="btn1" /></td>
                        </tr>
                    </c:forEach>
                </c:if>
                </tbody>
            </table>
            <div style="width:800px;margin:0 auto;">
                <div id="kkpager"></div>
            </div>
        </div>
    </div>
    <div class="row clearfix" id="startbtn">
        <div class="col-md-12 column" >
            <button type="button" class="btn btn-success btn-lg active center-block">开始分配权限</button>
        </div>
    </div>
    <div class="row clearfix" id="presentAudit">
        <h2 class="text-center" style="color: #ffffff">详细信息</h2>
        <div class="col-md-6 column">
            <form class="form-horizontal" role="form" style="font-size: 18px;">
                <div class="form-group">
                    <label  class="col-sm-3 control-label">角色名称</label>
                    <div class="col-sm-9">
                        <input type="hidden" name="hiddenroleid" id="hiddenroleid" value=""/>
                        <p class="form-control-static" name="name" id="name">_ _ _ _</p>
                    </div>
                </div>
                <div class="form-group">
                    <label  class="col-sm-3 control-label">角色描述</label>
                    <div class="col-sm-9">
                        <p class="form-control-static" name="describe" id="describe">_ _ _ _
                        </p>
                    </div>
                </div>
                <div class="form-group">
                    <label  class="col-sm-3 control-label">创建人</label>
                    <div class="col-sm-9">
                        <p class="form-control-static" name="buildperson" id="buildperson">_ _ _ _</p>
                    </div>
                </div>
                <div class="form-group">
                    <label  class="col-sm-3 control-label">创建日期</label>
                    <div class="col-sm-9">
                        <p class="form-control-static" name="builddate" id="builddate">_ _ _ _</p>
                    </div>
                </div>
                <div class="form-group">
                    <label  class="col-sm-3 control-label">修改人</label>
                    <div class="col-sm-9">
                        <p class="form-control-static" name="updateperson" id="updateperson">_ _ _ _</p>
                    </div>
                </div>
                <div class="form-group">
                    <label  class="col-sm-3 control-label">修改日期</label>
                    <div class="col-sm-9">
                        <p class="form-control-static" name="updatedate" id="updatedate">_ _ _ _</p>
                    </div>
                </div>
            </form>
        </div>
        <div class="col-md-6 column operate" >
            <div class="btns" style="width: 80%;">
                <button type="button" id="passbtn" class="btn btn-warning btn-default btn-block active">分配权限</button>
                <button type="button" id="backbtn" class="btn btn-info btn-block btn-primary active">返回</button>
            </div>
        </div>
    </div>
    <div class="row clearfix" id="manage">
        <div class="row chooserole">
            <h2 class="text-center" style="color: #ffffff">权限分配</h2>
            <div class="roles col-sm-6">
                <select name="roles" multiple="multiple" class="optiscroll center-block" id="roles" style=" width: 60%;height: 250px">
                    <s:iterator value="%{#request.menus}" id="menu">
                        <option value=<s:property value="#menu.menu_id"/>>
                            <s:property value="#menu.title"/>
                        </option>
                    </s:iterator>
                </select>
                <button type="button" id="add" style="width: 60%" class="btn btn-block btn-success center-block">选中点击添加角色</button>
            </div>
            <div class="alreadychoose col-sm-6" style="margin-bottom: 10px;">
                <select name="roles" multiple="multiple" class="optiscroll center-block" id="choosedroles" style="width: 60%;height: 250px">


                </select>
                <button type="button" id="remove" style="width: 60%" class="btn btn-block btn-danger center-block">选中点击删除角色</button>
            </div>
            <div class="center-block" style="">
                <!--<button  type="button"  class="btn btn-warning " style="width: 150px;">返回</button>-->
                <button  type="button"  class="btn center-block" id="rolesbtn" style="width: 150px;background: #286385;color: #fff">确定</button>
            </div>
        </div>
    </div>
</div>
<script src="/static/js/jquery/jquery-2.1.1.min.js"></script>
<script src="/static/js/bootstrap/bootstrap.min.js"></script>
<script src="/static/js/jquery/jquery.optiscroll.js"></script> <!--滚动条样式-->
<script src="/static/js/background/milkybg.js"></script>
<script>
    $(function () {
        $('#startbtn').on('click',function () {
            $(this).removeClass('show').addClass('hide');
            $('#presentAudit').addClass('show')
        })
        $('#backbtn').on('click',function () {
            $('#presentAudit').removeClass('show').addClass('hide');
            $('#startbtn').removeClass('hide').addClass('show');
            $('#manage').removeClass('show').addClass('hide');
        })

        $('#passbtn').on('click',function () {
//            $('#btns').toggleClass('hide');
            $('#manage').removeClass('hide').addClass('show');
            $('#manage .chooserole').removeClass('hide').addClass('show');
        })
        $('#add').on('click',function () {
            $('#roles option:selected').appendTo('#choosedroles');
        })
        $('#remove').on('click',function () {
            $('#choosedroles option:selected').appendTo('#roles');
        })
        $('#rolesbtn').on('click',function () {
            var list=$('#choosedroles option');
            var idlist=[];
            list.each(function (index) {
                idlist.push(list[index].value);
                console.log(list[index].value)
            })
            console.log(list);
            console.log(idlist);
        })
    })
</script>
<script type="text/javascript">
    function getParameter(name) {
        var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r!=null) return unescape(r[2]); return null;
    }
    //init
    $(function(){
        var totalPage = ${page.getTotalPage()};
        var totalRecords = ${page.getTotalRecord()};
        var pageNo = ${page.getPageNo()};
        if(!pageNo){
            pageNo = 1;
        }
        //生成分页
        //有些参数是可选的，比如lang，若不传有默认值
        kkpager.generPageHtml({
            pno : pageNo,
            //总页码
            total : totalPage,
            //总数据条数
            totalRecords : totalRecords,
            //链接前部
            hrefFormer : '/checkwork/RoleAction_toRoleDistribute',
            //链接尾部
            hrefLatter : '.do',
            getLink : function(n){
                return this.hrefFormer + this.hrefLatter + "?pno="+n;
            }

        });
    });
</script>

<script type="text/javascript">
    var returnArray=[];
    /* 点击按钮触发 点击详细信息把待审核的信息加载出来 */
    $('.btn1').on('click',function (email) {

        value = $(this).attr("name"); // $(this)表示获取当前被点击元素的name值  即邮箱的值
        $.ajax({
            //提交方式
            type: "GET",
            //访问servleturl
            url: "/checkwork/RoleAction_queryRoleInfo.do?param="+value,
            dataType:"json",
            success: function (data) {
                var jsonStr=data;
                var jsonObj =  JSON.parse(jsonStr);
                console.log(jsonObj);
                $('#name').html(jsonObj.role.rolename);
                $('#describe').html(jsonObj.role.rmk);
                $('#buildperson').html(jsonObj.role.createby);
                var myear=jsonObj.role.createdate.year;
                var mmonth=jsonObj.role.createdate.month+1;
                var mdate=jsonObj.role.createdate.date;
                $('#builddate').html(myear+"年"+mmonth+"月"+mdate+"日");
                $('#updateperson').html(jsonObj.role.modifyby);
                var myear1=jsonObj.role.modifydate.year;
                var mmonth1=jsonObj.role.modifydate.month+1;
                var mdate1=jsonObj.role.modifydate.date;
                $('#updatedate').html(myear1+"年"+mmonth1+"月"+mdate1+"日");
                $('#hiddenroleid').html(jsonObj.role.roleid);
                if (returnArray[0]!=null) {
                    returnArray[0]=jsonObj.role.roleid;
                }else{
                    returnArray.push(jsonObj.role.roleid);
                }
            }
        });
    })
//    为角色添加权限
    $('#rolesbtn').on('click',function (email) {
        var roleid = returnArray[0];
        var list=$('#choosedroles option');
        list.each(function (index) {
            returnArray[index+1]=(list[index].value);
        })
        var jsonstr=JSON.stringify(returnArray);

        var url="/checkwork/RoleAction_completeRoleDistribute.do?data="+jsonstr;
        window.location.href = url;
    })
</script>

</body>
</html>