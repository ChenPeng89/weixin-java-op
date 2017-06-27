package com.renren.kylin.bean.device;

import com.google.gson.annotations.SerializedName;
import com.renren.kylin.util.json.WxOpGsonBuilder;

/**
 * Created by keungtung on 10/12/2016.
 */
public class WxDeviceBindResult extends AbstractDeviceBean{
  @SerializedName("base_resp")
  private BaseResp baseResp;

  public static WxDeviceBindResult fromJson(String json) {
    return WxOpGsonBuilder.create().fromJson(json, WxDeviceBindResult.class);
  }

  public BaseResp getBaseResp() {
    return baseResp;
  }

  public void setBaseResp(BaseResp baseResp) {
    this.baseResp = baseResp;
  }
}
