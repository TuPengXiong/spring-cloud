package com.tx.job.lite;

import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.dataflow.DataflowJobConfiguration;
import com.dangdang.ddframe.job.config.script.ScriptJobConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.executor.handler.JobProperties.JobPropertiesEnum;
import com.dangdang.ddframe.job.executor.handler.impl.DefaultExecutorServiceHandler;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.api.strategy.impl.AverageAllocationJobShardingStrategy;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.tx.job.MyDataflowElasticJob;
import com.tx.job.MySimpleElasticJob;
import com.tx.job.exception.MyJobException;

/**
 * 
 * @ClassName:  LiteJobDemo   
 * @Description:LiteJobDemo   
 * @author: tupengxiong tupengxiong@qq.com
 * @date:   2018年1月27日 上午10:22:52   
 * @version V1.0
 */
public class LiteJobDemo {

	private static final String serverLists = "localhost:2181";
	private static final Class<?> exceptionClass = MyJobException.class;
	private static final String scriptPath = LiteJobDemo.class.getClassLoader().getResource("test.bat").getPath();
	
	public static void main(String[] args) {
		// simple
		new JobScheduler(createRegistryCenter(serverLists, "elastic-job-demo-simple"), createJobConfiguration(
				"demoSimpleJob", "0/15 * * * * ?", 3, MySimpleElasticJob.class.getCanonicalName())).init();
		// dataflow
		new JobScheduler(createRegistryCenter(serverLists, "elastic-job-demo-dataflow"),
				createDataflowJobConfiguration("demoDataflowJob", "0/15 * * * * ?", 3,
						MyDataflowElasticJob.class.getCanonicalName(), false)).init();
		// script
		new JobScheduler(createRegistryCenter(serverLists, "elastic-job-demo-script"),
				createScriptJobConfiguration("demoScriptJob", "0/15 * * * * ?", 3,scriptPath)).init();
	}

	private static CoordinatorRegistryCenter createRegistryCenter(String serverLists,String namespace) {
		CoordinatorRegistryCenter regCenter = new ZookeeperRegistryCenter(new ZookeeperConfiguration(serverLists, namespace));
		regCenter.init();
		return regCenter;
	}
	
	
	/**
	 * 
	 * @Title: createJobConfiguration   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param jobName
	 * @param: @param cron
	 * @param: @param shardingTotalCount
	 * @param: @param jobClass
	 * @param: @return      
	 * @return: LiteJobConfiguration      
	 * @throws
	 */
	private static LiteJobConfiguration createJobConfiguration(String jobName,String cron,int shardingTotalCount,String jobClass) {
		// 创建作业配置
		// ...
		// 定义作业核心配置
		JobCoreConfiguration simpleCoreConfig = JobCoreConfiguration.newBuilder(jobName,cron,shardingTotalCount)
				.failover(false) //设置是否开启失效转移. 默认false
				.shardingItemParameters("") //设置分片序列号和个性化参数对照表. 默认""  0=a,1=b,2=c
				.jobParameter("") //设置作业自定义参数. 默认""  可以配置多个相同的作业, 但是用不同的参数作为不同的调度实例.
				.misfire(true) //设置是否开启misfire. 默认true
				.description("") //设置作业描述信息. 默认""
				.jobProperties(JobPropertiesEnum.JOB_EXCEPTION_HANDLER.getKey(), exceptionClass.getCanonicalName())  //作业异常处理器.   默认DefaultJobExceptionHandler.class.getCanonicalName()
				.jobProperties(JobPropertiesEnum.EXECUTOR_SERVICE_HANDLER.getKey(), DefaultExecutorServiceHandler.class.getCanonicalName()) //线程池服务处理器.
				.build();
		
		// 定义SIMPLE类型配置
		SimpleJobConfiguration simpleJobConfig = new SimpleJobConfiguration(simpleCoreConfig,jobClass);
		
		// 定义Lite作业根配置
		LiteJobConfiguration simpleJobRootConfig = LiteJobConfiguration.newBuilder(simpleJobConfig)
				.overwrite(true) //设置本地配置是否可覆盖注册中心配置.  默认false
				.disabled(false) //设置作业是否启动时禁止. 默认false
				.reconcileIntervalMinutes(10) //设置修复作业服务器不一致状态服务执行间隔分钟数. 默认10
				.monitorPort(-1) //设置作业辅助监控端口. 默认 -1
				.monitorExecution(true) //设置监控作业执行时状态. 默认true
				.maxTimeDiffSeconds(-1) //最大容忍的本机与注册中心的时间误差秒数 默认 -1
				.jobShardingStrategyClass(AverageAllocationJobShardingStrategy.class.getCanonicalName()) //设置作业分片策略实现类全路径 默认 AverageAllocationJobShardingStrategy
				.build();
		return simpleJobRootConfig;
	}
	
