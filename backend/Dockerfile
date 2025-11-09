# Etapa 1: Build com Maven
FROM maven:3.8-openjdk-17-slim AS build
WORKDIR /app
COPY . /app
RUN mvn clean package -DskipTests

# Etapa 2: Imagem final
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app

# Copia o JAR do build
COPY --from=build /app/target/*.jar /app/app.jar

# Copia o wait-for-it.sh e torna executável
COPY wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh

# Instala netcat para wait-for-it
RUN apt-get update && apt-get install -y netcat && rm -rf /var/lib/apt/lists/*

EXPOSE 8080

# Usa wait-for-it antes de iniciar o Spring Boot
ENTRYPOINT ["/wait-for-it.sh", "mysql:3306", "--", "java", "-jar", "/app/app.jar"]
