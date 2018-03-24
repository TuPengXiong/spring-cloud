package com.tx.image.plugin.qiniu;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.tx.beans.dto.image.ImageDTO;
import com.tx.beans.dto.image.ImageResultDTO;
import com.tx.dao.image.ImageDao;
import com.tx.image.plugin.ImagePlugin;
import com.tx.model.image.Image;

/**
 * Created by tpx on 2018/1/7.
 */
@Component("qiNiuImagePlugin")
public class QiNiuImagePlugin extends ImagePlugin {

	private Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private ImageDao imageDao;
	@Value("${qiniu.accessKey}")
	private String accessKey;
	@Value("${qiniu.secretKey}")
	private String secretKey;
	@Value("${qiniu.defaultBucket}")
	private String defaultBucket;
	@Value("${qiniu.imagePrefixUrl}")
	private String imagePrefixUrl;
	@Value("${qiniu.enable}")
	private boolean enable;
	// 构造一个带指定Zone对象的配置类 华东
	private Configuration cfg = new Configuration(Zone.zone0());
	private UploadManager uploadManager = new UploadManager(cfg);

	@Override
	public ImageResultDTO upload(ImageDTO imageDTO) {
		ImageResultDTO dto = new ImageResultDTO();
		dto.setSuccess(false);
		dto.setMsg("upload error");
		// 使用文件的md5sum作为七牛云上的文件名，防止重复上传
		String md5FileName = DigestUtils.md5Hex(imageDTO.getFile());
		Image image = exist(md5FileName);
		if (null == image) {
			// ...生成上传凭证，然后准备上传
			Auth auth = Auth.create(accessKey, secretKey);
			String token = auth.uploadToken(defaultBucket);
			Response response = null;
			Map<String, Object> map = null;
			try {
				response = uploadManager.put(imageDTO.getFile(), md5FileName, token);
				map = response.jsonToMap().map();
			} catch (QiniuException e) {
				logger.error("upload error!!!", e);
				return dto;
			}
			image = new Image();
			image.setPluginName(getPluginName());
			image.setUserId(imageDTO.getUserId());
			image.setCreateTime(new Date());
			image.setUpdateTime(new Date());
			image.setUsername(imageDTO.getUsername());
			image.setUrl(imagePrefixUrl + (String) map.get("key"));
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

	@SuppressWarnings("unused")
	private boolean yunExist(String md5FileName) {
		Auth auth = Auth.create(accessKey, secretKey);
		BucketManager bucketManager = new BucketManager(auth, cfg);
		try {
			bucketManager.stat(defaultBucket, md5FileName);
		} catch (QiniuException e) {
			logger.error("yunExist error!!!", e);
			return false;
		}
		return true;
	}

	@Override
	public boolean enabled() {
		return enable;
	}
}
