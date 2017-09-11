package com.newage.iep.pojos.post;
//岗位
public class Post {
  private String post_id;//岗位id
  private String post_name;//岗位名称
  private String depart_id;//部门id 一个部门有多个岗位
  private String post_charge;//岗位职责

  public String getPost_id() {
    return post_id;
  }

  public void setPost_id(String post_id) {
    this.post_id = post_id;
  }

  public String getPost_name() {
    return post_name;
  }

  public void setPost_name(String post_name) {
    this.post_name = post_name;
  }

  public String getDepart_id() {
    return depart_id;
  }

  public void setDepart_id(String depart_id) {
    this.depart_id = depart_id;
  }

  public String getPost_charge() {
    return post_charge;
  }

  public void setPost_charge(String post_charge) {
    this.post_charge = post_charge;
  }
}
