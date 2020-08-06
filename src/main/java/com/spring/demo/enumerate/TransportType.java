package com.spring.demo.enumerate;

import com.alibaba.fastjson.annotation.JSONType;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author zhangchen
 * @date 2019-04-26 14:13
 */
@JSONType(serializeEnumAsJavaBean = true)
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TransportType {
    //
    AIR("空运"), SEA("海派"), EXPRESS("快递"),RAIL("铁路"),SEA_LCL("海运拼箱"),TRUCK("卡派"), NONE("空");
    private String name;

    TransportType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
