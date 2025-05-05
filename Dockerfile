# Steg 1: Använd en Java 21-bild (istället för en äldre version)
FROM openjdk:21-jdk-slim

# Steg 2: Sätt arbetskatalogen i containern
WORKDIR /app

# Steg 3: Kopiera den byggda JAR-filen till containern
COPY app/target/currency-converter-app-1.0-SNAPSHOT-jar-with-dependencies.jar /app/app.jar


# Steg 4: Exponera port 8080 (kan tas bort om inte relevant)
EXPOSE 8080

# Steg 5: Kör applikationen när containern startas
CMD ["java", "-jar", "/app/app.jar"]
