package com.tx.interfaces.mq.kafka;

import com.tx.beans.dto.mq.kafka.KafkaMessageDTO;

public interface ProducerOutputService {

	/**
	 * 发送kafka消息
	 * @param kafkaMessageDTO
	 * @return
	 */
	KafkaMessageDTO sendMessage(KafkaMessageDTO kafkaMessageDTO);
}
