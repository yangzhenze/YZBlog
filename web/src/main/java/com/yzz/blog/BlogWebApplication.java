package com.yzz.blog;

import me.zhyd.braum.spring.boot.annotation.EnableBraumConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 程序启动类
 */
@ServletComponentScan
@EnableTransactionManagement
@EnableBraumConfiguration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.yzz.blog"})
public class BlogWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogWebApplication.class, args);
    }

}
