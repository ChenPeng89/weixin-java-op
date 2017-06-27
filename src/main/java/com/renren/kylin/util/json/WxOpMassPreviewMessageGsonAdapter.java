package com.renren.kylin.util.json;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.renren.kylin.bean.WxOpMassPreviewMessage;
import me.chanjar.weixin.common.api.WxConsts;

import java.lang.reflect.Type;

/**
 * @author miller
 */
public class WxOpMassPreviewMessageGsonAdapter implements JsonSerializer<WxOpMassPreviewMessage> {
  @Override
  public JsonElement serialize(WxOpMassPreviewMessage wxOpMassPreviewMessage, Type type, JsonSerializationContext jsonSerializationContext) {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("towxname", wxOpMassPreviewMessage.getToWxUserName());
    jsonObject.addProperty("touser", wxOpMassPreviewMessage.getToWxUserOpenid());
    if (WxConsts.MASS_MSG_NEWS.equals(wxOpMassPreviewMessage.getMsgType())) {
      JsonObject news = new JsonObject();
      news.addProperty("media_id", wxOpMassPreviewMessage.getMediaId());
      jsonObject.add(WxConsts.MASS_MSG_NEWS, news);
    }
    if (WxConsts.MASS_MSG_TEXT.equals(wxOpMassPreviewMessage.getMsgType())) {
      JsonObject sub = new JsonObject();
      sub.addProperty("content", wxOpMassPreviewMessage.getContent());
      jsonObject.add(WxConsts.MASS_MSG_TEXT, sub);
    }
    if (WxConsts.MASS_MSG_VOICE.equals(wxOpMassPreviewMessage.getMsgType())) {
      JsonObject sub = new JsonObject();
      sub.addProperty("media_id", wxOpMassPreviewMessage.getMediaId());
      jsonObject.add(WxConsts.MASS_MSG_VOICE, sub);
    }
    if (WxConsts.MASS_MSG_IMAGE.equals(wxOpMassPreviewMessage.getMsgType())) {
      JsonObject sub = new JsonObject();
      sub.addProperty("media_id", wxOpMassPreviewMessage.getMediaId());
      jsonObject.add(WxConsts.MASS_MSG_IMAGE, sub);
    }
    if (WxConsts.MASS_MSG_VIDEO.equals(wxOpMassPreviewMessage.getMsgType())) {
      JsonObject sub = new JsonObject();
      sub.addProperty("media_id", wxOpMassPreviewMessage.getMediaId());
      jsonObject.add(WxConsts.MASS_MSG_VIDEO, sub);
    }
    jsonObject.addProperty("msgtype", wxOpMassPreviewMessage.getMsgType());
    return jsonObject;
  }
}
