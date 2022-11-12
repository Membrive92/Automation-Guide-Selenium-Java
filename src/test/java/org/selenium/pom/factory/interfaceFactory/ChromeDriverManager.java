package org.selenium.pom.factory.interfaceFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverManager implements DriverManager{

    @Override
    public WebDriver createDriver() {
        WebDriverManager.chromedriver().cachePath("src/test/resources/Drivers").setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }
}
