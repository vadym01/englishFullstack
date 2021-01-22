package com.englishapp.demoen.service.userService;

import com.englishapp.demoen.entity.User;
import com.englishapp.demoen.entity.UserPageInfo;

import java.util.List;

public interface UserService {

    User saveUser(User user);
    String getUsername();
    User getUserObj();
}
