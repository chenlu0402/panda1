<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>销售</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="/layui/css/admin.css" media="all">
    <link rel="stylesheet" href="/layui/css/login.css" media="all">
    <script type="text/javascript" src="/layui/layui.js"></script>
    <script type="text/javascript" src="/jquery.min.js"></script>
</head>
<body>
<div class="layui-form">
    <div class="layui-input-inline">
        <select id="sex">
            <option value="">全部性别</option>
            <option value="1">男</option>
            <option value="2">女</option>
        </select>
    </div>
    <div class="layui-input-inline">
        <input type="text" id="title" placeholder="用户名模糊搜索" class="layui-input">
    </div>
    <div class="layui-btn-group">
        <button id="reload" class="layui-btn">搜索</button>
        <button id="submit" class="layui-btn">保存</button>
    </div>
</div>
<div id="transferTable"></div>
</body>
</html>
<script type="text/javascript">
    layui.config({
        base:'/layui_exts/'
    }).use('transferTable', function(){
        var transferTable = layui.transferTable,$=layui.$;
        var cols = [
            {checkbox: true, fixed: true}
            ,{field:'id', title: 'ID'}
            ,{field:'username', title: '用户名'}
            ,{field:'sex', title: '性别'}
        ]

        transferTable.render({
            elem: '#transferTable'
            ,url: ['api.php?t=1','api.php?t=2']
            ,cols: [[cols],[cols]]
            ,page: [true,true]
            ,id:['transferTable_1_1','transferTable_2_2']
            ,height:[500,500]
            ,where:{id:'1,2,3'}
            ,id_name:'id'
        })

        $('#reload').on('click',function(){
            transferTable.reload('transferTable_1_1',{
                page:{curr:1},
                where:{
                    title: $('#title').val(),
                    sex: $('#sex').val()
                }
            })
        })

        $('#submit').on('click',function(){
            var id = transferTable.get('transferTable_2_2');
            layer.msg(JSON.stringify(id));
        })

    })
</script>