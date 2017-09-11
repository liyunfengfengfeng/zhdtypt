package com.newage.iep.pojos.checkwork;

import java.util.Date;

/**
 * Created by a1996_000 on 2017/9/6.
 */
//考勤组
public class AttendanceTeam {

    private String id; // '主键',
    private String rolename;// '角色名称',
    private String includeusers;// '包含用户',
    private Date createtime;//  '创建时间',
    private Date updatetime;//  '更新时间',
    private String decriptions;//  '描述',
    private String owndepartment;//  '所属部门',
    private Integer status;//  '状态',

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getIncludeusers() {
        return includeusers;
    }

    public void setIncludeusers(String includeusers) {
        this.includeusers = includeusers;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getDecriptions() {
        return decriptions;
    }

    public void setDecriptions(String decriptions) {
        this.decriptions = decriptions;
    }

    public String getOwndepartment() {
        return owndepartment;
    }

    public void setOwndepartment(String owndepartment) {
        this.owndepartment = owndepartment;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
