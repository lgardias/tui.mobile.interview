FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

COPY build/libs/TuiMobileInterview-1.0-SNAPSHOT.jar tui.jar

EXPOSE 8080

CMD ["java", "-jar", "tui.jar"]