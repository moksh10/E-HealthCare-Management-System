FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ENV spring.datasource.url=jdbc:postgres://root:cAbJ9vwbMaryu9B6oXeA2vlWBHqheUkp@dpg-cieidgunqql22egh2teg-a.oregon-postgres.render.com/ehcare
ENV spring.datasource.username=root
ENV spring.datasource.password=cAbJ9vwbMaryu9B6oXeA2vlWBHqheUkp
ENV spring.jpa.hibernate.ddl-auto=update
ENV spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.PostgreSQLDialect
ENV spring.devtools.restart.enabled = true
ENV server.port=8080
ENV spring.jpa.hibernate.show-sql=true
ENV jwt.secret=HA0zIJoydY30zSqdcYzqKWIsPWEnJkpRlfaC0sq8piL50bQYUnGBoByAp6kuPJn

COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080