package com.spring.demo.permission;

import com.spring.demo.bean.AppResult;
import com.spring.demo.bean.AppResultBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AdminRedisServiceImpl implements AdminRedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public AppResult<String> getAdmin(String token) {
        PermissionUtil instance = PermissionUtil.getInstance(redisTemplate);
      String admin = instance.getAdmin(token);
        if (admin.equals("refuse")) {
            return AppResultBuilder.buildFailedResult("0004");
        }
        return AppResultBuilder.buildSuccessResult(admin,"0000",null,1);
    }

    @Override
    public AppResult<Boolean> hasPermission(String token, String permission) {

        PermissionUtil instance = PermissionUtil.getInstance(redisTemplate);
        boolean hasPermission = instance.hasPermission(token, permission);
        if (!hasPermission) {
            return AppResultBuilder.buildFailedResult(hasPermission, "0004");
        }
        return AppResultBuilder.buildSuccessResult(hasPermission, "0000");
    }

    @Override
    public String createScheduleToken() {
        PermissionUtil instance = PermissionUtil.getInstance(redisTemplate);
        return instance.createScheduleToken();
    }
}