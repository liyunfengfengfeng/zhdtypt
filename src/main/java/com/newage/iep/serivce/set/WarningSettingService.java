package com.newage.iep.serivce.set;

import com.newage.iep.pojos.WarningSetting;

import java.util.List;

/**
 * Created by a1996_000 on 2017/8/28.
 */
//预警业务层
public interface WarningSettingService {
    //查找预警设置
    List findWarningSettingCounts();
    //更新预警设置
    void updateWarningSetting(WarningSetting warningSetting);
    //保存预警设置
    void saveWarningSetting(WarningSetting warningSetting);
}
