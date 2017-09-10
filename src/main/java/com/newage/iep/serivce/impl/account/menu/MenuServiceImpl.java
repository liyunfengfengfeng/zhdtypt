package com.newage.iep.serivce.impl.account.menu;

import com.newage.iep.business.dao.GenericHibernateDAO;
import com.newage.iep.pojos.Menu;
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
        Query query = this.createQuery(" from Menu menu where menu.parent_id!=0  ");
        List secondMenus = query.list();
        System.out.println("二级菜单共有  :" + secondMenus.size());

        return query.list();
    }
    //查询该登陆用户所具有的一级业务导航菜单
    @Override
    public List queryFirstMenus(List root_menus_ids) {
        List firstMenus = new ArrayList();
        if(root_menus_ids!=null&&root_menus_ids.size()>0){
            for(Object obj:root_menus_ids){
                String rootid = (String)obj;
                Query query = this.createQuery("from Menu menu where menu.menu_id=:menu_id1 and module_name=:module_name1 order by menu_order asc");
                query.setString("menu_id1",rootid);
                query.setString("module_name1","业务导航");
                List list = query.list();
                if(list!=null&&list.size()==1) {
                    firstMenus.add(list.get(0));
                }
            }
            return firstMenus;
        }
        return null;
    }

    /**
     * 根据跟菜单id查询所有的跟菜单  功能模块菜单
     * 查询该登陆用户所具有的一级功能模块菜单菜单
     * @param root_menus_ids
     * @return
     */
    @Override
    public List queryFirstFunctionMenus(List root_menus_ids) {
        List firstFunctionMenus = new ArrayList();
        if(root_menus_ids!=null&&root_menus_ids.size()>0){
            for(Object obj:root_menus_ids){
                String rootid = (String)obj;
                Query query = this.createQuery("from Menu menu where menu.menu_id=:menu_id1 and module_name=:module_name1 order by menu_order asc");
                query.setString("menu_id1",rootid);
                query.setString("module_name1","功能模块");
                List list = query.list();
                if(list!=null&&list.size()==1) {
                    firstFunctionMenus.add(list.get(0));
                }
            }
            return firstFunctionMenus;
        }
        return null;
    }

    /**
     * 查询出所有一级菜单
     * @return
     */
    @Override
    public List<Menu> selectAllRootMenus() {
        Query query = this.createQuery("from Menu menu where menu.parent_id=:parent_id1 ");
        query.setString("parent_id1","0");
        List list = query.list();
        return list;
    }
}
