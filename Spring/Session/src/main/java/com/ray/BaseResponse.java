package com.ray;

import java.io.Serializable;

/**
 * Create by Ray on2017/4/25 17:52
 */
public class BaseResponse<T> implements Serializable {

    private static final long serialVersionUID = -2489121550537337308L;

    T result;

    int code = 0;

    String message = "成功";

    public BaseResponse() {}

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
