package com.guomiaomiao.loggingAspect;


import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Created by 15295 on 2018/5/24.
 */
@Aspect
@Component
public class Logging {
    @Before("execution(* com.guomiaomiao.service.impl.UserServiceImpl.*(..))")
    public void before() {
        System.out.println("111111111111");
    }
    @After("execution(* com.guomiaomiao.service.impl.UserServiceImpl.*(..))")
    public void after() {
        System.out.println("222222222222");
    }
    @AfterReturning("execution(* com.guomiaomiao.service.impl.UserServiceImpl.*(..))")
    public void afterReturning() {
        System.out.println("333333333333");
    }
    @AfterThrowing("execution(* com.guomiaomiao.service.impl.UserServiceImpl.*(..))")
    public void afterThrowing() {
        System.out.println("444444444444");
    }
}
