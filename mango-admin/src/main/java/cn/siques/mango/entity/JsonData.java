package cn.siques.mango.entity;

import java.io.Serializable;

public class JsonData implements Serializable {

    private Integer code;
    private Object data; // 数据
    private String msg;


    // 成功，传入数据
    public static JsonData buildSuccess() {
        return new JsonData(200, null, null);
    }

    // 成功，传入数据
    public static JsonData buildSuccess(Object data) {
        return new JsonData(200, data, null);
    }

    // 失败，传入描述信息
    public static JsonData buildError(String msg) {
        return new JsonData(402, null, msg);
    }

    // 失败，传入描述信息,状态码
    public static JsonData buildError(String msg, Integer code) {
        return new JsonData(code, null, msg);
    }

    // 成功，传入数据,及描述信息
    public static JsonData buildSuccess(Object data, String msg) {
        return new JsonData(200, data, msg);
    }

    // 成功，传入数据,及状态码
    public static JsonData buildSuccess(Object data, int code) {
        return new JsonData(code, data, null);
    }

    @Override
    public String toString() {
        return "JsonData{" +
                "code=" + code +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }

    public JsonData(Integer code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public JsonData() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
