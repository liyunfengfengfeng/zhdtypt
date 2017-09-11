package com.newage.iep.pojos.department;
//部门
public class Department {
  private String depart_id;//部门id
  private String depart_name;//部门名称
  private String cmp_id;//组织id 一个组织有多个岗位
  private String charge_person;//负责人
  private String business_scope;//业务范围

  public String getDepart_id() {
    return depart_id;
  }

  public void setDepart_id(String depart_id) {
    this.depart_id = depart_id;
  }

  public String getDepart_name() {
    return depart_name;
  }

  public void setDepart_name(String depart_name) {
    this.depart_name = depart_name;
  }

  public String getCmp_id() {
    return cmp_id;
  }

  public void setCmp_id(String cmp_id) {
    this.cmp_id = cmp_id;
  }

  public String getCharge_person() {
    return charge_person;
  }

  public void setCharge_person(String charge_person) {
    this.charge_person = charge_person;
  }

  public String getBusiness_scope() {
    return business_scope;
  }

  public void setBusiness_scope(String business_scope) {
    this.business_scope = business_scope;
  }
}
