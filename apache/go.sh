javac ./ct414/*.java
jar cvf ct414.jar ./ct414/*.class
javac -cp ./ct414.jar ./server/*.java
jar cvf ct414server.jar ./server/*.class
docker build -t my-apache2 .
docker run -dit --name my-apache-app -p $1:80:80 my-apache2