package com.tx.center.moudle.mq;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tx.beans.dto.mq.kafka.KafkaMessageDTO;
import com.tx.interfaces.mq.kafka.ProducerOutputService;

@Service("mqProducerClientService")
@FeignClient("mq")
public interface MqProducerClientService extends ProducerOutputService{

	@RequestMapping(path = "/kafka/send", method = RequestMethod.POST)
	@Override
	public KafkaMessageDTO sendMessage(@RequestBody KafkaMessageDTO kafkaMessageDTO);
}
