package com.renren.kylin.builder.kefu;

import com.renren.kylin.bean.kefu.WxOpKefuMessage;
import me.chanjar.weixin.common.api.WxConsts;

/**
 * 语音消息builder
 * <pre>
 * 用法: WxOpKefuMessage m = WxOpKefuMessage.VOICE().mediaId(...).toUser(...).build();
 * </pre>
 * @author chanjarster
 *
 */
public final class VoiceBuilder extends BaseBuilder<VoiceBuilder> {
  private String mediaId;

  public VoiceBuilder() {
    this.msgType = WxConsts.CUSTOM_MSG_VOICE;
  }

  public VoiceBuilder mediaId(String media_id) {
    this.mediaId = media_id;
    return this;
  }

  @Override
  public WxOpKefuMessage build() {
    WxOpKefuMessage m = super.build();
    m.setMediaId(this.mediaId);
    return m;
  }
}
