package com.newage.iep.serivce.impl.account;

import com.newage.iep.business.dao.GenericHibernateDAO;
import com.newage.iep.pojos.Account;
import com.newage.iep.serivce.account.AccountService;
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
     * @return  true邮箱已经被注册过了 false邮箱未注册
     */
    @Override
    public boolean queryEmail(String email) {
        System.out.println("dao层" + email);
        Query query = this.createQuery(" from Account account where account.email=:emailname");
        query.setString("emailname",email);
        List emails = query.list();
        //System.out.println("emails.size()" + emails.size());
        //用户的邮箱已经注册过了
        if(emails!=null&&emails.size()>0){
            return true;
        }
        return false;
    }

    /**
     * 检查用户名 密码是否匹配
     * @param email
     * @param password
     * @return
     */
    @Override
    public boolean checkLogin(String email, String password) {
        Query query = this.createQuery(" from Account account where account.email=:emailname and account.password=:passwordname");
        query.setString("emailname",email);
        query.setString("passwordname",password);
        List list = query.list();
        if(list!=null&&list.size()==1){
            return true;
        }
        return false;
    }

    /**
     * 根据邮箱查询当前状态码的值
     * @param email
     * @return
     */
    @Override
    public String getStatus(String email) {
        Query query = this.createQuery(" from Account account where account.email=:emailname");
        query.setString("emailname",email);
        List list = query.list();
        if(list!=null&&list.size()==1){
            Account account = (Account)list.get(0);
            return account.getStatus().toString().trim();
        }
        return null;
    }

    /**
     * 根据邮箱查询账户
     * @param email1
     * @return
     */
    @Override
    public Account queryAccountByEmail(String email1) {
        Query query = this.createQuery(" from Account account where account.email=:emailname");
        query.setString("emailname",email1);
        List list = query.list();
        if(list!=null&&list.size()==1){
            Object obj = list.get(0);
            Account account = (Account)obj;
            return account;
        }else{
            return null;
        }
    }

    /**
     * 更新账户信息
     * @param account
     */
    @Transactional
    @Override
    public void updateAccountInfo(Account account) {
        this.update(account);
    }


}
