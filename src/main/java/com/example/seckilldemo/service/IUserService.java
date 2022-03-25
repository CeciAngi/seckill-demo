package com.example.seckilldemo.service;

import com.example.seckilldemo.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.seckilldemo.vo.LoginVo;
import com.example.seckilldemo.vo.RespBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author qhh
 * @since 2022-03-24
 */
public interface IUserService extends IService<User> {

    /**
     * 登录
     * @param loginVo
     * @return
     */
    RespBean doLogin(LoginVo loginVo);
}
