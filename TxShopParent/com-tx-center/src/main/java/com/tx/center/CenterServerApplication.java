package com.tx.center;

import javax.annotation.Resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tx.beans.dto.image.ImageDTO;
import com.tx.beans.dto.image.ImageResultDTO;
import com.tx.center.moudle.image.ImageClientService;

/**
 * Created by tpx on 2017/8/11.
 */
@SpringBootApplication
@EnableEurekaClient  //服务注册与发现
@EnableFeignClients //远程服务调用 http
@EnableHystrix
@EnableHystrixDashboard
@RestController
public class CenterServerApplication {

	@Resource
	private ImageClientService imageClientService;
	public static void main(String[] args) {
		// 下面两行代码都可以用来启动
		SpringApplication.run(CenterServerApplication.class, args);
		// new SpringApplicationBuilder(Application.class).web(true).run(args);
	}
	
	@RequestMapping("/")
	public ImageResultDTO index(){
		ImageDTO imageDTO = new ImageDTO();
		imageDTO.setPluginName("qiNiuImagePlugin");
		imageDTO.setFile("TUPENGXIONG".getBytes());
		return imageClientService.upload(imageDTO);
	}
}