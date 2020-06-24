package com.example.demo.common.config;

/**
 * @program: demo
 * @description:
 * @author: Irving
 * @create: 2020-06-24
 **/
public class WechatConfig {
    //微信支付商户开通后 微信会提供appid
    public String appId;

    //微信支付商户开通后 微信会提供appSecret
    public String appSecret;

    //商户号
    public String mchId;

    //32位的api密钥,微信商户平台-账户设置-安全设置-api安全 密钥 用于拉起支付签名
    public String partnerkey;

    //openId 是微信用户针对公众号的标识，授权的部分这里不解释
    public String openId;

    //微信支付成功后异步通知地址 必须要求80端口并且地址不能带参数
    public String notifyUrl;

    //微信支付成功后同步通知地址 必须要求80端口并且地址不能带参数
    public String returnUrl;

    //证书apiclient_cert.p12文件位置 可加载
    public String certPath;

    //微信支付分 分配的服务 ID
    public String serviceId;

    //v3接口 CA证书 apiclient_key.pem私钥内容
    public String privateKey;

    //v3接口 CA证书 apiclient_cert.pem证书内容
    public String certificate;

    // APIv3密钥 32 位
    public String AES_KEY = "xxx";

    //商户证书序列号 CA证书 可查看微信商户平台-账户设置-安全设置-api安全密钥
    public String  MC_HSERIAL_NO = "xxxxx";

}
