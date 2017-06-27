package com.renren.kylin.bean.store;

import com.google.gson.annotations.SerializedName;
import me.chanjar.weixin.common.util.ToStringUtils;

public class WxOpStoreInfo {
  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }

  @SerializedName("base_info")
  private WxOpStoreBaseInfo baseInfo;

  public WxOpStoreBaseInfo getBaseInfo() {
    return this.baseInfo;
  }

  public void setBaseInfo(WxOpStoreBaseInfo baseInfo) {
    this.baseInfo = baseInfo;
  }

}
