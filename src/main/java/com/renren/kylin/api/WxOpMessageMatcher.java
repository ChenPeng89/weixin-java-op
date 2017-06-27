package com.renren.kylin.api;

import com.renren.kylin.bean.message.WxOpXmlMessage;

/**
 * 消息匹配器，用在消息路由的时候
 */
public interface WxOpMessageMatcher {

  /**
   * 消息是否匹配某种模式
   * @param message
   */
  boolean match(WxOpXmlMessage message);

}
