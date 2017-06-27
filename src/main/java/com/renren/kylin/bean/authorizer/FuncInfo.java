package com.renren.kylin.bean.authorizer;

import java.io.Serializable;

/**
 * @author : peng.chen5@renren-inc.com
 * @Time : 2017/6/14 下午3:49
 */
public class FuncInfo implements Serializable {
  private static final long serialVersionUID = 2771386909792183457L;

  private FuncscopeCategory funcscope_category;

  public FuncscopeCategory getFuncscope_category() {
    return funcscope_category;
  }

  public void setFuncscope_category(FuncscopeCategory funcscope_category) {
    this.funcscope_category = funcscope_category;
  }
}
