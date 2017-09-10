package com.newage.iep.action.role;


import com.newage.iep.pojos.Menu;
import com.newage.iep.pojos.Personnel;
import com.newage.iep.pojos.Role;
import com.newage.iep.serivce.Role.RoleService;
import com.newage.iep.serivce.account.PersonnelService;
import com.newage.iep.serivce.checkAccount.CheckAccountService;
import com.newage.iep.serivce.menu.MenuService;
import com.newage.iep.serivce.menu.RoleMenuService;
import com.newage.iep.util.page.Page;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by a1996_000 on 2017/9/9.
 */
public class RoleAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
    @Autowired
    @Qualifier("personnelService")
    PersonnelService personnelService;

    @Autowired
    @Qualifier("roleService")
    RoleService roleService;

    @Autowired
    @Qualifier("menuService")
    MenuService menuService;

    @Autowired
    @Qualifier("checkAccountService")
    CheckAccountService checkAccountService;

    @Autowired
    @Qualifier("roleMenuService")
    RoleMenuService roleMenuService;

    HttpServletRequest request;
    HttpServletResponse response;
    String result;
    /**
     * 到达角色创建页面
     * @return
     */
    public String toRoleCreate(){

        return "toRoleCreate";
    }
    /**
     * 保存角色信息
     */
    public String saveRoleInfo(){
        String roleName = (String)request.getParameter("roleName");
        String roleDescriptions = (String)request.getParameter("roleDescriptions");
        Date date = new Date();
        Role role = new Role();
        role.setRolename(roleName);
        role.setRmk(roleDescriptions);
        role.setCreatedate(date);
        role.setModifydate(date);
        //获取当前登录用户
        HttpSession session = ServletActionContext.getRequest().getSession();
        String email = (String)session.getAttribute("user_email");
        Personnel personnel = personnelService.queryPersonnelByEmail(email);
        role.setCreateby(personnel.getName());
        //role.setModify_by(personnel.getName());
        role.setModifyby(personnel.getName());
        role.setRole_level("10");
        roleService.saveRoleInfo(role);
        return "toRoleCreate";
    }

    /**
     * 到达为角色分配权限页面
     * @param
     */
    public String toRoleDistribute(){
        //查出所有的角色
        List<Role> roles = roleService.selectAllRoles();
        Page page = new Page();
        int totalRecord = roles.size();
        int pageSize = 4;
        //获取当前页
        String pno1 = (String)request.getParameter("pno");
        int pno = 1;
        if(pno1!=null){
            pno = Integer.parseInt(pno1);
        }
        //设置当前页
        page.setPageNo(pno);
        //每页容量
        page.setPageSize(pageSize);
        //总页数
        int totalPageNum = (totalRecord  +  pageSize  - 1) / pageSize;
        page.setTotalPage(totalPageNum);
        //总记录数
        page.setTotalRecord(totalRecord);
        request.setAttribute("page", page);
        //分页需要的起始与终止位置
        int start = (pno-1)* pageSize;
        int end = pageSize;
        page.setStart(start);
        page.setEnd(end);
        //依据分页查询出条件
        List<Role> rolePages = roleService.selectRolesByPage(page);
        request.setAttribute("roles",rolePages);
//        查询出所有一级菜单
        List<Menu> menus = menuService.selectAllRootMenus();
        //查询出所有角色信息
        request.setAttribute("menus",menus);
        return "roleDistribute";
    }
    /**
     * 查询角色详细信息
     */
    public String queryRoleInfo(){
        //获取要查询角色信息的id
        String roleId = (String)request.getParameter("param");
        //查询角色信息
        Role role = roleService.selectRoleById(roleId);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("role", role);
        JSONObject json = JSONObject.fromObject(map);//将map对象转换成json类型数据
        result = json.toString();//给result赋值，传递给页面
        return "queryRoleInfo";
    }

    /**
     * 为角色分配权限
     * @return
     */
    public String completeRoleDistribute(){
        String data=(String)request.getParameter("data");
        String [] strArray=checkAccountService.parseForArray(data);
        //删除该角色具有的所有权限
        roleMenuService.deleteAllMenus(strArray[0]);
        try {
            for (int i = 1; i < strArray.length; i++) {
                roleMenuService.allotMenuForRole(strArray[0], strArray[i]);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "completeRoleDistribute";
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
}
