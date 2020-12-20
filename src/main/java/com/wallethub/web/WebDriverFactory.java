package com.wallethub.web;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Scope("cucumber-glue")
public class WebDriverFactory {

    private static final Logger logger = LoggerFactory.getLogger(WebDriverFactory.class);
    private WebDriver webDriver;

    @Value("${browser}")
    private String browser;

    @Value("${geckodriver}")
    private String geckoDriver;

    @Value("${chromedriver}")
    private String chromeDriver;

    @Value("${webdriver.wait.secs}")
    private int webDriverWait;

    @Bean
    @Scope("cucumber-glue")
    public void setUpWebDriver() throws IllegalStateException {
        switch (browser.toLowerCase()) {
            case "firefox":
                logger.info("Running Firefox Web Driver with Driver Path:- {}", geckoDriver);
                //System.setProperty("webdriver.gecko.driver", geckoDriver);
                WebDriverManager.firefoxdriver().setup();
                webDriver = new FirefoxDriver();

                break;
            case "chrome":
                logger.info("Running Chrome Web Driver with Driver Path:- {}");
                Map<String, Object> prefs = new HashMap<String, Object>();
                prefs.put("profile.default_content_setting_values.notifications", 2);
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("disable-infobars");
                chromeOptions.addArguments("--disable-popup-blocking");
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.setExperimentalOption("prefs", prefs);
                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver(chromeOptions);
                webDriver.manage().deleteAllCookies();
                break;
            default:
                String errorMessage = String.format("%s is not a recognised option.", browser);
                throw new IllegalStateException(errorMessage);
        }
        logger.info(String.format("Browser is set to %s", browser));
    }

    public final WebDriver getWebDriver() {	return webDriver; }
}
