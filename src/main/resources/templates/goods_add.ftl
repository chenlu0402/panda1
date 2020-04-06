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
                    <button class="layui-btn" lay-submit lay-filter="add">
                        库存录入
                    </button>
                </div>
                <div class="layui-inline">
                    <button class="layui-btn" lay-submit lay-filter="save">
                        保存
                    </button>
                </div>
            </div>
        </div>
        <div class="layui-card-body">
            <table class="layui-table" id="goods_add" lay-filter="goods_add">
            </table>
        </div>
    </div>
</div>
</body>
</html>
<script type="text/html" id="bar">
    <a class="layui-btn layui-btn-xs layui-btn-radius layui-btn-warm" lay-event="copy">复制</a>
    <a class="layui-btn layui-btn-xs layui-btn-radius layui-btn-danger" lay-event="del">删除</a>
</script>
<script type="text/html" id="typeTpl">
    {{#
    var val = $('#hidden').val();
    var goodsType = eval('' + val + '');
    }}
    <select name="type" lay-filter="type">

        {{#
        for (var i = 0; i < goodsType.length; i++) {
        if( d.type == goodsType[i].id){
        }}
        <option value={{=goodsType[i].id}} selected>{{goodsType[i].typeName}}</option>
        {{#
        }else{
        }}
        <option value={{=goodsType[i].id}}>{{goodsType[i].typeName}}</option>
        {{#
        }
        }}
        {{#
        }
        }}
    </select>
