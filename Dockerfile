FROM ubuntu:bionic
RUN useradd spring
USER spring
COPY ./target/com.example.upper.upperapplication /upper
ENTRYPOINT ["/upper"]
