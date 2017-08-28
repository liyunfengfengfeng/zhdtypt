package com.newage.iep.serivce.set;

import com.newage.iep.pojos.EmergencySetting;

import java.util.List;

/**
 * Created by a1996_000 on 2017/8/28.
 */
//应急设置业务层
public interface EmergencySettingService {
    //查询数据库中应急事故记录
    List findEmergencySettingCounts();
    //更新应急设置
    void updateEmergencySetting(EmergencySetting emergencySetting);
    //保存应急设置
    void saveEmergencySetting(EmergencySetting emergencySetting);
}
