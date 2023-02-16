package com.example.javaee.exceptionHandler.exception;

public enum ExceptionEnum implements BaseErrorInfoInterface{

    // 数据操作错误定义

    USERNAME_空("5001","用户名不能为空"),
    USERNAME_无效("5002","该用户名无效"),
    USERNAME_敏感("5003","用户名包含敏感词"),
    USERNAME_重复("5004","用户名重复"),


    PASSWORD_空("5005","密码不能为空"),
    PASSWORD_错误("5006","密码错误"),
    PASSWORD_重复("5007","和原有密码一致，请修改"),

    ROLE_空("5008","密码不能为空"),
    ROLE_范围外("5009","用户角色值不在枚举范围内"),

    ID_不合法("5010","该用户id类型不合法"),
    ID_无效("5011","此用户id无效"),

    USER_无权限("5012","该用户无访问权限"),

    News_标题为空("5101","标题为空"),
    News_内容为空("5102","内容为空"),
    News_类别为空("5103","类别为空"),
    News_类别不存在("5104","类别不存在"),
    News_不属于("5105","该新闻不属于你"),
    News_ID不合法("5106","新闻ID不合法"),
    News_不可编辑("5107","该新闻已不可编辑"),
    News_新闻不存在("5108","新闻不存在"),


    PARAMS_接收参数错误("5501","接收参数错误"),
    PARAMS_接收状态参数非法("5502","接收状态参数非法");
    /**
     * 错误码
     */
    private final String resultCode;

    /**
     * 错误描述
     */
    private final String resultMsg;

    ExceptionEnum(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }



    @Override
    public String getResultCode() {
        return resultCode;
    }

    @Override
    public String getResultMsg() {
        return resultMsg;
    }
}
