package com.service.user.impl;



import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.UserMapper;
import com.model.User;
import com.service.user.IUserService;
@Service("userService")
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public void saveUser(User user) {
		userMapper.insert(user);
	}

	@Override
	public User queryByLoginMsg(User user) {
		return userMapper.queryByLoginMsg(user);
	}

	@Override
	public void updateByPrimaryKeySelective(User user) {
		userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public List<Map> queryAddressByLogin(Map<String, Object> params) {
		return userMapper.queryAddressByLogin(params);
	}

	@Override
	public void deleteAddressByPrimaryKey(Integer id) {
		userMapper.deleteAddressByPrimaryKey(id);
	}

	@Override
	public void insertUserAddress(Map<String, Object> params) {
		userMapper.insertUserAddress(params);
	}


	@Override
	public List<User> queryUserByCond(Map<String, Object> params) {
		return userMapper.queryUserByCond(params);
	}

	@Override
	public void deleteByPrimaryKey(Integer integer) {
		userMapper.deleteByPrimaryKey(integer);
	}

}
