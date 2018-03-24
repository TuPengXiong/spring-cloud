package com.tx.job.exception;

import org.apache.log4j.Logger;

import com.dangdang.ddframe.job.executor.handler.JobExceptionHandler;

/**
 * 
 * @ClassName:  MyJobException   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: tupengxiong tupengxiong@qq.com
 * @date:   2018年1月27日 上午10:28:17   
 * @version V1.0
 */
public class MyJobException implements JobExceptionHandler{

	private Logger logger = Logger.getLogger(this.getClass());
	@Override
	public void handleException(String jobName, Throwable cause) {
		logger.info("jobName:" + jobName +"error!!!");
		logger.error(String.format("Job '%s' exception occur in job processing", jobName), cause);
	}

}
