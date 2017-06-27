package com.renren.kylin.bean.authorizer;

import java.io.Serializable;

/**
 * @author : peng.chen5@renren-inc.com
 * @Time : 2017/6/14 下午3:46
 */
public class VerifyTypeInfo implements Serializable {
  private static final long serialVersionUID = 547891010631101659L;
  private int id;
  public void setId(int id) {
    this.id = id;
  }
  public int getId() {
    return id;
  }
}
