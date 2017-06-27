package com.renren.kylin.bean.material;


import com.renren.kylin.util.json.WxOpGsonBuilder;

import java.io.Serializable;

public class WxOpMaterialUploadResult implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = -128818731449449537L;
  private String mediaId;
  private String url;

  public String getMediaId() {
    return this.mediaId;
  }

  public void setMediaId(String mediaId) {
    this.mediaId = mediaId;
  }

  public String getUrl() {
    return this.url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public static WxOpMaterialUploadResult fromJson(String json) {
    return WxOpGsonBuilder.create().fromJson(json, WxOpMaterialUploadResult.class);
  }

  @Override
  public String toString() {
    return "WxOpMaterialUploadResult [media_id=" + this.mediaId + ", url=" + this.url + "]";
  }

}

