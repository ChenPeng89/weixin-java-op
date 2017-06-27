package com.renren.kylin.bean.result;


import com.renren.kylin.util.json.WxOpGsonBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author miller
 */
public class WxOpUserBlacklistGetResult {
  protected int total = -1;
  protected int count = -1;
  protected List<String> openidList = new ArrayList<>();
  protected String nextOpenid;

  public static WxOpUserBlacklistGetResult fromJson(String json) {
    return WxOpGsonBuilder.INSTANCE.create().fromJson(json, WxOpUserBlacklistGetResult.class);
  }

  public int getTotal() {
    return this.total;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  public int getCount() {
    return this.count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public List<String> getOpenidList() {
    return this.openidList;
  }

  public void setOpenidList(List<String> openidList) {
    this.openidList = openidList;
  }

  public String getNextOpenid() {
    return this.nextOpenid;
  }

  public void setNextOpenid(String nextOpenid) {
    this.nextOpenid = nextOpenid;
  }

  @Override
  public String toString() {
    return WxOpGsonBuilder.INSTANCE.create().toJson(this);
  }
}
