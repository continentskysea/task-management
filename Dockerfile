# JDK17の公式Dockerイメージを使用
FROM eclipse-temurin:17:alpine
# Gradleで生成されたJARファイルをコピー
COPY ./task-management-0.0.1-SNAPSHOT.jar /app/task_management.jar
# アプリケーションのポートを公開
EXPOSE 8080
# アプリケーションを起動
ENTRYPOINT  [ "java", "-jar", "/app/task_management.jar" ]
