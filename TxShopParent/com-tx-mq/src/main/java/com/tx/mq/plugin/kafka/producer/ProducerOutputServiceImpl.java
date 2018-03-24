package com.tx.mq.plugin.kafka.producer;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tx.beans.dto.mq.kafka.KafkaMessageDTO;
import com.tx.interfaces.mq.kafka.ProducerOutputService;
import com.tx.mq.plugin.kafka.output.IOutput;
import com.tx.mq.plugin.kafka.output.UserOutput;

@EnableBinding(UserOutput.class)
@Service("producerOutputService")
@RestController
public class ProducerOutputServiceImpl implements ProducerOutputService,InitializingBean {

	private Logger logger = Logger.getLogger(this.getClass());

	/**
	 * 类名与类映射map
	 */
	private Map<String, String> outPutsMapping = new HashMap<String, String>();

	/**
	 * 所有代理的IOutput
	 */
	@Autowired
	private Map<String, IOutput> outPutsMap = new HashMap<String, IOutput>();

	@Override
	public void afterPropertiesSet() throws Exception {
		for (String outPut : outPutsMapping.keySet()) {
			outPutsMapping.put(outPut.substring(outPut.lastIndexOf(".") + 1, outPut.length()), outPut);
		}
	}

	@RequestMapping(path = "/kafka/send", method = RequestMethod.POST)
	@Override
	public KafkaMessageDTO sendMessage(@RequestBody KafkaMessageDTO kafkaMessageDTO) {
		if(null == kafkaMessageDTO){
			return kafkaMessageDTO; 
		}
		kafkaMessageDTO.setSuccess(false);
		if(null == kafkaMessageDTO.getBody()){
			logger.error("null == kafkaMessageDTO.getBody()" + kafkaMessageDTO.getUserId());
			return kafkaMessageDTO; 
		}
		
		if(null == kafkaMessageDTO.getOutPut()){
			logger.error("null == kafkaMessageDTO.getOutPut()" + kafkaMessageDTO.getUserId());
			return kafkaMessageDTO; 
		}
		
		try {
			String className = outPutsMapping.get(kafkaMessageDTO.getOutPut());
			if(null == className){
				logger.error("null == outPutsMapping.getOutPut()" + kafkaMessageDTO.getUserId());
			}
			IOutput ioutPut = outPutsMap.get(className);
			kafkaMessageDTO.setSuccess(ioutPut.output().send(MessageBuilder.withPayload(kafkaMessageDTO.getBody()).build()));
		} catch (Exception e) {
			logger.error("ProducerOutputServiceImpl sendMessage error!!!"+ kafkaMessageDTO.getUserId(), e);
		}
		return kafkaMessageDTO; 
	}
}
