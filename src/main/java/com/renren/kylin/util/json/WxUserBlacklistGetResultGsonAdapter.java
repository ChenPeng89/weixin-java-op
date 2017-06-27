package com.renren.kylin.util.json;

import com.google.gson.*;
import com.renren.kylin.bean.result.WxOpUserBlacklistGetResult;
import me.chanjar.weixin.common.util.json.GsonHelper;

import java.lang.reflect.Type;

/**
 * @author miller
 */
public class WxUserBlacklistGetResultGsonAdapter implements JsonDeserializer<WxOpUserBlacklistGetResult> {
  @Override
  public WxOpUserBlacklistGetResult deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    JsonObject o = json.getAsJsonObject();
    WxOpUserBlacklistGetResult wxOpUserBlacklistGetResult = new WxOpUserBlacklistGetResult();
    wxOpUserBlacklistGetResult.setTotal(GsonHelper.getInteger(o, "total"));
    wxOpUserBlacklistGetResult.setCount(GsonHelper.getInteger(o, "count"));
    wxOpUserBlacklistGetResult.setNextOpenid(GsonHelper.getString(o, "next_openid"));
    if (o.get("data") != null && !o.get("data").isJsonNull() && !o.get("data").getAsJsonObject().get("openid").isJsonNull()) {
      JsonArray data = o.get("data").getAsJsonObject().get("openid").getAsJsonArray();
      for (int i = 0; i < data.size(); i++) {
        wxOpUserBlacklistGetResult.getOpenidList().add(GsonHelper.getAsString(data.get(i)));
      }
    }
    return wxOpUserBlacklistGetResult;
  }
}
