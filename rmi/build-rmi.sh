docker build -t rmiserver --build-arg SYSTEM_IP=$1 .
docker run -dit --rm --name my-rmi --restart always --net=host -p $1:1099:1099 rmiserver