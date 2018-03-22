package cn.hmst.sso.controller;

import cn.hmst.common.pojo.JsonData;
import cn.hmst.pojo.SysUser;
import cn.hmst.sso.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by hm on 2017/12/25.
 */
@Controller
public class LoginUserController {
    @Autowired
    private LoginService loginService;

    @RequestMapping("/login.page")
    public String loginPage() {
        return  "redirect:/pages/login.html";
    }

    @RequestMapping("/logout.page")
    public String logout(HttpServletRequest request) throws IOException, ServletException {
        request.getSession().removeAttribute("tokenId");
        return  "redirect:/pages/login.html";
    }

    @RequestMapping("/login.json")
    @ResponseBody
    public JsonData Login(String username, String password, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws IOException, ServletException {
        SysUser sysUser = loginService.LoginByUserNameAndPassword(username, password);
        request.getSession().setAttribute("tokenId", sysUser);
        return JsonData.success();
    }

}
