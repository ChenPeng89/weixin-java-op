package com.renren.kylin.api;

import com.renren.kylin.bean.auth.AuthCode;
import com.renren.kylin.bean.auth.Authorization;
import me.chanjar.weixin.common.util.ToStringUtils;
import me.chanjar.weixin.common.util.http.ApacheHttpClientBuilder;

import java.io.File;
import java.util.List;

/**
 * 基于内存的微信配置provider，在实际生产环境中应该将这些配置持久化
 *
 * @author peng.chen5@renren-inc.com
 */
public class WxOpInMemoryConfigStorage extends WxOpConfigStorage {

  /**
   * 临时文件目录
   */
  protected volatile File tmpDirFile;

  protected volatile ApacheHttpClientBuilder apacheHttpClientBuilder;

  @Override
  public String getComponentAccessToken() {
    return this.componentAccessToken;
  }


  @Override
  public boolean isAccessTokenExpired() {
    return System.currentTimeMillis() > this.expiresTime;
  }


  @Override
  public synchronized void updateComponentAccessToken(String componentAccessToken, int expiresInSeconds) {
    this.componentAccessToken = componentAccessToken;
    this.expiresTime = System.currentTimeMillis() + (expiresInSeconds - 200) * 1000L;
  }

  @Override
  public void expireAccessToken() {
    this.expiresTime = 0;
  }

  @Override
  public String getJsapiTicket(String authorizerAppId) {
    return this.jsapiTicket;
  }


  @Override
  public boolean isJsapiTicketExpired(String authorizerAppId) {
    return System.currentTimeMillis() > this.jsapiTicketExpiresTime;
  }

  @Override
  public synchronized void updateJsapiTicket(String jsapiTicket, int expiresInSeconds ,String authorizerAppId) {
    this.jsapiTicket = jsapiTicket;
    // 预留200秒的时间
    this.jsapiTicketExpiresTime = System.currentTimeMillis() + (expiresInSeconds - 200) * 1000L;
  }

  @Override
  public void expireJsapiTicket(String authorizerAppId) {
    this.jsapiTicketExpiresTime = 0;
  }


  @Override
  public String getComponentAppId() {
    return this.componentAppId;
  }

  @Override
  public void setComponentAppId(String componentAppId) {
    this.componentAppId = componentAppId;
  }

  @Override
  public String getComponentAppSecret() {
    return this.componentAppSecret;
  }

  @Override
  public void setComponentAppSecret(String componentAppSecret) {
    this.componentAppSecret = componentAppSecret;
  }

  @Override
  public String getToken() {
    return this.token;
  }

  @Override
  public void setToken(String token) {
    this.token = token;
  }

  @Override
  public long getExpiresTime() {
    return this.expiresTime;
  }

  @Override
  public String getAesKey() {
    return this.aesKey;
  }

  @Override
  public void setAesKey(String aesKey) {
    this.aesKey = aesKey;
  }


  @Override
  public String getHttpProxyHost() {
    return this.httpProxyHost;
  }

  public void setHttpProxyHost(String httpProxyHost) {
    this.httpProxyHost = httpProxyHost;
  }

  @Override
  public int getHttpProxyPort() {
    return this.httpProxyPort;
  }

  public void setHttpProxyPort(int httpProxyPort) {
    this.httpProxyPort = httpProxyPort;
  }

  @Override
  public String getHttpProxyUsername() {
    return this.httpProxyUsername;
  }

  public void setHttpProxyUsername(String httpProxyUsername) {
    this.httpProxyUsername = httpProxyUsername;
  }

  @Override
  public String getHttpProxyPassword() {
    return this.httpProxyPassword;
  }

  public void setHttpProxyPassword(String httpProxyPassword) {
    this.httpProxyPassword = httpProxyPassword;
  }

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }

  @Override
  public File getTmpDirFile() {
    return this.tmpDirFile;
  }

  public void setTmpDirFile(File tmpDirFile) {
    this.tmpDirFile = tmpDirFile;
  }

  @Override
  public ApacheHttpClientBuilder getApacheHttpClientBuilder() {
    return this.apacheHttpClientBuilder;
  }

  public void setApacheHttpClientBuilder(ApacheHttpClientBuilder apacheHttpClientBuilder) {
    this.apacheHttpClientBuilder = apacheHttpClientBuilder;
  }


  @Override
  public boolean autoRefreshToken() {
    return true;
  }

  @Override
  public void setComponentVerifyTicket(String componentVerifyTicket) {
    this.componentVerifyTicket = componentVerifyTicket;
  }

  @Override
  public String getComponentVerifyTicket() {
    return this.componentVerifyTicket;
  }

  /**
   * 更新被托管公众号的授权信息
   * @param authorizationInfo
   * @see Authorization
   */
  @Override
  public void updateAuthorizationInfo(Authorization authorizationInfo) {
    // 预留200秒的时间
    authorizationInfo.setExpiresTime(System.currentTimeMillis() + (authorizationInfo.getExpiresTime() - 200) * 1000L);
    this.authorizationInfos.put(authorizationInfo.getAuthorizerAppId() , authorizationInfo);
  }

  /**
   * 获取被托管公众号的授权信息
   * @param authorizerAppId 被托管公众号的appid
   * @return
   */
  @Override
  public Authorization getAuthorizationInfo(String authorizerAppId) {
    return this.authorizationInfos.get(authorizerAppId);
  }

  @Override
  public String getAuthorizerAccessToken(String authorizerAppId) {
    Authorization authorizationInfo = authorizationInfos.get(authorizerAppId);
    if(authorizationInfo == null){
      throw new RuntimeException("未查到相应的授权信息");
    }
    return authorizationInfo.getAuthorizerAccessToken();
  }

  @Override
  public void updateAuthorizerAccessToken(String authorizerAppId, String authorizerAccessToken, String authorizerRefreshToken, int expireIn) {
    Authorization authorizationInfo = getAuthorizationInfo(authorizerAppId);
    authorizationInfo.setAuthorizerAccessToken(authorizerAccessToken);
    authorizationInfo.setAuthorizerRefreshToken(authorizerRefreshToken);
    // 预留200秒的时间
    authorizationInfo.setExpiresTime(expireIn);
    updateAuthorizationInfo(authorizationInfo);
  }


  /**
   * 授权回调信息的授权码
   */
  @Override
  public List<AuthCode> getAuthCodes() {
    return this.authCodes;
  }

  @Override
  public void setAuthCode(String authCode , int expireIn) {
    AuthCode authCodeInfo = new AuthCode();
    authCodeInfo.setAuthCode(authCode);
    authCodeInfo.setExpiresTime(System.currentTimeMillis() + expireIn);
    this.authCodes.add(authCodeInfo);
  }

  @Override
  public void updateAuthCodes(List<AuthCode> authCodes) {
    if(authCodes == null){
      return;
    }
    this.authCodes = authCodes;
  }

}
