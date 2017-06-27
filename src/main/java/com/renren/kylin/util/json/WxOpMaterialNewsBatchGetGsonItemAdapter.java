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
import com.renren.kylin.bean.material.WxOpMaterialNews;
import com.renren.kylin.bean.material.WxOpMaterialNewsBatchGetResult;
import me.chanjar.weixin.common.util.json.GsonHelper;

import java.lang.reflect.Type;
import java.util.Date;

public class WxOpMaterialNewsBatchGetGsonItemAdapter implements JsonDeserializer<WxOpMaterialNewsBatchGetResult.WxMaterialNewsBatchGetNewsItem> {

  @Override
  public WxOpMaterialNewsBatchGetResult.WxMaterialNewsBatchGetNewsItem deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
    WxOpMaterialNewsBatchGetResult.WxMaterialNewsBatchGetNewsItem wxMaterialNewsBatchGetNewsItem = new WxOpMaterialNewsBatchGetResult.WxMaterialNewsBatchGetNewsItem();
    JsonObject json = jsonElement.getAsJsonObject();
    if (json.get("media_id") != null && !json.get("media_id").isJsonNull()) {
      wxMaterialNewsBatchGetNewsItem.setMediaId(GsonHelper.getAsString(json.get("media_id")));
    }
    if (json.get("update_time") != null && !json.get("update_time").isJsonNull()) {
      wxMaterialNewsBatchGetNewsItem.setUpdateTime(new Date(1000 * GsonHelper.getAsLong(json.get("update_time"))));
    }
    if (json.get("content") != null && !json.get("content").isJsonNull()) {
      JsonObject newsItem = json.getAsJsonObject("content");
      wxMaterialNewsBatchGetNewsItem.setContent(WxOpGsonBuilder.create().fromJson(newsItem, WxOpMaterialNews.class));
    }
    return wxMaterialNewsBatchGetNewsItem;
  }
}
