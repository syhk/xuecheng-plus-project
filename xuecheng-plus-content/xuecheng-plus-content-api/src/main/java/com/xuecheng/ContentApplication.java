package com.xuecheng;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Mr.M
 * @version 1.0
 * @description 内容管理服务启动类
 * @date 2023/2/11 15:49
 */
@EnableSwagger2Doc // 生成接口文档
//http://localhost:63040/content/swagger-ui.html 访问地址查看接口信息
@SpringBootApplication
public class ContentApplication {
    public static void main(String[] args) {
        SpringApplication.run(ContentApplication.class, args);
    }
}
