package com.orange.dao.impl;

import com.orange.dao.IUserDao;
import com.orange.entity.UserInfoDO;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository("userDao")
public class UserDaoImpl extends BaseDAO<UserInfoDO> implements IUserDao {

    @Override
    public UserInfoDO getByName(String name) {
        Map<String, Object> params = new HashMap<>(3);
        params.put("name", name);
        return queryForObject(nameSpace + "getByName", params);
    }

    @Override
    public void saveUser(UserInfoDO userInfoDO) {
        this.save(userInfoDO);
    }
}
