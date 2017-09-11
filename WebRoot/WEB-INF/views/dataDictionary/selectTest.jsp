<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>test</title>
</head>
<body>
<script src="/static/js/jquery-2.1.1.min.js"></script>

<script type="text/javascript">
    window.onload=function(){
        //所在区域联动列表
        setup();//加载的时候就执行里面的代码：如下的js代码有显示
    }

</script>

<div class="row">
    <span>选择地址:</span>
    <select style="WIDTH: 80px" id="province" name="provinceName" >
        <option value="unchoose">--请选择省份--</option>
        <s:iterator value="%{#request.provinceList}" id="TCityEntity">
            <option value="${TCityEntity.id}">${TCityEntity.name}</option>
        </s:iterator>
    </select>

    <select style="WIDTH: 80px" id="city" name="cityName" >
            <option value="unchoose">--请选择城市--</option>
    </select>

    <select style="WIDTH: 90px" id="area" name="districtName" >
            <option value="unchoose">--请选择区域--</option>
    </select>
</div>
</body>
<script type="text/javascript">
    /***
     * 省市区三级联动：
     * 三个下拉列表id分别是：province、city、area
     * @returns
     */
    function setup() {
        //获取城市
        $("#province").click(function () {//当点击省份的下拉选的时候，选中之后，城市进行联动

            var options=$("#province option:selected"); //获取选中的项
            $.ajax({
                url: "DataDictionary_queryCityByProvinceId.do?provinceId="+options.val(),
                type: "GET",
                dataType:'json',

                    success:function(data){
                    $("#city").empty(); //清空下拉列表·
                var jData = JSON.parse(data);
                    var strOptions = "";
                    console.log(jData);
                    for(var i=0;i<jData.length;i++){
                        strOptions = strOptions + '<OPTION value="' + jData[i].id + '">' + jData[i].name + '</OPTION>';
                    }
                    document.getElementById("city").innerHTML = strOptions;
                }
            });

        });
        //获取区县
        $("#city").click(function () {//当选择城市的下拉选的时候，区域进行联动
            var options=$("#city option:selected"); //获取选中的项
            $.ajax({
                url: "DataDictionary_queryAreaByCityId.do?cityId="+options.val(),
                type: "GET",
                dataType:'json',

                success:function(data){
                    $("#area").empty(); //清空下拉列表
                    var jData = JSON.parse(data);
                    var strOptions = "";
                    console.log(jData);
                    for(var i=0;i<jData.length;i++){
                        strOptions = strOptions + '<OPTION value="' + jData[i].id + '">' + jData[i].name + '</OPTION>';
                    }
                    document.getElementById("area").innerHTML = strOptions;
                }
            });
        });

    }
</script>
</html>
