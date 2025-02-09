FROM openjdk:11
WORKDIR /app
COPY src /app/src
COPY data /app/data
RUN javac -d out src/*.java
CMD ["java", "-cp", "out", "App"]