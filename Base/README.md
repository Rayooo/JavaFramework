### 坑点

Mapper.xml一定要放在resources中，因为maven项目的约定配置文件必须放resources里，src目录下的xml文件默认不会编译到target。

当我配置好所有的pagehelper后，发现还是不能分页，因为在spring boot下，需要引入pagehelper-spring-boot-starter，之后即可

```xml
<dependency>
  	<groupId>com.github.pagehelper</groupId>
  	<artifactId>pagehelper-spring-boot-starter</artifactId>
  	<version>1.1.1</version>
</dependency>		
```

