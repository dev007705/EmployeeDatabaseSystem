# EmployeeDatabaseSystem
This JWT Authentication based employee database system has been created by using spring boot and mongoDB and dockerize by using Docker.
For dockerizing spring boot application and MongoDB, run these command on CMD- 
**docker pull mongo:latest
docker run -d -p 27017:27017 --name devmongo mongo:latest
docker build -t devspringboot-mongodb:1.0 .
docker run -p 8080:8080 --name devspringboot-mongodb --link devmongo:mongo -d devspringboot-mongodb:1.0
docker logs devspringboot-mongodb**
Now you can run the application on localhost.
