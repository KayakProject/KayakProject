# KayakProject

## Table of contents
1. [Setup](#setup)
2. [Steps to follow after the setup](#steps-to-follow-after-the-setup)
3. [Test cases](#test-cases)
4. [Steps before doing automation](#steps-before-doing-automation)
5. [Start code automation](#start-code-automation)
6. [Project structure](#project-structure)
7. [Git commands](#git-commands)
8. [Tips](#tips)

## Setup
1. Download Java 15.0.1: https://jdk.java.net/archive/ and extract the zip folder

2. Install Java 15.0.1 on your IDE
   * Intellij: https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk
   * Eclipse: 
     * create a project in your workspace
     * Go to Project > Properties > Java Compiler 
     * Set the JDK Compliance to 15
     * Go to Window > Preferences > Java > Installed JRE's tab
     * Click Add
     * Choose Standard VM
     * Click Directory and add Java 15.0.1 downloaded in step 1

     
3. Create a Pixel_5 in the emulator (Android 13.0 Google APIs, x86_64)
   1. Open Device Manager
   2. Select Create device
   3. In Phone, choose Pixel 5 and Next
   4. Select Tiramisu and Next
   5. Click Finish
   

## Steps to follow after the setup
1. Create a folder where you want to put your project
2. Create a new project in your IDE located in the folder created

| WARNING: Make sure to select the right Java version (15.0.1) when creating the project |
   | --- |

2. Press Alt + CTL + T. *This will open the command line from Eclipse*
3. In the command line from the IDE, enter `git clone https://github.com/KayakProject/KayakProject.git`
4. Then enter `git branch ANY_NAME`
5. Then enter `git checkout THE_NAME_YOU_ENTERED`
6. Build the libraries using Maven in the pom.xml file
7. Copy and paste the file kayak.apk located at src > main > java > resources > app in any location on your computer
8. Run the Pixel_5 emulator
9. Drag and drop the file pasted in the emulator


## Test Cases
One sheet per module will be made. 
For example, if a sheet has the name "Authentication", the test case number will be test_A_NUMBER (e.g. test_A_001_METHODNAME)
<img width="789" alt="Screen Shot 2023-04-23 at 10 53 24 PM" src="https://user-images.githubusercontent.com/88994997/234141276-1af7a69b-01e1-4147-9b67-8618e7a69bd9.png">
<img width="594" alt="Screen Shot 2023-04-23 at 10 53 35 PM" src="https://user-images.githubusercontent.com/88994997/234142784-4ba76270-62ac-4fc2-bafb-5ada27abef95.png">



## Steps before doing automation
1. Start the appium server on Appium Desktop
2. Turn on the Pixel_5 in the emulator
3. Go into testng.xml
4. Replace tests.webTests.TestAuthentication by your class used for test

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
Use this file if a data can have multiple states.

#### strings.xml: 
Stores all string from the website we need.

#### config.properties: 
Stores variables needed for launching a connexion with appium or chrome driver.

## Git Commands
- Clone repository: `git clone [url]`
- Create a new branch: `git branch [name]`
- Switch into a branch: `git checkout [name]`
- Pull the code from remote git repository: `git pull`
- Check which file are added or committed: `git status`
- Add file into staging area: `git add [file]`
- Add file into local git repository `git commit -m “[descriptive message]”`
- Add file into remote git repository: `git push`

| WARNING: Always check that you are not into the main branch before adding any file into the staging area by doing `git status` |
   | --- |

<img width="674" alt="Screen Shot 2023-04-24 at 8 16 42 PM" src="https://user-images.githubusercontent.com/88994997/234143803-81dcaffa-0561-40df-9d84-66ade11e2dbc.png">

## Good Practices
- To run tests without opening the Chrome browser, uncomment the line 26 in the class BaseTestWeb (*headless browser*)
- Regarding the number of test cases, let's add the number of the test case to link it with the code
  - Example: `public void test_A_001_authenticationInvalidUsername(){}`


