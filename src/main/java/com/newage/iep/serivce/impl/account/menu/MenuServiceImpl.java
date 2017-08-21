package com.newage.iep.serivce.impl.account.menu;

import com.newage.iep.business.dao.GenericHibernateDAO;
import com.newage.iep.serivce.menu.MenuService;
import org.hibernate.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a1996_000 on 2017/8/21.
 */
//菜单业务层
@Service("menuService")
public class MenuServiceImpl extends GenericHibernateDAO implements MenuService{
    /**
     * 查询二级菜单
     * @return
     */
    @Override
    public List querySecondMenus() {
        Query query = this.createQuery(" from Menu menu where menu.parent_id!=0");
        List secondMenus = query.list();
        System.out.println("二级菜单共有  :" + secondMenus.size());

        return query.list();
    }
    //查询该登陆用户所具有的一级菜单
    @Override
    public List queryFirstMenus(List root_menus_ids) {
        List firstMenus = new ArrayList();
        if(root_menus_ids!=null&&root_menus_ids.size()>0){
            for(Object obj:root_menus_ids){
                String rootid = (String)obj;
                Query query = this.createQuery("from Menu menu where menu.menu_id=:menu_id1 ");
                query.setString("menu_id1",rootid);
                List list = query.list();
                if(list!=null&&list.size()==1) {
                    firstMenus.add(list.get(0));
                }
            }
            return firstMenus;
        }
        return null;
    }
}
