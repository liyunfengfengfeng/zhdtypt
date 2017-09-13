package com.newage.iep.serivce.dataDictionary;


import com.newage.iep.pojos.city.City;
import com.newage.iep.pojos.organization.OrganizationType;

import java.util.List;

/**
 * Created by qq101 on 2017/8/28.
 */
public interface DataDictionaryService {

/*
    通过level字段等于0 查询省份
*/
    List<City> queryProvinceByLevel();
    /*
    通过省份id 查询包含的城市
    */
    List<City> queryCityByProvinceId(int id);

    /*
    通过城市id 查询包含区
    */
    List<City> queryAreaByCityId(int id);

    /*查询单位类别*/
    List<OrganizationType> queryTypeForOrganization();

}
