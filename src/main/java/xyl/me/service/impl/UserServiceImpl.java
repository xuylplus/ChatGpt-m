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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;

    /**
     * 注册账号
     *
     * @param user
     */

    public Map registerUser(User user) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getUsername, user.getUsername());

        User service = userMapper.selectOne(lqw);
        if (service != null) {
            //throw new HAException(null, ApiCode.ERR_SYSTEM, "该用户已存在");
            //返回错误信息
            return MapResponseUtil.createMapResponse(ApiCode.ERR_SYSTEM, null, "该用户已存在");
        }

        // 对密码进行加密
        String newPassWord = getMD5(user.getPassword());
        user.setPassword(newPassWord);
        userMapper.insert(user);

        return MapResponseUtil.createMapResponse(ApiCode.SUCCESS, user.getUsername(),"注册成功");
    }


    /**
     * 加密方法
     *
     * @param str
     * @return
     * @throws NoSuchAlgorithmException
     */
    public String getMD5(String str)  {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] md5Bytes = md.digest(str.getBytes());
        return md5Bytes.toString();
    }

}
