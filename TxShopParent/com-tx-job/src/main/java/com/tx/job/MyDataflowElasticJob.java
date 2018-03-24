package com.tx.job;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;

/**
 * 流式处理数据只有fetchData方法的返回值为null或集合长度为空时，作业才停止抓取，否则作业将一直运行下去； 
 * 非流式处理数据则只会在每次作业执行过程中执行一次fetchData方法和processData方法，随即完成本次作业。
 * 
 * @ClassName:  MyDataflowElasticJob   
 * @Description:流式处理数据   
 * @author: tupengxiong tupengxiong@qq.com
 * @date:   2018年1月27日 上午10:28:03   
 * @version V1.0
 */
public class MyDataflowElasticJob implements DataflowJob<String>{
	private Logger logger = Logger.getLogger(this.getClass());
	@Override
	public List<String> fetchData(ShardingContext shardingContext) {
		List<String> list = new ArrayList<String>(shardingContext.getShardingItem());
		for(int i=0;i<=shardingContext.getShardingItem();i++){
			list.add("shardingContext|" + shardingContext.getShardingItem() + ":" +i);
		}
		
		return list;
	}

	@Override
	public void processData(ShardingContext shardingContext, List<String> data) {
		logger.info("do something by sharding item"+ shardingContext.getShardingItem());
		logger.info("--------->"+ JSON.toJSONString(data));
	}

}
