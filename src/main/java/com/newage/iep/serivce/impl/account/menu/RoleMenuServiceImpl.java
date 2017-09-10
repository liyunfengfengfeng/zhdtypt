package com.newage.iep.serivce.impl.account.menu;

import com.newage.iep.business.dao.GenericHibernateDAO;
import com.newage.iep.pojos.RoleMenu;
import com.newage.iep.serivce.menu.RoleMenuService;
import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a1996_000 on 2017/8/21.
 */
@Service("roleMenuService")
public class RoleMenuServiceImpl extends GenericHibernateDAO implements RoleMenuService {
    //根据角色id查询出所有的根菜单
    @Override
    public List queryAllRootMenuIds(List roles) {//传入参数为角色id
        List root_menus_ids = new ArrayList();
        if(roles!=null&&roles.size()>0){
            for(Object obj :roles){
                String roleid = (String)obj;
                Query query = this.createQuery("from RoleMenu roleMenu where roleMenu.role_id=:roleid1 ");
                query.setString("roleid1",roleid);
                List list = query.list();
                if(list!=null){
                    for(Object obj2:list){
                        RoleMenu roleMenu = (RoleMenu) obj2;
                        root_menus_ids.add(roleMenu.getMenu_id());
                    }
                }
                return root_menus_ids;
            }
        }
        return null;
    }

    /**
     * 为角色分配菜单权限
     * @param s
     * @param s1
     */
    @Transactional
    @Override
    public void allotMenuForRole(String s, String s1) {

        RoleMenu roleMenu = new RoleMenu();
        roleMenu.setMenu_id(s1);
        roleMenu.setRole_id(s);
        this.save(roleMenu);
    }
    //删除该角色对应的所有的菜单
    @Transactional
    @Override
    public void deleteAllMenus(String s) {
        Query query = this.createQuery("from RoleMenu roleMenu where roleMenu.role_id=:roleid1 ");
        query.setString("roleid1",s);
        List<RoleMenu> list = query.list();
        for(RoleMenu roleMenu:list){
            this.delete(roleMenu);
        }
    }
}
