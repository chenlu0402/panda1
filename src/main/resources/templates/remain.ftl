<!DOCTYPE html>
<html lang="en">
<#include "head.ftl">
<body>
    <div class="easyui-panel" style="width:auto;height:500px;">

        <table class="easyui-datagrid" style="width:400px;height:250px"
               data-options="url:'http://localhost:8080/user',fitColumns:true,singleSelect:true">
            <thead>
            <tr>
                <th data-options="field:'id',width:100">id</th>
                <th data-options="field:'name',width:100">Name</th>
                <th data-options="field:'password',width:100,align:'right'">password</th>
            </tr>
            </thead>
        </table>
    </div>
</body>
</html>

<script type="text/javascript">
    $(function(){

    });
</script>