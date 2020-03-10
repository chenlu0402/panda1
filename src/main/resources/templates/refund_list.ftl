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
                    <label class="layui-form-label">退货时间</label>
                    <div class="layui-input-block">
                        <input type="text" id="datetime" name="datetimeRange" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <button class="layui-btn layuiadmin-btn-replys" data-type="reload" lay-submit
                            lay-filter="refund-search">
                        <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
                    </button>
                </div>
            </div>
        </div>
        <div class="layui-card-body">
            <table class="layui-table" id="refund-list" lay-filter="refund-list"></table>
        </div>
    </div>
</div>
</body>
</html>
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
            id: 'refund-list'
            , elem: '#refund-list'
            , url: '/order/listRefundInfo' //数据接口
            , method: 'post'
            , contentType: 'application/json'
            , page: true //开启分页
            , cols: [[ //表头
                {field: 'orderId', title: '订单编号', fixed: 'left'}
                , {field: 'spuName', title: '商品名称',width: 200}
                , {field: 'size', title: '尺寸',width: 90}
                , {field: 'color', title: '颜色',width: 90}
                , {field: 'count', title: '退货数量',width: 90}
                , {field: 'amount', title: '退款金额',width: 90}
                , {field: 'remark', title: '备注'}
                , {field: 'createdTime', title: '退货时间',width: 160}
            ]]
        });
        //---------------------------搜索-----------------------------
        form.on('submit(refund-search)', function (data) {
            var field = data.field;

            //执行重载
            table.reload('refund-list', {
                where: field
            });
        });
    });
</script>

