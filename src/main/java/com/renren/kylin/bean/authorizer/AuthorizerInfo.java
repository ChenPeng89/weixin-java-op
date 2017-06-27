package com.renren.kylin.bean.authorizer;

import java.io.Serializable;

/**
 * @author : peng.chen5@renren-inc.com
 * @Time : 2017/6/14 下午3:52
 */
public class AuthorizerInfo implements Serializable {
  private static final long serialVersionUID = -8083390272755732317L;

  private String nick_name;
  private String head_img;
  private ServiceTypeInfo service_type_info;
  private VerifyTypeInfo verify_type_info;
  private String user_name;
  private String principal_name;
  private BusinessInfo business_info;
  private String alias;
  private String qrcode_url;

  public String getNick_name() {
    return nick_name;
  }

  public void setNick_name(String nick_name) {
    this.nick_name = nick_name;
  }

  public String getHead_img() {
    return head_img;
  }

  public void setHead_img(String head_img) {
    this.head_img = head_img;
  }

  public ServiceTypeInfo getService_type_info() {
    return service_type_info;
  }

  public void setService_type_info(ServiceTypeInfo service_type_info) {
    this.service_type_info = service_type_info;
  }

  public VerifyTypeInfo getVerify_type_info() {
    return verify_type_info;
  }

  public void setVerify_type_info(VerifyTypeInfo verify_type_info) {
    this.verify_type_info = verify_type_info;
  }

  public String getUser_name() {
    return user_name;
  }

  public void setUser_name(String user_name) {
    this.user_name = user_name;
  }

  public String getPrincipal_name() {
    return principal_name;
  }

  public void setPrincipal_name(String principal_name) {
    this.principal_name = principal_name;
  }

  public BusinessInfo getBusiness_info() {
    return business_info;
  }

  public void setBusiness_info(BusinessInfo business_info) {
    this.business_info = business_info;
  }

  public String getAlias() {
    return alias;
  }

  public void setAlias(String alias) {
    this.alias = alias;
  }

  public String getQrcode_url() {
    return qrcode_url;
  }

  public void setQrcode_url(String qrcode_url) {
    this.qrcode_url = qrcode_url;
  }
}
