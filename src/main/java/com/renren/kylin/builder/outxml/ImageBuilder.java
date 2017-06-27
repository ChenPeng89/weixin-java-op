package com.renren.kylin.builder.outxml;

import com.renren.kylin.bean.message.WxOpXmlOutImageMessage;

/**
 * 图片消息builder
 * @author chanjarster
 */
public final class ImageBuilder extends BaseBuilder<ImageBuilder, WxOpXmlOutImageMessage> {

  private String mediaId;

  public ImageBuilder mediaId(String media_id) {
    this.mediaId = media_id;
    return this;
  }

  @Override
  public WxOpXmlOutImageMessage build() {
    WxOpXmlOutImageMessage m = new WxOpXmlOutImageMessage();
    setCommon(m);
    m.setMediaId(this.mediaId);
    return m;
  }

}
