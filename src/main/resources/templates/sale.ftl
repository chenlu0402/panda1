<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>销售</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="/layui/css/admin.css" media="all">
    <link rel="stylesheet" href="/layui/css/login.css" media="all">
    <script type="text/javascript" src="/layui/layui.js"></script>
    <script type="text/javascript" src="/jquery.min.js"></script>
    <style>
        .layui-table-cell, .layui-table-tool-panel li {
            overflow: visible !important;
        }

        .layui-form-select .layui-input {
            height: 30px;
            overflow: auto !important;
        }

    </style>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-form layui-card-header layuiadmin-card-header-auto">
            <div class="layui-form-item">
                <input type="hidden" id="hidden" value="">
                <div class="layui-inline">
                    <label class="layui-form-label">商品编号</label>
                    <div class="layui-input-block">
                        <input type="text" name="spuId" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <button class="layui-btn" data-type="reload" lay-submit lay-filter="goods-search">
                        <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
                    </button>
                </div>
                <div class="layui-inline">
                    <button class="layui-btn" lay-submit lay-filter="handle_settle">
                        结算
                    </button>
                </div>
            </div>
        </div>
        <div class="layui-card-body">
            <table class="layui-table" id="sale" lay-filter="sale">
            </table>
        </div>
    </div>
</div>
<div class="layui-form" lay-filter="select_goods" id="select_goods" style="padding: 20px 30px 0 0;display: none">
    <input type="hidden" name="skuId" value="">
    <div class="layui-form-item">
        <label class="layui-form-label">商品编号</label>
        <div class="layui-input-block">
            <input type="text" name="spuId" disabled="disabled" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">商品名称</label>
        <div class="layui-input-block">
            <input type="text" name="spuName" disabled="disabled" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">规格</label>
        <div class="layui-input-block">
            <select name="skuName" lay-filter="skuName">
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">销售价</label>
        <div class="layui-input-block">
            <input type="text" name="salePrice" class="layui-input" disabled="disabled">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">数量</label>
        <div class="layui-input-block">
            <input type="text" name="count" class="layui-input" lay-verify="required|count">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">折扣类型</label>
        <div class="layui-input-block">
            <select name="discountType" lay-filter="discountType" lay-verify="">
                <option value="0">无优惠</option>
                <option value="1">折扣</option>
                <option value="2">立减</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">折扣/立减金额</label>
        <div class="layui-input-block">
            <input type="text" name="discountAmount" class="layui-input" lay-verify="required|amount">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">实收金额</label>
        <div class="layui-input-block">
            <input type="text" name="amount" class="layui-input" lay-verify="required|amount" disabled="disabled">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="submit" class="layui-btn" lay-submit="" lay-filter="addSkuToTable">确定</button>
            <button type="submit" class="layui-btn" lay-submit="" lay-filter="cancel">取消</button>
        </div>
    </div>
</div>
<div class="layui-form" lay-filter="settle_form" id="settle_form" style="padding: 20px 30px 0 0;display: none">
    <div class="layui-form-item">
        <label class="layui-form-label">应收</label>
        <div class="layui-input-block">
            <input type="text" name="orderAmount" disabled="disabled" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">实收</label>
        <div class="layui-input-block">
            <input type="text" name="totalAmount" lay-verify="required|amount" class="layui-input">
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
            <button type="submit" class="layui-btn" lay-submit="" lay-filter="settle">确定</button>
            <button type="submit" class="layui-btn" lay-submit="" lay-filter="cancelSettle">取消</button>
        </div>
    </div>
</div>
</body>
</html>
<script type="text/html" id="bar">
    <a class="layui-btn layui-btn-xs layui-btn-radius layui-btn-danger" lay-event="del">删除</a>
