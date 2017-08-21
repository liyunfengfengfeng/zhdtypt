package com.newage.iep.action.menu;

import com.newage.iep.pojos.Account;
import com.newage.iep.pojos.Menu;
import com.newage.iep.serivce.account.AccountService;
import com.newage.iep.serivce.menu.AccountRoleService;
import com.newage.iep.serivce.menu.MenuService;
import com.newage.iep.serivce.menu.RoleMenuService;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by a1996_000 on 2017/8/21.
 */

/**
 * 菜单控制层
 */
public class MenuAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
    HttpServletRequest request;
    HttpServletResponse response;
    //注入账户业务层
    @Autowired
    @Qualifier("accountService")
    AccountService accountService;
    @Autowired
    @Qualifier("accountRoleService")
    AccountRoleService accountRoleService;
    @Autowired
    @Qualifier("roleMenuService")
    RoleMenuService roleMenuService;
    @Autowired
    @Qualifier("menuService")
    MenuService menuService;
    //返回json数据
    private String result;
    /**
     * 动态查询菜单 不同用户由于角色不同产生不同的菜单
     * @param
     */
    public String queryAccountMenus(){
        System.out.println("动态查询菜单 不同用户由于角色不同产生不同的菜单");
        //从session中获取邮箱信息
        HttpSession session = ServletActionContext.getRequest().getSession();
        String email = (String)session.getAttribute("user_email");
        //查询出当前登录用户
        Account account = accountService.queryAccountByEmail(email);
        //System.out.println(account.getAccount_0_id());
        //查询出当前登录用户的所有角色
        if(account!=null) {
            List roles = accountRoleService.queryAllRoles(account.getAccount_0_id());//返回所有角色id
            //根据角色id查询出所有的根菜单  1级菜单
            List root_menus_ids = roleMenuService.queryAllRootMenuIds(roles);
            //根据跟菜单id查询所有的跟菜单
            List root_menus = menuService.queryFirstMenus(root_menus_ids);
            System.out.println("root_menus is   :" + root_menus);
            //根据跟菜单查询出所有子菜单    2级菜单
            List child_menus = menuService.querySecondMenus();
            System.out.println("子菜单是   :" + child_menus);
            //System.out.println("该用户根菜单的数量是  :" + root_menus.size());
            for (Object obj:root_menus) {
                Menu parentmenu = (Menu)obj;
                List<Menu> childList = new ArrayList<>();
                for (Object obj1:child_menus) {
                    Menu childmenu = (Menu)obj1;
                    if (parentmenu.getMenu_id().equals(childmenu.getParent_id())){
                        childList.add(childmenu);
                    }
                }
                //childList.sort(new MenuComparator());
                parentmenu.setChildList(childList);

            }
            Map<String,Object> map1 = new HashMap<String,Object>();
            map1.put("menus", root_menus);//用户名密码错误
            JSONObject json = JSONObject.fromObject(map1);//将map对象转换成json类型数据
            result = json.toString();//给result赋值，传递给页面
        }else{
            System.out.println("account is null");
        }

        return "queryAccountMenus";
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
