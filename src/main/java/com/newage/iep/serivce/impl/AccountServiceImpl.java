package com.newage.iep.serivce.impl;

import com.newage.iep.business.dao.GenericHibernateDAO;
import com.newage.iep.pojos.Account;
import com.newage.iep.serivce.AccountService;
import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by a1996_000 on 2017/8/10.
 */
//账户业务层
@Service("accountService")
public class AccountServiceImpl extends GenericHibernateDAO implements AccountService{
    /**
     * 处理用户注册
     * @param account
     * @return
     */
    @Transactional
    @Override
    public boolean register(Account account) {
        this.save(account);
        return true;
    }

    /**
     * 查询所有的组织
     * @return
     */
    @Override
    public List queryAllOrgs() {
       Query query = this.createQuery("from Organization");
        return query.list();
    }

    /**
     * 检查用户输入的邮箱是否存在
     * @param email
     * @return
     */
    @Override
    public boolean queryEmail(String email) {
        System.out.println("dao层" + email);
        Query query = this.createQuery(" from Account account where account.email=:emailname");
        query.setString("emailname",email);
        List emails = query.list();
        System.out.println("emails.size()" + emails.size());
        //用户的邮箱已经注册过了
        if(emails!=null&&emails.size()>0){
            return true;
        }
        return false;
    }


}
