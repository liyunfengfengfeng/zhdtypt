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
            <span class="text-left"><h3 style="color: #ffffff">新增电子围栏</h3></span>
        </div>
    </div>
    <div class="row" id="content">
    <form action="" class="col-md-12" method="post"  id="enclosure" name="enclosure" onsubmit="return CheckForm()">
    <div class="row" >
        <div class="col-md-6">
            <div class="form-group">
                <label for="enclosureName" class="col-sm-4 control-label text-left">围栏名称</label>
                <div class="col-sm-8">
                    <input name="enclosureName" id="enclosureName" class="form-control" type="text" placeholder="请写入围栏名称" required="required" AUTOCOMPLETE=OFF />
                </div>
            </div>
            <div class="form-group">
                <label for="enclosureDescriptions" class="col-sm-4 control-label text-left"> 围栏描述 </label>
                <div class="col-sm-8">
                    <textarea class="form-control" id="enclosureDescriptions" name="enclosureDescriptions" rows="3"  required="required" placeholder="请写入您对围栏的描述..."></textarea>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="form-group">
                <label for="attendanceName" class="col-sm-4 control-label text-left">考勤组</label>
                <div class="col-sm-8">
                    <select name="attendanceName" id="attendanceName" class="form-control">
                        <option value="choose">请选择考勤组</option>
                        <s:iterator value="#request.attendanceNameList">
                            <option value="<s:property value="rolename"/>"><s:property value="rolename"/></option>
                        </s:iterator>
                    </select>
                </div>
                </div>
                <div class="form-group">
                    <label for="suggestId" class="col-sm-4 control-label text-left">地区范围</label>
                    <div class="col-sm-8">
                        <input name="suggestId" id="suggestId" class="form-control" type="text" placeholder="请写入地区"  required="required" AUTOCOMPLETE=OFF />
                    </div>
                    <div id="searchResultPanel" style="border:1px solid #C0C0C0;width:150px;height:auto; display:none;"></div>
                </div>
            <div class="form-group">
                <label  class="col-sm-4 control-label text-left">状态</label>
                <div class="col-sm-8">
                    <!--<input class="form-control" type="text"  AUTOCOMPLETE=OFF/>-->
                    <div class="radio">
                        <label>
                            <input type="radio" name="status"  value="1" checked> 启用
                        </label>
                        <label>
                            <input type="radio" name="status" value="0">  停用
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
                <button class="btn btn-success" type="submit" id="submit" style="margin-top: 30px;width: 150px;font-size: 20px"><span class="glyphicon glyphicon-saved"></span>  保存</button>
            </div>
        </div>
    </div>
    </form>
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
    
    function CheckForm() {
        return false;
    }
    
    //定义使用多边形或圆形通过Type区分
    var enclosureType;

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
            enclosureType = 0;
            map.clearOverlays();
            usePolygon();
        }
    })

    btn2.on('click',function () {
        if($('#suggestId').val()==''){
            alert("请先写入地区");
        }else {
            $circlebtn.removeClass('hide').addClass('show');
            $polygonbtn.removeClass('show').addClass('hide');
            map.clearOverlays();
            enclosureType = 1;
            useCircle();
        }
    })

    var msgtip=function (state)
    {
        var str="";
        switch (state)
        {
            case -2:
                str="请选择围栏状态"
                break;
            case -1:
                str = "请填写围栏名称"
                break;
            case 0:
                str = "请选择考勤组";
                break;
            case 1:
                str = "请填写围栏名称或者选择一个围栏框选类型";
                break;
            case 2:
                str = "围栏设置成功";
                break;
            case 3:
                str = "请选择使用围栏类型";
                break;
            case 4:
                str = "围栏名称已存在或为空，请更改";
                break;
        }
        alert(str);
    }

    /**
     * json判断并传输数据
     */
    $("#submit").on('click',function (event) {
        var enclosureName = $("#enclosureName").val();
        var attendanceName = $("#attendanceName").val();
        var enclosureDescriptions = $("#enclosureDescriptions").val();
        var status = $("input[name='status']:checked").val();
        var suggestId=$("#suggestId").val();
        if(enclosureName==""||attendanceName=="choose"||enclosureDescriptions==""||suggestId=="") {
            alert('请完善所有信息后提交');
        }else {
            if (enclosureType == 0) {
                getPoint();
                var json = {
                    "enclosureName": enclosureName,
                    "status": status,
                    "attendanceName": attendanceName,
                    "enclosureDescriptions": enclosureDescriptions,
                    "enclosureType": enclosureType,
                    "polygonLng": polygonLng,
                    "polygonLat": polygonLat
                };
            }
            else if (enclosureType == 1) {
                getCirleInfo();
                var json = {
                    "enclosureName": enclosureName,
                    "status": status,
                    "attendanceName": attendanceName,
                    "enclosureDescriptions": enclosureDescriptions,
                    "enclosureType": enclosureType,
                    "circleRadius": circleRadius,
                    "circleCenterLng": circleCenterLng,
                    "circleCenterLat": circleCenterLat
                };
            }
            console.log(json);
            $.ajax({
                type: "POST",
                url: "/community/enclosureAction_saveEnclosure.do",
                contentType: "application/json",
                dataType: "html json",
                data: JSON.stringify(json),
                success: function (data) {
                    //var jsondata = eval("("+data+")");
                    // console.log(jsondata);
                    //console.log(jsondata.state);
                    // $(data).each()
                    // console.log(data);
                    var jsondata = eval("(" + data + ")");
                    console.log(jsondata.state);
                    msgtip(parseInt(jsondata.state));
                    window.location.href = "/community/enclosureAction_getAllEnclosure.do";
                }
            })


        }

    })
    // 百度地图API功能
    function G(id) {
        return document.getElementById(id);
    }



    var map = new BMap.Map("l-map");
    map.centerAndZoom("北京",12);                   // 初始化地图,设置城市和地图级别。
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
        polygon = new BMap.Polygon([
                    new BMap.Point(pp.lng+0.0003,pp.lat),
                    new BMap.Point(pp.lng-0.0001,pp.lat+0.0003),
                    new BMap.Point(pp.lng-0.0001,pp.lat-0.0003)],
                {strokeColor:"blue", strokeWeight:2, strokeOpacity:0.5}
        );
        map.addOverlay(polygon);
    }

    /**
     * 使用圆形框选围栏
     */
    function useCircle(){
        //添加圆形
        circle = new BMap.Circle(new BMap.Point(pp.lng + 0.0001, pp.lat+0.0001), 10, {strokeColor:"blue", strokeWeight:2, strokeOpacity:0.5});
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
</script>
</body>
</html>