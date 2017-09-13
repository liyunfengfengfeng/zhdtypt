package com.newage.iep.serivce.impl.account.dataDictionary;

import com.newage.iep.business.dao.GenericHibernateDAO;
import com.newage.iep.pojos.city.City;
import com.newage.iep.pojos.organization.OrganizationType;
import com.newage.iep.serivce.dataDictionary.DataDictionaryService;
import org.hibernate.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by qq101 on 2017/8/28.
 */
@Service("dataDictionaryService")
public class dataDictioinaryServiceImpl extends GenericHibernateDAO implements DataDictionaryService {
    /*
    通过level字段等于0 查询省份
    */
    @Override
    public List<City> queryProvinceByLevel(){
        Query query = this.createQuery("from City tc where tc.level=0");
        List<City> list = query.list();

        return list;

    }
    /*
        通过省份id 查询包含的城市
    */
    @Override
    public List<City> queryCityByProvinceId(int provinceId){
        Query query = this.createQuery("from City tc where tc.parent_id=:provinceId");
        query.setInteger("provinceId",provinceId);
        List<City> list = query.list();

        return list;
    }

    /*
          通过城市id 查询包含的区
      */
    @Override
    public List<City> queryAreaByCityId(int cityId){
        Query query = this.createQuery("from City tc where tc.parent_id=:cityId");
        query.setInteger("cityId",cityId);
        List<City> list = query.list();

        return list;
    }

   /* 在新增组织页面上的使用单位类别*/
    @Override
    public List<OrganizationType> queryTypeForOrganization(){

        Query query = this.createQuery("from OrganizationType");

        List<OrganizationType> list = query.list();

        return list;
    }
}
