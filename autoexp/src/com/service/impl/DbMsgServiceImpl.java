package com.service.impl;

import com.mapper.DbMsgMapper;
import com.model.DbMsg;
import com.service.IDbMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 数据库配置信息表的Service实现类
 * @author dujian
 *
 */
@Service("dbMsgService")
public class DbMsgServiceImpl implements IDbMsgService {
	
	@Autowired
	private DbMsgMapper dbMsgMapper;
	
	@Override
	public List<DbMsg> getDbMsgList(Map<String, Object> map) {
		List<DbMsg> dbMsgList = dbMsgMapper.getDbMsgList(map);
		return dbMsgList;
	}

	@Override
	public void addDbMsg(DbMsg dbMsg) {
		dbMsgMapper.addDbMsg(dbMsg);
	}

	@Override
	public void updateDbMsg(DbMsg dbMsg) {
		dbMsgMapper.updateDbMsg(dbMsg);
	}

	@Override
	public void deleteDbMsg(String ids) {
		dbMsgMapper.deleteDbMsg(ids);
	}

	@Override
	public DbMsg findDbMsgById(String id) {
		DbMsg dbMsg = dbMsgMapper.findDbMsgById(id);
		return dbMsg;
	}
	
}
