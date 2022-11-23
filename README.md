
Automation Framework based on Selenium-java.
This framework is build from basic, using bad practices and starting tests until finishing an readable, scalable and maintanable framework

Bad practice that are fix:

- Hardcoded test data  -  Constants/Enums
- Hardcoded static Text  - XMLs and props files
- Hardcoded driver executable path
- Code duplication - Page object model
- Non-Atomic test   -  create atomic tests
- Non-readable test - Use builder pattern
- Duplicate steps tests - discrete tests
- User state dependence - create state
- Multiple enviroment support  - create specifics files for each enviroment
- Multiple browser support  - Create specific methods for browser
- Static sleeps  -  Explicits waits
- Poor readibility  - Page object model and Specifics method names


Dependencies used in framework:

- Selenium: used for web automation
- Maven: Management projects tool used for manage dependencies and launch tests.
- Testng: Framework based on Junit thanks their annotations helps to develop tests easily
- Jackson-databind: used for created Object mapper for mapping json file to object
- JavaFaker: used for generating data
- Webdriver manager io.github.bonigarcia: used for manage and auto generating webdrivers for automation
- Allure report: used for generate reports based on automation results
- ashot: used for take full screen screenshots.
- Rest-assured: used for creating API tests
- Jsoup: used for work with html elements.


Launching tests:
- Java requiered
- Maven required
- "maven clean tests" on root directori in a terminal
- Executing testng.xml for launching tests with the configuration write in this file