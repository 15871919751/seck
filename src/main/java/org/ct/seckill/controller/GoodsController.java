package org.ct.seckill.controller;

import lombok.extern.slf4j.Slf4j;
import org.ct.seckill.common.MyRsp;
import org.ct.seckill.domain.SecKillUser;
import org.ct.seckill.redis.GoodsKey;
import org.ct.seckill.redis.RedisService;
import org.ct.seckill.service.GoosService;
import org.ct.seckill.service.SeckillUserService;
import org.ct.seckill.vo.DetailVo;
import org.ct.seckill.vo.GoodsVo;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.IWebContext;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    static  int  secKillStatus = 0;

    static int remainSeconds = 0;

    @Autowired
    SeckillUserService seckillUserService;

    @Autowired
    GoosService goodsService;

    @Autowired
    RedisService redisService;

    @Autowired
    ThymeleafViewResolver thymeleafViewResolver;

    @GetMapping(value = "/to_list",produces = "text/html")
    @ResponseBody
    public Object list(HttpServletRequest request,HttpServletResponse response, Model model) {
        //取缓存
        String html = redisService.get(GoodsKey.GOODS_KEY, "", String.class);
        if (StringUtils.isNotBlank(html)) {
            return html;
        }
        List<GoodsVo> goodsList = goodsService.getListGoodsVo();
        model.addAttribute("goodsList", goodsList);
        //手动渲染
        IWebContext iWebContext = new WebContext(request, response, request.getServletContext(), request.getLocale(), model.asMap());
        html =  thymeleafViewResolver.getTemplateEngine().process("goods_list", iWebContext);
        if (StringUtils.isNotBlank(html)) {
            redisService.set(GoodsKey.GOODS_KEY, "", html);
        }
        return html;
    }

//    @GetMapping(value = "/to_detail2/{goodsId}", produces = "text/html")
//    @ResponseBody
//    public Object detail2(HttpServletRequest request,HttpServletResponse response,Model model, @PathVariable("goodsId") Long goodsId, SecKillUser user) {
//        //取商品详细缓存
//        String html = redisService.get(GoodsKey.GOODS_DETAIL_KEY, String.valueOf(goodsId), String.class);
//        if (StringUtils.isNotBlank(html)) {
//            return html;
//        }
//        GoodsVo goodsVo = goodsService.getGoodsVoByGoodsId(goodsId);
//        model.addAttribute("goodsVo", goodsVo);
//        model.addAttribute("user", user);
//        //秒杀开始时间
//        long startAt = goodsVo.getStartDate().getTime();
//        //秒杀结束时间
//        long endAt = goodsVo.getEndDate().getTime();
//        //现在时间
//        long nowAt = System.currentTimeMillis();
//        //剩余秒杀时间
//        if (nowAt < startAt) {
//            //秒杀未开始
//            secKillStatus = 0;
//            remainSeconds = (int) (startAt - nowAt) / 1000;
//        } else if (nowAt > endAt) {
//            //秒杀已经结束
//            secKillStatus = 2;
//            remainSeconds = -1;
//        } else {
//            //秒杀进行中
//            secKillStatus = 1;
//            remainSeconds = (int) (endAt - nowAt)/1000;
//        }
//        //秒杀状态 0未开始 1进行中 2已经结束
//        model.addAttribute("secKillStatus", secKillStatus);
//        //秒杀开始时间
//        model.addAttribute("remainSeconds", remainSeconds);
//        //手动渲染
//        IWebContext iWebContext = new WebContext(request, response, request.getServletContext(), request.getLocale(), model.asMap());
//        html =  thymeleafViewResolver.getTemplateEngine().process("goods_detail", iWebContext);
//        if (StringUtils.isNotBlank(html)) {
//            redisService.set(GoodsKey.GOODS_DETAIL_KEY, String.valueOf(goodsId), html);
//        }
//        return html;
//    }

    @GetMapping(value = "/detail/{goodsId}")
    @ResponseBody
    public Object detail( @PathVariable("goodsId") Long goodsId, SecKillUser user) {
        GoodsVo goodsVo = goodsService.getGoodsVoByGoodsId(goodsId);
        //秒杀开始时间
        long startAt = goodsVo.getStartDate().getTime();
        //秒杀结束时间
        long endAt = goodsVo.getEndDate().getTime();
        //现在时间
        long nowAt = System.currentTimeMillis();
        //剩余秒杀时间
        if (nowAt < startAt) {
            //秒杀未开始
            secKillStatus = 0;
            remainSeconds = (int) (startAt - nowAt) / 1000;
        } else if (nowAt > endAt) {
            //秒杀已经结束
            secKillStatus = 2;
            remainSeconds = -1;
        } else {
            //秒杀进行中
            secKillStatus = 1;
            remainSeconds = (int) (endAt - nowAt)/1000;
        }
        DetailVo detailVo = new DetailVo();
        detailVo.setRemainSeconds(remainSeconds);
        detailVo.setSecKillStatus(secKillStatus);
        detailVo.setGoodsVo(goodsVo);
        detailVo.setUser(user);
        return MyRsp.success(detailVo);
    }
}
