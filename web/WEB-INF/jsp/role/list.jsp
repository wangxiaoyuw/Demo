<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<html class="no-js">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Amaze UI Admin index Examples</title>
    <meta name="description" content="这是一个 index 页面">
    <meta name="keywords" content="index">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="icon" type="image/png" href="../assets01/i/favicon.png">
    <link rel="apple-touch-icon-precomposed" href="../assets01/i/app-icon72x72@2x.png">
    <meta name="apple-mobile-web-app-title" content="Amaze UI" />
    <link rel="stylesheet" href="../../assets01/css/amazeui.min.css"/>
    <link rel="stylesheet" href="../../assets01/css/admin.css">
    <script src="../../assets01/js/jquery.min.js"></script>
    <script src="../../assets01/js/app.js"></script>
    <link rel="stylesheet" href="../../assets01/css/demo.css" type="text/css">
    <link rel="stylesheet" href="../../assets01/css/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="../../assets01/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="../../assets01/js/jquery.ztree.core.js"></script>
    <script type="text/javascript" src="../../assets01/js/jquery.ztree.excheck.js"></script>
</head>
<body>
<header class="am-topbar admin-header">
    <div class="am-topbar-brand"><img src="../assets01/i/logo.png"></div>

    <div class="am-collapse am-topbar-collapse" id="topbar-collapse">
        <ul class="am-nav am-nav-pills am-topbar-nav admin-header-list">

            <li class="am-dropdown tognzhi" data-am-dropdown><button onclick="tuichu()">退出</button>
                <button class="am-btn am-btn-primary am-dropdown-toggle am-btn-xs am-radius am-icon-bell-o" data-am-dropdown-toggle> 消息管理<span class="am-badge am-badge-danger am-round">6</span></button>
                <ul class="am-dropdown-content">



                    <li class="am-dropdown-header">所有消息都在这里</li>



                    <li><a href="#">未激活会员 <span class="am-badge am-badge-danger am-round">556</span></a></li>
                    <li><a href="#">未激活代理 <span class="am-badge am-badge-danger am-round">69</span></a></li>
                    <li><a href="#">未处理汇款</a></li>
                    <li><a href="#">未发放提现</a></li>
                    <li><a href="#">未发货订单</a></li>
                    <li><a href="#">低库存产品</a></li>
                    <li><a href="#">信息反馈</a></li>



                </ul>
            </li>

            <li class="kuanjie">

                <a href="#">会员管理</a>
                <a href="#">奖金管理</a>
                <a href="#">订单管理</a>
                <a href="#">产品管理</a>
                <a href="#">个人中心</a>
                <a href="#">系统设置</a>
            </li>

            <li class="soso">

                <p>

                    <select data-am-selected="{btnWidth: 70, btnSize: 'sm', btnStyle: 'default'}">
                        <option value="b">全部</option>
                        <option value="o">产品</option>
                        <option value="o">会员</option>

                    </select>

                </p>

                <p class="ycfg"><input type="text" class="am-form-field am-input-sm" placeholder="圆角表单域" /></p>
                <p><button class="am-btn am-btn-xs am-btn-default am-xiao"><i class="am-icon-search"></i></button></p>
            </li>




            <li class="am-hide-sm-only" style="float: right;"><a href="javascript:;" id="admin-fullscreen"><span class="am-icon-arrows-alt"></span> <span class="admin-fullText">开启全屏</span></a></li>
        </ul>
    </div>
</header>

<div class="am-cf admin-main page-content">

    <div class="nav-navicon admin-main admin-sidebar">
        <div class="sideMenu am-icon-dashboard" style="color:#aeb2b7; margin: 10px 0 0 0;"> 欢迎系统管理员：${sessionScope.user.username}</div>
        <div class="sideMenu">
            <c:forEach items="${map}" var="menu">
                <h3 class="am-icon-flag"><em></em> <a href="">${menu.key.description}</a></h3>
                <ul>
                    <c:forEach items="${menu.value}" var="son">
                        <li><a href="${son.permission}">${son.description}</a></li>
                    </c:forEach>
                </ul>
            </c:forEach>
        </div>
        <!-- sideMenu End -->

        <script type="text/javascript">
            jQuery(".sideMenu").slide({
                titCell:"h3", //鼠标触发对象
                targetCell:"ul", //与titCell一一对应，第n个titCell控制第n个targetCell的显示隐藏
                effect:"slideDown", //targetCell下拉效果
                delayTime:300 , //效果时间
               triggerTime:150, //鼠标延迟触发时间（默认150）
                defaultPlay:true,//默认是否执行效果（默认true）
                returnDefault:true //鼠标从.sideMen移走后返回默认状态（默认false）
            });
        </script>
    </div>

