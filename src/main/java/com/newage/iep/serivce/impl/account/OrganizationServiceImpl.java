package com.newage.iep.serivce.impl.account;

import com.newage.iep.business.dao.GenericHibernateDAO;
import com.newage.iep.serivce.account.OrganizationService;
import org.hibernate.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by a1996_000 on 2017/8/11.
 */
//组织业务层
@Service("organizationService")
public class OrganizationServiceImpl extends GenericHibernateDAO implements OrganizationService{
    /**
     * 检查用户输入的组织代号是否有效
     * @param cmp_code
     * @return
     */
    @Override
    public boolean checkCmpCode(String cmp_code) {
        Query query = this.createQuery(" from Organization organization where organization.cmp_code=:cmp_code ");
        query.setString("cmp_code",cmp_code);
        List orgs = query.list();
        //用户输入的组织代号有效
        if(orgs!=null&&orgs.size()==1){
            return true;
        }
        return false;
    }
}
