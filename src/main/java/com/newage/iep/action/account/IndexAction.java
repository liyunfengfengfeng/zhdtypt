package com.newage.iep.action.account;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by a1996_000 on 2017/8/14.
 */
//到达首页之后的操作
public class IndexAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
    HttpServletRequest request;
    HttpServletResponse response;

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
        return "toAccount";
    }
    /**
     * 到达人员信息页面
     * @return
     */
    public String toInfor(){
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
