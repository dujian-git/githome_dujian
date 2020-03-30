package com.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.AllSourceMapper;
import com.model.AllSource;
import com.service.IAllSourceService;

/**
 * All_source的Service实现类
 * 
 * @author Administrator
 * 
 */
@Service("allSourceService")
public class AllSourceServiceImpl implements IAllSourceService {

	@Autowired
	private AllSourceMapper allSourceMapper;

	@Override
	public List<AllSource> getAllSourceList(Map<String, Object> map) {
		return allSourceMapper.getAllSourceList(map);
	}

	@Override
	public List<AllSource> getGroupList(Map<String, Object> map) {
		return allSourceMapper.getGroupList(map);
	}
}
