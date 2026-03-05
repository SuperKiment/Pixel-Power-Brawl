FROM maven:3.9-eclipse-temurin-21

WORKDIR /app
COPY . .

RUN mvn clean package -DskipTests

CMD ["java","-jar","target/epsi-pixel-power-brawl-0.0.1-SNAPSHOT.jar"]
