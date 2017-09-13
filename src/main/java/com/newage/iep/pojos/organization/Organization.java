package com.newage.iep.pojos.organization;

import java.util.Date;
import java.util.Set;

//组织单位信息
public class Organization {
  private String cmp_id;//组织id
  private String cmp_code;//组织编号
  private String cmp_name;//组织名称
  private String cmp_name_eng;//单位名称英文
  private String cmp_address;//单位地址
  private Long business_type;//工商类型
  private String gps_longitude;//经度
  private String gps_latitude;//纬度
  private Long employee_number;//员工数
  private String phone;//电话号码
  private String rescue_tel;//救援电话
  private String mail;//邮箱
  private String cmp_type;//单位类别
  private Long checkout_flag;//检测标志
  private Long make_flag;//制造标志
  private Long build_flag;//施工标志
  private Long maintenan_flag;//维保标志
  private Long use_flag;//使用标志
  private String licence_no;//许可证号
  private String charge_person;//负责人
  private String certificate_code;//组织机构代码
  private String country;//国家
  private String area;//行政区域
  private String post_code;//邮政编码
  private Long register_capital;//注册资本
  private String fixed_assets;//固定资产
  private String license_no;//营业执照注册号
  private String license_department;//营业执照登记机构
  private String business_term;//营业期限
  private String law_person;//法人
  private String law_code;//法人身份号
  private String website;//网址
  private String business_scope;//业务范围
  private String economic_type;//经济类型
  private String org_level;//机构级别
  private String total_no;//总人数
  private String approve_department;//批准成立机关
  private String status;//状态
  private String rmk;//备注
  private java.util.Date create_date;//创建日期
  private String create_by;//创建者
  private String modify_by;//修改者
  private java.util.Date modify_date;//修改日期
  private String fax;//传真
  private String title;//标题
  private String content;//内容
  private String Parent_id;
  private Set<Accessory> accessoryEntities;

  public Set<Accessory> getAccessoryEntities() {
    return accessoryEntities;
  }

  public void setAccessoryEntities(Set<Accessory> accessoryEntities) {
    this.accessoryEntities = accessoryEntities;
  }

  public String getCmp_id() {
    return cmp_id;
  }

  public void setCmp_id(String cmp_id) {
    this.cmp_id = cmp_id;
  }

  public String getCmp_code() {
    return cmp_code;
  }

  public void setCmp_code(String cmp_code) {
    this.cmp_code = cmp_code;
  }

  public String getCmp_name() {
    return cmp_name;
  }

  public void setCmp_name(String cmp_name) {
    this.cmp_name = cmp_name;
  }

  public String getCmp_name_eng() {
    return cmp_name_eng;
  }

  public void setCmp_name_eng(String cmp_name_eng) {
    this.cmp_name_eng = cmp_name_eng;
  }

  public String getCmp_address() {
    return cmp_address;
  }

  public void setCmp_address(String cmp_address) {
    this.cmp_address = cmp_address;
  }

  public Long getBusiness_type() {
    return business_type;
  }

  public void setBusiness_type(Long business_type) {
    this.business_type = business_type;
  }

  public String getGps_longitude() {
    return gps_longitude;
  }

  public void setGps_longitude(String gps_longitude) {
    this.gps_longitude = gps_longitude;
  }

  public String getGps_latitude() {
    return gps_latitude;
  }

  public void setGps_latitude(String gps_latitude) {
    this.gps_latitude = gps_latitude;
  }

  public Long getEmployee_number() {
    return employee_number;
  }

