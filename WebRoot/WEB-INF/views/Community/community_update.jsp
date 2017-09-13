<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>编辑小区围栏</title>
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
            <span class="text-left"><h3 style="color: #ffffff">新增小区围栏</h3></span>
        </div>
    </div>
    <div class="row" id="content">
            <div class="row" >
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="communityName" class="col-sm-4 control-label text-left">小区名称</label>
                        <div class="col-sm-8">
                            <input name="communityName" id="communityName" class="form-control" type="text" placeholder="${community.communityName}" value="${community.communityName}" AUTOCOMPLETE=OFF />
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="form-group">
                            <label for="communityName" class="col-sm-4 control-label text-left">所在城区</label>
                            <div class="col-sm-8">
                                <input type="hidden" name="belongDistrict" id="belongDistrict" value="${community.belongDistrict}">
                                <input class="form-control" type="text" value="${districtName}" AUTOCOMPLETE=OFF disabled />
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="form-group">
                            <label for="communityName" class="col-sm-4 control-label text-left">所在城市</label>
                            <div class="col-sm-8">
                                <input type="hidden" name="belongCity" id="belongCity" value="${community.belongCity}">
                                <input class="form-control" type="text" value="${cityName}" AUTOCOMPLETE=OFF disabled />
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="communityAddress" class="col-sm-4 control-label text-left">小区详细地址</label>
                        <div class="col-sm-8">
                            <input name="communityAddress" id="communityAddress" class="form-control" type="text" placeholder="${community.communityAddress}" value="${community.communityAddress}" AUTOCOMPLETE=OFF />
                        </div>
                    </div>

                </div>
                <div class="col-sm-12" style="padding: 5px;">
                    <div id="l-map" style="border:solid 4px #fff"></div>
                </div>
                <div class="col-sm-12">
                    <div class="center-block" style="width: 320px">
                        <button class="btn btn-warning backbtn" style="margin-top: 30px;width: 150px;font-size: 20px"><span class="glyphicon glyphicon-circle-arrow-left"></span> 返回</button>
                        <button class="btn btn-success" id="submit" style="margin-top: 30px;width: 150px;font-size: 20px"><span class="glyphicon glyphicon-saved"></span>  修改</button></button>
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
                    <button type="button" class="btn btn-warning" onclick="polygon.enableEditing();">开启多边形编辑功能</button>
                    <button type="button" class="btn btn-warning" onclick="polygon.disableEditing();">关闭多边形编辑功能</button>
                </div>
                <div class="btn-group" style="float:right;color: #0f0f0f;display: none" id="circlebtn" >
                    <button type="button" class="btn btn-warning" onclick="circle.enableEditing();">开启圆形编辑功能</button>
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
    //定义使用多边形或圆形通过Type区分
    var coordinateType = ${community.coordinateType};

    //定义圆形
    var circle;

    //定义多边形
    var polygon;

    //定义寻找到的点
    var pp;

    //定义多边形点
    var polygonLng = [];
    var polygonLat = [];

    //定义圆形点
    var circleRadius;
    var circleCenterLng;
    var circleCenterLat;
    var points = new Array();


    <c:forEach items="${requestScope.communitypolygoncoordinateList}" var="polygon" varStatus="status">
    points[${status.count} - 1] = new BMap.Point(${polygon.communityPolygonLng}, ${polygon.communtiyPolygonLat});
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


    /**
     * json判断并传输数据
     */
    $("#submit").on('click',function (event) {
        var communityName = $("#communityName").val();
        var communityAddress = $("#communityAddress").val();
        var belongDistrict = $("#belongDistrict").val();
        var belongCity = $("#belongCity").val();
        var communityCurrentName = "${community.communityName}";
        var communityCurrentType = ${community.coordinateType};
        if(communityName==""||communityAddress=="") {
            alert('请完善所有信息后提交');
        }else {
            if (coordinateType == 0) {
                getPoint();
                var json = {
                    "communityCurrentName": communityCurrentName,
                    "communityCurrentType": communityCurrentType,
                    "communityName": communityName,
                    "communityAddress": communityAddress,
                    "belongDistrict": belongDistrict,
                    "belongCity": belongCity,
                    "coordinateType": coordinateType,
                    "polygonLng": polygonLng,
                    "polygonLat": polygonLat
                };
            }
            else if (coordinateType == 1) {
                getCirleInfo();
                var json = {
                    "communityCurrentName": communityCurrentName,
                    "communityCurrentType": communityCurrentType,
                    "communityName": communityName,
                    "communityAddress": communityAddress,
                    "belongDistrict": belongDistrict,
                    "belongCity": belongCity,
                    "coordinateType": coordinateType,
                    "circleRadius": circleRadius,
                    "circleCenterLng": circleCenterLng,
                    "circleCenterLat": circleCenterLat
                };
            }
            console.log(json);
            $.ajax({
                type: "POST",
                url: "/community/communityAction_updateCommunity.do",
                contentType: "application/json",
                dataType: "html json",
                data: JSON.stringify(json),
                success: function (data) {
                    //var jsondata = eval("("+data+")");
                    // console.log(jsondata);
                    //console.log(jsondata.state);
                    // $(data).each()
                    // console.log(data);
                    /*var jsondata = eval("("+data+")");
                 console.log(jsondata.state);
                 msgtip(parseInt(jsondata.state));*/
                    console.log("fuck u");
                window.location.href = "/community/communityAction_getAllCommunity.do";
            }

        })
            }
    })
    // 百度地图API功能
    function G(id) {
        return document.getElementById(id);
    }



    var map = new BMap.Map("l-map");
    var point;

    if(parseInt(${community.coordinateType} )== 0)
    {
        point = new BMap.Point(${communitypolygoncoordinate.communityPolygonLng},${communitypolygoncoordinate.communtiyPolygonLat});
        usePolygon();
    }
    else if(parseInt(${community.coordinateType}) == 1)
    {
        point = new BMap.Point(${communitycirclecoordinate.communityCircleLng},${communitycirclecoordinate.communityCircleLat});
        useCircle();
    }

    map.centerAndZoom(point,17);                   // 初始化地图,设置城市和地图级别。
    map.enableScrollWheelZoom();

    var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
            {"input" : "suggestId"
                ,"location" : map
            });

    ac.addEventListener("onhighlight", function(e) {  //鼠标放在下拉列表上的事件
        var str = "";
        var _value = e.fromitem.value;
        var value = "";
        if (e.fromitem.index > -1) {
            value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
        }
        str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;

        value = "";
        if (e.toitem.index > -1) {
            _value = e.toitem.value;
            value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
        }
        str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
        G("searchResultPanel").innerHTML = str;
    });

    var myValue;
    ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
        var _value = e.item.value;
        myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
        G("searchResultPanel").innerHTML ="onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;
        setPlace();
    });
    /**
     *	使用多边形框选围栏
     */
    function usePolygon(){
        //添加六边形
        polygon = new BMap.Polygon(points,
                {strokeColor:"blue", strokeWeight:2, strokeOpacity:0.5}
        );
        map.addOverlay(polygon);
    }

    /**
     * 使用圆形框选围栏
     */
    function useCircle(){
        //添加圆形
        var circlePoint = new BMap.Point(${communitycirclecoordinate.communityCircleLng}, ${communitycirclecoordinate.communityCircleLat});
        var circleRadius = ${communitycirclecoordinate.communityCircleRadius}
                circle = new BMap.Circle(circlePoint, circleRadius, {strokeColor:"blue", strokeWeight:2, strokeOpacity:0.5});
        map.addOverlay(circle);
    }

    /**
     * 获取当前搜索地点信息
     */
    function setPlace(){
        map.clearOverlays();    //清除地图上所有覆盖物
        function myFun(){
            pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
            //设置屏幕中心显示地图的坐标
            map.centerAndZoom(pp, 18);
            //鼠标滑轮可以控制地图缩放
            map.enableScrollWheelZoom();
        }
        var local = new BMap.LocalSearch(map, { //智能搜索
            onSearchComplete: myFun
        });
        local.search(myValue);
    }

    /**
     * 获取当前多边形的信息
     */
    function getPoint(){
        var polygonPath = polygon.getPath();

        for(var i = 0; i < polygonPath.length; i++){
            console.log(polygonPath[i].lng, polygonPath[i].lat);
            polygonLng[i] = polygonPath[i].lng;
            polygonLat[i] = polygonPath[i].lat;
        }
    }

    /**
     * 获取当前圆的信息
     */
    function getCirleInfo(){
        var circleCenter = circle.getCenter();
        circleRadius = circle.getRadius();
        console.log(circleRadius);
        circleCenterLng = circleCenter.lng;
        circleCenterLat = circleCenter.lat;
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
</script>
</body>
</html>