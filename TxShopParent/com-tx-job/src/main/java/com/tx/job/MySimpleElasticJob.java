package com.tx.job;

import org.apache.log4j.Logger;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

/**
 * 简单实现，未经任何封装的类型。需实现SimpleJob接口。该接口仅提供单一方法用于覆盖，此方法将定时执行。
 * 与Quartz原生接口相似，但提供了弹性扩缩容和分片等功能。
 * @ClassName:  MySimpleElasticJob   
 * @Description:简单实现 
 * @author: tupengxiong tupengxiong@qq.com
 * @date:   2018年1月27日 上午10:28:08   
 * @version V1.0
 */
public class MySimpleElasticJob implements SimpleJob {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
    @Override
    public void execute(ShardingContext context) {
		logger.info("do something by sharding item"+ context.getShardingItem());
    }
}