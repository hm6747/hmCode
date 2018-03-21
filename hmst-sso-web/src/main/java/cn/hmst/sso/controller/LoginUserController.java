package cn.hmst.sso.controller;

import cn.hmst.common.util.MD5Util;
import cn.hmst.pojo.SysUser;
import cn.hmst.service.SysUserService;
import com.alibaba.dubbo.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
    private SysUserService sysUserService;

    @RequestMapping("/logout.page")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getSession().invalidate();
        String path = "login.jsp";
        response.sendRedirect(path);
    }

    @RequestMapping("/login.page")
    public void Login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        SysUser sysUser = sysUserService.findByKeyword(username);
        String errorMsg = "";
        String ret = request.getParameter("ret");
        if (StringUtils.isBlank(username)) {
            errorMsg = "用户名不可以为空";
        } else if (StringUtils.isBlank(password)) {
            errorMsg = "密码不可以为空";
        } else if (sysUser == null) {
            errorMsg = "查询不到指定的用户";
        } else if (!sysUser.getPassword().equals(MD5Util.encrypt(password))) {
            errorMsg = "用户名或密码错误";
        } else if (sysUser.getStatus() != 1) {
            errorMsg = "用户已被冻结，请联系管理员";
        } else {
            //login success
            request.getSession().setAttribute("user",sysUser);
            if(StringUtils.isNotEmpty(ret)){
                response.sendRedirect(ret);
                return;
            }else {
                response.sendRedirect("/admin/login.page");
                return;
            }
        }
        request.setAttribute("error",errorMsg);
        request.setAttribute("username",username);
        if(StringUtils.isNotEmpty(ret)){
            request.setAttribute("ret",ret);
        }
        String path = "login.jsp";
        request.getRequestDispatcher(path).forward(request, response);
    }
}