package com.newage.iep.action.systemparametermanagement;

import com.newage.iep.pojos.*;
import com.newage.iep.serivce.set.*;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by a1996_000 on 2017/8/25.
 */
//设置
public class SetAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
    HttpServletRequest request;
    HttpServletResponse response;
    //基础设置
    @Autowired
    @Qualifier("BasicSettingService")
    BasicSettingService basicSettingService;
    //故障设置
    @Autowired
    @Qualifier("faultSettingService")
    FaultSettingService faultSettingService;
    //应急设置
    @Autowired
    @Qualifier("emergencySettingService")
    EmergencySettingService emergencySettingService;
    //预警设置
    @Autowired
    @Qualifier("warningSettingService")
    WarningSettingService warningSettingService;
    //公告设置
    @Autowired
    @Qualifier("announceSettingService")
    AnnounceSettingService announceSettingService;
    //基础设置
    private String logintimeout;// '登陆超时',
    private String linkpath;//'链接路径',
    private String companyname;//'公司名称',
    private String contactinfo;//'联系方式',
    private String welcomespeech;//'欢迎语',
    //故障设置
    private String errorwarningmethod;// '故障预警方式',
    private String warningmethod;//  '预警方式',
    private String noticepersonnel;//  '通知人员',
    //应急设置
    private String emergencypersonnelarrivaltime; //'应急人员到场时间',
    private String smstomaintenancer; //'报警后是否发送短信至维保人员',
    private String smstouser; //'报警后是否发送短信至使用人员',
    //预警设置
    private String earlywarning;// varchar(255) DEFAULT NULL COMMENT '维保提前预警',
    private String sendnotice;// int(11) DEFAULT NULL COMMENT '是否发送通知',
    private String annualadvancewarning;// varchar(255) DEFAULT NULL COMMENT '年间提前预警',
    //公告设置
    private String programupdatereminder; //varchar(255) DEFAULT NULL COMMENT '节目更新提醒',
    private String realtimeupload; //int(11) DEFAULT NULL COMMENT '实时上传',
    private String programupdatedevice; //varchar(255) DEFAULT NULL COMMENT '节目更新设备',
    /**
     * 到达设置页面
     * @return
     */
    public String toSet(){
        //查询基础设置信息  BasicSetting
        List<BasicSetting> basicSettings = basicSettingService.selectAllBasicSettings();
        //查询故障设置信息  FaultSetting
        List<FaultSetting> faultSettings = faultSettingService.selectAllFaultSettings();
        //查询应急设置信息  EmergencySetting
        List<EmergencySetting> emergencySettings = emergencySettingService.selectAllEmergencySettings();
        //查询预警设置信息  WarningSetting
        List<WarningSetting> warningSettings = warningSettingService.selectAllWarningSettings();
        //查询公告设置信息  AnnounceSetting
        List<AnnounceSetting> announceSettings = announceSettingService.selectAllAnnounceSettings();
        request.setAttribute("basicSettings",basicSettings);
        request.setAttribute("faultSettings",faultSettings);
        request.setAttribute("emergencySettings",emergencySettings);
        request.setAttribute("warningSettings",warningSettings);
        request.setAttribute("announceSettings",announceSettings);
        return "toSet";
    }

    /**
     * 保存或更新基础设置
     * @param
     */
    public String saveOrUpdateBasicSetting(){

        //查询数据库中是否有记录
        List list = basicSettingService.findBasicSettingCounts();
        if(list!=null&&list.size()==1){//有记录
            BasicSetting basicSetting = (BasicSetting)list.get(0);
            basicSetting.setCompanyname(companyname);
            basicSetting.setContactinfo(contactinfo);
            basicSetting.setLinkpath(linkpath);
            basicSetting.setWelcomespeech(welcomespeech);
            basicSetting.setLogintimeout(logintimeout);
            basicSettingService.updateBasicSetting(basicSetting);
            return "saveOrUpdateSetting";
        }else{
            //保存用户填写的信息
            BasicSetting basicSetting = new BasicSetting();
            basicSetting.setCompanyname(companyname);
            basicSetting.setContactinfo(contactinfo);
            basicSetting.setLinkpath(linkpath);
            basicSetting.setWelcomespeech(welcomespeech);
            basicSetting.setLogintimeout(logintimeout);
            basicSettingService.saveBasicSetting(basicSetting);
            return "saveOrUpdateSetting";
        }

    }

    /**
     * 保存或更新故障设置
     * @return
     */
    public String saveOrUpdateFaultSetting(){
        //查询数据库中是否有记录
        List list = faultSettingService.findFaultSettingCounts();
        if(list!=null&&list.size()==1){//有记录
            FaultSetting faultSetting = (FaultSetting)list.get(0);
            faultSetting.setErrorwarningmethod(errorwarningmethod);
            faultSetting.setWarningmethod(warningmethod);
            faultSetting.setNoticepersonnel(noticepersonnel);
            faultSettingService.updateFaultSetting(faultSetting);
            return "saveOrUpdateSetting";
        }else{
            //保存用户填写的信息
            FaultSetting faultSetting = new FaultSetting();
            faultSetting.setErrorwarningmethod(errorwarningmethod);
            faultSetting.setWarningmethod(warningmethod);
            faultSetting.setNoticepersonnel(noticepersonnel);
            faultSettingService.saveFaultSetting(faultSetting);
            return "saveOrUpdateSetting";
        }

    }

    /**
     * 保存或更新应急设置
     * @param
     */
    public String saveOrUpdateEmergencySetting(){
        //查询数据库中是否有记录
        List list = emergencySettingService.findEmergencySettingCounts();
        if(list!=null&&list.size()==1){//有记录
            EmergencySetting emergencySetting = (EmergencySetting)list.get(0);
            emergencySetting.setEmergencypersonnelarrivaltime(emergencypersonnelarrivaltime);//应急人员到场时间
            if(smstomaintenancer!=null){//是否发送短信给维保人员
                emergencySetting.setSmstomaintenancer(1);
            }else{
                emergencySetting.setSmstomaintenancer(0);
            }
            if(smstouser!=null){//是否发送短信给使用人员
                emergencySetting.setSmstouser(1);
            }else{
                emergencySetting.setSmstouser(0);
            }
            emergencySettingService.updateEmergencySetting(emergencySetting);
            return "saveOrUpdateSetting";
        }else{
            //保存用户填写的信息
            EmergencySetting emergencySetting = new EmergencySetting();
            emergencySetting.setEmergencypersonnelarrivaltime(emergencypersonnelarrivaltime);//应急人员到场时间
            if(smstomaintenancer!=null){//是否发送短信给维保人员
                emergencySetting.setSmstomaintenancer(1);
            }else{
                emergencySetting.setSmstomaintenancer(0);
            }
            if(smstouser!=null){//是否发送短信给使用人员
                emergencySetting.setSmstouser(1);
            }else{
                emergencySetting.setSmstouser(0);
            }
            emergencySettingService.saveEmergencySetting(emergencySetting);
            return "saveOrUpdateSetting";
        }
    }

    /**
     *保存或更新预警设置
     * @param
     */
    public String saveOrUpdateWarningSetting(){

        //查询数据库中是否有记录
        List list = warningSettingService.findWarningSettingCounts();
        if(list!=null&&list.size()==1){//有记录
            WarningSetting warningSetting = (WarningSetting)list.get(0);
            warningSetting.setAnnualadvancewarning(annualadvancewarning);//年间提前预警
            warningSetting.setEarlywarning(earlywarning);//维保提前预警
            if(sendnotice!=null){//预警是否发送通知
                warningSetting.setSendnotice(1);
            }else{
                warningSetting.setSendnotice(0);
            }
            warningSettingService.updateWarningSetting(warningSetting);
            return "saveOrUpdateSetting";
        }else{
            //保存用户填写的信息
            WarningSetting warningSetting = new WarningSetting();
            warningSetting.setAnnualadvancewarning(annualadvancewarning);//年间提前预警
            warningSetting.setEarlywarning(earlywarning);//维保提前预警
            if(sendnotice!=null){//预警是否发送通知
                warningSetting.setSendnotice(1);
            }else{
                warningSetting.setSendnotice(0);
            }
            warningSettingService.saveWarningSetting(warningSetting);
            return "saveOrUpdateSetting";
        }
    }

    /**
     * 保存或更新公告设置
     * @param
     */
    public String saveOrUpdateAnnounceSetting(){
        //查询数据库中是否有记录
        List list = announceSettingService.findAnnounceSettingCounts();
        if(list!=null&&list.size()==1){//有记录
            AnnounceSetting announceSetting = (AnnounceSetting)list.get(0);
            announceSetting.setProgramupdatereminder(programupdatereminder);
            announceSetting.setProgramupdatedevice(programupdatedevice);
            if(realtimeupload!=null){//是否实时上传
                announceSetting.setRealtimeupload(1);
            }else{
                announceSetting.setRealtimeupload(0);
            }

            announceSettingService.updateAnnounceSetting(announceSetting);
            return "saveOrUpdateSetting";
        }else{
            //保存用户填写的信息
            AnnounceSetting announceSetting = new AnnounceSetting();
            announceSetting.setProgramupdatereminder(programupdatereminder);
            announceSetting.setProgramupdatedevice(programupdatedevice);
            if(realtimeupload!=null){//是否实时上传
                announceSetting.setRealtimeupload(1);
            }else{
                announceSetting.setRealtimeupload(0);
            }
            announceSettingService.saveAnnounceSetting(announceSetting);
            return "saveOrUpdateSetting";
        }
    }
    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }
    //--------------------------------getter/setter---------------------------------------------------------------

    public String getLogintimeout() {
        return logintimeout;
    }

    public void setLogintimeout(String logintimeout) {
        this.logintimeout = logintimeout;
    }

    public String getLinkpath() {
        return linkpath;
    }

    public void setLinkpath(String linkpath) {
        this.linkpath = linkpath;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getContactinfo() {
        return contactinfo;
    }

    public void setContactinfo(String contactinfo) {
        this.contactinfo = contactinfo;
    }

    public String getWelcomespeech() {
        return welcomespeech;
    }

    public void setWelcomespeech(String welcomespeech) {
        this.welcomespeech = welcomespeech;
    }

    public String getErrorwarningmethod() {
        return errorwarningmethod;
    }

    public void setErrorwarningmethod(String errorwarningmethod) {
        this.errorwarningmethod = errorwarningmethod;
    }

    public String getWarningmethod() {
        return warningmethod;
    }

    public void setWarningmethod(String warningmethod) {
        this.warningmethod = warningmethod;
    }

    public String getNoticepersonnel() {
        return noticepersonnel;
    }

    public void setNoticepersonnel(String noticepersonnel) {
        this.noticepersonnel = noticepersonnel;
    }

    public String getEmergencypersonnelarrivaltime() {
        return emergencypersonnelarrivaltime;
    }

    public void setEmergencypersonnelarrivaltime(String emergencypersonnelarrivaltime) {
        this.emergencypersonnelarrivaltime = emergencypersonnelarrivaltime;
    }

    public String getSmstomaintenancer() {
        return smstomaintenancer;
    }

    public void setSmstomaintenancer(String smstomaintenancer) {
        this.smstomaintenancer = smstomaintenancer;
    }

    public String getSmstouser() {
        return smstouser;
    }

    public void setSmstouser(String smstouser) {
        this.smstouser = smstouser;
    }

    public String getEarlywarning() {
        return earlywarning;
    }

    public void setEarlywarning(String earlywarning) {
        this.earlywarning = earlywarning;
    }

    public String getSendnotice() {
        return sendnotice;
    }

    public void setSendnotice(String sendnotice) {
        this.sendnotice = sendnotice;
    }

    public String getAnnualadvancewarning() {
        return annualadvancewarning;
    }

    public void setAnnualadvancewarning(String annualadvancewarning) {
        this.annualadvancewarning = annualadvancewarning;
    }

    public String getProgramupdatereminder() {
        return programupdatereminder;
    }

    public void setProgramupdatereminder(String programupdatereminder) {
        this.programupdatereminder = programupdatereminder;
    }

    public String getRealtimeupload() {
        return realtimeupload;
    }

    public void setRealtimeupload(String realtimeupload) {
        this.realtimeupload = realtimeupload;
    }

    public String getProgramupdatedevice() {
        return programupdatedevice;
    }

    public void setProgramupdatedevice(String programupdatedevice) {
        this.programupdatedevice = programupdatedevice;
    }
}
