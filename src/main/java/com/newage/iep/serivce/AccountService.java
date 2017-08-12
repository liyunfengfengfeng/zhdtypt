package com.newage.iep.serivce;

import com.newage.iep.pojos.Account;

import java.util.List;

/**
 * Created by a1996_000 on 2017/8/10.
 */
//账户业务层
public interface AccountService {
    /**
     * 处理用户注册
     * @param account
     * @return
     */
    public boolean register(Account account);

    /**
     * 查询所有的组织
     * @return
     */
    List queryAllOrgs();

    /**
     * 检查用户输入的邮箱是否存在
     * @param email
     * @return
     */
    boolean queryEmail(String email);
}
