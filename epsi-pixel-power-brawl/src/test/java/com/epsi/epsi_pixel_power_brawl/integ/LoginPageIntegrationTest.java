package com.epsi.epsi_pixel_power_brawl.integ;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginPageIntegrationTest {

    private WebDriver driver;
    private WebDriverWait wait;
    
    @Value("${test.username}")
    private String username;

    @Value("${test.password}")
    private String password;
    
    @Value("${test.frontendurl}")
    private String frontEndURL;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testLoginPage() {
        driver.get(frontEndURL+"/login");

        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login"));

        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
        
        wait.until(ExpectedConditions.urlToBe(frontEndURL+"/team-choosing"));

        String currentUrl = driver.getCurrentUrl();
        assert currentUrl.equals(frontEndURL+"/team-choosing");
    }

    @AfterEach
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}