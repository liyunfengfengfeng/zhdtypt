package com.newage.iep.action.dataDictionary;


import com.newage.iep.pojos.city.City;
import com.newage.iep.serivce.dataDictionary.DataDictionaryService;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONArray;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by qq101 on 2017/8/14.
 */

//省市区--数据字典
public class DataDictionaryAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
    HttpServletRequest request;
    HttpServletResponse response;

    String result=null;
    String result2=null;

    @Autowired
    @Qualifier("dataDictionaryService")
    DataDictionaryService DataDictionaryService;



    public String  queryCityByProvinceId() {

        String  provinceId=(String)request.getParameter("provinceId");
        int id= Integer.parseInt(provinceId);
        List<City> cityList= DataDictionaryService.queryCityByProvinceId(id);
        result = JSONArray.fromObject(cityList).toString();
        return "cityList";
    }

    public String  queryAreaByCityId() {

        String  cityId=(String)request.getParameter("cityId");
        int id= Integer.parseInt(cityId);
        List<City> cityList= DataDictionaryService.queryAreaByCityId(id);
        result2 = JSONArray.fromObject(cityList).toString();
        return "areaList";
    }


    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
    public String getResult2() {
        return result2;
    }
    public void setResult2(String result2) {
        this.result2 = result2;
    }
    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

}

