FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ENV spring.datasource.url=jdbc:mysql://sql.freedb.tech:3306/freedb_ehcare
ENV spring.datasource.username=freedb_sql12629433
ENV spring.datasource.password=7%Nb6gFhfNJPn8y
ENV spring.jpa.hibernate.ddl-auto=update
ENV spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
ENV spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect


ENV spring.devtools.restart.enabled=true
ENV server.port=8080

ENV jwt.secret=HA0zIJoydY30zSqdcYzqKWIsPWEnJkpRlfaC0sq8piL50bQYUnGBoByAp6kuPJn


COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080