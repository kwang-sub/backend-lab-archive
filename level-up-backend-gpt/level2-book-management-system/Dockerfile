FROM openjdk:17-jdk-slim

WORKDIR /app

# 빌드 jar 파일명으로 변경 필요
COPY build/libs/level2-book-management-system-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]