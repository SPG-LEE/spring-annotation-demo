package com.spring.demo.bean;

import java.util.Date;

/**
 * 接口数据实体类
 *
 * @param <T>
 */

public class AppResult<T> {

    private boolean success;// 是否成功

    private String message;// 消息

    private String code;// 消息字段

    private Date systemDate = new Date();// 系统时间

    private T data;// 数据

    private long total;// 数量

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getSystemDate() {
        return systemDate;
    }

    public void setSystemDate(Date systemDate) {
        this.systemDate = systemDate;
    }
}
