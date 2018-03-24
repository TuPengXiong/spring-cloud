package com.tx.beans.dto.mq.kafka;

import java.io.Serializable;
import java.util.Date;

public class KafkaMessageDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -284378599970547463L;

	/**
	 * 用户ID
	 */
	private Long userId;
	
	/**
	 * 用户名
	 */
	private String username;
	
	/**
	 * 对应binder的outPut
	 */
	private String outPut;
	
	/**
	 * 消息内容
	 */
	private String body;
	
	/**
	 * 发送是否成功
	 */
	private boolean success;
	
	private Date createTime;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOutPut() {
		return outPut;
	}

	public void setOutPut(String outPut) {
		this.outPut = outPut;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public boolean getSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	
}
