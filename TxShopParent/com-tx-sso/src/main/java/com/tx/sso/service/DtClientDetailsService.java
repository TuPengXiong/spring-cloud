package com.tx.sso.service;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import com.tx.dao.sso.ClientDao;
import com.tx.model.sso.Client;

/**
 * Created by tpx on 2018/1/23.
 */
@Service("dtClientDetailsService")
public class DtClientDetailsService implements ClientDetailsService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ClientDao clientDao;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        logger.info("clientId=========:"+clientId);
        Client client = new Client();
        client.setClientId(clientId);
        client = clientDao.selectOne(client);
        if(null == client){
            throw new ClientRegistrationException("not exist!!!");
        }
        BaseClientDetails clientDetails = new BaseClientDetails(client.getClientId(), client.getResourceIds(), client.getScopes(), client.getAuthorizedGrantTypes(), client.getAuthorities(), client.getRegisteredRedirectUri());
        if(null != client.getAutoApproveScopes()){
        	Set<String> setAutoApproveScopes = new HashSet<String>();
        	for(String autoApproveScope :client.getAutoApproveScopes().split(",")){
        		setAutoApproveScopes.add(autoApproveScope);
        	}
        	clientDetails.setAutoApproveScopes(setAutoApproveScopes);
        }
        return clientDetails;
    }
}
