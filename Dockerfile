FROM openjdk:11-jre-slim

ENV JAVA_ENV=DEVELOPMENT
ENV KUMULUZEE_ENV_NAME=prod
ENV KUMULUZEE_ENV_PROD=true
ENV KUMULUZEE_DATASOURCES0_CONNECTIONURL=jdbc:postgresql://localhost:5432/analysis
ENV KUMULUZEE_DATASOURCES0_USERNAME=postgres
ENV KUMULUZEE_DATASOURCES0_PASSWORD=postgres

RUN mkdir /app
WORKDIR /app

ADD ./api/target/analysis.jar /app

EXPOSE 8080

CMD ["java", "-jar", "analysis.jar", "com.kumuluz.ee.EeApplication"]