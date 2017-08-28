package com.newage.iep.serivce.impl.account.set;

import com.newage.iep.business.dao.GenericHibernateDAO;
import com.newage.iep.pojos.AnnounceSetting;
import com.newage.iep.serivce.set.AnnounceSettingService;
import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by a1996_000 on 2017/8/28.
 */
//公告设置业务层
@Service("announceSettingService")
public class AnnounceSettingServiceImpl extends GenericHibernateDAO implements AnnounceSettingService {
    @Override
    public List findAnnounceSettingCounts() {
        Query query = this.createQuery(" from AnnounceSetting ");
        List list = query.list();
        if(list!=null){
            return list;
        }
        return null;
    }
    //更新公告设置
    @Transactional
    @Override
    public void updateAnnounceSetting(AnnounceSetting announceSetting) {
        if(announceSetting!=null){
            this.update(announceSetting);
        }
    }
    //保存公告设置
    @Transactional
    @Override
    public void saveAnnounceSetting(AnnounceSetting announceSetting) {
        if(announceSetting!=null){
            this.save(announceSetting);
        }
    }
}
