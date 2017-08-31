<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理审核注册</title>
    <script type="text/javascript" src="<c:url value="/static/js/jquery-2.1.1.min.js"/>"></script>
    <link rel="stylesheet" href="/static/css/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/optiscroll.css">
    <style>
        body{
            padding: 15px;
            background: rgb(229, 243, 233);
        }
        .show{
            display: block;
        }
        .hide{
            display: none;
        }
        body{
            padding: 80px;
        }
        #startbtn{
            margin: 20px;
        }
        #waitAudit{
            background: darkred;
            border-radius: 20px;
            border: solid 4px darkred;
            /*opacity: 0.8;*/
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
<div class="container" >
    <div class="row clearfix" id="waitAudit">
        <div class="table-responsive">
            <table class="table table-hover" class="waitAuditTable" style="font-size: 18px;">
                <caption class="text-center"><h2 style="color: #ffffff">等待审核的账号</h2></caption>
                <thead>
                <tr>
                    <th>姓名</th>
                    <th>邮箱</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <s:iterator value="%{#request.checkAccountList}" id="TAccountEntity">
                    <tr>
                        <td><s:property value="#TAccountEntity.name"/></td>
                        <td><s:property value="#TAccountEntity.email"/></td>
                        <td>未审核</td>
                        <td><input type="button" value="详细信息" style="background-color:#FFB90F" name="${TAccountEntity.email}" class="btn1" /></td>
                    </tr>
                </s:iterator>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row clearfix" id="startbtn">
        <div class="col-md-12 column" >
            <button type="button" class="btn btn-warning btn-lg active center-block">开始审核注册账号</button>
        </div>
    </div>
    <div class="row clearfix" id="presentAudit">
        <h2 class="text-center" style="color: #ffffff">详细信息</h2>
        <div class="col-md-6 column">
            <form class="form-horizontal" role="form" style="font-size: 18px;">
                <div class="form-group">
                    <label  class="col-sm-2 control-label">邮箱</label>
                    <div class="col-sm-10">
                        <p class="form-control-static" name="email" id="email">_ _ _ _</p>
                    </div>
                </div>
                <div class="form-group">
                    <label  class="col-sm-2 control-label">密码</label>
                    <div class="col-sm-10">
                        <p class="form-control-static" name="password" id="password">_ _ _ _</p>
                    </div>
                </div>
                <div class="form-group">
                    <label  class="col-sm-2 control-label">姓名</label>
                    <div class="col-sm-10">
                        <p class="form-control-static" name="name" id="name">_ _ _ _</p>
                    </div>
                </div>
                <div class="form-group">
                    <label  class="col-sm-2 control-label">性别</label>
                    <div class="col-sm-10">
                        <p class="form-control-static" name="sex" id="sex">_ _ _ _</p>
                    </div>
                </div>
                <div class="form-group">
                    <label  class="col-sm-2 control-label">身份证号</label>
                    <div class="col-sm-10">
                        <p class="form-control-static" name="idCard" id="idCard">_ _ _ _</p>
                    </div>
                </div>
                <div class="form-group">
                    <label  class="col-sm-2 control-label">出生日期</label>
                    <div class="col-sm-10">
                        <p class="form-control-static" name="birthdate" id="birthdate">_ _ _ _</p>
                    </div>
                </div>
                <div class="form-group">
                    <label  class="col-sm-2 control-label">组织代码</label>
                    <div class="col-sm-10">
                        <p class="form-control-static" name="organize" id="organize">_ _ _ _</p>
                    </div>
                </div>
            </form>
        </div>
        <div class="col-md-6 column operate" >
            <div class="btns" style="width: 80%;">
                <button type="button" id="passbtn" class="btn btn-danger btn-default btn-block active" disabled="disabled">通过并分配角色</button>
                <button type="button" id="falsebtn" class="btn btn-default btn-block btn-primary active" disabled="disabled">不能通过</button>
                <button type="button" id="backbtn" class="btn btn-warning btn-block btn-primary active">返回</button>
            </div>
        </div>
    </div>
    <div class="row clearfix" id="manage">
        <div class="row chooserole">
            <h2 class="text-center" style="color: #ffffff">分配角色：</h2>
            <div class="roles col-sm-6">
                <select name="roles" multiple="multiple" class="optiscroll center-block" id="roles" style=" width: 60%;height: 250px">
                    <s:iterator value="%{#request.roleList}" id="TRoleEntity">
                        <option value=<s:property value="#TRoleEntity.Role_id"/>>
                            <s:property value="#TRoleEntity.Role_name"/>
                        </option>
                    </s:iterator>
                </select>
                <button type="button" id="add" style="width: 60%" class="btn btn-block btn-success center-block">选中点击添加角色</button>
            </div>
            <div class="alreadychoose col-sm-6" style="margin-bottom: 10px;">
                <select name="roles" multiple="multiple" class="optiscroll center-block" id="choosedroles" style="width: 60%;height: 250px">

                </select>
                <button type="button" id="remove" style="width: 60%" class="btn btn-block btn-success center-block">选中点击删除角色</button>
            </div>
            <div class="center-block" style="">
                <!--<button  type="button"  class="btn btn-warning " style="width: 150px;">返回</button>-->
                <button  type="button"  class="btn center-block"  id="successbtn" dstyle="width: 150px;background: #286385;color: #fff">确定通过</button>
            </div>
        </div>
        <div class="sendmsg">
            <button  type="button"  class="btn center-block" id="failbtn" style="width: 150px;background: #286385;color: #fff">确定不通过</button>
        </div>
    </div>
