package com.tx.image.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.tx.beans.dto.image.ImageDTO;
import com.tx.beans.dto.image.ImageResultDTO;
import com.tx.image.plugin.ImagePlugin;
import com.tx.interfaces.image.ImageService;

/**
 * Created by tpx on 2018/1/7.
 */
@Service("imageService")
@RestController
public class ImageServiceImpl implements ImageService {

	private Logger logger = Logger.getLogger(this.getClass());
    @Resource
    private Map<String, ImagePlugin> plugins = new HashMap<String, ImagePlugin>();
    /**
     * 上传图片
     **/
    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    @Override
    public ImageResultDTO upload(@RequestBody ImageDTO imageDTO) {
        ImageResultDTO dto = new ImageResultDTO();
        if (null == imageDTO) {
            dto.setSuccess(false);
            dto.setMsg("imageDTO is null");
            return dto;
        }
        logger.info(JSON.toJSONString(imageDTO));
        ImagePlugin plugin = plugins.get(imageDTO.getPluginName());
        if (null == plugin) {
            dto.setSuccess(false);
            dto.setMsg("plugin not exist");
            return dto;
        }
        if (!plugin.enabled()) {
            dto.setSuccess(false);
            dto.setMsg("plugin not enabled");
            return dto;
        }
        if (imageDTO.getFile() == null || imageDTO.getFile().length == 0) {
            dto.setSuccess(false);
            dto.setMsg("file is empty");
            return dto;
        }
        try {
            return plugin.upload(imageDTO);
        } catch (Exception ex) {
        	logger.error("upload file error!!!",ex);
            dto.setSuccess(false);
            dto.setMsg("upload error");
            return dto;
        }
    }
}
