# Pixel-Power-Brawl

## To make this project work

In `pixel-power-ng/src/app`, create a `environment.ts` from the `environment.copy.ts` and replace the API informations.

Example :
```
export const API_URL = 'localhost:8081/';
```

---

In `epsi-pixel-power-brawl/src/main/resources`, create a `application.properties` and complete the informations

Example :
```
spring.application.name=epsi-pixel-power-brawl

spring.datasource.url=jdbc:postgresql://postgres:5432/pixelpowerbrawl
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.datasource.driver-class-name=org.postgresql.Driver

server.port=8081

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

jwt.secret=VGhpc0lzQVNlY3VyZTI1NkJpdEtleUZvckpXVEhTMjU2
```

---

The examples work with the base docker-compose.yml file.
To build :
```
docker compose build
```
To run :
```
docker compose up
```

The frontend will be accessible on the `4200` port and the API on `8081`.
