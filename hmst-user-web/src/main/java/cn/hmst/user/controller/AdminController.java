package cn.hmst.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by hm on 2017/12/25.
 */
@Controller
@RequestMapping("/login")
public class AdminController {

    @RequestMapping("/login.page")
    public ModelAndView login() {
        return new ModelAndView("login");
    }
}