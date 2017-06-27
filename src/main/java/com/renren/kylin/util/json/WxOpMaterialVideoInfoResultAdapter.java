package com.renren.kylin.util.json;

import com.google.gson.*;
import com.renren.kylin.bean.material.WxOpMaterialVideoInfoResult;
import me.chanjar.weixin.common.util.json.GsonHelper;

import java.lang.reflect.Type;

/**
 * @author codepiano
 */
public class WxOpMaterialVideoInfoResultAdapter implements JsonDeserializer<WxOpMaterialVideoInfoResult> {

  @Override
  public WxOpMaterialVideoInfoResult deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    WxOpMaterialVideoInfoResult uploadResult = new WxOpMaterialVideoInfoResult();
    JsonObject uploadResultJsonObject = json.getAsJsonObject();

    if (uploadResultJsonObject.get("title") != null && !uploadResultJsonObject.get("title").isJsonNull()) {
      uploadResult.setTitle(GsonHelper.getAsString(uploadResultJsonObject.get("title")));
    }
    if (uploadResultJsonObject.get("description") != null && !uploadResultJsonObject.get("description").isJsonNull()) {
      uploadResult.setDescription(GsonHelper.getAsString(uploadResultJsonObject.get("description")));
    }
    if (uploadResultJsonObject.get("down_url") != null && !uploadResultJsonObject.get("down_url").isJsonNull()) {
      uploadResult.setDownUrl(GsonHelper.getAsString(uploadResultJsonObject.get("down_url")));
    }
    return uploadResult;
  }

}
