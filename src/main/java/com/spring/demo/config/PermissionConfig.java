package com.spring.demo.config;

import com.spring.demo.permission.AdminRedisUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Configuration
public class PermissionConfig {
    @Autowired
    protected HttpServletRequest httpRequest;

    @Autowired
    private RedisTemplate redisTemplate;

    @Around("@annotation(com.spring.demo.permission.RequirePermission)")
    public Object validate(ProceedingJoinPoint point) {
        String token = httpRequest.getHeader("x-access-token");
        return AdminRedisUtil.validateToken(point, redisTemplate, token);
    }
}