  public void setEmployee_number(Long employee_number) {
    this.employee_number = employee_number;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getRescue_tel() {
    return rescue_tel;
  }

  public void setRescue_tel(String rescue_tel) {
    this.rescue_tel = rescue_tel;
  }

  public String getMail() {
    return mail;
  }

  public void setMail(String mail) {
    this.mail = mail;
  }

  public String getCmp_type() {
    return cmp_type;
  }

  public void setCmp_type(String cmp_type) {
    this.cmp_type = cmp_type;
  }

  public Long getCheckout_flag() {
    return checkout_flag;
  }

  public void setCheckout_flag(Long checkout_flag) {
    this.checkout_flag = checkout_flag;
  }

  public Long getMake_flag() {
    return make_flag;
  }

  public void setMake_flag(Long make_flag) {
    this.make_flag = make_flag;
  }

  public Long getBuild_flag() {
    return build_flag;
  }

  public void setBuild_flag(Long build_flag) {
    this.build_flag = build_flag;
  }

  public Long getMaintenan_flag() {
    return maintenan_flag;
  }

  public void setMaintenan_flag(Long maintenan_flag) {
    this.maintenan_flag = maintenan_flag;
  }

  public Long getUse_flag() {
    return use_flag;
  }

  public void setUse_flag(Long use_flag) {
    this.use_flag = use_flag;
  }

  public String getLicence_no() {
    return licence_no;
  }

  public void setLicence_no(String licence_no) {
    this.licence_no = licence_no;
  }

  public String getCharge_person() {
    return charge_person;
  }

  public void setCharge_person(String charge_person) {
    this.charge_person = charge_person;
  }

  public String getCertificate_code() {
    return certificate_code;
  }

  public void setCertificate_code(String certificate_code) {
    this.certificate_code = certificate_code;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getArea() {
    return area;
  }

  public void setArea(String area) {
    this.area = area;
  }

  public String getPost_code() {
    return post_code;
  }

  public void setPost_code(String post_code) {
    this.post_code = post_code;
  }

  public Long getRegister_capital() {
    return register_capital;
  }

  public void setRegister_capital(Long register_capital) {
    this.register_capital = register_capital;
  }

  public String getFixed_assets() {
    return fixed_assets;
  }

  public void setFixed_assets(String fixed_assets) {
    this.fixed_assets = fixed_assets;
  }

  public String getLicense_no() {
    return license_no;
  }

  public void setLicense_no(String license_no) {
    this.license_no = license_no;
  }

  public String getLicense_department() {
    return license_department;
  }

  public void setLicense_department(String license_department) {
    this.license_department = license_department;
  }

  public String getBusiness_term() {
    return business_term;
  }

  public void setBusiness_term(String business_term) {
    this.business_term = business_term;
  }

  public String getLaw_person() {
    return law_person;
  }

  public void setLaw_person(String law_person) {
    this.law_person = law_person;
  }

  public String getLaw_code() {
    return law_code;
  }

  public void setLaw_code(String law_code) {
    this.law_code = law_code;
  }

  public String getWebsite() {
    return website;
  }

  public void setWebsite(String website) {
    this.website = website;
  }

  public String getBusiness_scope() {
    return business_scope;
  }

  public void setBusiness_scope(String business_scope) {
    this.business_scope = business_scope;
  }

  public String getEconomic_type() {
    return economic_type;
  }

  public void setEconomic_type(String economic_type) {
    this.economic_type = economic_type;
  }

  public String getOrg_level() {
    return org_level;
  }

  public void setOrg_level(String org_level) {
    this.org_level = org_level;
  }

  public String getTotal_no() {
    return total_no;
  }

  public void setTotal_no(String total_no) {
    this.total_no = total_no;
  }

  public String getApprove_department() {
    return approve_department;
  }

  public void setApprove_department(String approve_department) {
    this.approve_department = approve_department;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getRmk() {
    return rmk;
  }

  public void setRmk(String rmk) {
    this.rmk = rmk;
  }

  public Date getCreate_date() {
    return create_date;
  }

  public void setCreate_date(Date create_date) {
    this.create_date = create_date;
  }

  public String getCreate_by() {
    return create_by;
  }

  public void setCreate_by(String create_by) {
    this.create_by = create_by;
  }

  public String getModify_by() {
    return modify_by;
  }

  public void setModify_by(String modify_by) {
    this.modify_by = modify_by;
  }

  public Date getModify_date() {
    return modify_date;
  }

  public void setModify_date(Date modify_date) {
    this.modify_date = modify_date;
  }

  public String getFax() {
    return fax;
  }

  public void setFax(String fax) {
    this.fax = fax;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getParent_id() {
    return Parent_id;
  }

  public void setParent_id(String parent_id) {
    Parent_id = parent_id;
  }
}
