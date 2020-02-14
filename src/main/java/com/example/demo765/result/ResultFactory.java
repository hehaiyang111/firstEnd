package com.example.demo765.result;/**
 * @Description TO
 * @Author hehaiyang
 * @Date 2020/2/14 16:59
 **/

/**
 *@Description TO
 *@Author hehaiyang
 *@Date 2020/2/14 16:59
 **/
public class ResultFactory {
    public static Result buildSuccessResult(Object data) {
        return buildResult(ResultCode.SUCCESS, "成功", data);
    }

    public static Result buildFailResult(String message) {
        return buildResult(ResultCode.FAIL, message, null);
    }

    public static Result buildResult(ResultCode resultCode, String message, Object data) {
        return buildResult(resultCode.code, message, data);
    }

    public static Result buildResult(ResultCode resultCode, String message) {
        return buildResult(resultCode, message);
    }

    public static Result buildResult(int resultCode, String message, Object data) {
        return new Result(resultCode, message, data);
    }
}
