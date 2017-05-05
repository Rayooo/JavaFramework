### 坑点

Mapper.xml一定要放在resources中，因为maven项目的约定配置文件必须放resources里，src目录下的xml文件默认不会编译到target。如果一定要放在src文件夹下，可以在pom.xml中build节点中加入，并且在mybatis-config.xml中配置mapper.xml路径

```xml
<!--pom.xml-->
<!--防止intellij等在Source目录下忽略.xml文件，防止编译不加入classes-->
<resources>
   <resource>
      <directory>src/main/java</directory>
      <includes>
         <include>**/*Mapper.xml</include>
      </includes>
   </resource>
</resources>

<!--mybatis-config.xml-->
<bean id="commonSqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
  	<property name="configLocation" value="classpath:mybatis-config.xml" />
  	<property name="dataSource" ref="dataSource" />
  	<!-- 自动扫描mapping.xml文件,这里的路径主要是target中编译完的路径 -->
  	<property name="mapperLocations" value="classpath*:**/mapper/*Mapper.xml" />
</bean>
```

当我配置好所有的pagehelper后，发现还是不能分页，因为在spring boot下，需要引入pagehelper-spring-boot-starter，之后即可

```xml
<dependency>
  	<groupId>com.github.pagehelper</groupId>
  	<artifactId>pagehelper-spring-boot-starter</artifactId>
  	<version>1.1.1</version>
</dependency>		
```

