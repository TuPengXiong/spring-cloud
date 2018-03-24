package com.tx.enums;

/**
 * 
 * @author xiongjun
 *
 */
public enum ServiceResultCode {

	NULL_PARAM(100,"参数为空"),
	ERROR_PARAM(101,"参数错误"),
	SUCCESS(200,"成功"),
	NULL_DATA(400,"没有数据"), 
	AUTH_ERROR(401,"验证错误"),
	DB_ERROR(500,"数据库异常"), 
	OTHER_ERROR(900,"其他错误"),
	DEFAULT_ERROR(0,"默认错误"),
	DEFAULT_SUCCESS(1,"默认成功");

    private Integer code;

    private String desc;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private ServiceResultCode(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
