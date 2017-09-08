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
}
