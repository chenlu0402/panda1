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
                    <button class="layui-btn" lay-submit lay-filter="add_goods">
                        添加商品
                    </button>
                    <button type="button" class="layui-btn" id="upload">
                       批量导入
                    </button>
                </div>
            </div>
        </div>
        <div class="layui-card-body">
            <table id="LAY-app-forumreply-list" lay-filter="LAY-app-forumreply-list"></table>
        </div>
    </div>
</div>
<div class="layui-form" lay-filter="edit_goods" id="edit_goods" style="padding: 20px 30px 0 0;display: none">
    <input type="hidden" name="spuId" value="">
    <div class="layui-form-item">
        <label class="layui-form-label">商品名称</label>
        <div class="layui-input-block">
            <input type="text" name="spuName" lay-verify="required" placeholder="请输入商品名称" autocomplete="off"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">进货价</label>
        <div class="layui-input-block">
            <input type="text" name="inPrice" lay-verify="number" placeholder="请输入进货价" autocomplete="off"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">售出价</label>
        <div class="layui-input-block">
            <input type="text" name="salePrice" lay-verify="number" placeholder="请输入售出价" autocomplete="off"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">数量</label>
        <div class="layui-input-inline">
            <input type="text" name="totalCount" lay-verify="number" placeholder="请输入数量" autocomplete="off"
                   class="layui-input">
        </div>
    </div>
</div>
</body>
</html>
<script type="text/html" id="bar">
    <a class="layui-btn layui-btn-xs layui-btn-radius layui-btn-normal" lay-event="detail">详情</a>
    <a class="layui-btn layui-btn-xs layui-btn-radius layui-btn-warm" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-xs layui-btn-radius layui-btn-danger" lay-event="del">删除</a>
</script>
<script>
    layui.config({
        base: '/layui/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'table','upload'], function () {
        var $ = layui.$
                , form = layui.form
                , table = layui.table
                ,upload = layui.upload;

        upload.render({
            elem: '#upload' //绑定元素
            ,url: '/goods/upload' //上传接口
            ,accept: 'file'
            ,size: 1024
            ,done: function(res){
                if(res.code == 0){
                    table.reload('LAY-app-forumreply-list');
                    layer.msg('导入成功！');
                }else{
                    layer.msg(res.msg);
                }
            }
            ,error: function(){
                //请求异常回调
            }
        });

        table.render({
            id: 'LAY-app-forumreply-list'
            , elem: '#LAY-app-forumreply-list'
            , url: '/goods/pageQuerySpu' //数据接口
            , method: 'post'
            , contentType: 'application/json'
            , page: true //开启分页
            , cols: [[ //表头
                {field: 'spuId', title: '商品编号', fixed: 'left'}
                , {field: 'spuName', title: '商品名称'}
                , {field: 'inPrice', title: '进货价'}
                , {field: 'salePrice', title: '售出价'}
                , {field: 'totalCount', title: '数量', sort: true}
                , {field: 'createdTime', title: '插入时间', width: 180, sort: true}
                , {field: 'updatedTime', title: '更新时间', width: 180, sort: true}
                , {fixed: 'right', width: 180, align: 'center', toolbar: '#bar'}
            ]]
        });

        //监听搜索
        form.on('submit(LAY-app-forumreply-search)', function (data) {
            var field = data.field;

            //执行重载
            table.reload('LAY-app-forumreply-list', {
                where: field
            });
        });

        //监听添加
        form.on('submit(add_goods)', function (data) {
            layer.open({
                type: 1,
                title:'添加商品',
                btn: ['确认', '取消'],
                yes: function(index, layero){
                    var formVal = form.val("edit_goods");
                    $.ajax({
                        url: "/goods/addSpu",
                        type:'POST',
                        data:JSON.stringify(formVal),
                        contentType:'application/json',
                        dataType:'json',
                        success: function(){
                            layer.close(index);
                            table.reload('LAY-app-forumreply-list');
                            layer.msg('添加成功！');
                        }
                    });
                },
                cancel:function (index, layero) {
                    layer.close(index);
                },
                content: $('#edit_goods') //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
            });
        });

        table.on('tool(LAY-app-forumreply-list)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）

            if (layEvent === 'detail') { //查看
                //do somehing
                alert('detail');
            } else if (layEvent === 'del') { //删除
                layer.confirm('确认删除？', function (index) {
                    var spuId = data.spuId;
                    $.ajax({
                        url: "/goods/updateSpu",
                        type:'POST',
                        data:JSON.stringify({"spuId":spuId,"isDeleted":1}),
                        contentType:'application/json',
                        dataType:'json',
                        success: function(){
                            layer.close(index);
                            table.reload('LAY-app-forumreply-list');
                            layer.msg('删除成功！');
                        }
                    });
                });
            } else if (layEvent === 'edit') { //编辑
                form.val("edit_goods", {//formTest 即 class="layui-form" 所在元素属性 lay-filter="" 对应的值
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
                        var formVal = form.val("edit_goods");
                        $.ajax({
                            url: "/goods/updateSpu",
                            type:'POST',
                            data:JSON.stringify(formVal),
                            contentType:'application/json',
                            dataType:'json',
                            success: function(){
                                layer.close(index);
                                table.reload('LAY-app-forumreply-list');
                                layer.msg('修改成功！');
                            }
                        });
                    },
                    cancel:function (index, layero) {
                        layer.close(index);
                    },
                    content: $('#edit_goods') //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
                });
            }
        });
    });
</script>

