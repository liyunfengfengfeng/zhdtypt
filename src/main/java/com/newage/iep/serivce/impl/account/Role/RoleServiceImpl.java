package com.newage.iep.serivce.impl.account.Role;

import com.newage.iep.business.dao.GenericHibernateDAO;
import com.newage.iep.pojos.role.Role;
import com.newage.iep.serivce.Role.RoleService;
import com.newage.iep.util.page.Page;
import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by a1996_000 on 2017/9/9.
 */
@Service("roleService")
public class RoleServiceImpl extends GenericHibernateDAO implements RoleService {
    /**
     * 保存角色信息
     * @param role
     */
    @Transactional
    @Override
    public void saveRoleInfo(Role role) {
        this.save(role);
    }

    /**
     * 查询出所有的角色
     * @return
     */
    @Override
    public List<Role> selectAllRoles() {
        Query query = this.createQuery("from Role");
        return query.list();
    }

    /**
     * 分页查询角色
     * @param page
     * @return
     */
    @Override
    public List<Role> selectRolesByPage(Page page) {
        Query query = this.createQuery("from Role ");
        query.setFirstResult(page.getStart());//是int值，是开始查询的位置
        query.setMaxResults(page.getPageSize());//最大容量
        //返回分页后的数据
        List list = query.list();
        return list;
    }

    /**
     * 通过id查询角色
     * @param roleId
     * @return
     */
    @Override
    public Role selectRoleById(String roleId) {
        Query query = this.createQuery(" from Role role where role.roleid=:roleid1");
        query.setString("roleid1",roleId);
        List list = query.list();
        if(list!=null&&list.size()==1){
            Role role = (Role)list.get(0);
            return role;
        }
        return null;
    }
}
