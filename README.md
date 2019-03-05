# monitoring-todolist
Modification of todolist app for use as a microservice in bachelor thesis "Monitoring Microservices in the Cloud".

original app: https://github.com/Well5a/vertsys-todolist

## Changes
* uses PostgreSQL database instead of MySQL
* updated to latest Spring Boot release (currently 2.1.3)
* changed server port to 5555

## Build the app:
```
mvn clean package
```

## App usage (mappings):
* http://localhost:5555/ui for list of todos
* `curl -X POST localhost:5555/task` to add a task to the list
* `curl -X DELETE localhost:5555/id` to delete a task from the list
