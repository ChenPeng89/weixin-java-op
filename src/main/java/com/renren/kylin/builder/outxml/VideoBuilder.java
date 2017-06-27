package com.renren.kylin.builder.outxml;

import com.renren.kylin.bean.message.WxOpXmlOutVideoMessage;

/**
 * 视频消息builder
 * @author chanjarster
 *
 */
public final class VideoBuilder extends BaseBuilder<VideoBuilder, WxOpXmlOutVideoMessage> {

  private String mediaId;
  private String title;
  private String description;

  public VideoBuilder title(String title) {
    this.title = title;
    return this;
  }
  public VideoBuilder description(String description) {
    this.description = description;
    return this;
  }
  public VideoBuilder mediaId(String mediaId) {
    this.mediaId = mediaId;
    return this;
  }

  @Override
  public WxOpXmlOutVideoMessage build() {
    WxOpXmlOutVideoMessage m = new WxOpXmlOutVideoMessage();
    setCommon(m);
    m.setTitle(this.title);
    m.setDescription(this.description);
    m.setMediaId(this.mediaId);
    return m;
  }

}
