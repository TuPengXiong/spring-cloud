package com.tx.dao.mq;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.tx.dao.mapper.mq.MessageMapper;
import com.tx.model.mq.Message;

@Repository("messageDao")
public class MessageDao implements MessageMapper {

	@Resource
	private MessageMapper messageMapper;

	@Override
	public Integer insert(Message image) {
		return messageMapper.insert(image);
	}

	@Override
	public Integer insertAllColumn(Message entity) {
		return messageMapper.insertAllColumn(entity);
	}

	@Override
	public Integer deleteById(Serializable id) {
		return messageMapper.deleteById(id);
	}

	@Override
	public Integer deleteByMap(Map<String, Object> columnMap) {
		return messageMapper.deleteByMap(columnMap);
	}

	@Override
	public Integer delete(Wrapper<Message> wrapper) {
		return messageMapper.delete(wrapper);
	}

	@Override
	public Integer deleteBatchIds(List<? extends Serializable> idList) {
		return messageMapper.deleteBatchIds(idList);
	}

	@Override
	public Integer updateById(Message entity) {
		return messageMapper.updateById(entity);
	}

	@Override
	public Integer updateAllColumnById(Message entity) {
		return messageMapper.updateAllColumnById(entity);
	}

	@Override
	public Integer update(Message entity, Wrapper<Message> wrapper) {
		return messageMapper.update(entity, wrapper);
	}

	@Override
	public Message selectById(Serializable id) {
		return messageMapper.selectById(id);
	}

	@Override
	public List<Message> selectBatchIds(List<? extends Serializable> idList) {
		return messageMapper.selectBatchIds(idList);
	}

	@Override
	public List<Message> selectByMap(Map<String, Object> columnMap) {
		return messageMapper.selectByMap(columnMap);
	}

	@Override
	public Message selectOne(Message entity) {
		return messageMapper.selectOne(entity);
	}

	@Override
	public Integer selectCount(Wrapper<Message> wrapper) {
		return messageMapper.selectCount(wrapper);
	}

	@Override
	public List<Message> selectList(Wrapper<Message> wrapper) {
		return messageMapper.selectList(wrapper);
	}

	@Override
	public List<Map<String, Object>> selectMaps(Wrapper<Message> wrapper) {
		return messageMapper.selectMaps(wrapper);
	}

	@Override
	public List<Object> selectObjs(Wrapper<Message> wrapper) {
		return messageMapper.selectObjs(wrapper);
	}

	@Override
	public List<Message> selectPage(RowBounds rowBounds, Wrapper<Message> wrapper) {
		return messageMapper.selectPage(rowBounds, wrapper);
	}

	@Override
	public List<Map<String, Object>> selectMapsPage(RowBounds rowBounds, Wrapper<Message> wrapper) {
		return messageMapper.selectMapsPage(rowBounds, wrapper);
	}

}
