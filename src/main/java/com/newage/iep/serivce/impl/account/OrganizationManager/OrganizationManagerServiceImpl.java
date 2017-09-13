package com.newage.iep.serivce.impl.account.OrganizationManager;

import com.newage.iep.business.dao.GenericHibernateDAO;
import com.newage.iep.pojos.organization.Accessory;
import com.newage.iep.pojos.organization.Organization;
import com.newage.iep.pojos.organization.OrganizationType;
import com.newage.iep.serivce.OrganizationManager.OrganizationManagerService;
import com.newage.iep.util.page.Page;
import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qq101 on 2017/8/21.
 */
@Service("organizationManagerService")
public class OrganizationManagerServiceImpl extends GenericHibernateDAO implements OrganizationManagerService {
    @Override
    public List<Organization> queryOrganization() {
        List<Organization> list=new ArrayList<Organization>();
        try {
            Query query = this.createQuery("from Organization org");
            list =query.list();
            return list;
        }catch(Exception e){
            return list;
        }
    }

    @Override
    public Organization queryOrganization(String cmpId) {
        Organization org=new Organization();
        try {
            Query query = this.createQuery("from Organization org where org.cmp_id=:cmpId");
            query.setString("cmpId",cmpId);
            List<Organization> list= query.list();
            org=list.get(0);
            return org;
        }catch(Exception e){
            e.printStackTrace();
            return org;
        }
    }

    @Override
    public boolean queryOrganizationByName(String cmp_name) {
            Query query = this.createQuery("from Organization org where org.cmp_name=:cmp_name");
            query.setString("cmp_name",cmp_name);
            List list =query.list();
            int count=list.size();
        return count>0?true:false;
    }

    @Override
    public boolean queryOrganizationById(String cmp_code) {
        Query query = this.createQuery("from Organization org where org.cmp_code=:cmp_code");
        query.setString("cmp_code",cmp_code);
        List list =query.list();
        int count=list.size();
        return count>0?true:false;
    }

    @Transactional
    @Override
    public boolean addOrganization(Organization organization) {
        try{
            this.save(organization);
            return  true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    @Transactional
    @Override
    public boolean updateOrganization(Organization organization) {
        try{
            this.update(organization);
            return  true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    @Transactional
    @Override
    public boolean deleteOrganization(Organization organization) {
        try{
            this.delete(organization);
            return  true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public String queryOrganizationId(Organization organization) {
        Query query = this.createQuery("select cmp_id from Organization org where org.cmp_name=:cmpName");
        query.setString("cmpName",organization.getCmp_name());
        List<String> list =query.list();
        String cmp_id=list.get(0);
        if(cmp_id!=null){
            System.out.println("querying id----"+cmp_id);
        }
        return cmp_id;
    }
    @Transactional
    @Override
    public boolean addAccessoryForOrganization(String fileName, String savePath, String cmpId) {
        //保存附件对象
        Accessory accessoryEntity=new Accessory();
        accessoryEntity.setAccessoryName(fileName);
        accessoryEntity.setAccessoryPath(savePath);
        //找出对应组织
        Query query = this.createQuery("from Organization org where org.cmp_id=:cmpId");
        query.setString("cmpId",cmpId);
        List<Organization> list =query.list();
        Organization organization=list.get(0);
        //进行关联
        accessoryEntity.setCmp(organization);
        save(accessoryEntity);

        //可以成功保存数据。
        //先添加数据，将关系字段设置为null，再用update语句来更新关系字段
        //但是会发出多余的update语句来维持关系。
//        save(organization);
        return true;
    }

    @Override
    public List<OrganizationType> queryOrganizationType(String id) {
        String sql="select  t.Cmp_type from t_organizationtype t where t.CmpType_id=? ";
        Query query=createSQLQuery(sql,id);
        List<OrganizationType> list=query.list();
        return list;
    }

    @Override
    public List<Organization> selectOrganizationsByPage(Page page) {
        Query query = this.createQuery("from Organization org");
        query.setFirstResult(page.getStart());//是int值，是开始查询的位置
        query.setMaxResults(page.getPageSize());//最大容量
        //返回分页后的数据
        List list = query.list();
        return list;

    }


}
