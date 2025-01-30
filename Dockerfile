# Etapa de construcción
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app

# Copiar solo el pom.xml y descargar las dependencias
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiar el código fuente y construir el proyecto
COPY src /app/src
RUN mvn package -DskipTests

# Etapa de ejecución
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copiar el JAR construido desde la etapa de construcción
COPY --from=build /app/target/*.jar /app/app.jar

# Variables de entorno para la conexión a la base de datos
ENV SPRING_DATASOURCE_URL=${SPRING_DATASOURCE_URL}
ENV SPRING_DATASOURCE_USERNAME=${SPRING_DATASOURCE_USERNAME}
ENV SPRING_DATASOURCE_PASSWORD=${SPRING_DATASOURCE_PASSWORD}

# Puerto expuesto
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
