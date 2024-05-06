# JDK17の公式Dockerイメージを使用
FROM openjdk:17-alpine

# Gradleで生成されたJARファイルをコピー
COPY ./task-management/task-management-0.0.1-SNAPSHOT.jar ./task-management.jar

# アプリケーションのポートを公開
EXPOSE 8080

# アプリケーションを起動
ENTRYPOINT  [ "java", "-jar", "/app/task-management.jar" ]
