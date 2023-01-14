package com.example.javaee.utils;

import java.util.HashMap;
import java.util.Map;

public class Result
{
    public Result() {}

    public static Map<String, Object> success(String details, Object data)
    {
        Map<String, Object> map = new HashMap();
        map.put("code", 200);
        map.put("message", "成功 : " + details);
        map.put("response", data);
        return map;
    }

    public static Map<String, Object> fail(String details, Object data)
    {
        Map<String, Object> map = new HashMap();
        map.put("code", 200);
        map.put("message", "失败 : " + details);
        map.put("response", data);
        return map;
    }
}