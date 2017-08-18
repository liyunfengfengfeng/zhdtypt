package com.newage.iep.action.account;


import com.newage.iep.form.RegisterForm;
import com.newage.iep.pojos.Account;
import com.newage.iep.pojos.Personnel;
import com.newage.iep.serivce.account.AccountService;
import com.newage.iep.serivce.account.OrganizationService;
import com.newage.iep.serivce.account.PersonnelService;
import com.newage.iep.util.MailUtil;
import com.newage.iep.util.VericodeUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
    //注入组织业务层
    @Autowired
    @Qualifier("organizationService")
    OrganizationService organizationService;

    //获取json 传输json
    private String result = "";
    private String email;
    private String organize;
    //注册页面需要的数据
    //private String email;
    private String vericode;
    private String password;
    private String repassword;
    private String name;
    private String idCard;
    private String sex;
    private String birth;
    //private String organize;

    /**
     * 注册用户
     * @return
     */
    public String registerUser(){
        System.out.println("开始了用户的注册==============================================");
        System.out.println(getName()+getPassword()+getSex());
        System.out.println(getIdCard()+getOrganize()+getVericode());
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
        account.setName(getName());
        account.setSex(getSex());
        account.setId_number(getIdCard());
        account.setCmp_id(getOrganize());//设置组织id
        //account.setBath(registerForm.getBirth());
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟
        String dstr = getBirth();
        try {
            if(dstr!=null){

                Date date1=sdf.parse(dstr);
                account.setBath(date1);
                personnel.setBath(dstr);//待转化
            }


                // }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        account.setEmail(getEmail());
        account.setPassword(getPassword());
        boolean flag = accountService.register(account);
        //注册人员信息
        personnel.setName(account.getName());
        personnel.setSex(account.getSex());
        personnel.setId_no(account.getId_number());
        personnel.setMail(account.getEmail());
        personnel.setBelong_cmp(account.getCmp_id());
        personnel.setPassword(account.getPassword());

        personnel.setPicture_path("/static/img/pic.png");
        //状态设为0未审核1审核通过2审核未通过
        personnel.setStatu(String.valueOf(0));
        //注册人员信息
        personnelService.registerPersonnelInfo(personnel);
        if(flag){//注册成功
            //将数据存储在map里，再转换成json类型数据，也可以自己手动构造json类型数据
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("flag", 1);
            JSONObject json = JSONObject.fromObject(map);//将map对象转换成json类型数据
            result = json.toString();//给result赋值，传递给页面
            return "returnlogin";//返回登陆页
        }else{
            //将数据存储在map里，再转换成json类型数据，也可以自己手动构造json类型数据
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("flag", 0);
            JSONObject json = JSONObject.fromObject(map);//将map对象转换成json类型数据
            result = json.toString();//给result赋值，传递给页面
            return "returnregister";//返回注册页
        }
    }

    /**
     * 校验用户邮箱是否已经被注册
     * @param
     */
    public String checkEmail(){
        //将数据存储在map里，再转换成json类型数据，也可以自己手动构造json类型数
        Map<String,Object> map1 = new HashMap<String,Object>();
        //System.out.println("要进行检验是否注册的邮箱"+getEmail()+"=======================================================");
        boolean flag = accountService.queryEmail(getEmail());
        // true 邮箱被注册过了
        if(flag){
            map1.put("state", 0);//
            JSONObject json = JSONObject.fromObject(map1);//将map对象转换成json类型数据
            result = json.toString();//给result赋值，传递给页面
        }else{
            map1.put("state", 1);//
            JSONObject json = JSONObject.fromObject(map1);//将map对象转换成json类型数据
            result = json.toString();//给result赋值，传递给页面
        }

        return "checkEmail";
    }

    /**
     * 检查用户输入的组织是否有效
     * @param
     */
    public String checkOrg(){
        boolean flag = organizationService.checkCmpCode(getOrganize());
        Map<String,Object> map2 = new HashMap<String,Object>();
        // true 组织代号是有效的
        if(flag){
            map2.put("state", 1);
            JSONObject json = JSONObject.fromObject(map2);//将map对象转换成json类型数据
            result = json.toString();//给result赋值，传递给页面
        }else{
            map2.put("state", 0);//组织代号无效
            JSONObject json = JSONObject.fromObject(map2);//将map对象转换成json类型数据
            result = json.toString();//给result赋值，传递给页面
        }
        return "checkOrg";
    }

    /**
     * 发送验证码
     * @return
     */
    public String sendEmail(){
        System.out.println("发送验证码");
        System.out.println("发送验证码到邮箱 " + getEmail());
        int code = VericodeUtil.getRandNum();//生成验证码
        System.out.println(code);
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.setAttribute("code",code);//存储验证码到session中
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
     * 对验证码进行校验
     * @return
     */
    public String checkVericode(){
        System.out.println("用户输入的验证码是 ：" + getVericode());
        HttpSession session = ServletActionContext.getRequest().getSession();
        Object code = session.getAttribute("code");//获取session中的验证码
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
    //-----------------------------------------------------访问servlet api--------------------------------------------------------------------
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

    public String getOrganize() {
        return organize;
    }

    public void setOrganize(String organize) {
        this.organize = organize;
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
}
