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
public class WxOpKfOnlineList {
  @SerializedName("kf_online_list")
  private List<WxOpKfInfo> kfOnlineList;

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }

  public List<WxOpKfInfo> getKfOnlineList() {
    return this.kfOnlineList;
  }

  public void setKfOnlineList(List<WxOpKfInfo> kfOnlineList) {
    this.kfOnlineList = kfOnlineList;
  }

  public static WxOpKfOnlineList fromJson(String json) {
    return WxOpGsonBuilder.INSTANCE.create().fromJson(json, WxOpKfOnlineList.class);
  }
}
