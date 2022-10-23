package org.selenium.pom.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager {

    public WebDriver initializerDriver(){
     //   WebDriverManager.chromedriver().cachePath("src/test/resources/Drivers").setup();
        WebDriverManager.firefoxdriver().cachePath("src/test/resources/Drivers").setup();
       // System.setProperty("webdriver.chrome.driver", "src/test/resources/driver/chromedriver.exe");
       // WebDriver driver = new ChromeDriver();
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        return driver;
    }
}
