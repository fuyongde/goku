package com.sunflower.goku.dubbo.api;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * @author fuyongde
 * @date 2019/3/28
 * @desc TODO add description in here
 */
public class CommonResponse<T> implements Serializable {

    private static int CODE_SUCCESS = 0;
    private static int CODE_ERROR = 1;
    private static String MSG_SUCCESS = "success";

    private int code;
    private boolean success;
    private String msg;
    private T value;
    private Throwable throwable;

    public CommonResponse(T value) {
        this.code = CODE_SUCCESS;
        this.msg = MSG_SUCCESS;
        this.success = true;
        this.value = value;
    }

    public CommonResponse(String msg, Throwable throwable) {
        this.code = CODE_ERROR;
        this.success = false;
        this.msg = msg;
        this.throwable = throwable;
    }

    public CommonResponse(int code, String msg, Throwable throwable) {
        this.code = code;
        this.success = false;
        this.msg = msg;
        this.throwable = throwable;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
