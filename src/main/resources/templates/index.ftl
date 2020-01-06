<!DOCTYPE html>
<html lang="en">
<#include "head.ftl">
<body>
    <div class="easyui-panel" title="龙岩市季青服装有限公司" style="width:auto;height:500px;">
        <div class="easyui-layout" data-options="fit:true">
            <div data-options="region:'west',split:true" style="width:200px;padding:10px">
                <ul class="easyui-tree" id="tree">
                    <li id="intel">
                        <span>panda sale</span>
                        <ul>
                            <li>库存管理</li>
                            <li>销售管理</li>
                            <li>经营分析</li>
                        </ul>
                    </li>
                </ul>
            </div>
            <div data-options="region:'center'" id="myTab" class="easyui-tabs"  style="padding:10px"></div>
        </div>
    </div>
</body>
</html>

<script type="text/javascript">
    $(function(){
        $('#tree').tree({
            onClick: function(node){
                var title = node.text;
                if ($('#myTab').tabs('exists', title)){
                    $('#myTab').tabs('select', title);
                } else {
                    /**添加一个tab标签**/
                    $("#myTab").tabs('add',{
                        title: title,
                        fit:true,
                        selected: true,
                        closable:true,
                        content:'<iframe src="http://localhost:8080/remain" scrolling="no" style="width:100%;height:100%;"></iframe>'
                    });
                }
            }
        });
    });
</script>