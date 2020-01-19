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
                    <label class="layui-form-label">货物名称</label>
                    <div class="layui-input-block">
                        <input type="text" name="spuName" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <button class="layui-btn layuiadmin-btn-replys" data-type="reload" lay-submit
                            lay-filter="LAY-app-forumreply-search">
                        <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
                    </button>
                </div>
                <div class="layui-inline">
                    <button class="layui-btn layuiadmin-btn-replys" lay-submit
                            lay-filter="add_goods">
                        添加商品
                    </button>
                </div>
            </div>
        </div>
        <div class="layui-card-body">
            <table id="goods_detail_list" lay-filter="goods_detail_list"></table>
        </div>
    </div>
</div>
<div class="layui-form" lay-filter="edit_detail" id="edit_detail" style="padding: 20px 30px 0 0;display: none">
    <input type="hidden" name="spuId" value="">
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
    <a class="layui-btn layui-btn-xs layui-btn-radius layui-btn-warm" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-xs layui-btn-radius layui-btn-danger" lay-event="del">删除</a>
</script>
<script>
    layui.config({
        base: '/layui/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'table'], function () {
        var $ = layui.$
                , form = layui.form
                , table = layui.table;

        table.render({
            id: 'goods_detail_list'
            , elem: '#goods_detail_list'
            , height: 312
            , url: '/goods/listSkuBySpuId' //数据接口
            , contentType: 'application/json'
            , page: true //开启分页
            , cols: [[ //表头
                {field: 'skuId', title: '编号', fixed: 'left'}
                , {field: 'size', title: '尺寸'}
                , {field: 'color', title: '颜色'}
                , {field: 'feature1', title: '特征一'}
                , {field: 'feature2', title: '特征二'}
                , {field: 'count', title: '数量'}
                , {fixed: 'right', align: 'center', toolbar: '#bar'}
            ]]
        });

        //监听添加
        form.on('submit(add_goods)', function (data) {
            layer.open({
                type: 1,
                title:'添加商品',
                btn: ['确认', '取消'],
                yes: function(index, layero){
                    var formVal = form.val("edit_detail");
                    $.ajax({
                        url: "/goods/addSpu",
                        type:'POST',
                        data:JSON.stringify(formVal),
                        contentType:'application/json',
                        dataType:'json',
                        success: function(){
                            layer.close(index);
                            table.reload('goods_detail_list');
                            layer.msg('添加成功！');
                        }
                    });
                },
                cancel:function (index, layero) {
                    layer.close(index);
                },
                content: $('#edit_detail') //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
            });
        });

        table.on('tool(goods_detail_list)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）

            if (layEvent === 'del') { //删除
                layer.confirm('真的删除行么', function (index) {
                    var spuId = data.spuId;
                    $.ajax({
                        url: "/goods/updateSpu",
                        type:'POST',
                        data:JSON.stringify({"spuId":spuId,"isDeleted":1}),
                        contentType:'application/json',
                        dataType:'json',
                        success: function(){
                            layer.close(index);
                            table.reload('goods_detail_list');
                            layer.msg('删除成功！');
                        }
                    });
                });
            } else if (layEvent === 'edit') { //编辑
                form.val("edit_detail", {//formTest 即 class="layui-form" 所在元素属性 lay-filter="" 对应的值
                    "spuId":data.spuId
                    ,"spuName": data.spuName // "name": "value"
                    ,"inPrice":data.inPrice
                    ,"salePrice":data.salePrice
                    ,"totalCount": data.totalCount
                });
                layer.open({
                    type: 1,
                    title:'修改商品',
                    btn: ['确认', '取消'],
                    yes: function(index, layero){
                        var formVal = form.val("edit_detail");
                        $.ajax({
                            url: "/goods/updateSpu",
                            type:'POST',
                            data:JSON.stringify(formVal),
                            contentType:'application/json',
                            dataType:'json',
                            success: function(){
                                layer.close(index);
                                table.reload('goods_detail_list');
                                layer.msg('修改成功！');
                            }
                        });
                    },
                    cancel:function (index, layero) {
                        layer.close(index);
                    },
                    content: $('#edit_detail') //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
                });
            }
        });
    });
</script>

