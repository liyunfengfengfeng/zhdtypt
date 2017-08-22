package com.newage.iep.pojos;

/**
 * Created by a1996_000 on 2017/8/20.
 */

import java.util.List;

/**
 * 菜单信息
 */
public class Menu {
    private String menu_id;// 菜单id 主键
    private String title;// 菜单名称
    private String desn;// 菜单描述
    private String parent_id;// 菜单父id
    private String url;// 菜单url
    private String image_path;// 图片路径
    private List childList;//子菜单
    //新增字段
    private String module_name;
    private Integer menu_order;

    public String getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(String menu_id) {
        this.menu_id = menu_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesn() {
        return desn;
    }

    public void setDesn(String desn) {
        this.desn = desn;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public List getChildList() {
        return childList;
    }

    public void setChildList(List childList) {
        this.childList = childList;
    }

    public String getModule_name() {
        return module_name;
    }

    public void setModule_name(String module_name) {
        this.module_name = module_name;
    }

    public Integer getMenu_order() {
        return menu_order;
    }

    public void setMenu_order(Integer menu_order) {
        this.menu_order = menu_order;
    }
}
