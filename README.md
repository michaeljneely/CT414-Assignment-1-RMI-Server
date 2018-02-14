# CT414 Assignment 1 - Java Remote Method Invocation - Server

> Complete the implementation of a Java RMI based Assessment System for the university.  The server allows clients to authenticate and download an Assessment object.  The Assessment object implements an interface that provides methods to retrieve and answer a list of multiple-choice questions. The Assessment is completed on the client and the updated Assessment object can then be submitted back to the server for correction.

## The process

This server is based on two docker images:
 - [Apache2](https://hub.docker.com/_/httpd/)
 - [Java](https://hub.docker.com/_/openjdk/)

The server begins by starting apache to server the common interfaces in 'ct414.jar'. It then begins the java rmi server on port 1099 and exports and binds the server object.

## Prerequisites
- Linux server (preferably Ubuntu)
- Docker :whale: (``sudo apt-get install docker-ce``)
- Git (``sudo apt-get install git``)

## Instructions
- Clone this repo
- Run ``clean.sh`` to stop and remove all running docker images
- Edit the ``DESIGNATED_IP`` variable in ``run.sh`` to match your server's IP address.
- Run ``run.sh``