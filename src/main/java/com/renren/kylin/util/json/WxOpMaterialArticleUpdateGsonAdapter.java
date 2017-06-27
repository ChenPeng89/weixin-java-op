/*
 * KINGSTAR MEDIA SOLUTIONS Co.,LTD. Copyright c 2005-2013. All rights reserved.
 *
 * This source code is the property of KINGSTAR MEDIA SOLUTIONS LTD. It is intended
 * only for the use of KINGSTAR MEDIA application development. Reengineering, reproduction
 * arose from modification of the original source, or other redistribution of this source
 * is not permitted without written permission of the KINGSTAR MEDIA SOLUTIONS LTD.
 */
package com.renren.kylin.util.json;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.renren.kylin.bean.material.WxOpMaterialArticleUpdate;
import com.renren.kylin.bean.material.WxOpMaterialNews;

import java.lang.reflect.Type;

public class WxOpMaterialArticleUpdateGsonAdapter implements JsonSerializer<WxOpMaterialArticleUpdate> {

  @Override
  public JsonElement serialize(WxOpMaterialArticleUpdate wxOpMaterialArticleUpdate, Type typeOfSrc, JsonSerializationContext context) {
    JsonObject articleUpdateJson = new JsonObject();
    articleUpdateJson.addProperty("media_id", wxOpMaterialArticleUpdate.getMediaId());
    articleUpdateJson.addProperty("index", wxOpMaterialArticleUpdate.getIndex());
    articleUpdateJson.add("articles", WxOpGsonBuilder.create().toJsonTree(wxOpMaterialArticleUpdate.getArticles(), WxOpMaterialNews.WxOpMaterialNewsArticle.class));
    return articleUpdateJson;
  }

}
