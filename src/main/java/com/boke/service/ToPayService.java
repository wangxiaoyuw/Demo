package com.boke.service;

import com.boke.pojo.WxPayDto;
import com.boke.utils.GetWxOrderno;
import com.boke.utils.RequestHandler;
import com.boke.utils.TenpayUtil;
import org.springframework.stereotype.Service;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by wangzy on 2017/7/14.
 */
@Service
public class ToPayService {

    private static String appid = "wx2530049f3fbc04dc";
    private static String appsecret = "162ba840637e4f4ba571639a12bdfe36";
    private static String partner = "1354198602";
    //这个参数partnerkey是在商户后台配置的一个32位的key,微信商户平台-账户设置-安全设置-api安全
    private static String partnerkey = "dTkus4q8cjmy9ffUsE5ZQzjpC99FjJgC";
    //openId 是微信用户针对公众号的标识，授权的部分这里不解释
    private static String openId = "ohPpYs4VyR51JoYcgM3V9y0w3fd0";
    //微信支付成功后通知地址 必须要求80端口并且地址不能带参数
    private static String notifyurl = "http://www.weixin.qq.com/wxpay/pay.php";																	 // Key


    public String getCodeurl(WxPayDto tpWxPayDto) {
        // 1 参数
        // 订单号
        String orderId = tpWxPayDto.getOrderId();
        // 附加数据 原样返回
        String attach = "";
        // 总金额以分为单位，不带小数点
        String totalFee = getMoney(tpWxPayDto.getTotalFee());

        // 订单生成的机器 IP
        String spbill_create_ip = tpWxPayDto.getSpbillCreateIp();
        // 这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
        String notify_url = notifyurl;
        String trade_type = "NATIVE";

        // 商户号
        String mch_id = partner;
        // 随机字符串
        String nonce_str = getNonceStr();

        // 商品描述根据情况修改
        String body = tpWxPayDto.getBody();

        // 商户订单号
        String out_trade_no = orderId;

        SortedMap<String, String> packageParams = new TreeMap<String, String>();
        packageParams.put("appid", appid);
        packageParams.put("mch_id", mch_id);
        packageParams.put("nonce_str", nonce_str);
        packageParams.put("body", body);
        packageParams.put("attach", attach);
        packageParams.put("out_trade_no", out_trade_no);

        // 这里写的金额为1 分到时修改
        packageParams.put("total_fee", totalFee);
        packageParams.put("spbill_create_ip", spbill_create_ip);
        packageParams.put("notify_url", notify_url);

        packageParams.put("trade_type", trade_type);

        RequestHandler reqHandler = new RequestHandler(null, null);
        reqHandler.init(appid, appsecret, partnerkey);

        String sign = reqHandler.createSign(packageParams);
        String xml = "<xml>" + "<appid>" + appid + "</appid>" + "<mch_id>"
                + mch_id + "</mch_id>" + "<nonce_str>" + nonce_str
                + "</nonce_str>" + "<sign>" + sign + "</sign>"
                + "<body><![CDATA[" + body + "]]></body>"
                + "<out_trade_no>" + out_trade_no
                + "</out_trade_no>" + "<attach>" + attach + "</attach>"
                + "<total_fee>" + totalFee + "</total_fee>"
                + "<spbill_create_ip>" + spbill_create_ip
                + "</spbill_create_ip>" + "<notify_url>" + notify_url
                + "</notify_url>" + "<trade_type>" + trade_type
                + "</trade_type>" + "</xml>";
        String code_url = "";
        String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";


        code_url = new GetWxOrderno().getCodeUrl(createOrderURL, xml);
        System.out.println("code_url----------------"+ xml);
        System.out.println(code_url);
        return code_url;

    }


    public static String getNonceStr(){
        String currTime = TenpayUtil.getCurrTime();
        String strTime = currTime.substring(8,currTime.length());
        String strRandom = TenpayUtil.buildRandom(4)+"";
        return strTime+strRandom;
    }

    /**
     * 元转换成分
     * @param
     * @return
     */
    public static String getMoney(String amount) {
        if(amount==null){
            return "";
        }
        // 金额转化为分为单位
        String currency =  amount.replaceAll("\\$|\\￥|\\,", "");  //处理包含, ￥ 或者$的金额
        int index = currency.indexOf(".");
        int length = currency.length();
        Long amLong = 0l;
        if(index == -1){
            amLong = Long.valueOf(currency+"00");
        }else if(length - index >= 3){
            amLong = Long.valueOf((currency.substring(0, index+3)).replace(".", ""));
        }else if(length - index == 2){
            amLong = Long.valueOf((currency.substring(0, index+2)).replace(".", "")+0);
        }else{
            amLong = Long.valueOf((currency.substring(0, index+1)).replace(".", "")+"00");
        }
        return amLong.toString();
    }

}
