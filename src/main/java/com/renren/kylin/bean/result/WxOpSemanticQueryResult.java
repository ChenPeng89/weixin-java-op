package com.renren.kylin.bean.result;


import com.renren.kylin.util.json.WxOpGsonBuilder;

import java.io.Serializable;

/**
 * 语义理解查询结果对象
 *
 * http://mp.weixin.qq.com/wiki/index.php?title=语义理解
 *
 * @author Daniel Qian
 */
public class WxOpSemanticQueryResult implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 4811088544804441365L;
  private String query;
  private String type;
  private String semantic;
  private String result;
  private String answer;
  private String text;

  public String getQuery() {
    return this.query;
  }

  public void setQuery(String query) {
    this.query = query;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getSemantic() {
    return this.semantic;
  }

  public void setSemantic(String semantic) {
    this.semantic = semantic;
  }

  public String getResult() {
    return this.result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public String getAnswer() {
    return this.answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }

  public String getText() {
    return this.text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public static WxOpSemanticQueryResult fromJson(String json) {
    return WxOpGsonBuilder.create().fromJson(json, WxOpSemanticQueryResult.class);
  }

}
