package com.renren.kylin.bean.authorizer;

import java.io.Serializable;

/**
 * 被托管公众号的账号信息
 * @author : peng.chen5@renren-inc.com
 * @Time : 2017/6/14 下午3:35
 */
public class Authorizer implements Serializable {
  private static final long serialVersionUID = 7397716368103092130L;
  private AuthorizerInfo authorizer_info;
  private AuthorizationInfo authorization_info;

  public AuthorizerInfo getAuthorizer_info() {
    return authorizer_info;
  }

  public void setAuthorizer_info(AuthorizerInfo authorizer_info) {
    this.authorizer_info = authorizer_info;
  }

  public AuthorizationInfo getAuthorization_info() {
    return authorization_info;
  }

  public void setAuthorization_info(AuthorizationInfo authorization_info) {
    this.authorization_info = authorization_info;
  }
}
