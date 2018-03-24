package com.tx.image.plugin;

import com.tx.beans.dto.image.ImageDTO;
import com.tx.beans.dto.image.ImageResultDTO;

/**
 * Created by tpx on 2018/1/7.
 */
public abstract class ImagePlugin {

    /**
     * 上传图片
     **/
    public abstract ImageResultDTO upload(ImageDTO imageDTO);

    /**
     * 获取插件名称
     **/
    public abstract String getPluginName();
    
    /**
     * 是否启用
     **/
    public abstract  boolean enabled();
}
