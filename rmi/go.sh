docker build -t my-rmi --build-arg SYSTEM_IP=$1 .
docker run -dit --net=host -p $1:1099:1099 my-rmi