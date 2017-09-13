package com.newage.iep.action.organizationManager;


import com.newage.iep.pojos.city.City;
import com.newage.iep.pojos.organization.Organization;
import com.newage.iep.pojos.organization.OrganizationType;
import com.newage.iep.serivce.OrganizationManager.OrganizationManagerService;
import com.newage.iep.serivce.dataDictionary.DataDictionaryService;
import com.newage.iep.util.page.Page;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONObject;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by qq101 on 2017/8/21.
 */
public class OrganizationManagerAction extends ActionSupport implements ModelDriven<Organization>,ServletRequestAware,ServletResponseAware {
    HttpServletRequest request;
    HttpServletResponse response;
    String result=null;
    String result2=null;
    private String cmp_name;
    private String cmp_id;


    private  Organization organization=new Organization();//组织信息注入


    @Autowired
    @Qualifier("organizationManagerService")
    OrganizationManagerService organizationManagerService;

    @Autowired
    @Qualifier("dataDictionaryService")
    DataDictionaryService dataDictionaryService;

    /*
    跳转到组织管理主页*/
    public String archive(){
        /*查询出所有数据*/
        List<Organization> list= organizationManagerService.queryOrganization();
        /*初始化page类*/
        Page page = new Page();
        int totalRecord = list.size();
        int pageSize = 5;
        int pno = 1;
        /*获取请求中的参数*/
        String pno1 = (String)request.getParameter("pno");
        if(pno1!=null){
            pno = Integer.parseInt(pno1);
        }
        String pageSize1 = (String)request.getParameter("pageSize");
        if(pageSize1!=null){
            pageSize= Integer.parseInt(pageSize1);
        }
        //设置当前页
            page.setPageNo(pno);
        //每页容量
        page.setPageSize(pageSize);
        //总页数
        int totalPageNum = (totalRecord  +  pageSize  - 1) / pageSize;
        page.setTotalPage(totalPageNum);
        //总记录数
        page.setTotalRecord(totalRecord);
        request.setAttribute("pageSize",pageSize);
        request.setAttribute("page", page);

        //hibernate分页需要的起始与终止位置
        int start = (pno-1)* pageSize;
        int end = pageSize;
        page.setStart(start);
        page.setEnd(end);

        //依据分页查询出条件
        List<Organization> organizationPages = organizationManagerService.selectOrganizationsByPage(page);
        request.setAttribute("organizationList",organizationPages);
        return "indexOfOrganization";
    }
    /*
    跳转到组织管理新增页*/
    public String addPage(){
        //     查询省份 返回给省份列表
        List<City> list= dataDictionaryService.queryProvinceByLevel();
        List<OrganizationType> list2=dataDictionaryService.queryTypeForOrganization();
        request.setAttribute("provinceList",list);
        request.setAttribute("typeList",list2);
        return "addPageOfOrganization";
    }
    /*
     添加组织到数据库*/
    public String add() throws Exception {
        boolean bool=organizationManagerService.addOrganization(organization);
        if (bool==true) {
            String id=organization.getCmp_id();
            request.setAttribute("cmp_id",id);
            return SUCCESS;
        }
        else
            return ERROR;
    }
    /*
     更新组织到数据库*/
    public String update() throws Exception {
        boolean bool=organizationManagerService.updateOrganization(organization);
        if (bool==true) {
            return "return";
        }else
            return ERROR;
    }
    /*
     跳转到组织查看详细信息页面*/
    public String lookOver(){
        Organization organization=new Organization();
        String cmpId=(String)request.getParameter("param");
        organization=organizationManagerService.queryOrganization(cmpId);
        request.setAttribute("organizationDetail",organization);
        //查询相应的单位类别
        List<OrganizationType> list2=organizationManagerService.queryOrganizationType(organization.getCmp_type());
        request.setAttribute("organizationType",list2.get(0));
        return "organizationLookOver";
    }
    /*
    跳转到编辑详细信息页面*/
    public String edit(){
        List<City> list= dataDictionaryService.queryProvinceByLevel();
        List<OrganizationType> list2=dataDictionaryService.queryTypeForOrganization();
        request.setAttribute("provinceList",list);
        request.setAttribute("typeList",list2);

        Organization organization=new Organization();
        String cmpId=(String)request.getParameter("param");
        organization=organizationManagerService.queryOrganization(cmpId);
        request.setAttribute("organizationDetail",organization);
        return "organizationEdit";
    }
    /*
     删除组织*/
    public String delete(){
        Organization organization=new Organization();
        String cmpId=(String)request.getParameter("param");
        organization=organizationManagerService.queryOrganization(cmpId);

        boolean bool=organizationManagerService.deleteOrganization(organization);
        if (bool==true) {
            return "return";
        }else
            return ERROR;
    }
    /*
     检查组织名称*/
    public String checkCmpName(){
        String cn=(String)request.getParameter("cmp_name");
        //将数据存储在map里，再转换成json类型数据，也可以自己手动构造json类型数
        Map<String,Object> map1 = new HashMap<String,Object>();
        boolean flag = organizationManagerService.queryOrganizationByName(cn);
        // true 被注册过了
        if(flag){
            map1.put("state", 0);//
            JSONObject json = JSONObject.fromObject(map1);//将map对象转换成json类型数据
            result = json.toString();//给result赋值，传递给页面
        }else{
            map1.put("state", 1);//
            JSONObject json = JSONObject.fromObject(map1);//将map对象转换成json类型数据
            result = json.toString();//给result赋值，传递给页面
        }
        return "checkCmpName";

    }
    /*
    检查组织ID
    */
    public String checkCmpId(){
        String cc=(String)request.getParameter("cmp_code");
        //将数据存储在map里，再转换成json类型数据，也可以自己手动构造json类型数
        Map<String,Object> map1 = new HashMap<String,Object>();
        boolean flag = organizationManagerService.queryOrganizationById(cc);
        // true 被注册过了
        if(flag){
            map1.put("state", 0);//
            JSONObject json = JSONObject.fromObject(map1);//将map对象转换成json类型数据
            result2 = json.toString();//给result赋值，传递给页面
        }else{
            map1.put("state", 1);//
            JSONObject json = JSONObject.fromObject(map1);//将map对象转换成json类型数据
            result2 = json.toString();//给result赋值，传递给页面
        }
        return "checkCmpId";

    }

    public String getCmp_name() {
        return cmp_name;
    }

    public void setCmp_name(String cmp_name) {
        this.cmp_name = cmp_name;
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

    public String getCmp_id() {
        return cmp_id;
    }

    public void setCmp_id(String cmp_id) {
        this.cmp_id = cmp_id;
    }

    /**
     * 模型驱动
     * @return Organization
     */
    @Override
    public Organization getModel() {
        return organization;
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
