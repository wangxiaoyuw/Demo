<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>扫码支付前台demo</title>
    <meta name="keywords" content="关键字">
    <meta name="description" content="描述">
    <meta name="content-type" content="text/html;charset=gbk">

</head>

<body>
<input id="id" value="${url}"/>
<div align="center" id="qrcode">
    <p>
        扫我，扫我
        <br><br>

    </p>
</div>
</body>
</html>
<script src="../assets01/qrcode.js"></script>
<script src="../assets01/js/jquery.min.js"></script>

<script type="text/javascript">
    //这个地址是Demo.java生成的code_url,这个很关键
    var url = $("#id").val();

    //参数1表示图像大小，取值范围1-10；参数2表示质量，取值范围'L','M','Q','H'   生成二维码
    var qr = qrcode(10, 'M');
    qr.addData(url);
    qr.make();
    var dom=document.createElement('DIV');
    dom.innerHTML = qr.createImgTag();
    var element=document.getElementById("qrcode");
    element.appendChild(dom);
</script>