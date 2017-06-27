package com.renren.kylin.util.json;

import com.google.gson.*;
import com.renren.kylin.bean.template.WxOpTemplateIndustry;
import me.chanjar.weixin.common.util.json.GsonHelper;

import java.lang.reflect.Type;

/**
 * @author miller
 */
public class WxOpIndustryGsonAdapter
  implements JsonSerializer<WxOpTemplateIndustry>, JsonDeserializer<WxOpTemplateIndustry> {
  private static WxOpTemplateIndustry.Industry convertFromJson(JsonObject json) {
    WxOpTemplateIndustry.Industry industry = new WxOpTemplateIndustry.Industry();
    industry.setFirstClass(GsonHelper.getString(json, "first_class"));
    industry.setSecondClass(GsonHelper.getString(json, "second_class"));
    return industry;
  }

  @Override
  public JsonElement serialize(WxOpTemplateIndustry wxOpIndustry, Type type,
                               JsonSerializationContext jsonSerializationContext) {
    JsonObject json = new JsonObject();
    json.addProperty("industry_id1", wxOpIndustry.getPrimaryIndustry().getId());
    json.addProperty("industry_id2", wxOpIndustry.getSecondIndustry().getId());
    return json;
  }

  @Override
  public WxOpTemplateIndustry deserialize(JsonElement jsonElement, Type type,
                                          JsonDeserializationContext jsonDeserializationContext)
    throws JsonParseException {
    WxOpTemplateIndustry wxOpIndustry = new WxOpTemplateIndustry();
    JsonObject primaryIndustry = jsonElement.getAsJsonObject()
      .get("primary_industry").getAsJsonObject();
    wxOpIndustry.setPrimaryIndustry(convertFromJson(primaryIndustry));
    JsonObject secondaryIndustry = jsonElement.getAsJsonObject()
      .get("secondary_industry").getAsJsonObject();
    wxOpIndustry.setSecondIndustry(convertFromJson(secondaryIndustry));
    return wxOpIndustry;
  }
}
