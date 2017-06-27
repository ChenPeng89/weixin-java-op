package com.renren.kylin.api;

import com.google.gson.Gson;
import com.renren.kylin.bean.auth.AuthCode;
import com.renren.kylin.bean.auth.Authorization;
import com.renren.kylin.util.serialize.SerializeUtils;
import me.chanjar.weixin.common.util.ToStringUtils;
import me.chanjar.weixin.common.util.http.ApacheHttpClientBuilder;
import redis.clients.jedis.Jedis;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * 基于Redis的微信配置provider
 *
 * @author peng.chen5@renren-inc.com
 */
public class WxOpInRedisConfigStorage extends WxOpConfigStorage {

  private final static String COMPONENT_ACCESS_TOKEN_KEY = "kylin_wechat_op_component_access_token_";

  private final static String COMPONENT_VERIFY_TICKET = "kylin_wechat_op_component_verify_ticket_";

  private final static String JSAPI_TICKET_KEY = "kylin_wechat_op_jsapi_ticket_";

  private final static String AUTHORIZATION_INFO = "kylin_wechat_op_authorization_info_";

  private final static String AUTH_CODE = "kylin_wechat_op_auth_code_";


  protected Jedis jedis;
  protected Gson gson = new Gson();

  public WxOpInRedisConfigStorage(Jedis jedis) {
    this.jedis = jedis;
  }

  @Override
  public String getComponentAccessToken() {
    return jedis.get(COMPONENT_ACCESS_TOKEN_KEY.concat(componentAppId));
  }

  @Override
  public boolean isAccessTokenExpired() {
    return getComponentAccessToken() == null ? true : false;
  }


  @Override
  public synchronized void updateComponentAccessToken(String componentAccessToken, int expiresInSeconds) {
    jedis.set(COMPONENT_ACCESS_TOKEN_KEY.concat(componentAppId) , componentAccessToken );
    jedis.expire(COMPONENT_ACCESS_TOKEN_KEY.concat(componentAppId), expiresInSeconds - 200);
  }

  @Override
  public void expireAccessToken() {
    jedis.expire(COMPONENT_ACCESS_TOKEN_KEY.concat(componentAppId), 0);
  }

  @Override
  public String getJsapiTicket(String authorizerAppId) {
    return jedis.get(JSAPI_TICKET_KEY.concat(authorizerAppId));
  }



  @Override
  public boolean isJsapiTicketExpired(String authorizerAppId) {
    return getJsapiTicket(authorizerAppId) == null ? true : false;
  }

  @Override
  public synchronized void updateJsapiTicket(String jsapiTicket, int expiresInSeconds ,String authorizerAppId) {
    jedis.set(JSAPI_TICKET_KEY.concat(authorizerAppId) , jsapiTicket );
    jedis.expire(JSAPI_TICKET_KEY.concat(authorizerAppId), expiresInSeconds - 200);
  }

  @Override
  public void expireJsapiTicket(String authorizerAppId) {
    jedis.expire(JSAPI_TICKET_KEY.concat(authorizerAppId), 0);
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
    return 0;
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
    jedis.set(COMPONENT_VERIFY_TICKET.concat(componentAppId) , componentVerifyTicket);
  }

  @Override
  public String getComponentVerifyTicket() {
    return jedis.get(COMPONENT_VERIFY_TICKET.concat(componentAppId));
  }

  /**
   * 更新被托管公众号的授权信息
   * @param authorizationInfo
   * @see Authorization
   */
  @Override
  public void updateAuthorizationInfo(Authorization authorizationInfo) {
    jedis.set(AUTHORIZATION_INFO.concat(authorizationInfo.getAuthorizerAppId()).getBytes() , SerializeUtils.serialize(authorizationInfo));
  }

  /**
   * 获取被托管公众号的授权信息
   * @param authorizerAppId 被托管公众号的appid
   * @return
   */
  @Override
  public Authorization getAuthorizationInfo(String authorizerAppId) {
    byte[] bytes = jedis.get(AUTHORIZATION_INFO.concat(authorizerAppId).getBytes());
    return gson.fromJson(gson.toJson(SerializeUtils.unserialize(bytes)) , Authorization.class);
  }

  @Override
  public String getAuthorizerAccessToken(String authorizerAppId) {
    Authorization authorizationInfo = getAuthorizationInfo(authorizerAppId);
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
    authorizationInfo.setExpiresTime(System.currentTimeMillis() + (expireIn - 200) * 1000L);
    updateAuthorizationInfo(authorizationInfo);
  }


  /**
   * 授权回调信息的授权码
   */
  @Override
  public List<AuthCode> getAuthCodes() {
    byte[] bytes = jedis.get(AUTH_CODE.concat(componentAppId).getBytes());
    List authCodes = gson.fromJson(gson.toJson(SerializeUtils.unserialize(bytes)) , List.class);
    return (authCodes == null ? new ArrayList<AuthCode>() : authCodes);
  }

  @Override
  public void setAuthCode(String authCode , int expireIn) {
    AuthCode authCodeInfo = new AuthCode();
    authCodeInfo.setAuthCode(authCode);
    authCodeInfo.setExpiresTime(System.currentTimeMillis() + expireIn);
    List<AuthCode> authCodes = getAuthCodes();
    authCodes.add(authCodeInfo);
    updateAuthCodes(authCodes);
  }

  @Override
  public void updateAuthCodes(List<AuthCode> authCodes) {
    if(authCodes == null){
      return;
    }
    jedis.set(AUTH_CODE.concat(componentAppId).getBytes(), SerializeUtils.serialize(authCodes));
  }


  public void setJedis(Jedis jedis) {
    this.jedis = jedis;
  }
}
