package com.newage.iep.action.checkwork;

import com.newage.iep.pojos.AttendanceTeam;
import com.newage.iep.pojos.AttendenceOrg;
import com.newage.iep.pojos.Organization;
import com.newage.iep.serivce.account.OrganizationService;
import com.newage.iep.serivce.checkwork.AttendenceOrgService;
import com.newage.iep.serivce.checkwork.CheckWorkService;
import com.newage.iep.util.page.Page;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONObject;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by a1996_000 on 2017/9/6.
 */
//考勤组管理
public class CheckWorkAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
    //注入考勤组业务层
    @Autowired
    @Qualifier("checkWorkService")
    CheckWorkService checkWorkService;
    //部门业务层
    @Autowired
    @Qualifier("organizationService")
    OrganizationService organizationService;
    //部门组织业务层
    @Autowired
    @Qualifier("attendenceOrgService")
    AttendenceOrgService attendenceOrgService;
    private String result;
    HttpServletRequest request;
    HttpServletResponse response;
    //到达考勤组页面
    public String toCheckWork(){
        //查询出所有的考勤组信息
        List checkWorks = checkWorkService.selectAllCheckInfo();
        //分页
        Page page = new Page();
        int totalRecord = checkWorks.size();
        int pageSize = 10;
        //获取当前页
        String pno1 = (String)request.getParameter("pno");

        int pno = 1;
        if(pno1!=null){
            pno = Integer.parseInt(pno1);
        }
        String pagesize1 = (String)request.getParameter("pagesize");
        System.out.println("pagesize is " + pagesize1);
        if(pagesize1!=null){
            pageSize = Integer.parseInt(pagesize1);
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
        request.setAttribute("page", page);
        //分页需要的起始与终止位置
        int start = (pno-1)* pageSize;
        int end = pageSize;
        page.setStart(start);
        page.setEnd(end);
        //依据分页查询出条件  分页后的考勤组
        List<AttendanceTeam> checkWorkPages = checkWorkService.selectcheckWorkPages(page);
        //返回考勤信息
        request.setAttribute("checkWorks",checkWorkPages);
        request.setAttribute("pageSize",pageSize);
        return "toCheckWork";
    }
    //到新增考勤组页面
    public String addCheckWork(){
        //查询出所有的组织
        List<Organization> organizations = organizationService.searchInfor();
        request.setAttribute("organizations",organizations);
        return "addCheckWork";
    }
    //返回到考勤组页面
    public String backToCheckWork(){

        //将数据存储在map里，再转换成json类型数据，也可以自己手动构造json类型数据
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("info", "返回考勤组页面");
        JSONObject json = JSONObject.fromObject(map);//将map对象转换成json类型数据
        result = json.toString();//给result赋值，传递给页面
        return "backToCheckWork";
    }
    /**
     * 删除考勤组记录
     */
    public String delCheckWorkInfo(){
        String checkWorkId = (String)request.getParameter("checkid");
        //根据id删除考勤组信息
        checkWorkService.delCheckWork(checkWorkId);
        return "delCheckWorkInfo";
    }

    /**
     * 新增考勤组信息
     * @param
     */
    public String saveCheckWork(){
        String checkworkname = (String)request.getParameter("checkworkname");
        String descriptions = (String)request.getParameter("descriptions");
        String users = (String)request.getParameter("users");
        String state = (String)request.getParameter("state");
        AttendanceTeam attendanceTeam = new AttendanceTeam();
        attendanceTeam.setDecriptions(descriptions);
        attendanceTeam.setIncludeusers(users);
        attendanceTeam.setStatus(Integer.parseInt(state));
        attendanceTeam.setRolename(checkworkname);
        Date date = new Date();
        attendanceTeam.setCreatetime(date);
        attendanceTeam.setUpdatetime(date);
        //保存考勤组信息
        checkWorkService.saveCheckWork(attendanceTeam);
        //保存考勤组与组织多对多关系
        //AttendenceOrg attendenceOrg = new AttendenceOrg();
        //获取当前考勤组id
        String attendenceId = checkWorkService.selectAttendencrIdByName(checkworkname);
        //System.out.println("attendenceId    " + attendenceId);
        //获取考勤组包含的组织信息
        String[] strs = request.getParameterValues("of");
        for(String orgId:strs){
            //System.out.println("12354    ：" + s);
            AttendenceOrg attendenceOrg = new AttendenceOrg();
            attendenceOrg.setAttenddenceid(attendenceId);
            attendenceOrg.setOrgid(orgId);
            attendenceOrgService.saveAttendenceOrg(attendenceOrg);
        }
        return "saveCheckWork";
    }
    /**
     * 到达编辑页面
     */
    public String editCheckWork(){
        String checkWorkId = (String)request.getParameter("checkWorkId");
        //System.out.println("checkWorkId     :" + checkWorkId);
        //通过考勤组id查询出考勤组信息用于回显
        AttendanceTeam attendanceTeam = checkWorkService.selectCheckWorkById(checkWorkId);
        //查询出所有的组织
        List<Organization> organizations = organizationService.searchInfor();
        //处理所属组织的回显  该考勤组所属的组织
        List<AttendenceOrg> attendenceOrgs = attendenceOrgService.selectAllOrgsBelongAttendence(checkWorkId);
        request.setAttribute("organizations",organizations);
        request.setAttribute("attendanceTeam",attendanceTeam);
        request.setAttribute("attendenceOrgs",attendenceOrgs);
        return "editCheckWork";
    }

    /**
     * 修改考勤组信息
     * @param
     */
    public String updateCheckWork(){
        String checkworkid = (String)request.getParameter("checkworkid");
        String checkworkname = (String)request.getParameter("checkworkname");
        String descriptions = (String)request.getParameter("descriptions");
        String users = (String)request.getParameter("users");
        String state = (String)request.getParameter("state");
        AttendanceTeam attendanceTeam = checkWorkService.selectCheckWorkById(checkworkid);
        attendanceTeam.setIncludeusers(users);
        attendanceTeam.setDecriptions(descriptions);
        attendanceTeam.setRolename(checkworkname);
        attendanceTeam.setStatus(Integer.parseInt(state));
        checkWorkService.updateAttendanceTeam(attendanceTeam);
        return "updateCheckWork";
    }
    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
