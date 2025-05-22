# Stage 1: Build the application
FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Create the runtime image
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

ARG APP_USER=dimdimuser
ARG APP_GROUP=dimdimgroup
ARG UID=1001
ARG GID=1001

RUN groupadd -g ${GID} ${APP_GROUP} && \
    useradd -u ${UID} -g ${APP_GROUP} -ms /bin/bash ${APP_USER}

COPY --from=builder /app/target/*.jar app.jar

# Variáveis de ambiente padrão (podem ser sobrescritas com -e no docker run)
ENV SERVER_PORT=8080
ENV SPRING_DATASOURCE_URL="jdbc:oracle:thin:@//oracle-db-dimdim:1521/XEPDB1"
ENV SPRING_DATASOURCE_USERNAME="appuser_dimdim"
ENV SPRING_DATASOURCE_PASSWORD="apppassword_dimdim"
ENV JWT_SECRET="DefaultSecretJWTKeyFromDockerfile"
ENV JWT_EXPIRATION="86400000"
ENV SPRING_JPA_HIBERNATE_DDL_AUTO="create"

RUN chown ${APP_USER}:${APP_GROUP} /app && \
    chown ${APP_USER}:${APP_GROUP} app.jar

USER ${APP_USER}
EXPOSE ${SERVER_PORT}
ENTRYPOINT ["java", "-jar", "app.jar"]