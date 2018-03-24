package com.tx.dao.sso;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.tx.dao.mapper.sso.ClientMapper;
import com.tx.model.sso.Client;

@Repository("clientDao")
public class ClientDao implements ClientMapper {

	@Autowired
	private ClientMapper clientMapper;

	@Override
	public Integer insert(Client client) {
		return clientMapper.insert(client);
	}

	@Override
	public Integer insertAllColumn(Client entity) {
		return clientMapper.insertAllColumn(entity);
	}

	@Override
	public Integer deleteById(Serializable id) {
		return clientMapper.deleteById(id);
	}

	@Override
	public Integer deleteByMap(Map<String, Object> columnMap) {
		return clientMapper.deleteByMap(columnMap);
	}

	@Override
	public Integer delete(Wrapper<Client> wrapper) {
		return clientMapper.delete(wrapper);
	}

	@Override
	public Integer deleteBatchIds(List<? extends Serializable> idList) {
		return clientMapper.deleteBatchIds(idList);
	}

	@Override
	public Integer updateById(Client entity) {
		return clientMapper.updateById(entity);
	}

	@Override
	public Integer updateAllColumnById(Client entity) {
		return clientMapper.updateAllColumnById(entity);
	}

	@Override
	public Integer update(Client entity, Wrapper<Client> wrapper) {
		return clientMapper.update(entity, wrapper);
	}

	@Override
	public Client selectById(Serializable id) {
		return clientMapper.selectById(id);
	}

	@Override
	public List<Client> selectBatchIds(List<? extends Serializable> idList) {
		return clientMapper.selectBatchIds(idList);
	}

	@Override
	public List<Client> selectByMap(Map<String, Object> columnMap) {
		return clientMapper.selectByMap(columnMap);
	}

	@Override
	public Client selectOne(Client entity) {
		return clientMapper.selectOne(entity);
	}

	@Override
	public Integer selectCount(Wrapper<Client> wrapper) {
		return clientMapper.selectCount(wrapper);
	}

	@Override
	public List<Client> selectList(Wrapper<Client> wrapper) {
		return clientMapper.selectList(wrapper);
	}

	@Override
	public List<Map<String, Object>> selectMaps(Wrapper<Client> wrapper) {
		return clientMapper.selectMaps(wrapper);
	}

	@Override
	public List<Object> selectObjs(Wrapper<Client> wrapper) {
		return clientMapper.selectObjs(wrapper);
	}

	@Override
	public List<Client> selectPage(RowBounds rowBounds, Wrapper<Client> wrapper) {
		return clientMapper.selectPage(rowBounds, wrapper);
	}

	@Override
	public List<Map<String, Object>> selectMapsPage(RowBounds rowBounds, Wrapper<Client> wrapper) {
		return clientMapper.selectMapsPage(rowBounds, wrapper);
	}

}
