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

## Invoke

This is a Knative Serving install with Kourier as the ingress.

```
INGRESS=$(kubectl get --namespace kourier-system service/kourier -o jsonpath='{.status.loadBalancer.ingress[0].ip}')
curl $INGRESS -H "Host: upper.default.example.com" -H "Content-Type: text/plain" -d hello && echo
```

## Logs

```
$ kubectl logs deployment/upper-9vrgf-deployment -c user-container

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                        

Apr 18, 2020 10:28:08 AM org.springframework.boot.StartupInfoLogger logStarting
INFO: Starting UpperApplication on upper-9vrgf-deployment-67bc9fcf5c-28nrl with PID 1 (/upper started by spring in /)
Apr 18, 2020 10:28:08 AM org.springframework.boot.SpringApplication logStartupProfileInfo
INFO: No active profile set, falling back to default profiles: default
Apr 18, 2020 10:28:08 AM org.springframework.cloud.function.web.flux.FunctionHandlerMapping <init>
INFO: FunctionCatalog: org.springframework.cloud.function.context.catalog.BeanFactoryAwareFunctionRegistry@7fa8254336d8
2020-04-18 10:28:08.645  WARN 1 --- [           main] io.netty.channel.DefaultChannelId        : Failed to find the current process ID from ''; using a random value: 1605631008
Apr 18, 2020 10:28:08 AM org.springframework.boot.web.embedded.netty.NettyWebServer start
INFO: Netty started on port(s): 8080
Apr 18, 2020 10:28:08 AM org.springframework.boot.StartupInfoLogger logStarted
INFO: Started UpperApplication in 0.05 seconds (JVM running for 0.053)
Apr 18, 2020 10:28:09 AM org.springframework.cloud.function.context.catalog.BeanFactoryAwareFunctionRegistry compose
INFO: Looking up function 'null' with acceptedOutputTypes: []
```