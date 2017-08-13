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
    <title>展示全部的个人信息，将用户注册的信息进行回显</title>
</head>
<body>
<form action="PersonnelAction_addPersonInfo.do" method="post">
    <s:if test="%{#request.personnel!=null}">
        <s:iterator value="%{#request.personnel}" var="p">
            <input name="Id" type="hidden" value="<s:property value="%{#p.Id}"/>"/>
        <tr>
            <td>邮箱：</td>
            <td><input name="Mail" value="<s:property value="%{#p.Mail}"/>"/></td>
        </tr>
            <tr>
                <td>姓名：</td>
                <td><input name="Name" value="<s:property value="%{#p.Name}"/>"/></td>
            </tr>
            <tr>
                <td>出生日期：</td>
                <td><input name="Bath" value="<s:property value="%{#p.Bath}"/>"/></td>
            </tr>
            <tr>
                <td>性别：</td>
                <td><input name="Sex" value="<s:property value="%{#p.Sex}"/>"/></td>
            </tr>
            <tr>
                <td>登陆名称：</td>
                <td><input name="Login_name" value=""/></td>
            </tr>
            </s:iterator>
        </s:if>
    <input type="submit" value="完善个人信息"/>
</form>
</body>
</html>
