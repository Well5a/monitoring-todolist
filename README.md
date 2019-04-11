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
mvn clean install
```

## App usage (mappings)
* http://localhost:5555/ui for list of todos
* `curl -X POST localhost:5555/task` to add a task to the list
* `curl -X DELETE localhost:5555/id` to delete a task from the list

## Start the app with Docker-Compose
Run `docker-compose up` in root directory to start the app.

This will automatically pull the images from Dockerhub, you can find the todolist app [here](https://hub.docker.com/r/well5a/todolist).
You can also get the image
* locally: Build the app, then the image with `docker build -t todolist .` in project root directory
* or by pulling it manually from Dockerhub: `docker pull well5a/todolist`

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

The InspectIT Ocelot Docker image saves the agent jar on a provided volume.
Other microservices can access this volume to get the agent and inject it via an overwritten entrypoint.

### PostgreSQL Exporter (database metrics)
PostgreSQL saves various metrics about the database server in form of a set of tables (e.g. with the [Statistics Collector](https://www.postgresql.org/docs/11/monitoring-stats.html)). 
The [PostgreSQL Exporter](https://github.com/wrouesnel/postgres_exporter) exports and exposes these metrics on default port 9187 for use by Prometheus.
A dashboard for these metrics is also included.

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
* Prometheus: 31090
* Grafana: 31300

The todo-service includes the app, the database and the database metric exporter.

With Minikube you can access a dashboard that shows the availability of your cluster and the ressources within.
Command `kubectl proxy` makes the dashboard available at localhost:8001.
