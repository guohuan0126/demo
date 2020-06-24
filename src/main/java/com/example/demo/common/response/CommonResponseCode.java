package com.example.demo.common.response;

/**
 * @author Xiaochun Liu
 * @date 2018/10/25
 */
public enum CommonResponseCode implements ResponseCode {
    /**
     * 请求成功
     */
    SUCCESS(200, "OK"),
    /**
     * 参数错误
     */
    PARAMETER_ERROR(400, "Bad Request"),
    /**
     * 未授权
     */
    UNAUTHORIZED(401, "Unauthorized"),
    /**
     * 请求资源不存在
     */
    NOT_FOUND(404, "Not Found"),
    /**
     * 内部程序错误
     */
    INNER_ERROR(500, "Inner Error");

    CommonResponseCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Integer code;
    private String msg;

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
