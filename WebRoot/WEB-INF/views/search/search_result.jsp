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
    <style>
        body{
            background: rgba(255,255,255,0);
        }
        #searchresult .list{
            margin-top: 20px;
            height: 100px;
            background: rgba(255,255,255,0.4);
            border-radius: 5px;
            color: #ffffff;
        }
        #searchresult .list .content{
            white-space:nowrap;
            text-overflow:ellipsis;
            -o-text-overflow:ellipsis;
            overflow:hidden;
        }
        #title{
            color: #ffffff;
        }
        #page li a{
            background: rgba(255,255,255,0.4);
            color: #ffffff;
        }
        .overflow{

        }
    </style>
</head>
<body>
<div class="container">
    <div class="page-header" id="title">
        <h1>${searchecontent}搜索结果</h1>
    </div>
    <div class="row" id="searchresult">
        <s:if test="%{#request.orgs != null}">
        <s:iterator value="%{#request.orgs}" var="org">
        <div class="col-md-12 list">

                <h3><a href="SearchAction_searchResultDetails.do?id=<s:property value="%{#org.Cmp_id}"/>" style="color: #ffffff"><s:property value="%{#org.title}"/></a></h3>
                <div class="content">
                    <s:property value="%{#org.content}"/>
                </div>

        </div>
        </s:iterator>
        </s:if>
    </div>
      <div class="row">
          <ul class="pagination center-block" id="page">
              <li><a href="#">&laquo;</a></li>
              <li><a href="#">1</a></li>
              <li><a href="#">2</a></li>
              <li><a href="#">3</a></li>
              <li><a href="#">4</a></li>
              <li><a href="#">5</a></li>
              <li><a href="#">6</a></li>
              <li><a href="#">7</a></li>
              <li><a href="#">8</a></li>
              <li><a href="#">9</a></li>
              <li><a href="#">10</a></li>
              <li><a href="#">&raquo;</a></li>
          </ul>
      </div>
</div>
<script src="/static/js/jquery/jquery-2.1.1.min.js"></script>
<script src="/static/js/bootstrap/bootstrap.min.js"></script>
</body>
</html>