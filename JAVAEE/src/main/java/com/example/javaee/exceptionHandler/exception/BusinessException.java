package com.example.javaee.exceptionHandler.exception;

//这个是自己创建的业务异常类
public class BusinessException extends RuntimeException{

    private Integer code;
    private String msg;

    public BusinessException(BaseErrorInfoInterface infoInterface) {
        this.code = Integer.valueOf(infoInterface.getResultCode());
        this.msg = infoInterface.getResultMsg();
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
