package com.newage.iep.pojos;

import java.util.Date;
//账户信息用于用户登录或注册
public class Account {
  private String account_0_id;//id
  private String name;//名称
  private String email;//邮箱
  private String password;//密码
  private String id_number;//身份证号
  private String sex;//性别
  private java.util.Date bath;//出生日期
  private String depart_id;//部门id
  private String post_id;//岗位id
  private String cmp_id;//组织id
  private Long status;//状态 0 未审核1审核通过 2审核未通过
  private String create_by;//审核人
  private java.util.Date create_date;//创建日期
  private java.util.Date modity_date;//修改日期
  private String modify_by;//修改人

  public String getAccount_0_id() {
    return account_0_id;
  }

  public void setAccount_0_id(String account_0_id) {
    this.account_0_id = account_0_id;
  }

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

  public Date getBath() {
    return bath;
  }

  public void setBath(Date bath) {
    this.bath = bath;
  }

  public String getDepart_id() {
    return depart_id;
  }

  public void setDepart_id(String depart_id) {
    this.depart_id = depart_id;
  }

  public String getPost_id() {
    return post_id;
  }

  public void setPost_id(String post_id) {
    this.post_id = post_id;
  }

  public String getCmp_id() {
    return cmp_id;
  }

  public void setCmp_id(String cmp_id) {
    this.cmp_id = cmp_id;
  }

  public Long getStatus() {
    return status;
  }

  public void setStatus(Long status) {
    this.status = status;
  }

  public String getCreate_by() {
    return create_by;
  }

  public void setCreate_by(String create_by) {
    this.create_by = create_by;
  }

  public Date getCreate_date() {
    return create_date;
  }

  public void setCreate_date(Date create_date) {
    this.create_date = create_date;
  }

  public Date getModity_date() {
    return modity_date;
  }

  public void setModity_date(Date modity_date) {
    this.modity_date = modity_date;
  }

  public String getModify_by() {
    return modify_by;
  }

  public void setModify_by(String modify_by) {
    this.modify_by = modify_by;
  }
}
