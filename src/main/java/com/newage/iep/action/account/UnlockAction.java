package com.newage.iep.action.account;

import com.newage.iep.pojos.account.Account;
import com.newage.iep.serivce.account.AccountService;
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
 * Created by a1996_000 on 2017/8/24.
 */
//屏幕解锁
public class UnlockAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
    //注入账户业务层
    @Autowired
    @Qualifier("accountService")
    AccountService accountService;
    HttpServletRequest request;
    HttpServletResponse response;
    private String password;
    private String result;
    /**
     * 解锁屏幕
     * @return
     */
    public String unlockScreen(){
        //System.out.println("用户解锁输入的密码是   :" + getPassword());
        //判断用户输入的密码是否正确
        //从session中获取邮箱信息
        HttpSession session = ServletActionContext.getRequest().getSession();
        String email1 = (String)session.getAttribute("user_email");
        Account account = accountService.queryAccountByEmail(email1);
        System.out.println(account.getPassword() + "    ====    "  + getPassword());
        if(account!=null&&account.getPassword().equals(getPassword())){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("state", 1);//用户输入的密码正确
            JSONObject json = JSONObject.fromObject(map);//将map对象转换成json类型数据
            result = json.toString();//给result赋值，传递给页面
        }else{
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("state", 0);//用户输入的密码错误
            JSONObject json = JSONObject.fromObject(map);//将map对象转换成json类型数据
            result = json.toString();//给result赋值，传递给页面
        }
        return "unlockScreen";
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
