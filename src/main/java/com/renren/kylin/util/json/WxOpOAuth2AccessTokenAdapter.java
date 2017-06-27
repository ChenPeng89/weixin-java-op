package com.renren.kylin.util.json;

import com.google.gson.*;
import com.renren.kylin.bean.result.WxOpOAuth2AccessToken;
import me.chanjar.weixin.common.util.json.GsonHelper;

import java.lang.reflect.Type;

public class WxOpOAuth2AccessTokenAdapter implements JsonDeserializer<WxOpOAuth2AccessToken> {

  @Override
  public WxOpOAuth2AccessToken deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws
    JsonParseException {
    WxOpOAuth2AccessToken accessToken = new WxOpOAuth2AccessToken();
    JsonObject accessTokenJsonObject = json.getAsJsonObject();

    if (accessTokenJsonObject.get("access_token") != null && !accessTokenJsonObject.get("access_token").isJsonNull()) {
      accessToken.setAccessToken(GsonHelper.getAsString(accessTokenJsonObject.get("access_token")));
    }
    if (accessTokenJsonObject.get("expires_in") != null && !accessTokenJsonObject.get("expires_in").isJsonNull()) {
      accessToken.setExpiresIn(GsonHelper.getAsPrimitiveInt(accessTokenJsonObject.get("expires_in")));
    }
    if (accessTokenJsonObject.get("refresh_token") != null && !accessTokenJsonObject.get("refresh_token").isJsonNull()) {
      accessToken.setRefreshToken(GsonHelper.getAsString(accessTokenJsonObject.get("refresh_token")));
    }
    if (accessTokenJsonObject.get("openid") != null && !accessTokenJsonObject.get("openid").isJsonNull()) {
      accessToken.setOpenId(GsonHelper.getAsString(accessTokenJsonObject.get("openid")));
    }
    if (accessTokenJsonObject.get("scope") != null && !accessTokenJsonObject.get("scope").isJsonNull()) {
      accessToken.setScope(GsonHelper.getAsString(accessTokenJsonObject.get("scope")));
    }
    if (accessTokenJsonObject.get("unionid") != null && !accessTokenJsonObject.get("unionid").isJsonNull()) {
      accessToken.setUnionId(GsonHelper.getAsString(accessTokenJsonObject.get("unionid")));
    }
    return accessToken;
  }

}
