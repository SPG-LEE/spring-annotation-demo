package com.spring.demo.permission;

import com.spring.demo.bean.AppResult;

public interface AdminRedisService {

    AppResult<String> getAdmin(String token);

    AppResult<Boolean> hasPermission(String token, String permission);

    String createScheduleToken();

}
