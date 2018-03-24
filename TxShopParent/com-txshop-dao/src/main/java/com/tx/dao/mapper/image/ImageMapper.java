package com.tx.dao.mapper.image;

import org.springframework.stereotype.Repository;

import com.tx.dao.annotation.DataSource;
import com.tx.dao.mapper.SuperMapper;
import com.tx.enums.dao.DBTypeEnum;
import com.tx.model.image.Image;
@Repository
@DataSource(value=DBTypeEnum.imageDatasource)
public interface ImageMapper extends SuperMapper<Image>{

}
