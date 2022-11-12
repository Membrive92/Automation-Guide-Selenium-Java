package org.selenium.pom.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.selenium.pom.constants.BrowserType;
import org.selenium.pom.utils.ConfigLoader;

import java.io.IOException;

public class DriverManagerOrigin {
    WebDriver driver;
    public WebDriver initializerDriver(String browser){
        switch (BrowserType.valueOf(browser)) {
            case CHROME -> {
                WebDriverManager.chromedriver().cachePath("src/test/resources/Drivers").setup();
                driver = new ChromeDriver();
            }
            case FIREFOX -> {
                WebDriverManager.firefoxdriver().cachePath("src/test/resources/Drivers").setup();
                driver = new FirefoxDriver();
            }
            default -> throw new IllegalStateException("Invalid browser name: " + browser);
        }
        driver.manage().window().maximize();
        return driver;
    }

    public WebDriver initializerDriverWithProps() throws IOException {
        String browserName = ConfigLoader.getInstance().getBrowser();

        switch (browserName) {
            case "chrome" -> {
                WebDriverManager.chromedriver().cachePath("src/test/resources/Drivers").setup();
                driver = new ChromeDriver();
            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().cachePath("src/test/resources/Drivers").setup();
                driver = new FirefoxDriver();
            }
            default -> throw new IllegalStateException("Invalid browser name: " + browserName);
        }
        driver.manage().window().maximize();
        return driver;
    }
}




