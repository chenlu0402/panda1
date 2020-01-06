<!DOCTYPE html>
<html lang="en">
<#include "head.ftl">
<body>
    <div class="easyui-panel" style="width:auto;height:500px;">
        <form id="ff" method="post">
            <div>
                <label for="name">Name:</label>
                <input type="text" id="name"/>
            </div>
        </form>
        <div id="tb">
            <a href="#" class="easyui-linkbutton" id="query" data-options="plain:true">查询</a>
            <a href="#" class="easyui-linkbutton" id="add" data-options="plain:true">添加</a>
            <a href="#" class="easyui-linkbutton" id="delete" data-options="plain:true">删除</a>
            <a href="#" class="easyui-linkbutton" id="update" data-options="plain:true">更新</a>
        </div>
        <table class="easyui-datagrid" style="width:400px;height:250px" id="dg"
               data-options="fitColumns:true,singleSelect:true,toolbar:'#tb',pagination:true">
            <thead>
            <tr>
                <th data-options="field:'spuId'">商品id</th>
                <th data-options="field:'spuName'">商品名称</th>
                <th data-options="field:'inPrice'">进价</th>
                <th data-options="field:'salePrice'">售价</th>
                <th data-options="field:'totalCount'">总件数</th>
                <th data-options="field:'createdTime'">创建时间</th>
                <th data-options="field:'updatedTime'">修改时间</th>
            </tr>
            </thead>
        </table>
    </div>
</body>
</html>

<script type="text/javascript">
    $(function(){
        $('#query').bind('click',function () {
          var spuName = $('#name').val();
            /*var queryParams = $('#dg').datagrid('options').queryParams;
            queryParams.spuName = spuName;
            $('#dg').datagrid('options').queryParams=queryParams;*/
            $('#dg').datagrid('load', {
                'spuName': spuName
            });
            //$('#dg').datagrid('reload');
        });

        $('#dg').datagrid({
            loader: function (param, success, error) {
                $.ajax({
                    type: "POST",
                    url : 'http://localhost:8080/goods/pageQuerySpu',
                    contentType: "application/json",
                    dataType: "json",
                    data: JSON.stringify({'spuName':""})//数据转换成JSON格式
                }).done(function (data) {
                    success(data.data);
                }).fail(function () {

                });
            }
        })
    });
</script>