package com.newage.iep.action.account;

import com.newage.iep.form.PersonnelForm;
import com.newage.iep.pojos.Personnel;
import com.newage.iep.serivce.account.PersonnelService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by a1996_000 on 2017/8/12.
 */
//人员信息
public class PersonnelAction extends ActionSupport implements ModelDriven<PersonnelForm>, ServletRequestAware,ServletResponseAware {
    HttpServletRequest request;
    HttpServletResponse response;
    private PersonnelForm personnelForm = new PersonnelForm();
    //注入人员信息
    @Autowired
    @Qualifier("personnelService")
    PersonnelService personnelService;
    /**
     * 查询所有的人员信息
     * @param
     */
    public String queryPersons(){
        String mail = "2305743208@qq.com";
        //对用户的信息进行回显
        Personnel personnel = personnelService.queryPersonByMail(mail);

        if(personnel!=null){

            request.setAttribute("personnel",personnel);
        }
        return "personInfo";
    }

    /**
     * 完善个人信息
     * @param
     */
    public String addPersonInfo(){
        Personnel personnel = new Personnel();

        //BeanUtils.copyProperties(personnel,PersonnelForm);
        personnel.setId(personnelForm.getId());
        personnel.setMail(personnelForm.getMail());
        personnel.setName(personnelForm.getName());
        personnel.setBath(personnelForm.getBath());
        personnel.setSex(personnelForm.getSex());
        personnel.setLogin_name(personnelForm.getLogin_name());
        personnelService.updatePersonnelInfo(personnel);

        return "personInfo";
    }
    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    /**
     * 模型驱动
     * @return
     */
    @Override
    public PersonnelForm getModel() {
        return personnelForm;
    }
}
