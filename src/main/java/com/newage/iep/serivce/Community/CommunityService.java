package com.newage.iep.serivce.Community;


import com.newage.iep.pojos.Community.Community;
import com.newage.iep.pojos.Community.Communitycirclecoordinate;
import com.newage.iep.pojos.Community.Communitypolygoncoordinate;
import com.newage.iep.pojos.city.City;
import com.newage.iep.util.page.Page;

import java.util.List;

/**
 * Created by Administrator on 2017/9/2.
 */
public interface CommunityService
{
    /**
     * 获取所有小区
     * @return
     */
    List<Community> getAllCommunity();

    /**
     * 保存小区
     * @param communityName    小区名称
     * @param communityAddress 小区详细地址
     * @param belongDistrict   小区所属区域
     * @param belongCity       小区所属城市
     * @param coordinateType   小区所属类型
     * @return
     */
    boolean saveCommunity(String communityName, String communityAddress, String belongDistrict, String belongCity, Byte coordinateType);

    /**
     * 使用多边形围栏保存小区
     * @param communityName       小区名称
     * @param communityPolygonLng 小区多边形经度
     * @param communityPolygonLat 小区多边形纬度
     * @return
     */
    boolean saveCommunityPolygonCoordinate(String communityName, String communityPolygonLng, String communityPolygonLat);

    /**
     * 使用圆形围栏保存小区
     * @param communityName         小区名称
     * @param communityCircleLng    圆形围栏经度
     * @param communityCircleLat    圆形围栏纬度
     * @param communityCircleRadius 圆形围栏半径
     * @return
     */
    boolean saveCommunityCircleCoordinate(String communityName, String communityCircleLng, String communityCircleLat, String communityCircleRadius);

    /**
     * 删除小区
     * @param id             小区ID
     * @param coordinateType 小区围栏类型
     * @return
     */
    boolean deleteCommunity(String id, Byte coordinateType);

    /**
     * 获取圆形围栏
     * @param id 小区id
     * @return
     */
    List<Communitycirclecoordinate> getCircle(String id);

    /**
     * 获取多边形围栏
     * @param id 小区id
     * @return
     */
    List<Communitypolygoncoordinate> getPolygon(String id);

    /**
     * 根据名称获取小区Id
     * @param communityName 小区名称
     * @return
     */
    Community getCommunityIdByName(String communityName);

    List<City> getAllProvince();

    List<City> getCity(String id);

    List<City> getDistrict(String id);

    String getCityNameById(String id);

    List<City> getCityListById(String id);

    List<Community> selectcheckWorkPages(Page page);
}
