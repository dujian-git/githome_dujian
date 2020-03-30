package com.mapper;

import java.util.List;
import java.util.Map;

import com.model.AllSource;

/**
 * 对Oracle的all_source表进行相关操作的接口
 * @author Administrator
 *
 */
public interface AllSourceMapper {

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
