package com.renren.kylin.bean.kefu.result;

import com.google.gson.annotations.SerializedName;
import com.renren.kylin.util.json.WxOpGsonBuilder;
import me.chanjar.weixin.common.util.ToStringUtils;

import java.util.List;

/**
 * Created by Binary Wang on 2016/7/15.
 */
public class WxOpKfMsgList {
  @SerializedName("recordlist")
  private List<WxOpKfMsgRecord> records;

  @SerializedName("number")
  private Integer number;

  @SerializedName("msgid")
  private Long msgId;

  public List<WxOpKfMsgRecord> getRecords() {
    return this.records;
  }

  public void setRecords(List<WxOpKfMsgRecord> records) {
    this.records = records;
  }

  public Integer getNumber() {
    return this.number;
  }

  public void setNumber(Integer number) {
    this.number = number;
  }

  public Long getMsgId() {
    return this.msgId;
  }

  public void setMsgId(Long msgId) {
    this.msgId = msgId;
  }

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }

  public static WxOpKfMsgList fromJson(String responseContent) {
    return WxOpGsonBuilder.INSTANCE.create().fromJson(responseContent, WxOpKfMsgList.class);
  }
}
