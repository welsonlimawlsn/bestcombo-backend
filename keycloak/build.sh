#!/bin/bash

sudo docker build . -t keycloak-bestcombo

#sudo docker tag keycloak-bestcombo gcr.io/melodic-splicer-331419/keycloak-bestcombo
sudo docker tag keycloak-bestcombo:latest 692705717345.dkr.ecr.us-east-2.amazonaws.com/keycloak:latest

#/home/welson/devtools/google-cloud-sdk/bin/gcloud auth print-access-token | sudo docker login -u oauth2accesstoken --password-stdin https://gcr.io
aws ecr get-login-password --region us-east-2 | sudo docker login --username AWS --password-stdin 692705717345.dkr.ecr.us-east-2.amazonaws.com

sudo docker push 692705717345.dkr.ecr.us-east-2.amazonaws.com/keycloak:latest
