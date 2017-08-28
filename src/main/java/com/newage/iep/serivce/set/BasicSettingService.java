package com.newage.iep.serivce.set;

import com.newage.iep.pojos.BasicSetting;

import java.util.List;

/**
 * Created by a1996_000 on 2017/8/28.
 */
//基础设置业务层
public interface BasicSettingService {
    /**
     * 查找数据库中基本设置的数量
     * @return
     */
    List findBasicSettingCounts();
    //更新基础设置
    void updateBasicSetting(BasicSetting basicSetting);
    //保存基础设置
    void saveBasicSetting(BasicSetting basicSetting);
}
