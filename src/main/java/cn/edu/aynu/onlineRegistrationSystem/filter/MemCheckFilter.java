package cn.edu.aynu.onlineRegistrationSystem.filter;

import cn.edu.aynu.onlineRegistrationSystem.entity.memInfo;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 检测用户二级页面的登录态
 */
public class MemCheckFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;
        HttpSession session=request.getSession();
        memInfo user= (memInfo) session.getAttribute("user");
        if(user==null){//管理员也会被过滤，因为后台用户数据库没有对应的账号信息，查询相应比赛会出错
            response.sendRedirect("/ORS/login");//TODO 暂定跳转
        }else{
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }
}
