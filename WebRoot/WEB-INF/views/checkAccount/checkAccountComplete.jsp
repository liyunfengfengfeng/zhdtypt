<%@page language="java" import="java.util.*" pageEncoding="UTF-8"  %>
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
    <title>管理审核注册完成</title>

</head>
<body style="background-color: white">
操作成功
<a href="http://127.0.0.1:8080/CheckAccount_queryByStatus.do">返回</a>
</body>

</html>
