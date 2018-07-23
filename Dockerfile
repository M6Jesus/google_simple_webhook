FROM java:8-jdk
ADD target/google_webhook.jar google_webhook.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/google_webhook.jar"]