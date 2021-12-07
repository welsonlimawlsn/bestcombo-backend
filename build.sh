#!/bin/bash

./mvnw package

sudo -S docker build -f src/main/docker/Dockerfile.jvm -t backend-bestcombo .

#sudo docker tag backend-bestcombo gcr.io/melodic-splicer-331419/backend-bestcombo
sudo docker tag backend-bestcombo:latest 692705717345.dkr.ecr.us-east-2.amazonaws.com/backend:latest

aws ecr get-login-password --region us-east-2 | sudo docker login --username AWS --password-stdin 692705717345.dkr.ecr.us-east-2.amazonaws.com
#./bin/gcloud auth print-access-token | sudo docker login -u oauth2accesstoken --password-stdin https://gcr.io

sudo docker push 692705717345.dkr.ecr.us-east-2.amazonaws.com/backend:latest
