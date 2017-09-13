<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>小区管理</title>
    <link rel="stylesheet" href="/static/css/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/table/table.css">
</head>
<body>
<canvas id="canvas" style="position:fixed;z-index:-1;height: 100%;width: 100%;"></canvas>
<div class="container">
    <div class="col-md-12" id="record">
        <div class="title">小区设置</div>
        <div class="topcontent">
            <button type="button" class="btn btn-info recordbtn"><a href="/community/communityAction_changePage.do">
                <span class="glyphicon glyphicon-plus"></span> 新增小区</a>
            </button>
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
                            <div class="btn-group" style="float: right">
                                <button type="button" class="btn btn-default dropdown-toggle"
                                        data-toggle="dropdown">
                                    显示/隐藏列 <span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a href="javascript:void(0)"><input type="checkbox" checked="checked"> 序号</a></li>
                                    <li><a href="javascript:void(0)"><input type="checkbox" checked="checked"> 小区名称</a></li>
                                    <li><a href="javascript:void(0)"><input type="checkbox" checked="checked"> 详细地址</a></li>
                                    <li><a href="javascript:void(0)"><input type="checkbox" checked="checked"> 所属城区</a></li>
                                    <li><a href="javascript:void(0)"><input type="checkbox" checked="checked"> 所属城市</a></li>
                                    <li><a href="javascript:void(0)"><input type="checkbox" checked="checked"> 操作</a></li>
                                </ul>
                            </div>

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
                    <th style="width:10%">序号</th>
                    <th style="width:10%">小区</th>
                    <th style="width:10%">详细地址</th>
                    <th style="width:10%">所属城区</th>
                    <th style="width:10%">所属城市</th>
                    <th style="width:20%">操作</th>
                </tr>
                </thead>
                <tbody>
                <s:iterator value="#request.communityList" status="status">
                    <form action="/community/communityAction_deleteCommunity.do" method="post">
                        <tr>
                            <td><s:property value="#status.index+1"/></td>
                            <td><a href="/community/communityAction_showCommunity.do?communityName=<s:property value="communityName"/>"><s:property value="communityName"/></a></td>
                            <td><s:property value="communityAddress"/></td>
                            <td><s:property value="#request.districtNameList[#status.index]"/></td>
                            <td><s:property value="#request.cityNameList[#status.index]"/></td>
                                <td class="btns">
                                    <input type="hidden" value="<s:property value="communityId" />" name="communityId" />
                                    <input type="hidden" value="<s:property value="coordinateType"/>" name="coordinateType" />
                                    <button type="button" class="btn btn-success" style="width:50%;float: left;font-size: 12px">
                                        <a href="/community/communityAction_showCommunity.do?communityName=<s:property value="communityName"/>"><span class="glyphicon glyphicon-ok-circle"></span> 编辑</a></button>
                                    <button type="submit" class="btn btn-danger" style="width:50%;float: right;font-size: 12px"><span class="glyphicon glyphicon-remove-circle"></span> 删除</button>
                                </td>
                        </tr>
                    </form>
                </s:iterator>
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
    //        显示隐藏列
    var $table=$('table');
    function showOrhidden(index){
        $('table tr').find('th:eq('+index+')').toggle();
        $('table tr').find('td:eq('+index+')').toggle();
    }
    $('input:checkbox').on('click',function () {
        index=$(this).parents("li").index();
        showOrhidden(index);
    })
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
            stylesheet:"css/bootstrap/bootstrap.min.css"
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
        window.location.href = "/communityAction_getAllCommunity.do?pagesize=" + record;
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
            hrefFormer : 'communityAction_getAllCommunity',
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