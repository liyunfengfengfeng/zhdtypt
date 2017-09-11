package com.newage.iep.pojos.set;

/**
 * Created by a1996_000 on 2017/8/28.
 */
//公告设置
public class AnnounceSetting {

    private String id; //varchar(255) NOT NULL,
    private String programupdatereminder; //varchar(255) DEFAULT NULL COMMENT '节目更新提醒',
    private Integer realtimeupload; //int(11) DEFAULT NULL COMMENT '实时上传',
    private String programupdatedevice; //varchar(255) DEFAULT NULL COMMENT '节目更新设备',

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProgramupdatereminder() {
        return programupdatereminder;
    }

    public void setProgramupdatereminder(String programupdatereminder) {
        this.programupdatereminder = programupdatereminder;
    }

    public Integer getRealtimeupload() {
        return realtimeupload;
    }

    public void setRealtimeupload(Integer realtimeupload) {
        this.realtimeupload = realtimeupload;
    }

    public String getProgramupdatedevice() {
        return programupdatedevice;
    }

    public void setProgramupdatedevice(String programupdatedevice) {
        this.programupdatedevice = programupdatedevice;
    }
}
