package com.renren.kylin.util.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.renren.kylin.bean.*;
import com.renren.kylin.bean.datacube.WxDataCubeUserCumulate;
import com.renren.kylin.bean.datacube.WxDataCubeUserSummary;
import com.renren.kylin.bean.kefu.*;
import com.renren.kylin.bean.material.*;
import com.renren.kylin.bean.result.*;
import com.renren.kylin.bean.template.WxOpTemplateIndustry;
import com.renren.kylin.bean.template.WxOpTemplateMessage;


public class WxOpGsonBuilder {

  public static final GsonBuilder INSTANCE = new GsonBuilder();

  static {
    INSTANCE.disableHtmlEscaping();
    INSTANCE.registerTypeAdapter(WxOpKefuMessage.class, new WxOpKefuMessageGsonAdapter());
    INSTANCE.registerTypeAdapter(WxOpMassNews.class, new WxOpMassNewsGsonAdapter());
    INSTANCE.registerTypeAdapter(WxOpMassTagMessage.class, new WxOpMassTagMessageGsonAdapter());
    INSTANCE.registerTypeAdapter(WxOpMassOpenIdsMessage.class, new WxOpMassOpenIdsMessageGsonAdapter());
    INSTANCE.registerTypeAdapter(WxOpUser.class, new WxOpUserGsonAdapter());
    INSTANCE.registerTypeAdapter(WxOpUserList.class, new WxUserListGsonAdapter());
    INSTANCE.registerTypeAdapter(WxOpMassVideo.class, new WxOpMassVideoAdapter());
    INSTANCE.registerTypeAdapter(WxOpMassSendResult.class, new WxOpMassSendResultAdapter());
    INSTANCE.registerTypeAdapter(WxOpMassUploadResult.class, new WxOpMassUploadResultAdapter());
    INSTANCE.registerTypeAdapter(WxOpQrCodeTicket.class, new WxQrCodeTicketAdapter());
    INSTANCE.registerTypeAdapter(WxOpTemplateMessage.class, new WxOpTemplateMessageGsonAdapter());
    INSTANCE.registerTypeAdapter(WxOpSemanticQueryResult.class, new WxOpSemanticQueryResultAdapter());
    INSTANCE.registerTypeAdapter(WxOpOAuth2AccessToken.class, new WxOpOAuth2AccessTokenAdapter());
    INSTANCE.registerTypeAdapter(WxDataCubeUserSummary.class, new WxOpUserSummaryGsonAdapter());
    INSTANCE.registerTypeAdapter(WxDataCubeUserCumulate.class, new WxOpUserCumulateGsonAdapter());
    INSTANCE.registerTypeAdapter(WxOpMaterialUploadResult.class, new WxOpMaterialUploadResultAdapter());
    INSTANCE.registerTypeAdapter(WxOpMaterialVideoInfoResult.class, new WxOpMaterialVideoInfoResultAdapter());
    INSTANCE.registerTypeAdapter(WxOpMassNews.WxOpMassNewsArticle.class, new WxOpMassNewsArticleGsonAdapter());
    INSTANCE.registerTypeAdapter(WxOpMaterialArticleUpdate.class, new WxOpMaterialArticleUpdateGsonAdapter());
    INSTANCE.registerTypeAdapter(WxOpMaterialCountResult.class, new WxOpMaterialCountResultAdapter());
    INSTANCE.registerTypeAdapter(WxOpMaterialNews.class, new WxOpMaterialNewsGsonAdapter());
    INSTANCE.registerTypeAdapter(WxOpMaterialNews.WxOpMaterialNewsArticle.class, new WxOpMaterialNewsArticleGsonAdapter());
    INSTANCE.registerTypeAdapter(WxOpMaterialNewsBatchGetResult.class, new WxOpMaterialNewsBatchGetGsonAdapter());
    INSTANCE.registerTypeAdapter(WxOpMaterialNewsBatchGetResult.WxMaterialNewsBatchGetNewsItem.class, new WxOpMaterialNewsBatchGetGsonItemAdapter());
    INSTANCE.registerTypeAdapter(WxOpMaterialFileBatchGetResult.class, new WxOpMaterialFileBatchGetGsonAdapter());
    INSTANCE.registerTypeAdapter(WxOpMaterialFileBatchGetResult.WxMaterialFileBatchGetNewsItem.class, new WxOpMaterialFileBatchGetGsonItemAdapter());
    INSTANCE.registerTypeAdapter(WxOpCardResult.class, new WxOpCardResultGsonAdapter());
    INSTANCE.registerTypeAdapter(WxOpCard.class, new WxOpCardGsonAdapter());
    INSTANCE.registerTypeAdapter(WxOpMassPreviewMessage.class, new WxOpMassPreviewMessageGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMediaImgUploadResult.class, new WxMediaImgUploadResultGsonAdapter());
    INSTANCE.registerTypeAdapter(WxOpTemplateIndustry.class, new WxOpIndustryGsonAdapter());
    INSTANCE.registerTypeAdapter(WxOpUserBlacklistGetResult.class, new WxUserBlacklistGetResultGsonAdapter());
  }

  public static Gson create() {
    return INSTANCE.create();
  }

}
