package com.tx.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient  //服务注册与发现
@EnableFeignClients //远程服务调用 http
@EnableHystrix
@EnableHystrixDashboard
@RestController
public class SearchServerApplication {

	public static void main(String[] args) {
		// 下面两行代码都可以用来启动
		SpringApplication.run(SearchServerApplication.class, args);
		// new SpringApplicationBuilder(Application.class).web(true).run(args);
	}
	
	@RequestMapping("/")
	public String index() {
		return "Search" + System.currentTimeMillis();
	}
}
