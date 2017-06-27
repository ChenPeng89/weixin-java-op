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
import com.renren.kylin.bean.result.WxOpUserList;
import me.chanjar.weixin.common.util.json.GsonHelper;

import java.lang.reflect.Type;

public class WxUserListGsonAdapter implements JsonDeserializer<WxOpUserList> {

  @Override
  public WxOpUserList deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    JsonObject o = json.getAsJsonObject();
    WxOpUserList wxOpUserList = new WxOpUserList();
    wxOpUserList.setTotal(GsonHelper.getLong(o, "total"));
    wxOpUserList.setCount(GsonHelper.getInteger(o, "count"));
    wxOpUserList.setNextOpenid(GsonHelper.getString(o, "next_openid"));
    if (o.get("data") != null && !o.get("data").isJsonNull() && !o.get("data").getAsJsonObject().get("openid").isJsonNull()) {
      JsonArray data = o.get("data").getAsJsonObject().get("openid").getAsJsonArray();
      for (int i = 0; i < data.size(); i++) {
        wxOpUserList.getOpenids().add(GsonHelper.getAsString(data.get(i)));
      }
    }
    return wxOpUserList;
  }

}
