function login(){
    $('#loginForm').validate({
        submitHandler:function(form){
            doLogin();
        }
    });
}
function doLogin(){
    g_showLoading();
    var pass = md5(""+g_passsword_salt.charAt(0)+g_passsword_salt.charAt(2)+$("#password").val()+g_passsword_salt.charAt(5)+g_passsword_salt.charAt(4))
    console.log(pass)
    $.ajax({
        type : "POST",
        url :  "/seckill/login/do_login",
        data : {
            "mobile":$("#mobile").val(),
            "password":pass
        },
        success : function(data){
            layer.closeAll();
            console.log(data)
            if(data.state == 200){
                layer.msg("登录成功", {time: 1000}, function () {
                    // window.location.href="/seckill/goods/to_list";
                });
            }else{
                layer.msg(data.msg);;
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            layer.closeAll();
            layer.msg("服务器发生异常");
        }
    });
}