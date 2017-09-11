package com.newage.iep.pojos.account;

/**
 * Created by a1996_000 on 2017/8/20.
 */

/**
 * 用户角色
 */
public class AccountRole {
    private String id;
    private String account_id;//账户id
    private String role_id;//角色id

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
