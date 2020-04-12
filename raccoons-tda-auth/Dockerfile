FROM openjdk:11-jre-slim

ENV TDA_AUTH_ENDPOINT_AUTH=https://auth.tdameritrade.com/auth               \
    TDA_AUTH_ENDPOINT_TOKEN=https://api.tdameritrade.com/v1/oauth2/token    \
    TDA_AUTH_TOKEN_EXPIRATION=1800000           \
    TDA_AUTH_REDIS_URI=redis://redis:6379       \
    TDA_AUTH_REFRESH_ENABLE=true                \
    TDA_AUTH_REFRESH_FREQUENCY=30000            \
    TDA_AUTH_REFRESH_TIME_THRESHOLD=180000      \
    TDA_AUTH_REFRESH_THRESHOLD=0.25             \
    TDA_AUTH_MAPPING_CALLBACK=auth/callback     \
    TDA_AUTH_MAPPING_LOGIN=login

#    TDA_AUTH_DATASOURCE_JDBC_URI=jdbc:postgresql://localhost:5432/
#    TDA_AUTH_DATASOURCE_USERNAME=postgres
#    TDA_AUTH_DATASOURCE_PASSWORD=password

EXPOSE 8080

COPY ./raccoons-tda-api/build/libs/*.jar /app/libs/raccoons-tda-api.jar
COPY ./raccoons-tda-auth-lib/build/libs/*.jar /app/libs/raccoons-tda-auth-lib.jar
COPY ./raccoons-tda-auth/build/libs/*.jar /app/raccoons-tda-auth-service.jar

COPY docker-entrypoint.sh /usr/local/bin/
RUN ln -s usr/local/bin/docker-entrypoint.sh / # backwards compat

ENTRYPOINT ["docker-entrypoint.sh"]

ENTRYPOINT ["java", \
            "-XX:+UnlockExperimentalVMOptions", \
            "-XX:+UseCGroupMemoryLimitForHeap", \
            "-Djava.security.egd=file:/dev/./urandom", \
            "-cp", \
            "./app/libs/*:/app/raccoons-tda-auth-service.jar", \
            "com.raccoons.tda.auth.TDAAuthService"]