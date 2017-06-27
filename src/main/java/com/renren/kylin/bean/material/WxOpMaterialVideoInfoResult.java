package com.renren.kylin.bean.material;


import com.renren.kylin.util.json.WxOpGsonBuilder;

import java.io.Serializable;

public class WxOpMaterialVideoInfoResult implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 1269131745333792202L;
  private String title;
  private String description;
  private String downUrl;

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getDownUrl() {
    return this.downUrl;
  }

  public void setDownUrl(String downUrl) {
    this.downUrl = downUrl;
  }

  public static WxOpMaterialVideoInfoResult fromJson(String json) {
    return WxOpGsonBuilder.create().fromJson(json, WxOpMaterialVideoInfoResult.class);
  }

  @Override
  public String toString() {
    return "WxOpMaterialVideoInfoResult [title=" + this.title + ", description=" + this.description + ", downUrl=" + this.downUrl + "]";
  }

}
