package com.spring.demo.permission;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import java.util.concurrent.TimeUnit;

public class PermissionUtil {
    private RedisTemplate redisTemplate;

    private static PermissionUtil cur;

    public static PermissionUtil getInstance(RedisTemplate redisTemplate) {
        if (cur == null) {
            cur = new PermissionUtil(redisTemplate);
        }
        return cur;
    }

    private PermissionUtil(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public String createScheduleToken() {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        final String token = Jwts.builder().setSubject(PermissionConstants
                .SCHEDULE + "," +
                System.currentTimeMillis())
                .signWith(SignatureAlgorithm.HS512, "admin")
                .compact();
        String redisToken = Jwts.builder().setSubject(PermissionConstants
                .SCHEDULE)
                .signWith(SignatureAlgorithm.HS512, "admin").compact();
        ops.set(token, redisToken);
        ops.set(redisToken, token);
        redisTemplate.expire(token, 1, TimeUnit.DAYS);
        redisTemplate.expire(redisToken, 1, TimeUnit.DAYS);
        return token;
    }

    public String getAdmin(String token) {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();

        String redisToken = ops.get(token);
        if (redisToken == null) {
            return null;
        }
        String whiteList = whiteList(token, redisToken);
        if (whiteList != null) {
            return whiteList;
        }
     return "refuse";
    }

    private String whiteList(String token, String redisToken) {
        String key = Jwts.builder().setSubject(PermissionConstants
                .SCHEDULE)
                .signWith(SignatureAlgorithm.HS512, "admin").compact();
        if (redisToken.equals(key)) {
            redisTemplate.expire(token, 1, TimeUnit.DAYS);
            redisTemplate.expire(redisToken, 1, TimeUnit.DAYS);
            return "apply";
        }
        return null;
    }

    public boolean hasPermission(String token, String permission) {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        String redisToken = ops.get(token);
        if (redisToken == null) {
            return false;
        }
        String whiteList = whiteList(token, redisToken);
        if (whiteList != null) {
            return true;
        }

        return false;
    }

}
