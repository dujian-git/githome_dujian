package com.mapper;

import com.model.DbMsg;

import java.util.List;
import java.util.Map;

/**
 * 数据库配置信息表的mapper接口
 * @author dujian
 *
 */
public interface DbMsgMapper {
	
	public List<DbMsg> getDbMsgList(Map<String, Object> map);
	
    public void addDbMsg(DbMsg dbMsg);

	public void updateDbMsg(DbMsg dbMsg);
	
	public void deleteDbMsg(String ids);
	
	public DbMsg findDbMsgById(String id);
}
