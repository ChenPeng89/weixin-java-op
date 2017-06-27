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
import com.renren.kylin.bean.material.WxOpMaterialNewsBatchGetResult;
import me.chanjar.weixin.common.util.json.GsonHelper;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class WxOpMaterialNewsBatchGetGsonAdapter implements JsonDeserializer<WxOpMaterialNewsBatchGetResult> {

  @Override
  public WxOpMaterialNewsBatchGetResult deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
    WxOpMaterialNewsBatchGetResult wxOpMaterialNewsBatchGetResult = new WxOpMaterialNewsBatchGetResult();
    JsonObject json = jsonElement.getAsJsonObject();
    if (json.get("total_count") != null && !json.get("total_count").isJsonNull()) {
      wxOpMaterialNewsBatchGetResult.setTotalCount(GsonHelper.getAsInteger(json.get("total_count")));
    }
    if (json.get("item_count") != null && !json.get("item_count").isJsonNull()) {
      wxOpMaterialNewsBatchGetResult.setItemCount(GsonHelper.getAsInteger(json.get("item_count")));
    }
    if (json.get("item") != null && !json.get("item").isJsonNull()) {
      JsonArray item = json.getAsJsonArray("item");
      List<WxOpMaterialNewsBatchGetResult.WxMaterialNewsBatchGetNewsItem> items = new ArrayList<>();
      for (JsonElement anItem : item) {
        JsonObject articleInfo = anItem.getAsJsonObject();
        items.add(WxOpGsonBuilder.create().fromJson(articleInfo, WxOpMaterialNewsBatchGetResult.WxMaterialNewsBatchGetNewsItem.class));
      }
      wxOpMaterialNewsBatchGetResult.setItems(items);
    }
    return wxOpMaterialNewsBatchGetResult;
  }
}
