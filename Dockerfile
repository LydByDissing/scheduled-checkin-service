FROM maven:3-jdk-11-slim AS build

COPY . /usr/src/mymaven
RUN mvn package

FROM openjdk:11-jre-slim-alpine
COPY --from=build /usr/src/mymaven/target/scheduled-checkin-service-0.0.1-SNAPSHOT.jar /bin/scheduled-checkin-service.jar

COPY docker-entrypoint.sh /
RUN chmod +x /docker-entrypoint.sh
ENTRYPOINT ["/docker-entrypoint.sh"]
