curl https://search.maven.org/remotecontent?filepath=junit/junit/4.12/junit-4.12.jar > junit.jar
curl https://search.maven.org/remotecontent?filepath=org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar > hamcrest.jar
javac -cp ../apache/ct414.jar:./junit.jar:./hamcrest.jar ./*.java
docker build -t my-rmi .
docker run -dit --name my-rmi --net=host -p 1099:1099 my-rmi