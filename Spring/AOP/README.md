### Spring Boot AOP 和拦截器

#### 拦截器

在@SpringBootApplication启动文件中

```java
@SpringBootApplication
public class AopApplication extends WebMvcConfigurerAdapter {

   public static void main(String[] args) {
      SpringApplication.run(AopApplication.class, args);
   }

   @Override
   public void addInterceptors(InterceptorRegistry registry) {
      registry.addInterceptor(new MyInterceptor());
   }
}
```

注意 extends WebMvcConfigurerAdapter  然后重写方法addInterceptors，设置拦截器

编写拦截器

```java
package com.ray.interceptor;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MyInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("preHandle");

        return super.preHandle(request, response, handler);
        //拦截请求
//        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        System.out.println("postHandle");

        super.postHandle(request, response, handler, modelAndView);
    }


}
```

### AOP

启用AOP只要@Aspect 这个注解就可以了，比较容易，注意一下简写的方法

```java
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
      @Before("logAop() && args(name)")
      @AfterReturning("logAop()")
      @AfterThrowing("logAop()")

//  否则就要写成
      @Before("execution(* com.tengj.demo.service.impl.UserServiceImpl.*(..))")
      @AfterReturning("execution(* com.tengj.demo.service.impl.UserServiceImpl.*(..))")
      @AfterThrowing("execution(* com.tengj.demo.service.impl.UserServiceImpl.*(..))")

```