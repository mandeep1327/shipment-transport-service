FROM ghcr.io/graalvm/graalvm-ce:22.1.0
ARG JAR_FILE=target/shipment-loading-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-javaagent:/app/dd-java-agent.jar", "-XX:InitialRAMPercentage=30.0", "-XX:MaxRAMPercentage=50.0", "-jar","/app/app.jar"]