<div class=" admin-content">

		<div class="daohang">
			<ul>
                <li><button type="button" class="am-btn am-btn-default am-radius am-btn-xs"> 首页</button> </li>
				<li><button type="button" class="am-btn am-btn-default am-radius am-btn-xs">帮助中心<a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close="">×</a></button></li>
				<li><button type="button" class="am-btn am-btn-default am-radius am-btn-xs">奖金管理<a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close="">×</a></button></li>
				<li><button type="button" class="am-btn am-btn-default am-radius am-btn-xs">产品管理<a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close="">×</a></button></li>

			</ul>
</div>


<div class="admin-biaogelist page-content">

    <div class="listbiaoti am-cf">
      <ul class="am-icon-users"> 会员管理</ul>
       <a href="/role/add">新增</a>
      <dl class="am-icon-home" style="float: right;">当前位置： 首页 > <a href="#">会员列表</a></dl>
    </div>

    <form >
          <table width="100%" class="am-table am-table-bordered am-table-radius am-table-striped">
            <thead>
              <tr class="am-success">
                <th class="table-check"><input type="checkbox" /></th>
                <th class="table-id">ID</th>
                <th class="table-title">角色名</th>
                  <th class="table-title">描述</th>
                <th width="130px" class="table-set">操作</th>
              </tr>
            </thead>
            <tbody><c:forEach items="${roleList}" var="role">
              <tr>
                <td><input type="checkbox" /></td>

                <td >${role.id}</td>
                <td class="am-hide-sm-only">${role.rolename}</td>
                  <td class="am-hide-sm-only">${role.roledesc}</td>
                <td>
                    <div class="am-btn-toolbar">
                        <div >
                            <shiro:hasPermission name="edit">  <a href="javascript:update('${role.id}');"> 修改</a></shiro:hasPermission>
                            <shiro:hasPermission name="delete"> <button onclick="deleteId(${role.id})" class="am-btn am-btn-default am-btn-xs am-text-danger am-round"  title="删除"><span class="am-icon-trash-o"></span></button></shiro:hasPermission>
                            <shiro:hasPermission name="premission">   <a href="javascript:prem('${role.id}');">权限</a></shiro:hasPermission></div>
                    </div>

                </td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
        <hr />
        </form>
    <%--<%@ include file="../page.jsp" %>--%>

    <div class="am-popup am-popup-inner" id="my-popups">
        <div class="am-popup-hd">
            <h4 class="am-popup-title">修改栏目名称</h4>
            <span data-am-modal-close class="am-close">&times;</span> </div>
        <div class="am-popup-bd">
            <form class="am-form tjlanmu">
                <div class="am-form-group">
                    <div class="zuo">栏目名称：</div>
                    <div class="you">
                        <input type="email" class="am-input-sm" id="doc-ipt-email-1" placeholder="请输入标题">
                    </div>
                </div>
                <div class="am-form-group">
                    <div class="zuo">栏目关键词：</div>
                    <div class="you">
                        <input type="password" class="am-input-sm" id="doc-ipt-pwd-1" placeholder="请输入关键词">
                    </div>
                </div>
                <div class="am-form-group am-cf">
                    <div class="zuo">栏目描述：</div>
                    <div class="you">
                        <textarea class="" rows="2" id="doc-ta-1"></textarea>
                    </div>
                </div>
                <div class="am-form-group am-cf">
                    <div class="zuo">栏目图片：</div>
                    <div class="you" style="height: 45px;">
                        <input type="file" id="doc-ipt-file-1">
                        <p class="am-form-help">请选择要上传的文件...</p>
                    </div>
                </div>
                <div class="am-form-group am-cf">
                    <div class="zuo">简介：</div>
                    <div class="you">
                        <textarea class="" rows="2" id="doc-ta-11"></textarea>
                    </div>
                </div>
                <div class="am-form-group am-cf">
                    <div class="zuo">状态：</div>
                    <div class="you" style="margin-top: 3px;">
                        <label class="am-checkbox-inline">
                            <input type="checkbox" value="option1">
                            显示 </label>
                        <label class="am-checkbox-inline">
                            <input type="checkbox" value="option2">
                            隐藏 </label>
                    </div>
                </div>
                <div class="am-form-group am-cf">
                    <div class="you">
                        <p>
                            <button type="submit" class="am-btn am-btn-success am-radius">提交</button>
                        </p>
                    </div>
                </div>
            </form>
        </div>
    </div>

 <div class="foods">
  <ul>
    版权所有@2015. 模板收集自 <a href="http://www.cssmoban.com/" target="_blank" title="模板之家">模板之家</a> -  More Templates<a href="http://www.cssmoban.com/" title="网页模板" target="_blank">网页模板</a>
  </ul>
  <dl>
    <a href="" title="返回头部" class="am-icon-btn am-icon-arrow-up"></a>
  </dl>
