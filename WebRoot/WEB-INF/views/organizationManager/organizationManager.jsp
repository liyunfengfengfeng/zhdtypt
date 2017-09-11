<%@page language="java" import="java.util.*"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>组织管理</title>
    <link rel="stylesheet" href="/static/css/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/kkpaper/kkpager_blue.css">
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
        /*table>tbody tr td{*/
        /*text-align: center;*/
        /*}*/
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
            background-color: orangered;
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
    </style>
</head>
<body>
<canvas id="canvas" style="position:fixed;z-index:-1;height: 100%;width: 100%;"></canvas>
<div class="container">
    <div style="height: 100px"></div>
    <div class="row">
        <div class="col-md-12" id="record">
            <div class="title">单位档案</div>
            <div class="topcontent">
                <button type="button" class="btn recordbtn"><a href="OrganizationManager_addPage.do"><span class="glyphicon glyphicon-plus"></span> 新增</a></button>
            </div>
        </div>
            <div class="col-md-12" >
            <div class="table-responsive" id="contentmain" >
                <table  class="table table-bordered">
                    <div style="width: 100%;margin-bottom: 10px">
                        <div class="contentcontrol">
                            <div class="number">显示
                                <select id="recordnumber" >
                                    <option value="<s:property value="%{#request.pageSize}"/>">(<s:property value="%{#request.pageSize}"/>)</option>
                                    <option value="5">5</option>
                                    <option value="10">10</option>
                                    <option value="15">15</option>
                                    <option value="20">20</option>
                                </select><span style="color: white"> 条记录</span>
                            </div>
                            <div style="float: right">
                                <div class="btn-group btns" style="float: right;font-size: 15px;">
                                    <button type="button" class="btn btn-default copy" data-clipboard-target="table">复制</button>
                                    <!--<button type="button" class="btn btn-default csv">CSV</button>-->
                                    <button type="button" class="btn btn-default print">打印</button>
                                </div>
                                <div class="input-group search" style="width: 300px;float: right">
                                                               <span class="input-group-btn">
                                                              <button class="btn btn-default" type="button" >搜索</button>
                                                               </span>
                                    <input type="text" class="form-control">
                                </div>
                            </div>
                        </div>
                    </div>
                    <thead>
                    <tr>
                        <th style="width:6%">序号</th>
                        <th style="width:10%">单位名称</th>
                        <th style="width:14%">地址</th>
                        <th style="width:7%">负责人</th>
                        <th style="width:13%">电话</th>
                        <th style="width:13%">传真</th>
                        <th style="width:17%">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <s:iterator value="%{#request.organizationList}" id="organization" status="item">
                        <tr>
                            <td class="altbg1" align="center" nowrap="nowrap">
                                <s:property value="%{#item.getIndex()+1}"/>
                                <br>
                            </td>
                            <td><s:property value="#organization.cmp_name"/></td>
                            <td><s:property value="#organization.cmp_address"/></td>
                            <td><s:property value="#organization.law_person"/></td>
                            <td><s:property value="#organization.phone"/></td>
                            <td><s:property value="#organization.fax"/></td>
                            <td>
                                <div class="btn-group center-block">
                                    <button type="button" class="btn btn-info" name="${organization.cmp_id}"><span class="glyphicon glyphicon-search"></span> 查看</button>
                                    <button type="button" class="btn btn-success" name="${organization.cmp_id}"><span class="glyphicon glyphicon-ok-circle"></span> 编辑</button>
                                    <button type="button" class="btn btn-danger"name="${organization.cmp_id}"><span class="glyphicon glyphicon-remove-circle"></span> 删除</button>
                                </div>
                            </td>
                        </tr>
                    </s:iterator>
                    </tbody>
                </table>
                <div style="width:800px;margin:0 auto;">
                    <div id="kkpager"></div>
                </div>
            </div>
        </div>

    </div>
</div>

<script src="/static/js/kkpaper/kkpager.js"></script>
<script src="/static/js/kkpaper/kkpager.min.js"></script>
<script src="/static/js/jquery/jquery-2.1.1.min.js"></script>
<script src="/static/js/bootstrap/bootstrap.min.js"></script>
<script src="/static/js/milkybg.js"></script>
<script src="/static/js/jQuery.print.js"></script>
<script src="/static/js/export/xlsx.core.min.js"></script>
<script src="/static/js/export/blob.js"></script>
<script src="/static/js/export/FileSaver.min.js"></script>
<script src="/static/js/export/tableexport.min.js"></script>
<script type="text/javascript">
    $(function(){
        $("table").tableExport({
            headings: true,
            footers: true,
            formats: [ "xlsx","xls","csv","txt"],
            fileName: "组织单位管理表",
            bootstrap: true,
            position: "bottom",
            ignoreRows: null,
            ignoreCols: null});

        $('.print').on('click',function () {
            $("table").print({
                globalStyles: false,
                iframe:false,
                noPrintSelector: "caption"
            });
        })

    })
</script>
<script  src="/static/js/clipboard.min.js"></script>
<script>
    var clipboard = new Clipboard('.copy');
    clipboard.on('success', function(e) {
        alert("文字已复制到剪贴板中");
//        console.log(e);
    });

    clipboard.on('error', function(e) {
//        console.log(e);
    });
</script>
<script type="text/javascript">
    //每页展示的记录数
    $('#recordnumber').change(function(){
        var record = $("#recordnumber").val();
        window.location.href = "OrganizationManager_archive.do?pageSize="+record;
    })


    $('.btn-info').on('click',function () {
        var str1=$(this).attr("name"); // jQuery attr() 方法用于获取属性值
        var str2="OrganizationManager_lookOver.do?param="+str1;
        window.location.href = str2;
    })
    $('.btn-success').on('click',function () {
        var str1=$(this).attr("name"); // jQuery attr() 方法用于获取属性值
        var str2="OrganizationManager_edit.do?param="+str1;
        window.location.href = str2;
    })
    $('.btn-danger').on('click',function () {
        var str1=$(this).attr("name"); // jQuery attr() 方法用于获取属性值
        var str2="OrganizationManager_delete.do?param="+str1;
        window.location.href = str2;
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
            hrefFormer : 'OrganizationManager_archive',
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
