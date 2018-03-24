package com.tx.sso.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;

import com.tx.sso.service.DtClientDetailsService;

/**
 * Created by tpx on 2017/10/24.
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConf implements AuthorizationServerConfigurer {

	@Autowired
	private DtClientDetailsService dtClientDetailsService;

	@Autowired
	private SSOTokenStore ssoTokenStore;

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.realm("oauth-server");
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.withClientDetails(dtClientDetailsService);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.setClientDetailsService(dtClientDetailsService);
		endpoints.accessTokenConverter(new DefaultAccessTokenConverter());
		endpoints.tokenStore(ssoTokenStore);
	}
}
