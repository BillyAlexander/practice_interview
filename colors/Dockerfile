FROM eclipse-temurin:17-jre
WORKDIR /app

# Copia el JAR compilado
COPY target/colors-0.0.1-SNAPSHOT.jar app.jar


# Variables de entorno 

EXPOSE 8082

CMD ["java", "-jar", "app.jar"]