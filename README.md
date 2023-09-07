# BotsCrewUniversityBot

Simple Spring Boot java project, written using Telegram api for University.
Which consists of departments and lectors.
The lectors could work in more than one department. 
A lector could have one degree (assistant, associate professor, professor).

All data is stored in the relational database.

## Features
### Bot can handle various of commands described bellow:
- Show all departments
- Show all employees
- Who is head of department {department_name}
- Show {department_name} statistics
- Show the average salary for the department {department_name}
- Show count of employee for {department_name}
- Global search by {template}

## Setup guide
Clone this repository.
####
To run this project locally on your computer, you need to make sure you have the following components installed:
* JDK 17 ([installation guide](https://docs.oracle.com/en/java/javase/17/install/overview-jdk-installation.html#GUID-8677A77F-231A-40F7-98B9-1FD0B48C346A))
* Apache Maven ([download here](https://maven.apache.org/download.cgi), [installation guide](https://maven.apache.org/install.html))
* Docker ([installation guide](https://docs.docker.com/desktop/install/windows-install/))

### Application configuration
#### Connect DB to project
Docker was used to connect to the PostgreSQL database.
To do this, you need to start the container with the following command:

`docker run --name container_name -p 5432:5432 -e POSTGRES_PASSWORD=password -d username`

Fill in the fields with the values that you specified when creating the connection in MySql Workbench.
Also you need to edit fields in file [application.properties](src%2Fmain%2Fresources%2Fapplication.properties) according to previously entered values.
```
db.url= YOUR_DATABASE_URL
db.user= YOUR_USER_NAME
db.password= YOUR_PASSWORD
```