	/**
	 * 
	 * @Title: createDataflowJobConfiguration   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param jobName
	 * @param: @param cron
	 * @param: @param shardingTotalCount
	 * @param: @param jobClass
	 * @param: @param streamingProcess
	 * @param: @return      
	 * @return: LiteJobConfiguration      
	 * @throws
	 */
	private static LiteJobConfiguration createDataflowJobConfiguration(String jobName,String cron,int shardingTotalCount,String jobClass,boolean streamingProcess) {
		// 创建作业配置
		// ...
		// 定义作业核心配置
		JobCoreConfiguration simpleCoreConfig = JobCoreConfiguration.newBuilder(jobName, cron, shardingTotalCount)
				.failover(false) //设置是否开启失效转移. 默认false
				.shardingItemParameters("") //设置分片序列号和个性化参数对照表. 默认""  0=a,1=b,2=c
				.jobParameter("") //设置作业自定义参数. 默认""  可以配置多个相同的作业, 但是用不同的参数作为不同的调度实例.
				.misfire(true) //设置是否开启misfire. 默认 true
				.description("") //设置作业描述信息. 默认""
				.jobProperties(JobPropertiesEnum.JOB_EXCEPTION_HANDLER.getKey(), exceptionClass.getCanonicalName())  //作业异常处理器.   默认DefaultJobExceptionHandler.class.getCanonicalName()
				.jobProperties(JobPropertiesEnum.EXECUTOR_SERVICE_HANDLER.getKey(), DefaultExecutorServiceHandler.class.getCanonicalName()) //线程池服务处理器.
				.build();
		
		// 定义Dataflow类型配置  
		DataflowJobConfiguration dataflowJobConfiguration = new DataflowJobConfiguration(simpleCoreConfig , jobClass, streamingProcess);
		
		// 定义Lite作业根配置
		LiteJobConfiguration dataflowJobRootConfig = LiteJobConfiguration.newBuilder(dataflowJobConfiguration)
				.overwrite(true) //设置本地配置是否可覆盖注册中心配置.  默认false
				.disabled(false) //设置作业是否启动时禁止. 默认false
				.reconcileIntervalMinutes(10) //设置修复作业服务器不一致状态服务执行间隔分钟数. 默认10
				.monitorPort(-1) //设置作业辅助监控端口. 默认 -1
				.monitorExecution(true) //设置监控作业执行时状态. 默认true
				.maxTimeDiffSeconds(-1) //最大容忍的本机与注册中心的时间误差秒数 默认 -1
				.jobShardingStrategyClass(AverageAllocationJobShardingStrategy.class.getCanonicalName()) //设置作业分片策略实现类全路径 默认 AverageAllocationJobShardingStrategy
				.build();
		return dataflowJobRootConfig;
	}
	
	
	/**
	 * 
	 * @Title: createScriptJobConfiguration   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param jobName
	 * @param: @param cron
	 * @param: @param shardingTotalCount
	 * @param: @param scriptPath
	 * @param: @return      
	 * @return: LiteJobConfiguration      
	 * @throws
	 */
	private static LiteJobConfiguration createScriptJobConfiguration(String jobName,String cron,int shardingTotalCount,String scriptPath) {
		// 创建作业配置
		// ...
		// 定义作业核心配置
		JobCoreConfiguration simpleCoreConfig = JobCoreConfiguration.newBuilder(jobName, cron, shardingTotalCount)
				.failover(false) //设置是否开启失效转移. 默认false
				.shardingItemParameters("") //设置分片序列号和个性化参数对照表. 默认""  0=a,1=b,2=c
				.jobParameter("") //设置作业自定义参数. 默认""  可以配置多个相同的作业, 但是用不同的参数作为不同的调度实例.
				.misfire(true) //设置是否开启misfire. 默认 true
				.description("") //设置作业描述信息. 默认""
				.jobProperties(JobPropertiesEnum.JOB_EXCEPTION_HANDLER.getKey(), exceptionClass.getCanonicalName())  //作业异常处理器.   默认DefaultJobExceptionHandler.class.getCanonicalName()
				.jobProperties(JobPropertiesEnum.EXECUTOR_SERVICE_HANDLER.getKey(), DefaultExecutorServiceHandler.class.getCanonicalName()) //线程池服务处理器.
				.build();
		
		// 定义Script类型配置  
		ScriptJobConfiguration scriptJobConfiguration = new ScriptJobConfiguration(simpleCoreConfig , scriptPath);
		
		// 定义Lite作业根配置
		LiteJobConfiguration dataflowJobRootConfig = LiteJobConfiguration.newBuilder(scriptJobConfiguration)
				.overwrite(true) //设置本地配置是否可覆盖注册中心配置.  默认false
				.disabled(false) //设置作业是否启动时禁止. 默认false
				.reconcileIntervalMinutes(10) //设置修复作业服务器不一致状态服务执行间隔分钟数. 默认10
				.monitorPort(-1) //设置作业辅助监控端口. 默认 -1
				.monitorExecution(true) //设置监控作业执行时状态. 默认true
				.maxTimeDiffSeconds(-1) //最大容忍的本机与注册中心的时间误差秒数 默认 -1
				.jobShardingStrategyClass(AverageAllocationJobShardingStrategy.class.getCanonicalName()) //设置作业分片策略实现类全路径 默认 AverageAllocationJobShardingStrategy
				.build();
		return dataflowJobRootConfig;
	}
}