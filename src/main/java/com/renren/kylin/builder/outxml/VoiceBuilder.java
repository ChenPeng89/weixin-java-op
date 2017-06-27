package com.renren.kylin.builder.outxml;

import com.renren.kylin.bean.message.WxOpXmlOutVoiceMessage;

/**
 * 语音消息builder
 * @author chanjarster
 */
public final class VoiceBuilder extends BaseBuilder<VoiceBuilder, WxOpXmlOutVoiceMessage> {

  private String mediaId;

  public VoiceBuilder mediaId(String mediaId) {
    this.mediaId = mediaId;
    return this;
  }

  @Override
  public WxOpXmlOutVoiceMessage build() {
    WxOpXmlOutVoiceMessage m = new WxOpXmlOutVoiceMessage();
    setCommon(m);
    m.setMediaId(this.mediaId);
    return m;
  }

}
