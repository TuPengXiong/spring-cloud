import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.alibaba.fastjson.JSON;
import com.tx.beans.dto.image.ImageDTO;
import com.tx.beans.dto.image.ImageResultDTO;
import com.tx.image.ImageServerApplication;
import com.tx.interfaces.image.ImageService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = ImageServerApplication.class)
@SpringBootTest
public class SpringWebJunitTest{

	private Logger logger = Logger.getLogger(this.getClass());

	@Resource
	private ImageService imageService;

	@Test
	public void testqiNiuImagePlugin() {
		ImageDTO imageDTO = new ImageDTO();
		imageDTO.setPluginName("qiNiuImagePlugin");
		imageDTO.setUserId(1L);
		imageDTO.setUsername("1");
		imageDTO.setFile(new String("123").getBytes());
		ImageResultDTO imageResultDTO = imageService.upload(imageDTO);
		logger.info(JSON.toJSONString(imageResultDTO));
	}
	
	@Test
	public void testftpImagePlugin() {
		ImageDTO imageDTO = new ImageDTO();
		imageDTO.setPluginName("ftpImagePlugin");
		imageDTO.setUserId(1L);
		imageDTO.setUsername("1");
		imageDTO.setFile(new String("123").getBytes());
		ImageResultDTO imageResultDTO = imageService.upload(imageDTO);
		logger.info(JSON.toJSONString(imageResultDTO));
	}
}
