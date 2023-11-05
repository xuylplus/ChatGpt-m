package xyl.me.util;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import lombok.SneakyThrows;
import xyl.me.domain.User;

import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

public class BaiDuUtil {

    @SneakyThrows
    public static String getAddress(String address) {
        String ak = "v3qcGGb2FISqR8y34St4GGdcAQci79t0"; // AK
        String addr = address; // 地址
        // 构建请求参数
        String url = "https://api.map.baidu.com/geocoding/v3/";
        LinkedHashMap paramsMap = new LinkedHashMap<String, String>();
        paramsMap.put("address", address);
        paramsMap.put("output", "json");
        paramsMap.put("ak", ak);


        paramsMap.put("callback", "showLocation");
        // paramsMap.put("sn", s);


        //调用api
        String body = cn.hutool.http.HttpRequest.get(url).form(paramsMap).execute().body();
        System.out.println(body);
        //切割字符串body 只要后面的json
        String substring = body.substring(body.indexOf("(") + 1, body.lastIndexOf(")"));


        //转换为map
        Map<String, String> map = JSONUtil.toBean(substring, new TypeReference<Map<String, String>>() {
        }, true);

        //josin

        if ("0".equals(map.get("status"))) {// 解析的地址不为空时 进行值的获取
            Map<String, String> result1 = JSONUtil.toBean(map.get("result"), new TypeReference<Map<String, String>>() {
            }, true);
            Map<String, String> location1 = JSONUtil.toBean(result1.get("location"), new TypeReference<Map<String, String>>() {
            }, true);

            // 纬度值 经度值
            String location = location1.get("lat") + "," + location1.get("lng");

            return location;
        }
        return null;
    }
}
