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
import com.renren.kylin.bean.WxOpMassNews;

import java.lang.reflect.Type;

public class WxOpMassNewsGsonAdapter implements JsonSerializer<WxOpMassNews>, JsonDeserializer<WxOpMassNews> {

  @Override
  public JsonElement serialize(WxOpMassNews message, Type typeOfSrc, JsonSerializationContext context) {
    JsonObject newsJson = new JsonObject();

    JsonArray articleJsonArray = new JsonArray();
    for (WxOpMassNews.WxOpMassNewsArticle article : message.getArticles()) {
      JsonObject articleJson = WxOpGsonBuilder.create().toJsonTree(article, WxOpMassNews.WxOpMassNewsArticle.class).getAsJsonObject();
      articleJsonArray.add(articleJson);
    }
    newsJson.add("articles", articleJsonArray);

    return newsJson;
  }

  @Override
  public WxOpMassNews deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
    WxOpMassNews wxOpMassNews = new WxOpMassNews();
    JsonObject json = jsonElement.getAsJsonObject();
    if (json.get("media_id") != null && !json.get("media_id").isJsonNull()) {
      JsonArray articles = json.getAsJsonArray("articles");
      for (JsonElement article1 : articles) {
        JsonObject articleInfo = article1.getAsJsonObject();
        WxOpMassNews.WxOpMassNewsArticle article = WxOpGsonBuilder.create().fromJson(articleInfo, WxOpMassNews.WxOpMassNewsArticle.class);
        wxOpMassNews.addArticle(article);
      }
    }
    return wxOpMassNews;
  }
}
