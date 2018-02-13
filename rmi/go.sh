docker build -t my-rmi --build-arg SYSTEM_IP=159.65.23.223 .
docker run -dit --net=host -p 159.65.23.223:1099:1099 -p 159.65.23.223:8080:80 my-rmi