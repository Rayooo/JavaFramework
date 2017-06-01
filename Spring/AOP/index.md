## Spring Boot AOP 和拦截器

### AOP术语

#### 通知（Advice）

通知定义了切面是什么以及何时使用

-   前置通知（Before），在目标方法被调用之前调用通知功能
-   后置通知（After），在目标方法完成之后条用通知
-   返回通知（After-returning），在目标方法成功执行指挥调用通知
-   异常通知（After-throwing），在目标方法抛出异常后调用通知
-   环绕通知（Around），包裹了被通知的方法，在被通知的方法调用之前喝调用之后执行自定义的行为

#### 连接点（Join Point）

连接点是在应用执行过程中能够插入切面的一个点，这个点可以是调用方法时，抛出异常时，甚至修改一个字段时。切面代码可以利用这些点插入到应用的正常流程之中，并添加新的行为

#### 切点（Pointcut）

切点定义了何处，切点的定义会匹配通知所要织入的一个或多个连接点。我们通常使用明确的类和方法名称，或是利用正则表达式定义所匹配的类和方法名称来制定这些切点

#### 切面（Aspect）

切面是通知和切点的结合，通知和切点共同定义了切面的全部内容——它是什么，在何时和何处完成其功能

#### 引入（Introduction）

引入允许我们向现有的类添加新的方法或属性，无需修改这些现有的类的情况下，让它们具有新的行为和状态

#### 织入（Weaving）

织入是把切面应用到目标对象并创建新的代理对象的过程。切面在指定的连接点被织入到目标对象中，在目标对象的生命周期里有多个点可以进行织入

-   编译期：切面在目标类编译时被织入，这种方式需要特殊的编译器，AspectJ的织入编译器就是以这种方式织入切面的
-   类加载期：切面在目标类加载到JVM时被织入，这种方式需要特殊的类加载器，它可以在目标类被引入应用之前增强该目标类的字节码。AspectJ5的加载时织入就支持这种方式织入切面
-   运行期：切面在应用运行的某个时刻被织入，一般情况下，在织入切面时，AOP容器会为目标对象动态地创建一个代理对象，Spring AOP就是以这种方式织入切面的。Spring AOP构建在动态代理之上。





----

### 其他

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


#### 过滤器和拦截器的区别

http://www.jianshu.com/p/fbff642f9901

http://www.jianshu.com/p/3e6433ead5c3

**过滤器（filter）：**

-   过滤器处于客户端与Web资源（Servlet、JSP、HTML）之间，客户端与Web资源之间的请求和响应都要通过过滤器进行过滤。举例：在过滤器中定义了禁止访问192.10.10.1这个地址，那么当客户端发出访问192.10.10.1的请求时，经过过滤器后，客户端得到的响应是出现该IP禁止访问的提示。
-   在java web中，你传入的request,response提前过滤掉一些信息，或者提前设置一些参数，然后再传入servlet或者struts的 action进行业务逻辑，比如过滤掉非法url（不是login.do的地址请求，如果用户没有登陆都过滤掉）,或者在传入servlet或者 struts的action前统一设置字符集，或者去除掉一些非法字符

**拦截器（interceptor）:**，

-   拦截器是一种面向方面/切面编程（AOP Aspect-Oriented Programming）.
-   面向切面就是将多个模块的的通用服务进行分离，如权限管理、日志服务，他们在多个模块中都会用到，就可以将其各自封装为一个可重用模块。而这些通用服务的具体实现是通过拦截器来完成，比如用户客户端访问一些保密模块都应先通过权限审查的拦截器来进行权限审查，确定用户是否具有该项操作的权限后方能向下执行。
-   在面向切面编程的就是在你的service或者一个方法，前调用一个方法，或者在方法后调用一个方法比如动态代理就是拦截器的简单实现，在你调用方法前打印出字符串（或者做其它业务逻辑的操作），也可以在你调用方法后打印出字符串，甚至在你抛出异常的时候做业务逻辑的操作。

### 两者的区别

-   拦截器是基于java反射机制的，而过滤器是基于函数回调。
-   拦截器不依赖于Servlet容器，而过滤器依赖于servlet容器。
-   拦截器只能对action请求起作用，而过滤器可以对几乎所以的请求起作用。
-   拦截器可以访问action上下文，值栈里的对象，而过滤器不能。
-   在Action的生命周期周，拦截器可以被多次调用，而过滤器只能在容器初始化的时候被调用一次。

**执行顺序 ：**过滤前 - 拦截前 - Action处理 - 拦截后 - 过滤后。个人认为过滤是一个横向的过程，首先把客户端提交的内容进行过滤(例如未登录用户不能访问内部页面的处理)；过滤通过后，拦截器将检查用户提交数据的验证，做一些前期的数据处理，接着把处理后的数据发给对应的Action；Action处理完成返回后，拦截器还可以做其他过程(还没想到要做啥)，再向上返回到过滤器的后续操作。

拦截器 ：是在面向切面编程的就是在你的service或者一个方法前调用一个方法，或者在方法后调用一个方法比如动态代理就是拦截器的简单实现，在你调用方法前打印出字符串（或者做其它业务逻辑的操作），也可以在你调用方法后打印出字符串，甚至在你抛出异常的时候做业务逻辑的操作。

过滤器：是在java web中，你传入的request,response提前过滤掉一些信息，或者提前设置一些参数，然后再传入servlet或者struts的 action进行业务逻辑，比如过滤掉非法url（不是login.do的地址请求，如果用户没有登陆都过滤掉）,或者在传入servlet或者 struts的action前统一设置字符集，或者去除掉一些非法字符.