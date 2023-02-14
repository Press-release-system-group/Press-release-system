package com.example.javaee.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Result<T> implements Serializable
{
    public Result() {}

//    public static Map<String, Object> success(String details, Object data)
//    {
//        Map<String, Object> map = new HashMap();
//        map.put("code", 200);
//        map.put("message", "成功 : " + details);
//        map.put("response", data);
//        return map;
//    }
//
//    public static Map<String, Object> fail(String details, Object data)
//    {
//        Map<String, Object> map = new HashMap();
//        map.put("code", 200);
//        map.put("message", "失败 : " + details);
//        map.put("response", data);
//        return map;
//    }
    private Integer code;
    private String msg;
    private T data;

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
