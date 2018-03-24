package com.tx.sso.service;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tx.dao.sso.UserDao;

/**
 * Created by tpx on 2017/7/12.
 */
@Service("dtUserDetailsService")
public class DtUserDetailsService implements UserDetailsService {


    private  final Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        logger.info("username=========:"+username);
        com.tx.model.sso.User entity = new com.tx.model.sso.User();
        entity.setUsername(username);
        entity = userDao.selectOne(entity);
        if(entity == null){
            throw new UsernameNotFoundException("用户名或密码错误！");
        }
        Set<SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("LOGIN"));
        User user = new User(entity.getUsername(),entity.getPassword(),authorities);
        return  user;
    }
}
