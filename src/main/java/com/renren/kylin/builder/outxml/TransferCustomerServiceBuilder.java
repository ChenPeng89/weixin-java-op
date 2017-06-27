package com.renren.kylin.builder.outxml;

import com.renren.kylin.bean.message.WxOpXmlOutTransferKefuMessage;
import org.apache.commons.lang3.StringUtils;

/**
 * 客服消息builder
 * <pre>
 * 用法: WxMpKefuMessage m = WxMpXmlOutMessage.TRANSFER_CUSTOMER_SERVICE().content(...).toUser(...).build();
 * </pre>
 *
 * @author chanjarster
 */
public final class TransferCustomerServiceBuilder extends BaseBuilder<TransferCustomerServiceBuilder, WxOpXmlOutTransferKefuMessage> {
  private String kfAccount;

  public TransferCustomerServiceBuilder kfAccount(String kf) {
    this.kfAccount = kf;
    return this;
  }

  @Override
  public WxOpXmlOutTransferKefuMessage build() {
    WxOpXmlOutTransferKefuMessage m = new WxOpXmlOutTransferKefuMessage();
    setCommon(m);
    if(StringUtils.isNotBlank(this.kfAccount)){
      WxOpXmlOutTransferKefuMessage.TransInfo transInfo = new WxOpXmlOutTransferKefuMessage.TransInfo();
      transInfo.setKfAccount(this.kfAccount);
      m.setTransInfo(transInfo);
    }
    return m;
  }
}
