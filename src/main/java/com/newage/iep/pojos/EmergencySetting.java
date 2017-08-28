package com.newage.iep.pojos;

/**
 * Created by a1996_000 on 2017/8/28.
 */
//应急设置
public class EmergencySetting {

    private String id; //
    private String emergencypersonnelarrivaltime; //'应急人员到场时间',
    private Integer smstomaintenancer; //'报警后是否发送短信至维保人员',
    private Integer smstouser; //'报警后是否发送短信至使用人员',

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmergencypersonnelarrivaltime() {
        return emergencypersonnelarrivaltime;
    }

    public void setEmergencypersonnelarrivaltime(String emergencypersonnelarrivaltime) {
        this.emergencypersonnelarrivaltime = emergencypersonnelarrivaltime;
    }

    public int getSmstomaintenancer() {
        return smstomaintenancer;
    }

    public void setSmstomaintenancer(int smstomaintenancer) {
        this.smstomaintenancer = smstomaintenancer;
    }

    public int getSmstouser() {
        return smstouser;
    }

    public void setSmstouser(int smstouser) {
        this.smstouser = smstouser;
    }
}
