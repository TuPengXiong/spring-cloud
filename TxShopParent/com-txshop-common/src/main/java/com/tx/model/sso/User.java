package com.tx.model.sso;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * Created by tpx on 2018/01/23.
 */
@TableName("user")
public class User extends Model<User> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8619989390952884370L;

	@TableId(value = "id", type = IdType.AUTO)
	private Long id;
	
	@TableField("username")
	private String username;
	
	@TableField("password")
	private String password;
	
	@TableField("nickname")
	private String nickname;
	
	@TableField("sex")
	private Boolean sex;
	
	@TableField("birthday")
	private String birthday;
	
	@TableField("status")
	private Integer status;
	
	@TableField("phone")
	private String phone;
	
	@TableField("phone_time")
	private Date phoneTime;
	
	@TableField("email")
	private String email;
	
	@TableField("id_card")
	private String idCard;
	
	@TableField("id_card_time")
	private String idCardTime;
	
	@TableField("photo_url")
	private String photoUrl;
	
	@TableField("create_time")
	private Date createTime;
	
	@TableField("update_time")
	private Date updateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Boolean getSex() {
		return sex;
	}

	public void setSex(Boolean sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getPhoneTime() {
		return phoneTime;
	}

	public void setPhoneTime(Date phoneTime) {
		this.phoneTime = phoneTime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getIdCardTime() {
		return idCardTime;
	}

	public void setIdCardTime(String idCardTime) {
		this.idCardTime = idCardTime;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
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

	@Override
	protected Serializable pkVal() {
		return id;
	}

}
