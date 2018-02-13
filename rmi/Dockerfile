FROM openjdk:8-jdk
EXPOSE 1099
RUN rmiregistry &
CMD ["java", "-cp", ":./*.jar", "-Djava.rmi.server.codebase=159.65.23.223/ct414.jar", "-Djava.rmi.server.hostname=159.65.23.223", "-Djava.security.policy=./server.policy", "./ExamEngine"]