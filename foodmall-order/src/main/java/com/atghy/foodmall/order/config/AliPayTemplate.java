package com.atghy.foodmall.order.config;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.atghy.foodmall.order.vo.PayVo;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-08-12
 * Description:
 * 买家账号ymwtwh9142@sandbox.com
 */
@ConfigurationProperties(prefix = "alipay")
@Component
@Data
public class AliPayTemplate {

    //在支付宝创建的应用的id
    private   String app_id = "2016102700769019";

    // 商户私钥，您的PKCS8格式RSA2私钥
    private  String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDiDEZjqcr0zniuLjB5mQ4ucJ5PWv8m/mYG9S8MEGwPLjRZB8DYVe+rJksC34XdYJ9sgVUVKxKsNsVElI6fhhlfz65fS1juY4cdxhzEcsYvSrsmU++2/5ZReZbCuAhdxPaskD2ruAxIsfnS+/FOgsiZjhZjEomDvHBqSG3mgRLktc990mgNX5eR0GcPDXdbU2AmrLQrGtYWgl71W59YhYj8jehvOV9qAX28DT/iB837Snt71ndN90DpkNHwcstZ4QQWQIAvVDy3ufnE1xmi+ndQha8/mwmBEIL4lSq+D9w5kjyHy6iBrhbazxWMtsrENnbzdm7J2IL7nq4Wa9ZTS3mVAgMBAAECggEABTm5atZi/bPEMGqa7kSugj5SOo1k9pbM7v49/y5mARfVV/WI7LdX2gIBm+hk5NA1/0EiIWIkhVyixMoWXfOwpHldk5BfVutwFS0ePyBUGoXEu8cW9IkGBY4lAQ1WDCzgcu7GrjCMACY1Gz59vo3ha0NQmyuziBsUEjDocXqsYCMUsJgEprJVglIPc8zsYQllEQg32zv+HSM59pl9XeBfonhwKgzp519qgHPH6RdT8HthhjXTN1+hLc5UZqhwUmqKTj6bviECQmvGGfBwwOW56ShLLYQ5qnEHEJSsxRkHDQ7UZcH6IitEme8vDNP8KmfMwfVNvFquzBxbb8hd+ZiQ4QKBgQDxfA/CD+h1pmU/jExWfUnc/ND/+Yvh+FRTf+gGLuTZ9xJpbbScnCVfWse6imGXcm5Mrdq3BYx0vpae4CnpEzlLlpeKc0jFdsEA7/5YuxXm2YxA9a286hRa0AUDZk/edKkLviPXHQAf+fEgUP83TBiU4DS5MIedRGkNccB3OB8VmQKBgQDvoqz6W5aHBrJU/TL9DQ0LHiaUWXZkom5obO0ZL2qxlRm4YCHqzaMGk5Qz6smxabbWf7Ald2s2CvRrVcLje7y4iPffz9tpJ/VV0TTl2szwodwkI9bLYPbVQcje4920wbhywihCPsLJjvMVKvuBfd4muIF3Ip+dba2tdjvjQJhJXQKBgHCkOA2DsiE+7reH0AwAATZNz4e3daKAXYVdgnNv6FwrvbDj9X2f8GbcR5Ih/ofQXtbxubXppqzGQif11FbeGrnBkwqH4AgL7tdrGy90hTDbhWq/rr0SS1BY/B2r2dGghUHu/XTYGDjSM8GpRoMawdSgCLfhhUC8qmwVBXwW95jxAoGAcMM08I5ek+VTz2hiMO+OPrgOYWqDqrFAkQUIwPwuEahetQMPksT3POX/LDsEc+aR8fed9NNv8LtQ7p0pmxAj6xYQxhGyZpY1/Q73/F1saKNRyvPOZF+ZZxqQoknJjwBFgkLY3OtZj60jt8dFGFcZTVEvItd5EoSE4IYtgi9oOcUCgYEAl2TF1AxGNYYlK60DmIUR2Tz5rUOEELm5FhtLzDoGaYeAUYCJQiyIbI+f2pePfSCmFXvIqI5EWl+U0pKWn65lPKvs5My6aE8zslfO3Ty3zoe0TiKM7VtyWOqL2l12Uc/1Znb2w+ACpK87JzB8XSawf8bu6h9gvEmv8nE7KY/QqnE=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    private  String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnu89FeqpopGVgK18azrwQi48RN779CeYYB4WOncdE4YFX5y2uR72S5w2hZ6NYHOoJAOidT35oZXRSc+SdkTouFmBSYRNpGCEGM8Ccccp8zXOWm4wXHRua4ORtcbyGnvf3qN0wBfE7TD5VQcxH3+Clyo+qsbWfyWEHpawy+k3EEMQH/OSMfrqaszBrJM1wEb0R8bXu9v9RpY9onSgyovc9iQQltLlFuk7iKT0hZpFNhzvpJY0wNSC+Wsdos4k5P7egyRnFvsu9K4bh2+3vXBktCoXVfKDBiMXauMYYzqkWBJXW7pLyrJkdynQSaGqbFtE49gUr+UVQjNQMdFL/ZBtvQIDAQAB";

    // 服务器[异步通知]页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    // 支付宝会悄悄的给我们发送一个请求，告诉我们支付成功的信息 支付回调
    public static String notify_url = "http://23h5r47232.51vip.biz/payed/notify";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    //同步通知，支付成功，一般跳转到成功页
    public static String return_url = "http://order.foodmall.com/memberOrder.html";

    // 签名方式
    private  String sign_type = "RSA2";

    // 字符编码格式
    private  String charset = "utf-8";

    private String timeout = "30m";

    // 支付宝网关； https://openapi.alipaydev.com/gateway.do
    private  String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    public  String pay(PayVo vo) throws AlipayApiException {

        //AlipayClient alipayClient = new DefaultAlipayClient(AlipayTemplate.gatewayUrl, AlipayTemplate.app_id, AlipayTemplate.merchant_private_key, "json", AlipayTemplate.charset, AlipayTemplate.alipay_public_key, AlipayTemplate.sign_type);
        //1、根据支付宝的配置生成一个支付客户端
        AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl,
                app_id, merchant_private_key, "json",
                charset, alipay_public_key, sign_type);
        //2、创建一个支付请求 //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(return_url);
        alipayRequest.setNotifyUrl(notify_url);
        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = vo.getOut_trade_no();
        //付款金额，必填
        String total_amount = vo.getTotal_amount();
        //订单名称，必填
        String subject = vo.getSubject();
        //商品描述，可空
        String body = vo.getBody();
        //timeout_express设置订单收单时间为1m
        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"timeout_express\":\""+timeout+"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        String result = alipayClient.pageExecute(alipayRequest).getBody();

        //会收到支付宝的响应，响应的是一个页面，只要浏览器显示这个页面，就会自动来到支付宝的收银台页面
        System.out.println("支付宝相应结果:"+result);

        return result;

    }
}

