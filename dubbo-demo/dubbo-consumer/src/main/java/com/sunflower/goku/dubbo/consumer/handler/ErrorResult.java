package com.sunflower.goku.dubbo.consumer.handler;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 错误返回结果
 *
 * @author fuyongde
 * @date 2017/10/30 17:38
 */
public class ErrorResult<T> implements Serializable {

    /**
     * timestamp : 1491271552469
     * status : 500
     * error : Internal Server Error
     * exception : java.lang.NullPointerException
     * message : No message available
     * path : /black/api/test/testNull
     */

    private LocalDateTime timestamp;
    private int status;
    private String exception;
    private T message;
    private String path;

    public ErrorResult() {
    }

    public ErrorResult(int status, T message, String path) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
        this.path = path;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public T getMessage() {
        return message;
    }

    public void setMessage(T message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}

