package com.homeworkh.commonserver.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

/**
 * @author lq
 * @date 2020/12/18 10:15
 */
@Data
@ApiModel(description = "响应对象")
public class RestResponse {
    private static final long serialVersionUID = 1L;
    private static final Integer OK=200;
    private static final Integer ERROR=500;
    public static final Integer NO_PERMISSION = 10001;
    public static final Integer NO_SESSION = 10002;
    public static final Integer NOT_FOUND = 404;
    public static final Integer NOT_READABLE = 10003;//接收参数信息错误


    private Integer code = OK;
    private String msg;
    private Object data;
    private String path;
    private Date timestamp;

    private RestResponse(){}
    private RestResponse(Integer code, String msg, String path) {
        this.code = code;
        this.msg = msg;
        this.path = path;
        this.timestamp = new Date();
    }

    private RestResponse(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.timestamp = new Date();
    }

    private RestResponse(Integer code, String msg) {
        this(code, msg, null);
    }

    public static RestResponse ok(String msg) {
        return new RestResponse(OK, msg);
    }

    public static RestResponse ok(String msg, Object data) {
        return new RestResponse(OK, msg, data);
    }

    public static RestResponse error(String msg) {
        return new RestResponse(ERROR, msg);
    }

    public static RestResponse error(Integer code, String msg) {
        return new RestResponse(code, msg);
    }

    public static RestResponse error(Integer code, String msg, String path) {
        return new RestResponse(code, msg, path);
    }
}
