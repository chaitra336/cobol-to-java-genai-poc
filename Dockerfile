Dockerfile
```dockerfile
FROM openjdk:17-jre-slim
WORKDIR /app
COPY 02_modern_java/*.java .
RUN javac AccountService.java
EXPOSE 8080
CMD ["java", "AccountService"]
