package com.renren.kylin.builder.kefu;

import com.renren.kylin.bean.kefu.WxOpKefuMessage;
import me.chanjar.weixin.common.api.WxConsts;

/**
 * 文本消息builder
 * <pre>
 * 用法: WxOpKefuMessage m = WxOpKefuMessage.TEXT().content(...).toUser(...).build();
 * </pre>
 * @author chanjarster
 *
 */
public final class TextBuilder extends BaseBuilder<TextBuilder> {
  private String content;

  public TextBuilder() {
    this.msgType = WxConsts.CUSTOM_MSG_TEXT;
  }

  public TextBuilder content(String content) {
    this.content = content;
    return this;
  }

  @Override
  public WxOpKefuMessage build() {
    WxOpKefuMessage m = super.build();
    m.setContent(this.content);
    return m;
  }
}
