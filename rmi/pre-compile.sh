curl https://search.maven.org/remotecontent?filepath=junit/junit/4.12/junit-4.12.jar
curl https://search.maven.org/remotecontent?filepath=org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar
cp ../src/server/* ./
javac -cp ../apache/ct414.jar:./junit-4.12.jar:./hamcrest-core-1.3.jar ./*.java