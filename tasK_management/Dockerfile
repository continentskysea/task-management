# JDK17の公式Dockerイメージを使用
FROM eclipse-temurin:17:alpine

# 実行時にディレクトリを生成する
RUN mkdir /app

# Gradleで生成されたJARファイルをコピー
COPY ./build/libs/*.jar ./app/task-management.jar

# アプリケーションのポートを公開
EXPOSE 8080

# アプリケーションを起動
ENTRYPOINT  [ "java", "-jar", "/app/task-management.jar" ]
