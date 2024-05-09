# JDK17の公式Dockerイメージを使用
FROM eclipse-eclipse-temurin:17

# 実行時にディレクトリを生成する
RUN mkdir /app

# Gradleで生成されたJARファイルをコピー
COPY ./tasK_management/build/libs/task_task-management-0.0.1-SNAPSHOT.jar ./tasK_management/app/task-management.jar

# アプリケーションのポートを公開
EXPOSE 8080

# アプリケーションを起動
CMD  [ "java", "-jar", "/app/task-management.jar" ]
