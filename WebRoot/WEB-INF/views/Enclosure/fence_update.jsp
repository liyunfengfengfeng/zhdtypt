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
            background: rgb(229, 243, 233);
        }
        #title{
            height: 55px;
            /*width: 100%;*/
            /*margin: 40px auto;*/
            margin-bottom: 30px;
            background: #015775;
            border: solid 3px #015775;
            box-shadow: 0px 0px 2px 6px #015775;
            border-radius: 3px;
        }
        #content{
            font-size: 18px;
            color: white;
            background: #015775;
            border: solid 3px #015775;
            box-shadow: 0px 0px 2px 6px #015775;
            border-radius: 3px;
        }
        #l-map{height:300px;width:100%;}
        #r-result{width:100%;}
        #control{width:100%;}
        .show{
            display: block;
        }
        .hide{
            display: none;
        }
    </style>
</head>
<body>
<canvas id="canvas" style="position:fixed;z-index:-1;height: 100%;width: 100%;"></canvas>
<div class="container">
    <div class="row">
        <div class="col-sm-12" id="title">
            <span class="text-left"><h3 style="color: #ffffff">编辑电子围栏</h3></span>
        </div>
    </div>
    <div class="row" id="content">
            <div class="row" >
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="enclosureName" class="col-sm-4 control-label text-left">围栏名称</label>
                        <div class="col-sm-8">
                            <input name="enclosureName" id="enclosureName" class="form-control" type="text" value="${enclosure.enclosureName}" AUTOCOMPLETE=OFF />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="enclosureDescriptions" class="col-sm-4 control-label text-left"> 围栏描述 </label>
                        <div class="col-sm-8">
                            <textarea class="form-control" id="enclosureDescriptions" name="enclosureDescriptions" rows="3">${enclosure.descriptions}</textarea>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="attendanceName" class="col-sm-4 control-label text-left">考勤组</label>
                        <div class="col-sm-8">
                            <select name="attendanceName" id="attendanceName" class="form-control">
                                <s:iterator var="attendanceteam" value="#request.attendanceteamList" id="attendanceteam">
                                    <option value="<s:property value="rolename"/>" <s:if test="#request.attendanceteamName.equals(#attendanceteam.rolename)">selected</s:if>><s:property value="rolename"/></option>
                                </s:iterator>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label  class="col-sm-4 control-label text-left">状态</label>
                        <div class="col-sm-8">
                            <!--<input class="form-control" type="text"  AUTOCOMPLETE=OFF/>-->
                            <div class="radio">
                                <label>
                                    <input type="radio" name="status"  value="1" <s:if test="%{#request.enclosure.status==1}">checked</s:if>> 启用
                                </label>
                                <label>
                                    <input type="radio" name="status"  value="0" <s:if test="%{#request.enclosure.status == 0}">checked</s:if>>  停用
                                </label>
                            </div>
                        </div>
                    </div>
                </div>


                <div class="col-sm-12" style="padding: 5px;">
                    <div id="l-map" style="border:solid 4px #fff"></div>
                </div>
                <div class="col-sm-12">
                    <div class="center-block" style="width: 320px">
                        <button class="btn btn-warning backbtn" style="margin-top: 30px;width: 150px;font-size: 20px"><span class="glyphicon glyphicon-circle-arrow-left"></span> 返回</button>
                        <button type="button" class="btn btn-success" id="submit" style="margin-top: 30px;width: 150px;font-size: 20px"><span class="glyphicon glyphicon-saved"></span>修改</button></button>
                    </div>
                </div>
            </div>
        <div class="row">
            <div class="col-sm-6 btn-group" style="padding-top: 10px;">
                <button type="button" class="btn btn-info" id="btn1">使用多边形框选围栏</button>
                <button type="button" class="btn btn-info" id="btn2">使用圆形框选围栏</button>
            </div>
            <div class="col-sm-6" style="padding-top: 10px;">
                <div class="btn-group" style="color: #0f0f0f;float:right;display: none;" id="polygonbtn">
                    <button type="button" id="btn3" class="btn btn-warning" onclick="polygon.enableEditing();">开启多边形编辑功能</button>
                    <button type="button" class="btn btn-warning" onclick="polygon.disableEditing();">关闭多边形编辑功能</button>
                </div>
                <div class="btn-group" style="float:right;color: #0f0f0f;display: none" id="circlebtn" >
                    <button type="button" id="btn4" class="btn btn-warning" onclick="circle.enableEditing();">开启圆形编辑功能</button>
                    <button type="button" class="btn btn-warning" onclick="circle.disableEditing();">关闭圆形编辑功能</button>
                </div>
            </div>
        </div>
    </div>


</div>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=0wGYtEGXfdsZnyKWuOOyzNAwjcQBhtyK"></script>
<script src="/static/js/jquery/jquery-1.11.0.min.js"></script>
<script src="/static/js/bootstrap/bootstrap.min.js"></script>
<script src="/static/js/tojson.js"></script>
<script src="/static/js/background/milkybg.js"></script>
<script>
    $(function () {
        $('.backbtn').on('click',function () {
            history.back();
        })
    })
