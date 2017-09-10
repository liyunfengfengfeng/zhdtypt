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
    <title>Title</title>
    <link rel="stylesheet" href="/static/css/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/table/table.css">
    <script type="text/javascript" src="/static/js/kkpaper/kkpager.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/static/css/kkpaper/kkpager_blue.css" />

</head>
<body>
<canvas id="canvas" style="position:fixed;z-index:-1;height: 100%;width: 100%;"></canvas>
<div class="container">
    <div class="col-md-12" id="record">
        <div class="title">考勤组</div>
        <div class="topcontent">
            <button type="button" class="btn btn-info recordbtn"><a href="/checkwork/CheckWorkAction_addCheckWork.do"><span class="glyphicon glyphicon-plus"></span> 新增考勤组</a></button>
        </div>
    </div>
    <div class="col-md-12" >
        <div class="table-responsive" id="contentmain" >
            <table  class="table table-bordered">
                <div style="width: 100%;margin-bottom: 10px">
                    <div class="contentcontrol">
                        <div class="number">
                            <select name="recordnumber" id="recordnumber" >
                                <option value="10"  <s:if test="%{#request.pageSize==10}">selected</s:if> >10</option>
                                <option value="25"  <s:if test="%{#request.pageSize==25}">selected</s:if>  >25</option>
                                <option value="50"  <s:if test="%{#request.pageSize==50}">selected</s:if> >50</option>
                                <option value="100" <s:if test="%{#request.pageSize==100}">selected</s:if> >100</option>
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
                                    <li><a href="javascript:void(0)"><input type="checkbox" checked="checked"> 角色名称</a></li>
                                    <li><a href="javascript:void(0)"><input type="checkbox" checked="checked"> 包含用户</a></li>
                                    <li><a href="javascript:void(0)"><input type="checkbox" checked="checked"> 创建时间</a></li>
                                    <li><a href="javascript:void(0)"><input type="checkbox" checked="checked"> 更新时间</a></li>
                                    <li><a href="javascript:void(0)"><input type="checkbox" checked="checked"> 描述</a></li>
                                    <li><a href="javascript:void(0)"><input type="checkbox" checked="checked"> 状态</a></li>
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
                    <th style="width:10%">角色名称</th>
                    <th style="width:10%">包含用户</th>
                    <th style="width:10%">创建时间</th>
                    <th style="width:10%">更新时间</th>
                    <th style="width:10%">描述</th>
                    <th style="width:10%">状态</th>
                    <th style="width:20%">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:if test="${ ! empty requestScope.checkWorks }">
                <c:forEach items="${requestScope.checkWorks }" var="checkWork" varStatus="abc">
                <tr>
                    <%--隐藏标签考勤组id--%>
                    <input type="hidden" value="${checkWork.id}" name="checkworkid" id="checkworkid"/>
                    <td>${abc.count}</td>
                    <td><a href="">${checkWork.rolename}</a></td>
                    <td>${checkWork.includeusers}</td>
                    <td>${checkWork.createtime}</td>
                    <td>${checkWork.updatetime}</td>
                    <td>${checkWork.decriptions}</td>
                    <td>
                        <c:choose>
                            <c:when test="${ checkWork.status==1 }">
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
                        <button type="button" class="btn btn-success" id="btn-edit" style="width:50%;float: left;font-size: 12px">
                            <a href="/checkwork/CheckWorkAction_editCheckWork.do?checkWorkId=${checkWork.id}"><span class="glyphicon glyphicon-ok-circle"></span> 编辑</a></button>
                        <button type="button" id="delcheckworkbutton"  class="btn btn-danger" style="width:50%;float: right;font-size: 12px"><a href="/checkwork/CheckWorkAction_delCheckWorkInfo.do?checkid=${checkWork.id}"><span class="glyphicon glyphicon-remove-circle"></span> 删除</a></button>
                    </td>
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
</div>
<script src="/static/js/jquery/jquery-1.11.0.min.js"></script>
<script src="/static/js/bootstrap/bootstrap.min.js"></script>
<script src="/static/js/background/milkybg.js"></script>
<script src="/static/js/table/jquery.filtertable.js"></script><!--表格过滤-->
<script src="/static/js/table/jQuery.print.js"></script><!--打印-->
<script src="/static/js/table/export/xlsx.core.min.js"></script><!--输出表格-->
<script src="/static/js/table/export/blob.js"></script><!--输出表格-->
<script src="/static/js/table/export/FileSaver.min.js"></script><!--输出表格-->
<script src="/static/js/table/export/tableexport.min.js"></script><!--输出表格-->
<script  src="/static/js/table/clipboard.min.js"></script><!--复制-->
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
            fileName: "考勤管理表",
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


            //每页展示的记录数
            $('#recordnumber').change(function(){

                var record = $("#recordnumber").val();


                window.location.href = "/checkwork/CheckWorkAction_toCheckWork.do?pagesize="+record;
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
            hrefFormer : 'CheckWorkAction_toCheckWork',
            //链接尾部
            hrefLatter : '.do',
            getLink : function(n){
                return this.hrefFormer + this.hrefLatter + "?pno="+n;
            }

        });
    });
</script>
<script>


</script>
</body>
</html>