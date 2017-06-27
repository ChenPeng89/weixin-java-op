package com.renren.kylin.bean.result;


import com.renren.kylin.util.json.WxOpGsonBuilder;

import java.io.Serializable;

/**
 * 换取二维码的Ticket
 *
 * @author chanjarster
 */
public class WxOpQrCodeTicket implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 5777119669111011584L;
  protected String ticket;
  protected int expire_seconds = -1;
  protected String url;

  public String getTicket() {
    return this.ticket;
  }

  public void setTicket(String ticket) {
    this.ticket = ticket;
  }

  /**
   * 如果返回-1说明是永久
   */
  public int getExpire_seconds() {
    return this.expire_seconds;
  }

  public void setExpire_seconds(int expire_seconds) {
    this.expire_seconds = expire_seconds;
  }

  public String getUrl() {
    return this.url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public static WxOpQrCodeTicket fromJson(String json) {
    return WxOpGsonBuilder.INSTANCE.create().fromJson(json, WxOpQrCodeTicket.class);
  }

  @Override
  public String toString() {
    return WxOpGsonBuilder.INSTANCE.create().toJson(this);
  }
}
