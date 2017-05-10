package com.sir.app.retrofit.model.movie.base;
/**
 * Created by zhuyinan on 2017/04/11
 */
public class MovieResponse<T> {

    private int error_code;

    private String reason;

    private T result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
