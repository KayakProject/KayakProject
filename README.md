# KayakProject

Simple automation of some functionalities of the Kayak travel booking website (https://www.ca.kayak.com/).

## Requirements
- Java 11 or above
- Maven
- Android emulator
- Appium

## Setup
1. Start any Android emulator
2. Build the Maven pom.xml
3. Launch Appium server

## Launch
Run the testng.xml file for the results of the web tests and testngmobile.xml for the results of the mobile tests.

## Project Structure
The project structure allows to keep a code more readable and maintainable.
It means that if the developers of the Kayak website change a locator or text or if we decide to update the data we need, we will need to update the code in a single line.

### Listeners class
- It takes screenshots on test fail
- It updates the result of the status and provides explanation if a test has failed directly into the excel report

### PageObject classes
Used to add locators and interactions with the elements

### Test classes
Used to add the methods for tests. It calls methods from the pageObjects classes.

### Utilities
#### Common/Mobile/Web utilities
Includes methods to : 
- handle exceptions
- get current date
- select elements 
- check if a document has been downloaded on user computer
- read data from an excel file
- write data into an excel file

#### JSONReader
Used to enter all data we decided to use in the test cases

#### StringsReader
Used to enter all strings we need to use in the project

### Resources
#### data.json:
Stores all the data we chose for the test cases.
Use this file if a data can have multiple states.

#### strings.xml:
Stores all string from the website we need.

#### config.properties:
Stores variables needed for launching a connexion with appium or chrome driver.