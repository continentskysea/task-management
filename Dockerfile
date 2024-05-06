# JDK17の公式Dockerイメージを使用
FROM openjdk:17-alpine

# Gradleで生成されたJARファイルをコピー
COPY ./build/libs/task-management-0.0.1-SNAPSHOT.jar /app/task-management.jar

# アプリケーションのポートを公開
EXPOSE 8080

# アプリケーションを起動
CMD [ "java", "-jar", "/app/spring-render-deploy.jar" ]
