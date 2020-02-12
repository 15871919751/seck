function getOrderInfo() {
    var orderId = g_getQueryString("orderId");
    $.ajax({
        url: "/order/detail",
        type: "GET",
        data: {orderId: orderId},
        success: function (data) {
            if (data.state == 200) {
                paddingOrderDetail(data)
            } else {
                layer.msg(data.msg);
            }
        },
        error: function () {
            layer.msg("秒杀服务请求错误");
        }
    });
}

function paddingOrderDetail(detail) {
    var orderInfo = detail.content.orderInfo;
    var goodsVo = detail.content.goodsVo;
    var orderInfoStatus = orderInfo.status;
    $("#goodsName").html(orderInfo.goodsName);
    $("#goodsImg").attr("src",goodsVo.goodsImg);
    $("#secKillPrice").html(orderInfo.goodsPrice);
    $("#nowDate").text(new Date(orderInfo.createDate).format("yyyy-MM-dd hh:mm:ss"));

    if (orderInfoStatus == 0) {
        $("#orderInfoStatus").html("未支付");
    }else   if (orderInfoStatus == 1) {
        $("#orderInfoStatus").html("已支付");
    }else   if (orderInfoStatus == 2) {
        $("#orderInfoStatus").html("已发货");
    }else if (orderInfoStatus == 3) {
        $("#orderInfoStatus").html("已收货");
    } else if (orderInfoStatus == 4) {
        $("#orderInfoStatus").html("已退款");
    }else if (orderInfoStatus == 5) {
        $("#orderInfoStatus").html("已完成");
    } else {
        $("#orderInfoStatus").html("状态有误");
    }
}