package com.newage.iep.serivce.impl.account.set;

import com.newage.iep.business.dao.GenericHibernateDAO;
import com.newage.iep.pojos.WarningSetting;
import com.newage.iep.serivce.set.WarningSettingService;
import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by a1996_000 on 2017/8/28.
 */
//预警业务层
    @Service("warningSettingService")
public class WarningSettingServiceImpl extends GenericHibernateDAO implements WarningSettingService{
    //查找预警设置
    @Override
    public List findWarningSettingCounts() {
        Query query = this.createQuery(" from WarningSetting ");
        List list = query.list();
        if(list!=null){
            return list;
        }
        return null;
    }
    //更新预警设置
    @Transactional
    @Override
    public void updateWarningSetting(WarningSetting warningSetting) {
        if(warningSetting!=null){
            this.update(warningSetting);
        }
    }
    //保存预警设置
    @Transactional
    @Override
    public void saveWarningSetting(WarningSetting warningSetting) {
        if(warningSetting!=null){
            this.save(warningSetting);
        }
    }
    //查询所有的预警设置
    @Override
    public List<WarningSetting> selectAllWarningSettings() {
        Query query = this.createQuery(" from WarningSetting ");
        List list = query.list();
        return list;
    }
}
