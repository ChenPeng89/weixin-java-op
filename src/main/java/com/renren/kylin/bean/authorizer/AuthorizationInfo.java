package com.renren.kylin.bean.authorizer;

import java.io.Serializable;
import java.util.List;

/**
 * @author : peng.chen5@renren-inc.com
 * @Time : 2017/6/14 下午3:50
 */
public class AuthorizationInfo implements Serializable {
  private static final long serialVersionUID = 6842472207480563578L;

  private String appid;
  private List<FuncInfo> func_info;

  public String getAppid() {
    return appid;
  }

  public void setAppid(String appid) {
    this.appid = appid;
  }

  public List<FuncInfo> getFunc_info() {
    return func_info;
  }

  public void setFunc_info(List<FuncInfo> func_info) {
    this.func_info = func_info;
  }
}
