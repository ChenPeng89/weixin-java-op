package com.renren.kylin.bean.authorizer;

import java.io.Serializable;

/**
 * @author : peng.chen5@renren-inc.com
 * @Time : 2017/6/14 下午3:48
 */
public class BusinessInfo implements Serializable {
  private static final long serialVersionUID = 8073953040793710448L;

  private int open_store;
  private int open_scan;
  private int open_pay;
  private int open_card;
  private int open_shake;

  public int getOpen_store() {
    return open_store;
  }

  public void setOpen_store(int open_store) {
    this.open_store = open_store;
  }

  public int getOpen_scan() {
    return open_scan;
  }

  public void setOpen_scan(int open_scan) {
    this.open_scan = open_scan;
  }

  public int getOpen_pay() {
    return open_pay;
  }

  public void setOpen_pay(int open_pay) {
    this.open_pay = open_pay;
  }

  public int getOpen_card() {
    return open_card;
  }

  public void setOpen_card(int open_card) {
    this.open_card = open_card;
  }

  public int getOpen_shake() {
    return open_shake;
  }

  public void setOpen_shake(int open_shake) {
    this.open_shake = open_shake;
  }
}
