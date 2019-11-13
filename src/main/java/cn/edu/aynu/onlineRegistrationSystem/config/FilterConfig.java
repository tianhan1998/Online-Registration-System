package cn.edu.aynu.onlineRegistrationSystem.config;

import cn.edu.aynu.onlineRegistrationSystem.filter.AdminCheckFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2019/11/13 0013
 */
@Configuration//TODO 配置filter注册类
public class FilterConfig {
    /**
     * 检测管理员登录态filter
     * @return
     */
    @Bean
    FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new AdminCheckFilter());
        filterRegistrationBean.addUrlPatterns("/admin/*");
        return filterRegistrationBean;
    }
}
