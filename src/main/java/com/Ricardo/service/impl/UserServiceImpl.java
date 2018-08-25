package com.Ricardo.service.impl;

import com.Ricardo.dao.IUserDao;
import com.Ricardo.model.User;
import com.Ricardo.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserDao userDao;

    public User selectUser(long userId) {
        return this.userDao.selectUser(userId);
    }

}