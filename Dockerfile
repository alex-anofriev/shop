FROM openjdk:17-oracle
ADD target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
#"--spring.profiles.active=docker"