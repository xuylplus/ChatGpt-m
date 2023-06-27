package xyl.me.contorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xyl.me.domain.User;
import xyl.me.service.UserService;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * 用户注册
     * @param user
     * @return
     */
    @PostMapping("/register")
    public Map registerUser(@RequestBody User user) {
        Map map = userService.registerUser(user);
        //输出map
        System.out.println(map);
        return map;
    }

}
