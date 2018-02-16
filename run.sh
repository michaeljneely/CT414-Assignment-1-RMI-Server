DESIGNATED_IP=0.0.0.0
cd apache
bash build-apache.sh $DESIGNATED_IP
cd ../rmi
bash build-rmi.sh $DESIGNATED_IP