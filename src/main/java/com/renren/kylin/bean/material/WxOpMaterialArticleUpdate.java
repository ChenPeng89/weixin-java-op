package com.renren.kylin.bean.material;


import com.renren.kylin.util.json.WxOpGsonBuilder;

import java.io.Serializable;

public class WxOpMaterialArticleUpdate implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = -7611963949517780270L;
  private String mediaId;
  private int index;
  private WxOpMaterialNews.WxOpMaterialNewsArticle articles;

  public String getMediaId() {
    return this.mediaId;
  }

  public void setMediaId(String mediaId) {
    this.mediaId = mediaId;
  }

  public int getIndex() {
    return this.index;
  }

  public void setIndex(int index) {
    this.index = index;
  }

  public WxOpMaterialNews.WxOpMaterialNewsArticle getArticles() {
    return this.articles;
  }

  public void setArticles(WxOpMaterialNews.WxOpMaterialNewsArticle articles) {
    this.articles = articles;
  }

  public String toJson() {
    return WxOpGsonBuilder.create().toJson(this);
  }

  @Override
  public String toString() {
    return "WxOpMaterialArticleUpdate [" + "mediaId=" + this.mediaId + ", index=" + this.index + ", articles=" + this.articles + "]";
  }
}
