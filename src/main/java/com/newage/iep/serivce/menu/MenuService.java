package com.newage.iep.serivce.menu;

import java.util.List;

/**
 * Created by a1996_000 on 2017/8/21.
 */
public interface MenuService {
    //查询二级菜单
    List querySecondMenus();
    //查询该登陆用户所具有的一级菜单
    List queryFirstMenus(List root_menus_ids);
}
