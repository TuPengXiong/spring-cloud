package com.tx.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by tpx on 2017/8/11.
 */
@SpringBootApplication
@EnableEurekaServer  //Eureka 启动一个服务注册中心提供给其他应用进行对话
public class EurekaServerApplication {
    public static void main(String[] args) {
        //下面两行代码都可以用来启动
        SpringApplication.run(EurekaServerApplication.class, args);
        //new SpringApplicationBuilder(Application.class).web(true).run(args);
    }
}