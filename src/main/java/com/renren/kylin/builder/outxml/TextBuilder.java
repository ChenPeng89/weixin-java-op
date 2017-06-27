package com.renren.kylin.builder.outxml;

import com.renren.kylin.bean.message.WxOpXmlOutTextMessage;

/**
 * 文本消息builder
 * @author chanjarster
 *
 */
public final class TextBuilder extends BaseBuilder<TextBuilder, WxOpXmlOutTextMessage> {
  private String content;

  public TextBuilder content(String content) {
    this.content = content;
    return this;
  }

  @Override
  public WxOpXmlOutTextMessage build() {
    WxOpXmlOutTextMessage m = new WxOpXmlOutTextMessage();
    setCommon(m);
    m.setContent(this.content);
    return m;
  }
}
