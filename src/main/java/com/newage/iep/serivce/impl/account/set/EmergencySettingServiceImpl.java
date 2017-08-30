package com.newage.iep.serivce.impl.account.set;

import com.newage.iep.business.dao.GenericHibernateDAO;
import com.newage.iep.pojos.EmergencySetting;
import com.newage.iep.serivce.set.EmergencySettingService;
import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by a1996_000 on 2017/8/28.
 */
@Service("emergencySettingService")
public class EmergencySettingServiceImpl extends GenericHibernateDAO implements EmergencySettingService {
    //查询数据库中应急事故记录
    @Override
    public List findEmergencySettingCounts() {
        Query query = this.createQuery(" from EmergencySetting ");
        List list = query.list();
        if(list!=null){
            return list;
        }
        return null;
    }
    //更新应急设置
    @Transactional
    @Override
    public void updateEmergencySetting(EmergencySetting emergencySetting) {
        if(emergencySetting != null){
            this.update(emergencySetting);
        }
    }
    //保存应急设置
    @Transactional
    @Override
    public void saveEmergencySetting(EmergencySetting emergencySetting) {
        if(emergencySetting != null){
            this.save(emergencySetting);
        }
    }
    //查询所有的应急设置
    @Override
    public List<EmergencySetting> selectAllEmergencySettings() {
        Query query = this.createQuery(" from EmergencySetting ");
        List list = query.list();
        return list;
    }
}
