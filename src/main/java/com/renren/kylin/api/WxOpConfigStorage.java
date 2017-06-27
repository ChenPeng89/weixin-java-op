package com.renren.kylin.api;

import com.renren.kylin.bean.auth.AuthCode;
import com.renren.kylin.bean.auth.Authorization;
import me.chanjar.weixin.common.util.http.ApacheHttpClientBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 微信客户端配置存储
 *
 * @author chanjarster
 */
public abstract class  WxOpConfigStorage {

  protected volatile String componentAppId;
  protected volatile String componentAppSecret;
  protected volatile String token;
  protected volatile String componentAccessToken;
  protected volatile String aesKey;
  protected volatile long expiresTime;

  protected volatile String httpProxyHost;
  protected volatile int httpProxyPort;
  protected volatile String httpProxyUsername;
  protected volatile String httpProxyPassword;

  protected volatile String jsapiTicket;
  protected volatile long jsapiTicketExpiresTime;

  protected volatile String componentVerifyTicket;

  protected ConcurrentHashMap<String , Authorization> authorizationInfos = new ConcurrentHashMap<>();

  protected List<AuthCode> authCodes = new ArrayList<>();

  protected Lock accessTokenLock = new ReentrantLock();
  protected Lock jsapiTicketLock = new ReentrantLock();
  protected Lock authorizerAccessTokenLock = new ReentrantLock();
  protected Lock authCodeLock = new ReentrantLock();

  /**
   * 临时文件目录
   */
  protected volatile File tmpDirFile;

  protected volatile ApacheHttpClientBuilder apacheHttpClientBuilder;

  /////////////////////////////////////
  //          平台自身参数            //
  ////////////////////////////////////

  public abstract String getComponentAccessToken();

  public Lock getAccessTokenLock(){
    return this.accessTokenLock;
  }

  public abstract  boolean isAccessTokenExpired();
  /**
   * 强制将access token过期掉
   */
  public abstract  void expireAccessToken();
  public abstract  long getExpiresTime();
  /**
   * 应该是线程安全的
   *
   * @param accessToken      新的accessToken值
   * @param expiresInSeconds 过期时间，以秒为单位
   */
  public abstract void updateComponentAccessToken(String accessToken, int expiresInSeconds);

  public abstract String getComponentAppId();
  public abstract void setComponentAppId(String componentAppId);

  public abstract String getComponentAppSecret();

  public abstract void setComponentAppSecret(String componentAppSecret);

  public abstract String getToken();

  public abstract void setToken(String token);

  public abstract String getAesKey();

  public abstract void setAesKey(String aeskey);

  /**
   * 是否自动刷新token
   */
  public abstract boolean autoRefreshToken();


  public abstract void setComponentVerifyTicket(String componentVerifyTicket);

  public abstract String getComponentVerifyTicket();


  /////////////////////////////////////
  //          被托管平台参数           //
  ////////////////////////////////////


  public abstract String getJsapiTicket(String authorizerAppId);

  public Lock getJsapiTicketLock(){
    return jsapiTicketLock;
  }

  public abstract boolean isJsapiTicketExpired(String authorizerAppId);
  /**
   * 强制将jsapi ticket过期掉
   */
  public abstract void expireJsapiTicket(String authorizerAppId);
  /**
   * 应该是线程安全的
   *
   * @param jsapiTicket      新的jsapi ticket值
   * @param expiresInSeconds 过期时间，以秒为单位
   * @param authorizerAppId            被托管公众号的appId
   */
  public abstract void updateJsapiTicket(String jsapiTicket, int expiresInSeconds , String authorizerAppId);

  /**
   * 更新被托管公众号的授权信息
   * @param authorizationInfo
   * @see Authorization
   */
  public abstract void updateAuthorizationInfo(Authorization authorizationInfo);
  /**
   * 获取被托管公众号的授权信息
   * @param authorizerAppId 被托管公众号的appid
   * @return
   */
  public abstract Authorization getAuthorizationInfo(String authorizerAppId);

  public abstract String getAuthorizerAccessToken(String authorizerAppId);

  public abstract void updateAuthorizerAccessToken(String authorizerAppId , String authorizerAccessToken , String authorizerRefreshToken , int expireIn);

  public Lock getAuthorizerAccessTokenLock(){
    return authorizerAccessTokenLock;
  }

  /**
   * 授权回调信息的授权码
   * @return
   */
  public abstract List<AuthCode> getAuthCodes();

  public abstract void setAuthCode(String authCode , int expireIn);

  public abstract void updateAuthCodes(List<AuthCode> authCodes);

  public Lock getAuthCodeLock(){
    return authCodeLock;
  }




  public abstract String getHttpProxyHost();

  public abstract int getHttpProxyPort();

  public abstract String getHttpProxyUsername();

  public abstract String getHttpProxyPassword();

  public abstract File getTmpDirFile();

  /**
   * http client builder
   *
   * @return ApacheHttpClientBuilder
   */
  public abstract ApacheHttpClientBuilder getApacheHttpClientBuilder();

}
