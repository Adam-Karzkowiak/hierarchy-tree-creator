# hierarchy-tree-creator
## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)

## General info
App to create hierarchy tree from String input formatted in CSV form. Many roots? It's not a problem :) Time and space complexity - O(n). 
	
## Technologies
Project is created with:
* Java v 11
* Spring Boot v 2.4.5 (DATA, WEB, VALIDATION)
* H2Datbase
* Docker
* OpenCSV 5.4
* 
	
## Setup
produce docker container with pre-built server:  
docker build -t hierarchy .  
command to start the server within the container:  
docker run --rm -p 8080:8080 hierarchy  
