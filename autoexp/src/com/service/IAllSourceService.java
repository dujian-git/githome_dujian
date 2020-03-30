package com.service;

import java.util.List;
import java.util.Map;

import com.model.AllSource;

/**
 * All_source的Service接口
 * @author Administrator
 *
 */
public interface IAllSourceService {

	/**
	 * 条件查询记录
	 * @param map
	 * @return
	 */
	public List<AllSource> getAllSourceList(Map<String,Object> map);
	
	/**
	 * 分组的记录
	 * @param map
	 * @return
	 */
	public List<AllSource> getGroupList(Map<String,Object> map);
}
