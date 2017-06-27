package com.renren.kylin.api;

import com.renren.kylin.bean.*;
import com.renren.kylin.bean.auth.Authorization;
import com.renren.kylin.bean.authorizer.Authorizer;
import com.renren.kylin.bean.message.WxOpXmlMessage;
import com.renren.kylin.bean.result.WxOpMassSendResult;
import com.renren.kylin.bean.result.WxOpMassUploadResult;
import com.renren.kylin.bean.result.WxOpSemanticQueryResult;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import org.apache.http.HttpHost;

import java.io.UnsupportedEncodingException;

/**
 * @author : peng.chen5@renren-inc.com
 * @Time : 2017/6/13 上午8:50
 */
public interface WxOpService {
  /**
   * <pre>
   * 验证消息的确来自微信服务器
   * 详情请见: http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421135319&token=&lang=zh_CN
   * </pre>
   */
  boolean checkSignature(String timestamp, String nonce, String signature);

  /**
   * 获取第三方平台 component_access_token
   * @return
   */
  String getComponentAccessToken() throws WxErrorException;

  /**
   * 获取用户授权url
   * @param callbackUrl 回调地址
   * @return
   */
  String getComponentLoginUrl(String callbackUrl) throws UnsupportedEncodingException;

  /**
   * 处理微信服务器向 【授权服务器URL】 推送消息
   * @param signature     微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
   * @param encType       加密类型 一般为：aes
   * @param msgSignature  消息体签名
   * @param timestamp     时间戳
   * @param nonce         随机串
   * @param content       报文信息内容
   * @param authorizedEventHandler        授权事件handler
   * @see com.renren.kylin.api.AuthorizedEventHandler
   * 详情见：https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1453779503&token=&lang=zh_CN
   */
  String handleAuthEvent(String signature, String encType, String msgSignature,
                                   String timestamp, String nonce, String content , AuthorizedEventHandler authorizedEventHandler);

  /**
   * 处理微信服务器向 【授权服务器URL】 推送消息 : 推送 component_verify_ticket
   * 详情见：https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1453779503&token=&lang=zh_CN
   */
  String handleComponentVerifyTicketEvent(WxOpXmlMessage message);


  /**
   * 处理微信服务器向 【授权服务器URL】 推送消息 : 推送授权相关通知
   * @param authorizedEventHandler        授权事件handler
   * @see com.renren.kylin.api.AuthorizedEventHandler
   * 详情见：https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1453779503&token=&lang=zh_CN
   */
  String handleAuthorizedEvent(WxOpXmlMessage message ,
                               AuthorizedEventHandler authorizedEventHandler );


  /**
   * 根据被托管公众号的appId来获取对应的authorizer_access_token
   * @param authorizerAppId
   */
  String getAuthorizerAccessToken(String authorizerAppId) throws WxErrorException;

  /**
   * 处理授权回调
   * @param auth_code   授权码
   * @param expires_in  有效期
   * @return AuthorizationInfo
   */
  Authorization handleAuthorizationCallback(String auth_code, int expires_in );


  /**
   * 获取授权方的帐号基本信息
   * @param authorizerAppId
   * @return
   */
  Authorizer getAuthorizer(String authorizerAppId);



  /**
   * <pre>
   * 上传群发用的图文消息，上传后才能群发图文消息
   *
   * 详情请见: http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140549&token=&lang=zh_CN
   * </pre>
   *
   * @see #massGroupMessageSend(WxOpMassTagMessage, String )
   * @see #massOpenIdsMessageSend(WxOpMassOpenIdsMessage, String )
   */
  WxOpMassUploadResult massNewsUpload(WxOpMassNews news, String authorizerAppId) throws WxErrorException;

  /**
   * <pre>
   * 上传群发用的视频，上传后才能群发视频消息
   * 详情请见: http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140549&token=&lang=zh_CN
   * </pre>
   *
   * @see #massGroupMessageSend(WxOpMassTagMessage, String )
   * @see #massOpenIdsMessageSend(WxOpMassOpenIdsMessage, String)
   */
  WxOpMassUploadResult massVideoUpload(WxOpMassVideo video, String authorizerAppId) throws WxErrorException;

  /**
   * <pre>
   * 分组群发消息
   * 如果发送图文消息，必须先使用 {@link #massNewsUpload(WxOpMassNews, String )} 获得media_id，然后再发送
   * 如果发送视频消息，必须先使用 {@link #massVideoUpload(WxOpMassVideo, String )} 获得media_id，然后再发送
   * 详情请见: http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140549&token=&lang=zh_CN
   * </pre>
   */
  WxOpMassSendResult massGroupMessageSend(WxOpMassTagMessage message, String authorizerAppId) throws WxErrorException;

