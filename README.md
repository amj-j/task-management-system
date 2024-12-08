# Task Management System Web App

This app is a project that I built to practice my Spring boot and webapp development skills. It doesn't serve any real life purpose.

I tried to implement Spring security, but I wasn't able to find any tutorial or guide that wasn't way too basic, or way too complicated or deprecated.

## Installation with Docker

### Prerequisites
- Docker compose

### Installation
1. In your terminal, navigate to the directory inside which you wish to download this repository
2. Clone this repository using command ```git clone https://github.com/amj-j/task-management-system.git```
3. Navigate inside the new directory which contains the cloned repository created by the previous command.
4. If your port ```8080``` is already occupied, in the ```docker-compose.yml``` file change the port configuration on line 8 from ```8080:8080``` to ```8081:8080``` or choose any other vacant port.
5. Execute this command to run the app: ```docker-compose up --build```. If ```docker-compose``` is unrecognized, run ```docker compose up --build```.
6. Open your browser and type ```localhost:8080``` (or replace ```8080``` with the port you chose in step 4). The index page of the app will open in your browser.
7. To terminate the app, go back to your terminal and press ```Ctrl + C```. This will kill the Docker compose process.

## Installation without Docker

### Prerequisites
- JDK 17 or higher
- Maven
- MySQL

### Installation
1. Create a new database in your MySQL that will serve as a database for this webapp
2. In your terminal, navigate to the directory inside which you wish to download this repository
3. Clone this repository using command ```git clone https://github.com/amj-j/task-management-system.git```
4. Open the ```src/main/resources/application.properties``` file in your IDE.
5. Replace the following (this disrupts the Docker compose setup):
   - ```${SPRING_DATASOURCE_URL}``` with ```jdbc:mysql://mysql:3306/your_db_name``` (instead of ```3306```, enter the port on which your MySQL database is running on and instead of ```your_db_name``` the name of your database created in step 1.)
   - ```${SPRING_DATASOURCE_USERNAME}``` with your MySQL database username you wish to use
   - ```${SPRING_DATASOURCE_PASSWORD}``` with your MySQL database password corresponding to the username
6. If your port ```8080``` is already occupied, add this line into the same ```application.properties``` file: ```server.port=8081``` and replace ```8081``` with a port you wish to run this app on.
7. In your terminal, navigate to the root directory of the repository (should be called ```task-management-system```)
8. Execute this command to download necessary dependencies and compile the app: ```mvn clean install```
9. Execute this command to run the app: ```mvn spring-boot:run```
10. Open your browser and type ```localhost:8080``` (or replace ```8080``` with the port you chose in step 4). The index page of the app will open in your browser.
11. To terminate the app, go back to your terminal and press ```Ctrl + C```. This will kill the Docker compose process.
