package com.newage.iep.pojos.role;

/**
 * Created by a1996_000 on 2017/8/20.
 */

import java.util.Date;

/**
 * 角色信息
 */
public class Role {
    private String roleid;//角色id 主键
    private String rolename;//角色名称
    private String Belong_by;//所属用户编码
    private String Belong_module;//功能模块编码
    private String rmk;//备注
    private String createby;//创建人
    private Date createdate;//创建日期
    private String modifyby;//修改人
    private Date modifydate;//修改日期
    private String Role_level;//角色等级

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getBelong_by() {
        return Belong_by;
    }

    public void setBelong_by(String belong_by) {
        Belong_by = belong_by;
    }

    public String getBelong_module() {
        return Belong_module;
    }

    public void setBelong_module(String belong_module) {
        Belong_module = belong_module;
    }

    public String getRmk() {
        return rmk;
    }

    public void setRmk(String rmk) {
        this.rmk = rmk;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby;
    }



    public String getModifyby() {
        return modifyby;
    }

    public void setModifyby(String modifyby) {
        this.modifyby = modifyby;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getModifydate() {
        return modifydate;
    }

    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }

    public String getRole_level() {
        return Role_level;
    }

    public void setRole_level(String role_level) {
        Role_level = role_level;
    }
}
