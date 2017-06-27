package com.renren.kylin.bean.result;


import com.renren.kylin.util.json.WxOpGsonBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * 关注者列表
 * @author chanjarster
 *
 */
public class WxOpUserList {

  protected long total = -1;
  protected int count = -1;
  protected List<String> openids = new ArrayList<>();
  protected String nextOpenid;
  public long getTotal() {
    return this.total;
  }
  public void setTotal(long total) {
    this.total = total;
  }
  public int getCount() {
    return this.count;
  }
  public void setCount(int count) {
    this.count = count;
  }
  public List<String> getOpenids() {
    return this.openids;
  }
  public void setOpenids(List<String> openids) {
    this.openids = openids;
  }
  public String getNextOpenid() {
    return this.nextOpenid;
  }
  public void setNextOpenid(String nextOpenid) {
    this.nextOpenid = nextOpenid;
  }

  public static WxOpUserList fromJson(String json) {
    return WxOpGsonBuilder.INSTANCE.create().fromJson(json, WxOpUserList.class);
  }

  @Override
  public String toString() {
    return  WxOpGsonBuilder.INSTANCE.create().toJson(this);
  }
}
