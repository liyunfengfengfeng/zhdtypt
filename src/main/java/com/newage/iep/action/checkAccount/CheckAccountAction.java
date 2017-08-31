package com.newage.iep.action.checkAccount;

/**
 * Created by qq101 on 2017/8/14.
 */

import com.newage.iep.pojos.Account;
import com.newage.iep.pojos.Role;
import com.newage.iep.serivce.checkAccount.CheckAccountService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONArray;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by a1996_000 on 2017/8/12.
 */
//人员信息
public class CheckAccountAction extends ActionSupport implements ModelDriven<Account>, ServletRequestAware,ServletResponseAware {
    HttpServletRequest request;
    HttpServletResponse response;

    String result=null;
    String result2=null;

    private Account tAccountEntity=new Account();
    //注入人员信息

    @Autowired
    @Qualifier("checkAccountService")
    CheckAccountService checkAccountService;

    /**
     * 查询所有status=0的账户  到达审核账户页面
     * @param
     */
    public String queryByStatus(){
        List<Account> list= checkAccountService.queryAccountByStatus();

        //-------添加角色查询
        List<Role> list2= checkAccountService.queryRoleByLevel("10");

        request.setAttribute("checkAccountList",list);
        request.setAttribute("roleList",list2);
        return "checkAccountList";
    }

    /**
     * 查询对应邮箱的账户 即待审核的用户的详细信息进行显示
     * @param
     */
    @ResponseBody
    public String  queryPersons() throws IOException {
        String mail=(String)request.getParameter("param");
        //----test

        List<Account> list=new ArrayList<Account>();
        //对用户的信息通过邮箱进行查询
        list= checkAccountService.queryPersonByMail(mail);

        result = JSONArray.fromObject(list).toString();


        return "queryString";
    }

    /**
     * 为用户分配角色
     * @return
     * @throws IOException
     */
    public String writeToDataBaseFor1() throws IOException {

        String data=(String)request.getParameter("data");
        String [] strArray=checkAccountService.parseForArray(data);
        try {
            for (int i = 1; i < strArray.length; i++) {
                checkAccountService.allotRoleForAccount(strArray[0], strArray[i]);
            }
            return "1success";//分配成功
        }catch (Exception e){
            e.printStackTrace();
            return "1failed";//分配失败
        }

    }

    /**
     * 审核未通过
     * @return
     * @throws IOException
     */
    public String writeToDataBaseFor2() throws IOException {

        String data=(String)request.getParameter("data");
        System.out.println("writeToDataBaseFor2-----"+data);
        try {
            checkAccountService.updateAccountStatusTo2(data);
            return "2success";
        }catch (Exception e){
            e.printStackTrace();
            return "2failed";
        }
    }


    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    /**
     * 模型驱动
     * @return
     */
    @Override
    public Account getModel() {
        return tAccountEntity;
    }
}
