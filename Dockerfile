FROM maven:3-jdk-8-slim AS build

WORKDIR /usr/src/mymaven
COPY . .
RUN mvn package

FROM openjdk:8-jre-slim
COPY --from=build /usr/src/mymaven/target/scheduled-checkin-service-0.0.1-SNAPSHOT.jar /bin/scheduled-checkin-service.jar

COPY docker-entrypoint.sh /
RUN chmod +x /docker-entrypoint.sh
ENTRYPOINT ["/docker-entrypoint.sh"]
