package com.tx.mq.plugin.kafka.consumer;

import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import com.alibaba.fastjson.JSON;
import com.tx.mq.plugin.kafka.input.UserInput;


@EnableBinding(UserInput.class)
@EnableAutoConfiguration
public class ConsumerInputService {

	private Logger logger = Logger.getLogger(this.getClass());
    @StreamListener(UserInput.INPUT)
    public void process(Message<?> message) {
    	logger.info("消费者接收到消息:"+JSON.toJSONString(message));
        Acknowledgment acknowledgment = message.getHeaders().get(KafkaHeaders.ACKNOWLEDGMENT, Acknowledgment.class);
        if (acknowledgment != null) {
        	logger.info("Acknowledgment provided");
            acknowledgment.acknowledge();
        }
    }
}