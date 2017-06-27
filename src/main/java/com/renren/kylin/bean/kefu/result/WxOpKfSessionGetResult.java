package com.renren.kylin.bean.kefu.result;

import com.google.gson.annotations.SerializedName;
import com.renren.kylin.util.json.WxOpGsonBuilder;
import me.chanjar.weixin.common.util.ToStringUtils;

/**
 *
 * @author Binary Wang
 *
 */
public class WxOpKfSessionGetResult {
  /**
   * kf_account 正在接待的客服，为空表示没有人在接待
   */
  @SerializedName("kf_account")
  private String kfAccount;

  /**
   * createtime 会话接入的时间 ，UNIX时间戳
   */
  @SerializedName("createtime")
  private long createTime;

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }

  public static  WxOpKfSessionGetResult fromJson(String json) {
    return WxOpGsonBuilder.INSTANCE.create().fromJson(json,  WxOpKfSessionGetResult.class);
  }

  public String getKfAccount() {
    return this.kfAccount;
  }

  public void setKfAccount(String kfAccount) {
    this.kfAccount = kfAccount;
  }

  public long getCreateTime() {
    return this.createTime;
  }

  public void setCreateTime(long createTime) {
    this.createTime = createTime;
  }

}
