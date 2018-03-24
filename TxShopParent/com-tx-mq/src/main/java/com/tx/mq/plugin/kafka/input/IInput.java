package com.tx.mq.plugin.kafka.input;

import org.springframework.messaging.SubscribableChannel;

public interface IInput {
	
	String INPUT = null;
	
	SubscribableChannel input();
}
