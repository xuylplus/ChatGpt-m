package xyl.me.service;

import xyl.me.domain.User;

import java.util.Map;

public interface UserService {
    Map registerUser(User user);

    Map login(User user);
}
