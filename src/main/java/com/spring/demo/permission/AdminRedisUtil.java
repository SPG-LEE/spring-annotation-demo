package com.spring.demo.permission;

import com.spring.demo.bean.AppResult;
import com.spring.demo.bean.AppResultBuilder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.Arrays;

public class AdminRedisUtil {

    public static AppResult<String> getAdmin(RedisTemplate redisTemplate, String token) {
        PermissionUtil instance = PermissionUtil.getInstance(redisTemplate);
        String admin = instance.getAdmin(token);

        if (admin == null) {
            return AppResultBuilder.buildFailedResult("0001");
        }
        return AppResultBuilder.buildSuccessResult(admin,
                "0000");
    }

    public static AppResult<Boolean> hasPermission(RedisTemplate redisTemplate, String token, String permission) {
        PermissionUtil instance = PermissionUtil.getInstance(redisTemplate);
        boolean hasPermission = instance.hasPermission(token, permission);
        if (!hasPermission) {
            return AppResultBuilder.buildFailedResult(hasPermission,
                   "0002");
        }
        return AppResultBuilder.buildSuccessResult(hasPermission,
                "0000");
    }

    public static String createScheduleToken(RedisTemplate redisTemplate) {
        PermissionUtil instance = PermissionUtil.getInstance(redisTemplate);
        String token = instance.createScheduleToken();
        return token;
    }

    public static Object validateToken(ProceedingJoinPoint point, RedisTemplate redisTemplate, String token) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Object[] args = point.getArgs();
        String[] parameterNames = signature.getParameterNames();
        int isPermissionIndex = Arrays.asList(parameterNames).indexOf("isPermission");
        boolean isPermission = true;
        if (isPermissionIndex >=0) {
            if (args[isPermissionIndex] instanceof Boolean){
                isPermission = (boolean) args[isPermissionIndex];
            }
       }
        Method method = signature.getMethod();
        Class returnType = signature.getReturnType();
        if (token == null || token.trim().isEmpty()) {
            AppResult<Object> AppResult = AppResultBuilder
                    .buildFailedResult("0005");
            return AppResult;
        }
        AppResult<String> adminResult = getAdmin(redisTemplate, token);
        if (!adminResult.isSuccess()) {
            AppResult<Object> AppResult = AppResultBuilder
                    .buildFailedResult("0001");
            return AppResult;
        }
        // 判断是否有权限

        RequirePermission requirePermission = method.getAnnotation(RequirePermission.class);

        if (isPermission && requirePermission.required() && !hasPermission(redisTemplate, token, requirePermission.value()).getData()) {
            AppResult<Object> AppResult = AppResultBuilder
                    .buildFailedResult("0005");
            return AppResult;
        }
        //  System.out.println("RequirePermissionAspect end");
        try {
            return point.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            AppResult<Object> AppResult = AppResultBuilder
                    .buildFailedResult("0006",getExceptionToString(throwable));
            return AppResult;
        }
    }


    private static String getExceptionToString(Throwable e) {
        if (e == null) {
            return "";
        }
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }
}
