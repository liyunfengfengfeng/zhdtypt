package com.newage.iep.serivce.checkAccount;



import com.newage.iep.pojos.Account;
import com.newage.iep.pojos.Role;

import java.util.List;

/**
 * Created by qq101 on 2017/8/14.
 */
public interface CheckAccountService {
    /**
     * 查询t_account表中Status相应数值的账号----------待审核账号列表
     */
    List<Account> queryAccountByStatus();
    /**
     * 查询t_account表中对应Email的账户信息---------用户详细信息查询
     */
    List<Account> queryPersonByMail(String mail);

    /**
     * 查询t_role表中所有角色码=10的角色---------用户角色分配
     */
    List<Role> queryRoleByLevel(String number);
    /*

       解析数组形式的数据  变成多个参数
     */
    String[] parseForArray(String data);
    /*
        给用户分配角色
      */
    void allotRoleForAccount(String accountId, String RoleId);
     /*
        更改用户状态为2  审核没通过
      */
    void updateAccountStatusTo2(String emailName);

}
