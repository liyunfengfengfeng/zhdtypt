package com.newage.iep.serivce.menu;

import com.newage.iep.pojos.Menu;

import java.util.List;

/**
 * Created by a1996_000 on 2017/8/21.
 */
public interface MenuService {
    //查询二级菜单
    List querySecondMenus();
    //查询该登陆用户所具有的一级菜单
    List queryFirstMenus(List root_menus_ids);
    //根据跟菜单id查询所有的跟菜单  功能模块菜单
    List queryFirstFunctionMenus(List root_menus_ids);
    //查询parentid=0的菜单
    List<Menu> selectAllRootMenus();
}
