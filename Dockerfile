# build段階
FROM eclipse-temurin:17:alpine AS build
COPY ./ /home/app
RUN cd /home/app && ./gradlew build
RUN cd /home/app && ./gradlew bootJar


# パッケージ段階
# JDK17の公式Dockerイメージを使用
FROM eclipse-temurin:17:alpine
# Gradleで生成されたJARファイルをコピー
COPY --from=build /home/app/build/libs/task-management-0.0.1-SNAPSHOT.jar /usr/local/lib/task_management.jar
# アプリケーションのポートを公開
EXPOSE 8080
# アプリケーションを起動
ENTRYPOINT  [ "java", "-jar", "-Dfile.encoding=UTF-8", "/usr/local/lib/task_management.jar" ]
