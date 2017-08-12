<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户注册</title>


    
</head>
<body style="background-color: pink">
<form action="AccountAction_register.do" method="post">
    <table border="0" align="center">
        <tr>
            <td>邮箱：</td>
            <td><input name="email"></td>
            <td><button>发送验证码</button></td>
        </tr>
        <tr>
            <td>验证码：</td>
            <td><input name="checkNum"></td>
        </tr>
        <tr>
            <td>密码：</td>
            <td><input name="password" type="password"></td>
        </tr>
        <tr>
            <td>确认密码：</td>
            <td><input name="con_Password" type="password"></td>
        </tr>
        <tr>
            <td>姓名：</td>
            <td><input name="name"></td>
        </tr>
        <tr>
            <td>身份证号：</td>
            <td><input name="id_number"></td>
        </tr>
        <tr>
            <td>性别：</td>
            <td>
                <select name="sex">
                    <option value="男">男</option>
                    <option value="女">女</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>出生日期：</td>
            <td><input name="bath" type="date"></td>
        </tr>
        <tr>
            <td>组织代号：</td>
            <td><input name="cmp_code"></td>
            <td><s:fielderror fieldName="cmpCodeError"/></td>
        </tr>

        <tr align="center">
            <td colspan="2"><input type="submit" value="提交注册"></td>
        </tr>
    </table>
</form>
<script src="/static/js-jquery/jquery-2.1.1.min.js" type="text/javascript" charset="utf-8"></script>
<script >
    $(function(){
        $( "[name|='email']").on('blur',function () {
            //alert($(this).val());
            var email=$(this).val();
            var data={
                result:email
            }
            $.ajax({
                url:"AccountAction_checkEmail.do?email="+email,
                type:"POST",
                dataType:"text",
                contentType:"application/json",
                statusCode:{
                    200:function (data) {
                        console.log(data.result);
                    }
                }
            })
        })

    })
</script>
</body>
</html>