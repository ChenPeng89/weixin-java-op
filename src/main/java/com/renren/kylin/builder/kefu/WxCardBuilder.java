package com.renren.kylin.builder.kefu;

import com.renren.kylin.bean.kefu.WxOpKefuMessage;
import me.chanjar.weixin.common.api.WxConsts;

/**
 * 卡券消息builder
 * <pre>
 * 用法: WxOpKefuMessage m = WxOpKefuMessage.WXCARD().cardId(...).toUser(...).build();
 * </pre>
 * @author mgcnrx11
 *
 */
public final class WxCardBuilder extends BaseBuilder<WxCardBuilder> {
  private String cardId;

  public WxCardBuilder() {
    this.msgType = WxConsts.CUSTOM_MSG_WXCARD;
  }

  public WxCardBuilder cardId(String cardId) {
    this.cardId = cardId;
    return this;
  }

  @Override
  public WxOpKefuMessage build() {
    WxOpKefuMessage m = super.build();
    m.setCardId(this.cardId);
    return m;
  }
}
