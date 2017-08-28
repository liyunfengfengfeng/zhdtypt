package com.newage.iep.pojos;

/**
 * Created by a1996_000 on 2017/8/28.
 */
//预警设置
public class WarningSetting {
    private String id;// varchar(255) NOT NULL,
    private String earlywarning;// varchar(255) DEFAULT NULL COMMENT '维保提前预警',
    private Integer sendnotice;// int(11) DEFAULT NULL COMMENT '是否发送通知',
    private String annualadvancewarning;// varchar(255) DEFAULT NULL COMMENT '年间提前预警',

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEarlywarning() {
        return earlywarning;
    }

    public void setEarlywarning(String earlywarning) {
        this.earlywarning = earlywarning;
    }

    public Integer getSendnotice() {
        return sendnotice;
    }

    public void setSendnotice(Integer sendnotice) {
        this.sendnotice = sendnotice;
    }

    public String getAnnualadvancewarning() {
        return annualadvancewarning;
    }

    public void setAnnualadvancewarning(String annualadvancewarning) {
        this.annualadvancewarning = annualadvancewarning;
    }
}
