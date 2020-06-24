package com.example.demo.common.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tangxuezhi
 * @date 2019-04-13
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDomain {
    private Integer code;
    private String msg;
    private Object data;

    private static Map emptyJson = new HashMap();

    public ResponseDomain(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = emptyJson;
    }

    public ResponseDomain(ResponseCode commonResponseCode) {
        this.code = commonResponseCode.getCode();
        this.msg = commonResponseCode.getMsg();
        this.data = emptyJson;
    }

    public ResponseDomain(ResponseCode commonResponseCode, Object object) {
        this.code = commonResponseCode.getCode();
        this.msg = commonResponseCode.getMsg();
        this.data = object;
    }

    public static ResponseDomain ok() {
        return new ResponseDomain(CommonResponseCode.SUCCESS);
    }

    public static ResponseDomain ok(Object data) {
        return new ResponseDomain(CommonResponseCode.SUCCESS, data);
    }


    public static ResponseDomain error() {
        return new ResponseDomain(CommonResponseCode.INNER_ERROR);
    }

    public static ResponseDomain error(Object data) {
        return new ResponseDomain(CommonResponseCode.INNER_ERROR, data);
    }

    public static ResponseDomain custom(ResponseCode responseCode) {
        return new ResponseDomain(responseCode);
    }
}

