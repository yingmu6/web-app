package com.orange.dao;

import com.orange.entity.UserInfoDO;

public interface IUserDao {

    UserInfoDO getByName(String name);

    void saveUser(UserInfoDO userInfoDO);

}
