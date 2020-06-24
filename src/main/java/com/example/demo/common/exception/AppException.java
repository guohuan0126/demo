package com.example.demo.common.exception;

import com.example.demo.common.response.ResponseCode;

/**
 * @author tangxuezhi
 * @date 2019-04-10
 */
public class AppException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private ResponseCode responseCode;

    public AppException(ResponseCode appResponseCode) {
        this.responseCode = appResponseCode;
    }

    public AppException(ResponseCode appResponseCode, String message) {
        super(message);
        this.responseCode = appResponseCode;
    }

    public ResponseCode getResponseCode() {
        return responseCode;
    }

    /**
     * for better performance
     *
     * @return Throwable
     */
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

    public Throwable doFillInStackTrace() {
        return super.fillInStackTrace();
    }
}
