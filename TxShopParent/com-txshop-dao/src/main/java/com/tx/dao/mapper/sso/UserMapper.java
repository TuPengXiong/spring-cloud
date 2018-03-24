package com.tx.dao.mapper.sso;

import org.springframework.stereotype.Repository;

import com.tx.dao.annotation.DataSource;
import com.tx.dao.mapper.SuperMapper;
import com.tx.enums.dao.DBTypeEnum;
import com.tx.model.sso.User;
@Repository
@DataSource(value=DBTypeEnum.ssoDatasource)
public interface UserMapper extends SuperMapper<User>{

}
