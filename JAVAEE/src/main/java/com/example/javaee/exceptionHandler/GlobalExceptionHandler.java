package com.example.javaee.exceptionHandler;

import com.example.javaee.exceptionHandler.exception.BusinessException;
import com.example.javaee.utils.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sun.awt.SunHints;

import java.security.SignatureException;

//这里是异常的处理区
@RestControllerAdvice
public class GlobalExceptionHandler {

    //这里是业务异常
    @ExceptionHandler(value = BusinessException.class)
    public Result businessException(BusinessException e){
        Result result=new Result();
        result.setCode(e.getCode());
        result.setMsg(e.getMsg());
        return result;
    }

//    @ExceptionHandler(value = RuntimeException.class)
//    public Result businessException(RuntimeException e){
//        Result result=new Result();
//        result.setCode(500);
//        result.setMsg("服务器出现未定义异常   "+  e.toString());
//
//        return result;
//    }


}
