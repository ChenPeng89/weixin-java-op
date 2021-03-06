package com.renren.kylin.bean.result;


import com.renren.kylin.util.json.WxOpGsonBuilder;

import java.io.Serializable;

/**
 * <pre>
 * 上传群发用的素材的结果
 * 视频和图文消息需要在群发前上传素材
 * </pre>
 * @author chanjarster
 *
 */
public class WxOpMassUploadResult implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 6568157943644994029L;
  private String type;
  private String mediaId;
  private long createdAt;

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getMediaId() {
    return this.mediaId;
  }

  public void setMediaId(String mediaId) {
    this.mediaId = mediaId;
  }

  public long getCreatedAt() {
    return this.createdAt;
  }

  public void setCreatedAt(long createdAt) {
    this.createdAt = createdAt;
  }

  public static WxOpMassUploadResult fromJson(String json) {
    return WxOpGsonBuilder.create().fromJson(json, WxOpMassUploadResult.class);
  }

  @Override
  public String toString() {
    return "WxUploadResult [type=" + this.type + ", media_id=" + this.mediaId + ", created_at=" + this.createdAt + "]";
  }

}
