docker build -t my-rmi --build-arg SYSTEM_IP=$1 .
docker run -dit -p my-rmi