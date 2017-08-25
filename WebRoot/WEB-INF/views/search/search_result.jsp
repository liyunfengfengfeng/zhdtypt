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
        <h1>....的搜索结果</h1>
    </div>
    <div class="row" id="searchresult">
        <div class="col-md-12 list">
                <h3><a href="search_result_details.jsp" style="color: #ffffff">sdklgsd</a></h3>
                <div class="content">
                    sdagassdagassdagassdad打算干啥德国是大噶事耽搁单个阿斯顿噶山豆根大使馆撒大个gas
                        打算干啥德国是大噶事耽搁单个阿斯顿噶山豆根大使馆撒大个
                        打算干啥德国是大噶事耽搁单个阿斯顿噶山豆根大使馆撒大个sdagassdagassdagassdad打算干啥德国是大噶事耽搁单个阿斯顿噶山豆根大使馆撒大个gas
                        打算干啥德国是大噶事耽搁单个阿斯顿噶山豆根大使馆撒大个
                        打算干啥德国是大噶事耽搁单个阿斯顿噶山豆根大使馆撒大个sdagassdagassdagassdad打算干啥德国是大噶事耽搁单个阿斯顿噶山豆根大使馆撒大个gas
                        打算干啥德国是大噶事耽搁单个阿斯顿噶山豆根大使馆撒大个
                        打算干啥德国是大噶事耽搁单个阿斯顿噶山豆根大使馆撒大个sdagassdagassdagassdad打算干啥德国是大噶事耽搁单个阿斯顿噶山豆根大使馆撒大个gas
                        打算干啥德国是大噶事耽搁单个阿斯顿噶山豆根大使馆撒大个
                        打算干啥德国是大噶事耽搁单个阿斯顿噶山豆根大使馆撒大个
                </div>
        </div>
        <div class="col-md-12 list"></div>
        <div class="col-md-12 list"></div>
        <div class="col-md-12 list"></div>
        <div class="col-md-12 list"></div>
        <div class="col-md-12 list"></div>
        <div class="col-md-12 list"></div>
        <div class="col-md-12 list"></div>
        <div class="col-md-12 list"></div>
        <div class="col-md-12 list"></div>
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