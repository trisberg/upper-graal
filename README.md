# upper-graal

POC to build SpringCloudFunction as GraalVM native-image and to be deployed to Knative.

## Prerequisites

- [GraalVM](https://www.graalvm.org/) version `ce-java8-20.0.0`

Built with the [spring-graal-native](https://github.com/spring-projects-experimental/spring-graal-native) on Ubuntu 18.04.

If you see an error indicating that `libz.a` is missing then install the following:

```
sudo apt-get install zlib1g-dev
```

## Setup

```
export JAVA_HOME=/home/trisberg/bin/graalvm-ce-java8-20.0.0
export PATH=${JAVA_HOME}/bin:${PATH}
```

## Build

```
./mvnw -Pgraal clean package
```

```
docker build . -t trisberg/upper-graal
docker push trisberg/upper-graal
```

## Deploy

```
kubectl apply -f knative-service.yaml
```
