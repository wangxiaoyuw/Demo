(function e(t,n,r){function s(o,u){if(!n[o]){if(!t[o]){var a=typeof require=="function"&&require;if(!u&&a)return a(o,!0);if(i)return i(o,!0);throw new Error("Cannot find module '"+o+"'")}var f=n[o]={exports:{}};t[o][0].call(f.exports,function(e){var n=t[o][1][e];return s(n?n:e)},f,f.exports,e,t,n,r)}return n[o].exports}var i=typeof require=="function"&&require;for(var o=0;o<r.length;o++)s(r[o]);return s})({1:[function(require,module,exports){
    /**
     * Created by Administrator on 2017/2/20.
     */
    $(document).ready(function () {
        var ua = navigator.userAgent;
        $("#repeatCommitFlag").val(0);
        var ajaxReturnType = "";
        if (ua.indexOf("MSIE 8.0") > 0 || ua.indexOf("MSIE 9.0") > 0) {
            $("#AlertForIE89").show();//显示IE8、9的特殊提示
            ajaxReturnType = "text";
        }else{
            ajaxReturnType = "json";
        }
        $("#uploadPhoto").click(function () {
            var repeatCommitFlag = $("#repeatCommitFlag").val();
            if(repeatCommitFlag==1){
                return;
            }
            if(ajaxReturnType=="text"){
                //IE8、9下取消了截图功能,坐标传-1
                $("#x").val("-1");
                $("#y").val("-1");
                $("#h").val("-1");
                $("#w").val("-1");
            }
            $("#repeatCommitFlag").val(1);
            $("#myForm").ajaxSubmit({
                success: function (data) {
                    if(ajaxReturnType=="text"){
                        data = JSON.parse(data);
                    }
                    console.info(data);
                    if (data.errorCode != 10000) {
                        if(data.errorCode == -1){
                            alertDiv({type:"2",des:"图片审核未通过,上传失败",time:"3000",fn:function(){window.location.href="/personalPhoto"}});
                        }else if(data.errorCode == -2){
                            alertDiv({type:"2",des:data.resultMsg,time:"3000",fn:function(){window.location.href="/personalPhoto"}});
                        }else{
                            alertDiv({type:"2",des:"上传失败",time:"3000",fn:function(){window.location.href="/personalPhoto"}});
                        }
                    } else {
                        alertDiv({type:"2",des:"上传成功",time:"3000",fn:function(){window.location.href="/personalPhoto"}});
                    }
                },
                error: function () {
                    alertDiv({type:"2",des:"上传失败-异常",time:"3000",fn:function(){}});
                },
                url: '/upload', /*设置post提交到的页面*/
                type: "post",/*设置表单以post方法提交*/
                dataType: ajaxReturnType
            });
        });
    });
},{}]},{},[1])