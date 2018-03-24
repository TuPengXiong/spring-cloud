package com.tx.dao.image;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.tx.dao.image.ImageDao;
import com.tx.dao.mapper.image.ImageMapper;
import com.tx.model.image.Image;

@Repository("imageDao")
public class ImageDao implements ImageMapper {

	@Resource
	private ImageMapper imageMapper;

	@Override
	public Integer insert(Image image) {
		return imageMapper.insert(image);
	}

	@Override
	public Integer insertAllColumn(Image entity) {
		return imageMapper.insertAllColumn(entity);
	}

	@Override
	public Integer deleteById(Serializable id) {
		return imageMapper.deleteById(id);
	}

	@Override
	public Integer deleteByMap(Map<String, Object> columnMap) {
		return imageMapper.deleteByMap(columnMap);
	}

	@Override
	public Integer delete(Wrapper<Image> wrapper) {
		return imageMapper.delete(wrapper);
	}

	@Override
	public Integer deleteBatchIds(List<? extends Serializable> idList) {
		return imageMapper.deleteBatchIds(idList);
	}

	@Override
	public Integer updateById(Image entity) {
		return imageMapper.updateById(entity);
	}

	@Override
	public Integer updateAllColumnById(Image entity) {
		return imageMapper.updateAllColumnById(entity);
	}

	@Override
	public Integer update(Image entity, Wrapper<Image> wrapper) {
		return imageMapper.update(entity, wrapper);
	}

	@Override
	public Image selectById(Serializable id) {
		return imageMapper.selectById(id);
	}

	@Override
	public List<Image> selectBatchIds(List<? extends Serializable> idList) {
		return imageMapper.selectBatchIds(idList);
	}

	@Override
	public List<Image> selectByMap(Map<String, Object> columnMap) {
		return imageMapper.selectByMap(columnMap);
	}

	@Override
	public Image selectOne(Image entity) {
		return imageMapper.selectOne(entity);
	}

	@Override
	public Integer selectCount(Wrapper<Image> wrapper) {
		return imageMapper.selectCount(wrapper);
	}

	@Override
	public List<Image> selectList(Wrapper<Image> wrapper) {
		return imageMapper.selectList(wrapper);
	}

	@Override
	public List<Map<String, Object>> selectMaps(Wrapper<Image> wrapper) {
		return imageMapper.selectMaps(wrapper);
	}

	@Override
	public List<Object> selectObjs(Wrapper<Image> wrapper) {
		return imageMapper.selectObjs(wrapper);
	}

	@Override
	public List<Image> selectPage(RowBounds rowBounds, Wrapper<Image> wrapper) {
		return imageMapper.selectPage(rowBounds, wrapper);
	}

	@Override
	public List<Map<String, Object>> selectMapsPage(RowBounds rowBounds, Wrapper<Image> wrapper) {
		return imageMapper.selectMapsPage(rowBounds, wrapper);
	}

}
