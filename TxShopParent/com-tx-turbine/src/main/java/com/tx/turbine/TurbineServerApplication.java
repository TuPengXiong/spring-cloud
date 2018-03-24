package com.tx.turbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.web.bind.annotation.RequestMapping;

@EnableTurbine
@SpringBootApplication
@EnableHystrix
@EnableHystrixDashboard
public class TurbineServerApplication {

	public static void main(String[] args) {
		// 下面两行代码都可以用来启动
		SpringApplication.run(TurbineServerApplication.class, args);
		// new SpringApplicationBuilder(Application.class).web(true).run(args);
	}

	@RequestMapping("/")
	public String index() {
		return "Turbine" + System.currentTimeMillis();
	}
}