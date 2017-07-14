function alertDiv(opt){
    if(!opt){
        return
    };
    var _opt={
        type:'',
        des:'',
        time:'',
        fn:function(){}
    };
    var opt=$.extend(_opt,opt);
    if(opt.type==1){
        var div=$('<div id="alert_div"></div>');
        var p=$('<p id="title">'+opt.des+'</p>')
        var p1=$('<p id="btn_box"></p>')
        var btn=$('<input type="button" value="取消" id="cancel"/>')
        var btn1=$('<input type="button" value="确定" id="enter"/>')
        var w=$(window).width();
        var h=$(window).height();
        var _t=(w-356)/2;
        var _l=(h-175)/2;
        var cssObj={
            'width': '356px',
            'height': '175px',
            'border':'1px solid #333',
            'border-bottom-width':'4px',
            'background':'#fff',
            'position': 'fixed',
            'z-index':'999',
            'top':_l,
            'left':_t
        }
        var cssP={
            'width': '316px',
            'text-align': 'center',
            'font-size': '20px',
            'color': '#333',
            'margin': '50px auto',
        }
        var cssP1={
            'width': '316px',
            'text-align': 'center',
            'font-size': '20px',
            'color': '#333',
            'margin': '50px auto',
        }
        var cssBtn={
            'width': '148px',
            'height': '30px',
            'float': 'left',
            'font-family':'"Microsoft YaHei", 微软雅黑',
            'border-width': '1px',
            'border-style': 'solid',
            'border-color': 'rgb(218, 218, 218)',
            'border-image': 'initial'
        }
        var cssBtn1={
            'width': '148px',
            'height': '30px',
            'float': 'left',
            'font-family':'"Microsoft YaHei", 微软雅黑',
            'border-width': '1px',
            'border-style': 'solid',
            'border-color': 'rgb(218, 218, 218)',
            'border-image': 'initial',
            'background': '#ffa903',
            'border': '#ff9103',
            'margin-left': '20px',
            'color': '#fff'
        }
        $(div).css(cssObj);
        $(p).css(cssP);
        $(p1).css(cssP1);
        $(btn).css(cssBtn);
        $(btn1).css(cssBtn1);
        $(p1).append(btn).append(btn1)
        $(div).append(p).append(p1)
        $('body').append(div)
        function bindEvents(){
            $('#cancel').on('click',function(){
                $('#alert_div').remove();
            })
            $('#enter').on('click',function(){
                opt.fn();
                $('#alert_div').remove();
            })
        }
        bindEvents()
    }else if(opt.type==2){
        var div=$('<div id="alert_div"></div>');
        var p=$('<p id="title">'+opt.des+'</p>')
        var w=$(window).width();
        var h=$(window).height();
        var _t=(w-340)/2;
        var _l=(h-64)/2;
        var cssObj={
            'width': '300px',
            'height': '24px',
            'background':'#fff',
            'position': 'fixed',
            'padding':'20px 20px',
            'background':'#000',
            'margin': '0',
            'top':_l,
            'left':_t,
            'z-index':'999',
            'filter':'alpha(opacity=90)',
            'opacity':'.9'
        }
        var cssP={
            'text-align': 'center',
            'font-size': '18px',
            'color':'#fff',
            'margin':'0px',
        }
        $(div).css(cssObj);
        $(div).append(p)
        $(p).css(cssP);
        $('body').append(div)
        setTimeout(function(){
            $('#alert_div').remove();
            opt.fn();
        },opt.time)

    }
}
