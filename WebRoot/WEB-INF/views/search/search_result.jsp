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
    <script type="text/javascript" src="/static/js/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="/static/js/kkpaper/kkpager.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/static/css/kkpaper/kkpager_blue.css" />
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
        <s:if test="%{#request.organizationPages != null}">
        <s:iterator value="%{#request.organizationPages}" var="org">
        <div class="col-md-12 list">

                <h3><a href="SearchAction_searchResultDetails.do?id=<s:property value="%{#org.Cmp_id}"/>" style="color: #ffffff"><s:property value="%{#org.title}"/></a></h3>
                <div class="content">
                    <s:property value="%{#org.content}"/>
                </div>

        </div>
        </s:iterator>
        </s:if>
    </div>

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
                hrefFormer : 'SearchAction_toSearch',
                //链接尾部
                hrefLatter : '.do',
                getLink : function(n){
                    return this.hrefFormer + this.hrefLatter + "?pno="+n;
                }

            });
        });
    </script>
    <div style="width:800px;margin:0 auto;">
        <div id="kkpager"></div>
    </div>
</div>
<script src="/static/js/jquery/jquery-2.1.1.min.js"></script>
<script src="/static/js/bootstrap/bootstrap.min.js"></script>
</body>
</html>