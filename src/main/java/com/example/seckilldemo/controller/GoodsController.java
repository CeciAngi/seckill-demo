package com.example.seckilldemo.controller;

import com.example.seckilldemo.entity.User;
import com.example.seckilldemo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author ：qhh
 * @date ：Created in 2022/3/28 16:06
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private IUserService userService;

    /**
     * 跳转到商品列表页
     * @param
     * @param model
     * @param
     * @return
     */
    @RequestMapping("/toList")
    public String toList(Model model, User user){
//        if (StringUtils.isEmpty(ticket)){
//            return "login";
//        }
////        User user = (User) session.getAttribute(ticket);
//        User user = userService.getUserByCookie(ticket, request, response);
//        if (user == null){
//            return "login";
//        }
        model.addAttribute("user", user);
        return "goodsList";
    }

}
