# ビルド用のイメージを取得(gradlew buildと同義)
FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /home/app
# Dockerが配置されているところにあるすべてのファイルをコピーする
COPY . .
# gradlew スクリプトに実行権限を付与
RUN chmod +x gradlew
# Dockerコンテナ起動時にビルドしjarファイルを生成
RUN ./gradlew bootJar

# 実行用のイメージ
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
# ビルド済みのjarファイルをコピー
COPY --from=build /home/app/build/libs/*.jar app.jar
# ポートを指定 (必要に応じて)
EXPOSE 8080
# jarファイルを実行
CMD ["java", "-jar", "app.jar"]