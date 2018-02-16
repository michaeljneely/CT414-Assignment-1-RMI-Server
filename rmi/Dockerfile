FROM openjdk:8-jdk
ARG SYSTEM_IP
ENV SYSTEM_IP=$SYSTEM_IP
COPY ./ ./usr/src/
WORKDIR /usr/src/
RUN curl https://search.maven.org/remotecontent?filepath=junit/junit/4.12/junit-4.12.jar > junit.jar
RUN curl https://search.maven.org/remotecontent?filepath=org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar > hamcrest.jar
RUN curl $SYSTEM_IP/ct414.jar > ct414.jar
EXPOSE 1099
EXPOSE 80
RUN rmiregistry &
RUN javac -cp :./junit.jar:./ct414.jar:./hamcrest.jar server/*.java
RUN javac -cp :./junit.jar:./ct414.jar:./hamcrest.jar test/*.java
RUN java -cp :./ct414.jar:./junit.jar:./hamcrest.jar org.junit.runner.JUnitCore test.IntegrationTest
CMD java -cp :./junit.jar:./ct414.jar:./hamcrest.jar -Djava.rmi.server.codebase=${SYSTEM_IP}/ct414.jar -Djava.rmi.server.hostname=${SYSTEM_IP} -Djava.security.policy=./server.policy server.ExamEngine