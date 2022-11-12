package org.selenium.pom.base;



import io.restassured.http.Cookies;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.constants.BrowserType;
import org.selenium.pom.factory.abstractFactory.DriverManagerAbstract;
import org.selenium.pom.factory.abstractFactory.DriverManagerFactoryAbstract;
import org.selenium.pom.utils.CookieUtils;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import ru.yandex.qatools.ashot.shooting.ShootingStrategy;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class BaseTest {
    private ThreadLocal <DriverManagerAbstract> driverManager = new ThreadLocal<>();
    private ThreadLocal <WebDriver> driver = new ThreadLocal<>();
   private void setDriverManager(DriverManagerAbstract driverManager){
        this.driverManager.set(driverManager);
    }

    protected DriverManagerAbstract getDriverManager(){
      return this.driverManager.get();
    }

    private void setDriver(WebDriver driver){
        this.driver.set(driver);
    }

    protected WebDriver getDriver(){
        return this.driver.get();
    }

    @Parameters("browser")
    @BeforeMethod
    public synchronized void startDriver(@Optional String browser){
          browser = System.getProperty("browser" , browser);
       // if (browser == null) browser = "CHROME";
/*
           Using Builder pattern
       setDriver(new DriverManagerOrigin().initializerDriver(browser));

           Using Factory Design pattern with interface
        setDriver(DriverManagerFactory.getManager(BrowserType.valueOf(browser)).createDriver()); */


        setDriverManager(DriverManagerFactoryAbstract.
                getManager(BrowserType.valueOf(browser)));
        setDriver(getDriverManager().getDriver());
        System.out.println("CURRENT THREAD: " + Thread.currentThread().getId() + ", " +
                "DRIVER = " + getDriver());
    }

    @Parameters("browser")
    @AfterMethod
    public synchronized void quitDriver(@Optional String browser, ITestResult result) throws InterruptedException, IOException {
       Thread.sleep(300);
       System.out.println("CURRENT THREAD: " + Thread.currentThread().getId() + ", " +
                "DRIVER = " + getDriverManager().getDriver());
       // getDriver().quit();

        if(result.getStatus() == ITestResult.FAILURE){
            File destinationFile = new File("src/test/resources/screenshots" + File.separator+ browser + File.separator +
                    result.getTestClass().getRealClass().getSimpleName() + "_" + result.getMethod().getMethodName() +
                    ".png");
           // takeScreenshot(destinationFile);
            takeScreenshotUsingAshot(destinationFile);
        }
        getDriverManager().getDriver().quit();
    }

    public void injectCookiesToBrowser(Cookies cookies){
        List<Cookie> seleniumCookies = new CookieUtils().convertRestAssuredCookiesToSeleniumCookies(cookies);
        for (Cookie cookie: seleniumCookies){
            getDriver().manage().addCookie(cookie);
        }

    }

    private void takeScreenshot(File destinationFile) throws IOException {
        TakesScreenshot takesScreenshot = (TakesScreenshot) getDriver();
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(sourceFile, destinationFile);
    }

    private void takeScreenshotUsingAshot(File destinationFile){
        Screenshot screenshot = new AShot()
               .shootingStrategy(ShootingStrategies.viewportPasting(100))
               .takeScreenshot(getDriver());
        try {
            ImageIO.write(screenshot.getImage(), "PNG", destinationFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
