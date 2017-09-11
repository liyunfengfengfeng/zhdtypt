package com.newage.iep.pojos.checkwork;

/**
 * Created by a1996_000 on 2017/9/9.
 */
//考勤组与组织的关系
public class AttendenceOrg {

    private String id;// varchar(50) NOT NULL,
    private String orgid; //varchar(50) DEFAULT NULL COMMENT '组织id',
    private String attenddenceid;//varchar(50) DEFAULT NULL COMMENT '考勤组id',

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public String getAttenddenceid() {
        return attenddenceid;
    }

    public void setAttenddenceid(String attenddenceid) {
        this.attenddenceid = attenddenceid;
    }
}
