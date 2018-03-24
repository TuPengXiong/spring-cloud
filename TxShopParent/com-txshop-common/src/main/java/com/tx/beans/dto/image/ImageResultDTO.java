package com.tx.beans.dto.image;

import java.io.Serializable;

/**
 * Created by tpx on 2018/1/7.
 */
public class ImageResultDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7434893219638729368L;

	/**
     * 是否成功
     **/
    private boolean success;

    /**
     * 成功或失败的信息
     **/
    private String msg;

    /***文件上传的ID*/
    private Long id;

    /**
     * 图片的链接地址
     **/
    private String url;


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
