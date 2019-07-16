package com.spring.demo.controller.search;

import com.spring.demo.bean.AppResult;
import com.spring.demo.bean.AppResultBuilder;
import com.spring.demo.bean.ServiceResult;
import com.spring.demo.controller.search.bean.request.GetClassListRequest;
import com.spring.demo.controller.search.bean.response.GetClassListResponse;
import com.spring.demo.entity.Class;
import com.spring.demo.service.ClassService;
import io.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("demo/builder")
@Validated
@CrossOrigin(allowedHeaders = {"x-access-token"})
public class BuilderDemoController {
    @Autowired
    private ClassService classService;

    @GetMapping("/search")

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "gradeId", value = "班级id", required = true),
            @ApiImplicitParam(paramType = "query", name = "pageIndex", value = "页码", defaultValue = "0"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "每页数量", defaultValue = "10"),
            @ApiImplicitParam(paramType = "query", name = "orderByField", value = "排序方式", defaultValue = "-updateDate"),
            @ApiImplicitParam(paramType = "query", name = "pageNoLimit", value = "是否分页", defaultValue = "true")
    })
    public AppResult<List<GetClassListResponse>> findAll(@Valid @ModelAttribute GetClassListRequest getClassListRequest) {
        //构建查询bean
        //构建查询
        ServiceResult<List<Class>> getClassResult = classService.findAll(getClassListRequest);
        if (!getClassResult.isSuccess()) {
            return AppResultBuilder.buildFailedResult(getClassResult.getCode());
        }
        //构建响应bean
        List<GetClassListResponse> result = new ArrayList<>();
        getClassResult.getData().forEach(origin -> {
            GetClassListResponse target = new GetClassListResponse();
            BeanUtils.copyProperties(origin, target);
            result.add(target);

        });
        //返回结果集
        return AppResultBuilder.buildSuccessResult(result, getClassResult.getCode(), getClassResult.getTotal());
    }
}
