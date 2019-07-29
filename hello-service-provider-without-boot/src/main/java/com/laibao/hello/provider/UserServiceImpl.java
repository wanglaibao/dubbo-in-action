package com.laibao.hello.provider;

import com.laibao.hello.api.model.User;
import com.laibao.hello.api.service.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public User getUser() {
        return new User("byiwnd",28);
    }
}
