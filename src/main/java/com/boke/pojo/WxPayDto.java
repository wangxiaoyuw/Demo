package com.boke.pojo;

public class WxPayDto {

    private String orderId;//订单号
    private String totalFee;//金额
    private String spbillCreateIp;//订单生成的机器 IP
    private String notifyUrl;//这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等
    private String body;// 商品描述根据情况修改
    private String openId;//微信用户对一个公众号唯一


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }

    public void setSpbillCreateIp(String spbillCreateIp) {
        this.spbillCreateIp = spbillCreateIp;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

}

