FROM daocloud.io/library/java:8u40-b09
VOLUME /tmp
ADD docker-0.0.1-SNAPSHOT.jar app.jar
RUN touch -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]