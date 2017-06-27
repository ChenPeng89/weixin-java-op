package com.renren.kylin.bean.kefu.result;

import com.google.gson.annotations.SerializedName;
import com.renren.kylin.util.json.WxOpGsonBuilder;
import me.chanjar.weixin.common.util.ToStringUtils;

import java.util.List;

/**
 *
 * @author Binary Wang
 *
 */
public class WxOpKfSessionWaitCaseList {
  /**
   * count 未接入会话数量
   */
  @SerializedName("count")
  private Long count;

  /**
   * waitcaselist 未接入会话列表，最多返回100条数据
   */
  @SerializedName("waitcaselist")
  private List<WxOpKfSession> kfSessionWaitCaseList;

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }

  public static WxOpKfSessionWaitCaseList fromJson(String json) {
    return WxOpGsonBuilder.INSTANCE.create().fromJson(json,
        WxOpKfSessionWaitCaseList.class);
  }

  public List<WxOpKfSession> getKfSessionWaitCaseList() {
    return this.kfSessionWaitCaseList;
  }

  public void setKfSessionWaitCaseList(List<WxOpKfSession> kfSessionWaitCaseList) {
    this.kfSessionWaitCaseList = kfSessionWaitCaseList;
  }

}
