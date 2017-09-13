package com.newage.iep.pojos.Community;


/**
 * Created by Administrator on 2017/9/2.
 */

public class Community {
    private String communityId;
    private String communityName;
    private String communityAddress;
    private String belongDistrict;
    private String belongCity;
    private Byte coordinateType;

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getCommunityAddress() {
        return communityAddress;
    }

    public void setCommunityAddress(String communityAddress) {
        this.communityAddress = communityAddress;
    }

    public String getBelongDistrict() {
        return belongDistrict;
    }

    public void setBelongDistrict(String belongDistrict) {
        this.belongDistrict = belongDistrict;
    }

    public String getBelongCity() {
        return belongCity;
    }

    public void setBelongCity(String belongCity) {
        this.belongCity = belongCity;
    }

    public Byte getCoordinateType() {
        return coordinateType;
    }

    public void setCoordinateType(Byte coordinateType) {
        this.coordinateType = coordinateType;
    }
}
