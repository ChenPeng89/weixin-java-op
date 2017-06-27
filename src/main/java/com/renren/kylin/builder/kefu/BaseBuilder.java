package com.renren.kylin.builder.kefu;


import com.renren.kylin.bean.kefu.WxOpKefuMessage;

public class BaseBuilder<T> {
  protected String msgType;
  protected String toUser;

  @SuppressWarnings("unchecked")
	public T toUser(String toUser) {
    this.toUser = toUser;
    return (T) this;
  }

  public WxOpKefuMessage build() {
    WxOpKefuMessage m = new WxOpKefuMessage();
    m.setMsgType(this.msgType);
    m.setToUser(this.toUser);
    return m;
  }
}
