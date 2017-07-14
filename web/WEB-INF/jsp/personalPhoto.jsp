<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>裁剪并上传头像</title>
    <link rel="stylesheet" href="../../assets01/jcrop/jquery.Jcrop.css" type="text/css"/>
    <link rel="stylesheet" href="../../assets01/jcrop/changeHeadImg.css" type="text/css"/>
    <link rel="stylesheet" href="../../assets01/jcrop/uploadUserPhoto.css" type="text/css"/>
    <link rel="stylesheet" href="../../assets01/jcrop/personalCenter.css" type="text/css"/>
    <link rel="stylesheet" href="../../assets01/jcrop/base.css" type="text/css"/>
    <script type="text/javascript" src="../../assets01/jcrop/libJS/jquery.min.js"></script>
    <script type="text/javascript" src="../../assets01/jcrop/libJS/jquery.Jcrop.js"></script>
    <script type="text/javascript" src="../../assets01/jcrop/out.js"></script>
    <script type="text/javascript" src="../../assets01/jcrop/alertDiv.js"></script>
    <script type="text/javascript" src="../../assets01/jcrop/uploadUserPhoto.js"></script>
    <script type="text/javascript" src="../../assets01/jcrop/jquery.form.js"></script>
</head>
<body>

<div class="content clearfix">
    <div class="left_tab_menu clearfix">
        <ul>
            <li><a href="http://wangxiaoyu.date/">裁剪并上传头像</a></li>
        </ul>
    </div>
    <div class="right_content clearfix">
        <div class="account_m">
            <ul  class="account_tab">
                <li class="cur"><a href="personalPhoto">修改头像</a></li>
            </ul>
            <div class="account_con clearfix">
                <div class="content_img tab">
                    <div class="fl">
                        <input type="hidden" id="repeatCommitFlag" value="0">
                        <p class="use_photo">使用新头像</p>
                        <p class="normal_notice">你可以上传JPG、PNG、JPEG文件，文件需小于3MB</p>
                        <p id="AlertForIE89" class="alert_for_IE">请保证您的本地目录路径上载至服务器功能开启！</p>
                        <div class="upLoad upload_img_left">
                            <span>上传头像</span>
                            <form id="myForm" action="/upload" class="upload_form" method="POST" enctype="multipart/form-data" name="form1">
                                <input class="photo-file" type="file" unselectable="on" name="file"  accept="image/jpg, image/jpeg, image/png" id="fcupload" onchange="readURLMy(this);" />
                                <img id="target" alt="" style="" name="" src="" >
                                <input type="hidden" id="x" name="x" />
                                <input type="hidden" id="y" name="y" />
                                <input type="hidden" id="w" name="w" />
                                <input type="hidden" id="h" name="h" />
                                <input type="button" style="display: none" value="保存头像" id="uploadPhoto" class="submit_css">
                                <input type="button" style="display: none" id="canclePhoto" value="取消" class="button_css">
                            </form>
                            <p class="error_msg" id="error_photo_type" style="display:none">图片格式错误，请重新选择</p>
                        </div>

                    </div>
                    <div class="fr js_cur_img">
                        <div class="cur_img_text">
                            <p>当前头像</p>
                        </div>
                        <div class="cur_img">
                            <img src="${avatarUrl}" alt="当前头像">
                        </div>
                    </div>
                    <div class="cur_img_right clearfix">
                        <p>系统会根据 您上传的图片生成3种尺寸的头像，如图所示。</p>
                        <div id="preview-pane" style="display: none">
                            <div class="preview-container">
                                <img src="" class="jcrop-preview" alt="" />
                            </div>
                        </div>
                        <div id="preview-pane2" style="display: none">
                            <div class="preview-container">
                                <img src="" class="jcrop-preview" alt="" />
                            </div>
                        </div>
                        <div id="preview-pane3" style="display: none">
                            <div class="preview-container">
                                <img src="" class="jcrop-preview" alt="" />
                            </div>
                        </div>
                        <div class="three_img clearfix" style="position: relative">
                            <div class="size_left clearfix">
                                <div class="size_120 clearfix"> <span><img src="http://static.qcdqcdn.com/passport/0/img/change_img.png" alt=""></span></div>
                                <p>160*160px</p>
                            </div>
                            <div class="size_right clearfix">
                                <div class="size_div1">
                                    <div class="size_30"><span><img src="http://static.qcdqcdn.com/passport/0/img/change_img.png" alt=""></span></div>
                                    <p>70*70px</p>
                                </div>
                                <div class="size_div2">
                                    <div class="size_60"><span><img src="http://static.qcdqcdn.com/passport/0/img/change_img.png" alt=""></span></div>
                                    <p>100*100px</p>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
<script>

    $(document).ready(function () {
        $('form').show();
        $('#canclePhoto').on('click',function () {
            window.location.reload();
        });
        $('.account_tab li').on('click',function () {
            $(this).addClass('cur').siblings().removeClass('cur');
            $('.account_con .tab').eq($(this).index()).show().siblings().hide()
        })

        var nextInputCode=(function () {
            $('.next_step input').on('click',function () {
                $('#js_content_password .tab_p').show();
                $('#js_content_password .phone_identify').hide();
            })
        })();

        var modifySuccess=function () {
            $('#js_save_settings').on('click',function () {
                $('#js_modify_password_success').show();
            });
            $('#js_select_button button').on('click',function () {
                $('#js_modify_password_success').hide();
            })
        };
        modifySuccess();
    })
</script>
<script>

    //Jcrop全局API
    var jcrop_api = null,
        boundx,
        boundy,


        $mainImg = $('#target'),
        // Grab some information about the preview pane
        $preview = $('#preview-pane'),
        $pcnt = $('#preview-pane .preview-container'),
        $pimg = $('#preview-pane .preview-container img'),

        $preview2 = $('#preview-pane2'),
        $pcnt2 = $('#preview-pane2 .preview-container'),
        $pimg2 = $('#preview-pane2 .preview-container img'),

        $preview3 = $('#preview-pane3'),
        $pcnt3 = $('#preview-pane3 .preview-container'),
        $pimg3 = $('#preview-pane3 .preview-container img'),

        xsize = $pcnt.width(),
        ysize = $pcnt.height(),

        xsize2 = $pcnt2.width(),
        ysize2 = $pcnt2.height(),

        xsize3 = $pcnt3.width(),
        ysize3 = $pcnt3.height();
</script>

</body>
</html>
