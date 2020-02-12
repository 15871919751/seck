package org.ct.seckill.dao;
import org.apache.ibatis.annotations.Param;
import org.ct.seckill.domain.MiaoshaGoods;
import org.ct.seckill.vo.GoodsVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author K
 * @Date 2020/1/26 22:07
 * @Version 1.0
 */
@Repository("goodsDao")
public interface GoodsDao {
    /**
     * 通过miaosha_goods和goods的id联查
     *
     * @return 返回一个GoodsVo对象
     */
    List<GoodsVo> getListGoodsVo();
    /**
     * 根据商品Id查询商品详细信息
     * @param goodsId 商品Id
     * @return 返回一个商品详细信息
     * */
    GoodsVo getGoodsVoByGoodsId(Long goodsId);
     /**
      * 更改库存
      * @param miaoshaGoods 秒杀商品信息
      * @return 判断操作是否成功
      * */
    int reduceStock(@Param("miaoshaGoods") MiaoshaGoods miaoshaGoods);
}
