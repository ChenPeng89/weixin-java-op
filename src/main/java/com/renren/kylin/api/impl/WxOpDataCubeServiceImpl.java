package com.renren.kylin.api.impl;

import com.google.gson.JsonObject;
import com.renren.kylin.api.WxOpDataCubeService;
import com.renren.kylin.api.WxOpService;
import com.renren.kylin.bean.datacube.*;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.apache.commons.lang3.time.FastDateFormat;

import java.text.Format;
import java.util.Date;
import java.util.List;

/**
 *  Created by Binary Wang on 2016/8/23.
 * @author binarywang (https://github.com/binarywang)
 */
public class WxOpDataCubeServiceImpl implements WxOpDataCubeService {
  private static final String API_URL_PREFIX = "https://api.weixin.qq.com/datacube";

  private final Format dateFormat = FastDateFormat.getInstance("yyyy-MM-dd");

  private WxOpService wxOpService;

  public WxOpDataCubeServiceImpl(WxOpService wxOpService) {
    this.wxOpService = wxOpService;
  }

  @Override
  public List<WxDataCubeUserSummary> getUserSummary(Date beginDate, Date endDate , String authorizerAppId) throws WxErrorException {
    String url = API_URL_PREFIX + "/getusersummary";
    JsonObject param = new JsonObject();
    param.addProperty("begin_date", this.dateFormat.format(beginDate));
    param.addProperty("end_date", this.dateFormat.format(endDate));
    String responseContent = this.wxOpService.post(url, param.toString() , authorizerAppId);
    return WxDataCubeUserSummary.fromJson(responseContent);
  }

  @Override
  public List<WxDataCubeUserCumulate> getUserCumulate(Date beginDate, Date endDate , String authorizerAppId) throws WxErrorException {
    String url = API_URL_PREFIX + "/getusercumulate";
    JsonObject param = new JsonObject();
    param.addProperty("begin_date", this.dateFormat.format(beginDate));
    param.addProperty("end_date", this.dateFormat.format(endDate));
    String responseContent = this.wxOpService.post(url, param.toString() , authorizerAppId);
    return WxDataCubeUserCumulate.fromJson(responseContent);
  }

  @Override
  public List<WxDataCubeArticleResult> getArticleSummary(Date beginDate, Date endDate , String authorizerAppId) throws WxErrorException {
    String url = API_URL_PREFIX + "/getarticlesummary";
    JsonObject param = new JsonObject();
    param.addProperty("begin_date", this.dateFormat.format(beginDate));
    param.addProperty("end_date", this.dateFormat.format(endDate));
    String responseContent = this.wxOpService.post(url, param.toString() , authorizerAppId);
    return WxDataCubeArticleResult.fromJson(responseContent);
  }

  @Override
  public List<WxDataCubeArticleTotal> getArticleTotal(Date beginDate, Date endDate , String authorizerAppId) throws WxErrorException {
    String url = API_URL_PREFIX + "/getarticletotal";
    JsonObject param = new JsonObject();
    param.addProperty("begin_date", this.dateFormat.format(beginDate));
    param.addProperty("end_date", this.dateFormat.format(endDate));
    String responseContent = this.wxOpService.post(url, param.toString() , authorizerAppId);
    return WxDataCubeArticleTotal.fromJson(responseContent);
  }

  @Override
  public List<WxDataCubeArticleResult> getUserRead(Date beginDate, Date endDate , String authorizerAppId) throws WxErrorException {
    String url = API_URL_PREFIX + "/getuserread";
    JsonObject param = new JsonObject();
    param.addProperty("begin_date", this.dateFormat.format(beginDate));
    param.addProperty("end_date", this.dateFormat.format(endDate));
    String responseContent = this.wxOpService.post(url, param.toString() , authorizerAppId);
    return WxDataCubeArticleResult.fromJson(responseContent);
  }

  @Override
  public List<WxDataCubeArticleResult> getUserReadHour(Date beginDate, Date endDate , String authorizerAppId) throws WxErrorException {
    String url = API_URL_PREFIX + "/getuserreadhour";
    JsonObject param = new JsonObject();
    param.addProperty("begin_date", this.dateFormat.format(beginDate));
    param.addProperty("end_date", this.dateFormat.format(endDate));
    String responseContent = this.wxOpService.post(url, param.toString() , authorizerAppId);
    return WxDataCubeArticleResult.fromJson(responseContent);
  }

  @Override
  public List<WxDataCubeArticleResult> getUserShare(Date beginDate, Date endDate , String authorizerAppId) throws WxErrorException {
    String url = API_URL_PREFIX + "/getusershare";
    JsonObject param = new JsonObject();
    param.addProperty("begin_date", this.dateFormat.format(beginDate));
    param.addProperty("end_date", this.dateFormat.format(endDate));
    String responseContent = this.wxOpService.post(url, param.toString() , authorizerAppId);
    return WxDataCubeArticleResult.fromJson(responseContent);
  }

  @Override
  public List<WxDataCubeArticleResult> getUserShareHour(Date beginDate, Date endDate , String authorizerAppId) throws WxErrorException {
    String url = API_URL_PREFIX + "/getusersharehour";
    JsonObject param = new JsonObject();
    param.addProperty("begin_date", this.dateFormat.format(beginDate));
    param.addProperty("end_date", this.dateFormat.format(endDate));
    String responseContent = this.wxOpService.post(url, param.toString() , authorizerAppId);
    return WxDataCubeArticleResult.fromJson(responseContent);
  }

