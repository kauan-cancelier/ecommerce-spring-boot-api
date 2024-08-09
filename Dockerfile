FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/ecommerce-spring-boot-api-1.0-SNAPSHOT.jar ecommerce-spring-boot-api.jar
ENTRYPOINT ["java","-jar","/ecommerce-spring-boot-api.jar"]
