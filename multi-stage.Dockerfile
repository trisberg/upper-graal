FROM oracle/graalvm-ce:20.0.0-java8 AS build
WORKDIR /app
COPY . .
RUN ./mvnw -Pgraal clean package
FROM ubuntu:bionic
RUN useradd spring
USER spring
COPY --from=build /app/target/com.example.upper.upperapplication /upper
ENTRYPOINT ["/upper"]
