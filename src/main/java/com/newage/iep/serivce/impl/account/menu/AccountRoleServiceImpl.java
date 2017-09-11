package com.newage.iep.serivce.impl.account.menu;

import com.newage.iep.business.dao.GenericHibernateDAO;
import com.newage.iep.pojos.account.AccountRole;
import com.newage.iep.serivce.menu.AccountRoleService;
import org.hibernate.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a1996_000 on 2017/8/21.
 */
//用户角色业务层
@Service("accountRoleService")
public class AccountRoleServiceImpl  extends GenericHibernateDAO implements AccountRoleService{
    //根据当前用户id查询出所有用户具有的所有角色 返回所有角色id
    @Override
    public List queryAllRoles(String account_0_id) {
        Query query = this.createQuery("from AccountRole accountRole where accountRole.account_id=:account_id1 ");
        query.setString("account_id1",account_0_id);
        List list = query.list();
        if(list!=null){
            List roleids = new ArrayList();
            for(Object obj:list){
                AccountRole accountRole = (AccountRole)obj;
                if(accountRole!=null){
                    roleids.add(accountRole.getRole_id());
                }
            }
            return roleids;
        }
        return null;
    }
}
