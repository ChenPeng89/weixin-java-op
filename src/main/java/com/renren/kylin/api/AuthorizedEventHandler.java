package com.renren.kylin.api;

import com.renren.kylin.bean.message.WxOpXmlMessage;

/**
 * 处理微信服务器向 【授权服务器URL】 推送消息 : 推送授权相关通知 handler
 * @author : peng.chen5@renren-inc.com
 * @Time : 2017/6/13 下午8:53
 */
public interface AuthorizedEventHandler {
  String handle(WxOpXmlMessage wxOpXmlMessage);

}
