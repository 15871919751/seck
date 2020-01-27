package org.ct.seckill.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.ct.seckill.dao.GoodsDao;
import org.ct.seckill.service.GoosService;
import org.ct.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author K
 * @Date 2020/1/26 22:05
 * @Version 1.0
 */
@Slf4j
@Service
public class GoodsServiceImpl implements GoosService {

    @Autowired
    GoodsDao goodsDao;

    @Override
    public List<GoodsVo> getListGoodsVo() {
        return goodsDao.getListGoodsVo();
    }
}
