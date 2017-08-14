package com.newage.iep.action.account;

import com.newage.iep.serivce.account.AccountService;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONObject;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by a1996_000 on 2017/8/14.
 */
public class LoginAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
    HttpServletRequest request;
    HttpServletResponse response;
    private String email;
    private String password;
    private String result = "";
    //注入账户业务层
    @Autowired
    @Qualifier("accountService")
    AccountService accountService;
    /**
     * 处理用户登录
     */
    public String login(){
        System.out.println("登录输入的邮箱是 " + email);
        System.out.println("登录输入的密码是 " + password);
        //检查用户名与密码是否匹配
        boolean flag = accountService.checkLogin(email,password);
        //将数据存储在map里，再转换成json类型数据，也可以自己手动构造json类型数
        Map<String,Object> map1 = new HashMap<String,Object>();
        if(flag==false){
            map1.put("state", -1);//用户名密码错误
            JSONObject json = JSONObject.fromObject(map1);//将map对象转换成json类型数据
            result = json.toString();//给result赋值，传递给页面
        }else{
            String status = accountService.getStatus(email);//查询状态码
            //数据库中0代表未审核1代表审核通过2代表审核未通过
            int state = Integer.parseInt(status);
            if(state==0){
                map1.put("state", 0);//用户信息未审
                JSONObject json = JSONObject.fromObject(map1);//将map对象转换成json类型数据
                result = json.toString();//给result赋值，传递给页面
            }else if(state==1){//审核通过可以登录
                map1.put("state", 1);//审核通过可以登录
                JSONObject json = JSONObject.fromObject(map1);//将map对象转换成json类型数据
                result = json.toString();//给result赋值，传递给页面
            }else if(state==2){//审核未通过
                map1.put("state", 2);//审核未通过
                JSONObject json = JSONObject.fromObject(map1);//将map对象转换成json类型数据
                result = json.toString();//给result赋值，传递给页面
            }

        }
        return "login";
    }
    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }
    //---------------------------------------getter/setter-----------------------------------------------

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
