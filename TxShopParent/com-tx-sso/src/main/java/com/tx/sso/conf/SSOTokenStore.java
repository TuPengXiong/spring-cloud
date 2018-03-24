package com.tx.sso.conf;

import java.util.Collection;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tx.dao.sso.ClientDao;
import com.tx.model.sso.Client;

@Service
public class SSOTokenStore  extends InMemoryTokenStore implements TokenStore{

	@Autowired
	private ClientDao clientDao;
	
	private final Logger logger = Logger.getLogger(this.getClass()); 
	@Override
	public OAuth2Authentication readAuthentication(OAuth2AccessToken token) {
		OAuth2Authentication auth2Authentication = super.readAuthentication(token);
		logger.info("readAuthentication》》》》》》》》》》》》》》》》》》》》》》》》" + JSON.toJSONString(auth2Authentication));
		return auth2Authentication;
	}

	@Override
	public OAuth2Authentication readAuthentication(String token) {
		OAuth2Authentication auth2Authentication = super.readAuthentication(token);
		logger.info("readAuthentication》》》》》》》》》》》》》》》》》》》》》》》》" + JSON.toJSONString(auth2Authentication));
		return auth2Authentication;
	}

	/**
	 * 授权成功之后
	 * @Title: storeAccessToken 
	 * @Description: 授权成功之后
	 * @author tupx 
	 * @date 2018年1月25日 下午4:42:57 
	 * @version V1.0
	 */
	@Override
	public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
		super.storeAccessToken(token, authentication);
		
		/**
		 * {"additionalInformation":{},
		 * "expiration":1516912647751,
		 * "expired":false,"expiresIn":43199,
		 * "refreshToken":{"expiration":1519461447747,"value":"2067fa71-a242-47cd-b3b0-49b8b839473d"},
		 * "scope":["test","info"],
		 * "tokenType":"bearer",
		 * "value":"49d1fd4b-9469-4a82-8c80-07d58325ad23"}
		 */
		logger.info("storeAccessToken》》》》》》》》》》》》》》》》》》》》》》》》" + JSON.toJSONString(token));
		
		/**
		 * {"authenticated":true,"authorities":[{"authority":"LOGIN"}],"clientOnly":false,"credentials":"","name":"u1","oAuth2Request":{"approved":true,"authorities":[],"clientId":"test","extensions":{},"grantType":"authorization_code","redirectUri":"https://baidu.com","refresh":false,"requestParameters":{"response_type":"token","code":"vEyD2g","redirect_uri":"https://baidu.com","grant_type":"authorization_code","client_id":"test"},"resourceIds":[],"responseTypes":["token"],"scope":["test","info"]},"principal":{"accountNonExpired":true,"accountNonLocked":true,"authorities":[{"$ref":"$.authorities[0]"}],"credentialsNonExpired":true,"enabled":true,"username":"u1"},"userAuthentication":{"authenticated":true,"authorities":[{"$ref":"$.authorities[0]"}],"details":{"remoteAddress":"0:0:0:0:0:0:0:1","sessionId":"69CAC42CF4605C15BB964BAA69E3C2EC"},"name":"u1","principal":{"$ref":"$.principal"}}}
		 */
		
		logger.info("storeAccessToken》》》》》》》》》》》》》》》》》》》》》》》》" + JSON.toJSONString(authentication));
		
