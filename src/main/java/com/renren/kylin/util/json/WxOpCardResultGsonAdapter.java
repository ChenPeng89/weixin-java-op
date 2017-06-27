package com.renren.kylin.util.json;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.renren.kylin.bean.WxOpCard;
import com.renren.kylin.bean.result.WxOpCardResult;
import me.chanjar.weixin.common.util.json.GsonHelper;

import java.lang.reflect.Type;

/**
 * Created by YuJian on 15/11/11.
 *
 * @author YuJian
 * @version 15/11/11
 */
public class WxOpCardResultGsonAdapter implements JsonDeserializer<WxOpCardResult> {
  @Override
  public WxOpCardResult deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
    WxOpCardResult cardResult = new WxOpCardResult();
    JsonObject jsonObject = jsonElement.getAsJsonObject();

    cardResult.setOpenId(GsonHelper.getString(jsonObject, "openid"));
    cardResult.setErrorCode(GsonHelper.getString(jsonObject, "errcode"));
    cardResult.setErrorMsg(GsonHelper.getString(jsonObject, "errmsg"));
    cardResult.setCanConsume(GsonHelper.getBoolean(jsonObject, "can_consume"));
    cardResult.setUserCardStatus(GsonHelper.getString(jsonObject, "user_card_status"));

    WxOpCard card = WxOpGsonBuilder.INSTANCE.create().fromJson(jsonObject.get("card"),
      new TypeToken<WxOpCard>() {
      }.getType());

    cardResult.setCard(card);

    return cardResult;
  }
}
