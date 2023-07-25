package xyl.me.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyl.me.domain.User;
import xyl.me.mapper.UserMapper;
import xyl.me.service.UserService;
import xyl.me.util.ApiCode;
import xyl.me.util.HAException;
import xyl.me.util.MapResponseUtil;
import xyl.me.util.PasswordUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import static cn.hutool.core.date.DateTime.now;

@Service
public class UserServiceImpl implements UserService {

    private static final int MAX_INCORRECT_PASSWORD_ATTEMPTS = 3; // 允许的最大错误密码次数


    @Autowired
    private UserMapper userMapper;

    /**
     * 注册账号
     *
     * @param user
     */

    public Map registerUser(User user) {
        User service = selectUser(user);
        if (service != null) {
            //throw new HAException(null, ApiCode.ERR_SYSTEM, "该用户已存在");
            //返回错误信息
            return MapResponseUtil.createMapResponse(ApiCode.ERR_USER_EXIST_ALREADY, null, "该用户已存在");
        }

        // 对密码进行加密
        String newPassWord = PasswordUtils.encryptPassword(user.getPassword());
        user.setPassword(newPassWord);
        user.setCreateTime(now());
        userMapper.insert(user);

        return MapResponseUtil.createMapResponse(ApiCode.SUCCESS, user.getUsername(),"注册成功");
    }


    /**
     * 用户登录
     *
     * @param user
     */
    @Override
    public Map login(User user) {

        //先判断是否有当前用户
        User service = selectUser(user);
        if (service == null) {
            //返回错误信息
            return MapResponseUtil.createMapResponse(ApiCode.ERR_USER_NOT_FOUND, null, "该用户不存在");
        }

        //判断当前用户是否被禁用
        if (service.getAccountLocked() == 0) {
            //返回错误信息
            return MapResponseUtil.createMapResponse(ApiCode.ERR_USER_LOGIN_ALREADY, null, "该用户已被禁用");
        }



        //判断密码是否正确
        if (!PasswordUtils.verifyPassword(user.getPassword(), service.getPassword())) {
            //密码错误，错误次数加1
            int incorrectPasswordAttempts = service.getIncorrectPasswordAttempts() + 1;
            //更新错误次数
            service.setIncorrectPasswordAttempts(incorrectPasswordAttempts);
            userMapper.updateById(service);
            //判断错误次数是否超过最大次数
            if (incorrectPasswordAttempts >= MAX_INCORRECT_PASSWORD_ATTEMPTS) {
                service.setAccountLocked(0);
                userMapper.updateById(service);
                //返回错误信息
                return MapResponseUtil.createMapResponse(ApiCode.ERR_ADMIN_LOGIN_ERR, null, "密码错误次数过多，账号已被锁定");
            }

            //返回错误信息
            return MapResponseUtil.createMapResponse(ApiCode.ERR_ADMIN_LOGIN_ERR, null, "用户名或密码错误");
        }

        //返回成功信息
        return MapResponseUtil.createMapResponse(ApiCode.SUCCESS, service.getUsername(), "登录成功");

    }





    public User selectUser(User user) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getUsername, user.getUsername());
        User service = userMapper.selectOne(lqw);
        return service;
    }





}
