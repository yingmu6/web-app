package com.orange.service.impl;

import com.orange.dao.IUserDao;
import com.orange.entity.UserInfoDO;
import com.orange.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserDao userDao;

    public void saveUser(String name, Integer age) {
        //先查询，再添加
        UserInfoDO oldDO = userDao.getByName(name);
        if (oldDO != null) {
            return;
        }

        UserInfoDO userInfoDO = new UserInfoDO();
        userInfoDO.setName(name);
        userInfoDO.setAge(age);
        long curTime = System.currentTimeMillis() / 1000;
        userInfoDO.setGmtCreate(curTime);
        userInfoDO.setGmtModified(curTime);
        userDao.saveUser(userInfoDO);
    }
}
