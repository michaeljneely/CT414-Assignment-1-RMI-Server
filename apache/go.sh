javac ./ct414/*.java
jar cvf ct414.jar ./ct414/*.class
docker build -t my-apache2 .
docker run -dit --name my-apache-app -p $1:80:80 my-apache2