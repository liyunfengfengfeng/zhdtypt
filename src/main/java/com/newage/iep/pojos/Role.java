package com.newage.iep.pojos;

/**
 * Created by a1996_000 on 2017/8/20.
 */

import java.util.Date;

/**
 * 角色信息
 */
public class Role {
    private String Role_id;//角色id 主键
    private String Role_name;//角色名称
    private String Belong_by;//所属用户编码
    private String Belong_module;//功能模块编码
    private String Rmk;//备注
    private String Create_by;//创建人
    private Date Create_date;//创建日期
    private String Modify_by;//修改人
    private Date Modify_date;//修改日期
    private String Role_level;//角色等级

    public String getRole_id() {
        return Role_id;
    }

    public void setRole_id(String role_id) {
        Role_id = role_id;
    }

    public String getRole_name() {
        return Role_name;
    }

    public void setRole_name(String role_name) {
        Role_name = role_name;
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
        return Rmk;
    }

    public void setRmk(String rmk) {
        Rmk = rmk;
    }

    public String getCreate_by() {
        return Create_by;
    }

    public void setCreate_by(String create_by) {
        Create_by = create_by;
    }

    public Date getCreate_date() {
        return Create_date;
    }

    public void setCreate_date(Date create_date) {
        Create_date = create_date;
    }

    public String getModify_by() {
        return Modify_by;
    }

    public void setModify_by(String modify_by) {
        Modify_by = modify_by;
    }

    public Date getModify_date() {
        return Modify_date;
    }

    public void setModify_date(Date modify_date) {
        Modify_date = modify_date;
    }

    public String getRole_level() {
        return Role_level;
    }

    public void setRole_level(String role_level) {
        Role_level = role_level;
    }
}
