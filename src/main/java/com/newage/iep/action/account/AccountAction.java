package com.newage.iep.action.account;

import com.newage.iep.pojos.Account;
import com.newage.iep.pojos.Personnel;
import com.newage.iep.serivce.account.AccountService;
import com.newage.iep.serivce.account.OrganizationService;
import com.newage.iep.serivce.account.PersonnelService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONObject;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by a1996_000 on 2017/8/10.
 */
//用户操作
public class AccountAction extends ActionSupport implements ModelDriven<Account>,ServletRequestAware,ServletResponseAware{

    //账户对象
    private Account account = new Account();
    //人员信息对象
    //private Personnel personnel = new Personnel();
    //注入账户业务层
    @Autowired
    @Qualifier("accountService")
    AccountService accountService;
    //组织业务层
    @Autowired
    @Qualifier("organizationService")
    OrganizationService organizationService;
    HttpServletRequest request;
    HttpServletResponse response;
    //邮箱是否已经被注册的校验信息
    private String result;
    //所有的组织
    List orgs;
    //组织代号
    private String cmp_code;
    @Autowired
    @Qualifier("personnelService")
    PersonnelService personnelService;
    //注册页面需要的数据
    private String email;
    private String vericode;
    private String password;
    private String repassword;
    private String name;
    private String idCard;
    private String sex;
    private String birth;
    private String organize;
    /**
     * 跳转到注册页面
     * @return
     */
    public String toRegister(){
        //查询出所有的组织
        List orgs = organizationService.queryAllOrgs();
        request.setAttribute("orgs",orgs);
        return "register";
    }
    /**
     * 跳转至登录页面
     * @return
     */
    public String toLogin(){
        return "login";
    }
    /**
     * 检查用户输入的邮箱是否已经被注册过
     */
    public String checkEmail(){
        String email = (String)request.getParameter("email");
        boolean flag = accountService.queryEmail(email);
        //邮箱已经被注册过了 flag设置为true
        if(flag){
            //将数据存储在map里，再转换成json类型数据，也可以自己手动构造json类型数据
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("flag", true);
            JSONObject json = JSONObject.fromObject(map);//将map对象转换成json类型数据
            result = json.toString();//给result赋值，传递给页面
        }
        return Action.SUCCESS;
    }

    /**
     * 用户注册
     */
    public String register(){
        //检查用户输入的组织代号是否为空
        if(cmp_code.equals("")){
            this.addFieldError("cmpCodeError", "组织代号不能为空");
            return "register";
        }
        //检查用户输入的组织代号是否有效
        boolean cmpCode = organizationService.checkCmpCode(cmp_code);

        if(!cmpCode){
            this.addFieldError("cmpCodeError", "组织代号不存在");
            return "register";
        }

        //注册时注册  创建日期
        Date date = new Date();
        account.setCreate_date(date);
        //状态设为0未审核1审核通过2审核未通过
        account.setStatus(0L);
        //注册账户信息
        boolean flag = accountService.register(account);
        Personnel personnel = new Personnel();
        personnel.setName(account.getName());
        personnel.setSex(account.getSex());
        personnel.setId_no(account.getId_number());
        personnel.setBath(account.getBath());//待转化
        personnel.setMail(account.getEmail());
        Date date1 = new Date();
        personnel.setCreate_date(date1);
        //注册人员信息
        personnelService.registerPersonnelInfo(personnel);
        if(flag){
            return "login";
        }else{
            return "register";
        }
    }

    /**
     * 注册新用户
     * @return
     */
    public String registerNewUser(){
        System.out.println("=============================================================================");
        System.out.println(getEmail()+" : "+getPassword());
        return "login";
    }
    //返回表单模型
    @Override
    public Account getModel() {
        return account;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public AccountService getAccountService() {
        return accountService;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    public List getOrgs() {
        return orgs;
    }

    public void setOrgs(List orgs) {
        this.orgs = orgs;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getCmp_code() {
        return cmp_code;
    }

    public void setCmp_code(String cmp_code) {
        this.cmp_code = cmp_code;
    }



    //设置request
    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }
    //设置response
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

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getOrganize() {
        return organize;
    }

    public void setOrganize(String organize) {
        this.organize = organize;
    }
}