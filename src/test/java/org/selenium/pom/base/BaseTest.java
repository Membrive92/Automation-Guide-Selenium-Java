package org.selenium.pom.base;

import org.openqa.selenium.WebDriver;
import org.selenium.pom.factory.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.time.Duration;

public class BaseTest {
    private ThreadLocal <WebDriver> driver = new ThreadLocal<>();

    private void setDriver(WebDriver driver){
        this.driver.set(driver);

    }

    protected WebDriver getDriver(){
      return this.driver.get();
    }
    @Parameters("browser")
    @BeforeMethod
    public void startDriver(String browser){
        browser = System.getProperty("browser" , browser);
       setDriver(new DriverManager().initializerDriver(browser));
        System.out.println("CURRENT THREAD: " + Thread.currentThread().getId() + ", " +
                "DRIVER = " + getDriver());
    }

    @AfterMethod
    public void quitDriver() throws InterruptedException {
        //fix connection reset error
        Thread.sleep(100);
        System.out.println("CURRENT THREAD: " + Thread.currentThread().getId() + ", " +
                "DRIVER = " + getDriver());
        getDriver().quit();
    }
}
