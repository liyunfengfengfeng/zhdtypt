package com.newage.iep.serivce.impl.account.set;

import com.newage.iep.business.dao.GenericHibernateDAO;
import com.newage.iep.pojos.FaultSetting;
import com.newage.iep.serivce.set.FaultSettingService;
import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by a1996_000 on 2017/8/28.
 */
//故障设置业务层
@Service("faultSettingService")
public class FaultSettingServiceImpl extends GenericHibernateDAO implements FaultSettingService {
    /**
     * 查询故障记录的数量
     * @return
     */
    @Override
    public List findFaultSettingCounts() {
        Query query = this.createQuery(" from FaultSetting ");
        List list = query.list();
        if(list!=null){
            return list;
        }
        return null;
    }

    /**
     * 更新故障信息
     * @param faultSetting
     */
    @Transactional
    @Override
    public void updateFaultSetting(FaultSetting faultSetting) {
        if(faultSetting!=null){
            this.update(faultSetting);
        }
    }

    /**
     * 保存故障信息
     * @param faultSetting
     */
    @Transactional
    @Override
    public void saveFaultSetting(FaultSetting faultSetting) {
        if(faultSetting!=null){
            this.save(faultSetting);
        }
    }
    //查询所有的故障记录
    @Override
    public List<FaultSetting> selectAllFaultSettings() {
        Query query = this.createQuery(" from FaultSetting ");
        List list = query.list();
        return list;
    }
}
