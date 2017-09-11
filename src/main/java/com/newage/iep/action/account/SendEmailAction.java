package com.newage.iep.action.account;

import com.newage.iep.pojos.account.Account;
import com.newage.iep.pojos.account.Personnel;
import com.newage.iep.serivce.account.AccountService;
import com.newage.iep.serivce.account.PersonnelService;
import com.newage.iep.util.MailUtil;
import com.newage.iep.util.VericodeUtil;
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
 * Created by a1996_000 on 2017/8/23.
 */
public class SendEmailAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
    HttpServletRequest request;
    HttpServletResponse response;
    private String email;
    private String result;
    private String vericode;
    private String password;
    //注入账户业务层
    @Autowired
    @Qualifier("accountService")
    AccountService accountService;
    //人员信息业务层
    @Autowired
    @Qualifier("personnelService")
    PersonnelService personnelService;
    /**
     * 发送验证码到邮箱  找回密码
     * @param
     */
    public String sendEmail(){
        System.out.println("发送验证码");
        System.out.println("发送验证码到邮箱 " + getEmail());
        int code = VericodeUtil.getRandNum();//生成验证码
        System.out.println(code);
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.setAttribute("findpwd",code);//存储验证码到session中
        MailUtil mailUtil = new MailUtil();
        //mailUtil.sendMail("2305743208@qq.com", "8888");//接收方  接受码
        mailUtil.sendMail(getEmail(),String.valueOf(code));//发送验证码到邮箱
        //将数据存储在map里，再转换成json类型数据，也可以自己手动构造json类型数
        Map<String,Object> map1 = new HashMap<String,Object>();
        map1.put("state", "验证码已经发送");
        JSONObject json = JSONObject.fromObject(map1);//将map对象转换成json类型数据
        result = json.toString();//给result赋值，传递给页面
        return "sendEmail";
    }

    /**
     * 检查用户输入的验证码是否正确
     * @param
     */
    public String checkVericode(){
        System.out.println("用户输入的验证码是 ：" + getVericode());
        HttpSession session = ServletActionContext.getRequest().getSession();
        Object code = session.getAttribute("findpwd");//获取session中的验证码
        //System.out.println("session中的验证码是 ：" + code);
        Map<String,Object> map1 = new HashMap<String,Object>();
        //验证码正确
        if(code.toString().equals(getVericode())){
            System.out.println("验证码正确");
            //将数据存储在map里，再转换成json类型数据，也可以自己手动构造json类型数
            map1.put("state", 1);
            JSONObject json = JSONObject.fromObject(map1);//将map对象转换成json类型数据
            result = json.toString();//给result赋值，传递给页面
        }else{//验证码错误
            System.out.println("验证码错误");
            //将数据存储在map里，再转换成json类型数据，也可以自己手动构造json类型数
            //Map<String,Object> map1 = new HashMap<String,Object>();
            map1.put("state", 0);
            JSONObject json = JSONObject.fromObject(map1);//将map对象转换成json类型数据
            result = json.toString();//给result赋值，传递给页面
        }
        return "checkVericode";
    }

    /**
     * 用户找回密码重新输入新密码
     * @return
     */
    public String updatepassword(){
        System.out.println("用户要更新的密码是" + password);
        //从session中获取邮箱信息

        Account account = accountService.queryAccountByEmail(getEmail());
        account.setPassword(password);
        //更新账户信息
        accountService.updateAccountInfo(account);
        //根据邮箱获取当前人员信息
        Personnel personnel = personnelService.queryPersonnelByEmail(getEmail());
        personnel.setPassword(password);
        personnelService.updatePersonnelInfo(personnel);
        Map<String,Object> map1 = new HashMap<String,Object>();
        //验证码正确
        //将数据存储在map里，再转换成json类型数据，也可以自己手动构造json类型数
        map1.put("state", "成功找回密码");
        JSONObject json = JSONObject.fromObject(map1);//将map对象转换成json类型数据
        result = json.toString();//给result赋值，传递给页面

        return "updatepassword";
    }
    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

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

    public String getVericode() {
        return vericode;
    }

    public void setVericode(String vericode) {
        this.vericode = vericode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
