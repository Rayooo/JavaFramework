### 使用Docker来运行Spring Boot应用

1.先写一个Hello World的 Spring Boot 应用

2.在项目根目录执行 

```shell
$ mvn package
```

3.然后尝试运行这个jar包

```shell
$ java -jar target/docker-0.0.1-SNAPSHOT.jar
```

4.在地址栏输入localhost:8080看看是否成功

5.编写Dockerfile

```dockerfile
FROM daocloud.io/library/java:8u40-b09
VOLUME /tmp
ADD docker-0.0.1-SNAPSHOT.jar app.jar
RUN touch -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
```

6.build & run

```shell
$ sudo docker build -t hello .
$ sudo docker run -d -p 8080:8080 hello
```

7.VOLUME 映射了/tmp 因为这是Spring Boot 应用为Tomcat创建默认工作目录的地方，这个作用是为了创建一个临时文件在主机目录 /var/lib/docker 下 ，并且链接到容器内部 /tmp 文件，这一步对于这个helloworld是可选的，但是这对于其他Spring boot项目如果有写文件操作的话是很有必要的，