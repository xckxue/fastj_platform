package com.upotv.mcs.core.config;

import com.upotv.mcs.core.interceptor.McsInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by wow on 2017/6/26.
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Autowired
    McsInterceptor mcsInterceptor;

    //配置静态资源访问
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
    }

    //页面跳转
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/main").setViewName("main");
        super.addViewControllers(registry);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(mcsInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/js", "/css", "/images")
                .excludePathPatterns("/login","/logout","/getMenu","/dict/getDict")
                .excludePathPatterns("/interface/**")
                .excludePathPatterns("/");
        super.addInterceptors(registry);
    }
}
