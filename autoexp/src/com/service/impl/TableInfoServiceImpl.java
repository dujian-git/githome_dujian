package com.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.TableInfoMapper;
import com.model.TableInfo;
import com.service.ITableInfoService;

/**
 * TableInfoService的Service实现类
 * @author Administrator
 *
 */
@Service("tableInfoService")
public class TableInfoServiceImpl implements ITableInfoService{
	
	@Autowired
	private TableInfoMapper tableInfoMapper;

	@Override
	public List<TableInfo> getTableInfoWithDdlAndComList(Map<String, Object> map) {
		List<TableInfo> tableInfoWithDdlAndComList=tableInfoMapper.getTableInfoWithDdlAndComList(map);
		return tableInfoWithDdlAndComList;
	}

	@Override
	public List<TableInfo> getTableInfoWithIndexList(Map<String, Object> map) {
		List<TableInfo> tableInfoWithIndex=tableInfoMapper.getTableInfoWithIndexList(map);
		return tableInfoWithIndex;
	}
}
