package com.newage.iep.interceptor;

/**
 * Created by a1996_000 on 2017/8/22.
 */
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import java.util.Map;

public class ChekLoginInterceptor extends AbstractInterceptor{

    public String intercept(ActionInvocation invocation) throws Exception {
        //获取ActionContext
        ActionContext ac = invocation.getInvocationContext();
        //过去session;
        Map<String, Object> session = ac.getSession();
        //获取session中的用户信息
        Object user_email = session.get("user_email");
        if (user_email != null) {
            System.out.println("当前有session");
            return invocation.invoke();
        } else {
            System.out.println("当前无session");//用户未登录返回到登录页面
            return "login";
        }
    }

}
