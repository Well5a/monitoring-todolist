# monitoring-todolist
Modification of todolist app for use as a microservice in bachelor thesis "Monitoring Microservices in the Cloud".

original app: https://github.com/Well5a/vertsys-todolist

## Changes
* uses PostgreSQL database instead of MySQL
* updated to latest Spring Boot release (currently 2.1.3)
* changed server port to 5555
* implemented monitoring tool chain with micrometer, prometheus and grafana
* implemented InspectIT Ocelot for metric collection and exposure
* added Kubernetes config files

## Build the app
```
mvn clean package
```

## App usage (mappings)
* http://localhost:5555/ui for list of todos
* `curl -X POST localhost:5555/task` to add a task to the list
* `curl -X DELETE localhost:5555/id` to delete a task from the list

## Start the app with Docker-Compose
* build the app
* build image of the app: execute `docker build -t todolist .` in project root directory
* run: 'docker-compose up' in root directory to start the app

## Metrics Monitoring
* Prometheus: http://localhost:9090
* Grafana: http://localhost:3000
* Grafana login: user=admin password=todo

### Add datasources in Grafana
Dashboards and datasources are added and updated automatically through config files in the folder "grafana>provisioning".
If you need to add datasources manually these are the necessary credentials:
* Prometheus: URL=http://prometheus:9090
* PostgreSQL: host=todo-db:5432 database=tododb user=todo password=todo ssl=disable

### InspectIT Ocelot
[InspectIT Ocelot](https://github.com/inspectIT/inspectit-ocelot) collects metrics of the application and exposes them at localhost:8888.
Prometheus is configured to scrape this endpoint and additional Grafana Dashboards are added that use these metrics.

## Kubernetes
Folder "Kubernetes" holds config files for deploying the app with Prometheus and Grafana to a Kubernetes cluster.
Until now only deployment on local cluster with Minikube has been tested. 
The Folder "Kompose" holds Kubernetes config files generated with [Kompose](http://kompose.io/). 
These files won't create a working application and were merely used as templates.

### Prerequisites
* Kubernetes cluster (e.g. local [Minikube installation](https://kubernetes.io/docs/setup/minikube/))
* For the todo application the image from your local docker repository is used so be sure to build the app and the image first.
* The app is deployed to the custom namespace "todo-application" which needs to be created first on your cluster with `kubectl create ns todo-application`.

### Access
After deploying the app on your cluster the endpoints will become available on the internal IP address of your node.
Get it with `kubectl get nodes -o wide`. 
The ports of the services are specified in the config files with the "NodePort" tag:
* todo-service: 31555
* InspectIT: 31888
* Prometheus: 31000
* Grafana: 31300

With Minikube you can access a dashboard that shows the availability of your cluster and the ressources within.
Command `kubectl proxy` makes the dashboard available at localhost:8001.