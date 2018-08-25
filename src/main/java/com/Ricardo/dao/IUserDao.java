package com.Ricardo.dao;

import com.Ricardo.model.User;

public interface IUserDao {
    User selectUser(long id);
}
