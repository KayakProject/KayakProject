# KayakProject

## Table of contents
1. [Setup](#setup)
2. [Steps to follow after the setup](#steps-to-follow-after-the-setup)
3. [Steps before doing automation](#Steps before doing automation)
4. [Start code automation](#Start Code Automation)
5. [Project structure](#Project Structure)
6. [Git commands](#Git Commands)
7. [Tips](#tips)

## Setup
1. Download Java 15.0.1: https://www.oracle.com/java/technologies/javase/jdk15-archive-downloads.html

| WARNING: Don't pick Java 15.0.2 (both are on the same page) |
   | --- |

2. Install Java 15.0.1 on your IDE
   * Intellij: https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk
   * Eclipse: 

3. Install Appium CLI
   * Uninstall your appium: `npm uninstall -g appium` in your command line
   * Then: 
     * Mac:
         1. `/usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"`
         2. `sudo npm install -g appium@next --unsafe-perm=true --allow-root`
     * Windows: `install -g appium@next`
     * For both: `appium driver install uiautomator2`

     
4. Create a Pixel_5 in the emulator (Android 13.0 Google APIs, x86_64)



## Steps to follow after the setup
1. Create a folder where you want to put your project
2. Create a new project in your IDE located in the folder created

| WARNING: Make sure to select the right Java version (15.0.1) when creating the project |
   | --- |

3. In the command line from the IDE, enter `git clone https://github.com/KayakProject/KayakProject.git`
4. Then enter `git branch ANY_NAME`
5. Then enter `git checkout THE_NAME_YOU_ENTERED`
6. Build the libraries using Maven in the pom.xml file
7. Copy and paste the file kayak.apk located at src > main > java > resources > app in any location on your computer
8. Run the Pixel_5 emulator
9. Drag and drop the file pasted in the emulator


## Steps before doing automation
1. Open command line and write: `appium`. *This will start the appium server*
2. Turn on the Pixel_5 in the emulator
3. Go into testng.xml
4. Replace tests.webTests.TestConnexionWeb by your class used for test

## Start Code Automation
1. Pick one test case
2. Find the locator of the elements needed with the web or mobile inspector
3. Add the locators in src > test > locators > mobileLocators/webLocators > the right class
   * example : `By anyLocatorsName = By.xpath("any xpath");`
4. Create the method used to interact with the element in src > test > pageObjects > mobilePo/webPo > the right class
   * example : `public void poClickConnectBtn(){}`
5. Create the test in src > test > tests > mobileTests/webTests > the right class
6. Run the testng.xml file


## Project Structure
The project structure allows to keep a code more readable and maintainable. 
It means that if the developers of the Kayak website change a locator or text or we decide to update the data we need, we will need to update the code in a single line. 
### Listeners class 
Used to add a behavior if a fail test. It also takes screenshots

### PageObject classes
Used to add locators and interactions with the elements

### Test classes
Used to add the methods for tests. It calls methods from the pageObjects classes

### Utilities
#### Common/Mobile/Web utilities
Includes methods to handle exceptions and get current date.

WARNING: in the PageObject classes, if you need to find an element use `utils.waitForElement(LOCATOR);`
instead of `driver.findElement(LOCATOR);`


#### JSONReader
Used to enter all data we decided to use in the test cases
WARNING: if you need to use data in the PageObject classes or Test classes, code `jsonReader.getStringJsonObject("data" + File.separator + "data.json", "KEY USED", "VALUE USED");`

#### StringsReader
Used to enter all strings we need to use in the project
WARNING: to get a String from the strings file, code `stringsReader.readStringsXML("XML NAME OF THE STRING");`

### Resources
#### data.json: 
Stores all the data we chose for the test cases.

#### strings.xml: 
Stores all string from the website we need.

#### config.properties: 
Stores variables needed for launching a connexion with appium or chrome driver.

## Git Commands

## Tips
- To run tests without opening the Chrome browser, uncomment the line 26 in the class BaseTestWeb
- Regarding the number of test cases, let's add the number of the test case to link it with the code
  - Example: `public void test_001_authenticationInvalidUsername(){}`


