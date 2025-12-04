# =============================
# Stage 1 — Build com Maven
# =============================
FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

COPY pom.xml mvnw ./
COPY .mvn .mvn

RUN ./mvnw dependency:go-offline -B

COPY src ./src

RUN ./mvnw clean package -DskipTests

# =============================
# Stage 2 — Runtime leve (JRE)
# =============================
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]