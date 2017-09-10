package com.newage.iep.serivce.checkwork;

import com.newage.iep.pojos.AttendenceOrg;

import java.util.List;

/**
 * Created by a1996_000 on 2017/9/9.
 */
public interface AttendenceOrgService {
    /**
     * 保存考勤组与组织的关系
     * @param attendenceOrg
     */
    void saveAttendenceOrg(AttendenceOrg attendenceOrg);

    /**
     * 通过考勤id查询所有的考勤组与组织
     * @param checkWorkId
     * @return
     */
    List<AttendenceOrg> selectAllOrgsBelongAttendence(String checkWorkId);
}
