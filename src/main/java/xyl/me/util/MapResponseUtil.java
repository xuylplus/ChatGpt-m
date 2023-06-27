package xyl.me.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义返回map
 */
public class MapResponseUtil {

    public static Map createMapResponse(String code, Object data, String msg) {

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", code);
        result.put("msg", msg);
        result.put("data", data);
        return result;
    }

    /**
     * @Transactional default rollbackFor RuntimeException
     */
    public static Map createMapResponse(String code, Object data, String msg, RuntimeException ex) {
        if (ex != null) {
            throw new HAException(code, msg);
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", code);
        result.put("msg", msg);
        result.put("data", data);
        return result;
    }


    /**
     * 默认成功返回
     * <p>
     * code : A00000
     * msg  : 操作成功
     */
    public static Map success() {
        return createMapResponse(ApiCode.SUCCESS, null, "操作成功");
    }

    /**
     * o 返回参数
     */
    public static Map success(Object o) {
        return createMapResponse(ApiCode.SUCCESS, o, "操作成功");
    }

    public static Map badRequest(String msg, boolean format) {
        return createMapResponse(ApiCode.ERR_WRONG_PARAMS, null, format ? String.format("系统错误：illegal argument for %s", msg) : msg);
    }

    public static Map badRequest(String msg) {
        return createMapResponse(ApiCode.ERR_WRONG_PARAMS, null, msg);
    }

    public static Map badRequest(String msg, RuntimeException ex) {
        return createMapResponse(ApiCode.ERR_WRONG_PARAMS, null, msg, ex);
    }

    public static Map error(String msg) {
        return createMapResponse(ApiCode.ERR_SYSTEM, null, msg);
    }


    public static Map createTaiPingMapResponse(String flag, String error) {

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("flag", flag);
        result.put("error", error);
        return result;
    }


    public static Map createPromaMapResponse(String errCode, String errMsg) {

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errCode", errCode);
        result.put("errMsg", errMsg);
        return result;
    }

    public static Map TicketRenBaoMapResponse(String errCode, String errMsg) {

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("retCode", errCode);
        result.put("retMsg", errMsg);
        return result;
    }
    public static Map TicketRenBaoStatusMapResponse(String errCode, String errMsg,String status) {

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("retCode", errCode);
        result.put("retMsg", errMsg);
        result.put("status", status);
        return result;
    }

    public static Map TicketRenBaoDataMapResponse(String errCode, String errMsg,Object data) {

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("retCode", errCode);
        result.put("retMsg", errMsg);
        result.put("data", data);
        return result;
    }


}
