#!/bin/sh

BBS_NETWORK='bbs_nw'

BBS_DOCKER_FILE='Dockerfile'
BBS_IMAGE_NAME='bbs-image'

BBS_MASTER_DOCKER_FILE='Dockerfile-master'
BBS_MASTER_IMAGE_NAME='bbs-master-image'
BBS_MASTER_CONTAINER='bbs_master'

echo "==================================================="
echo "Compiling java files .."
javac src/bbs/*.java
echo "Done"

echo "==================================================="
echo "Creating isolated network .."

if [ ! "$(docker network ls | grep bbs_nw)" ] ; then
	docker network create -d bridge $BBS_NETWORK
	echo "Created."
else
	echo "Network already exist."
fi

echo "==================================================="
echo "Buiding Dockerfile-master .."
docker build -t $BBS_MASTER_IMAGE_NAME -f $BBS_MASTER_DOCKER_FILE .
echo "Done."


echo "==================================================="
echo "Creating docker containers for master machine .."
docker run --rm -itd --network $BBS_NETWORK --name=$BBS_MASTER_CONTAINER $BBS_MASTER_IMAGE_NAME

SSH_KEY=$(docker exec $BBS_MASTER_CONTAINER	cat /root/.ssh/id_ecdsa.pub)
rm ./ssh/authorized_keys
echo $SSH_KEY >> ./ssh/authorized_keys
echo "Done."


echo "==================================================="
echo "Buiding Dockerfile .."
docker build -t $BBS_IMAGE_NAME -f $BBS_DOCKER_FILE .
echo "Done."

echo "==================================================="
echo "Creating docker container for server .."
SERVER_HOST=$(cat src/bbs/system.properties | grep 'RW.server=' | awk -F'=' '{ print $2 }')
docker run --rm -itd --network $BBS_NETWORK --name=$SERVER_HOST $BBS_IMAGE_NAME
echo "Done."

echo "==================================================="
echo "Creating docker containers for clients .."
cat src/bbs/system.properties | grep -E 'RW.reader|RW.writer' | awk -F'=' '{ print $2 }' | xargs -I {} docker run --rm -itd --network $BBS_NETWORK --name={} $BBS_IMAGE_NAME
echo "Done."

echo "==================================================="
docker ps
echo "==================================================="

echo "Starting .."
docker exec -it $BBS_MASTER_CONTAINER java bbs/Start
echo "Sleeping 20s until all clients are done .."
sleep 20 # until all clients are done
echo "Done."

echo "==================================================="
echo "SERVER READERS LOGS"
docker exec -it $SERVER_HOST cat /home/src/server_readers.txt
echo "==================================================="
echo "SERVER WRITERS LOGS"
docker exec -it $SERVER_HOST cat /home/src/server_writers.txt
