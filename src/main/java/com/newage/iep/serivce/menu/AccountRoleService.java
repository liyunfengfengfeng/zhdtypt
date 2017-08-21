package com.newage.iep.serivce.menu;

import java.util.List;

/**
 * Created by a1996_000 on 2017/8/21.
 */
//用户角色业务层
public interface AccountRoleService {
    //根据当前用户id查询出所有用户具有的所有角色
    List queryAllRoles(String account_0_id);
}
