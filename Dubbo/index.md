生产者Provider

首先必须在服务器上开启zk，然后dubbo注册zk

通过SpringBoot来启动应用

1.使用 @ImportResource("classpath:spring-config.xml") 来指定Spring配置文件，在生产者中

```java
@SpringBootApplication
@ImportResource("classpath:spring-config.xml")
public class ProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProviderApplication.class, args);
	}
}
```

2.在resources中编写spring-config.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:dubbo="http://code.alibabatech.com/schema/dubbo"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://code.alibabatech.com/schema/dubbo
        http://code.alibabatech.com/schema/dubbo/dubbo.xsd ">

    <!-- 具体的实现bean -->
    <bean id="testService" class="com.ray.service.impl.TestServiceImpl" />
    <!-- 提供方应用信息，用于计算依赖关系 -->
    <dubbo:application name="provider"  />
    <!-- 使用zookeeper注册中心暴露服务地址 -->
    <dubbo:registry address="zookeeper://123.206.121.72:2181" />
    <!-- 用dubbo协议在20880端口暴露服务 -->
    <dubbo:protocol name="dubbo" port="29014" />
    <!-- 声明需要暴露的服务接口 -->
    <dubbo:service interface="com.ray.service.TestService" ref="testService" />
</beans>
```

如果 xmlns:dubbo="http://code.alibabatech.com/schema/dubbo" 这个找不到，用maven重新加载包，因为这个dubbo挂了好久了，阿里将这个xsd放入jar包中了

3.编写TestService

```java
package com.ray.service;

public interface TestService {

    String getName();

}	
```

4.编写TestServiceImpl

```Java
package com.ray.service.impl;


import com.ray.service.TestService;
import org.springframework.stereotype.Service;

@Service("testService")
public class TestServiceImpl implements TestService {

    @Override
    public String getName() {
        return "This is TestServiceImpl";
    }

}
```

5.设置消费者Customer，如1设置spring-config.xml路径

6.在spring-config.xml设置dubbo消费者信息

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:dubbo="http://code.alibabatech.com/schema/dubbo"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://code.alibabatech.com/schema/dubbo
        http://code.alibabatech.com/schema/dubbo/dubbo.xsd ">


    <!-- 消费方应用名，用于计算依赖关系，不是匹配条件，不要与提供方一样 -->
    <dubbo:application name="consumer" />
    <!-- 使用multicast广播注册中心暴露发现服务地址 -->
    <dubbo:registry  protocol="zookeeper" address="zookeeper://123.206.121.72:2181" />
    <!-- 生成远程服务代理，可以和本地bean一样使用demoService -->
    <dubbo:reference id="testService" interface="com.ray.service.TestService" />

</beans>
```

7.将TestService的interface放入Customer中，一般来说是定义一个facade，放入jar包中

8.在Customer中Controller编写调用TestService的方法

```java
package com.ray.controller;


import com.ray.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "aaa")
public class HelloController {

    @Autowired
    private TestService testService;

    @RequestMapping("/test")
    public String test(){
        return testService.getName();
    }

}
```

9.注意端口冲突，因此要将application.properties中设置springboot 的启动端口

```shell
server.port=8081
```