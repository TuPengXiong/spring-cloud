package com.tx.mq.plugin.kafka.output;

import org.springframework.messaging.MessageChannel;

public interface IOutput {

	String OUTPUT = null;
	
	MessageChannel output();
}
