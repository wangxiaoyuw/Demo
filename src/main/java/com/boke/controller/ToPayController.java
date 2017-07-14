package com.boke.controller;

import com.boke.pojo.WxPayDto;
import com.boke.service.ToPayService;
import com.boke.utils.TenpayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wangzy on 2017/7/14.
 */
@Controller
public class ToPayController {

    private static String appid = "wx2530049f3fbc04dc";
    private static String appsecret = "162ba840637e4f4ba571639a12bdfe36";

    @Autowired
    private ToPayService toPayService;

    @RequestMapping(value = "/pay")
    public String topay(Model model){
        //扫码支付
        WxPayDto tpWxPay = new WxPayDto();
        tpWxPay.setBody("水费");
        tpWxPay.setOrderId(getNonceStr());
        tpWxPay.setSpbillCreateIp("127.0.0.93");
        tpWxPay.setTotalFee("0.01");
       String url = toPayService.getCodeurl(tpWxPay);
       model.addAttribute("url",url);
       return "topay";
    }

    public static String getNonceStr(){
        String currTime = TenpayUtil.getCurrTime();
        String strTime = currTime.substring(8,currTime.length());
        String strRandom = TenpayUtil.buildRandom(4)+"";
        return strTime+strRandom;
    }


}
