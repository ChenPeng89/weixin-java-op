package com.renren.kylin.bean.store;

import com.google.gson.annotations.SerializedName;
import com.renren.kylin.util.json.WxOpGsonBuilder;
import me.chanjar.weixin.common.util.ToStringUtils;

import java.util.List;

/**
 * 门店列表结果类
 * @author <a href="https://github.com/binarywang">binarywang(Binary Wang)</a>
 *         Created by Binary Wang on 2016-09-27.
 *
 */
public class WxOpStoreListResult {
  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }

  public static WxOpStoreListResult fromJson(String json) {
    return WxOpGsonBuilder.create().fromJson(json, WxOpStoreListResult.class);
  }

  /**
   * 错误码，0为正常
   */
  @SerializedName("errcode")
  private Integer errCode;

  /**
   * 错误信息
   */
  @SerializedName("errmsg")
  private String errMsg;

  /**
   * 门店信息列表
   */
  @SerializedName("business_list")
  private List<WxOpStoreInfo> businessList;

  /**
   * 门店信息总数
   */
  @SerializedName("total_count")
  private Integer totalCount;

  public Integer getTotalCount() {
    return this.totalCount;
  }

  public void setTotalCount(Integer totalCount) {
    this.totalCount = totalCount;
  }

  public Integer getErrCode() {
    return this.errCode;
  }

  public void setErrCode(Integer errCode) {
    this.errCode = errCode;
  }

  public String getErrMsg() {
    return this.errMsg;
  }

  public void setErrMsg(String errMsg) {
    this.errMsg = errMsg;
  }

  public List<WxOpStoreInfo> getBusinessList() {
    return this.businessList;
  }

  public void setBusinessList(List<WxOpStoreInfo> businessList) {
    this.businessList = businessList;
  }

}
