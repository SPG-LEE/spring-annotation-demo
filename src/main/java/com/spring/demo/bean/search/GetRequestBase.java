package com.spring.demo.bean.search;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

@Data
public class GetRequestBase {

    private int pageIndex;

    private int pageSize = 1;

    private String orderByField;

    private boolean pageNoLimit;

    private boolean isPermission;

}
