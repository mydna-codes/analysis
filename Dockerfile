FROM openjdk:11-jre-slim

ENV KUMULUZEE_VERSION=not_set
ENV KUMULUZEE_ENV_NAME=dev
ENV KUMULUZEE_ENV_PROD=false
ENV KUMULUZEE_GRPC_CLIENTS0_PORT=8081
ENV KUMULUZEE_GRPC_CLIENTS0_ADDRESS=localhost

RUN mkdir /app
WORKDIR /app

ADD ./api/target/analysis.jar /app

EXPOSE 8080

CMD ["java", "-jar", "analysis.jar", "com.kumuluz.ee.EeApplication"]