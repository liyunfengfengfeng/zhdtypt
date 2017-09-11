package com.newage.iep.serivce.impl.account;

import com.newage.iep.business.dao.GenericHibernateDAO;
import com.newage.iep.pojos.organization.Organization;
import com.newage.iep.serivce.account.OrganizationService;
import com.newage.iep.util.page.Page;
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
     * @return true有效 false无效 不合法的组织
     */
    @Override
    public boolean checkCmpCode(String cmp_code) {
        Query query = this.createQuery(" from Organization organization where organization.cmp_name=:cmp_code ");
        query.setString("cmp_code",cmp_code);
        List orgs = query.list();
        //用户输入的组织代号有效
        if(orgs!=null&&orgs.size()==1){
            return true;
        }
        return false;
    }

    /**
     * 查询出所有的组织
     * @return
     */
    @Override
    public List queryAllOrgs() {
        Query query =  this.createQuery(" from Organization ");
        List orgs = query.list();
        return orgs;
    }

    /**
     * 根据id查询组织单位
     * @param orgid
     * @return
     */
    @Override
    public Organization queryOrgById(String orgid) {
        Query query = this.createQuery("from Organization organization where organization.cmp_id=:cmp_id1 ");
        query.setString("cmp_id1",orgid);
        List list = query.list();
        if(list!=null&&list.size()==1){
            Organization organization = (Organization) list.get(0);
            return organization;
        }
        return null;
    }

    /**
     * 搜索组织中匹配的标题与内容标题与内容
     * @param searchecontent
     * @return
     */
    @Override
    public List searchInfor(String searchecontent) {
        Query query = this.createQuery("from Organization organization where organization.title like :name1 or organization.content like :content1 ");
        query.setString("name1","%" + searchecontent + "%");
        query.setString("content1","%" + searchecontent + "%");
        List orgs = query.list();
        System.out.println("orgs size is "  +  orgs.size());
        return orgs;
    }

    /**
     * 查询所有组织信息
     * @return
     */
    @Override
    public List searchInfor() {
        Query query = this.createQuery("from Organization organization  ");
        List orgs = query.list();

        return orgs;
    }

    /**
     * 通过id查询组织信息
     * @param cmpid
     * @return
     */
    @Override
    public Organization searchOrgInfor(String cmpid) {
        Query query = this.createQuery("from Organization organization where organization.cmp_id=:cmp_id1 ");
        query.setString("cmp_id1",cmpid);
        List list = query.list();
        if(list!=null&&list.size()==1){
            Organization organization = (Organization) list.get(0);
            return organization;
        }
        return null;
    }

    /**
     * 分页查询出所有数据
     * @param page
     * @return
     */
    @Override
    public List<Organization> selectOrganizationsByPage(Page page) {
        Query query = this.createQuery("from Organization ");
        query.setFirstResult(page.getStart());//是int值，是开始查询的位置
        query.setMaxResults(page.getPageSize());//最大容量
        //返回分页后的数据
        List list = query.list();
        return list;
    }
}
