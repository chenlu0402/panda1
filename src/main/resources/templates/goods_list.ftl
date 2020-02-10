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
                    <label class="layui-form-label">商品名称</label>
                    <div class="layui-input-block">
                        <input type="text" name="spuName" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">品类</label>
                    <div class="layui-input-block">
                        <select name="type" lay-verify="">
                            <option value="">请选择商品类型</option>
                            <#list goodsTypeList as t>
                                <option value="${t.id}">${t.typeName}</option>
                            </#list>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <button class="layui-btn layuiadmin-btn-replys" data-type="reload" lay-submit
                            lay-filter="goods-search">
                        <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
                    </button>
                </div>
            </div>
        </div>
        <div class="layui-card-body">
            <table id="goods-list" lay-filter="goods-list"></table>
        </div>
    </div>
</div>
</body>
</html>
<script>
    layui.config({
        base: '/layui/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'table', 'upload', 'form'], function () {
        var $ = layui.$
                , form = layui.form
                , table = layui.table
                , upload = layui.upload;

        //----------------------------上传--------------------------------------
        upload.render({
            elem: '#upload' //绑定元素
            , url: '/goods/upload' //上传接口
            , accept: 'file'
            , size: 1024
            , done: function (res) {
                if (res.code == 0) {
                    table.reload('goods-list');
                    layer.msg('导入成功！');
                } else {
                    layer.msg(res.msg);
                }
            }
            , error: function () {
                //请求异常回调
            }
        });

        //------------------------------------表格初始化------------------------------------
        table.render({
            id: 'goods-list'
            , elem: '#goods-list'
            , url: '/goods/pageQueryGoods' //数据接口
            , method: 'post'
            , contentType: 'application/json'
            , page: true //开启分页
            , cols: [[ //表头
                {field: 'spuId', title: '商品编号',width: 90, fixed: 'left'}
                , {field: 'spuName', title: '商品名称',width: 200, fixed: 'left'}
                , {field: 'typeName', title: '品类',width: 90}
                , {field: 'size', title: '尺码',width: 90}
                , {field: 'color', title: '颜色',width: 90}
                , {field: 'feature1', title: '特征一',width: 90}
                , {field: 'feature2', title: '特征二',width: 90}
                , {field: 'feature3', title: '特征三',width: 90}
                , {field: 'count', title: '数量',width: 90,sort: true}
                , {field: 'salePrice', title: '销售价',width: 90}
                , {field: 'createdTime', title: '插入时间', width: 170, sort: true}
                , {field: 'updatedTime', title: '更新时间', width: 170, sort: true}
            ]]
        });

        //---------------------------搜索-----------------------------
        form.on('submit(goods-search)', function (data) {
            var field = data.field;

            //执行重载
            table.reload('goods-list', {
                where: field
            });
        });
    });
</script>

