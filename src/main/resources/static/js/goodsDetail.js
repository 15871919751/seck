function getDetail() {
    var goodsId = g_getQueryString("goodsId");
    $.ajax({
        url: "/goods/detail/" + goodsId,
        type: "GET",
        success: function (data) {
            if (data.state == 200) {
                render(data);
            } else {
                layer.msg(data.msg)
            }

        },
        error: function () {
            layer.msg("请求服务器错误")
        }
    });
}

function render(detail) {
    var secKillStatus = detail.content.secKillStatus;
    var goodsVo = detail.content.goodsVo;
    var user = detail.content.user;
    var remainSeconds = detail.content.remainSeconds;
    if (user != null) {
        $("#userTip").hide();
        $("#nickName").html(user.nickname);
    }
    $("#goodsName").text(goodsVo.goodsName);
    $("#goodsImg").attr("src",goodsVo.goodsImg);
    $("#startDate").text(new Date(goodsVo.startDate).format("yyyy-MM-dd hh:mm:ss"));
    if (secKillStatus==0){
        $("#buyButton").attr("disabled", true);
        $("#miaoshatext").html("秒杀未开始");
        timer(remainSeconds);
    }  else if (secKillStatus == 1) {
        $("#miaoshatext").html("秒杀进行中");
        $("#buyButton").attr("disabled", false);
        $("#verifyCodeImg").attr("src", "/miaosha/verifyCode?goodsId=" + goodsVo.id);
        $("#verifyCodeImg").show();
        $("#verifyCode").show();
        timer(remainSeconds);
    } else {
        $("#buyButton").attr("disabled", true);
        $("#miaoshaTip").html("秒杀已结束");
        $("#verifyCodeImg").hide();
        $("#verifyCode").hide();
    }
    $("#goodsId").val(goodsVo.id);
    $("#goodsPrice").html(goodsVo.goodsPrice);
    $("#miaoshaPrice").html(goodsVo.secKillPrice);
    $("#stockCount").html(goodsVo.stockCount);
}

function timer(intDiff) {
    window.setInterval(function () {
        var day = 0,
            hour = 0,
            minute = 0,
            second = 0;//时间默认值
        if (intDiff > 0) {
            day = Math.floor(intDiff / (60 * 60 * 24));
            hour = Math.floor(intDiff / (60 * 60)) - (day * 24);
            minute = Math.floor(intDiff / 60) - (day * 24 * 60) - (hour * 60);
            second = Math.floor(intDiff) - (day * 24 * 60 * 60) - (hour * 60 * 60) - (minute * 60);
        }
        if (minute <= 9) minute = '0' + minute;
        if (second <= 9) second = '0' + second;
        $('#day_show').html(day + "天");
        $('#hour_show').html('<s id="h"></s>' + hour + '时');
        $('#minute_show').html('<s></s>' + minute + '分');
        $('#second_show').html('<s></s>' + second + '秒');
        intDiff--;
    }, 1000);
}

function getMiaoshaResult(goodsId) {
    g_showLoading();
    $.ajax({
        url: "/miaosha/result",
        type: "GET",
        data: {goodsId: goodsId},
        success: function (data) {
            if (data.state == 200) {
                var result = data.content;
                if (result < 0) {
                    layer.msg("秒杀失败")
                }else if (result == 0) {
                    setTimeout(function () {
                        getMiaoshaResult(goodsId);
                    }, 50);
                } else {
                    layer.confirm("恭喜你,秒杀成功!查看订单?", {btn: ["确定", "取消"]},
                        function () {
                            window.location.href = "/order_detail.htm?orderId=" + result;
                           },
                        function () {
                            layer.closeAll();
                           });
                }
            } else {
                layer.msg(data.msg);
            }
        },
        error: function () {
            layer.msg("秒杀服务请求错误");
        }
    });
}


function doMiaosha(path) {
    var goodsId = $("#goodsId").val();
    $.ajax({
        url: "/miaosha/"+path+"/do_miaosha",
        type: "POST",
        data: {goodsId: goodsId},
        success: function (data) {
            if (data.state == 200) {
                getMiaoshaResult(goodsId);
            } else {
                layer.msg(data.msg);
            }
        },
        error: function () {
            layer.msg("秒杀服务请求错误");
        }
    });
}


function getMiaoshaPath() {
    var goodsId = $("#goodsId").val();
    var verifyCode = $("#verifyCode").val();
    $.ajax({
        url: "/miaosha/path",
        type: "GET",
        data: {goodsId: goodsId, verifyCode: verifyCode},
        success: function (data) {
            if (data.state == 200) {
                var path = data.content;
                doMiaosha(path);
            } else {
                layer.msg(data.msg);
            }
        },
        error: function () {
            layer.msg("获取秒杀地址错误");
        }
    });
}

function changeCodeImg() {
    $("#verifyCodeImg").attr("src", "/miaosha/verifyCode?goodsId=" + $("#goodsId").val() + "&timestamp=" + new Date().getTime());
}



