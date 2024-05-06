# JDK17の公式Dockerイメージを使用
FROM eclipse-temurin:17

# アプリケーションのファイルをコピー
COPY ./build/libs/spring-render-deploy-0.0.1-SNAPSHOT.jar /app/spring-render-deploy.jar

# アプリケーションのポートを公開
EXPOSE 8080

# アプリケーションを起動
CMD [ "java", "-jar", "/app/spring-render-deploy.jar" ]
