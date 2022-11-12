package org.selenium.pom.factory.abstractFactory;

import org.selenium.pom.constants.BrowserType;

public class DriverManagerFactoryAbstract {

    public static DriverManagerAbstract getManager(BrowserType browserType){
        switch (browserType){
            case CHROME -> {
                return new ChromeDriverManagerAbstract();
            }
            case FIREFOX -> {
                return new FirefoxDriverManagerAbstract();
            }
            default -> throw new IllegalStateException("Invalid driver: " + browserType);
        }
    }
}
