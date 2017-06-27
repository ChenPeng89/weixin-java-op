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
import com.renren.kylin.bean.material.WxOpMaterialUploadResult;
import me.chanjar.weixin.common.util.json.GsonHelper;

import java.lang.reflect.Type;

/**
 * @author codepiano
 */
public class WxOpMaterialUploadResultAdapter implements JsonDeserializer<WxOpMaterialUploadResult> {

  @Override
  public WxOpMaterialUploadResult deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    WxOpMaterialUploadResult uploadResult = new WxOpMaterialUploadResult();
    JsonObject uploadResultJsonObject = json.getAsJsonObject();

    if (uploadResultJsonObject.get("url") != null && !uploadResultJsonObject.get("url").isJsonNull()) {
      uploadResult.setUrl(GsonHelper.getAsString(uploadResultJsonObject.get("url")));
    }
    if (uploadResultJsonObject.get("media_id") != null && !uploadResultJsonObject.get("media_id").isJsonNull()) {
      uploadResult.setMediaId(GsonHelper.getAsString(uploadResultJsonObject.get("media_id")));
    }
    return uploadResult;
  }

}
