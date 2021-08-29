FROM openjdk:11
ADD web/target/*.jar virtualZoo.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "virtualZoo.jar"]