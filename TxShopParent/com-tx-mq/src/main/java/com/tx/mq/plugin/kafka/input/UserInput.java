package com.tx.mq.plugin.kafka.input;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface UserInput extends IInput {

	String INPUT = "userInput";

	@Input(UserInput.INPUT)
	SubscribableChannel input();

}