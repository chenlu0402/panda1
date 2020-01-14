<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>登入</title>
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

  <div class="layadmin-user-login layadmin-user-display-show" id="LAY-user-login" style="display: none;">

    <div class="layadmin-user-login-main">
      <div class="layadmin-user-login-box layadmin-user-login-header">
        <h2>季青职业服</h2>
        <p>龙岩市季青服装有限公司后台管理系统</p>
      </div>
      <div class="layadmin-user-login-box layadmin-user-login-body layui-form">
        <div class="layui-form-item">
          <label class="layadmin-user-login-icon layui-icon layui-icon-username" for="LAY-user-login-username"></label>
          <input type="text" name="username" id="LAY-user-login-username" lay-verify="required" placeholder="用户名" class="layui-input">
        </div>
        <div class="layui-form-item">
          <label class="layadmin-user-login-icon layui-icon layui-icon-password" for="LAY-user-login-password"></label>
          <input type="password" name="password" id="LAY-user-login-password" lay-verify="required" placeholder="密码" class="layui-input">
        </div>
        <div class="layui-form-item">
          <button class="layui-btn layui-btn-fluid" lay-submit lay-filter="LAY-user-login-submit">登 入</button>
        </div>
      </div>
    </div>
  </div>
  <script>
      $(function(){
          layui.use(['form' ,'layer'], function() {
              var form = layui.form;
              var layer = layui.layer;
              form.on("submit(LAY-user-login-submit)",function () {
                  login();
                  return false;
              });
              var path=window.location.href;
              if(path.indexOf("kickout")>0){
                  layer.alert("您的账号已在别处登录；若不是您本人操作，请立即修改密码！",function(){
                      window.location.href="/login";
                  });
              }
          })
      })

      function login(){
          var username=$("#username").val();
          var password=$("#password").val();
          var rememberMe = $("#rememberMe").val();
          $.get("/user/login",$("#useLogin").serialize(),function(data){
              //if(data.code == 1){
                  window.location.href=data;
              /*}else{
                  layer.alert(data.message,function(){
                      layer.closeAll();//关闭所有弹框
                  });
              }*/
          });
      }
  </script>
</body>
</html>