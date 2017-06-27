package com.renren.kylin.builder.kefu;

import com.renren.kylin.bean.kefu.WxOpKefuMessage;
import me.chanjar.weixin.common.api.WxConsts;

import java.util.ArrayList;
import java.util.List;

/**
 * 图文消息builder
 * <pre>
 * 用法:
 * WxOpKefuMessage m = WxOpKefuMessage.NEWS().addArticle(article).toUser(...).build();
 * </pre>
 * @author chanjarster
 *
 */
public final class NewsBuilder extends BaseBuilder<NewsBuilder> {

  private List<WxOpKefuMessage.WxArticle> articles = new ArrayList<>();

  public NewsBuilder() {
    this.msgType = WxConsts.CUSTOM_MSG_NEWS;
  }

  public NewsBuilder addArticle(WxOpKefuMessage.WxArticle article) {
    this.articles.add(article);
    return this;
  }

  @Override
  public WxOpKefuMessage build() {
    WxOpKefuMessage m = super.build();
    m.setArticles(this.articles);
    return m;
  }
}
