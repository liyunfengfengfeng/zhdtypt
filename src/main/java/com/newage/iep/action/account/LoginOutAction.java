package com.newage.iep.action.account;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by a1996_000 on 2017/8/22.
 */

/**
 * 用户的退出操作
 */
public class LoginOutAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
    HttpServletRequest request;
    HttpServletResponse response;

    /**
     * 用户退出操作
     * @return
     */
    public String quit(){
        //从session中获取邮箱信息
        HttpSession session = ServletActionContext.getRequest().getSession();
        String email1 = (String)session.getAttribute("user_email");
        if(email1 != null){
            session.removeAttribute("user_email");
        }
        return "quit";
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
