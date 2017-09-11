package com.newage.iep.action.account;

import com.newage.iep.pojos.account.Account;
import com.newage.iep.pojos.account.Personnel;
import com.newage.iep.serivce.account.AccountService;
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
 * Created by a1996_000 on 2017/8/19.
 */
public class ModifyPwdAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
    HttpServletRequest request;
    HttpServletResponse response;
    private String password;
    private String repassword;
    //注入账户业务层
    @Autowired
    @Qualifier("accountService")
    AccountService accountService;
    //人员信息业务层
    @Autowired
    @Qualifier("personnelService")
    PersonnelService personnelService;
    /**
     * 修改账户密码
     * @return
     */
    public String modifyAccountPwd(){
        System.out.println("=========================================="+password+"  " + repassword);
        //从session中获取邮箱信息
        HttpSession session = ServletActionContext.getRequest().getSession();
        String email1 = (String)session.getAttribute("user_email");
        Account account = accountService.queryAccountByEmail(email1);
        account.setPassword(password);
        //更新账户信息
        accountService.updateAccountInfo(account);
        //根据邮箱获取当前人员信息
        Personnel personnel = personnelService.queryPersonnelByEmail(email1);
        personnel.setPassword(password);
        personnelService.updatePersonnelInfo(personnel);
        //邮箱回显

        request.setAttribute("email",email1);
        return "modifyAccountPwd";
    }
    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }
}
