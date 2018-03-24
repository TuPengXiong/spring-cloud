package com.tx.zuul.filter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class TokenFilter extends ZuulFilter {

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		System.out.println(String.format("%s TokenFilter request to %s", request.getMethod(),
				request.getRequestURL().toString()));
		//对该请求进行路由  
		ctx.setSendZuulResponse(true); 
		ctx.setResponseStatusCode(200);
		//ctx.setResponseBody("{\"success\":\"true\"}");// 输出最终结果
		return null;
	}

	/**
	 *是否执行
	 */
	@Override
	public boolean shouldFilter() {
		return true;
	}

	/**
	 * 优先级为0，数字越大，优先级越低
	 */
	@Override
	public int filterOrder() {
		return 1;
	}

	/**
	 * pre：可以在请求被路由之前调用 route：在路由请求时候被调用 post：在route和error过滤器之后被调用
	 * error：处理请求时发生错误时被调用
	 */
	@Override
	public String filterType() {
		return "pre";// 前置过滤器
	}

}
