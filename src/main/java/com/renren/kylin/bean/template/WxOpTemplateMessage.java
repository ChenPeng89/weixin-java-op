package com.renren.kylin.bean.template;


import com.renren.kylin.util.json.WxOpGsonBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 参考 http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433751277&token=&lang=zh_CN 发送模板消息接口部分
 */
public class WxOpTemplateMessage implements Serializable {
  private static final long serialVersionUID = 5063374783759519418L;

  /**
   * 接收者openid
   */
  private String toUser;

  /**
   * 模板ID
   */
  private String templateId;

  /**
   * <pre>
   * 跳小程序所需数据，不需跳小程序可不用传该数据
   * url和miniprogram都是非必填字段，若都不传则模板无跳转；若都传，会优先跳转至小程序。
   * 开发者可根据实际需要选择其中一种跳转方式即可。当用户的微信客户端版本不支持跳小程序时，将会跳转至url。
   * </pre>
   */
  private String url;
  /**
   * 模板跳转链接
   * @see #url
   */
  private MiniProgram miniProgram;

  /**
   * 模板数据
   */
  private List< WxOpTemplateData> data = new ArrayList<>();

  public WxOpTemplateMessage() {
  }

  public String getToUser() {
    return this.toUser;
  }

  public void setToUser(String toUser) {
    this.toUser = toUser;
  }

  public String getTemplateId() {
    return this.templateId;
  }

  public void setTemplateId(String templateId) {
    this.templateId = templateId;
  }

  public String getUrl() {
    return this.url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public List< WxOpTemplateData> getData() {
    return this.data;
  }

  public void setData(List< WxOpTemplateData> data) {
    this.data = data;
  }

  public void addWxOpTemplateData( WxOpTemplateData datum) {
    this.data.add(datum);
  }

  public MiniProgram getMiniProgram() {
    return this.miniProgram;
  }

  public void setMiniProgram(MiniProgram miniProgram) {
    this.miniProgram = miniProgram;
  }

  public String toJson() {
    return WxOpGsonBuilder.INSTANCE.create().toJson(this);
  }

  public static WxOpTemplateMessageBuilder builder() {
    return new WxOpTemplateMessageBuilder();
  }

  public static class MiniProgram {
    private String appid;
    private String pagePath;

    public MiniProgram() {
    }

    public MiniProgram(String appid, String pagePath) {
      this.appid = appid;
      this.pagePath = pagePath;
    }

    public String getAppid() {
      return this.appid;
    }

    public void setAppid(String appid) {
      this.appid = appid;
    }

    public String getPagePath() {
      return this.pagePath;
    }

    public void setPagePath(String pagePath) {
      this.pagePath = pagePath;
    }
  }

  public static class WxOpTemplateMessageBuilder {
    private String toUser;
    private String templateId;
    private String url;
    private List< WxOpTemplateData> data = new ArrayList<>();
    private MiniProgram miniProgram;

    public WxOpTemplateMessageBuilder toUser(String toUser) {
      this.toUser = toUser;
      return this;
    }

    public WxOpTemplateMessageBuilder templateId(String templateId) {
      this.templateId = templateId;
      return this;
    }

    public WxOpTemplateMessageBuilder url(String url) {
      this.url = url;
      return this;
    }

    public WxOpTemplateMessageBuilder data(List<WxOpTemplateData> data) {
      this.data = data;
      return this;
    }

    public WxOpTemplateMessageBuilder from( WxOpTemplateMessage origin) {
      this.toUser(origin.toUser);
      this.templateId(origin.templateId);
      this.url(origin.url);
      this.data(origin.data);
      return this;
    }

    public WxOpTemplateMessageBuilder miniProgram(MiniProgram miniProgram) {
      this.miniProgram = miniProgram;
      return this;
    }

    public  WxOpTemplateMessage build() {
       WxOpTemplateMessage m = new  WxOpTemplateMessage();
      m.toUser = this.toUser;
      m.templateId = this.templateId;
      m.url = this.url;
      m.data = this.data;
      m.miniProgram = this.miniProgram;
      return m;
    }
  }

}
