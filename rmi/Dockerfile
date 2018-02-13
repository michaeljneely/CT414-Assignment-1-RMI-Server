FROM openjdk:8-jdk
COPY ./ ./usr/src/
WORKDIR /usr/src/
EXPOSE 1099
RUN rmiregistry &
RUN javac -cp :./junit.jar:./ct414.jar:./hamcrest.jar server/*.java
CMD ["java", "-cp", ":./junit.jar:./ct414.jar:./hamcrest.jar", "-Djava.rmi.server.codebase=159.65.23.223/ct414.jar", "-Djava.rmi.server.hostname=159.65.23.223", "-Djava.security.policy=./server.policy", "server.ExamEngine"]