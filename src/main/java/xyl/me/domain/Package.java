package xyl.me.domain;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class Package {
    /**
     * 套餐ID，主键自增
     */
    private Integer packageId;

    /**
     * 套餐名称，非空
     */
    private String name;

    /**
     * 套餐价格，精确到小数点后两位
     */
    private BigDecimal price;



    /**
     * 总消息次数
     */
    private Integer messageCount;

}
