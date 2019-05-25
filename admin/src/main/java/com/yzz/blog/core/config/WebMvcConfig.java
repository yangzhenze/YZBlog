package com.yzz.blog.core.config;

import com.yzz.blog.business.enums.ConfigKeyEnum;
import com.yzz.blog.business.service.SysConfigService;
import com.yzz.blog.core.interceptor.RememberAuthenticationInterceptor;
import com.yzz.blog.framework.holder.SpringContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Map;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/7/15 15:03
 * @since 1.0
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private RememberAuthenticationInterceptor rememberAuthenticationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(rememberAuthenticationInterceptor)
                .excludePathPatterns("/passport/**", "/error/**", "/assets/**", "/getKaptcha/**", "favicon.ico")
                .addPathPatterns("/**");
    }

    /**
     * 添加静态资源文件，外部可以直接访问地址
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        SysConfigService configService = SpringContextHolder.getBean(SysConfigService.class);
        Map<String, Object> config = configService.getConfigs();
        registry.addResourceHandler(config.get(ConfigKeyEnum.LOCAL_FILE_URL.getKey())+"**").addResourceLocations("file:"+ config.get(ConfigKeyEnum.LOCAL_FILE_PATH.getKey()));
        //super.addResourceHandlers(registry);
    }
}
