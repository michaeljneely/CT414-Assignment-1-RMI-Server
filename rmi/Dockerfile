FROM ubuntu

RUN apt-get install -y
RUN apt-get update
RUN apt-get install -y openjdk-8-jdk wget
RUN apachectl -k start
COPY ./ /usr/src
WORKDIR /usr/src/src/
RUN wget https://search.maven.org/remotecontent?filepath=junit/junit/4.12/junit-4.12.jar -O junit.jar
RUN wget https://search.maven.org/remotecontent?filepath=org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar -O hamcrest.jar
RUN javac ct414/*.java
RUN jar cvf ct414.jar ct414/*.class
EXPOSE 1099
RUN rmiregistry &
RUN javac -cp ./ct414.jar:./junit.jar server/*.java
CMD ["java", "-cp", ":./*.jar", "server/ExamEngine"]
# CMD ["java", "-cp", ":./*.jar", "-Djava.rmi.server.hostname=127.0.0.1", "-Djava.security.policy=../server.policy", "server/ExamEngine"]
#CMD ["java", "-cp", ":./*.jar", "-Djava.rmi.server.codebase=./ct414.jar", "-Djava.rmi.server.hostname=localhost", "-Djava.security.policy=../server.policy", "server/ExamEngine"]
#RUN java -cp :./*.jar -Djava.rmi.server.codebas=./ct414.jar -Djava.security.policy=../server.policy server/ExamEngine
#RUN javac -cp ./ct414.jar:./junit.jar server/*.java