# ビルド用のイメージを取得(gradlew buildと同義)
FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /home/app
# Dockerが配置されているところにあるすべてのファイルをコピーする
COPY . .
# gradlew スクリプトに実行権限を付与
RUN chmod +x  gradlew 
# Dockerコンテナ起動時にビルドしjarファイルを生成
RUN sh ./gradlew bootJar

FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /home/app/build/libs/*.jar app.jar
# ポートを指定
EXPOSE 8080

# アプリケーションの実行
ENTRYPOINT [ "java", "-jar", "app.jar" ]