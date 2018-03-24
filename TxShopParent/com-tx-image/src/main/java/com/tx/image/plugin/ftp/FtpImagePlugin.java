package com.tx.image.plugin.ftp;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.tx.beans.dto.image.ImageDTO;
import com.tx.beans.dto.image.ImageResultDTO;
import com.tx.dao.image.ImageDao;
import com.tx.image.plugin.ImagePlugin;
import com.tx.image.utils.FtpUtils;
import com.tx.model.image.Image;

/**
 * Created by tpx on 2018/1/7.
 */
@Component("ftpImagePlugin")
public class FtpImagePlugin extends ImagePlugin {

	private Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private ImageDao imageDao;
	@Resource
	private FtpUtils ftpUtils;
	@Value("${ftp.upload.path}")
	private String imageUploadPath;
	@Value("${ftp.imagePrefixUrl}")
	private String imagePrefixUrl;
	@Value("${ftp.enable}")
	private boolean enable;
	
	@Override
	public ImageResultDTO upload(ImageDTO imageDTO) {
		ImageResultDTO dto = new ImageResultDTO();
		dto.setSuccess(false);
		dto.setMsg("upload error");
		// 使用文件的md5sum作为文件名，防止重复上传
		String md5FileName = DigestUtils.md5Hex(imageDTO.getFile());
		Image image = exist(md5FileName);
		if (null == image) {
			try {
				ftpUtils.upload(imageUploadPath+md5FileName, imageDTO.getFile());
			} catch (Exception e) {
				logger.error("upload error!!!", e);
				return dto;
			}
			image = new Image();
			image.setPluginName(getPluginName());
			image.setUserId(imageDTO.getUserId());
			image.setCreateTime(new Date());
			image.setUpdateTime(new Date());
			image.setUsername(imageDTO.getUsername());
			image.setUrl(imagePrefixUrl + md5FileName);
			image.setMd5(md5FileName);
			imageDao.insert(image);
		}
		dto.setId(image.getId());
		dto.setUrl(image.getUrl());
		dto.setSuccess(true);
		dto.setMsg("success");
		return dto;
	}

	@Override
	public String getPluginName() {
		return getClass().getDeclaredAnnotation(Component.class).value();
	}

	private Image exist(String md5FileName) {
		Image image = new Image();
		image.setMd5(md5FileName);
		return imageDao.selectOne(image);
	}

	@Override
	public boolean enabled() {
		return enable;
	}
}
