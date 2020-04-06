<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>goods</title>
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
        <div class="layui-card-body">
            <input type="hidden" id="orderId" value="${orderId}">
            <table id="order-detail" lay-filter="order-detail"></table>
        </div>
    </div>
</div>
<div class="layui-form" lay-filter="refund_goods" id="refund_goods" style="padding: 20px 30px 0 0;display: none">
    <input type="hidden" name="skuId" value="">
    <input type="hidden" name="saleCount" id="saleCount" value="">
    <input type="hidden" name="realityAmount" id="realityAmount" value="">
    <div class="layui-form-item">
        <label class="layui-form-label">商品名称</label>
        <div class="layui-input-block">
            <input type="text" name="spuName" disabled="disabled" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">退货数量</label>
        <div class="layui-input-block">
            <input type="text" name="count" lay-verify="required|count" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">退款金额</label>
        <div class="layui-input-block">
            <input type="text" name="amount" lay-verify="required|amount" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-block">
            <input type="text" name="remark" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="submit" class="layui-btn" lay-submit="" lay-filter="refund">确定</button>
            <button type="submit" class="layui-btn" lay-submit="" lay-filter="cancel">取消</button>
        </div>
    </div>
</div>
</body>
</html>
<script type="text/html" id="barTpl">
    {{#
    var count = parseInt(d.count);
    var refundCount = 0;
    if(d.refundCount != null){
    refundCount = parseInt(d.refundCount);
    }
    if(count > refundCount){
    var obj = JSON.stringify(d).replace(/"/g, '&#39;');
    }}
    <a class="layui-btn layui-btn-xs layui-btn-radius layui-btn-danger" onclick="refund({{obj}})" data="" lay-filter="refund">退货</a>
    {{#
    }
    }}
</script>
<script>
    layui.use(['table','form'], function () {
        var $ = layui.$
                , form = layui.form
                , table = layui.table;

        var orderId = $('#orderId').val();

        //正整数
        var pIntReg = /^[1-9]\d*$/;
        //大于0的整数加小数
        var pIntPointReg = /^\d*\.?\d{1,2}$/;

        form.verify({
            amount: function (value, item) { //value：表单的值、item：表单的DOM对象
                var text = $(item).parent().prev().text();
                if (!pIntPointReg.test(value)) {
                    return text + '只能输入大于0的数字！';
                }
            },
            count: function (value, item) { //value：表单的值、item：表单的DOM对象
                var text = $(item).parent().prev().text();
                if (!pIntReg.test(value)) {
                    return text + '只能输入大于0的整数！';
                }
            }
        });

        //------------------------------------表格初始化------------------------------------
        table.render({
            id: 'order-detail'
            , elem: '#order-detail'
            , url: '/order/listDetail?orderId='+orderId //数据接口
            , method: 'Get'
            , cols: [[ //表头
                {field: 'skuId',hide:true, title: 'skuId'}
                , {field: 'spuName', title: '商品名称',width: 220, fixed: 'left'}
                , {field: 'size', title: '尺寸',width: 70}
                , {field: 'color', title: '颜色',width: 70}
                , {field: 'count', title: '售出数量',width: 90}
                , {field: 'discountTypeDesc', title: '折扣类型',width: 90}
                , {field: 'originAmount', title: '售价',width: 70}
                , {field: 'discountAmount',width: 120, title: '折扣/立减金额'}
                , {field: 'amount', title: '实收金额',width: 90}
                , {field: 'refundCount', title: '退货数量',width: 90}
                , {field: 'refundAmount', title: '退款金额',width: 90}
                , {width: 70,align: 'center', templet: '#barTpl'}
            ]]
        });

        //---------------------------表单事件-----------------------------
        $('input[name=count]').bind('change', function () {
            var saleCount = $('#saleCount').val();
            var realityAmount = $('#realityAmount').val();
            var count = $(this).val();
            if(count == null || count == ''){
                layer.msg("退货数量不能为空！");
                return;
            }
            if(parseInt(count) > parseInt(saleCount)){
                layer.msg("退货数量不能大于实际销售数量！");
                $(this).val('');
                return;
            }
            var amount = parseFloat(realityAmount)/parseFloat(saleCount)*parseInt(count);
            $('input[name=amount]').val(amount);
        });

        $('input[name=amount]').bind('change', function () {
            var realityAmount = $('#realityAmount').val();
            var amount = $(this).val();
            if(parseFloat(amount) > parseFloat(realityAmount)){
                layer.msg("退款金额不能大于实际销售金额！");
                $(this).val('');
                return;
            }
        });

        var layerIndex = 0;
        form.on('submit(refund)', function (data) {
            data.field.orderId = $('#orderId').val();
            $.ajax({
                url: "/order/refund",
                type: 'Post',
                data: JSON.stringify(data.field),
                contentType: 'application/json',
                dataType: 'json',
                success: function (dt) {
                    layer.msg("操作成功！");
                    layer.close(layerIndex);
                    table.reload('order-detail');
                }
            });
        });

        form.on('submit(cancel)', function (data) {
            layer.close(layerIndex);
        });

        //---------------------------表格工具栏-----------------------------
        window.refund = function(data){
            var amount = parseFloat(data.amount)/parseFloat(data.count);
            form.val("refund_goods", {
                "skuId": data.skuId
                , "spuName": data.spuName
                , "count": 1
                , "amount": amount
                , "saleCount": data.count
                , "realityAmount": data.amount
            });

            layerIndex = layer.open({
                type: 1,
                title: '退货',
                content: $('#refund_goods')
            });
        };
    });
</script>

