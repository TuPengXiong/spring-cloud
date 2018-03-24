package com.tx.model.image;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * Created by tpx on 2018/1/7.
 */
@TableName("tb_image")
public class Image extends Model<Image> {

	 /** 
	 * <p>Title: </p> 
	 * <p>Description: </p> 
	 */
	private static final long serialVersionUID = 1167322072997488495L;

	/**
     * 主键ID
     **/
	 @TableId(value="id", type= IdType.AUTO)
    private Long id;

    /**
     * 创建时间
     */
	@TableField("create_time")
    private Date createTime;

    /**
     * 更新时间
     */
	@TableField("update_time")
    private Date updateTime;

    /**
     * 文件的MD5
     **/
	@TableField("md5")
    private String md5;
    /**
     * 用户ID
     **/
	@TableField("user_id")
    private Long userId;
    /**
     * 用户名称
     **/
	@TableField("username")
    private String username;
    /**
     * 文件地址
     **/
	@TableField("url")
    private String url;
    /**
     * 上传插件
     **/
	@TableField("plugin_name")
    private String pluginName;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
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

    public String getPluginName() {
        return pluginName;
    }

    public void setPluginName(String pluginName) {
        this.pluginName = pluginName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

	@Override
	protected Serializable pkVal() {
		return this.getId();
	}
}
