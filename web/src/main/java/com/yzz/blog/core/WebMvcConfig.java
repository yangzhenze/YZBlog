package com.yzz.blog.core;

import com.yzz.blog.business.enums.ConfigKeyEnum;
import com.yzz.blog.business.service.SysConfigService;
import com.yzz.blog.core.intercepter.BraumIntercepter;
import com.yzz.blog.framework.holder.SpringContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Map;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    BraumIntercepter braumIntercepter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(braumIntercepter)
                .excludePathPatterns("/assets/**", "/error/**", "favicon.ico", "/css/**", "/js/**", "/img/**")
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
