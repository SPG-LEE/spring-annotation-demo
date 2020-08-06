package com.spring.demo.controller;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class ListAbroadOrderRequest {
    private String orderByField;
    private String orderOutStatus;
    private String orderStatus;
    private int pageSize;
    private int pageIndex;
    private List<String> shopNames;

}
