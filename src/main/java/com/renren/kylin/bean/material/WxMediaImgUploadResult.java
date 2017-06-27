package com.renren.kylin.bean.material;


import com.renren.kylin.util.json.WxOpGsonBuilder;

import java.io.Serializable;

/**
 * @author miller
 */
public class WxMediaImgUploadResult implements Serializable {
  /**
   *
   */
  private static final long serialVersionUID = 1996392453428768829L;
  private String url;

  public static WxMediaImgUploadResult fromJson(String json) {
    return WxOpGsonBuilder.create().fromJson(json, WxMediaImgUploadResult.class);
  }

  public String getUrl() {
    return this.url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
