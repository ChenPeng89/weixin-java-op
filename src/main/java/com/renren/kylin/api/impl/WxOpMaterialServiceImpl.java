package com.renren.kylin.api.impl;

import com.renren.kylin.api.WxOpMaterialService;
import com.renren.kylin.api.WxOpService;
import com.renren.kylin.bean.material.*;
import com.renren.kylin.util.http.*;
import com.renren.kylin.util.json.WxOpGsonBuilder;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.result.WxError;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.fs.FileUtils;
import me.chanjar.weixin.common.util.http.MediaDownloadRequestExecutor;
import me.chanjar.weixin.common.util.http.MediaUploadRequestExecutor;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Binary Wang on 2016/7/21.
 */
public class WxOpMaterialServiceImpl implements WxOpMaterialService {
  private static final String MEDIA_API_URL_PREFIX = "https://api.weixin.qq.com/cgi-bin/media";
  private static final String MATERIAL_API_URL_PREFIX = "https://api.weixin.qq.com/cgi-bin/material";
  private WxOpService wxOpService;

  public WxOpMaterialServiceImpl(WxOpService wxOpService) {
    this.wxOpService = wxOpService;
  }

  @Override
  public WxMediaUploadResult mediaUpload(String mediaType, String fileType, InputStream inputStream , String appId) throws WxErrorException {
    try {
      return this.mediaUpload(mediaType, FileUtils.createTmpFile(inputStream, UUID.randomUUID().toString(), fileType) , appId);
    } catch (IOException e) {
      e.printStackTrace();
      throw new WxErrorException(WxError.newBuilder().setErrorMsg(e.getMessage()).build());
    }
  }

  @Override
  public WxMediaUploadResult mediaUpload(String mediaType, File file , String appId) throws WxErrorException {
    String url = MEDIA_API_URL_PREFIX + "/upload?type=" + mediaType;
    return this.wxOpService.execute(new MediaUploadRequestExecutor(), url, file , appId);
  }


  @Override
  public File mediaDownload(String media_id , String appId) throws WxErrorException {
    String url = MEDIA_API_URL_PREFIX + "/get";
    return this.wxOpService.execute(
      new MediaDownloadRequestExecutor(this.wxOpService.getWxOpConfigStorage().getTmpDirFile()),
      url,
      "media_id=" + media_id , appId);
  }

  @Override
  public WxMediaImgUploadResult mediaImgUpload(File file, String appId) throws WxErrorException {
    String url = MEDIA_API_URL_PREFIX + "/uploadimg";
    return this.wxOpService.execute(new MediaImgUploadRequestExecutor(), url, file , appId);
  }

  @Override
  public WxOpMaterialUploadResult materialFileUpload(String mediaType, WxOpMaterial material , String appId) throws WxErrorException {
    String url = MATERIAL_API_URL_PREFIX + "/add_material?type=" + mediaType;
    return this.wxOpService.execute(new MaterialUploadRequestExecutor(), url, material , appId);
  }

  @Override
  public WxOpMaterialUploadResult materialNewsUpload(WxOpMaterialNews news , String appId) throws WxErrorException {
    if (news == null || news.isEmpty()) {
      throw new IllegalArgumentException("news is empty!");
    }
    String url = MATERIAL_API_URL_PREFIX + "/add_news";
    String responseContent = this.wxOpService.post(url, news.toJson() , appId);
    return WxOpMaterialUploadResult.fromJson(responseContent);
  }

  @Override
  public InputStream materialImageOrVoiceDownload(String media_id , String appId) throws WxErrorException {
    String url = MATERIAL_API_URL_PREFIX + "/get_material";
    return this.wxOpService.execute(new MaterialVoiceAndImageDownloadRequestExecutor(this.wxOpService.getWxOpConfigStorage().getTmpDirFile()), url, media_id , appId);
  }

  @Override
  public WxOpMaterialVideoInfoResult materialVideoInfo(String media_id , String appId) throws WxErrorException {
    String url = MATERIAL_API_URL_PREFIX + "/get_material";
    return this.wxOpService.execute(new MaterialVideoInfoRequestExecutor(), url, media_id , appId);
  }

  @Override
  public WxOpMaterialNews materialNewsInfo(String media_id , String appId) throws WxErrorException {
    String url = MATERIAL_API_URL_PREFIX + "/get_material";
    return this.wxOpService.execute(new MaterialNewsInfoRequestExecutor(), url, media_id , appId);
  }

  @Override
  public boolean materialNewsUpdate(WxOpMaterialArticleUpdate wxOpMaterialArticleUpdate , String appId) throws WxErrorException {
    String url = MATERIAL_API_URL_PREFIX + "/update_news";
    String responseText = this.wxOpService.post(url, wxOpMaterialArticleUpdate.toJson() , appId);
    WxError wxError = WxError.fromJson(responseText);
    if (wxError.getErrorCode() == 0) {
      return true;
    } else {
      throw new WxErrorException(wxError);
    }
  }

  @Override
  public boolean materialDelete(String media_id , String appId) throws WxErrorException {
    String url = MATERIAL_API_URL_PREFIX + "/del_material";
    return this.wxOpService.execute(new MaterialDeleteRequestExecutor(), url, media_id , appId);
  }

  @Override
  public WxOpMaterialCountResult materialCount(String appId) throws WxErrorException {
    String url = MATERIAL_API_URL_PREFIX + "/get_materialcount";
    String responseText = this.wxOpService.get(url, null , appId);
    WxError wxError = WxError.fromJson(responseText);
    if (wxError.getErrorCode() == 0) {
      return WxOpGsonBuilder.create().fromJson(responseText, WxOpMaterialCountResult.class);
    } else {
      throw new WxErrorException(wxError);
    }
  }

  @Override
  public WxOpMaterialNewsBatchGetResult materialNewsBatchGet(int offset, int count , String appId) throws WxErrorException {
    String url = MATERIAL_API_URL_PREFIX + "/batchget_material";
    Map<String, Object> params = new HashMap<>();
    params.put("type", WxConsts.MATERIAL_NEWS);
    params.put("offset", offset);
    params.put("count", count);
    String responseText = this.wxOpService.post(url, WxGsonBuilder.create().toJson(params) , appId);
    WxError wxError = WxError.fromJson(responseText);
    if (wxError.getErrorCode() == 0) {
      return WxOpGsonBuilder.create().fromJson(responseText, WxOpMaterialNewsBatchGetResult.class);
    } else {
      throw new WxErrorException(wxError);
    }
  }

  @Override
  public WxOpMaterialFileBatchGetResult materialFileBatchGet(String type, int offset, int count , String appId) throws WxErrorException {
    String url = MATERIAL_API_URL_PREFIX + "/batchget_material";
    Map<String, Object> params = new HashMap<>();
    params.put("type", type);
    params.put("offset", offset);
    params.put("count", count);
    String responseText = this.wxOpService.post(url, WxGsonBuilder.create().toJson(params) , appId);
    WxError wxError = WxError.fromJson(responseText);
    if (wxError.getErrorCode() == 0) {
      return WxOpGsonBuilder.create().fromJson(responseText, WxOpMaterialFileBatchGetResult.class);
    } else {
      throw new WxErrorException(wxError);
    }
  }

}
