# Build jar
FROM gradle:7.1-jdk11 AS build
WORKDIR /project
COPY build.gradle.kts ./
COPY settings.gradle.kts ./
COPY gradle.properties ./
COPY src ./src
RUN gradle shadowJar

# Start
FROM openjdk:11
WORKDIR /app
COPY --from=build /project/build/libs/urban-dictionary-app.jar ./app.jar
CMD ["java", "-jar", "app.jar"]
