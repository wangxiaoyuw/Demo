/**
 *
 * Created by wangxy on 2017/3/4.
 */
$(document).ready(function(){
    var ua = navigator.userAgent;
    if (ua.indexOf("MSIE 8.0") > 0 || ua.indexOf("MSIE 9.0") > 0) {
        $("#fcupload").css("top","186px");
    }
});

function readURLMy(input) {
    //判断你图片的大小和类型
    var file = input.value;
    console.log(file);
    if (!/.(jpg|jpeg|png|JPG|JPEG|PNG)$/.test(file)) {
        $("#error_photo_type").html('您选择的图片不符合规则，请重新选择');
        $("#error_photo_type").show();
        //$('form').hide();
        $('#uploadPhoto').hide();
        $('#canclePhoto').hide();
        $('.cur_img_right').hide();
        $('.js_cur_img').show();
        if (jcrop_api != undefined) {
            jcrop_api.destroy();
        }
        $mainImg.removeAttr("style");//移除掉所有的样式，让jcrop重新生成
        $mainImg.removeAttr('src');
        return false;
    } else {
        // if (((input.files[0].size).toFixed(2)) >= (3 * 1024 * 1024)) {
        //     $("#error_photo_type").html('您选择的图片不符合规则，请重新选择');
        //     $("#error_photo_type").show();
        //     $('form').hide();
        //     $('.cur_img_right').hide();
        //     $('.js_cur_img').show();
        //     return false;
        // }
    }

    var ua = navigator.userAgent;
    if (ua.indexOf("MSIE 8.0") > 0 || ua.indexOf("MSIE 9.0") > 0) {

        $("#error_photo_type").hide();
        $('form').show();
        $('.cur_img_right').show();
        $('.js_cur_img').hide();
        $('#uploadPhoto').show();
        $('#canclePhoto').show();

        var fileInputElm = jQuery('#fcupload');
        var reallocalpath = jQuery('#fcupload')[0].value;
        console.log(reallocalpath);
        fileInputElm.get(0).select();
        var pic = $mainImg.get(0);
        pic.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='scale',src=\"" + reallocalpath + "\")";
        pic.src = reallocalpath;
        $mainImg.css("width","400px");
        $mainImg.css("height","400px");
        $mainImg.show();

        $pimg.get(0).style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='scale',src=\"" + reallocalpath + "\")";
        $pimg.get(0).src = reallocalpath;

        $pimg2.get(0).style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='scale',src=\"" + reallocalpath + "\")";
        $pimg2.get(0).src = reallocalpath;

        $pimg3.get(0).style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='scale',src=\"" + reallocalpath + "\")";
        $pimg3.get(0).src = reallocalpath;

        if (jcrop_api != undefined) {
            jcrop_api.destroy();
        }
        jcrop_api = $.Jcrop('#target', {
            setSelect: [20, 20, 200, 200],
            onChange: updatePreview,
            onSelect: updatePreview,
            boxWidth: 322,
            aspectRatio: xsize / ysize
        });
        var bounds = jcrop_api.getBounds();
        boundx = bounds[0];
        boundy = bounds[1];
        //$preview.appendTo(jcrop_api.ui.holder);
        //$preview2.appendTo(jcrop_api.ui.holder);
       // $preview3.appendTo(jcrop_api.ui.holder);
        jcrop_api.release();
        jcrop_api.disable();
    }else{
        if (((input.files[0].size).toFixed(2)) >= (3 * 1024 * 1024)) {
            $("#error_photo_type").html('您选择的图片不符合规则，请重新选择');
            $("#error_photo_type").show();
            //$('form').hide();
            $('#uploadPhoto').hide();
            $('#canclePhoto').hide();
            $('.cur_img_right').hide();
            $('.js_cur_img').show();
            if (jcrop_api != undefined) {
                jcrop_api.destroy();
            }
            $mainImg.removeAttr("style");//移除掉所有的样式，让jcrop重新生成
            $mainImg.removeAttr('src');
            return false;
        }
        $("#error_photo_type").hide();
        $('form').show();
        $('.cur_img_right').show();
        $('.js_cur_img').hide();
        $('#uploadPhoto').show();
        $('#canclePhoto').show();
        if (input.files && input.files[0]) {
            if (jcrop_api != undefined) {
                jcrop_api.destroy();
            }
            var reader = new FileReader();
            reader.readAsDataURL(input.files[0]);
            reader.onload = function (e) {
                $mainImg.removeAttr("style");//移除掉所有的样式，让jcrop重新生成
                $mainImg.removeAttr('src');
                $mainImg.attr('src', e.target.result);
                $pimg.removeAttr('src');
                $pimg.attr('src', e.target.result);
                $pimg2.removeAttr('src');
                $pimg2.attr('src', e.target.result);
                $pimg3.removeAttr('src');
                $pimg3.attr('src', e.target.result);

                // jcrop_api = $.Jcrop('#target', {
                //     setSelect: [20, 20, 200, 200],
                //     onChange: updatePreview,
                //     onSelect: updatePreview,
                //     boxWidth: 322,
                //     aspectRatio: xsize / ysize
                // });
                $('#target').Jcrop({
                    setSelect: [20, 20, 200, 200],
                    onChange: updatePreview,
                    onSelect: updatePreview,
                    boxWidth: 322,
                    aspectRatio: xsize / ysize
                },function(){
                    jcrop_api = this;
                    var bounds = jcrop_api.getBounds();
                    boundx = bounds[0];
                    boundy = bounds[1];
                    $preview.appendTo(jcrop_api.ui.holder);
                    $preview2.appendTo(jcrop_api.ui.holder);
                    $preview3.appendTo(jcrop_api.ui.holder);
                    //显示三个预览图
                    $preview.show();
                    $preview2.show();
                    $preview3.show();
                });
            };
        }

    }

}
function updatePreview(c) {
    $("#x").val(c.x);
    $("#y").val(c.y);
    $("#w").val(c.w);
    $("#h").val(c.h);
    if (parseInt(c.w) > 0) {
        var rx = xsize / c.w;
        var ry = ysize / c.h;

        $pimg.css({
            width: Math.round(rx * boundx) + 'px',
            height: Math.round(ry * boundy) + 'px',
            marginLeft: '-' + Math.round(rx * c.x) + 'px',
            marginTop: '-' + Math.round(ry * c.y) + 'px'
        });

        var rx = xsize2 / c.w;
        var ry = ysize2 / c.h;

        $pimg2.css({
            width: Math.round(rx * boundx) + 'px',
            height: Math.round(ry * boundy) + 'px',
            marginLeft: '-' + Math.round(rx * c.x) + 'px',
            marginTop: '-' + Math.round(ry * c.y) + 'px'
        });
        var rx = xsize3 / c.w;
        var ry = ysize3 / c.h;

        $pimg3.css({
            width: Math.round(rx * boundx) + 'px',
            height: Math.round(ry * boundy) + 'px',
            marginLeft: '-' + Math.round(rx * c.x) + 'px',
            marginTop: '-' + Math.round(ry * c.y) + 'px'
        });
    }
};