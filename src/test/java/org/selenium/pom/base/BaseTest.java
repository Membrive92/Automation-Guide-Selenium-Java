package org.selenium.pom.base;

import org.openqa.selenium.WebDriver;
import org.selenium.pom.factory.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;

    @Parameters("browser")
    @BeforeMethod
    public void startDriver(String browser){
        driver = new DriverManager().initializerDriver(browser);
    }

    @AfterMethod
    public void quitDriver() throws InterruptedException {
        //fix connection reset error
        Thread.sleep(100);
        driver.quit();
    }
}