  /**
   * <pre>
   * 按openId列表群发消息
   * 如果发送图文消息，必须先使用 {@link #massNewsUpload(WxOpMassNews, String )} 获得media_id，然后再发送
   * 如果发送视频消息，必须先使用 {@link #massVideoUpload(WxOpMassVideo, String )} 获得media_id，然后再发送
   * 详情请见: http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140549&token=&lang=zh_CN
   * </pre>
   */
  WxOpMassSendResult massOpenIdsMessageSend(WxOpMassOpenIdsMessage message, String authorizerAppId) throws WxErrorException;

  /**
   * <pre>
   * 群发消息预览接口
   * 开发者可通过该接口发送消息给指定用户，在手机端查看消息的样式和排版。为了满足第三方平台开发者的需求，在保留对openID预览能力的同时，增加了对指定微信号发送预览的能力，但该能力每日调用次数有限制（100次），请勿滥用。
   * 接口调用请求说明
   *  http请求方式: POST
   *  https://api.weixin.qq.com/cgi-bin/message/mass/preview?access_token=ACCESS_TOKEN
   * 详情请见：http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140549&token=&lang=zh_CN
   * </pre>
   *
   * @return wxOpMassSendResult
   */
  WxOpMassSendResult massMessagePreview(WxOpMassPreviewMessage wxOpMassPreviewMessage, String authorizerAppId) throws Exception;

  /**
   * <pre>
   * 长链接转短链接接口
   * 详情请见: http://mp.weixin.qq.com/wiki/index.php?title=长链接转短链接接口
   * </pre>
   *
   */
  String shortUrl(String long_url, String authorizerAppId) throws WxErrorException;

  /**
   * <pre>
   * 语义查询接口
   * 详情请见：http://mp.weixin.qq.com/wiki/index.php?title=语义理解
   * </pre>
   */
  WxOpSemanticQueryResult semanticQuery(WxOpSemanticQuery semanticQuery, String authorizerAppId) throws WxErrorException;

  /**
   * <pre>
   * 获取微信服务器IP地址
   * http://mp.weixin.qq.com/wiki/0/2ad4b6bfd29f30f71d39616c2a0fcedc.html
   * </pre>
   */
  String[] getCallbackIP() throws WxErrorException;

  /**
   * 当本Service没有实现某个API的时候，可以用这个，针对所有微信API中的GET请求
   */
  String get(String url, String queryParam, String authorizerAppId) throws WxErrorException;

  /**
   * 当本Service没有实现某个API的时候，可以用这个，针对所有微信API中的POST请求
   */
  String post(String url, String postData, String authorizerAppId) throws WxErrorException;

  /**
   * <pre>
   * Service没有实现某个API的时候，可以用这个，
   * 比{@link #get}和{@link #post}方法更灵活，可以自己构造RequestExecutor用来处理不同的参数和不同的返回类型。
   * 可以参考，{@link me.chanjar.weixin.common.util.http.MediaUploadRequestExecutor}的实现方法
   * </pre>
   */
  <T, E> T execute(RequestExecutor<T, E> executor, String uri, E data, String authorizerAppId) throws WxErrorException;

  /**
   * 获取代理对象
   */
  HttpHost getHttpProxy();

  /**
   * 注入 {@link WxOpConfigStorage} 的实现
   */
  void setWxOpConfigStorage(WxOpConfigStorage wxConfigProvider);

  /**
   * <pre>
   * 设置当微信系统响应系统繁忙时，要等待多少 retrySleepMillis(ms) * 2^(重试次数 - 1) 再发起重试
   * 默认：1000ms
   * </pre>
   */
  void setRetrySleepMillis(int retrySleepMillis);

  /**
   * <pre>
   * 设置当微信系统响应系统繁忙时，最大重试次数
   * 默认：5次
   * </pre>
   */
  void setMaxRetryTimes(int maxRetryTimes);

  /**
   * 获取WxOpConfigStorage 对象
   *
   * @return WxOpConfigStorage
   */
  WxOpConfigStorage getWxOpConfigStorage();


  /**
   * 返回素材相关接口方法的实现类对象，以方便调用其各个接口
   *
   * @return WxOpMaterialService
   */
  WxOpMaterialService getMaterialService();

  /**
   * 返回用户相关接口方法的实现类对象，以方便调用其各个接口
   *
   * @return WxOpUserService
   */
  WxOpUserService getUserService();


  /**
   * 返回数据分析统计相关接口方法的实现类对象，以方便调用其各个接口
   *
   * @return WxOpDataCubeService
   */
  WxOpDataCubeService getDataCubeService();

}
