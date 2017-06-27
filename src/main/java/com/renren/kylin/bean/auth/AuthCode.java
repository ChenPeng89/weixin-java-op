package com.renren.kylin.bean.auth;

import java.io.Serializable;

/**
 * 授权回调信息
 * @author : peng.chen5@renren-inc.com
 * @Time : 2017/6/14 下午2:38
 */
public class AuthCode implements Serializable{

  private static final long serialVersionUID = -2345426471567097032L;

  private String authCode;
  private long expiresTime;

  public String getAuthCode() {
    return authCode;
  }

  public void setAuthCode(String authCode) {
    this.authCode = authCode;
  }

  public long getExpiresTime() {
    return expiresTime;
  }

  public void setExpiresTime(long expiresTime) {
    this.expiresTime = expiresTime;
  }
}
