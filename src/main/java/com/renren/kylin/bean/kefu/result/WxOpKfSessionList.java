package com.renren.kylin.bean.kefu.result;

import com.google.gson.annotations.SerializedName;
import com.renren.kylin.util.json.WxOpGsonBuilder;
import me.chanjar.weixin.common.util.ToStringUtils;

import java.util.List;

/**
 *
 * @author Binary Wang
 *
 */
public class WxOpKfSessionList {
  /**
   * 会话列表
   */
  @SerializedName("sessionlist")
  private List< WxOpKfSession> kfSessionList;

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }

  public static  WxOpKfSessionList fromJson(String json) {
    return WxOpGsonBuilder.INSTANCE.create().fromJson(json,
         WxOpKfSessionList.class);
  }

  public List< WxOpKfSession> getKfSessionList() {
    return this.kfSessionList;
  }

  public void setKfSessionList(List<WxOpKfSession> kfSessionList) {
    this.kfSessionList = kfSessionList;
  }
}
