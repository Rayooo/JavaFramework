package com.ray.util;

import java.io.Serializable;

/**
 * 2017/5/2 17:12
 */
public class BaseResponse<T> implements Serializable {

    private static final long serialVersionUID = -4074774418038838920L;

    private String message = "运行正确";

    private Integer code = 0;

    private T result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

}
