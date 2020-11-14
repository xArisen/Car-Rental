Main branch with working application.


<!-- TABLE OF CONTENTS -->
## Table of Contents

* [About the Project](#about-the-project)
  * [Built With](#built-with)
* [Getting Started](#getting-started)
  * [Prerequisites](#prerequisites)
  * [Installation](#installation)
* [Usage](#usage)
* [Contact](#contact)



<!-- ABOUT THE PROJECT -->
## About The Project

Project created in 2 months by a team of 4 people, including 2 front-end developers and 2 back-end developers. My part was making REST API (work time: 1 month). That is why in description I will focuse more on that part of application. WebApp allows any company to improve car rental managment. No need of writing emails, waiting for response, checking if car is actually available (not crashed or already rented). There are 2 types of accounts in the application: administrator and user. Both of account types comes with ton of functionalities.

User functionalities:
* Reservating avaliable car selected from database by: date (start, end), not suspended beacause of any fault. User can choose one of proposed cars and write argumentation why he choose this car
* Give the car back. Optional functionalities: report a bug (need to be checked by administrator), change the place of leaving the car
* See pending, current and history reservations
* Changing account email, phone number, password

Administrator functionalities:
* See pending, active reservations
* Checking rent requests: agree/disagree, change car (write justinfication)
* Adding new car to database (full specification, photo)
* Managing cars from database (editing informations, reporting issues, suspending, removing car)
* Adding new employee to database
* Editing employee basic info and remove him from database

Backend additional functionalities:
* Sending car photos, which are being saved in project folder
* JSON web token (logging connected with database, account permissions with different roles)
* Additional H2 database used for testing (active profile)
* DataBaseLoader - filling the external database at application startup (using CommandLineRunner or data.sql file)
* Entrance data validation

### Built With
Backend:
* Java 11
* Spring framework
* PostgreSQL - external database
* H2 - internal database
* Gradle
* junit5

Frontend:
* React.js

<!-- GETTING STARTED -->
## Getting Started

Instructions on setting up your project locally.
To get a local copy up and running follow these steps.

### Prerequisites

List of things you need to use the software.
* npm
```sh
npm install npm@latest -g
```
* PostrgeSQL database

### Installation

Clone the repo
```sh
git clone https://github.com/xArisen/Car-Rental.git
```
Back-end:
1. Create database (name, username and password located in application.yml file)
2. Create second run/debug configuration ("CarRentalApplication (h2)") with Active profiles: h2.
3. Start application on first configuration ("CarRentalApplication")

Front-end:
1. Go to frontend/car-rental-app
2. Install NPM packages
```sh
npm install
```
3. Start application
```sh
npm start
```


## Usage
Back-end port: localhost:8080                                            
Endpoints located in "controllers" folder files.

Front-end port: localhost:3000

<!-- CONTACT -->
## Contact

Bartosz Czapiewski - czapiewskipiotr04@gmail.com

Project Link: [https://github.com/xArisen/Car-Rental](https://github.com/xArisen/Car-Rental)

