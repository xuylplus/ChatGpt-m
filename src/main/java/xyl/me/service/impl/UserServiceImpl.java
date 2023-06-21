package xyl.me.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyl.me.domain.User;
import xyl.me.mapper.UserMapper;
import xyl.me.service.UserService;
@Service
public class UserServiceImpl implements UserService {



    @Autowired
    private UserMapper userMapper;

    /**
     * 注册账号
     * @param user
     */
    public void registerUser(User user) {
        userMapper.insert(user);
    }
}
