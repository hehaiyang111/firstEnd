package com.example.demo765.result;/**
 * @Description TO
 * @Author hehaiyang
 * @Date 2020/2/14 16:59
 **/


public enum ResultCode {
    SUCCESS(200),
    FAIL(400),
    UNAUTHORIZED(401),
    NOT_FOUND(404),
    INTERNAL_SERVER_ERROR(500);

    public int code;

    ResultCode(int code) {
        this.code = code;
    }
}
