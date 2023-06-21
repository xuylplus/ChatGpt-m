package xyl.me.domain;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    /**
     * 用户ID，主键自增
     */
    private Integer userId;

    /**
     * 用户名，非空
     */
    private String username;

    /**
     * 密码，非空
     */
    private String password;

    /**
     * 电子邮件地址，非空
     */
    private String email;

    /**
     * 当前使用的套餐ID
     */
    private Integer packageId;

    /**
     * 剩余消息次数，默认为0
     */
    private Integer remainingMessages;

    /**
     * 创建时间
     */
    private Date createTime;


}
