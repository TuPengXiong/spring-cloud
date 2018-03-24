package com.tx.dao.mapper.mq;

import org.springframework.stereotype.Repository;

import com.tx.dao.annotation.DataSource;
import com.tx.dao.mapper.SuperMapper;
import com.tx.enums.dao.DBTypeEnum;
import com.tx.model.mq.Message;
@Repository
@DataSource(value=DBTypeEnum.mqDatasource)
public interface MessageMapper extends SuperMapper<Message>{

}
