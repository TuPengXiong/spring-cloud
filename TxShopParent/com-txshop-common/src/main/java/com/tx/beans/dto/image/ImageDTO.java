package com.tx.beans.dto.image;

import java.io.Serializable;

/**
 * Created by tpx on 2018/1/7.
 */
public class ImageDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2807693734744212311L;
	/**
     * 文件字节
     **/
    private byte[] file;
    /**
     * 插件名字
     **/
    private String pluginName;
    /**
     * 用户ID
     **/
    private Long userId;
    /**
     * 用户名称
     **/
    private String username;

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getPluginName() {
        return pluginName;
    }

    public void setPluginName(String pluginName) {
        this.pluginName = pluginName;
    }

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
}
