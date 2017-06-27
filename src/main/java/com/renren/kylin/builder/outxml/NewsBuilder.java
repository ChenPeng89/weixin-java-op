package com.renren.kylin.builder.outxml;

import com.renren.kylin.bean.message.WxOpXmlOutNewsMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * 图文消息builder
 * @author chanjarster
 */
public final class NewsBuilder extends BaseBuilder<NewsBuilder, WxOpXmlOutNewsMessage> {

  protected final List<WxOpXmlOutNewsMessage.Item> articles = new ArrayList<>();

  public NewsBuilder addArticle(WxOpXmlOutNewsMessage.Item item) {
    this.articles.add(item);
    return this;
  }

  @Override
  public WxOpXmlOutNewsMessage build() {
    WxOpXmlOutNewsMessage m = new WxOpXmlOutNewsMessage();
    for(WxOpXmlOutNewsMessage.Item item : this.articles) {
      m.addArticle(item);
    }
    setCommon(m);
    return m;
  }

}
