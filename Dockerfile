FROM adoptopenjdk/openjdk11-openj9 AS base
ENV LANG C.UTF-8
WORKDIR /app

FROM adoptopenjdk/maven-openjdk11 AS build
WORKDIR /src
COPY . /src

RUN mvn -f /src/pom.xml clean install -U -DskipTests
RUN rm -f /src/target/*source.jar
RUN ls -lah /src/target
RUN cp -a /src/target/bdb-*.jar /app.jar

FROM base AS final
RUN rm -rf /var/cache/apk/*
COPY --from=build /app.jar .

CMD java -jar /app/app.jar