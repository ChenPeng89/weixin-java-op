/*
 * KINGSTAR MEDIA SOLUTIONS Co.,LTD. Copyright c 2005-2013. All rights reserved.
 *
 * This source code is the property of KINGSTAR MEDIA SOLUTIONS LTD. It is intended
 * only for the use of KINGSTAR MEDIA application development. Reengineering, reproduction
 * arose from modification of the original source, or other redistribution of this source
 * is not permitted without written permission of the KINGSTAR MEDIA SOLUTIONS LTD.
 */
package com.renren.kylin.util.json;

import com.google.gson.*;
import com.renren.kylin.bean.result.WxOpUser;
import me.chanjar.weixin.common.util.json.GsonHelper;

import java.lang.reflect.Type;

public class WxOpUserGsonAdapter implements JsonDeserializer<WxOpUser> {

  @Override
  public WxOpUser deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    JsonObject o = json.getAsJsonObject();
    WxOpUser wxOpUser = new WxOpUser();
    Integer subscribe = GsonHelper.getInteger(o, "subscribe");
    if (subscribe != null) {
      wxOpUser.setSubscribe(!new Integer(0).equals(subscribe));
    }
    wxOpUser.setCity(GsonHelper.getString(o, "city"));
    wxOpUser.setCountry(GsonHelper.getString(o, "country"));
    wxOpUser.setHeadImgUrl(GsonHelper.getString(o, "headimgurl"));
    wxOpUser.setLanguage(GsonHelper.getString(o, "language"));
    wxOpUser.setNickname(GsonHelper.getString(o, "nickname"));
    wxOpUser.setOpenId(GsonHelper.getString(o, "openid"));
    wxOpUser.setProvince(GsonHelper.getString(o, "province"));
    wxOpUser.setSubscribeTime(GsonHelper.getLong(o, "subscribe_time"));
    wxOpUser.setUnionId(GsonHelper.getString(o, "unionid"));
    Integer sexId = GsonHelper.getInteger(o, "sex");
    wxOpUser.setRemark(GsonHelper.getString(o, "remark"));
    wxOpUser.setGroupId(GsonHelper.getInteger(o, "groupid"));
    wxOpUser.setTagIds(GsonHelper.getLongArray(o, "tagid_list"));
    wxOpUser.setSexId(sexId);
    if (new Integer(1).equals(sexId)) {
      wxOpUser.setSex("男");
    } else if (new Integer(2).equals(sexId)) {
      wxOpUser.setSex("女");
    } else {
      wxOpUser.setSex("未知");
    }
    return wxOpUser;
  }

}
