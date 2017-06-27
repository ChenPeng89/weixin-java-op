package com.renren.kylin.bean.menu;

import com.google.gson.annotations.SerializedName;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.common.bean.menu.WxMenuRule;
import me.chanjar.weixin.common.util.ToStringUtils;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.util.List;

/**
 * <pre>
 *   公众号专用的菜单类，可能包含个性化菜单
 * Created by Binary Wang on 2017-1-17.
 * @author <a href="https://github.com/binarywang">binarywang(Binary Wang)</a>
 * </pre>
 */
public class WxOpMenu {
  @SerializedName("menu")
  private WxOpConditionalMenu menu;

  @SerializedName("conditionalmenu")
  private List<WxOpConditionalMenu> conditionalMenu;

  public static  WxOpMenu fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json,  WxOpMenu.class);
  }

  public WxOpConditionalMenu getMenu() {
    return menu;
  }

  public void setMenu(WxOpConditionalMenu menu) {
    this.menu = menu;
  }

  public List<WxOpConditionalMenu> getConditionalMenu() {
    return conditionalMenu;
  }

  public void setConditionalMenu(List<WxOpConditionalMenu> conditionalMenu) {
    this.conditionalMenu = conditionalMenu;
  }

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }

  public String toJson() {
    return WxGsonBuilder.create().toJson(this);
  }

  public static class WxOpConditionalMenu {
    @SerializedName("button")
    private List<WxMenuButton> buttons;
    @SerializedName("matchrule")
    private WxMenuRule rule;
    @SerializedName("menuid")
    private String menuId;

    @Override
    public String toString() {
      return ToStringUtils.toSimpleString(this);
    }

    public List<WxMenuButton> getButtons() {
      return buttons;
    }

    public void setButtons(List<WxMenuButton> buttons) {
      this.buttons = buttons;
    }

    public WxMenuRule getRule() {
      return rule;
    }

    public void setRule(WxMenuRule rule) {
      this.rule = rule;
    }

    public String getMenuId() {
      return menuId;
    }

    public void setMenuId(String menuId) {
      this.menuId = menuId;
    }
  }

}
