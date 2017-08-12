package com.newage.iep.serivce.impl;

import com.newage.iep.business.dao.GenericHibernateDAO;
import com.newage.iep.pojos.Personnel;
import com.newage.iep.serivce.PersonnelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by a1996_000 on 2017/8/12.
 */
//人员信息业务层
@Service("personnelService")
public class PersonnelServiceImpl extends GenericHibernateDAO implements PersonnelService {
    @Transactional
    //注册人员信息
    @Override
    public void registerPersonnelInfo(Personnel personnel) {

            this.save(personnel);

    }
}
