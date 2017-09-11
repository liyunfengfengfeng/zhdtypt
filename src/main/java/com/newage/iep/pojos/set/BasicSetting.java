package com.newage.iep.pojos.set;

/**
 * Created by a1996_000 on 2017/8/28.
 */
//基础设置
public class BasicSetting {

    private String id;
    private String logintimeout;// '登陆超时',
    private String linkpath;//'链接路径',
    private String companyname;//'公司名称',
    private String contactinfo;//'联系方式',
    private String welcomespeech;//'欢迎语',

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
}
