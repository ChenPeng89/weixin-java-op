package com.renren.kylin.api.impl;

import com.google.gson.JsonObject;
import com.renren.kylin.api.WxOpKefuService;
import com.renren.kylin.api.WxOpService;
import com.renren.kylin.bean.kefu.WxOpKefuMessage;
import com.renren.kylin.bean.kefu.request.WxOpKfAccountRequest;
import com.renren.kylin.bean.kefu.request.WxOpKfSessionRequest;
import com.renren.kylin.bean.kefu.result.*;
import me.chanjar.weixin.common.bean.result.WxError;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.http.MediaUploadRequestExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Date;

/**
 *
 * @author Binary Wang
 *
 */
public class WxOpKefuServiceImpl implements WxOpKefuService {
  protected final Logger log = LoggerFactory
      .getLogger(WxOpKefuServiceImpl.class);
  private static final String API_URL_PREFIX = "https://api.weixin.qq.com/customservice";
  private static final String API_URL_PREFIX_WITH_CGI_BIN = "https://api.weixin.qq.com/cgi-bin/customservice";
  private WxOpService wxOpService;

  public WxOpKefuServiceImpl(WxOpService wxOpService) {
    this.wxOpService = wxOpService;
  }

  @Override
  public boolean sendKefuMessage(WxOpKefuMessage message, String appId)
      throws WxErrorException {
    String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send";
    String responseContent = this.wxOpService.post(url, message.toJson() , appId);
    return responseContent != null;
  }

  @Override
  public WxOpKfList kfList(String appId) throws WxErrorException {
    String url = API_URL_PREFIX_WITH_CGI_BIN + "/getkflist";
    String responseContent = this.wxOpService.get(url, null , appId);
    return WxOpKfList.fromJson(responseContent);
  }

  @Override
  public WxOpKfOnlineList kfOnlineList(String appId) throws WxErrorException {
    String url = API_URL_PREFIX_WITH_CGI_BIN + "/getonlinekflist";
    String responseContent = this.wxOpService.get(url, null , appId);
    return WxOpKfOnlineList.fromJson(responseContent);
  }

  @Override
  public boolean kfAccountAdd(WxOpKfAccountRequest request ,String appId)
      throws WxErrorException {
    String url = API_URL_PREFIX + "/kfaccount/add";
    String responseContent = this.wxOpService.post(url, request.toJson(), appId);
    return responseContent != null;
  }

  @Override
  public boolean kfAccountUpdate(WxOpKfAccountRequest request,String appId)
      throws WxErrorException {
    String url = API_URL_PREFIX + "/kfaccount/update";
    String responseContent = this.wxOpService.post(url, request.toJson(), appId);
    return responseContent != null;
  }

  @Override
  public boolean kfAccountInviteWorker(WxOpKfAccountRequest request,String appId) throws WxErrorException {
    String url = API_URL_PREFIX + "/kfaccount/inviteworker";
    String responseContent = this.wxOpService.post(url, request.toJson(), appId);
    return responseContent != null;
  }

  @Override
  public boolean kfAccountUploadHeadImg(String kfAccount, File imgFile,String appId)
      throws WxErrorException {
    String url = API_URL_PREFIX + "/kfaccount/uploadheadimg?kf_account=" + kfAccount;
    WxMediaUploadResult responseContent = this.wxOpService
        .execute(new MediaUploadRequestExecutor(), url, imgFile, appId);
    return responseContent != null;
  }

  @Override
  public boolean kfAccountDel(String kfAccount,String appId) throws WxErrorException {
    String url = API_URL_PREFIX + "/kfaccount/del?kf_account=" + kfAccount;
    String responseContent = this.wxOpService.get(url, null, appId);
    return responseContent != null;
  }

  @Override
  public boolean kfSessionCreate(String openid, String kfAccount,String appId)
      throws WxErrorException {
    WxOpKfSessionRequest request = new WxOpKfSessionRequest(kfAccount, openid);
    String url = API_URL_PREFIX + "/kfsession/create";
    String responseContent = this.wxOpService.post(url, request.toJson(), appId);
    return responseContent != null;
  }

  @Override
  public boolean kfSessionClose(String openid, String kfAccount,String appId)
      throws WxErrorException {
    WxOpKfSessionRequest request = new WxOpKfSessionRequest(kfAccount, openid);
    String url = API_URL_PREFIX + "/kfsession/close";
    String responseContent = this.wxOpService.post(url, request.toJson(), appId);
    return responseContent != null;
  }

  @Override
  public WxOpKfSessionGetResult kfSessionGet(String openid, String appId)
      throws WxErrorException {
    String url = API_URL_PREFIX + "/kfsession/getsession?openid=" + openid;
    String responseContent = this.wxOpService.get(url, null, appId);
    return WxOpKfSessionGetResult.fromJson(responseContent);
  }

  @Override
  public WxOpKfSessionList kfSessionList(String kfAccount, String appId)
      throws WxErrorException {
    String url = API_URL_PREFIX + "/kfsession/getsessionlist?kf_account=" + kfAccount;
    String responseContent = this.wxOpService.get(url, null, appId);
    return WxOpKfSessionList.fromJson(responseContent);
  }

  @Override
  public WxOpKfSessionWaitCaseList kfSessionGetWaitCase(String appId)
      throws WxErrorException {
    String url = API_URL_PREFIX + "/kfsession/getwaitcase";
    String responseContent = this.wxOpService.get(url, null, appId);
    return WxOpKfSessionWaitCaseList.fromJson(responseContent);
  }

  @Override
  public WxOpKfMsgList kfMsgList(Date startTime, Date endTime, Long msgId, Integer number,String appId) throws WxErrorException {
    if(number > 10000){
      throw new WxErrorException(WxError.newBuilder().setErrorMsg("非法参数请求，每次最多查询10000条记录！").build());
    }

    if(startTime.after(endTime)){
      throw new WxErrorException(WxError.newBuilder().setErrorMsg("起始时间不能晚于结束时间！").build());
    }

    String url = API_URL_PREFIX + "/msgrecord/getmsglist";

    JsonObject param = new JsonObject();
    param.addProperty("starttime", startTime.getTime() / 1000); //starttime	起始时间，unix时间戳
    param.addProperty("endtime", endTime.getTime() / 1000); //endtime	结束时间，unix时间戳，每次查询时段不能超过24小时
    param.addProperty("msgid", msgId); //msgid	消息id顺序从小到大，从1开始
    param.addProperty("number", number); //number	每次获取条数，最多10000条

    String responseContent = this.wxOpService.post(url, param.toString(), appId);

    return WxOpKfMsgList.fromJson(responseContent);
  }

  @Override
  public WxOpKfMsgList kfMsgList(Date startTime, Date endTime,String appId) throws WxErrorException {
    int number = 10000;
    WxOpKfMsgList result =  this.kfMsgList(startTime,endTime, 1L, number, appId);

    if(result != null && result.getNumber() == number){
      Long msgId = result.getMsgId();
      WxOpKfMsgList followingResult =  this.kfMsgList(startTime,endTime, msgId, number, appId);
      while(followingResult != null  && followingResult.getRecords().size() > 0){
        result.getRecords().addAll(followingResult.getRecords());
        result.setNumber(result.getNumber() + followingResult.getNumber());
        result.setMsgId(followingResult.getMsgId());
        followingResult = this.kfMsgList(startTime,endTime, followingResult.getMsgId(), number, appId);
      }
    }

    return result;
  }

}
