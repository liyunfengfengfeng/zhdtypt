<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="/static/css/jquery.dataTables.css">
    <!-- jQuery -->
    <script type="text/javascript" charset="utf8" src="/static/js/jquery/jquery.min.js"></script>
    <!-- DataTables -->
    <script type="text/javascript" charset="utf8" src="/static/js/jquery.dataTables.js"></script>
</head>
<body>
<%--html--%>

<table class="table table-bordered table-hover" id="bigDataList">
    <thead>
    <tr>
        <th>
            用户id
        </th>
        <th>
            主叫
        </th>
        <th>
            识别码
        </th>
    </tr>
    </thead>
    <tbody>

    </tbody>
</table>


<script>
    var table1;
    //dataTable初始化对象
    function bigDataTable(queryData){
        table1= $('#bigDataList').DataTable({
            "paging": true,
            "lengthChange": true,
            "searching": true,
            "ordering": true,
            "aaSorting" : [[0, "asc"]], //默认的排序方式，第1列，升序排列
            "info": true,
            "autoWidth": false,
            "destroy":true,
            "processing":true,
            "scrollX": true,   //水平新增滚动轴
            "serverSide":true,    //true代表后台处理分页，false代表前台处理分页
            "aLengthMenu":[5,10,15,20],
            "PaginationType" : "full_numbers", //详细分页组，可以支持直接跳转到某页
            //当处理大数据时，延迟渲染数据，有效提高Datatables处理能力
            "deferRender": true,
            "ajax":{
                url:"CheckAccount_queryPersons1.do?param=1016108113@qq.com",
                dataSrc:
                        function(data){

                            console.log(data);

                            return data.dataList;             //自定义数据源，默认为data
                        },     //dataSrc相当于success，在datatable里面不能用success方法，会覆盖datatable内部回调方法
                type:"post",
            },
            columns:[
                { data: 'account0Id' },
                { data: 'cmpId'},
                { data: 'sex' }
            ],

            /*是否开启主题*/
            "bJQueryUI": true,
            "oLanguage": {    // 语言设置
                "sLengthMenu": "每页显示 _MENU_ 条记录",
                "sZeroRecords": "抱歉， 没有找到",
                "sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
                "sInfoEmpty": "没有数据",
                "sInfoFiltered": "(从 _MAX_ 条数据中检索)",
                "sZeroRecords": "没有检索到数据",
                "sSearch": "检索:",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "前一页",
                    "sNext": "后一页",
                    "sLast": "尾页"
                }
            },
        });
    }


</script>
<script type="text/javascript">
    $(document).ready(function() {
        $('#bigDataList').dataTable();//其中example为table的id，table中必须有thead！
    } );
</script>
</body>
</html>
