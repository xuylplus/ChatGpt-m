package xyl.me.util;

/**
 * @author wangfeng@evchar.cn
 *
 *         错误码： A01：用户；A02：商户；A03：订单 A00：未知及一般
 */
public class ApiCode {
	// ----------------------------通用错误-------------------------
	public static final String SUCCESS = "A00000";// 成功
	public static final String ERR_WRONG_PARAMS = "A00001";// 参数错误
	public static final String ERR_SYSTEM = "A00002";// 系统错误
	public static final String ERR_LOGIC = "A00003";// 业务错误
	public static final String NO_TOKEN = "A00004";// 未登录或token已过期

	// ----------------------------用户错误码------------------------
	public static final String ERR_USER_NOT_FOUND = "A01404"; // 用户不存在
	public static final String ERR_USER_EXIST_ALREADY = "A01405"; // 用户已注册
	public static final String ERR_USER_LOGIN_ALREADY = "A01406"; // 禁止短时间内再次登录


	// ----------------------------支付错误码------------------------
	public static final String ERR_LESS_BALANCE = "A04401"; // 余额不足
	public static final String ERR_PRICE = "A04403";// 不匹配
	public static final String ERR_ODR_NOT_FOUND = "A04404"; // 订单不存在
	public static final String ERR_ODR_HAS_PAY = "A04405"; // 订单已支付
	public static final String ERR_CHANNEL = "A04412"; // 不支持的支付方式

	// -----------------------------管理系统错误码-----------------------
	public static final String ERR_ADMIN_LOGIN_ERR = "A09406"; //用户名或密码错误


	// -----------------------------RENBAO CODE-----------------------
	public static final String RENBAO_SUCCESS = "0";// 成功
	public static final String RENBAO_WRONG_PARAMS = "2";// 错误参数
	public static final String RENBAO_OPR_FAIL = "1";// 操作失败

}
