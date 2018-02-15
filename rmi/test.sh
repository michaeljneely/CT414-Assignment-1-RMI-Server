cd ../apache/
javac ct414/*.java
jar cvf ct414.jar ct414/*.class
cd ../rmi/
javac -Xlint:unchecked -cp :../apache/ct414.jar:./junit.jar:./hamcrest.jar server/*.java
javac -Xlint:unchecked -cp :./../apache/ct414.jar:./junit.jar:./hamcrest.jar test/*.java
java -cp :./ct414.jar:./junit.jar:./hamcrest.jar org.junit.runner.JUnitCore test.IntegrationTest