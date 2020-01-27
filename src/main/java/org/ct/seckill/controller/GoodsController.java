package org.ct.seckill.controller;

import lombok.extern.slf4j.Slf4j;
import org.ct.seckill.service.GoosService;
import org.ct.seckill.service.SeckillUserService;
import org.ct.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * @Author K
 * @Date 2020/1/18 0:26
 * @Version 1.0
 */

@Slf4j
@Controller
@RequestMapping(path = "/goods")
public class GoodsController {


    @Autowired
    SeckillUserService seckillUserService;

    @Autowired
    GoosService goodsService;

    @GetMapping("/to_list")
    public Object list(Model model) {
        List<GoodsVo> goodsList = goodsService.getListGoodsVo();
        model.addAttribute("goodsList", goodsList);
        return "goods_list";
    }
}
