package com.newage.iep.action.account;

import com.newage.iep.pojos.account.Account;
import com.newage.iep.serivce.account.AccountService;
import com.newage.iep.serivce.account.PersonnelService;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by a1996_000 on 2017/8/19.
 */
public class CheckAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
    //注入账户业务层
    @Autowired
    @Qualifier("accountService")
    AccountService accountService;
    //人员信息业务层
    @Autowired
    @Qualifier("personnelService")
    PersonnelService personnelService;
    HttpServletRequest request;
    HttpServletResponse response;
    //获取修改账户信息页面表单
    private String email1;
    private String password;
    private String repassword;
    private String email;
    //用户输入的原密码
    private String oldpassword;
    //传json
    private String result;
    /**
     * 检查用户输入的原密码是否正确
     * @return
     */
    public String checkOldPwd(){
        System.out.println("用户输入的原密码是 ：" + oldpassword);
        //从session中获取邮箱信息
        HttpSession session = ServletActionContext.getRequest().getSession();
        String email = (String)session.getAttribute("user_email");
        //根据邮箱获取account
        Account account = accountService.queryAccountByEmail(email);
        Map<String, Object> map1 = new HashMap<String, Object>();
        if(account!=null&&account.getPassword().equals(oldpassword)) {
            map1.put("state", 1);//用户输入的原密码正确
            JSONObject json = JSONObject.fromObject(map1);//将map对象转换成json类型数据
            result = json.toString();//给result赋值，传递给页面
        }else{
            map1.put("state", -1);//用户输入的原密码错误
            JSONObject json = JSONObject.fromObject(map1);//将map对象转换成json类型数据
            result = json.toString();//给result赋值，传递给页面
        }
        return "checkOldPwd";
    }
    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOldpassword() {
        return oldpassword;
    }

    public void setOldpassword(String oldpassword) {
        this.oldpassword = oldpassword;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
