# monitoring-todolist
Modification of todolist app for use as a microservice in bachelor thesis "Monitoring Microservices in the Cloud".

original app: https://github.com/Well5a/vertsys-todolist

## Changes
* uses PostgreSQL database instead of MySQL
* updated to latest Spring Boot release (currently 2.1.3)
* changed server port to 5555
* implemented monitoring tool chain with micrometer, prometheus and grafana

## Build the app
```
mvn clean package
```

## App usage (mappings)
* http://localhost:5555/ui for list of todos
* `curl -X POST localhost:5555/task` to add a task to the list
* `curl -X DELETE localhost:5555/id` to delete a task from the list

## Start the app with Docker-Compose
* get PostgreSQL Docker image
* build image of the app: `docker build -t todolist .`
* run: 'docker-compose up' in root directory to start the app

## Metrics Monitoring
* Prometheus: http://localhost:9090
* Grafana: http://localhost:3000
* Grafana login: user=admin password=todo

### Add datasources
* For Prometheus just specify the above address
* PostgreSQL: host=todo-db:5432 database=tododb user=todo password=todo ssl=disable