	/*	Client entity = new Client();
		entity.setClientId(authentication.getName());
		entity = clientDao.selectOne(entity);
		
		Client record = new Client();
		record.setId(entity.getId());
		record.setAccessToken(token.getValue());
		record.setAccessTokenExpireTime(token.getExpiration());
		clientDao.updateById(record);
		*/
		logger.info("storeAccessToken》》》》》》》》》》》》》》》》》》》》》》》》" + JSON.toJSONString(authentication));
	}

	@Override
	public OAuth2AccessToken readAccessToken(String tokenValue) {
		OAuth2AccessToken oAuth2AccessToken = super.readAccessToken(tokenValue);
		logger.info("readAccessToken》》》》》》》》》》》》》》》》》》》》》》》》" + JSON.toJSONString(oAuth2AccessToken));
		return oAuth2AccessToken;
	}

	@Override
	public void removeAccessToken(OAuth2AccessToken token) {
		super.removeAccessToken(token);
		logger.info("removeAccessToken》》》》》》》》》》》》》》》》》》》》》》》》" + JSON.toJSONString(token));
	}

	@Override
	public void storeRefreshToken(OAuth2RefreshToken refreshToken, OAuth2Authentication authentication) {
		super.storeRefreshToken(refreshToken, authentication);
		/**
		 * {"expiration":1519461447747,"value":"2067fa71-a242-47cd-b3b0-49b8b839473d"}
		 */
		JSONObject jsonObject = (JSONObject) JSON.parse(refreshToken.getValue());
		
		/*Client entity = new Client();
		entity.setClientId(authentication.getName());
		entity = clientDao.selectOne(entity);
		
		Client record = new Client();
		record.setId(entity.getId());
		record.setRefreshAccessToken(jsonObject.getString("value"));
		record.setRefreshAccessTokenExpireTime(jsonObject.getDate("expiration"));
		clientDao.updateById(record);*/
		logger.info("storeRefreshToken》》》》》》》》》》》》》》》》》》》》》》》》" + JSON.toJSONString(refreshToken));
		
	}

	@Override
	public OAuth2RefreshToken readRefreshToken(String tokenValue) {
		OAuth2RefreshToken reAuth2RefreshToken = super.readRefreshToken(tokenValue);
		logger.info("readRefreshToken》》》》》》》》》》》》》》》》》》》》》》》》" + JSON.toJSONString(reAuth2RefreshToken));
		return reAuth2RefreshToken;
	}

	@Override
	public OAuth2Authentication readAuthenticationForRefreshToken(OAuth2RefreshToken token) {
		OAuth2Authentication oAuth2Authentication = super.readAuthenticationForRefreshToken(token);
		logger.info("readAuthenticationForRefreshToken》》》》》》》》》》》》》》》》》》》》》》》》" + JSON.toJSONString(oAuth2Authentication));
		return oAuth2Authentication;
	}

	@Override
	public void removeRefreshToken(OAuth2RefreshToken token) {
		super.removeRefreshToken(token);
		logger.info("removeRefreshToken》》》》》》》》》》》》》》》》》》》》》》》》" + JSON.toJSONString(token));
	}

	@Override
	public void removeAccessTokenUsingRefreshToken(OAuth2RefreshToken refreshToken) {
		super.removeAccessTokenUsingRefreshToken(refreshToken);
		logger.info("removeAccessTokenUsingRefreshToken》》》》》》》》》》》》》》》》》》》》》》》》" + JSON.toJSONString(refreshToken));
		
	}

	@Override
	public OAuth2AccessToken getAccessToken(OAuth2Authentication authentication) {
		OAuth2AccessToken oAuth2AccessToken = super.getAccessToken(authentication);
		logger.info("getAccessToken》》》》》》》》》》》》》》》》》》》》》》》》" + JSON.toJSONString(oAuth2AccessToken));
		return oAuth2AccessToken;
	}

	@Override
	public Collection<OAuth2AccessToken> findTokensByClientIdAndUserName(String clientId, String userName) {
		Collection<OAuth2AccessToken> accessTokens = super.findTokensByClientIdAndUserName(clientId, userName);
		logger.info("findTokensByClientIdAndUserName》》》》》》》》》》》》》》》》》》》》》》》》" + JSON.toJSONString(clientId+":"+userName));
		logger.info("findTokensByClientIdAndUserName》》》》》》》》》》》》》》》》》》》》》》》》" + JSON.toJSONString(accessTokens));
		return accessTokens;
	}

	@Override
	public Collection<OAuth2AccessToken> findTokensByClientId(String clientId) {
		Collection<OAuth2AccessToken> accessTokens = super.findTokensByClientId(clientId);
		logger.info("findTokensByClientIdAndUserName》》》》》》》》》》》》》》》》》》》》》》》》" + JSON.toJSONString(clientId));
		logger.info("findTokensByClientIdAndUserName》》》》》》》》》》》》》》》》》》》》》》》》" + JSON.toJSONString(accessTokens));
		return accessTokens;
	}

	
	
	
}
