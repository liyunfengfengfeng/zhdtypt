package com.newage.iep.serivce.impl.account.set;

import com.newage.iep.business.dao.GenericHibernateDAO;
import com.newage.iep.pojos.BasicSetting;
import com.newage.iep.serivce.set.BasicSettingService;
import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by a1996_000 on 2017/8/28.
 */
@Service("BasicSettingService")
public class BasicSettingServiceImpl extends GenericHibernateDAO implements BasicSettingService {
    /**
     * 查找数据库中基本设置的数量
     * @return
     */
    @Override
    public List findBasicSettingCounts() {
        Query query = this.createQuery(" from BasicSetting ");
        List list = query.list();
        if(list!=null){
            return list;
        }
        return null;
    }

    /**
     * 更新基础信息
     * @param basicSetting
     */
    @Transactional
    @Override
    public void updateBasicSetting(BasicSetting basicSetting) {
        if(basicSetting!=null){
            this.update(basicSetting);
        }
    }

    /**
     * 保存基础设置
     * @param basicSetting
     */
    @Transactional
    @Override
    public void saveBasicSetting(BasicSetting basicSetting) {
        if(basicSetting!=null){
            this.save(basicSetting);
        }
    }
}
