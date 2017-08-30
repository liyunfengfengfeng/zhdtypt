package com.newage.iep.serivce.set;

import com.newage.iep.pojos.AnnounceSetting;

import java.util.List;

/**
 * Created by a1996_000 on 2017/8/28.
 */
//公告设置业务层
public interface AnnounceSettingService {
    //查询公告信息
    List findAnnounceSettingCounts();
    //更新公告设置
    void updateAnnounceSetting(AnnounceSetting announceSetting);
    //保存公告设置
    void saveAnnounceSetting(AnnounceSetting announceSetting);
    //查询所有的公告设置
    List<AnnounceSetting> selectAllAnnounceSettings();
}
