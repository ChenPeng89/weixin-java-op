package com.renren.kylin.api.impl;

import com.google.gson.*;
import com.renren.kylin.api.*;
import com.renren.kylin.bean.*;
import com.renren.kylin.bean.auth.AuthCode;
import com.renren.kylin.bean.auth.Authorization;
import com.renren.kylin.bean.authorizer.Authorizer;
import com.renren.kylin.bean.message.WxOpXmlMessage;
import com.renren.kylin.bean.result.WxOpMassSendResult;
import com.renren.kylin.bean.result.WxOpMassUploadResult;
import com.renren.kylin.bean.result.WxOpSemanticQueryResult;
import com.renren.kylin.util.http.HttpRequestUtil;
import me.chanjar.weixin.common.bean.result.WxError;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.StandardSessionManager;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.common.util.crypto.SHA1;
import me.chanjar.weixin.common.util.http.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;

/**
 * @author : peng.chen5@renren-inc.com
 * @Time : 2017/6/13 上午9:56
 */
public class WxOpServiceImpl implements WxOpService {
  private static final JsonParser JSON_PARSER = new JsonParser();

  protected final Logger log = LoggerFactory.getLogger(this.getClass());
  protected WxSessionManager sessionManager = new StandardSessionManager();
  private WxOpConfigStorage wxOpConfigStorage;
  private WxOpUserService userService = new WxOpUserServiceImpl(this);
  private WxOpDataCubeService dataCubeService = new WxOpDataCubeServiceImpl(this);
  private WxOpMaterialService materialService = new WxOpMaterialServiceImpl(this);
  private WxOpKefuService kefuService = new WxOpKefuServiceImpl(this);
  private CloseableHttpClient httpClient;
  private HttpHost httpProxy;
  private int retrySleepMillis = 1000;
  private int maxRetryTimes = 5;
  Gson gson = new Gson();

