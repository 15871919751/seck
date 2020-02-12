package org.ct.seckill.controller;

import org.ct.seckill.common.MyRsp;
import org.ct.seckill.domain.OrderInfo;
import org.ct.seckill.service.GoosService;
import org.ct.seckill.service.MiaoshaService;
import org.ct.seckill.service.OrderService;
import org.ct.seckill.vo.GoodsVo;
import org.ct.seckill.vo.OrderInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Author K
 * @Date 2020/2/4 14:04
 * @Version 1.0
 */
@RequestMapping(path = "/order")
@Controller
public class OrderInfoController {

    @Autowired
    MiaoshaService miaoshaService;

    @Autowired
    OrderService orderService;

    @Autowired
    GoosService goodsService;

    @GetMapping("/detail")
    @ResponseBody
    public Object orderInfo(long orderId) {
        OrderInfo orderInfo = orderService.getOrderInfo(orderId);
        GoodsVo goodsVo = goodsService.getGoodsVoByGoodsId(orderInfo.getGoodsId());
        OrderInfoVo orderInfoVo = new OrderInfoVo(orderInfo, goodsVo);
        return MyRsp.success(orderInfoVo);
    }

}
