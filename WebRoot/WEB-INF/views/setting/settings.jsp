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
    <link rel="stylesheet" href="/static/css/bootstrap/bootstrap.min.css">
    <style>
        /* Default tab style */

        .tabs {
            position: relative;
            overflow: hidden;
            margin: 100px auto;
            width: 100%;
            font-weight: 300;
            font-size: 1.25em;
            background: rgba(255,255,255,0.5);
        }

        /* Nav */
        .tabs nav {
            text-align: center;
        }

        .tabs nav ul {
            position: relative;
            display: -ms-flexbox;
            display: -webkit-flex;
            display: -moz-flex;
            display: -ms-flex;
            display: flex;
            margin: 0 auto;
            padding: 0;
            max-width: 1200px;
            list-style: none;
            -ms-box-orient: horizontal;
            -ms-box-pack: center;
            -webkit-flex-flow: row wrap;
            -moz-flex-flow: row wrap;
            -ms-flex-flow: row wrap;
            flex-flow: row wrap;
            -webkit-justify-content: center;
            -moz-justify-content: center;
            -ms-justify-content: center;
            justify-content: center;
        }

        .tabs nav ul li {
            position: relative;
            z-index: 1;
            display: block;
            margin: 0;
            text-align: center;
            -webkit-flex: 1;
            -moz-flex: 1;
            -ms-flex: 1;
            flex: 1;
        }

        .tabs nav a {
            position: relative;
            display: block;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
            line-height: 2.5;
        }

        .tabs nav a span {
            vertical-align: middle;
            font-size: 0.75em;
        }

        .tabs nav li.tab-current a {
            color: #74777b;
        }

        .tabs nav a:focus {
            outline: none;
        }


        /* Content */
        .content-wrap {
            position: relative;
        }

        .content-wrap section {
            display: none;
            margin: 0 auto;
            padding: 1em;
            max-width: 1200px;
            text-align: center;
        }

        .content-wrap section.content-current {
            display: block;
        }

        /*.content-wrap section p {*/
            /*margin: 0;*/
            /*padding: 0.75em 0;*/
            /*color: rgba(40,44,42,0.05);*/
            /*font-weight: 900;*/
            /*font-size: 4em;*/
            /*line-height: 1;*/
        /*}*/

        /* Fallback */
        .no-js .content-wrap section {
            display: block;
            padding-bottom: 2em;
            border-bottom: 1px solid rgba(255,255,255,0.5);
        }

        .no-flexbox nav ul {
            display: block;
        }

        .no-flexbox nav ul li {
            min-width: 15%;
            display: inline-block;
        }
        /* Flip */
        /*****************************/

        .tabs-style-flip {
            max-width: 1200px;
        }

        .tabs-style-flip nav a {
            padding: 0.5em 0;
            color: #ffffff;
            font-size: 25px;
            -webkit-transition: color 0.3s;
            transition: color 0.3s;
        }

        .tabs-style-flip nav a:hover,
        .tabs-style-flip nav a:focus,
        .tabs-style-flip nav li.tab-current a {
            color: #74777b;
        }

        .tabs-style-flip nav a span {
            text-transform: uppercase;
            letter-spacing: 1px;
            font-weight: 700;
            font-size: 0.625em;
        }

        .tabs-style-flip nav a::after {
            position: absolute;
            top: 0;
            left: 0;
            z-index: -1;
            width: 100%;
            height: 100%;
            background-color: #f0f0f0;
            content: '';
            -webkit-transition: -webkit-transform 0.3s, background-color 0.3s;
            transition: transform 0.3s, background-color 0.3s;
            -webkit-transform: perspective(900px) rotate3d(1,0,0,90deg);
            transform: perspective(900px) rotate3d(1,0,0,90deg);
            -webkit-transform-origin: 50% 100%;
            transform-origin: 50% 100%;
            -webkit-perspective-origin: 50% 100%;
            perspective-origin: 50% 100%;
        }

        .tabs-style-flip nav li.tab-current a::after {
            background-color: rgba(255,255,255,0.7);
            -webkit-transform: perspective(900px) rotate3d(1,0,0,0deg);
            transform: perspective(900px) rotate3d(1,0,0,0deg);
        }

        .tabs-style-flip .content-wrap {
            background: rgba(255,255,255,0.7);
        }

    </style>
