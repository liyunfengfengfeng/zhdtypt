package com.newage.iep.action.search;

import com.newage.iep.pojos.organization.Organization;
import com.newage.iep.serivce.account.OrganizationService;
import com.newage.iep.util.page.Page;
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
        //System.out.println("您需要搜索的内容是  :" + searchecontent);
//        if(searchecontent==null){
//            return "";
//        }
        //页面顶部的搜索可以按照设备、组织、作业、事件等做全局资源的搜索
        //查询组织数据(既满足搜索条件的所有匹配数据)
        List orgs = new ArrayList();
        orgs.clear();
        if(searchecontent!=null&&searchecontent.length()>0) {//模糊查询 标题与内容
            orgs = organizationService.searchInfor(searchecontent);
        }else{
            orgs = organizationService.searchInfor();
        }

          Page page = new Page();
          int totalRecord = orgs.size();
        int pageSize = 1;
        //获取当前页
        String pno1 = (String)request.getParameter("pno");
        int pno = 1;
        if(pno1!=null){
             pno = Integer.parseInt(pno1);
        }
        //设置当前页
        page.setPageNo(pno);
        //每页容量
        page.setPageSize(1);
        //总页数
        int totalPageNum = (totalRecord  +  pageSize  - 1) / pageSize;
        page.setTotalPage(totalPageNum);
        //总记录数
        page.setTotalRecord(totalRecord);
        request.setAttribute("page", page);
        //分页需要的起始与终止位置
        int start = (pno-1)* pageSize;
        int end = pageSize;
        page.setStart(start);
        page.setEnd(end);
        //依据分页查询出条件
        List<Organization> organizationPages = organizationService.selectOrganizationsByPage(page);
        request.setAttribute("organizationPages",organizationPages);
        request.setAttribute("searchecontent",searchecontent);
        return "toSearch";
    }

    /**
     * 到达搜索结果详细信息页面
     * @param
     */
    public String searchResultDetails(){
        //获取要显示数据的id
        String cmpid = request.getParameter("id");
        //通过id查询到当前数据进行显示
        Organization organization = organizationService.searchOrgInfor(cmpid);
        request.setAttribute("info",organization);
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
