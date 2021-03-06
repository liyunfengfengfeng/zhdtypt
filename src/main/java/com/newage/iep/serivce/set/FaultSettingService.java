package com.newage.iep.serivce.set;

import com.newage.iep.pojos.set.FaultSetting;

import java.util.List;

/**
 * Created by a1996_000 on 2017/8/28.
 */
//故障记录
public interface FaultSettingService {
    //查询故障记录的数量
    List findFaultSettingCounts();
    //更新故障信息
    void updateFaultSetting(FaultSetting faultSetting);
    //保存故障信息
    void saveFaultSetting(FaultSetting faultSetting);
    //查询所有的故障记录
    List<FaultSetting> selectAllFaultSettings();
}
