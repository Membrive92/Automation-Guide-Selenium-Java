package org.selenium.pom.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager {

    public WebDriver initializerDriver(){
        WebDriverManager.chromedriver().cachePath("src/test/resources/Drivers").setup();
       // System.setProperty("webdriver.chrome.driver", "src/test/resources/driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }
}
