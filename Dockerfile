# ビルド用のイメージを取得(gradlew buildと同義)
FROM eclipse-temurin:17-jdk-alpine AS build
# Dockerが配置されているところにあるすべてのファイルをコピーする
COPY . /home/app
# Dockerコンテナ起動時にビルドしjarファイルを生成
RUN cd /home/app && ./gradlew build

FROM eclipse-temurin:17-jdk-alpine
COPY --from=build /home/app/build/libs/task_management-0.0.1-SNAPSHOT.jar /usr/local/lib/task_management-render.jar
# ポートを指定
EXPOSE 8080

# アプリケーションの実行
ENTRYPOINT [ "java", "-jar", "-Dfile.encoding=UTF-8",　"task_management-0.0.1-SNAPSHOT.jar" ]