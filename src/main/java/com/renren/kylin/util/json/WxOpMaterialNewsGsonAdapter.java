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

import java.lang.reflect.Type;

public class WxOpMaterialNewsGsonAdapter implements JsonSerializer<WxOpMaterialNews>, JsonDeserializer<WxOpMaterialNews> {

  @Override
  public JsonElement serialize(WxOpMaterialNews wxOpMaterialNews, Type typeOfSrc, JsonSerializationContext context) {
    JsonObject newsJson = new JsonObject();

    JsonArray articleJsonArray = new JsonArray();
    for (WxOpMaterialNews.WxOpMaterialNewsArticle article : wxOpMaterialNews.getArticles()) {
      JsonObject articleJson = WxOpGsonBuilder.create().toJsonTree(article, WxOpMaterialNews.WxOpMaterialNewsArticle.class).getAsJsonObject();
      articleJsonArray.add(articleJson);
    }
    newsJson.add("articles", articleJsonArray);

    return newsJson;
  }

  @Override
  public WxOpMaterialNews deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
    WxOpMaterialNews wxOpMaterialNews = new WxOpMaterialNews();
    JsonObject json = jsonElement.getAsJsonObject();
    if (json.get("news_item") != null && !json.get("news_item").isJsonNull()) {
      JsonArray articles = json.getAsJsonArray("news_item");
      for (JsonElement article1 : articles) {
        JsonObject articleInfo = article1.getAsJsonObject();
        WxOpMaterialNews.WxOpMaterialNewsArticle article = WxOpGsonBuilder.create().fromJson(articleInfo, WxOpMaterialNews.WxOpMaterialNewsArticle.class);
        wxOpMaterialNews.addArticle(article);
      }
    }
    return wxOpMaterialNews;
  }
}
