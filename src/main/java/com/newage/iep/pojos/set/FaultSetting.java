package com.newage.iep.pojos.set;

/**
 * Created by a1996_000 on 2017/8/28.
 */

/**
 * 故障设置
 */
public class FaultSetting {

    private String id;//
    private String errorwarningmethod;// '故障预警方式',
    private String warningmethod;//  '预警方式',
    private String noticepersonnel;//  '通知人员',

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
