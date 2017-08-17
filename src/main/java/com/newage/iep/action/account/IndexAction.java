package com.newage.iep.action.account;

import com.newage.iep.pojos.Personnel;
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
    /**
     * 跳转到首页
     * @return
     */
    public String index(){
        //System.out.println("===============================================================================");
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

        //System.out.println(personnel.getMail()+"人员信息要展示的邮箱"+personnel.getPost_duty());


        request.setAttribute("personnel",personnel);
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
