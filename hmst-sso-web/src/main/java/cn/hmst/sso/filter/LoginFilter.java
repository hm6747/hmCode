package cn.hmst.sso.filter;


import cn.hmst.param.RequestHolder;
import cn.hmst.pojo.SysUser;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hm on 2017/12/26.
 */
public class LoginFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        SysUser user = (SysUser)request.getSession().getAttribute("user");

        if(user == null){
            String path = "/pages/login.jsp";
            response.sendRedirect(path);
            return;
        }
        RequestHolder.add(user);
        RequestHolder.add(request);
        filterChain.doFilter(request,response);
        return;
    }

    @Override
    public void destroy() {

    }
}
