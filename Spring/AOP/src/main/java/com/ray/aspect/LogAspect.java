package com.ray.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {
    //         返回任意类型                               * 代表任意方法
    //             |                                     |
    //   execution(* com.ray.service.impl.UserServiceImpl.*(..))
    //       |                      |                       |
    // 方法执行时触发              方法所属的类               (..) 使用任意参数

    @Pointcut("execution(* com.ray.service.impl.UserServiceImpl.*(..))")
    public void logAop(){}

//    这里的@Pointcut注解是为了定义切面内重用的切点，也就是说把公共的东西抽出来，
// 定义了任意的方法名称logAop，这样下面用到的各种类型通知就只要写成
//
//      @Before("logAop() && args(name)")
//      @AfterReturning("logAop()")
//      @AfterThrowing("logAop()")

//  否则就要写成
//      @Before("execution(* com.tengj.demo.service.impl.UserServiceImpl.*(..))")
//      @AfterReturning("execution(* com.tengj.demo.service.impl.UserServiceImpl.*(..))")
//      @AfterThrowing("execution(* com.tengj.demo.service.impl.UserServiceImpl.*(..))")



    @Before("logAop() && args(name)")
    public void logBefore(String name){
        System.out.println(name + " 前置通知 Before");
    }

    @AfterReturning("logAop()")
    public void logAfterReturning(){
        System.out.println("返回通知 AfterReturning");
    }

    @After("logAop() && args(name)")        //args 定义只要跟sayHello参数名称一样就可以
    public void logAfter(String name){
        System.out.println(name + " 后置通知 After");
    }

    @AfterThrowing("logAop()")
    public void logAfterThrow(){
        System.out.println("异常通知 AfterThrowing");
    }

//    环绕通知，可以实现失败后重试
//    @Around("logAop()")
    public void logAround(ProceedingJoinPoint jp){
        try {
            System.out.println("自定义前置通知");
            jp.proceed();       //需要手动调用
            System.out.println("后置");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }


}