</head>
<body>
<canvas id="canvas" style="position:fixed;z-index:-1;height: 100%;width: 100%;"></canvas>
  <div class="container">
      <div class="row">
          <div class="col-md-12">
              <div class="tabs tabs-style-flip">
                  <nav>
                      <ul>
                          <li><a href="#section-flip-5" ><span>基础设置</span></a></li>
                          <li><a href="#section-flip-4" ><span>故障设置</span></a></li>
                          <li><a href="#section-flip-2" ><span>应急设置</span></a></li>
                          <li><a href="#section-flip-3" ><span>预警设置</span></a></li>
                          <li><a href="#section-flip-5" ><span>公告设置</span></a></li>
                      </ul>
                  </nav>
                  <div class="content-wrap">
                      <section id="section-flip-1">
                          <!--基础设置-->
                          <form class="form-horizontal" role="form" action="SetAction_saveOrUpdateBasicSetting.do" method="post">
                              <s:if test="%{#request.basicSettings != null}">
                              <s:iterator value="%{#request.basicSettings}" var="basicSetting">
                              <div class="form-group">
                                  <label for="overtime" class="col-sm-2 control-label">登陆超时</label>
                                  <div class="col-sm-10">
                                      <input type="text" class="form-control" id="overtime" name="logintimeout" value="<s:property value="%{#basicSetting.logintimeout}"/>">
                                  </div>
                              </div>
                              <div class="form-group">
                                  <label for="link" class="col-sm-2 control-label">链接路径</label>
                                  <div class="col-sm-10">
                                      <input type="text" class="form-control" id="link" name="linkpath" value="<s:property value="%{#basicSetting.linkpath}"/>">
                                  </div>
                              </div>
                              <div class="form-group">
                                  <label for="compname" class="col-sm-2 control-label">公司名称</label>
                                  <div class="col-sm-10">
                                      <input type="text" class="form-control" id="compname" name="companyname" value="<s:property value="%{#basicSetting.companyname}"/>">
                                  </div>
                              </div>
                              <div class="form-group">
                              <label for="contactway" class="col-sm-2 control-label">联系方式</label>
                              <div class="col-sm-10">
                                  <input type="text" class="form-control" id="contactway" name="contactinfo" value="<s:property value="%{#basicSetting.contactinfo}"/>">
                              </div>
                             </div>
                              <div class="form-group">
                                  <label for="welcomewords" class="col-sm-2 control-label">欢迎语</label>
                                  <div class="col-sm-10">
                                      <textarea  class="form-control" id="welcomewords" name="welcomespeech"><s:property value="%{#basicSetting.welcomespeech}"/></textarea>
                                  </div>
                              </div>
                                  </s:iterator>
                                  </s:if>
                              <div class="form-group">
                                  <div class="col-sm-12">
                                      <button type="submit" class="btn btn-success center-block" style="margin-top: 10px;width: 130px;font-size: 18px">保存 <span class="glyphicon glyphicon-circle-arrow-right"> </span></button>
                                  </div>
                              </div>
                          </form>
                      </section>
                      <section id="section-flip-2">





                          <!--故障设置-->
                          <form class="form-horizontal" role="form" action="SetAction_saveOrUpdateFaultSetting.do" method="post">
                              <s:if test="%{#request.faultSettings != null}">
                              <s:iterator value="%{#request.faultSettings}" var="faultSetting">
                          <div class="form-group">
                              <label for="troublewarning" class="col-sm-2 control-label">故障预警方式</label>
                              <div class="col-sm-10">
                                  <input type="text" class="form-control" id="troublewarning" name="errorwarningmethod" value="<s:property value="%{#faultSetting.errorwarningmethod}"/>">
                              </div>
                          </div>
                          <div class="form-group">
                              <label for="warning" class="col-sm-2 control-label">预警方式</label>
                              <div class="col-sm-10">
                                  <input type="text" class="form-control" id="warning" name="warningmethod" value="<s:property value="%{#faultSetting.warningmethod}"/>">
                              </div>
                          </div>
                          <div class="form-group">
                              <label for="informman" class="col-sm-2 control-label">通知人员</label>
                              <div class="col-sm-10">
                                  <input type="text" class="form-control" id="informman" name="noticepersonnel" value="<s:property value="%{#faultSetting.noticepersonnel}"/>">
                              </div>
                          </div>
                                  </s:iterator>
                                  </s:if>
                          <div class="form-group">
                              <div class="col-sm-12">
                                  <button type="submit" class="btn btn-success center-block" style="margin-top: 10px;width: 130px;font-size: 18px">保存 <span class="glyphicon glyphicon-circle-arrow-right"> </span></button>
                              </div>
                          </div>
                      </form>








                      </section>
                      <section id="section-flip-3">






                          <!--应急设置-->
                          <form class="form-horizontal" role="form" action="SetAction_saveOrUpdateEmergencySetting.do" method="post">
                              <s:if test="%{#request.emergencySettings != null}">
                                  <c:forEach items="${requestScope.emergencySettings }" var="emergencySetting">
                          <div class="form-group">
                              <label for="arrivetime" class="col-sm-4 control-label">应急人员到场时间</label>
                              <div class="col-sm-8">
                                  <input type="text" class="form-control" id="arrivetime" name="emergencypersonnelarrivaltime" value="${emergencySetting.emergencypersonnelarrivaltime}">
                              </div>
                          </div>
                          <div class="form-group">
                              <label for="sendtoW" class="col-sm-4 control-label left">报警后是否发送短信至维保人员</label>
                              <div class="col-sm-8">
                                  <c:if test="${emergencySetting.smstomaintenancer=='1'}">
                                      <c:set scope="page" value="checked" var="smstomaintenancer"/>
                                  </c:if>
                                  <input type="checkbox" class="form-control" id="sendtoW" name="smstomaintenancer" ${smstomaintenancer } >
                              </div>
                          </div>
                          <div class="form-group">
                              <label for="sendtoS" class="col-sm-4 control-label">报警后是否发送短信至使用人员</label>
                              <div class="col-sm-8">
                                  <c:if test="${emergencySetting.smstouser=='1'}">
                                         <c:set scope="page" value="checked" var="smstouser"/>
                                  </c:if>
                                  <input type="checkbox" class="form-control" id="sendtoS" name="smstouser" ${smstouser }>
                              </div>

                          </div>
                                  </c:forEach>
                              </s:if>
                          <div class="form-group">
                              <div class="col-sm-12">
                                  <button type="submit" class="btn btn-success center-block" style="margin-top: 10px;width: 130px;font-size: 18px">保存 <span class="glyphicon glyphicon-circle-arrow-right"> </span></button>
                              </div>
                          </div>
                      </form>








                      </section>
                      <section id="section-flip-4">






                          <!--预警设置-->
                          <form class="form-horizontal" role="form" action="SetAction_saveOrUpdateWarningSetting.do" method="post">
                              <s:if test="%{#request.warningSettings != null}">
                              <s:iterator value="%{#request.warningSettings}" var="warningSetting">
                              <div class="form-group">
                                  <label for="winadvance" class="col-sm-3 control-label">维保提前预警</label>
                                  <div class="col-sm-9">
                                      <input type="text" class="form-control" id="winadvance" name="earlywarning" value="<s:property value="%{#warningSetting.earlywarning}"/>">
                                  </div>
                              </div>
                              <div class="form-group">
                                  <label for="ifsend" class="col-sm-3 control-label left">预警是否发送通知</label>
                                  <div class="col-sm-9">
                                      <c:if test="${warningSetting.sendnotice=='1'}">
                                          <c:set scope="page" value="checked" var="sendnotice"/>
                                      </c:if>
                                      <input type="checkbox" class="form-control" id="ifsend" name="sendnotice" ${sendnotice }>
                                  </div>
                              </div>
                              <div class="form-group">
                                  <label for="yinadvance" class="col-sm-3 control-label">年检提前预警</label>
                                  <div class="col-sm-9">
                                      <input type="text" class="form-control" id="yinadvance" name="annualadvancewarning" value="<s:property value="%{#warningSetting.annualadvancewarning}"/>">
                                  </div>
                              </div>
                                  </s:iterator>
                                  </s:if>
                              <div class="form-group">
                                  <div class="col-sm-12">
                                      <button type="submit" class="btn btn-success center-block" style="margin-top: 10px;width: 130px;font-size: 18px">保存 <span class="glyphicon glyphicon-circle-arrow-right"> </span></button>
                                  </div>
                              </div>
                          </form>







                      </section>
                      <section id="section-flip-5">





                          <!--公告设置-->
                          <form class="form-horizontal" role="form" action="SetAction_saveOrUpdateAnnounceSetting.do" method="post">
                              <s:if test="%{#request.announceSettings != null}">
                              <s:iterator value="%{#request.announceSettings}" var="announceSetting">
                              <div class="form-group">
                                  <label for="updatawarn" class="col-sm-3 control-label">节目更新提醒</label>
                                  <div class="col-sm-9">
                                      <input type="text" class="form-control" id="updatawarn" name="programupdatereminder" value="<s:property value="%{#announceSetting.programupdatereminder}"/>">
                                  </div>
                              </div>
                              <div class="form-group">
                                  <label for="ifupload" class="col-sm-3 control-label left">是否实时上传</label>
                                  <div class="col-sm-9">
                                      <c:if test="${announceSetting.realtimeupload=='1'}">
                                          <c:set scope="page" value="checked" var="realtimeupload"/>
                                      </c:if>
                                      <input type="checkbox" class="form-control" id="ifupload" name="realtimeupload" ${realtimeupload}>
                                  </div>
                              </div>
                              <div class="form-group">
                                  <label for="updatefacility" class="col-sm-3 control-label">节目同时更新(设备)</label>
                                  <div class="col-sm-9">
                                      <input type="text" class="form-control" id="updatefacility" name="programupdatedevice" value="<s:property value="%{#announceSetting.programupdatedevice}"/>">
                                  </div>
                              </div>
                                  </s:iterator>
                                  </s:if>
                              <div class="form-group">
                                  <div class="col-sm-12">
                                      <button type="submit" class="btn btn-success center-block" style="margin-top: 10px;width: 130px;font-size: 18px">保存 <span class="glyphicon glyphicon-circle-arrow-right"> </span></button>
                                  </div>
                              </div>
                          </form>








                      </section>
                  </div><!-- /content -->
              </div>
          </div>
      </div>
  </div>
</body>
<script src="/static/js/jquery-2.1.1.min.js"></script>
<script src="/static/js/bootstrap/bootstrap.min.js"></script>
<script src="/static/js/milkybg.js"></script>
<script src="/static/js/Tabs.js"></script>
<script>
    (function() {

        [].slice.call( document.querySelectorAll( '.tabs' ) ).forEach( function( el ) {
            new CBPFWTabs( el );
        });

    })();
</script>
</html>