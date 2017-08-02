package com.renren.kylin.bean.auth;


import com.renren.kylin.bean.authorizer.FuncInfo;
import me.chanjar.weixin.common.api.WxConsts;

import java.io.Serializable;
import java.util.List;

/**
 * 用户授权信息，
 * @author : peng.chen5@renren-inc.com
 * @Time : 2017/6/13 下午5:12
 */
public class Authorization implements Serializable {
  private static final long serialVersionUID = 7288249527763411973L;

  /**
   * 授权方appid
   */
  private String authorizerAppId;
  /**
   * 授权方接口调用凭据（在授权的公众号或小程序具备API权限时，才有此返回值），也简称为令牌
   */
  private String authorizerAccessToken;
  /**
   * 接口调用凭据刷新令牌（在授权的公众号具备API权限时，才有此返回值），刷新令牌主要用于第三方平台获取和刷新已授权用户的access_token，只会在授权时刻提供，请妥善保存。 一旦丢失，只能让用户重新授权，才能再次拿到新的刷新令牌
   */
  private String authorizerRefreshToken;
  /**
   * 有效期（在授权的公众号或小程序具备API权限时，才有此返回值）
   */
  private long expiresTime;

  /**
   * 公众号授权给开发者的权限集列表，ID为1到15时分别代表：
      消息管理权限
      用户管理权限
      帐号服务权限
      网页服务权限
      微信小店权限
      微信多客服权限
      群发与通知权限
      微信卡券权限
      微信扫一扫权限
      微信连WIFI权限
      素材管理权限
      微信摇周边权限
      微信门店权限
      微信支付权限
      自定义菜单权限
   */
  private List<FuncInfo> funcInfo;


  public boolean isAuthorizerAccessTokenExpired(){
    return expiresTime < System.currentTimeMillis();
  }

  public String getAuthorizerAppId() {
    return authorizerAppId;
  }

  public void setAuthorizerAppId(String authorizerAppId) {
    this.authorizerAppId = authorizerAppId;
  }

  public String getAuthorizerAccessToken() {
    return authorizerAccessToken;
  }

  public void setAuthorizerAccessToken(String authorizerAccessToken) {
    this.authorizerAccessToken = authorizerAccessToken;
  }

  public String getAuthorizerRefreshToken() {
    return authorizerRefreshToken;
  }

  public void setAuthorizerRefreshToken(String authorizerRefreshToken) {
    this.authorizerRefreshToken = authorizerRefreshToken;
  }

  public long getExpiresTime() {
    return expiresTime;
  }

  public void setExpiresTime(long expiresTime) {
    this.expiresTime = expiresTime;
  }

  public List getFuncInfo() {
    return funcInfo;
  }

  public void setFuncInfo(List funcInfo) {
    this.funcInfo = funcInfo;
  }
}
