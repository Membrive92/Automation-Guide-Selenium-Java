package org.selenium.pom.base;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.factory.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.sql.Driver;

public class BaseTest {
  //  private ThreadLocal <WebDriver> driver = new ThreadLocal<>();
    protected WebDriver driver;

  /*  private void setDriver(WebDriver driver){
        this.driver.set(driver);
    }

    protected WebDriver getDriver(){
      return this.driver.get();
    }
   /* @Parameters("browser")
    @BeforeMethod
    //public void startDriver(String browser){*/


    @Before
    public void startDriver(){
      //  browser = System.getProperty("browser" , browser);
        String browser = System.getProperty("browser");
        driver = new DriverManager().initializerDriver(browser);
    /*   setDriver(new DriverManager().initializerDriver(browser));
        System.out.println("CURRENT THREAD: " + Thread.currentThread().getId() + ", " +
                "DRIVER = " + getDriver());*/
        System.out.println("CURRENT THREAD: " + Thread.currentThread().getId() + ", " +
                "DRIVER = " + driver);

    }

  //  @AfterMethod
    @After
    public void quitDriver() throws InterruptedException {
        //fix connection reset error
        Thread.sleep(100);
      /*  System.out.println("CURRENT THREAD: " + Thread.currentThread().getId() + ", " +
                "DRIVER = " + getDriver());
        getDriver().quit();*/
        System.out.println("CURRENT THREAD: " + Thread.currentThread().getId() + ", " +
                "DRIVER = " + driver);
        driver.quit();
    }
}
