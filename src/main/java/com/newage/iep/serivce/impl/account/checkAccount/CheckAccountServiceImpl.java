package com.newage.iep.serivce.impl.account.checkAccount;

import com.newage.iep.business.dao.GenericHibernateDAO;
import com.newage.iep.pojos.account.Account;
import com.newage.iep.pojos.account.AccountRole;
import com.newage.iep.pojos.role.Role;
import com.newage.iep.serivce.checkAccount.CheckAccountService;
import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by qq101 on 2017/8/14.
 */

/**
 * 审核用户业务层
 */
@Service("checkAccountService")
public class CheckAccountServiceImpl extends GenericHibernateDAO implements CheckAccountService {
    @Override
    public List<Account> queryAccountByStatus(){

        Query query = this.createQuery("from Account ta where ta.status=0");
        List<Account> list = query.list();
        if(list!=null){
            System.out.println("querying list by status=0----"+list.size());
        }
        return list;
    }

    /**
     * 通过邮箱查询人员中对应的用户
     * 查询当前待审核用户的信息
     * @param email
     * @return
     */
    @Override
    public List<Account> queryPersonByMail(String email) {
        Query query = this.createQuery(" from Account ta where ta.email=:email ");
        query.setString("email",email);
        List<Account> list = query.list();
        if(list!=null&&list.size()==1){
            Account account = list.get(0);
            System.out.println("querying list by email----"+list.size());

        }
        return list;
    }
    /**
     * 通过level查询对应的角色  查询出所有的角色为用户分配角色
     * @param number ---level
     * @return
     */
    @Override
    public List<Role> queryRoleByLevel(String  number){

        Query query = this.createQuery("from Role tr where tr.Role_level=:number");
        query.setString("number",number);
        List<Role> list = query.list();
        if(list!=null){
            System.out.println("querying role by Role_level="+number+"----"+list.size());
        }
        return list;
    }

    /**
     * 解析审核用户 分配角色后 前台传来的 String类型的参数  变成String数组
     * @param data

     * @return String[]
     */
    @Override
    public String[] parseForArray(String data){
       data=data.substring(1,data.length()-1);
        //去掉两边的[]
        String[] strArray = data.split(",");
        //用“，”分离
        for (int i = 0; i < strArray.length; i++) {
            strArray[i]=strArray[i].substring(1,strArray[i].length()-1);
            System.out.println(strArray[i]);
        }
        return strArray;
    }

    /**
     * 保存账户 角色关系
     * 为用户添加角色信息
     * @param accountId
     * @param roleId
     */
    @Transactional
    @Override
    public void allotRoleForAccount(String accountId,String roleId){
        AccountRole tAccountRoleEntity=new AccountRole();

//        tAccountRoleEntity.setAccountId(accountId);
//        tAccountRoleEntity.setRoleId(roleId);
          tAccountRoleEntity.setAccount_id(accountId);
          tAccountRoleEntity.setRole_id(roleId);
        try {
            String hql1="delete from AccountRole tar where tar.account_id=:accountId and tar.role_id=:roleId";

            Query query1=this.createQuery(hql1);
            query1.setString("accountId",accountId);
            query1.setString("roleId",roleId);
            query1.executeUpdate();

            save(tAccountRoleEntity);
            //设置用户状态为已审核
            String hql2="update Account ta set ta.status=1 where ta.account_0_id=:accountId";
            Query query2=this.createQuery(hql2);
            query2.setString("accountId",accountId);
            query2.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 审核未通过
     * @param emailName
     */
    @Override
    @Transactional
    public void updateAccountStatusTo2(String emailName){

        try {
            String hql1="update Account ta set ta.status=2 where ta.email=:emailName";

            Query query1=this.createQuery(hql1);
            query1.setString("emailName",emailName);
            query1.executeUpdate();


        }catch (Exception e){
            e.printStackTrace();

        }
    }
}
