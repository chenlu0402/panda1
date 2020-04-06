<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>refund</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="/layui/css/admin.css" media="all">
    <link rel="stylesheet" href="/layui/css/login.css" media="all">
    <script type="text/javascript" src="/layui/layui.js"></script>
    <script type="text/javascript" src="/jquery.min.js"></script>
</head>
<body>

<div class="layui-fluid">
    <div class="layui-card">
        <ul class="layui-timeline">
            <li class="layui-timeline-item">
                <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
                <div class="layui-timeline-content layui-text">
                    <h3 class="layui-timeline-title">库存总览</h3>
                    <p>
                        所有录入的商品都在这边展示
                    </p>
                </div>
            </li>
            <li class="layui-timeline-item">
                <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
                <div class="layui-timeline-content layui-text">
                    <h3 class="layui-timeline-title">库存录入</h3>
                    <p>展示每次录入商品的明细，录错的可以删除重新录入；点击【批量录入】进行录入操作，<br>
                        输入商品编号查询，如果已经录入过的商品将列出所有的商品信息明细，直接修改数量即可加库存；<br>
                        如果是没录入过的商品，需要录入商品所有信息<br>
                    </p>
                </div>
            </li>
            <li class="layui-timeline-item">
                <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
                <div class="layui-timeline-content layui-text">
                    <h3 class="layui-timeline-title">库存变化</h3>
                    <p>
                        展示库存所有的增减变动信息
                    </p>
                </div>
            </li>
            <li class="layui-timeline-item">
                <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
                <div class="layui-timeline-content layui-text">
                    <h3 class="layui-timeline-title">商品销售</h3>
                    <p>
                        输入商品编号查询要售卖的商品，选择折扣方式，根据销售价自动计算出应收金额
                    </p>
                </div>
            </li>
            <li class="layui-timeline-item">
                <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
                <div class="layui-timeline-content layui-text">
                    <h3 class="layui-timeline-title">订单列表</h3>
                    <p>
                        展示所有在商品销售中卖出的商品，对卖错的商品或者顾客退还的商品，可以进行退货处理
                    </p>
                </div>
            </li>
            <li class="layui-timeline-item">
                <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
                <div class="layui-timeline-content layui-text">
                    <h3 class="layui-timeline-title">退货明细</h3>
                    <p>
                        展示所有退货的商品
                    </p>
                </div>
            </li>
        </ul>
    </div>
</div>
</body>
</html>

