//﻿var leftScrollTop =$('#leftNav').offset().top;
var leftTool = {
    IE6:!!window.ActiveXObject && !window.XMLHttpRequest,
    ie6AdHeight:0,
    //以下为封装基本函数
    getQueryString: function (data) {
        var tdata = '';
        for (var key in data) {
            tdata += "&" + encodeURIComponent(key) + "=" + encodeURIComponent(data[key]);
        }
        tdata = tdata.replace(/^&/g, "");
        return tdata
    },
    addEvent: function (elm, type, fn, useCapture) {
        if (elm.addEventListener) {
            elm.addEventListener(type, fn, useCapture);
            return true;
        } else if (elm.attachEvent) {
            var r = elm.attachEvent('on' + type, fn);
            return r;
        } else {
            elm['on' + type] = fn;
        }
    },
    MathMinHeigth: function () {
        var winH = 0;
        if (window.innerHeight) {
            winH = Math.min(window.innerHeight, document.documentElement.clientHeight);
        } else if (document.documentElement && document.documentElement.clientHeight) {
            winH = document.documentElement.clientHeight;
        } else if (document.body) {
            winH = document.body.clientHeight;
        }
        return winH;
    },
    getElementByClassName: function (tagName, className) {
        var classObj = document.getElementsByTagName(tagName);
        var len = classObj.length;
        for (var i = 0; i < len; i++) {
            if (classObj[i].className == className) {
                return classObj[i];
                break;
            }
        }
    },
    attrStyle: function (elem, attr) {
        if (!elem) { return; }
        if (elem.style[attr]) {
            return elem.style[attr];
        } else if (elem.currentStyle) {
            return elem.currentStyle[attr];
        } else if (document.defaultView && document.defaultView.getComputedStyle) {
            attr = attr.replace(/([A-Z])/g, '-$1').toLowerCase();
            return document.defaultView.getComputedStyle(elem, null).getPropertyValue(attr);
        } else {
            return null;
        }
    },
    getOffsetTop: function (element) {
        var top = 0;
        while (element) {
            if (leftTool.attrStyle(element, "position") === "fixed") {
                break;
            }
            top += element.offsetTop;
            element = element.offsetParent;
        }
        return top;
    },
    //以下为实现点击A-Z实现滚动
    treeHref:function(curLitterNum){//点击A-Z实现滚动
        var hideItemAllHeight = 0;
        for (var i = 1; i < curLitterNum; i++) {
            var hideItem = document.getElementById("letter" + i);
            if (!hideItem)
                continue;
            var hideItemHeight = hideItem.offsetHeight - 1;
            hideItemAllHeight += hideItemHeight;
        }
        var treeBox = document.getElementById("treev1"); //树
        treeBox.scrollTop = hideItemAllHeight;
        //tree1高度不够滚动，重新计算高度
        if (treeBox.scrollTop < hideItemAllHeight) {
            var treeBottom = document.getElementById("tree-bottom");
            treeBottom.style.height = parseInt(treeBottom.style.height) + (hideItemAllHeight - treeBox.scrollTop) + "px";
            treeBox.scrollTop = hideItemAllHeight;
        }
    },
    scrollToCurrentTreeNode:function(){//滚动到指定位置
        var currentNode = document.getElementById("curObjTreeNode");

        var topHeight = leftTool.MathCurrentTreeNodeTop(currentNode);
        var treeBox = document.getElementById("treev1"); //树

        if (typeof CookieHelper == "undefined") {
            treeBox.scrollTop = topHeight;
            return;
        }
        var ScrollTreeNodeTop = CookieHelper.readCookie("ScrollTreeNodeTop");
        var isMaster = 0;
        if (currentNode) {
            var child = currentNode.firstChild;
            if (child.className && child.className.indexOf("mainBrand") != -1) {
                isMaster = 1;
            }
        } else {
            ScrollTreeNodeTop = 0;
        }
        //复制黏贴以及外部点击进来的树形链接
        if (isMaster == 0 && /(mb_|b_|sb_|master|brand|serial)/gi.test(document.referrer) && document.referrer != "") {
            topHeight = parseInt(ScrollTreeNodeTop);
        }
        treeBox.scrollTop = topHeight;
        if (topHeight > parseInt(treeBox.scrollTop)) {
            var treeBottom = document.getElementById("tree-bottom");
            treeBottom.style.height = parseInt(treeBottom.style.height) + (topHeight - parseInt(treeBox.scrollTop)) + "px";
            treeBox.scrollTop = topHeight;
        }
        CookieHelper.setCookie("ScrollTreeNodeTop", parseInt(treeBox.scrollTop), { "expires": 360, "path": "/", "domain": "bitauto.com" });
    },
    MathCurrentTreeNodeTop:function(curNode){//计算当前节点到树形顶部距离
        var topHeight = 0;
        if (!curNode)
            return topHeight;
        while (curNode && curNode.id != "treev1") {
            topHeight += curNode.offsetTop;
            curNode = curNode.offsetParent;
        }
        return topHeight;
    },
    treeHeight:function(){//调整树形导航的位置与树的高度
        if (!leftTool.IE6) {
            leftTool.MathScrollSettingTagBar();
        }
        else {
            setTimeout(function () { leftNav.style.top = 39 + leftTool.ie6AdHeight + "px"; }, 500);
        }
    },

    MathScrollSettingTagBar:function(){//计算滚动对导航栏和设置
        var topHeight = 0;
        var carTagBar = document.getElementById("treeFixedNav");
        var tempBar = document.getElementById("header_logo");
        var leftNav = document.getElementById("leftNav");
        while (tempBar) {
            topHeight += tempBar.offsetTop;
            tempBar = tempBar.offsetParent;
        }
        topHeight += bt_navigateNewHeight;
        var scrollHeight = document.body.scrollTop || document.documentElement.scrollTop;
        var winH = leftTool.MathMinHeigth(); //alert(winH);
        //页面较短时 定位跳动
        if (leftTool.attrStyle(carTagBar, "position") == "fixed") {
            scrollHeight += treeFixedNavHeight;
        }
        //浮动导航栏
        if (scrollHeight > topHeight) {
            leftNav.className = "publicTabNew publicTabNew-fixed";
        } else {
            leftNav.className = "publicTabNew";
        }
        window.setTimeout(function () { leftTool.treeBoxHeight(); }, 250);
        //计算树的高度
        leftTool.treeFixedNavTop();
    },
    treeFixedNavTop:function(){//根据导航栏位置设置树形距顶高度
        var treeFixedNav = document.getElementById("leftNav");
        var bt_smenuNew = document.getElementById("header_logo");
        var toptreeFixedNavHeight = leftTool.getOffsetTop(bt_smenuNew); //获取导航栏距离顶部距离
        if (treeFixedNav.className == "publicTabNew publicTabNew-fixed")//判断导航条是否是fixed
        {
            document.getElementById("leftNav").style.top = -(bt_navigateNewHeight) + "px";
            return;
        }
        var scrollHeight = document.body.scrollTop || document.documentElement.scrollTop;
        document.getElementById("leftNav").style.top = toptreeFixedNavHeight - scrollHeight + "px";
    },
    treeBoxHeight:function(){
        var treeBox = document.getElementById("treev1"); //树
        var winH;
        var topHeight = 0;

        var bodyClientHeight = document.documentElement.clientHeight;
        var leftNavTreeHeight = leftTool.getElementByClassName("div", "leftNavTree");
        var IFrameTomHeight = leftTool.getElementByClassName("div", "treeNavv1");
        var treev1 = document.getElementById("treev1");
        var isIE6 = !!window.ActiveXObject && !window.XMLHttpRequest;


        if (treeBox == null) {
            return;
        }

        while (treeBox) {
            topHeight += treeBox.offsetTop;
            treeBox = treeBox.offsetParent;
        }
        winH = leftTool.MathMinHeigth();

        if (topHeight < 1) {
            topHeight = 0;
        }
        if (winH <= topHeight) {
            winH = topHeight;
        }

        if (treev1 != null && treev1.nodeType == 1) {
            var tree1Height = winH - topHeight;
            if (tree1Height < 1) { tree1Height = 0; }
            treev1.style.height = tree1Height + "px";

        }
        if (leftNavTreeHeight != null && IFrameTomHeight != null && treev1 != null) {
            if (isIE6) {
                if (topHeight > (leftNavTreeHeight.clientHeight + IFrameTomHeight.clientHeight)) {
                    if (bodyClientHeight - leftNavTreeHeight.clientHeight - IFrameTomHeight.clientHeight - 10 < 0) {
                        treev1.style.height = 0;
                    }
                    else {
                        treev1.style.height = bodyClientHeight - leftNavTreeHeight.clientHeight - IFrameTomHeight.clientHeight - 10;
                    }
                }
            }
        }
        //树形高度
        var tempBar = document.getElementById("header_logo");
        var leftNav = document.getElementById("leftNav");
        var bt_smenuNewHeight = leftTool.getOffsetTop(tempBar);

        //bt_smenuNewHeight += bt_navigateNewHeight;
        var scrollHeight = document.body.scrollTop || document.documentElement.scrollTop;
        var treeFixedNav = document.getElementById("leftNav");
        if (leftTool.attrStyle(treeFixedNav, "position") == "fixed") { scrollHeight += treeFixedNavHeight; } //fixed 影响滚动高度
        var leftTreeHeight = winH - bt_smenuNewHeight + scrollHeight;
        leftNav.style.height = leftTreeHeight + "px";
        //滚动条到底部距离$(window).scrollTop()
        //console.log(document.documentElement.clientHeight)
    },
    //以下为生成树形代码并插入页面中
    getCharNavHtml:function(charObj){//获取字符html
        var html = "<div class=\"leftNavTree\"><ul id=\"tree_letter\">";
        var i = 0;
        for (var key in charObj) {
            i++;
            if (charObj[key] == 1)
                html += "<li  class=\"t-" + key.toLowerCase() + "\"><a href=\"#\" onclick=\"leftTool.treeHref(" + i + ");return false;\">" + key + "</a></li>";
            else
                html += "<li class=\"none t-" + key.toLowerCase() + "\">" + key + "</li>";
        }
        html += "</ul></div>";
        return html;
    },
    getTreeHtml:function(jsonTree){//获取左侧树html
        if (typeof jsonTree["char"] == "undefined")
            return "";
        var html = leftTool.getCharNavHtml(jsonTree["char"]);
        html += "<div class=\"treev1\" id=\"treev1\"><ul>";
        var i = 0;
        for (var firstChar in jsonTree["char"]) {
            i++;
            if (jsonTree.brand[firstChar] == undefined)
                continue;
            html += "<li class=\"root\" id=\"letter" + i + "\"><b>" + firstChar + "</b>";
            html += "<ul class=\"tree-item-box\">";
            //按字母输出主品牌
            if (typeof jsonTree.brand[firstChar] != "undefined")
                html += leftTool.getMasterHtml(jsonTree.brand[firstChar]);
            html += "</ul></li>";
        }
        html += "<li id=\"tree-bottom\" style=\"height:300px; overflow:hidden\"></li></ul></div>";
        return html;
    },
    getMasterHtml:function(masterList){//获取主品牌html
        var html = "";
        for (var j = 0; j < masterList.length; j++) {
            var className = "mainBrand";
            var curIdStr = "";
            if (masterList[j].cur == 1) {
                if (masterList[j].type == "mb") {
                    className = "mainBrand current current_unfold";
                    curIdStr = " id=\"curObjTreeNode\"";
                } else {
                    className = "mainBrand current_unfold";
                }
            }
            if (masterList[j].expand != undefined && masterList[j].expand == 1) {
                html += "<li" + curIdStr + "><a href=\"#\" onclick=\"leftTool.expandMaster(this," + masterList[j].id + ");return false;\" class=\"" + className + "\"><i class=''></i><big>" + masterList[j].name + "</big><span>(" + masterList[j].num + ")</span></a>";
            }
            else
                html += "<li" + curIdStr + "><a href=\"" + 'javascript:;' + "\" class=\"" + className + "\"><i class=''></i><big>" + masterList[j].name + "</big><span>(" + masterList[j].num + ")</span></a>";
            //品牌
            if (masterList[j].child != undefined)
                html += leftTool.getBrandHtml(masterList[j].child);
            html += "</li>";
        }
        return html;
    },
    getBrandHtml:function(brandList){//获取品牌html
        var html = "<ul class=\"tree-items\">";
        for (var k = 0; k < brandList.length; k++) {
            if (brandList[k].type == "cs") {
                html += leftTool.getSerialHtml(brandList[k]);
            }
            else {
                var className = "brandType";
                var curIdStr = "";
                if (brandList[k].cur == 1) {
                    if (brandList[k].type == "cb") {
                        className += " current";
                        curIdStr = " id=\"curObjTreeNode\"";
                    }
                }
                if (brandList.url == "")
                    html += "<li" + curIdStr + "><a class=\"" + className + "\"><i class='active'></i><big class='active'>" + brandList[k].name + "</big><span>(" + brandList[k].num + ")</span></a>";
                else
                    html += "<li" + curIdStr + "><a href=\"" + 'javascript:;' + "\" class=\"" + className + "\"><i class='active'></i><big class='active'>" + brandList[k].name + "</big><span>(" + brandList[k].num + ")</span></a>";
                if (brandList[k].child != undefined) {
                    html += "<ul>";
                    for (var i = 0; i < brandList[k].child.length; i++)
                        html += leftTool.getSerialHtml(brandList[k].child[i]);
                    html += "</ul>";
                }
                html += "</li>";
            }
        }
        html += "</ul>";
        return html;
    },
    getSerialHtml:function(serial){//获取子品牌html
        var html = "";
        var className = "subBrand";
        var curIdStr = "";
        if (serial.cur == 1) {
            className += " current";
            curIdStr = " id=\"curObjTreeNode\"";
        }
        if (serial.salestate == "停销"){

            html += "<li" + curIdStr + " class=\"saleoff\"><a href=\"" + serial.url + "\" class=\"" + className + "\"><big>" + serial.name + "</big><span> 停售</span>";
        }else{
            html += "<li" + curIdStr + "><a href=\"" + serial.url + "\" class=\"" + className + "\"><big>" + serial.name + "</big>";
        }
        if (serial.butie == 1)
            html += "<span class=\"green\">补贴</span>";
        html += "</a>";
        //if (params.tagtype == "chexing") {
        //
        //}
        //else if (params.tagtype == "yanghu" || params.tagtype == "zhishu" || params.tagtype == "xiaoliang") {
        //    html += "<li" + curIdStr + "><a href=\"" + serial.url + "\" class=\"" + className + "\"><big>" + serial.name + "</big></a>";
        //}
        //else {
        //    html += "<li" + curIdStr + "><a href=\"" + serial.url + "\" class=\"" + className + "\"><big>" + serial.name + "</big><span>(" + serial.num + ")</span></a>";
        //}
        html += "</li>";
        return html;
    },
    //点击展开，并改变箭头指向
    unfold:function(){
        $('#leftNav').on('click','.mainBrand,.brandType',function(){
            $(this).find('i').toggleClass('active').end().siblings('.tree-items,ul').toggle();
            $(this).find('i').siblings('big').toggleClass('active');
        });
    },
    //左侧树固定定位

    leftScroll:function(){
        //var scrollTop = document.documentElement.scrollTop || window.pageYOffset || document.body.scrollTop;
        //var l = $('#leftNav').offset().left;
        //if(scrollTop>=leftScrollTop){
        //    $('#leftNav').css({
        //        'position':'fixed',
        //        'top':100,
        //        'left':l
        //    })
        //}else{
        //    $('#leftNav').css({
        //        'position':'absolute',
        //        'top':0,
        //        'left':0
        //    })
        //}
    },
    JsonpCallBack:function(data){  //生成代码插入页面
        var jsonTree = data;
        var treeHtml = leftTool.getTreeHtml(jsonTree);
        //console.log(treeHtml);
        var treeBox = document.getElementById("leftNav");
        if (treeBox)
            treeBox.innerHTML = treeHtml;
        //标签间传递参数
        if (jsonTree.setcityurl != undefined) {
            var setcityurlObj = jsonTree.setcityurl;
            for (var i = 0; i < setcityurlObj.length; i++) {
                leftTool.SetTagUrlCity(setcityurlObj[i].tagname, setcityurlObj[i].tagurl, setcityurlObj[i].otherpara, setcityurlObj[i].allspell);
            }
        }
        leftTool.scrollToCurrentTreeNode();
        leftTool.addEvent(window, "load", leftTool.treeBoxHeight, false);
        leftTool.addEvent(window, "resize", leftTool.treeBoxHeight, false);

        //滚动事件
        leftTool.addEvent(window, "scroll", function () {
            if (!leftTool.IE6) {
                leftTool.MathScrollSettingTagBar();
            }
            else {
                var leftNav = document.getElementById("leftNav");
                leftNav.style.top = 29 + leftTool.ie6AdHeight + "px"; //29:登陆条高度
            }
        }, false);
        leftTool.treeHeight();
        leftTool.unfold();
        leftTool.leftScroll();
        window.onscroll=function(){
            leftTool.leftScroll();
        };
    },

};
leftTool.JsonpCallBack(leftNav);
var treeFixedNavHeight = parseInt(leftTool.attrStyle(document.getElementById("leftNav"), "height")) + 10; //10 =margin-bottom  导航栏高度
var bt_navigateNewHeight = parseInt(leftTool.attrStyle(document.getElementById("header_logo"), "height")) + 10; //10 =margin-bottom 头部图标栏高度