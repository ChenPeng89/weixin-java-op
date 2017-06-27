package com.renren.kylin.bean.authorizer;

import java.io.Serializable;

/**
 * @author : peng.chen5@renren-inc.com
 * @Time : 2017/6/14 下午3:45
 */
public class ServiceTypeInfo implements Serializable {
  private static final long serialVersionUID = -7697391239656045076L;
  private int id;
  public void setId(int id) {
    this.id = id;
  }
  public int getId() {
    return id;
  }
}
