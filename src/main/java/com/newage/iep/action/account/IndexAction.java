package com.newage.iep.action.account;

import com.newage.iep.pojos.organization.Organization;
import com.newage.iep.pojos.account.Personnel;
import com.newage.iep.serivce.account.OrganizationService;
import com.newage.iep.serivce.account.PersonnelService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;

/**
 * Created by a1996_000 on 2017/8/14.
 */
//到达首页之后的操作
public class IndexAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
    HttpServletRequest request;
    HttpServletResponse response;
    @Autowired
    @Qualifier("personnelService")
    PersonnelService personnelService;
    @Autowired
    @Qualifier("organizationService")
    OrganizationService organizationService;
    /**
     * 跳转到首页   此时登录已经成功
     * @return
     */
    public String index(){
        //System.out.println("===============================================================================");
        //从session中获取邮箱信息
        HttpSession session = ServletActionContext.getRequest().getSession();
        String email = (String)session.getAttribute("user_email");
        Personnel personnel = personnelService.queryPersonnelByEmail(email);
        if(personnel!=null){
            request.setAttribute("name",personnel.getName());
            request.setAttribute("picture_path",personnel.getPicture_path());
        }

        return "index";
    }

    /**
     * 到达账户信息页面
     * @return
     */
    public String toAccount(){
        //从session中获取邮箱信息
        HttpSession session = ServletActionContext.getRequest().getSession();
        String email = (String)session.getAttribute("user_email");
        request.setAttribute("email",email);
        return "toAccount";
    }
    /**
     * 到达人员信息页面
     * @return
     */
    public String toInfor(){
        //从session中获取邮箱信息
        HttpSession session = ServletActionContext.getRequest().getSession();
        String email = (String)session.getAttribute("user_email");
        Personnel personnel = personnelService.queryPersonnelByEmail(email);
        //组织单位id
        String orgid = personnel.getBelong_cmp();
        //根据id查询到组织
        Organization organization = organizationService.queryOrgById(orgid);
        //组织名称 所属单位
        if(organization!=null){
            personnel.setBelong_cmp(organization.getCmp_name());
        }

        //System.out.println(personnel.getMail()+"人员信息要展示的邮箱"+personnel.getPost_duty());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = personnel.getBath();
        String birthday=sdf.format(date);
        request.setAttribute("birthday",birthday);
        //修改时间
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date1 = personnel.getModity_date();
        String modifydate=sdf1.format(date);
        request.setAttribute("modifydate",modifydate);
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date2 = personnel.getModity_date();
        if(date2!=null) {
            String createdate = sdf2.format(date2);
            request.setAttribute("createdate", createdate);
        }
        request.setAttribute("personnel",personnel);
        //设置状态
        int statu = Integer.parseInt(personnel.getStatu());
        if(statu==1){
            request.setAttribute("info","通过审核");
        }
        return "toInfor";
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