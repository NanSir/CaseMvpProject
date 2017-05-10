package com.sir.app.retrofit.model.cartoon.bean;

/**
 * Created by zhuyinan on 2017/5/3.
 */

public class BookResponse<T> {

    private String ErrCode;

    private String ErrMsg;

    private T Return;

    private String Costtime;

    private boolean IsError;

    private String Message;

    public void setErrCode(String ErrCode) {
        this.ErrCode = ErrCode;
    }

    public String getErrCode() {
        return this.ErrCode;
    }

    public void setErrMsg(String ErrMsg) {
        this.ErrMsg = ErrMsg;
    }

    public String getErrMsg() {
        return this.ErrMsg;
    }

    public void setReturn(T Return) {
        this.Return = Return;
    }

    public T getReturn() {
        return this.Return;
    }

    public void setCosttime(String Costtime) {
        this.Costtime = Costtime;
    }

    public String getCosttime() {
        return this.Costtime;
    }

    public void setIsError(boolean IsError) {
        this.IsError = IsError;
    }

    public boolean getIsError() {
        return this.IsError;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public String getMessage() {
        return this.Message;
    }
}