  @Override
  public List<WxDataCubeMsgResult> getUpstreamMsg(Date beginDate, Date endDate , String authorizerAppId)
      throws WxErrorException {
    String url = API_URL_PREFIX + "/getupstreammsg";
    JsonObject param = new JsonObject();
    param.addProperty("begin_date", this.dateFormat.format(beginDate));
    param.addProperty("end_date", this.dateFormat.format(endDate));
    String responseContent = this.wxOpService.post(url, param.toString() , authorizerAppId);
    return WxDataCubeMsgResult.fromJson(responseContent);
  }

  @Override
  public List<WxDataCubeMsgResult> getUpstreamMsgHour(Date beginDate,
                                                      Date endDate , String authorizerAppId) throws WxErrorException {
    String url = API_URL_PREFIX + "/getupstreammsghour";
    JsonObject param = new JsonObject();
    param.addProperty("begin_date", this.dateFormat.format(beginDate));
    param.addProperty("end_date", this.dateFormat.format(endDate));
    String responseContent = this.wxOpService.post(url, param.toString() , authorizerAppId);
    return WxDataCubeMsgResult.fromJson(responseContent);
  }

  @Override
  public List<WxDataCubeMsgResult> getUpstreamMsgWeek(Date beginDate,
                                                      Date endDate , String authorizerAppId) throws WxErrorException {
    String url = API_URL_PREFIX + "/getupstreammsgweek";
    JsonObject param = new JsonObject();
    param.addProperty("begin_date", this.dateFormat.format(beginDate));
    param.addProperty("end_date", this.dateFormat.format(endDate));
    String responseContent = this.wxOpService.post(url, param.toString() , authorizerAppId);
    return WxDataCubeMsgResult.fromJson(responseContent);
  }

  @Override
  public List<WxDataCubeMsgResult> getUpstreamMsgMonth(Date beginDate,
                                                       Date endDate , String authorizerAppId) throws WxErrorException {
    String url = API_URL_PREFIX + "/getupstreammsgmonth";
    JsonObject param = new JsonObject();
    param.addProperty("begin_date", this.dateFormat.format(beginDate));
    param.addProperty("end_date", this.dateFormat.format(endDate));
    String responseContent = this.wxOpService.post(url, param.toString() , authorizerAppId);
    return WxDataCubeMsgResult.fromJson(responseContent);
  }

  @Override
  public List<WxDataCubeMsgResult> getUpstreamMsgDist(Date beginDate,
                                                      Date endDate , String authorizerAppId) throws WxErrorException {
    String url = API_URL_PREFIX + "/getupstreammsgdist";
    JsonObject param = new JsonObject();
    param.addProperty("begin_date", this.dateFormat.format(beginDate));
    param.addProperty("end_date", this.dateFormat.format(endDate));
    String responseContent = this.wxOpService.post(url, param.toString() , authorizerAppId);
    return WxDataCubeMsgResult.fromJson(responseContent);
  }

  @Override
  public List<WxDataCubeMsgResult> getUpstreamMsgDistWeek(Date beginDate,
                                                          Date endDate , String authorizerAppId) throws WxErrorException {
    String url = API_URL_PREFIX + "/getupstreammsgdistweek";
    JsonObject param = new JsonObject();
    param.addProperty("begin_date", this.dateFormat.format(beginDate));
    param.addProperty("end_date", this.dateFormat.format(endDate));
    String responseContent = this.wxOpService.post(url, param.toString() , authorizerAppId);
    return WxDataCubeMsgResult.fromJson(responseContent);
  }

  @Override
  public List<WxDataCubeMsgResult> getUpstreamMsgDistMonth(Date beginDate,
                                                           Date endDate , String authorizerAppId) throws WxErrorException {
    String url = API_URL_PREFIX + "/getupstreammsgdistmonth";
    JsonObject param = new JsonObject();
    param.addProperty("begin_date", this.dateFormat.format(beginDate));
    param.addProperty("end_date", this.dateFormat.format(endDate));
    String responseContent = this.wxOpService.post(url, param.toString() , authorizerAppId);
    return WxDataCubeMsgResult.fromJson(responseContent);
  }

  @Override
  public List<WxDataCubeInterfaceResult> getInterfaceSummary(Date beginDate,
                                                             Date endDate , String authorizerAppId) throws WxErrorException {
    String url = API_URL_PREFIX + "/getinterfacesummary";
    JsonObject param = new JsonObject();
    param.addProperty("begin_date", this.dateFormat.format(beginDate));
    param.addProperty("end_date", this.dateFormat.format(endDate));
    String responseContent = this.wxOpService.post(url, param.toString() , authorizerAppId);
    return WxDataCubeInterfaceResult.fromJson(responseContent);
  }

  @Override
  public List<WxDataCubeInterfaceResult> getInterfaceSummaryHour(Date beginDate,
                                                                 Date endDate , String authorizerAppId) throws WxErrorException {
    String url = API_URL_PREFIX + "/getinterfacesummaryhour";
    JsonObject param = new JsonObject();
    param.addProperty("begin_date", this.dateFormat.format(beginDate));
    param.addProperty("end_date", this.dateFormat.format(endDate));
    String responseContent = this.wxOpService.post(url, param.toString() , authorizerAppId);
    return WxDataCubeInterfaceResult.fromJson(responseContent);
  }
}
