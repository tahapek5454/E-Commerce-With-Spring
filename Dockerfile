FROM openjdk:18
WORKDIR /app
ADD target/secondhand-0.0.1-SNAPSHOT.jar secondhand-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "secondhand-0.0.1-SNAPSHOT.jar"]

