package com.newage.iep.serivce.impl.account;

import com.newage.iep.business.dao.GenericHibernateDAO;
import com.newage.iep.pojos.account.Personnel;
import com.newage.iep.serivce.account.PersonnelService;
import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by a1996_000 on 2017/8/12.
 */
//人员信息业务层
@Service("personnelService")
public class PersonnelServiceImpl extends GenericHibernateDAO implements PersonnelService {
    /**
     * 查询所有的人员信息
     */
    @Override
    public void queryAllPersons() {
       Query query = this.createQuery(" from Personnel ");
        System.out.println("query.list().size()  : " + query.list().size());
    }

    /**
     * 用户注册时添加人员信息
     * @param personnel
     */
    @Transactional
    @Override
    public void registerPersonnelInfo(Personnel personnel) {
        this.save(personnel);
    }

    /**
     * 通过邮箱查询人员中对应的用户
     * @param mail
     * @return
     */
    @Override
    public Personnel queryPersonByMail(String mail) {
        Query query = this.createQuery(" from Personnel personnel where personnel.Mail=:Mail ");
        query.setString("Mail",mail);
        List list = query.list();
        if(list!=null&&list.size()==1){
            Personnel personnel = (Personnel) list.get(0);
            return personnel;
        }
        return null;
    }

    /**
     * 更新人员信息
     * @param personnel
     */
    @Transactional
    @Override
    public void updatePersonnelInfo(Personnel personnel) {
        this.update(personnel);
    }

    /**
     * 根据邮箱查询人员信息
     * @param email1
     * @return
     */
    @Override
    public Personnel queryPersonnelByEmail(String email1) {
        Query query = this.createQuery(" from Personnel personnel where personnel.Mail=:emailname");
        query.setString("emailname",email1);
        List list = query.list();
        if(list!=null&&list.size()==1){
            Object obj = list.get(0);
            Personnel personnel = (Personnel)obj;
            return personnel;
        }else{
            return null;
        }
    }
}