</script>
<script type="text/javascript" >

    var enclosureType = ${enclosure.coordinateType};
    //定义圆形
    var circle;
    //定义多边形
    var polygon;
    //定义寻找到的点
    var pp;

    var polygonLng = [];
    var polygonLat = [];

    var circleRadius;
    var circleCenterLng;
    var circleCenterLat;
    var points = new Array();

    <c:forEach items="${requestScope.polygoncoordinateList}" var="polygon" varStatus="status">
    points[${status.count} - 1] = new BMap.Point(${polygon.polygonLng}, ${polygon.polygonLat});
    </c:forEach>

    //使用两个按钮区分多边形或圆形
    var btn1 = $('#btn1');
    var btn2 = $('#btn2');
    var $polygonbtn=$('#polygonbtn');
    var $circlebtn=$('#circlebtn');
    btn1.on ('click',function () {
        if($('#suggestId').val()==''){
            alert("请先写入地区");
        }else{
            $polygonbtn.removeClass('hide').addClass('show');
            $circlebtn.removeClass('show').addClass('hide');
            coordinateType = 0;
            map.clearOverlays();
            reusePolygon();
        }
    })

    btn2.on('click',function () {
        if($('#suggestId').val()==''){
            alert("请先写入地区");
        }else {
            $circlebtn.removeClass('hide').addClass('show');
            $polygonbtn.removeClass('show').addClass('hide');
            map.clearOverlays();
            coordinateType = 1;
            reuseCircle();
        }
    })

    $("#submit").on('click',function (event) {
        var enclosureName = $("#enclosureName").val();
        var enclosureCurrentName = "${enclosure.enclosureName}";
        var attendanceName= $("#attendanceName").val();
        var enclosureDescriptions = $("#enclosureDescriptions").val();
        var enclosureCurrentType = ${enclosure.coordinateType};
        var status = $("input[name='status']:checked").val();
        if(enclosureName==""||enclosureDescriptions=="") {
            alert('请完善所有信息后提交');
        }else {
        if(enclosureType == 0)
        {
            getPoint();
            var json = {"enclosureCurrentName":enclosureCurrentName, "status":status, "enclosureDescriptions":enclosureDescriptions, "attendanceName":attendanceName, "enclosureCurrentType":${enclosure.coordinateType}, "enclosureName":enclosureName, "enclosureType":enclosureType, "polygonLng":polygonLng, "polygonLat":polygonLat};
        }
        else if(enclosureType == 1)
        {
            getCirleInfo();
            var json = {"enclosureCurrentName":enclosureCurrentName, "status":status, "enclosureDescriptions":enclosureDescriptions, "attendanceName":attendanceName, "enclosureCurrentType":${enclosure.coordinateType},"enclosureName":enclosureName, "enclosureType":enclosureType, "circleRadius":circleRadius, "circleCenterLng":circleCenterLng, "circleCenterLat":circleCenterLat};

        }
        //var json=JSON.stringify($("#enclosure").serializeObject());
        console.log("111111");
        console.log(json);
        $.ajax({
            type: "POST",
            url: "/community/enclosureAction_updateEnclosure.do",
            contentType: "application/json",
            dataType: "html json",
            data: JSON.stringify(json),
            success:function (data) {
                //var jsondata = eval("("+data+")");
                // console.log(jsondata);
                //console.log(jsondata.state);
                // $(data).each()
                // console.log(data);
                /*var jsondata = eval("("+data+")");
                 console.log(jsondata.state);
                 msgtip(parseInt(jsondata.state));*/
                console.log("fuck u");
                window.location.href = "/community/enclosureAction_getAllEnclosure.do";
            }

        })
        }
    });
    // 百度地图API功能
    function G(id) {
        return document.getElementById(id);
    }

    var map = new BMap.Map("l-map");
    var point;

    judgeType();

    function judgeType()
    {
        if(parseInt(${enclosure.coordinateType} )== 0)
        {
            point = new BMap.Point(${polygoncoordinate.polygonLng},${polygoncoordinate.polygonLat});
            usePolygon();
        }
        else if(parseInt(${enclosure.coordinateType}) == 1)
        {
            point = new BMap.Point(${circlecoordinate.circleLng},${circlecoordinate.circleLat});
            useCircle();
        }
    }

    map.centerAndZoom(point,17);                   // 初始化地图,设置城市和地图级别。
    map.enableScrollWheelZoom();

    function usePolygon(){
        //添加六边形
        polygon = new BMap.Polygon(points,
                {strokeColor:"blue", strokeWeight:2, strokeOpacity:0.5}
        );
        map.addOverlay(polygon);
    }

    function useCircle(){
        //添加圆形
        var circlePoint = new BMap.Point(${circlecoordinate.circleLng}, ${circlecoordinate.circleLat});
        circle = new BMap.Circle(circlePoint,${circlecoordinate.circleRadius});
        map.addOverlay(circle);
    }

    function reusePolygon() {
        //添加六边形
        polygon = new BMap.Polygon([
                    new BMap.Point(point.lng+0.0003,point.lat),
                    new BMap.Point(point.lng-0.0001,point.lat+0.0003),
                    new BMap.Point(point.lng-0.0001,point.lat-0.0003)],
                {strokeColor:"blue", strokeWeight:2, strokeOpacity:0.5}
        );
        map.addOverlay(polygon);
    }

    function reuseCircle() {
        //添加圆形
        circle = new BMap.Circle(new BMap.Point(point.lng + 0.0001, point.lat+0.0001), 10, {strokeColor:"blue", strokeWeight:2, strokeOpacity:0.5});
        map.addOverlay(circle);
    }

    function getPoint(){
        var polygonPath = polygon.getPath();

        for(var i = 0; i < polygonPath.length; i++){
            console.log(polygonPath[i].lng, polygonPath[i].lat);
            polygonLng[i] = polygonPath[i].lng;
            polygonLat[i] = polygonPath[i].lat;
        }
    }

    function getCirleInfo(){
        var circleCenter = circle.getCenter();
        circleRadius = circle.getRadius();
        circleCenterLng = circleCenter.lng;
        circleCenterLat = circleCenter.lat;
    }
</script>
</body>
</html>