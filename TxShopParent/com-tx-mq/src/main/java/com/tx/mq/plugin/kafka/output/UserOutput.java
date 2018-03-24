package com.tx.mq.plugin.kafka.output;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface UserOutput extends IOutput{
	String OUTPUT = "userOutput";

	@Output(UserOutput.OUTPUT)
	MessageChannel output();
}

