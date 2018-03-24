package com.tx.enums.dao;

public enum DBTypeEnum {
	mqDatasource("mqDatasource"), 
	imageDatasource("imageDatasource"),
	ssoDatasource("ssoDatasource"),
	;
	private String value;

	DBTypeEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
