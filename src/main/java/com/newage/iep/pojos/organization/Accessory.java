package com.newage.iep.pojos.organization;



public class Accessory {
    private String accessoryId;//附件Id
    private String accessoryName;//附件名称
    private String accessoryPath;//附件存储路径
    private Organization cmp;

    public Organization getCmp() {
        return cmp;
    }

    public void setCmp(Organization cmp) {
        this.cmp = cmp;
    }


    public String getAccessoryId() {
        return accessoryId;
    }

    public void setAccessoryId(String accessoryId) {
        this.accessoryId = accessoryId;
    }


    public String getAccessoryName() {
        return accessoryName;
    }

    public void setAccessoryName(String accessoryName) {
        this.accessoryName = accessoryName;
    }


    public String getAccessoryPath() {
        return accessoryPath;
    }

    public void setAccessoryPath(String accessoryPath) {
        this.accessoryPath = accessoryPath;
    }


}
