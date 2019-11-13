package cn.edu.aynu.onlineRegistrationSystem.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Author: tianhan
 * Create Data: 2019/11/13 0013
 * 管理员登录态检测filter
 */
public class AdminCheckFilter implements Filter {
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
        String path = request.getRequestURI().substring(request.getContextPath().length()).replaceAll("[/]+$", "");
        if("/admin/login".equals(path)||"admin".equals(session.getAttribute("type"))){
            filterChain.doFilter(servletRequest, servletResponse);
        }else{
            response.sendRedirect("/ORS/login");//TODO 暂定跳转
        }
    }
}
