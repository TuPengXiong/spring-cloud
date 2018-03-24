package com.tx.dao.db;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.BeanFactoryDataSourceLookup;
import org.springframework.jdbc.datasource.lookup.DataSourceLookup;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import com.tx.dao.DynamicDataSource;
import com.tx.dao.mapper.image.ImageMapper;
import com.tx.dao.mapper.mq.MessageMapper;
import com.tx.dao.mapper.sso.UserMapper;
import com.tx.enums.dao.DBTypeEnum;

@Configuration
@MapperScan(basePackageClasses = { ImageMapper.class, MessageMapper.class, UserMapper.class })
@ConfigurationProperties(prefix="spring.datasource.mybatis")
public class DynamicDataSourceConf {

	private String typeAliasesPackage;

	private String mapperLocations;
	
	@Bean(name="dataSourceLookup")
	DataSourceLookup dataSourceLookup(BeanFactory beanFactory){
		DataSourceLookup dataSourceLookup = new BeanFactoryDataSourceLookup(beanFactory);
		return dataSourceLookup;
	}
	
	
	@Primary
	@Bean(name = "dataSource")
	public DynamicDataSource dataSource(
			@Qualifier(value = "dataSourceLookup") DataSourceLookup dataSourceLookup,
			@Qualifier(value = "imageDataSource") DataSource imageDataSource,
			@Qualifier(value = "mqDataSource") DataSource mqDataSource,
			@Qualifier(value = "ssoDataSource") DataSource ssoDataSource
	) {
		DynamicDataSource bean = new DynamicDataSource();
		Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
		targetDataSources.put(DBTypeEnum.imageDatasource.getValue(), imageDataSource);
		targetDataSources.put(DBTypeEnum.mqDatasource.getValue(), mqDataSource);
		targetDataSources.put(DBTypeEnum.ssoDatasource.getValue(), ssoDataSource);
		bean.setTargetDataSources(targetDataSources);
		bean.setDefaultTargetDataSource(imageDataSource);
		bean.setDataSourceLookup(dataSourceLookup);
		return bean;
	}

	@Primary
	@Bean(name = "sqlSessionFactory")
	SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
		MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
		mybatisSqlSessionFactoryBean.setDataSource(dataSource);
		mybatisSqlSessionFactoryBean.setTypeAliasesPackage(typeAliasesPackage);
		mybatisSqlSessionFactoryBean
				.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
		return mybatisSqlSessionFactoryBean.getObject();
	}

	DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("dataSource") DataSource dataSource){
		DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
		dataSourceTransactionManager.setDataSource(dataSource);
		return null;
	}
	@Primary
	@Bean(name = "globalConfiguration")
	GlobalConfiguration globalConfiguration(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
		GlobalConfiguration configuration = new GlobalConfiguration();
		configuration.setDbType("mysql");
		configuration.signGlobalConfig(sqlSessionFactory);
		return configuration;

	}
	    
	@Bean
	public ServletRegistrationBean druidServlet() {
		ServletRegistrationBean reg = new ServletRegistrationBean();
		reg.setServlet(new StatViewServlet());
		reg.addUrlMappings("/druid/*");
		reg.addInitParameter("loginUsername", "dataSource");
		reg.addInitParameter("loginPassword", "dataSource");
		return reg;
	}

	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new WebStatFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		filterRegistrationBean.addInitParameter("profileEnable", "true");
		filterRegistrationBean.addInitParameter("principalCookieName", "USER_COOKIE");
		filterRegistrationBean.addInitParameter("principalSessionName", "USER_SESSION");
		return filterRegistrationBean;
	}

	public String getTypeAliasesPackage() {
		return typeAliasesPackage;
	}

	public void setTypeAliasesPackage(String typeAliasesPackage) {
		this.typeAliasesPackage = typeAliasesPackage;
	}

	public String getMapperLocations() {
		return mapperLocations;
	}

	public void setMapperLocations(String mapperLocations) {
		this.mapperLocations = mapperLocations;
	}
}
