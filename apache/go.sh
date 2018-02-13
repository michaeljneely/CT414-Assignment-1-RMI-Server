javac ../src/ct414/*.java
jar cvf ct414.jar ../src/ct414/*.class
docker build -t my-apache2 .
docker run -dit --name my-apache-app --net=host -p 8080:80 my-apache2