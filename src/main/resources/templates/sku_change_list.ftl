<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>change</title>
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
                    <label class="layui-form-label">变更时间</label>
                    <div class="layui-input-block">
                        <input type="text" id="datetime" name="datetimeRange" style="width: 320px" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <button class="layui-btn layuiadmin-btn-replys" data-type="reload" lay-submit
                            lay-filter="change-search">
                        <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
                    </button>
                </div>
            </div>
        </div>
        <div class="layui-card-body">
            <table class="layui-table" id="change-list" lay-filter="change-list"></table>
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
            id: 'change-list'
            , elem: '#change-list'
            , url: '/goods/pageQueryChangeDetail' //数据接口
            , method: 'post'
            , contentType: 'application/json'
            , page: true //开启分页
            , cols: [[ //表头
                {field: 'spuName', title: '商品名称',width: 200,fixed: 'left'}
                , {field: 'size', title: '尺寸',width: 90}
                , {field: 'color', title: '颜色',width: 90}
                , {field: 'count', title: '变更数量',width: 90}
                , {field: 'actionDesc', title: '变更类型',width: 90}
                , {field: 'createdTime', title: '变更时间',width: 160}
            ]]
        });
        //---------------------------搜索-----------------------------
        form.on('submit(change-search)', function (data) {
            var field = data.field;

            //执行重载
            table.reload('change-list', {
                where: field
            });
        });
    });
</script>

