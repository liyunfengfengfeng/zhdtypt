package com.newage.iep.action.search;

import com.newage.iep.serivce.account.OrganizationService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by a1996_000 on 2017/8/24.
 */
//导航栏搜索处理
public class SearchAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
    HttpServletRequest request;
    HttpServletResponse response;
    private String searchecontent;
    @Autowired
    @Qualifier("organizationService")
    OrganizationService organizationService;
    //到达搜索页面
    public String toSearch(){
        System.out.println("您需要搜索的内容是  :" + searchecontent);
        if(searchecontent==null){
            return "";
        }
        //页面顶部的搜索可以按照设备、组织、作业、事件等做全局资源的搜索
        //查询组织数据
        List orgs = new ArrayList();
        orgs.clear();
        if(searchecontent!=null&&searchecontent.length()>0) {//模糊查询 标题与内容
            orgs = organizationService.searchInfor(searchecontent);
        }else{
            orgs = organizationService.searchInfor();
        }
        request.setAttribute("orgs",orgs);
        request.setAttribute("searchecontent",searchecontent);
        return "toSearch";
    }

    /**
     * 到达搜索结果详细信息页面
     * @param
     */
    public String searchResultDetails(){
        return "searchResultDetails";
    }
    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    public String getSearchecontent() {
        return searchecontent;
    }

    public void setSearchecontent(String searchecontent) {
        this.searchecontent = searchecontent;
    }
}
