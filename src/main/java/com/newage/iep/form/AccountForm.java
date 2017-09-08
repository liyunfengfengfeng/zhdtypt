package com.newage.iep.form;

/**
 * Created by a1996_000 on 2017/9/5.
 */

/**
 * 账户表单
 */
public class AccountForm {

    private String name;//名称
    private String email;//邮箱
    private String password;//密码
    private String id_number;//身份证号
    private String sex;//性别
    private String bath;//出生日期
    private String cmp_id;//组织id

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId_number() {
        return id_number;
    }

    public void setId_number(String id_number) {
        this.id_number = id_number;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBath() {
        return bath;
    }

    public void setBath(String bath) {
        this.bath = bath;
    }

    public String getCmp_id() {
        return cmp_id;
    }

    public void setCmp_id(String cmp_id) {
        this.cmp_id = cmp_id;
    }
}
