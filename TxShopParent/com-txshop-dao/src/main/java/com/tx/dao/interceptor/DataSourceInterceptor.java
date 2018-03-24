package com.tx.dao.interceptor;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.tx.dao.DbContextHolder;
import com.tx.dao.annotation.DataSource;

@Aspect
@Component
@Order(0)
public class DataSourceInterceptor {

	Logger logger = Logger.getLogger(DataSourceInterceptor.class);

	@Pointcut(value = "execution(public * com.tx.dao.mapper.**.**.*(..))")
	private void datasourcePointcut() {
	}

	/**
	 * 拦截目标方法，获取由@DataSource指定的数据源标识，设置到线程存储中以便切换数据源
	 * 
	 * @param point
	 * @throws Exception
	 */
	@Before(value = "datasourcePointcut()")
	public void dataSourceBefore(JoinPoint point) throws Exception {
		Class<?> target = point.getTarget().getClass();
		MethodSignature signature = (MethodSignature) point.getSignature();
		logger.info("dataSourceBefore>>>>>>" + target.getName() + "#" + signature.getName() + "......");
		// 默认使用目标类型的注解，如果没有则使用其实现接口的注解
		for (Class<?> clazz : target.getInterfaces()) {
			resolveDataSource(clazz);
		}
		resolveDataSource(target);
		
		logger.info("dataSource->>>>>>" + DbContextHolder.getDbType());
	}

	@After(value = "datasourcePointcut()")
	public void dataSourceAfter(JoinPoint point) throws Exception {
		Class<?> target = point.getTarget().getClass();
		MethodSignature signature = (MethodSignature) point.getSignature();
		logger.debug("dataSourceAfter>>>>>>" + target.getName() + "#" + signature.getName() + "......");
	}

	/**
	 * 提取目标对象方法注解和类型注解中的数据源标识
	 * 
	 * @param clazz
	 * @param method
	 */
	private void resolveDataSource(Class<?> clazz) {
		try {
			// 默认使用类型注解
			if (clazz.isAnnotationPresent(DataSource.class)) {
				DataSource source = clazz.getAnnotation(DataSource.class);
				DbContextHolder.setDbType(source.value());
			}
		} catch (Exception e) {
			logger.error(clazz + ":" + e.getMessage(), e);
		}
	}

}
