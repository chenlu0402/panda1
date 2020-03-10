<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>order</title>
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
        <div class="layui-form layui-card-header layuiadmin-card-header-auto">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">订单编号</label>
                    <div class="layui-input-block">
                        <input type="text" name="orderId" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">备注</label>
                    <div class="layui-input-block">
                        <input type="text" name="remark" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">订单创建时间</label>
                    <div class="layui-input-block">
                        <input type="text" id="datetime" name="datetimeRange" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <button class="layui-btn layuiadmin-btn-replys" data-type="reload" lay-submit
                            lay-filter="order-search">
                        <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
                    </button>
                </div>
            </div>
        </div>
        <div class="layui-card-body">
            <table class="layui-table" id="order-list" lay-filter="order-list"></table>
        </div>
    </div>
</div>
</body>
</html>
<script type="text/html" id="barTpl">
    <a class="layui-btn layui-btn-xs layui-btn-radius layui-btn-warm"  href="/order_detail_page?orderId={{=d.orderId}}" lay-filter="detail">详情</a>
    {{#
        var totalCount = parseInt(d.totalCount);
        var refundCount = 0;
        if(d.refundCount != null){
            refundCount = parseInt(d.refundCount);
        }
        if(totalCount > refundCount){
    }}
    <a class="layui-btn layui-btn-xs layui-btn-radius layui-btn-danger" onclick="refund('{{=d.orderId}}')" data={{=d.orderId}} lay-filter="refund">退货</a>
    {{#
        }
    }}
</script>
<script>
    layui.use(['table', 'form','laydate'], function () {
        var $ = layui.$
                , form = layui.form
                , table = layui.table
                , laydate = layui.laydate;

        //日期时间范围选择
        laydate.render({
            elem: '#datetime'
            , type: 'datetime'
            , format: 'yyyy-MM-dd HH:mm:ss'
            , range: '~'
        });

        //------------------------------------表格初始化------------------------------------
        table.render({
            id: 'order-list'
            , elem: '#order-list'
            , url: '/order/listOrder' //数据接口
            , method: 'post'
            , contentType: 'application/json'
            , page: true //开启分页
            , cols: [[ //表头
                {field: 'orderId', title: '订单编号', fixed: 'left'}
                , {field: 'totalCount', title: '商品数量',width: 90}
                , {field: 'smallChanges', title: '抹零金额',width: 90}
                , {field: 'totalAmount', title: '实收金额',width: 90}
                , {field: 'remark', title: '备注'}
                , {field: 'createdTime', title: '创建时间',width: 160}
                , {field: 'refundCount', title: '退货数量',width: 90}
                , {field: 'refundAmount', title: '退款金额',width: 90}
                , {width: 90,align: 'center', templet: '#barTpl',width: 120}
            ]]
        });
        //---------------------------搜索-----------------------------
        form.on('submit(order-search)', function (data) {
            var field = data.field;

            //执行重载
            table.reload('order-list', {
                where: field
            });
        });

        //---------------------------退货-----------------------------
        window.refund = function(orderId){
            layer.confirm('确认退掉这个订单的所有商品？', function (index) {
                $.ajax({
                    url: "/order/refund",
                    type: 'Post',
                    data: JSON.stringify({"orderId":orderId}),
                    contentType: 'application/json',
                    dataType: 'json',
                    success: function (dt) {
                        layer.msg("操作成功！");
                        layer.close(layerIndex);
                    }
                });
            });
        };
    });
</script>

