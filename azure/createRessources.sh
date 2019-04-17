#!/bin/bash

# Create a resource group
az group create \
--name mwe-monitoring \
--location westeurope

# Create the Kubernetes Cluster
az aks create \
--resource-group=mwe-monitoring \
--name=monitoring-cluster \
--dns-name-prefix=monitoring \
--node-count=1 \
--generate-ssh-keys \
--kubernetes-version=1.12.7 \
--location=westeurope

# install kubectl for azure cli
sudo az aks install-cli
# set the cluster as the current instance for kubectl
az aks get-credentials --resource-group=mwe-monitoring --name=monitoring-cluster

# Create the namespace and application ressources from the config files
kubectl create ns todo-application
# kubectl apply -f ./../kubernetes/