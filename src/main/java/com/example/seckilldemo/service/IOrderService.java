package com.example.seckilldemo.service;

import com.example.seckilldemo.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.seckilldemo.entity.User;
import com.example.seckilldemo.vo.GoodsVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author qhh
 * @since 2022-04-07
 */
public interface IOrderService extends IService<Order> {

    /**
     * 秒杀
     * @param user
     * @param goodsVo
     * @return
     */
    Order seckill(User user, GoodsVo goodsVo);
}