</script>
<script>
    layui.use(['table', 'form'], function () {
        var $ = layui.$
                , form = layui.form
                , table = layui.table;
        //正整数
        var pIntReg = /^[1-9]\d*$/;
        //大于0的整数加小数
        var pIntPointReg = /^\d*\.?\d+$/;
        //表格里的下拉框值改变时更新表格缓存数据，这样保存或者复制时获取的数据才正确
        form.on('select(type)', function (data) {
            var selectElem = $(data.elem);    //获取下拉框
            var tdElem = selectElem.closest('td'); //父元素td
            var trElem = tdElem.closest('tr');   //父元素tr
            var index = trElem.data('index');   //所在行的索引
            //更新这一行的type值，并重新加载表格才会生效
            table.cache.goods_add[index].type = data.value;
            table.reload('goods_add', {data: table.cache.goods_add});
        });

        //这种写法的函数在html里的比如onlick方法都能调用到
        //window.updateTypeVal = function(){};

        //------------------------------------品类下拉框数据初始化------------------------------------
        $.ajax({
            url: "/goods/listAllType",
            type: 'Get',
            contentType: 'application/json',
            dataType: 'json',
            async: false,
            success: function (data) {
                $('#hidden').val(JSON.stringify(data.data));
            }
        });

        //------------------------------------表格初始化------------------------------------
        table.render({
            id: 'goods_add'
            , elem: '#goods_add'
            , height: 400
            , limit: 100
            , data: []
            , cols: [[ //表头
                {field: 'spuId', title: '商品编号', width: 90, fixed: 'left'}
                , {field: 'skuId', title: 'sku编号', hide: true}
                , {field: 'spuName', title: '商品名称', width: 200, fixed: 'left', edit: 'text'}
                , {field: 'type', title: '品类', width: 130, templet: '#typeTpl'}
                , {field: 'size', title: '尺码', width: 90, edit: 'text'}
                , {field: 'color', title: '颜色', width: 90, edit: 'text'}
                , {field: 'feature1', title: '特征一', width: 90, edit: 'text'}
                , {field: 'feature2', title: '特征二', width: 90, edit: 'text'}
                , {field: 'feature3', title: '特征三', width: 90, edit: 'text'}
                , {field: 'count', title: '数量', width: 90, edit: 'text'}
                , {field: 'inPrice', title: '成本价', width: 90, edit: 'text'}
                , {field: 'salePrice', title: '销售价', width: 90, edit: 'text'}
                , {fixed: 'right', width: 120, align: 'center', toolbar: '#bar'}
            ]]
        });

        //------------------------------------按钮事件------------------------------------
        var addFlag = true;
        form.on('submit(add)', function (data) {
            if(!addFlag){
                return;
            }
            addFlag = false;
            var field = data.field;
            if (field.spuId == '') {
                layer.msg('请输入需要录入库存的商品编号！');
                addFlag = true;
                return;
            }
            $.ajax({
                url: "/goods/listGoodsBySpuId",
                type: 'Get',
                data: field,
                contentType: 'application/json',
                dataType: 'json',
                success: function (dt) {
                    var result = dt.data;
                    if (result.length == 0) {
                        result =
                                [{
                                    'spuId': field.spuId,
                                    'skuId': null,
                                    'spuName': null,
                                    'inPrice': null,
                                    'salePrice': null,
                                    'type': 1,
                                    'typeName': null,
                                    'size': null,
                                    'color': null,
                                    'count': null,
                                    'feature1': null,
                                    'feature2': null,
                                    'feature3': null
                                }]
                    }

                    var oldData = table.cache.goods_add;
                    if (oldData != null) {
                        for (var i = 0; i < result.length; i++) {
                            oldData.push(result[i]);
                        }
                    } else {
                        oldData = result;
                    }
                    //执行重载
                    table.reload('goods_add', {
                        data: oldData
                    });
                    addFlag = true;
                    $('input[name=spuId]').val('');
                }
            });
        });

        form.on('submit(save)', function (data) {
            var rowCount = $('.layui-table tbody').find('tr').length;
            if (rowCount == 0) {
                layer.msg('请往表格添加数据！');
                return;
            }

            layer.confirm('将保存表格中的所有商品，确认无误了吗？', function (index) {
                var allData = table.cache.goods_add;
                var result = [];
                //验证合法性再提交
                for(var i = 0;i<allData.length;i++){
                    //前端页面删除了行，获取的数据会存在空行
                    if(allData[i].length == 0){
                        allData.splice(i,1);
                        continue;
                    }
                    //把不是空行的存起来提交
                    result.push(allData[i]);
                    var rowNum = Number(i)+1;
                    var count = allData[i].count;
                    var inPrice = allData[i].inPrice;
                    var salePrice = allData[i].salePrice;
                    if(!pIntReg.test(count)){
                        layer.msg('第'+rowNum+'行的【数量】应填大于0的整数！请修改后重新提交');
                        return;
                    }
                    if(!pIntPointReg.test(inPrice)){
                        layer.msg('第'+rowNum+'行的【成本价】应填大于0的数字！请修改后重新提交');
                        return;
                    }
                    if(!pIntPointReg.test(salePrice)){
                        layer.msg('第'+rowNum+'行的【销售价】应填大于0的数字！请修改后重新提交');
                        return;
                    }
                }
                $.ajax({
                    url: "/goods/batchAddImportGoods",
                    type: 'post',
                    data: JSON.stringify(result),
                    contentType: 'application/json',
                    dataType: 'json',
                    async: false,
                    success: function (data) {
                        layer.msg('保存成功！');
                        setTimeout(function(){
                            window.location.reload();//刷新当前页面.
                        },1000);
                    }
                });
            });
        });

        //------------------------------------复制删除行------------------------------------
        table.on('tool(goods_add)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）

            if (layEvent === 'copy') { //复制
                var oldData = table.cache.goods_add;
                data.skuId = null;
                oldData.push(data);
                table.reload('goods_add', {data: oldData});
            } else if (layEvent === 'del') {
                obj.del();
            }
        });

        //------------------------------------监听单元格编辑------------------------------------
        table.on('edit(goods_add)', function (obj) {
            var fieldName = obj.field;
            var skuId = obj.data.skuId;
            var oldVal = $(obj.tr).find('td[data-field='+fieldName+'] div').text();
            if (fieldName == 'count') {
                if(!pIntReg.test(obj.value)){
                    layer.msg('【数量】请输入大于0的整数！');
                }
            }
            if (fieldName == 'inPrice') {
                if(!pIntPointReg.test(obj.value)){
                    layer.msg('【成本价】请输入大于0的数字！');
                }
            }
            if (fieldName == 'salePrice') {
                if(!pIntPointReg.test(obj.value)){
                    layer.msg('【销售价】请输入大于0的数字！');
                }
            }
            if (skuId != null && fieldName != 'count' && fieldName != 'inPrice') {
                layer.confirm('确定将' + oldVal + '改成' + obj.value + '？历史库存将一同修改', function (index) {
                    layer.close(index);
                }, function () {
                    var fieldObj = {};
                    fieldObj[fieldName] = oldVal;
                    obj.update(fieldObj);
                });
            }
        });
    });
</script>