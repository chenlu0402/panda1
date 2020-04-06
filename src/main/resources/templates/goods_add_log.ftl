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
        <div class="layui-form layui-card-header layuiadmin-card-header-auto">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label" style="width: 100px">商品名称</label>
                    <div class="layui-input-inline">
                        <input type="text" name="spuName" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">品类</label>
                    <div class="layui-input-inline">
                        <select name="type" lay-verify="">
                            <option value="">请选择商品类型</option>
                            <#list goodsTypeList as t>
                                <option value="${t.id}">${t.typeName}</option>
                            </#list>
                        </select>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label" style="width: 100px">商品录入时间</label>
                    <div class="layui-input-inline" style="width: 320px">
                        <input type="text" id="datetime" name="datetimeRange" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <button class="layui-btn" data-type="reload" lay-submit
                            lay-filter="goods_import_search">
                        <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
                    </button>
                </div>
                <div class="layui-inline">
                    <button class="layui-btn" lay-submit lay-filter="batch_add_goods">
                        批量录入
                    </button>
                </div>
            </div>
        </div>
        <div class="layui-card-body">
            <table id="goods_import" lay-filter="goods_import"></table>
        </div>
    </div>
</div>
<div class="layui-form" lay-filter="edit_goods" id="edit_goods" style="padding: 20px 30px 0 0;display: none">
    <input type="hidden" name="spuId" value="">
    <input type="hidden" name="skuId" value="">
    <div class="layui-form-item">
        <label class="layui-form-label">商品名称</label>
        <div class="layui-input-block">
            <input type="text" name="spuName" lay-verify="required" placeholder="请输入商品名称" autocomplete="off"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">品类</label>
        <div class="layui-input-block">
            <select name="type" lay-verify="">
                <#list goodsTypeList as t>
                    <option value="${t.id}">${t.typeName}</option>
                </#list>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">尺寸</label>
        <div class="layui-input-block">
            <input type="text" name="size" lay-verify="required" placeholder="请输入尺寸" autocomplete="off"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">颜色</label>
        <div class="layui-input-block">
            <input type="text" name="color" placeholder="请输入颜色" autocomplete="off"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">自定义1(选填)</label>
        <div class="layui-input-block">
            <input type="text" name="feature1" placeholder="请输入需要记录的特征" autocomplete="off"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">自定义2(选填)</label>
        <div class="layui-input-block">
            <input type="text" name="feature2" placeholder="请输入需要记录的特征" autocomplete="off"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">成本价</label>
        <div class="layui-input-block">
            <input type="text" name="inPrice" lay-verify="number" placeholder="请输入进货价" autocomplete="off"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">销售价</label>
        <div class="layui-input-block">
            <input type="text" name="salePrice" lay-verify="number" placeholder="请输入售出价" autocomplete="off"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">数量</label>
        <div class="layui-input-inline">
            <input type="text" name="count" lay-verify="number" placeholder="请输入数量" autocomplete="off"
                   class="layui-input">
        </div>
    </div>
</div>
</body>
</html>
<script type="text/html" id="bar">
    <a class="layui-btn layui-btn-xs layui-btn-radius layui-btn-danger" lay-event="del">删除</a>
</script>
<script>
    layui.use(['table', 'form', 'laydate'], function () {
        var $ = layui.$
                , form = layui.form
                , table = layui.table
                , upload = layui.upload
                , laydate = layui.laydate
                , layer = layui.layer;// 表单验证

        //日期时间范围选择
        laydate.render({
            elem: '#datetime'
            , type: 'datetime'
            , format: 'yyyy-MM-dd HH:mm:ss'
            , range: '~'
        });

        //------------------------------------表格初始化------------------------------------
        table.render({
            id: 'goods_import'
            , elem: '#goods_import'
            , url: '/goods/pageQueryImportGoods' //数据接口
            , method: 'post'
            , contentType: 'application/json'
            , page: true //开启分页
            , cols: [[ //表头
                {field: 'spuId', title: '商品编号', width: 90, fixed: 'left'}
                , {field: 'skuId', title: 'sku编号', hide: true}
                , {field: 'spuName', title: '商品名称', width: 200, fixed: 'left'}
                , {field: 'typeName', title: '品类', width: 90}
                , {field: 'size', title: '尺码', width: 90}
                , {field: 'color', title: '颜色', width: 90}
                , {field: 'feature1', title: '特征一', width: 90}
                , {field: 'feature2', title: '特征二', width: 90}
                , {field: 'feature3', title: '特征三', width: 90}
                , {field: 'count', title: '数量', width: 90, sort: true }
                , {field: 'inPrice', title: '成本价', width: 90}
                , {field: 'salePrice', title: '销售价', width: 90}
                , {field: 'createdTime', title: '插入时间', width: 170, sort: true}
                , {field: 'updatedTime', title: '更新时间', width: 170, sort: true}
                , {fixed: 'right', width: 120, align: 'center', toolbar: '#bar'}
            ]]
        });

        //---------------------------按钮事件（搜索、添加）-----------------------------
        form.on('submit(goods_import_search)', function (data) {
            var field = data.field;

            //执行重载
            table.reload('goods_import', {
                where: field
            });
        });

        form.on('submit(batch_add_goods)', function (data) {
            window.location.href="/goods_add_page";
        });

        //--------------------查看、删除、编辑---------------------------------------------------
        table.on('tool(goods_import)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）

            if (layEvent === 'del') { //删除
                var desc = data.spuName + data.size + data.color;
                layer.confirm('确认删除录入的商品：' + desc + '？', function (index) {
                    var skuId = data.skuId;
                    var createdTime = data.createdTime;
                    var count = data.count;
                    $.ajax({
                        url: "/goods/deletedImportedGoods",
                        type: 'POST',
                        data: JSON.stringify({"skuId": skuId, "createdTime":createdTime, "count": count}),
                        contentType: 'application/json',
                        dataType: 'json',
                        success: function () {
                            layer.close(index);
                            table.reload('goods_import');
                            layer.msg('删除成功！');
                        }
                    });
                });
            }
        });
    });
</script>

