# FROM maven:3.8.2-jdk-8 # for Java 8
# FROM maven:3.8.5-openjdk-17
FROM maven:3.8.5-jdk-11-slim

WORKDIR /lift
COPY . .
RUN mvn clean install

CMD mvn spring-boot:run