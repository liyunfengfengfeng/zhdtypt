package com.newage.iep.pojos;

/**
 * Created by a1996_000 on 2017/8/20.
 */
//角色菜单信息
public class RoleMenu {
    private String role_id;//角色id
    private String menu_id;//菜单id
    private String parent_id;//父菜单id
    private String id;//主键

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(String menu_id) {
        this.menu_id = menu_id;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
