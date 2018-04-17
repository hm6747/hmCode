package cn.hmst.sso.controller;

import cn.hmst.common.exception.ParamException;
import cn.hmst.common.pojo.JsonData;
import cn.hmst.common.util.Base64Util;
import cn.hmst.common.util.RSAUtil;
import cn.hmst.pojo.SysUser;
import cn.hmst.service.SysUserService;
import cn.hmst.sso.pojo.LoginUser;
import cn.hmst.sso.pojo.LoginUserDto;
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
import java.util.Map;


/**
 * Created by hm on 2017/12/25.
 */
@Controller
public class LoginUserController {
    private  static  final   String  PROVITE_KEY = "PROVITE_KEY";
    @Autowired
    private LoginService loginService;
    @Autowired
    private SysUserService sysUserService;
    @RequestMapping("/login.page")
    public String loginPage() {
        return "redirect:/pages/login.html";
    }

    @RequestMapping("/logout.page")
    public String logout(HttpServletRequest request) throws IOException, ServletException {
        request.getSession().removeAttribute("user");
        return "redirect:/pages/login.html";
    }

    @RequestMapping("/sign.json")
    @ResponseBody
    public JsonData Sign(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> keyMap = null;
        String publicKey = "";
        String privateKey = "";
        try {
            keyMap = RSAUtil.initKey();
             publicKey = RSAUtil.getPublicKey(keyMap);
             privateKey = RSAUtil.getPrivateKey(keyMap);
            request.getSession().setAttribute(PROVITE_KEY,privateKey);
        } catch (Exception e) {
            throw new ParamException("暂时无法登录");
        }
        return JsonData.success(publicKey);
    }

    @RequestMapping("/login.json")
    @ResponseBody
    public JsonData Login(String username, String password, Integer type,String key, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws IOException, ServletException {
        String privateKey = (String)request.getSession().getAttribute(PROVITE_KEY);
        try {
            password = new String(RSAUtil.decrypt(Base64Util.decryptBASE64(password),privateKey,false));
        } catch (Exception e) {
            e.printStackTrace();
        }
        LoginUser loginUser = loginService.LoginByUserNameAndPassword(username, password, type);
        SysUser user = sysUserService.findById(loginUser.getUserId());
        LoginUserDto loginUserDto = loginService.Login(user, loginUser);
        String id = request.getSession().getId();
        request.getSession().setAttribute("user", loginUserDto);
        return JsonData.success(loginUserDto);
    }

}
