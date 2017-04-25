



### Spring Session

使用 spring session 将session放在redis中

引入依赖

```xml

        <dependency>
            <groupId>org.springframework.data</groupId>
            <artifactId>spring-data-redis</artifactId>
            <version>1.8.3.RELEASE</version>
        </dependency>
        <dependency>
			<groupId>biz.paluch.redis</groupId>
			<artifactId>lettuce</artifactId>
			<version>4.3.1.Final</version>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-aop</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.session</groupId>
			<artifactId>spring-session</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
```

配置LettuceConnectionFactory，spring会将默认的session储存在redis中，可以实现分布式session

```java
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableRedisHttpSession
public class Config {

    @Bean
    public LettuceConnectionFactory connectionFactory() {
        LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory();
        connectionFactory.setHostName("localhost");
        connectionFactory.setPort(6379);
        return connectionFactory;
    }
}
```

继承 AbstractHttpSessionApplicationInitializer 加载配置

```java
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

public class Initializer extends AbstractHttpSessionApplicationInitializer {

    public Initializer() {
        super(Config.class);
    }

}
```

在Controller中写get，set方法

```java
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class Controller {

    @RequestMapping("/set")
    public String set(HttpServletRequest request){
        request.getSession().setAttribute("testKey","testValue");
        return "设置session testKey,testValue";
    }

    @RequestMapping("/query")
    public String query(HttpServletRequest request){
        Object value = request.getSession().getAttribute("testKey");
        return "查询 Session testKey = " + value;
    }

}
```

首先清空redis缓存

```powershell
127.0.0.1:6379> flushall
OK
```

请求localhost:8080/set后，查看redis

```shell
127.0.0.1:6379> keys *
1) "spring:session:sessions:expires:987a1e9a-70bb-4ee3-a58b-80191590c58e"
2) "spring:session:expirations:1493111700000"
3) "spring:session:sessions:987a1e9a-70bb-4ee3-a58b-80191590c58e"
```

可以查看其session内容

```shell
127.0.0.1:6379> HGETALL spring:session:sessions:e93a2f45-5e0e-44bd-bebb-430ca9e4635f
1) "maxInactiveInterval"
2) "\xac\xed\x00\x05sr\x00\x11java.lang.Integer\x12\xe2\xa0\xa4\xf7\x81\x878\x02\x00\x01I\x00\x05valuexr\x00\x10java.lang.Number\x86\xac\x95\x1d\x0b\x94\xe0\x8b\x02\x00\x00xp\x00\x00\a\b"
3) "creationTime"
4) "\xac\xed\x00\x05sr\x00\x0ejava.lang.Long;\x8b\xe4\x90\xcc\x8f#\xdf\x02\x00\x01J\x00\x05valuexr\x00\x10java.lang.Number\x86\xac\x95\x1d\x0b\x94\xe0\x8b\x02\x00\x00xp\x00\x00\x01[\xa4I\x86\x88"
5) "sessionAttr:testKey"
6) "\xac\xed\x00\x05t\x00\ttestValue"
7) "lastAccessedTime"
8) "\xac\xed\x00\x05sr\x00\x0ejava.lang.Long;\x8b\xe4\x90\xcc\x8f#\xdf\x02\x00\x01J\x00\x05valuexr\x00\x10java.lang.Number\x86\xac\x
```

当flushall后，请求localhost:8080/query 后，会显示testKey为null，证明spring将session放入了redis中