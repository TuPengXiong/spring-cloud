package com.tx.sso;

import java.security.Principal;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient  //服务注册与发现
@EnableFeignClients //远程服务调用 http
@EnableHystrix
@EnableHystrixDashboard
@PropertySource(value={"classpath:jdbc.properties"})
@ComponentScan(value={"com.tx"})
@RestController
public class SSOServerApplication {

	public static void main(String[] args) {
		// 下面两行代码都可以用来启动
		SpringApplication.run(SSOServerApplication.class, args);
		// new SpringApplicationBuilder(Application.class).web(true).run(args);
	}
	
	@RequestMapping("/")
	public String index() {
		return "SSO" + System.currentTimeMillis();
	}
	
	@PreAuthorize("hasRole('LOGIN')")
	@RequestMapping("/api/preAuthorize")
	public String preAuthorize() {
		return "/api/preAuthorize" + System.currentTimeMillis();
	}
	
	@PreAuthorize("hasRole('LOGIN')")
	@RequestMapping("/api/preAuthorizeRole")
	public String preAuthorizeRole() {
		return "/api/preAuthorizeRole" + System.currentTimeMillis();
	}
	
	
	@Secured({"LOGIN"})
	@RequestMapping("/api/secured")
	public String secured() {
		return "/api/secured" + System.currentTimeMillis();
	}
	
	@RolesAllowed({"LOGIN"})
	@RequestMapping("/api/solesAllowed")
	public String solesAllowed() {
		return "/api/solesAllowed" + System.currentTimeMillis();
	}
	@PermitAll
	@RequestMapping("/permitAll")
	public String permitAll() {
		return "/permitAll" + System.currentTimeMillis();
	}
	
	@DenyAll
	@RequestMapping("/denyAll")
	public String denyAll() {
		return "/denyAll" + System.currentTimeMillis();
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping("/isAuthenticated")
	public Principal isAuthenticated(Principal principal) {
		return principal;
	}
	
	@PreAuthorize("hasAuthority('LOGIN')")
	@RequestMapping("/api/hasAuthority")
	public String hasAuthority() {
		return "/api/hasAuthority" + System.currentTimeMillis();
	}
	
}