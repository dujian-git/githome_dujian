package com.mapper;

import java.util.List;
import java.util.Map;

import com.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User queryByLoginMsg(User user);

	List<Map> queryAddressByLogin(Map<String, Object> params);

	void deleteAddressByPrimaryKey(Integer id);

	void insertUserAddress(Map<String, Object> params);
	
	List<User> queryUserByCond(Map<String, Object> params);

}