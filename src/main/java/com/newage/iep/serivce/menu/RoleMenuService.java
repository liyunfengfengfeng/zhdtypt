package com.newage.iep.serivce.menu;

import java.util.List;

/**
 * Created by a1996_000 on 2017/8/21.
 */
public interface RoleMenuService {
    //根据角色id查询出所有的根菜单
    List queryAllRootMenuIds(List roles);
    //为角色分配菜单权限
    void allotMenuForRole(String s, String s1);
    //删除该角色对应的所有的菜单
    void deleteAllMenus(String s);
}
