package com.renren.kylin.builder.kefu;

import com.renren.kylin.bean.kefu.WxOpKefuMessage;
import me.chanjar.weixin.common.api.WxConsts;

/**
 * 图文消息builder
 * <pre>
 * 用法:
 * WxOpKefuMessage m = WxOpKefuMessage.NEWS().mediaId("xxxxx").toUser(...).build();
 * </pre>
 * @author Binary Wang
 *
 */
public final class OpNewsBuilder extends BaseBuilder<OpNewsBuilder> {
  private String mediaId;

  public OpNewsBuilder() {
    this.msgType = WxConsts.CUSTOM_MSG_MPNEWS;
  }

  public OpNewsBuilder mediaId(String mediaId) {
    this.mediaId = mediaId;
    return this;
  }

  @Override
  public WxOpKefuMessage build() {
    WxOpKefuMessage m = super.build();
    m.setOpNewsMediaId(this.mediaId);
    return m;
  }
}
