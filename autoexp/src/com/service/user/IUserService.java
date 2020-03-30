package com.service.user;

import java.util.List;
import java.util.Map;

import com.model.User;

public interface IUserService {
	
	public void saveUser(User user);

	public User queryByLoginMsg(User user);

	public void updateByPrimaryKeySelective(User user);

	public List<Map> queryAddressByLogin(Map<String, Object> params);

	public void deleteAddressByPrimaryKey(Integer integer);

	public void insertUserAddress(Map<String, Object> params);

	public List<User> queryUserByCond(Map<String, Object> params);

	public void deleteByPrimaryKey(Integer integer);

}
