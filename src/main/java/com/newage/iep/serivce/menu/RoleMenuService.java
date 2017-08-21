package com.newage.iep.serivce.menu;

import java.util.List;

/**
 * Created by a1996_000 on 2017/8/21.
 */
public interface RoleMenuService {
    //根据角色id查询出所有的根菜单
    List queryAllRootMenuIds(List roles);
}
