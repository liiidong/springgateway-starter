FROM openjdk8:zh
MAINTAINER gafci "liiidong@126.com"
VOLUME /tmp
ADD service-tenant-gateway-1.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar","--spring.profiles.active=prod"]