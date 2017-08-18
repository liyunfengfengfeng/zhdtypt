package com.newage.iep.action.account;

import com.newage.iep.form.SimplePersonInfoForm;
import com.newage.iep.pojos.Account;
import com.newage.iep.pojos.Personnel;
import com.newage.iep.serivce.account.AccountService;
import com.newage.iep.serivce.account.PersonnelService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.runqian.report4.ide.base.FuncManager.getFileName;

/**
 * Created by a1996_000 on 2017/8/16.
 */
//修改信息
public class ModifyAction extends ActionSupport implements ModelDriven<SimplePersonInfoForm>,ServletRequestAware,ServletResponseAware {
    private SimplePersonInfoForm simplePersonInfoForm = new SimplePersonInfoForm();
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
    //上传头像
    //获取上传文件,名称必须和表单file控件名相同
    private File pic;
    //获取上传文件名,命名格式：表单file控件名+FileName(固定)
    private String picFileName;
    //获取上传文件类型,命名格式：表单file控件名+ContentType(固定)
    private String uploadfileContentType;

    /**
     * 修改账户信息
     * @return
     */
    public String modifyPwd(){
        System.out.println("成功修改密码"+email1 + ":" + password + ":" + repassword);
        //根据邮箱获取当前登录账户
        Account account = accountService.queryAccountByEmail(email1);
        account.setPassword(password);
        //更新账户信息
        accountService.updateAccountInfo(account);
        //根据邮箱获取当前人员信息
        Personnel personnel = personnelService.queryPersonnelByEmail(email1);
        personnel.setPassword(password);
        personnelService.updatePersonnelInfo(personnel);
        //邮箱回显
        //从session中获取邮箱信息
        HttpSession session = ServletActionContext.getRequest().getSession();
        String email = (String)session.getAttribute("user_email");
        request.setAttribute("email",email);
        return "modifyPwd";
    }
    /**
     * 人员信息页面更新人员信息的操作
     */
    public String updatePersonInfo(){
        System.out.println("更新人员简单信息");
        System.out.println("处理用户头像的上传");
        System.out.println("fileName:"+getFileName());
        //System.out.println("contentType:"+getUploadImageContentType());
        //System.out.println("File:"+getPic());

        //获取要保存文件夹的物理路径(绝对路径)
        String realPath=ServletActionContext.getServletContext().getRealPath("G:/workspace/zhdtypt/WebRoot/static/picture");
       // File file = new File(realPath);

        //测试此抽象路径名表示的文件或目录是否存在。若不存在，创建此抽象路径名指定的目录，包括所有必需但不存在的父目录。
//        if(!file.exists())file.mkdirs();
//
//        try {
//            //保存文件
//            FileUtils.copyFile(uploadImage, new File(file,uploadImageFileName));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //姓名 身份证号 性别 出生日期
        //从session中获取邮箱信息
        HttpSession session = ServletActionContext.getRequest().getSession();
        String email = (String)session.getAttribute("user_email");
        //更新人员信息
        Personnel personnel = personnelService.queryPersonnelByEmail(email);
        personnel.setBath(simplePersonInfoForm.getBirth());
        personnel.setName(simplePersonInfoForm.getName());
        personnel.setSex(simplePersonInfoForm.getSex());
        personnel.setId_no(simplePersonInfoForm.getIdnumber());
        personnelService.updatePersonnelInfo(personnel);
        //更新账户信息
        Account account = accountService.queryAccountByEmail(email);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟
        String dstr=simplePersonInfoForm.getBirth();
        try {
            Date date=sdf.parse(dstr);
            account.setBath(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        account.setSex(simplePersonInfoForm.getSex());
        account.setId_number(simplePersonInfoForm.getIdnumber());
        account.setName(simplePersonInfoForm.getName());
        accountService.updateAccountInfo(account);
        return "updatePersonInfo";
    }
    public String updateMorePersonInfo(){
        System.out.println("更新人员详细信息");
        //从session中获取邮箱信息
        HttpSession session = ServletActionContext.getRequest().getSession();
        String email = (String)session.getAttribute("user_email");
        //更新人员信息
        Personnel personnel = personnelService.queryPersonnelByEmail(email);
        personnel.setUser_code(simplePersonInfoForm.getUser_code());//用户编号
        personnel.setArea_code(simplePersonInfoForm.getArea_code());//地区编号
        personnel.setPost_address(simplePersonInfoForm.getPost_address());//籍贯
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟
        System.out.println("simplePersonInfoForm.getArmy_date() 入伍时间  ：" + simplePersonInfoForm.getArmy_date());
        String dstr=simplePersonInfoForm.getArmy_date();
        personnel.setArmy_date(dstr);//入伍时间
        personnel.setPolitical_status(simplePersonInfoForm.getPolitical_status());//政治面貌

        String dstr1=simplePersonInfoForm.getParty_date();
        personnel.setParty_date(dstr1);//入党时间
        personnel.setPassport_no(simplePersonInfoForm.getPassport_no());//护照号码

        String dstr2=simplePersonInfoForm.getMaster_date();
        personnel.setMaster_date(dstr2);//任职时间
        personnel.setPost(simplePersonInfoForm.getPost());//岗位

        String dstr3=simplePersonInfoForm.getOperate_date();
        personnel.setOperate_date(dstr3);//工作时间
        //所属单位 职称 评定时间 专业特长 外语 原工作单位 在职情况 婚否 配偶姓名 配偶工作单位 配偶电话
        personnel.setBelong_cmp(simplePersonInfoForm.getBelong_cmp());
        personnel.setTechnical_title(simplePersonInfoForm.getTechnical_title());
        String dstr4=simplePersonInfoForm.getRate_time();
        personnel.setRate_time(dstr4);//评定时间

        personnel.setProfessional(simplePersonInfoForm.getProfessional());
        personnel.setForeign_language(simplePersonInfoForm.getForeign_language());
        personnel.setPost_cmp(simplePersonInfoForm.getPost_cmp());
        System.out.println("在职情况输入的是" + simplePersonInfoForm.getPost_duty());
        personnel.setPost_duty(simplePersonInfoForm.getPost_duty());
        personnel.setMarry(simplePersonInfoForm.getMarry());
        personnel.setMarry_name(simplePersonInfoForm.getMarry_name());
        personnel.setMarry_cmp(simplePersonInfoForm.getMarry_cmp());
        personnel.setMarry_tel(simplePersonInfoForm.getMarry_tel());
        //退休时间 退休职务 职系地址 离职日期 邮政编码 状态 档案编号 人员类型 电话号 邮箱 年龄 工作 地址 教育情况 创建日期 审核人 修改人 修改日期 备注

        String dstr5=simplePersonInfoForm.getRetire_date();
        personnel.setRetire_date(dstr5);//退休时间

        personnel.setRetire_duty(simplePersonInfoForm.getRetire_duty());
        personnel.setCon_Address(simplePersonInfoForm.getCon_address());
        //System.out.println("当前输入的离职日期是 " + simplePersonInfoForm.getLeave_date());
        String dstr6=simplePersonInfoForm.getLeave_date();
        personnel.setLeave_date(dstr6);//离职日期

        personnel.setPost_code(simplePersonInfoForm.getPost_code());
        personnel.setStatu(simplePersonInfoForm.getStatus());
        personnel.setFile_no(simplePersonInfoForm.getFile_no());
        personnel.setUser_type(simplePersonInfoForm.getUser_type());
        personnel.setPhone(simplePersonInfoForm.getPhone());
        personnel.setMail(simplePersonInfoForm.getMail());
        personnel.setAge(Integer.parseInt(simplePersonInfoForm.getAge()));
        personnel.setJob(simplePersonInfoForm.getJob());
        personnel.setAddress(simplePersonInfoForm.getAddress());
        personnel.setEducation(simplePersonInfoForm.getEducation());
        personnel.setCreate_by(simplePersonInfoForm.getCreate_by());
        personnel.setModity_by(simplePersonInfoForm.getModity_by());
        personnel.setRmk(simplePersonInfoForm.getRmk());
        String dstr7=simplePersonInfoForm.getCreate_date();
        personnel.setCreate_date(dstr7);//创建日期

        String dstr8=simplePersonInfoForm.getModity_date();
        personnel.setModity_date(dstr8);//修改日期
        personnelService.updatePersonnelInfo(personnel);
        return "updatePersonInfo";
    }
    //-------------------------访问servletAPI-----------------------------------------------
    @Override
    public SimplePersonInfoForm getModel() {
        return simplePersonInfoForm;
    }
    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }
    //--------------------------getter/setter-------------------------------------------------


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


}
