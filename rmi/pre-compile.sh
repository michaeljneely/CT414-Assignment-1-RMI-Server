docker build -t my-rmi .
docker run -dit --name my-rmi --net=host -p 1099:1099 my-rmi