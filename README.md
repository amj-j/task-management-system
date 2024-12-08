# Task Management System Web App

This app is a project that I built to practice my Spring boot and webapp development skills. It doesn't serve any real life purpose.

I tried to implement Spring security, but I wasn't able to find any tutorial or guide that wasn't way too basic, or way too complicated or deprecated.

## Instalation with Docker

### Prerequisites
You need to have Docker compose installed on your computer.

### Instalation
1. In your terminal, navigate to the directory inside which you wish to download this repository
2. Clone this repository using command ```git clone https://github.com/amj-j/task-management-system.git```
3. Navigate inside the new directory which contains the cloned repository created by the previous command.
4. If your port ```8080``` is already occupied, in the ```docker-compose.yml``` file change the port configuration on line 8 from ```8080:8080``` to ```8081:8080``` or choose any other vacant port.
5. Execute this command to run the app: ```docker-compose up --build```. If ```docker-compose``` is unrecognized, run ```docker compose up --build```.
6. Open your browser and type ```localhost:8080``` (or replace ```8080``` with the port you chose in step 4). The index page of the app will open in your browser.
7. To terminate the app, go back to your terminal and press ```Ctrl + C```. This will kill the Docker compose process.

## Instalation without Docker

### Prerequisites

### Instalation