</div>
<script src="/static/js/jquery-2.1.1.min.js"></script>
<script src="/static/js/bootstrap/bootstrap.min.js"></script>
<script src="/static/js/jquery.optiscroll.js"></script> <!--滚动条样式-->
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
            $('#manage .sendmsg').removeClass('show').addClass('hide');
        })
        $('#falsebtn').on('click',function () {
            $('#manage').removeClass('hide').addClass('show');
            $('#manage .sendmsg').removeClass('hide').addClass('show');
            $('#manage .chooserole').removeClass('show').addClass('hide');
        })

        $('#add').on('click',function () {
            $('#roles option:selected').appendTo('#choosedroles');
        })
        $('#remove').on('click',function () {
            $('#choosedroles option:selected').appendTo('#roles');
        })
    })
</script>
<script type="text/javascript">
</script>
</body>
<script type="text/javascript">
    var returnArray=[];
    var value;
    /* 点击按钮触发 点击详细信息把待审核的信息加载出来 */
    $('.btn1').on('click',function (email) {

        value = $(this).attr("name"); // $(this)表示获取当前被点击元素的name值  即邮箱的值
        $.ajax({
            //提交方式
            type: "GET",
            //访问servleturl
            url: "CheckAccount_queryPersons.do?param="+value,
            dataType:"json",
            success: function (data) {
                var jsonStr=data;
                var jsonObj =  JSON.parse(jsonStr);
                //Juery 写法 var jsonObj = $.parseJSON(jsonStr)
                // console.log(jsonStr);
                console.log(jsonObj);
                if (returnArray[0]!=null) {
                    returnArray[0]=jsonObj[0].account_0_id;
                }else{
                    returnArray.push(jsonObj[0].account_0_id);
                }

//                returnString="account0Id="+jsonObj[0].account0Id+",roleId=123123123123123";
                //把按钮 变成可点击
                $("#passbtn").attr('disabled',false);
                $("#falsebtn").attr('disabled',false);
                //设置属性
                $('#name').html(jsonObj[0].name);
                $('#email').html(jsonObj[0].email);
                $('#password').html(jsonObj[0].password);
                $('#idCard').html(jsonObj[0].id_number);
                $('#sex').html(jsonObj[0].sex);
                var myear=jsonObj[0].bath.year;
                var mmonth=jsonObj[0].bath.month+1;
                var mdate=jsonObj[0].bath.date;
                $('#birthdate').html(myear+"年"+mmonth+"月"+mdate+"日");
                $('#organize').html(jsonObj[0].cmp_id);

            }
        });
    })
    /*为用户分配角色*/
    $('#successbtn').on('click',function () {
        var list=$('#choosedroles option');

        list.each(function (index) {
            returnArray[index+1]=(list[index].value);
        })
        console.log(returnArray);
        var jsonstr=JSON.stringify(returnArray);
        console.log(jsonstr);
//        var json=JSON.stringify($("#email").serializeObject());
        var url="CheckAccount_writeToDataBaseFor1.do?data="+jsonstr;
        window.location.href = url;

    })
    /*审核不通过 value即为邮箱的值*/
    $('#failbtn').on('click',function () {

        var url="CheckAccount_writeToDataBaseFor2.do?data="+value;
        window.location.href = url;

    })




</script>
<script>
    $(function () {
        $('#startbtn').on('click',function () {
            $(this).addClass('hide');
            $('#presentAudit').addClass('show')
        })
        $('#backbtn').on('click',function () {
            $('#presentAudit').removeClass('show');
            $('#startbtn').removeClass('hide');
        })
        $('#add').on('click',function () {
            $('#roles option:selected').appendTo('#choosedroles');
        })
        $('#remove').on('click',function () {
            $('#choosedroles option:selected').appendTo('#roles');
        })
        $('#passbtn').on('click',function () {
            $('#btns').toggleClass('hide');
            $('#chooserole').toggleClass('show');
        })
        $('#falsebtn').on('click',function () {
            $('#btns').toggleClass('hide');
            $('#sendmsg').toggleClass('show');
        })
    })
</script>
</html>
