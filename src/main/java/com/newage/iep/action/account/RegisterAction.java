package com.newage.iep.action.account;


import com.newage.iep.form.RegisterForm;
import com.newage.iep.pojos.Account;
import com.newage.iep.pojos.Personnel;
import com.newage.iep.serivce.account.AccountService;
import com.newage.iep.serivce.account.PersonnelService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by a1996_000 on 2017/8/14.
 */
//处理用户的注册信息
public class RegisterAction extends ActionSupport implements ModelDriven<RegisterForm>,ServletRequestAware,ServletResponseAware {
    HttpServletRequest request;
    HttpServletResponse response;
    private RegisterForm registerForm = new RegisterForm();
    //注入账户业务层
    @Autowired
    @Qualifier("accountService")
    AccountService accountService;
    //人员信息
    @Autowired
    @Qualifier("personnelService")
    PersonnelService personnelService;
    //获取json 传输json
    private String result = "";
    private String email;
    /**
     * 注册用户
     * @return
     */
    public String registerUser(){
        //System.out.println("开始了用户的注册==============================================");
        //System.out.println(registerForm.getName()+registerForm.getPassword()+registerForm.getSex());
        //注册时注册  创建日期
        Date date = new Date();
        //创建账户
        Account account = new Account();
        //创建人员
        Personnel personnel = new Personnel();
        account.setCreate_date(date);
        //状态设为0未审核1审核通过2审核未通过
        account.setStatus(0L);
        //注册账户信息
        account.setName(registerForm.getName());
        account.setSex(registerForm.getSex());
        account.setId_number(registerForm.getIdCard());
        //account.setBath(registerForm.getBirth());
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟
        String dstr=registerForm.getBirth();
        try {
            if(dstr!=null){

                Date date1=sdf.parse(dstr);
                account.setBath(date1);
                personnel.setBath(date1.toString());//待转化
            }


                // }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        account.setEmail(registerForm.getEmail());
        account.setPassword(registerForm.getPassword());
        boolean flag = accountService.register(account);
        //注册人员信息
        personnel.setName(account.getName());
        personnel.setSex(account.getSex());
        personnel.setId_no(account.getId_number());
        personnel.setMail(account.getEmail());
        personnel.setPassword(account.getPassword());
        //状态设为0未审核1审核通过2审核未通过
        personnel.setStatu(String.valueOf(0));
        //注册人员信息
        personnelService.registerPersonnelInfo(personnel);
        if(flag){
            return "login";
        }else{
            return "register";
        }
    }

    /**
     * 校验用户邮箱是否已经被注册
     * @param
     */
    public String checkEmail(){
        //将数据存储在map里，再转换成json类型数据，也可以自己手动构造json类型数
        Map<String,Object> map1 = new HashMap<String,Object>();
        System.out.println("要进行检验是否注册的邮箱"+getEmail()+"=======================================================");


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
    //模型驱动
    @Override
    public RegisterForm getModel() {
        return registerForm;
    }
    //------------------------------------------------getter/setter--------------------------------------------------------------

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
