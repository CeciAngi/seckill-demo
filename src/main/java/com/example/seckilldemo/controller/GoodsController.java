package com.example.seckilldemo.controller;

import com.example.seckilldemo.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author ：qhh
 * @date ：Created in 2022/3/28 16:06
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    /**
     * 跳转到商品列表页
     * @param session
     * @param model
     * @param ticket
     * @return
     */
    @RequestMapping("/toList")
    public String toList(HttpSession session, Model model,
                         @CookieValue("userTicket") String ticket){
        if (StringUtils.isEmpty(ticket)){
            return "login";
        }
        User user = (User) session.getAttribute(ticket);
        if (user == null){
            return "login";
        }
        model.addAttribute("user", user);
        return "goodsList";
    }

}
