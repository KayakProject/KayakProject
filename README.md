# KayakProject

## Steps
1. Pick one test case
2. Find the locator of the elements needed with the web or mobile inspector
3. Add the locators in src > test > locators > mobileLocators/webLocators > the right class
   * example : `By anyLocatorsName = By.xpath("any xpath");`
4. Create the method used to interact with the element in src > test > pageObjects > mobilePo/webPo > the right class
   * example : `public void clickConnectBtn(){}`
