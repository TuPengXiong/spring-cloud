package com.tx.interfaces.image;

import com.tx.beans.dto.image.ImageDTO;
import com.tx.beans.dto.image.ImageResultDTO;

/**
 * Created by tpx on 2018/1/7.
 */
public interface ImageService {

    /**
     * 上传图片
     **/
    ImageResultDTO upload(ImageDTO imageDTO);
}
