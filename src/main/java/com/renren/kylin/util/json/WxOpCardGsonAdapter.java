package com.renren.kylin.util.json;

import com.google.gson.*;
import com.renren.kylin.bean.WxOpCard;
import me.chanjar.weixin.common.util.json.GsonHelper;

import java.lang.reflect.Type;

/**
 * Created by YuJian on 15/11/11.
 *
 * @author YuJian
 * @version 15/11/11
 */
public class WxOpCardGsonAdapter implements JsonDeserializer<WxOpCard> {

  @Override
  public WxOpCard deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext
    jsonDeserializationContext) throws JsonParseException {
    WxOpCard card = new WxOpCard();
    JsonObject jsonObject = jsonElement.getAsJsonObject();

    card.setCardId(GsonHelper.getString(jsonObject, "card_id"));
    card.setBeginTime(GsonHelper.getLong(jsonObject, "begin_time"));
    card.setEndTime(GsonHelper.getLong(jsonObject, "end_time"));

    return card;
  }

}
