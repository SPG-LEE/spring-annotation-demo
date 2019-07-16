package com.spring.demo.controller.search.bean.request;

import com.spring.demo.bean.search.GetRequestBase;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class GetClassListRequest extends GetRequestBase {

    @NotNull
    @ApiModelProperty(value = "年级id", required = true)
    private long gradeId;

}
