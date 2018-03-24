package com.tx.center.moudle.image;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tx.beans.dto.image.ImageDTO;
import com.tx.beans.dto.image.ImageResultDTO;
import com.tx.interfaces.image.ImageService;

@Service("imageClientService")
@FeignClient("image")
public interface ImageClientService extends ImageService {

	/**
	 * 上传图片
	 **/
	@RequestMapping(path = "/upload", method = RequestMethod.POST)
	@Override
	ImageResultDTO upload(@RequestBody ImageDTO imageDTO);
}
