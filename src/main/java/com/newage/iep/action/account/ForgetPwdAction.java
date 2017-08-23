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
 * Created by a1996_000 on 2017/8/22.
 */
public class ForgetPwdAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
    //注入账户业务层
    @Autowired
    @Qualifier("accountService")
    AccountService accountService;
    HttpServletRequest request;
    HttpServletResponse response;
    private String email;
    private String result;
    /**
     * 到达忘记密码页面
     * @return
     */
    public String forgetpwd(){
        return "forgetpwd";
    }

    /**
     * 检查用户输入的找回密码的邮箱是否有效 1 有效 0无效
     * @return
     */
    public String checkEmail(){
        System.out.println("检查用户输入的找回密码的邮箱是否有效  :" +  email);
        boolean flag = accountService.checkEmail(email);//true 有效 false无效
        //将数据存储在map里，再转换成json类型数据，也可以自己手动构造json类型数据
        Map<String,Object> map = new HashMap<String,Object>();
        if(flag){
            map.put("flag",1);//有效
        }else{
            map.put("flag",0);//无效
        }
        JSONObject json = JSONObject.fromObject(map);//将map对象转换成json类型数据
        result = json.toString();//给result赋值，传递给页面
        return "checkEmail";
    }


    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }
    //----------------------------------getter/setter-----------------------------------------------------------------------

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
