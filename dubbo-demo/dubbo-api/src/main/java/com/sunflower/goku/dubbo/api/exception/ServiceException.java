package com.sunflower.goku.dubbo.api.exception;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * @author fuyongde
 * @desc 服务异常
 * @date 2017/10/30 17:24
 */
public class ServiceException extends RuntimeException {

    private static final Properties PROPERTIES = new Properties();

    static {
        try {
            PROPERTIES.load(new InputStreamReader(ServiceException.class.getResourceAsStream("/error.properties"), Charset.defaultCharset()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private int errorCode;

    public ServiceException() {
        super(PROPERTIES.getProperty(String.valueOf(100000)));
        this.errorCode = 100000;
    }

    public ServiceException(int errorCode) {
        super(PROPERTIES.getProperty(String.valueOf(errorCode)));
        this.errorCode = errorCode;
    }

    public ServiceException(int errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

}
