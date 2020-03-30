package com.mapper;

import java.util.List;
import java.util.Map;

import com.model.TableInfo;

/**
 * 对Oracle用户表的信息进行相关操作的接口
 * @author Administrator
 *
 */
public interface TableInfoMapper {

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
