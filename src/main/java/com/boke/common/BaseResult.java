package com.boke.common;


/**
 * Created by chendd on 2015/3/29.
 *
 */
public class BaseResult<T>  {
    private  boolean success = true;
    private String resultMsg;
    private int errorCode;

    protected T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public void setResultEnum(CommonCode code){
        this.setErrorCode(code.getKey());
        this.setResultMsg(code.getValue());
        this.setSuccess(isSuccessCode(code.getKey()));
    }

    public void setResultEnum(CommonSubCode code){
        this.setErrorCode(code.getKey());
        this.setResultMsg(code.getValue());
        this.setSuccess(isSuccessCode(code.getKey()));
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public static <U> BaseResult<U> buildFailResult(int errorCode, String errorMsg, U value) {
        BaseResult<U> baseResult = new BaseResult<U>();
        baseResult.setSuccess(false);
        baseResult.setErrorCode(errorCode);
        baseResult.setValue(value);
        baseResult.setResultMsg(errorMsg);

        return baseResult;
    }

    public static <U> BaseResult<U> buildSuccessResult(U value) {
        BaseResult<U> baseResult = new BaseResult<U>();
        baseResult.setSuccess(true);
        baseResult.setValue(value);
        return baseResult;
    }

    private boolean isSuccessCode(int key){
        if (key==CommonCode.SUCCESS.getKey())return true;
        return false;
    }
}
