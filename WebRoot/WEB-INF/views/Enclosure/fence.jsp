<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="/static/css/bootstrap/bootstrap.min.css">
    <style>
        body{
            /*padding: 30px;*/
            background: rgb(11, 46, 56);
        }
        #record{
            height: 120px;
            margin-bottom: 26px;
        }
        .title{
            height: 50px;
            background: #01596f;
            box-shadow: 0px 0px 5px 6px #01596f;
            /*border: solid 1px #01485c;*/
            border-top-left-radius: 5px;
            border-top-right-radius: 5px;
            padding: 8px;
            font-size: 25px;
            color: #ffffff;
        }
        #record .topcontent{
            height: 75px;
            /*border: solid 1px #01596f;*/
            box-shadow: 0px 0px 5px 6px #01596f;
            border-bottom-left-radius: 5px;
            border-bottom-right-radius: 5px;
        }
        #record .recordbtn{
            width: 100px;
            margin: 20px;
            background: #2aabd2;
            color: white;
        }
        a{
            color: white;
        }
        .contentcontrol{
            display: -webkit-box;
            display: -moz-box;
            -webkit-box-orient: horizontal;
            -moz-box-orient: horizontal;
            -webkit-box-pack:justify;
        }
        .contentcontrol select{
            width: 80px;
            height: 35px;
            color: #01344a;
            border-radius: 5px;
            background: #f5f5f5;
        }
        .operate{
            height: 35px;
        }
        #contentmain{
            background: #01485c;
            box-shadow: 0px 0px 5px 6px #01485c;
            padding: 10px;
            font-size: 20px;
            color: white;
        }
        /*输出表格样式*/
        .top {
            caption-side: top;
        }

        .bottom {
            caption-side: bottom;
        }
        .button-default, .button-default:hover, .button-default:focus, .button-default:active {
            text-decoration: none;
        }

        .button-default {
            font: bold 12px sans-serif;
            color: #222222;
            cursor: pointer;
            padding: 5px;
            margin: 5px;
        }

        .button-default.xlsx:before, .button-default.xls:before, .button-default.csv:before, .button-default.txt:before {
            content: none;
        }

        .xlsx, .xls, .csv, .txt {
            margin: 4px 0;
        }

        .xlsx:before, .xls:before, .csv:before, .txt:before {
            margin-right: 10px;
            padding: 12px;
            box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.2);
        }

        .xlsx:before {
            background-color: darkgreen;
            content: url("/static/img/ixlsx.png");
        }

        .xls:before {
            background-color: green;
            content: url("/static/img/ixls.png");
        }

        .csv:before {
            background-color: blue;
            content: url("/static/img/icsv.png");
        }

        .txt:before {
            background-color: purple;
            content: url("/static/img/itxt.png");
        }
        /*表格过滤样式*/
        .filter-table .quick { margin-left: 0.5em; font-size: 0.8em; text-decoration: none; }
        .fitler-table .quick:hover { text-decoration: underline; }
        td.alt { background-color: #ffc; background-color: rgba(255, 255, 0, 0.2); }
    </style>
</head>
<body>
<canvas id="canvas" style="position:fixed;z-index:-1;height: 100%;width: 100%;"></canvas>
<div class="container">
    <div class="col-md-12" id="record">
        <div class="title">围栏设置</div>
        <div class="topcontent">
            <a href="/community/enclosureAction_changePage.do"><button type="button" class="btn recordbtn"><span class="glyphicon glyphicon-plus"></span> 新增围栏</button></a>
        </div>
    </div>
    <div class="col-md-12" >
        <div class="table-responsive" id="contentmain" >
            <table  class="table table-bordered">
                <div style="width: 100%;margin-bottom: 10px">
                    <div class="contentcontrol">
                        <div class="number">
                            <select name="recordnumber" id="recordnumber" >
                                <option value="10" <s:if test="%{#request.pageSize == 10}">selected</s:if>>10</option>
                                <option value="25" <s:if test="%{#request.pageSize == 25}">selected</s:if>>25</option>
                                <option value="50" <s:if test="%{#request.pageSize == 50}">selected</s:if>>50</option>
                                <option value="100" <s:if test="%{#request.pageSize == 100}">selected</s:if>>100</option>
                            </select><span style="color: white"> 条记录</span>
                        </div>
                        <div style="float: right">
                            <div class="btn-group btns" style="float: right;font-size: 15px;">
                                <button type="button" class="btn btn-default copy" data-clipboard-target="table"><span class="glyphicon glyphicon-tags"></span> 复制</button>
                                <button type="button" class="btn btn-default print" ><span class="glyphicon glyphicon-print"></span> 打印</button>
                            </div>
                            <div class="input-group search" style="width: 300px;float: right">
                                                               <span class="input-group-btn">
                                                              <button class="btn btn-default" type="button"><span class="glyphicon glyphicon-search"></span> 搜索</button>
                                                               </span>
                                <input type="text" class="form-control" id="searbtn-input">
                            </div>
                        </div>
                    </div>
                </div>
                <thead>
                <tr>
                    <th style="width:5%">序号</th>
                    <th style="width:10%">围栏名称</th>
                    <th style="width:10%">考勤组</th>
                    <th style="width:10%">创建时间</th>
                    <th style="width:10%">创建人</th>
                    <th style="width:10%">描述</th>
                    <th style="width:10%">状态</th>
                    <th style="width:15%">操作</th>
                </tr>
                </thead>
                <tbody>
                <form action="/community/enclosureAction_deleteEnclosure.do" method="post">
                <s:iterator value="#request.enclosureList" status="status" id="enclosure">
                    <tr>
                        <td><s:property value="#status.index+1"/></td>
                        <td><a href="enclosureAction_showEnclosure.do?enclosureName=<s:property value="enclosureName"/>"><s:property value="enclosureName"/></a></td>
                        <td><s:property value="#request.attendanceList[#status.index].rolename"/></td>
                        <td><s:date name="modifyDate" format="yyyy-MM-dd"/></td>
                        <td><s:property value="#request.modifyNameList[#status.index]"/></td>
                        <td><s:property value="descriptions" /></td>

                        <td>
                            <c:choose>
                                <c:when test="${ enclosure.status==1 }">
                                    <button type="button" class="btn btn-info btn-group-sm">
                                        <span class="glyphicon glyphicon-ok-circle"></span> 启用</button>
                                </c:when>
                                <c:otherwise>
                                    <button type="button" class="btn btn-warning">
                                        <span class="glyphicon glyphicon-remove-circle"></span> 停用</button>
                                </c:otherwise>
                            </c:choose>
                        </td>
                            <td class="btns">
                                <button type="button" class="btn btn-success" >
                                    <a href="/community/enclosureAction_showEnclosure.do?enclosureName=<s:property value="enclosureName"/>"><span class="glyphicon glyphicon-ok-circle"></span> 编辑</a></button>
                                    <input type="hidden" value="<s:property value="id" />" name="enclosureCurrentId"/>
                                    <input type="hidden" value="<s:property value="coordinateType"/>" name="enclosureCurrentType" />
                                <button type="submit" class="btn btn-danger" ><span class="glyphicon glyphicon-remove-circle"></span> 删除</button>
                            </td>
                    </tr>
                </s:iterator>
                    </form>
                </tbody>
            </table>
            <div style="width:800px;margin:0 auto;">
                <div id="kkpager"></div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="/static/js/kkpaper/kkpager.min.js"></script>
<link rel="stylesheet" type="text/css" href="/static/css/kkpaper/kkpager_blue.css" />
<script src="/static/js/jquery/jquery-1.11.0.min.js"></script>
<script src="/static/js/bootstrap/bootstrap.min.js"></script>
<script src="/static/js/background/milkybg.js"></script>
<script src="/static/js/table/jquery.filtertable.js"></script><!--表格过滤-->
<script src="/static/js/jQuery.print.js"></script><!--打印-->
<script src="/static/js/table/export/xlsx.core.min.js"></script><!--输出表格-->
<script src="/static/js/table/export/blob.js"></script><!--输出表格-->
<script src="/static/js/table/export/FileSaver.min.js"></script><!--输出表格-->
<script src="/static/js/table/export/tableexport.min.js"></script><!--输出表格-->
<script  src="/static/js/clipboard.min.js"></script><!--复制-->
<script>
//    <!--表格过滤-->
    $('table').filterTable({
        inputSelector: '#searbtn-input',
        minRows:1
    });
//    <!--输出表格-->
$(function(){
    $("table").tableExport({
        headings: true,
        footers: true,
        formats: [ "xlsx","xls","csv","txt"],
        fileName: "围栏设置管理表",
        bootstrap: true,
        position: "bottom",
        ignoreRows: null,
        ignoreCols: null});
//        <!--打印-->
    $('.print').on('click',function () {
        $("table").print({
            globalStyles: false,
            iframe:false,
            noPrintSelector: "caption,.btns",
            stylesheet:"/static/css/bootstrap/bootstrap.min.css"
        });
    })
//         <!--复制-->
    var clipboard = new Clipboard('.copy');
    clipboard.on('success', function(e) {
        alert("文字已复制到剪贴板中");
    });
    clipboard.on('error', function(e) {
    });

    $('#recordnumber').change(function () {

        var record = $("#recordnumber").val();
        window.location.href = "/community/enclosureAction_getAllEnclosure.do?pagesize=" + record;
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
            hrefFormer : '/community/enclosureAction_getAllEnclosure',
            //链接尾部
            hrefLatter : '.do',
            getLink : function(n){
                return this.hrefFormer + this.hrefLatter + "?pno="+n;
            }

        });
    });
</script>
</body>
</html>