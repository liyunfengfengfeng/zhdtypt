package com.newage.iep.serivce.Community;


import com.newage.iep.pojos.Enclousure.Circlecoordinate;
import com.newage.iep.pojos.Enclousure.Enclosure;
import com.newage.iep.pojos.Enclousure.Polygoncoordinate;
import com.newage.iep.pojos.checkwork.AttendanceTeam;
import com.newage.iep.util.page.Page;

import java.util.List;

/**
 * Created by Administrator on 2017/8/23.
 */
public interface EnclosureService
{
    /**
     * 检查围栏名称是否存在
     * @param enclosureName 围栏名称
     * @return
     */
    boolean checkHasEnclosure(String enclosureName);

    /**
     * 保存围栏
     * @param enclosureName         围栏名称
     * @param coordinateType        围栏类型
     * @param attendanceName        考勤组名称
     * @param enclosureDescriptions 围栏描述
     * @return
     */
    boolean saveEnclosure(String enclosureName, int coordinateType, String attendanceName, String enclosureDescriptions, String status);

    /**
     * 保存圆形坐标
     * @param enclosureName   围栏名称
     * @param circleRaidus    圆形半径
     * @param circleCenterLng 圆形圆心坐标经度
     * @param circleCenterLat 圆形圆心坐标纬度
     * @return
     */
    boolean saveCirclecoordinate(String enclosureName, String circleRaidus, String circleCenterLng, String circleCenterLat);

    /**
     * 保存多边形坐标
     * @param enclosureName 围栏名称
     * @param polygonLng    多边形坐标经度
     * @param polygonLat    多边形坐标纬度
     */
    void savePolygoncoordinate(String enclosureName, String polygonLng, String polygonLat);

    /**
     * 获取EnclosureList
     * @return
     */
    List<Enclosure> getAllEnclosure();

    /**
     * 获取圆形坐标
     * @param enclosureId 围栏Id
     * @return
     */
    List<Circlecoordinate> getCirclecoordinate(String enclosureId);

    /**
     * 获取多边形坐标
     * @param enclosureId 围栏id
     * @return
     */
    List<Polygoncoordinate> getPolygoncoordinate(String enclosureId);

    /**
     * 根据Name获取围栏
     * @param enclosureName 围栏名称
     * @return
     */
    Enclosure getIdByName(String enclosureName);

    /**
     * 删除围栏
     * @param enclosureId   围栏id
     * @param enclosureType 围栏类型
     * @return
     */
    boolean deleteEnclosure(String enclosureId, int enclosureType);

    /**
     * 根据考勤组id获取考勤组
     * @param attendanceteamId 考勤组id
     * @return
     */
    AttendanceTeam getAttendanceteamById(String attendanceteamId);

    /**
     * 获取所有考勤组
     * @return
     */
    List<AttendanceTeam> getAllAttendance();

    List<Enclosure> selectcheckWorkPages(Page page);

    String getModifyNameById(String modifyId);
}
