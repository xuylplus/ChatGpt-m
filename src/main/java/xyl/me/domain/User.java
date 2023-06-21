package xyl.me.domain;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

import static cn.hutool.core.date.DateUtil.now;

@Data
public class User {
    /**
     * 用户ID，主键自增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Value("now()")
    private Date createTime;






}
