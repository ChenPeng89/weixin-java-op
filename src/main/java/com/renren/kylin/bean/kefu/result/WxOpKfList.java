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
public class WxOpKfList {
  @SerializedName("kf_list")
  private List<WxOpKfInfo> kfList;

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }

  public List<WxOpKfInfo> getKfList() {
    return this.kfList;
  }

  public void setKfList(List<WxOpKfInfo> kfList) {
    this.kfList = kfList;
  }

  public static WxOpKfList fromJson(String json) {
    return WxOpGsonBuilder.INSTANCE.create().fromJson(json, WxOpKfList.class);
  }
}
