DESIGNATED_IP=0.0.0.0
cd apache
bash go.sh $DESIGNATED_IP
cd ../rmi
bash go.sh $DESIGNATED_IP