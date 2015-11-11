package com.livepass.xpd.xmlparserdemo.handlers;

/**
 * Created by VuVT on 11/11/2015.
 * Error object
 * Will be shown when WS returned error code
 */
public class ErrorCode {

    private String errorID;
    private String errorMsg;

    public ErrorCode() {

    }

    public ErrorCode(String errorID, String errorMsg) {
        super();
        this.errorID = errorID;
        this.errorMsg = errorMsg;
    }

    public String getErrorID() {
        return errorID;
    }

    public void setErrorID(String errorID) {
        this.errorID = errorID;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

}
