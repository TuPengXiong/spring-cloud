package com.tx.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Created by tpx on 2017/8/11.
 */
@SpringBootApplication
@EnableEurekaClient // 服务注册与发现
@EnableZuulProxy  //使用@EnableZuulProxy注解激活zuul
public class ZuulApplication {

	public static void main(String[] args) {
		// 下面两行代码都可以用来启动
		SpringApplication.run(ZuulApplication.class, args);
		// new SpringApplicationBuilder(Application.class).web(true).run(args);
	}
}