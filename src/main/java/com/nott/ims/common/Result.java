package com.nott.ims.common;

import java.io.Serializable;

public class Result implements Serializable {
    private String code;
    private String msg;
    private Object obj;

    public Result(String code, String msg, Object obj) {
        this.code = code;
        this.msg = msg;
        this.obj = obj;
    }

    public Result() {
    }


    public static Result okData(String msg, Object obj) {
        Result result = new Result();
        result.setCode("200");
        result.setMsg(msg);
        result.setObj(obj);
        return result;
    }

    public static Result ok(String msg) {
        Result result = new Result();
        result.setCode("200");
        result.setMsg(msg);
        return result;
    }

    public static Result okData(Object obj) {
        Result result = new Result();
        result.setCode("200");
        result.setMsg("success");
        result.setObj(obj);
        return result;
    }

    public static Result failure(String msg) {
        Result result = new Result();
        result.setCode("-999");
        result.setMsg(msg);
        return result;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
