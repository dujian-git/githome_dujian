package com.service;

import java.util.List;
import java.util.Map;

import com.model.TableInfo;

/**
 * TableInfo的Service接口
 * @author Administrator
 *
 */
public interface ITableInfoService {

	/**
	 * 条件获取携带表结构及注释信息的记录
	 * @param map
	 * @return
	 */
	public List<TableInfo> getTableInfoWithDdlAndComList(Map<String,Object> map);
	
	/**
	 * 条件获取携带表索引信息的记录
	 * @param map
	 * @return
	 */
	public List<TableInfo> getTableInfoWithIndexList(Map<String,Object> map);
}
