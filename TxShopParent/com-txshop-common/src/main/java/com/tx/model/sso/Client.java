package com.tx.model.sso;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * Created by tpx on 2017/10/24.
 */
@TableName(value = "client")
public class Client extends Model<Client>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Long id;
	
	@TableField("client_id")
	private String clientId;
	
	@TableField("resource_ids")
	private String resourceIds;
	
	@TableField("is_secret_required")
	private Boolean isSecretRequired;
	
	@TableField("client_secret")
	private String clientSecret;
	
	@TableField("is_scoped")
	private Boolean isScoped;
	
	@TableField("scopes")
	private String scopes;
	
	@TableField("authorized_grant_types")
	private String authorizedGrantTypes;
	
	@TableField("registered_redirect_uri")
	private String registeredRedirectUri;
	
	@TableField("auto_approve_scopes")
	private String autoApproveScopes;
	
	@TableField("authorities")
	private String authorities;
	
	@TableField("access_token")
	private String accessToken;
	
	@TableField("access_token_expire_time")
	private Date accessTokenExpireTime;
	
	@TableField("refresh_access_token")
	private String refreshAccessToken;
	
	@TableField("refresh_access_token_expire_time")
	private Date refreshAccessTokenExpireTime;
	
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

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getResourceIds() {
		return resourceIds;
	}

	public void setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds;
	}

	public Boolean isSecretRequired() {
		return isSecretRequired;
	}

	public void setSecretRequired(Boolean isSecretRequired) {
		this.isSecretRequired = isSecretRequired;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public Boolean isScoped() {
		return isScoped;
	}

	public void setScoped(Boolean isScoped) {
		this.isScoped = isScoped;
	}

	public String getScopes() {
		return scopes;
	}

	public void setScopes(String scopes) {
		this.scopes = scopes;
	}

	public String getAuthorizedGrantTypes() {
		return authorizedGrantTypes;
	}

	public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
		this.authorizedGrantTypes = authorizedGrantTypes;
	}

	public String getRegisteredRedirectUri() {
		return registeredRedirectUri;
	}

	public void setRegisteredRedirectUri(String registeredRedirectUri) {
		this.registeredRedirectUri = registeredRedirectUri;
	}

	public String getAutoApproveScopes() {
		return autoApproveScopes;
	}

	public void setAutoApproveScopes(String autoApproveScopes) {
		this.autoApproveScopes = autoApproveScopes;
	}

	public String getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshAccessToken() {
		return refreshAccessToken;
	}

	public void setRefreshAccessToken(String refreshAccessToken) {
		this.refreshAccessToken = refreshAccessToken;
	}


	public Boolean getIsSecretRequired() {
		return isSecretRequired;
	}

	public void setIsSecretRequired(Boolean isSecretRequired) {
		this.isSecretRequired = isSecretRequired;
	}

	public Boolean getIsScoped() {
		return isScoped;
	}

	public void setIsScoped(Boolean isScoped) {
		this.isScoped = isScoped;
	}

	public Date getAccessTokenExpireTime() {
		return accessTokenExpireTime;
	}

	public void setAccessTokenExpireTime(Date accessTokenExpireTime) {
		this.accessTokenExpireTime = accessTokenExpireTime;
	}

	public Date getRefreshAccessTokenExpireTime() {
		return refreshAccessTokenExpireTime;
	}

	public void setRefreshAccessTokenExpireTime(Date refreshAccessTokenExpireTime) {
		this.refreshAccessTokenExpireTime = refreshAccessTokenExpireTime;
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
