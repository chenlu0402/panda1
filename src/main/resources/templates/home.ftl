<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>主页</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="/layui/css/admin.css" media="all">
    <link rel="stylesheet" href="/layui/css/login.css" media="all">
    <script type="text/javascript" src="/layui/layui.js"></script>
    <script type="text/javascript" src="/jquery.min.js"></script>
</head>
<body class="layui-layout-body">

<div id="LAY_app">
    <div class="layui-layout layui-layout-admin">
        <!-- 侧边菜单 -->
        <div class="layui-side layui-side-menu">
            <div class="layui-side-scroll">
                <div class="layui-logo" lay-href="home/console.html">
                    <span>季青职业服</span>
                </div>

                <ul class="layui-nav layui-nav-tree" lay-shrink="all" id="LAY-system-side-menu" lay-filter="layadmin-system-side-menu">
                    <li data-name="home" class="layui-nav-item layui-nav-itemed">
                        <a href="javascript:;" lay-tips="库存列表" id="sku" lay-direction="4">
                            <i class="layui-icon layui-icon-tabs"></i>
                            <cite>库存列表</cite>
                        </a>
                        <a href="javascript:;" lay-tips="库存录入" id="goods_import" lay-direction="3">
                            <i class="layui-icon layui-icon-survey"></i>
                            <cite>库存录入</cite>
                        </a>
                        <a href="javascript:;" lay-tips="商品销售" id="sale" lay-direction="1">
                            <i class="layui-icon layui-icon-rmb"></i>
                            <cite>商品销售</cite>
                        </a>
                        <a href="javascript:;" lay-tips="订单列表" id="order" lay-direction="2">
                            <i class="layui-icon layui-icon-template-1"></i>
                            <cite>订单列表</cite>
                        </a>
                        <a href="javascript:;" lay-tips="退货明细" id="refund" lay-direction="2">
                            <i class="layui-icon layui-icon-unlink"></i>
                            <cite>退货明细</cite>
                        </a>
                    </li>
                </ul>
            </div>
        </div>

        <!-- 页面标签 -->
        <div class="layadmin-pagetabs" id="LAY_app_tabs">
            <div class="layui-tab" lay-unauto lay-allowClose="true" lay-filter="layadmin-layout-tabs">
                <ul class="layui-tab-title" id="LAY_app_tabsheader">
                    <li lay-id="home/console.html" lay-attr="home/console.html" class="layui-this"><i class="layui-icon layui-icon-home"></i></li>
                </ul>
            </div>
        </div>


        <!-- 主体内容 -->
        <div class="layui-body" id="LAY_app_body">
            <div class="layadmin-tabsbody-item layui-show">
                <iframe src="/goods_list_page" frameborder="0" class="layadmin-iframe"></iframe>
            </div>
        </div>

        <!-- 辅助元素，一般用于移动设备下遮罩 -->
        <div class="layadmin-body-shade" layadmin-event="shade"></div>
    </div>
</div>

<script>
    layui.config({
        base: '/layui/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use('index',function () {


        $('#sale').click(function () {
            layui.index.openTabsPage("/sale_page", "商品销售");
        });

        $('#order').click(function () {
            layui.index.openTabsPage("/order_list_page", "订单列表");
        });

        $('#goods_import').click(function () {
            layui.index.openTabsPage("/goods_add_log_page", "库存录入");
        });

        $('#sku').click(function () {
            layui.index.openTabsPage("/goods_list_page", "库存列表");
        });

        $('#refund').click(function () {
            layui.index.openTabsPage("/refund_list_page", "退货明细");
        });
    });
</script>
</body>
</html>


