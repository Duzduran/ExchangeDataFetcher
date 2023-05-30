FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ./target/DynatraceTask-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
WORKDIR /tmp
CMD java com.dynatraceTask.CurrencyExchangeAnalyzerApplication