  @Override
  public boolean checkSignature(String timestamp, String nonce, String signature) {
    try {
      return SHA1.gen(this.getWxOpConfigStorage().getToken(), timestamp, nonce)
        .equals(signature);
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * 获取第三方平台 component_access_token
   *
   * @return
   */
  @Override
  public String getComponentAccessToken() {
    if(StringUtils.isNotBlank(this.getWxOpConfigStorage().getComponentAccessToken()) &&
       !this.getWxOpConfigStorage().isAccessTokenExpired()){
      return this.getWxOpConfigStorage().getComponentAccessToken();
    }
    String componentVerifyTicket = this.getWxOpConfigStorage().getComponentVerifyTicket();
    if(StringUtils.isBlank(componentVerifyTicket)){
      throw new RuntimeException("获取 ComponentAccessToken 失败");
    }
    Lock lock = this.getWxOpConfigStorage().getAccessTokenLock();
    try{
      lock.lock();
      Gson gson = new Gson();
      Map param = new HashMap();
      param.put("component_appid" , this.getWxOpConfigStorage().getComponentAppId());
      param.put("component_appsecret" , this.getWxOpConfigStorage().getComponentAppSecret());
      param.put("component_verify_ticket" , componentVerifyTicket);
      String response = HttpRequestUtil.sendPost("https://api.weixin.qq.com/cgi-bin/component/api_component_token" , gson.toJson(param) , false);
      log.info("获取 component_access_token 响应信息 : {}" , response);
      Map data = gson.fromJson(response , Map.class);
      String component_access_token = (String) data.get("component_access_token" );
      Double expires_in = (Double) data.get("expires_in");
      log.info("获取到的 component_access_token : {}" , component_access_token);
      this.getWxOpConfigStorage().updateComponentAccessToken(component_access_token , expires_in.intValue());
      return component_access_token;
    }finally {
      lock.unlock();
    }
  }

  /**
   * 获取用户授权url
   *
   * @return
   */
  @Override
  public String getComponentLoginUrl(String callbackUrl) throws UnsupportedEncodingException {

    Map param = new HashMap(1);
    param.put("component_appid" , this.getWxOpConfigStorage().getComponentAppId());
    String response = HttpRequestUtil.sendPost("https://api.weixin.qq.com/cgi-bin/component/api_create_preauthcode?component_access_token=" + getComponentAccessToken() , gson.toJson(param) , false);
    log.info("获取 pre_auth_code 响应信息 : {}" , response);
    Map data = gson.fromJson(response , Map.class);
    String pre_auth_code = (String) data.get("pre_auth_code");
    log.info("获取到的 pre_auth_code ： {}" , pre_auth_code);
    String authUrl = "https://mp.weixin.qq.com/cgi-bin/componentloginpage?component_appid="+ this.getWxOpConfigStorage().getComponentAppId() + "" +
      "&pre_auth_code=" + pre_auth_code + "&redirect_uri=" + URLEncoder.encode(callbackUrl , "UTF-8");
    return authUrl;
  }

  /**
   * 处理微信服务器向 【授权服务器URL】 推送消息
   *
   * @param signature    微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
   * @param encType      加密类型 一般为：aes
   * @param msgSignature 消息体签名
   * @param timestamp    时间戳
   * @param nonce        随机串
   * @param content      报文信息内容
   * @return
   */
  @Override
  public String handleAuthEvent(String signature, String encType, String msgSignature, String timestamp, String nonce, String content , AuthorizedEventHandler authorizedEventHandler) {
    if(!this.checkSignature(timestamp , nonce , signature)){
      return "非法请求";
    }
    WxOpXmlMessage inMessage = null;
    String out = "success";
    if (encType == null) {
      // 明文传输的消息
      inMessage = WxOpXmlMessage.fromXml(content);
    } else if ("aes".equals(encType)) {
      inMessage = WxOpXmlMessage.fromEncryptedXml(content , this.getWxOpConfigStorage() , timestamp, nonce, msgSignature);
    }
    if(inMessage.getInfoType().equals(WxOpXmlMessage.INFO_TYPE_COMPONENT_VERIFY_TICKET)){
      out = handleComponentVerifyTicketEvent(inMessage);
    }else if(inMessage.getInfoType().equals(WxOpXmlMessage.INFO_TYPE_AUTHORIZED) ||
      inMessage.getInfoType().equals(WxOpXmlMessage.INFO_TYPE_UNAUTHORIZED) ||
      inMessage.getInfoType().equals(WxOpXmlMessage.INFO_TYPE_UPDATEAUTHORIZED)){
        out = handleAuthorizedEvent(inMessage , authorizedEventHandler);
        if(inMessage.getInfoType().equals(WxOpXmlMessage.INFO_TYPE_UNAUTHORIZED)){
            this.handleUnauthorizationCallback(inMessage.getAuthorizerAppId());
        }else{
            this.handleAuthorizationCallback(inMessage.getAuthorizationCode() , inMessage.getAuthorizationCodeExpiredTime());
        }
    }
    return out;
  }

  /**
   * 处理微信服务器向 【授权服务器URL】 推送消息 : 推送 component_verify_ticket
   * 详情见：https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1453779503&token=&lang=zh_CN
   *
   * @param message
   */
  @Override
  public String handleComponentVerifyTicketEvent(WxOpXmlMessage message) {
    String out = "success";
    // 获取 componentVerifyTicket
    log.info("开始获取componentVerifyTicket ====================");
    String component_verify_ticket = message.getComponentVerifyTicket();
    log.info("获取到的 componentVerifyTicket : {}" , component_verify_ticket);
    this.getWxOpConfigStorage().setComponentVerifyTicket(component_verify_ticket);
    return out;
  }

  /**
   * 处理微信服务器向 【授权服务器URL】 推送消息 : 推送授权相关通知
   *
   * @param message
   * @param authorizedEventHandler       授权事件handler
   * @see AuthorizedEventHandler
   * 详情见：https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1453779503&token=&lang=zh_CN
   */
  @Override
  public String handleAuthorizedEvent(WxOpXmlMessage message, AuthorizedEventHandler authorizedEventHandler) {
    return authorizedEventHandler.handle(message);
  }

  /**
   * 根据被托管公众号的appId来获取对应的authorizer_access_token
   * @param authorizerAppId
   */
  @Override
  public String getAuthorizerAccessToken(String authorizerAppId) throws WxErrorException {
    Lock lock = this.getWxOpConfigStorage().getAuthorizerAccessTokenLock();
    try {
      lock.lock();
      Authorization authorizationInfo = this.getWxOpConfigStorage().getAuthorizationInfo(authorizerAppId);
      if(authorizationInfo == null || authorizationInfo.isAuthorizerAccessTokenExpired()){
        Map<String , String> param = new HashMap();
        param.put("component_appid" , this.getWxOpConfigStorage().getComponentAppId());
        param.put("authorizer_appid" , authorizerAppId);
        param.put("authorizer_refresh_token" , authorizationInfo.getAuthorizerRefreshToken());
        String url = "https://api.weixin.qq.com/cgi-bin/component/api_authorizer_token?component_access_token=" + getComponentAccessToken();
        Gson gson = new Gson();
        String resp = HttpRequestUtil.sendPost(url , gson.toJson(param) , false);
        Map data = gson.fromJson(resp , Map.class);
        this.getWxOpConfigStorage().updateAuthorizerAccessToken(authorizerAppId , (String)data.get("authorizer_access_token") , (String)data.get("authorizer_refresh_token") , ((Double)data.get("expires_in")).intValue());
      }
    } finally {
      lock.unlock();
    }
    return this.getWxOpConfigStorage().getAuthorizerAccessToken(authorizerAppId);
  }

  /**
   * 处理授权回调
   *
   * @param auth_code  授权码
   * @param expires_in 有效期
   * @return
   */
  @Override
  public Authorization handleAuthorizationCallback(String auth_code, int expires_in) {
    Lock lock = this.getWxOpConfigStorage().getAuthCodeLock();
    try{
      lock.lock();
      this.getWxOpConfigStorage().setAuthCode(auth_code , expires_in);
      Map param = new HashMap();
      param.put("component_appid" , this.getWxOpConfigStorage().getComponentAppId());
      param.put("authorization_code" , auth_code);
      String resp = HttpRequestUtil.sendPost("https://api.weixin.qq.com/cgi-bin/component/api_query_auth?component_access_token=" + getComponentAccessToken() , gson.toJson(param) , false);
      Map data = gson.fromJson(resp , Map.class);
      data = (Map) data.get("authorization_info");
      Authorization authorization = new Authorization();
      authorization.setAuthorizerAppId((String)data.get("authorizer_appid"));
      authorization.setAuthorizerAccessToken((String)data.get("authorizer_access_token"));
      authorization.setAuthorizerRefreshToken((String)data.get("authorizer_refresh_token"));
      authorization.setExpiresTime(((Double)data.get("expires_in")).intValue());
      authorization.setFuncInfo((List)data.get("func_info"));
      this.getWxOpConfigStorage().updateAuthorizationInfo(authorization);
      List<AuthCode> list = this.getWxOpConfigStorage().getAuthCodes();
      if(list != null && list.size() != 0){
        list.remove(list.size() - 1);
        this.getWxOpConfigStorage().updateAuthCodes(list);
      }
      return authorization;
    }finally {
      lock.unlock();
    }

  }

    /**
     * 处理取消授权回调
     * @param authorizerAppId
     */
    @Override
    public void handleUnauthorizationCallback(String authorizerAppId) {
        // TODO 删除对应config
    }

    /**
   * 获取授权方的帐号基本信息
   * @param authorizerAppId
   * @return
   */
  @Override
  public Authorizer getAuthorizer(String authorizerAppId) {
    Map param = new HashMap(2);
    param.put("component_appid" , this.getWxOpConfigStorage().getComponentAppId());
    param.put("authorizer_appid" , authorizerAppId);
    String resp = HttpRequestUtil.sendPost("https://api.weixin.qq.com/cgi-bin/component/api_get_authorizer_info?component_access_token=" + getComponentAccessToken(), gson.toJson(param) , false);
    return gson.fromJson(resp , Authorizer.class);
  }

  @Override
  public WxOpMassUploadResult massNewsUpload(WxOpMassNews news  , String authorizerAppId) throws WxErrorException {
    String url = "https://api.weixin.qq.com/cgi-bin/media/uploadnews";
    String responseContent = this.post(url, news.toJson() , authorizerAppId);
    return WxOpMassUploadResult.fromJson(responseContent);
  }

  @Override
  public WxOpMassUploadResult massVideoUpload(WxOpMassVideo video  , String authorizerAppId) throws WxErrorException {
    String url = "https://api.weixin.qq.com/cgi-bin/media/uploadvideo";
    String responseContent = this.post(url, video.toJson() , authorizerAppId);
    return WxOpMassUploadResult.fromJson(responseContent);
  }

  @Override
  public WxOpMassSendResult massGroupMessageSend(WxOpMassTagMessage message  , String authorizerAppId) throws WxErrorException {
    String url = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall";
    String responseContent = this.post(url, message.toJson() , authorizerAppId);
    return WxOpMassSendResult.fromJson(responseContent);
  }

  @Override
  public WxOpMassSendResult massOpenIdsMessageSend(WxOpMassOpenIdsMessage message  , String authorizerAppId) throws WxErrorException {
    String url = "https://api.weixin.qq.com/cgi-bin/message/mass/send";
    String responseContent = this.post(url, message.toJson() ,authorizerAppId);
    return WxOpMassSendResult.fromJson(responseContent);
  }

  @Override
  public WxOpMassSendResult massMessagePreview(WxOpMassPreviewMessage wxOpMassPreviewMessage  , String authorizerAppId) throws Exception {
    String url = "https://api.weixin.qq.com/cgi-bin/message/mass/preview";
    String responseContent = this.post(url, wxOpMassPreviewMessage.toJson() , authorizerAppId);
    return WxOpMassSendResult.fromJson(responseContent);
  }

  @Override
  public String shortUrl(String long_url , String authorizerAppId) throws WxErrorException {
    String url = "https://api.weixin.qq.com/cgi-bin/shorturl";
    JsonObject o = new JsonObject();
    o.addProperty("action", "long2short");
    o.addProperty("long_url", long_url);
    String responseContent = this.post(url, o.toString() , authorizerAppId);
    JsonElement tmpJsonElement = JSON_PARSER.parse(responseContent);
    return tmpJsonElement.getAsJsonObject().get("short_url").getAsString();
  }

  @Override
  public WxOpSemanticQueryResult semanticQuery(WxOpSemanticQuery semanticQuery , String authorizerAppId) throws WxErrorException {
    String url = "https://api.weixin.qq.com/semantic/semproxy/search";
    String responseContent = this.post(url, semanticQuery.toJson() , authorizerAppId);
    return WxOpSemanticQueryResult.fromJson(responseContent);
  }


  @Override
  public String[] getCallbackIP() throws WxErrorException {
    String url = "https://api.weixin.qq.com/cgi-bin/getcallbackip";
    String responseContent = get(url, null  , null);
    JsonElement tmpJsonElement = JSON_PARSER.parse(responseContent);
    JsonArray ipList = tmpJsonElement.getAsJsonObject().get("ip_list").getAsJsonArray();
    String[] ipArray = new String[ipList.size()];
    for (int i = 0; i < ipList.size(); i++) {
      ipArray[i] = ipList.get(i).getAsString();
    }
    return ipArray;
  }

  @Override
  public String get(String url, String queryParam , String authorizerAppId) throws WxErrorException {
    return execute(new SimpleGetRequestExecutor(), url, queryParam , authorizerAppId);
  }

  @Override
  public String post(String url, String postData , String authorizerAppId) throws WxErrorException {
    return execute(new SimplePostRequestExecutor(), url, postData , authorizerAppId);
  }

  /**
   * 向微信端发送请求，在这里执行的策略是当发生access_token过期时才去刷新，然后重新执行请求，而不是全局定时请求
   */
  @Override
  public <T, E> T execute(RequestExecutor<T, E> executor, String uri, E data , String authorizerAppId) throws WxErrorException {
    int retryTimes = 0;
    do {
      try {
        T result = executeInternal(executor, uri, data , authorizerAppId);
        this.log.debug("\n[URL]:  {}\n[PARAMS]: {}\n[RESPONSE]: {}", uri, data, result);
        return result;
      } catch (WxErrorException e) {
        if (retryTimes + 1 > this.maxRetryTimes) {
          this.log.warn("重试达到最大次数【{}】", maxRetryTimes);
          //最后一次重试失败后，直接抛出异常，不再等待
          throw new RuntimeException("微信服务端异常，超出重试次数");
        }

        WxError error = e.getError();
        // -1 系统繁忙, 1000ms后重试
        if (error.getErrorCode() == -1) {
          int sleepMillis = this.retrySleepMillis * (1 << retryTimes);
          try {
            this.log.warn("微信系统繁忙，{} ms 后重试(第{}次)", sleepMillis, retryTimes + 1);
            Thread.sleep(sleepMillis);
          } catch (InterruptedException e1) {
            throw new RuntimeException(e1);
          }
        } else {
          throw e;
        }
      }
    } while (retryTimes++ < this.maxRetryTimes);

    this.log.warn("重试达到最大次数【{}】", this.maxRetryTimes);
    throw new RuntimeException("微信服务端异常，超出重试次数");
  }

  protected synchronized <T, E> T executeInternal(RequestExecutor<T, E> executor, String uri, E data , String authorizerAppId) throws WxErrorException {
    if (uri.indexOf("access_token=") != -1) {
      throw new IllegalArgumentException("uri参数中不允许有access_token: " + uri);
    }
    String accessToken = getAuthorizerAccessToken( authorizerAppId);

    String uriWithAccessToken = uri;
    uriWithAccessToken += uri.indexOf('?') == -1 ? "?access_token=" + accessToken : "&access_token=" + accessToken;

    try {
      return executor.execute(getHttpclient(), this.httpProxy, uriWithAccessToken, data);
    } catch (WxErrorException e) {
      WxError error = e.getError();
//      /*
//       * 发生以下情况时尝试刷新access_token
//       * 40001 获取access_token时AppSecret错误，或者access_token无效
//       * 42001 access_token超时
//       */
//      if (error.getErrorCode() == 42001 || error.getErrorCode() == 40001) {
//        // 强制设置wxOpConfigStorage它的access token过期了
//        this.getWxOpConfigStorage().expireAccessToken();
//        if (this.getWxOpConfigStorage().autoRefreshToken()) {
//          return this.execute(executor, uri, data , authorizerAppId);
//        }
//      }

      if (error.getErrorCode() != 0) {
        this.log.error("\n[URL]:  {}\n[PARAMS]: {}\n[RESPONSE]: {}", uri, data, error);
        throw new WxErrorException(error);
      }
      return null;
    } catch (IOException e) {
      this.log.error("\n[URL]:  {}\n[PARAMS]: {}\n[EXCEPTION]: {}", uri, data, e.getMessage());
      throw new RuntimeException(e);
    }
  }

  @Override
  public HttpHost getHttpProxy() {
    return this.httpProxy;
  }

  public CloseableHttpClient getHttpclient() {
    return this.httpClient;
  }

  private void initHttpClient() {
    WxOpConfigStorage configStorage = this.getWxOpConfigStorage();
    ApacheHttpClientBuilder apacheHttpClientBuilder = configStorage.getApacheHttpClientBuilder();
    if (null == apacheHttpClientBuilder) {
      apacheHttpClientBuilder = DefaultApacheHttpClientBuilder.get();
    }

    apacheHttpClientBuilder.httpProxyHost(configStorage.getHttpProxyHost())
      .httpProxyPort(configStorage.getHttpProxyPort())
      .httpProxyUsername(configStorage.getHttpProxyUsername())
      .httpProxyPassword(configStorage.getHttpProxyPassword());

    if (configStorage.getHttpProxyHost() != null && configStorage.getHttpProxyPort() > 0) {
      this.httpProxy = new HttpHost(configStorage.getHttpProxyHost(), configStorage.getHttpProxyPort());
    }

    this.httpClient = apacheHttpClientBuilder.build();
  }

  @Override
  public WxOpConfigStorage getWxOpConfigStorage() {
    return this.wxOpConfigStorage;
  }



  @Override
  public void setWxOpConfigStorage(WxOpConfigStorage wxConfigProvider) {
    this.wxOpConfigStorage = wxConfigProvider;
    this.initHttpClient();
  }

  @Override
  public void setRetrySleepMillis(int retrySleepMillis) {
    this.retrySleepMillis = retrySleepMillis;
  }

  @Override
  public void setMaxRetryTimes(int maxRetryTimes) {
    this.maxRetryTimes = maxRetryTimes;
  }


  @Override
  public WxOpUserService getUserService() {
    return this.userService;
  }


  @Override
  public WxOpDataCubeService getDataCubeService() {
    return this.dataCubeService;
  }

    /**
     * 返回客服相关接口方法的实现类对象，以方便调用其各个接口
     *
     * @return WxOpKefuService
     */
    @Override
    public WxOpKefuService getKefuService() {
        return this.kefuService;
    }

    /**
   * 返回素材相关接口方法的实现类对象，以方便调用其各个接口
   *
   * @return WxOpMaterialService
   */
  public WxOpMaterialService getMaterialService() {
    return this.materialService;
  }


}
