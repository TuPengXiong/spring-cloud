package com.tx.image;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tpx on 2017/8/11.
 */
@SpringBootApplication
@EnableEurekaClient  //服务注册与发现
@EnableFeignClients //远程服务调用 http
@EnableHystrix
@EnableHystrixDashboard
@RestController
@PropertySource(value={"classpath:jdbc.properties"})
@ComponentScan(value={"com.tx"})
public class ImageServerApplication {

    public static void main(String[] args) {
        //下面两行代码都可以用来启动
        SpringApplication.run(ImageServerApplication.class, args);
        //new SpringApplicationBuilder(Application.class).web(true).run(args);
    }
    @RequestMapping("/")
	public String index(){
		return "image"+System.currentTimeMillis();
	}
}