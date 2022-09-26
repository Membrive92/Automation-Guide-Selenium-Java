package org.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class MyFirstTestCase {

    @Test
    public void dummyTest(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://askomdch.com");
    }
}
