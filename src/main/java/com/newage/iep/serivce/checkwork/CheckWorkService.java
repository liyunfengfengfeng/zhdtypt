package com.newage.iep.serivce.checkwork;

import com.newage.iep.pojos.AttendanceTeam;
import com.newage.iep.util.page.Page;

import java.util.List;

/**
 * Created by a1996_000 on 2017/9/6.
 */
public interface CheckWorkService {
    //查询所有的考勤组信息
    List selectAllCheckInfo();

    /**
     * 删除考勤组信息
     * @param checkWorkId
     */
    void delCheckWork(String checkWorkId);
    //分页查询出考勤组信息
    List<AttendanceTeam> selectcheckWorkPages(Page page);
    //保存考勤组信息
    void saveCheckWork(AttendanceTeam attendanceTeam);

    /**
     * 通过考勤组名称查询寻其id
     * @param checkworkname
     * @return
     */
    String selectAttendencrIdByName(String checkworkname);
    //通过id查询出考勤组信息
    AttendanceTeam selectCheckWorkById(String checkWorkId);

    /**
     * 更新考勤组
     * @param attendanceTeam
     */
    void updateAttendanceTeam(AttendanceTeam attendanceTeam);
}
