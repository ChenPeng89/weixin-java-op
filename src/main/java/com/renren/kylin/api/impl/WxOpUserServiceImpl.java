package com.renren.kylin.api.impl;

import com.google.gson.JsonObject;
import com.renren.kylin.api.WxOpService;
import com.renren.kylin.api.WxOpUserService;
import com.renren.kylin.bean.WxOpUserQuery;
import com.renren.kylin.bean.result.WxOpUser;
import com.renren.kylin.bean.result.WxOpUserList;
import me.chanjar.weixin.common.exception.WxErrorException;

import java.util.List;

/**
 * Created by Binary Wang on 2016/7/21.
 */
public class WxOpUserServiceImpl implements WxOpUserService {
  private static final String API_URL_PREFIX = "https://api.weixin.qq.com/cgi-bin/user";
  private WxOpService wxOpService;

  public WxOpUserServiceImpl(WxOpService wxOpService) {
    this.wxOpService = wxOpService;
  }

  @Override
  public void userUpdateRemark(String openid, String remark , String appId) throws WxErrorException {
    String url = API_URL_PREFIX + "/info/updateremark";
    JsonObject json = new JsonObject();
    json.addProperty("openid", openid);
    json.addProperty("remark", remark);
    this.wxOpService.post(url, json.toString() , appId);
  }

  @Override
  public WxOpUser userInfo(String openid , String appId) throws WxErrorException {
    return this.userInfo(openid, null , appId);
  }

  @Override
  public WxOpUser userInfo(String openid, String lang , String appId) throws WxErrorException {
    String url = API_URL_PREFIX + "/info";
    lang = lang == null ? "zh_CN" : lang;
    String responseContent = this.wxOpService.get(url,
        "openid=" + openid + "&lang=" + lang , appId);
    return WxOpUser.fromJson(responseContent);
  }

  @Override
  public WxOpUserList userList(String next_openid , String appId) throws WxErrorException {
    String url = API_URL_PREFIX + "/get";
    String responseContent = this.wxOpService.get(url,
        next_openid == null ? null : "next_openid=" + next_openid  , appId);
    return WxOpUserList.fromJson(responseContent);
  }

  @Override
  public List<WxOpUser> userInfoList(List<String> openids , String appId)
      throws WxErrorException {
    return this.userInfoList(new WxOpUserQuery(openids) , appId);
  }

  @Override
  public List<WxOpUser> userInfoList(WxOpUserQuery userQuery , String appId) throws WxErrorException {
    String url = API_URL_PREFIX + "/info/batchget";
    String responseContent = this.wxOpService.post(url,
        userQuery.toJsonString() , appId);
    return WxOpUser.fromJsonList(responseContent);
  }

}
