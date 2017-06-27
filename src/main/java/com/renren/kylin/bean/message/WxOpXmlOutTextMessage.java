package com.renren.kylin.bean.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.util.xml.XStreamCDataConverter;

@XStreamAlias("xml")
public class WxOpXmlOutTextMessage extends WxOpXmlOutMessage {

  /**
   *
   */
  private static final long serialVersionUID = -3972786455288763361L;
  @XStreamAlias("Content")
  @XStreamConverter(value=XStreamCDataConverter.class)
  private String content;

  public WxOpXmlOutTextMessage() {
    this.msgType = WxConsts.XML_MSG_TEXT;
  }

  public String getContent() {
    return this.content;
  }

  public void setContent(String content) {
    this.content = content;
  }


}