</script>
<script>
    layui.use(['table', 'form'], function () {
        var $ = layui.$
                , form = layui.form
                , table = layui.table;

        //正整数
        var pIntReg = /^[1-9]\d*$/;
        //大于0的整数加小数
        var pIntPointReg = /^\d*\.?\d{1,2}$/;

        var range = /^\d{1,10}$/;

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
            id: 'sale'
            , elem: '#sale'
            , height: 400
            , limit: 100
            , data: []
            , cols: [[ //表头
                {field: 'spuId', title: '商品编号', width: 90, fixed: 'left'}
                , {field: 'spuName', title: '商品名称', width: 200, fixed: 'left'}
                , {field: 'skuId', title: 'sku编号', hide: true}
                , {field: 'skuName', title: '规格', width: 200}
                , {field: 'salePrice', title: '销售价', width: 90}
                , {field: 'count', title: '数量', width: 90, edit: 'text'}
                , {field: 'discountType', title: 'discount_type', hide: true}
                , {field: 'discountTypeDesc', title: '折扣类型', width: 90}
                , {field: 'discountAmount', title: '折扣/立减金额', width: 90}
                , {field: 'amount', title: '应收金额', width: 90}
                , {fixed: 'right', width: 120, align: 'center', toolbar: '#bar'}
            ]]
        });

        //------------------------------------监听form------------------------------------

        form.on('select(discountType)', function (data) {
            caculateAmount();
        });

        $('input[name=discountAmount]').bind('change', function () {
            caculateAmount();
        });

        $('input[name=count]').bind('change', function () {
            caculateAmount();
        });

        form.on('submit(goods-search)', function (data) {
            var field = data.field;
            if (field.spuId == '') {
                layer.msg('请输入商品编号！');
                return;
            }

            var field = data.field;

            $.ajax({
                url: "/goods/listSkuForSale",
                type: 'Get',
                data: field,
                contentType: 'application/json',
                dataType: 'json',
                success: function (dt) {
                    var result = dt.data;
                    if (result.length == 0) {
                        layer.msg('没有查询到商品信息，请确认商品编号是否正确！');
                        return;
                    }
                    $('#hidden').val(JSON.stringify(result));
                    var data = result[0];
                    form.val("select_goods", {
                        "spuId": data.spuId
                        , "skuId": data.skuId
                        , "spuName": data.spuName
                        , "skuName": data.skuName
                        , "salePrice": data.salePrice
                        , "count": 1
                        , "discountAmount": 0
                        , "amount": data.salePrice
                    });

                    var option = '';
                    for (var i = 0; i < result.length; i++) {
                        var selected = '';
                        if (i == 0) {
                            selected = 'selected';
                        }
                        option = option + '<option value="' + result[i].skuId + '"' + selected + '>' + result[i].skuName + '</option>';
                    }
                    $('select[name=skuName]').empty().append(option);
                    form.render('select','skuName');

                    layer.open({
                        type: 1,
                        title: '商品销售',
                        content: $('#select_goods') //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
                    });
                }
            });
        });

        form.on('submit(cancel)', function () {
            layer.close(layer.index);
        });

        form.on('submit(addSkuToTable)', function (data) {
            var formVal = form.val("select_goods");
            formVal.skuId = formVal.skuName;
            formVal.skuName = $('select[name=skuName] option:selected').text();
            formVal.discountTypeDesc = $('select[name=discountType] option:selected').text();
            var oldData = table.cache.sale;
            if (oldData != null) {
                oldData.push(formVal);
            } else {
                oldData = formVal;
            }
            layer.close(layer.index);
            //执行重载
            table.reload('sale', {
                data: oldData
            });
        });

        var handleSettleIndex = 0;
        form.on('submit(handle_settle)', function (data) {
            var rowCount = $('.layui-table tbody').find('tr').length;
            if (rowCount == 0) {
                layer.msg('请往表格添加需要结算的商品！');
                return;
            }

            var allData = table.cache.sale;
            var totalAmount = 0;
            for (var i = 0; i < allData.length; i++) {
                //跳过空行
                if(allData[i].length == 0){
                    continue;
                }
                totalAmount = totalAmount + parseFloat(allData[i].amount);
            }

            form.val("settle_form", {
                "orderAmount": totalAmount
                , "totalAmount": totalAmount
            });

            handleSettleIndex = layer.open({
                type: 1,
                title: '商品结算',
                content: $('#settle_form')
            });
        });

        form.on('submit(settle)',function () {
            var order = form.val("settle_form");
            var tableData = table.cache.sale;
            order.saleGoodsList = JSON.stringify(tableData);
            $.ajax({
                url: "/order/settleOrder",
                type: 'post',
                data: JSON.stringify(order),
                contentType: 'application/json',
                dataType: 'json',
                success: function (data) {
                    layer.msg('结算完成！');
                    layer.close(handleSettleIndex);
                }
            });
        });

        form.on('submit(cancelSettle)',function (){
            layer.close(handleSettleIndex);
        });

        //------------------------------------复制删除行------------------------------------
        table.on('tool(sale)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）

            if (layEvent === 'del') {
                obj.del();
            }
        });

        //------------------------------------监听form------------------------------------
        function caculateAmount() {
            var salePrice = $('input[name=salePrice]').val();
            var count = $('input[name=count]').val();
            if (count == null || count == '') {
                layer.msg("请输入数量！");
                return;
            }
            var disCountType = $('select[name=discountType]').val();
            var discountAmount = $('input[name=discountAmount]').val();
            var amount = $('input[name=amount]').val();

            if(disCountType == 0){
                amount = parseFloat(salePrice) * parseInt(count);
            }else if (disCountType == 1) {
                if (discountAmount > 10) {
                    layer.msg("折扣不能大于10，如：打8折则输入8");
                    $('input[name=discountAmount]').val('');
                    return;
                }
                amount = (parseFloat(salePrice) * (parseFloat(discountAmount) / 10)) * parseInt(count);
            }else if (disCountType == 2) {
                amount = parseFloat(salePrice) * parseInt(count) - parseFloat(discountAmount);
                if (amount < 0) {
                    layer.msg("折扣金额不能大于销售金额！");
                    $('input[name=discountAmount]').val('');
                    return;
                }
            }
            $('input[name=amount]').val(amount.toFixed(2));
        };
    });
</script>