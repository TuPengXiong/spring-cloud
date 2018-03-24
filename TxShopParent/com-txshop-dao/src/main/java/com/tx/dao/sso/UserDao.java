package com.tx.dao.sso;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.tx.dao.mapper.sso.UserMapper;
import com.tx.model.sso.User;

@Repository("userDao")
public class UserDao implements UserMapper {

	@Autowired
	private UserMapper userMapper;

	@Override
	public Integer insert(User user) {
		return userMapper.insert(user);
	}

	@Override
	public Integer insertAllColumn(User entity) {
		return userMapper.insertAllColumn(entity);
	}

	@Override
	public Integer deleteById(Serializable id) {
		return userMapper.deleteById(id);
	}

	@Override
	public Integer deleteByMap(Map<String, Object> columnMap) {
		return userMapper.deleteByMap(columnMap);
	}

	@Override
	public Integer delete(Wrapper<User> wrapper) {
		return userMapper.delete(wrapper);
	}

	@Override
	public Integer deleteBatchIds(List<? extends Serializable> idList) {
		return userMapper.deleteBatchIds(idList);
	}

	@Override
	public Integer updateById(User entity) {
		return userMapper.updateById(entity);
	}

	@Override
	public Integer updateAllColumnById(User entity) {
		return userMapper.updateAllColumnById(entity);
	}

	@Override
	public Integer update(User entity, Wrapper<User> wrapper) {
		return userMapper.update(entity, wrapper);
	}

	@Override
	public User selectById(Serializable id) {
		return userMapper.selectById(id);
	}

	@Override
	public List<User> selectBatchIds(List<? extends Serializable> idList) {
		return userMapper.selectBatchIds(idList);
	}

	@Override
	public List<User> selectByMap(Map<String, Object> columnMap) {
		return userMapper.selectByMap(columnMap);
	}

	@Override
	public User selectOne(User entity) {
		return userMapper.selectOne(entity);
	}

	@Override
	public Integer selectCount(Wrapper<User> wrapper) {
		return userMapper.selectCount(wrapper);
	}

	@Override
	public List<User> selectList(Wrapper<User> wrapper) {
		return userMapper.selectList(wrapper);
	}

	@Override
	public List<Map<String, Object>> selectMaps(Wrapper<User> wrapper) {
		return userMapper.selectMaps(wrapper);
	}

	@Override
	public List<Object> selectObjs(Wrapper<User> wrapper) {
		return userMapper.selectObjs(wrapper);
	}

	@Override
	public List<User> selectPage(RowBounds rowBounds, Wrapper<User> wrapper) {
		return userMapper.selectPage(rowBounds, wrapper);
	}

	@Override
	public List<Map<String, Object>> selectMapsPage(RowBounds rowBounds, Wrapper<User> wrapper) {
		return userMapper.selectMapsPage(rowBounds, wrapper);
	}

}
