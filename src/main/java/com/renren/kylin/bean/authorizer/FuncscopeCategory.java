package com.renren.kylin.bean.authorizer;

import java.io.Serializable;

/**
 * @author : peng.chen5@renren-inc.com
 * @Time : 2017/6/14 下午3:49
 */
public class FuncscopeCategory implements Serializable {
  private static final long serialVersionUID = 5318116798876082171L;

  private int id;
  public void setId(int id) {
    this.id = id;
  }
  public int getId() {
    return id;
  }
}
