cd ../apache/
javac ct414/*.java
jar cvf ct414.jar ct414/*.class
cd ../rmi/
curl https://search.maven.org/remotecontent?filepath=junit/junit/4.12/junit-4.12.jar > junit.jar
curl https://search.maven.org/remotecontent?filepath=org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar > hamcrest.jar
javac -cp :../apache/ct414.jar:./junit.jar:./hamcrest.jar server/*.java
javac -cp :../apache/ct414.jar:./junit.jar:./hamcrest.jar test/*.java
java -cp :../apache/ct414.jar:./junit.jar:./hamcrest.jar org.junit.runner.JUnitCore test.IntegrationTest