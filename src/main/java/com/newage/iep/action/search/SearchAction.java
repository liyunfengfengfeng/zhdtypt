package com.newage.iep.action.search;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by a1996_000 on 2017/8/24.
 */
//导航栏搜索处理
public class SearchAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
    HttpServletRequest request;
    HttpServletResponse response;
    private String searchecontent;
    //到达搜索页面
    public String toSearch(){
        System.out.println("您需要搜索的内容是  :" + searchecontent);
        //页面顶部的搜索可以按照设备、组织、作业、事件等做全局资源的搜索

        return "toSearch";
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
