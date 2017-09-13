package com.newage.iep.pojos.Enclousure;


/**
 * Created by Administrator on 2017/8/24.
 */

public class Polygoncoordinate {
    private String id;
    private String polygonLng;
    private String polygonLat;
    private String enclosureId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPolygonLng() {
        return polygonLng;
    }

    public void setPolygonLng(String polygonLng) {
        this.polygonLng = polygonLng;
    }

    public String getPolygonLat() {
        return polygonLat;
    }

    public void setPolygonLat(String polygonLat) {
        this.polygonLat = polygonLat;
    }

    public String getEnclosureId() {
        return enclosureId;
    }

    public void setEnclosureId(String enclosureId) {
        this.enclosureId = enclosureId;
    }
}
