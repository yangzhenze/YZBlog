package com.yzz.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

/**
 * 程序启动类
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/24 14:37
 * @since 1.0
 */
//// 移除 @SpringBootApplication and @ComponentScan, 用 @EnableAutoConfiguration 来替代
//@SpringBootApplication
@ServletComponentScan
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.yzz.blog"})
public class BlogAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogAdminApplication.class, args);
    }
}
