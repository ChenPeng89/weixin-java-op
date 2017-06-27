/**
 * 对公众平台发送给公众账号的消息加解密示例代码.
 *
 * @copyright Copyright (c) 1998-2014 Tencent Inc.
 */

// ------------------------------------------------------------------------

/**
 * 针对org.apache.commons.codec.binary.Base64，
 * 需要导入架包commons-codec-1.9（或commons-codec-1.8等其他版本）
 * 官方下载地址：http://commons.apache.org/proper/commons-codec/download_codec.cgi
 */
package com.renren.kylin.util.crypto;

import com.renren.kylin.api.WxOpConfigStorage;
import org.apache.commons.codec.binary.Base64;

public class WxOpCryptUtil extends me.chanjar.weixin.common.util.crypto.WxCryptUtil {

  /**
   * 构造函数
   *
   * @param wxOpConfigStorage
   */
  public WxOpCryptUtil(WxOpConfigStorage wxOpConfigStorage) {
    /*
     * @param token          公众平台上，开发者设置的token
     * @param encodingAesKey 公众平台上，开发者设置的EncodingAESKey
     * @param appId          公众平台appid
     */
    String encodingAesKey = wxOpConfigStorage.getAesKey();
    String token = wxOpConfigStorage.getToken();
    String appId = wxOpConfigStorage.getComponentAppId();

    this.token = token;
    this.appidOrCorpid = appId;
    this.aesKey = Base64.decodeBase64(encodingAesKey + "=");
  }

}