</div>

</div>

</div>

</div>

<!--[if lt IE 9]>
<script src="http://libs.baidu.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
<script src="../assets01/js/polyfill/rem.min.js"></script>
<script src="../assets01/js/polyfill/respond.min.js"></script>
<script src="../assets01/js/amazeui.legacy.js"></script>
<![endif]-->

<!--[if (gte IE 9)|!(IE)]><!-->
<script src="../assets01/js/amazeui.min.js"></script>

</body>
</html>
<script type="text/javascript">
  function update(id) {
      $(".page-content").load("/role/update/" + id);
  }

  function deleteId(id){
      $(".page-content").load("/role/delete/" + id);
  }
  function prem(id){
      $(".page-content").load("/role/tree/" + id);
  }

  function saveRole() {
      var rolename = $("#name").val();
      var roledesc = $("#desc").val();
      var  id = $("#id").val();
      var data = {
          "rolename" :rolename,
          "roledesc":roledesc,
          "id":id
      }

      $.ajax({
          url:"/role/usave",
          type:'POST',
          data:data,
          success:function () {
              alert("success")
          },
          error:function () {
              alert("false")
          }

      })
  }


  var setting = {
      check: {
          enable: true
      },
      data: {
          simpleData: {
              enable: true
          }
      }
  };

  /* var zNodes =[
   { id:1, pId:0, name:"随意勾选 1", open:true},
   { id:11, pId:1, name:"随意勾选 1-1", open:true},
   { id:111, pId:11, name:"随意勾选 1-1-1"},
   { id:112, pId:11, name:"随意勾选 1-1-2"},
   { id:12, pId:1, name:"随意勾选 1-2", open:true},
   { id:121, pId:12, name:"随意勾选 1-2-1"},
   { id:122, pId:12, name:"随意勾选 1-2-2"},
   { id:2, pId:0, name:"随意勾选 2", checked:true, open:true},
   { id:21, pId:2, name:"随意勾选 2-1"},
   { id:22, pId:2, name:"随意勾选 2-2", open:true},
   { id:221, pId:22, name:"随意勾选 2-2-1", checked:true},
   { id:222, pId:22, name:"随意勾选 2-2-2"},
   { id:23, pId:2, name:"随意勾选 2-3"}
   ];*/

  var code;

  function setCheck() {
      var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
          py = $("#py").attr("checked")? "p":"",
          sy = $("#sy").attr("checked")? "s":"",
          pn = $("#pn").attr("checked")? "p":"",
          sn = $("#sn").attr("checked")? "s":"",
          type = { "Y":py + sy, "N":pn + sn};
      zTree.setting.check.chkboxType = type;
      showCode('setting.check.chkboxType = { "Y" : "' + type.Y + '", "N" : "' + type.N + '" };');
  }
  function showCode(str) {
      if (!code) code = $("#code");
      code.empty();
      code.append("<li>"+str+"</li>");
  }

  $(document).ready(function(){
      $.post("/role/treedata",function (zNodes) {
          $.fn.zTree.init($("#treeDemo"), setting, zNodes);
          setCheck();
          $("#py").bind("change", setCheck);
          $("#sy").bind("change", setCheck);
          $("#pn").bind("change", setCheck);
          $("#sn").bind("change", setCheck);
      })

  });
  //-->

  //获取选中节点
  function onCheck(){

      //此方法选中节点，得到选中节点的id
      var treeObj=$.fn.zTree.getZTreeObj("treeDemo");
      var nodes=treeObj.getCheckedNodes(true);
      var ids = new Array();
      for(var i=0;i<nodes.length;i++){
          //获取选中节点的值
          ids.push(nodes[i].id);
      }
      var id = $("#id").val();
      var idstr = ids.toString();
      ajaxSubmit(id,idstr);
  }

  //角色-菜单信息入库
  function ajaxSubmit(id,idstr){alert(idstr)
      $.post("${ctx}/role/save",{"id":id,"mid":idstr},function(obj){
          $.messager.alert('提示',obj.msg);
          $("#menuWindow").window('close');
      },'json');
  }

</script>